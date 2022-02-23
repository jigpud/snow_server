package com.jigpud.snow.service.user;

import com.jigpud.snow.model.User;
import com.jigpud.snow.repository.admin.AdminRepository;
import com.jigpud.snow.repository.user.UserRepository;
import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.util.encrypt.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : jigpud
 */
@Component
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final TokenService tokenService;

    @Autowired
    UserServiceImpl(
            UserRepository userRepository,
            AdminRepository adminRepository,
            TokenService tokenService) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.tokenService = tokenService;
    }

    @Override
    public void register(String username, String password) {
        User newUser = new User();
        newUser.setSalt(Encryptor.uuid());
        newUser.setUsername(username);
        newUser.setPassword(Encryptor.hmacSHA256Encrypt(password, newUser.getSalt()));
        newUser.setUserid(Encryptor.uuid());
        userRepository.saveUser(newUser);
    }

    @Override
    public boolean hasRegistered(String username) {
        User user = userRepository.getUserByUsername(username);
        return username != null && username.equals(user.getUsername());
    }

    @Override
    public String login(String username, String password) {
        User user = userRepository.getUserByUsername(username);
        if (Encryptor.hmacSHA256Encrypt(password, user.getSalt()).equals(user.getPassword())) {
            // 密码验证成功
            String token = tokenService.createToken(user.getUserid());
            tokenService.markLogin(token);
            tokenService.markUser(token);
            return token;
        } else {
            // 密码验证失败
            return "";
        }
    }

    @Override
    public String adminLogin(String username, String password) {
        User user = userRepository.getUserByUsername(username);
        if (user != null) {
            // admin权限验证通过
            if (adminRepository.isAdmin(user.getUserid())) {
                String token = login(username, password);
                if (tokenService.verify(token) && tokenService.isLogin(token)) {
                    tokenService.markAdmin(token);
                    if (tokenService.isLogin(token) && tokenService.isAdmin(token)) {
                        return token;
                    }
                }
            }
        }
        return "";
    }

    @Override
    public void logout(String token) {
        tokenService.markLogout(token);
    }

    @Override
    public void adminLogout(String token) {
        tokenService.markUser(token);
        tokenService.markLogout(token);
    }

    @Override
    public boolean isLogin(String token) {
        return tokenService.isLogin(token);
    }

    @Override
    public boolean isAdmin(String token) {
        return tokenService.isAdmin(token);
    }
}
