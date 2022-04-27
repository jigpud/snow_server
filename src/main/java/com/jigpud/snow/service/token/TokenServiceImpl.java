package com.jigpud.snow.service.token;

import com.jigpud.snow.config.AudienceConfig;
import com.jigpud.snow.repository.token.TokenRepository;
import com.jigpud.snow.util.encrypt.Encryptor;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @author : jigpud
 */
@Slf4j
@Service
public class TokenServiceImpl implements TokenService {
    private final AudienceConfig audience;
    private final TokenRepository tokenRepository;

    @Autowired
    TokenServiceImpl(AudienceConfig audience, TokenRepository tokenRepository) {
        this.audience = audience;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public String createToken(String refreshToken) {
        String encryptedUserid = Encryptor.base64Encode(getUserid(refreshToken));
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secretBytes = DatatypeConverter.parseBase64Binary(audience.getSecret());
        Key singingKey = new SecretKeySpec(secretBytes, signatureAlgorithm.getJcaName());
        Date expiration = new Date(System.currentTimeMillis() + audience.getExpiration() * 1000);
        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(encryptedUserid)
                .setAudience(audience.getName())
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(singingKey);
        return jwtBuilder.compact();
    }

    @Override
    public String createRefreshToken(String userid) {
        String encryptedUserid = Encryptor.base64Encode(userid);
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secretBytes = DatatypeConverter.parseBase64Binary(audience.getSecret());
        Key singingKey = new SecretKeySpec(secretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(encryptedUserid)
                .setAudience(audience.getName())
                .setIssuedAt(new Date())
                .signWith(singingKey);
        String refreshToken = jwtBuilder.compact();
        tokenRepository.saveRefreshToken(userid, refreshToken);
        return refreshToken;
    }

    @Override
    public boolean verifyRefreshToken(String refreshToken) {
        if (refreshToken == null || refreshToken.isEmpty()) {
            return false;
        }
        String userid = getUserid(refreshToken);
        String availableRefreshToken = tokenRepository.getRefreshToken(userid);
        return refreshToken.equals(availableRefreshToken);
    }

    @Override
    public String refresh(String refreshToken) {
        return createToken(refreshToken);
    }

    @Override
    public Claims parseToken(String token) {
        byte[] secretBytes = DatatypeConverter.parseBase64Binary(audience.getSecret());
        return Jwts.parserBuilder()
                .setSigningKey(secretBytes)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public String getUserid(String token) {
        try {
            String base64Userid = parseToken(token).getSubject();
            return Encryptor.base64Decode(base64Userid);
        } catch (Exception e) {
            log.error("getUserid", e);
            return "";
        }
    }

    @Override
    public Date getExpiration(String token) {
        return parseToken(token).getExpiration();
    }

    @Override
    public boolean isExpiration(String token) {
        return parseToken(token).getExpiration().before(new Date());
    }

    @Override
    public boolean verify(String token) {
        try {
            String userid = getUserid(token);
            if (userid != null && !userid.isEmpty()) {
                return !isExpiration(token);
            }
            return false;
        } catch (Exception e) {
            log.error("verify", e);
            return false;
        }
    }

    @Override
    public void expireRefreshToken(String userid) {
        tokenRepository.removeRefreshToken(userid);
    }
}
