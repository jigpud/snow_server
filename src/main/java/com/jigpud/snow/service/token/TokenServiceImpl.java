package com.jigpud.snow.service.token;

import com.jigpud.snow.config.AudienceConfig;
import com.jigpud.snow.repository.token.TokenRepository;
import com.jigpud.snow.util.encrypt.Encryptor;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @author : jigpud
 */
@Component
public class TokenServiceImpl implements TokenService {
    private final AudienceConfig audience;
    private final TokenRepository tokenRepository;

    @Autowired
    TokenServiceImpl(AudienceConfig audience, TokenRepository tokenRepository) {
        this.audience = audience;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public String createToken(String userid) {
        String encryptedUserid = Encryptor.base64Encode(userid);
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secretBytes = DatatypeConverter.parseBase64Binary(audience.getSecret());
        Key singingKey = new SecretKeySpec(secretBytes, signatureAlgorithm.getJcaName());
        Date expiration = new Date(System.currentTimeMillis() + audience.getExpire() * 1000);
        JwtBuilder jwtBuilder = Jwts.builder()
                .setSubject(encryptedUserid)
                .setAudience(audience.getName())
                .setExpiration(expiration)
                .signWith(singingKey);
        return jwtBuilder.compact();
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
            return false;
        }
    }

    @Override
    public void markLogin(String token) {
        long expiration = (getExpiration(token).getTime() - System.currentTimeMillis()) / 1000;
        tokenRepository.markLogin(token, getUserid(token), expiration);
    }

    @Override
    public void markLogout(String token) {
        tokenRepository.markLogout(token, getUserid(token));
    }

    @Override
    public void markAdmin(String token) {
        long expiration = (getExpiration(token).getTime() - System.currentTimeMillis()) / 1000;
        tokenRepository.markAdmin(token, getUserid(token), expiration);
    }

    @Override
    public void markUser(String token) {
        tokenRepository.markUser(token, getUserid(token));
    }

    @Override
    public boolean isLogin(String token) {
        return tokenRepository.isLogin(token, getUserid(token));
    }

    @Override
    public boolean isAdmin(String token) {
        return tokenRepository.isAdmin(token, getUserid(token));
    }
}
