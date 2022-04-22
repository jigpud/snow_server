package com.jigpud.snow.service.user;

import com.jigpud.snow.model.User;
import com.jigpud.snow.repository.user.UserRepository;
import com.jigpud.snow.response.PageData;
import com.jigpud.snow.service.token.TokenService;
import com.jigpud.snow.util.encrypt.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : jigpud
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Autowired
    UserServiceImpl(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Override
    public void register(String username, String password) {
        User newUser = new User();
        newUser.setSalt(Encryptor.uuid());
        newUser.setUsername(username);
        newUser.setPassword(Encryptor.hmacSHA256Encrypt(password, newUser.getSalt()));
        newUser.setUserid(Encryptor.uuid());
        userRepository.addUser(newUser);
    }

    @Override
    public boolean haveUsernameIs(String username) {
        if (username == null || username.isEmpty()) {
            return false;
        }
        User user = userRepository.getUserByUsername(username);
        return user != null && username.equals(user.getUsername());
    }

    @Override
    public String login(String username, String password) {
        User user = userRepository.getUserByUsername(username);
        if (user != null && Encryptor.hmacSHA256Encrypt(password, user.getSalt()).equals(user.getPassword())) {
            // 密码验证成功
            return tokenService.createRefreshToken(user.getUserid());
        } else {
            // 密码验证失败
            return "";
        }
    }

    @Override
    public void update(User user) {
        userRepository.updateUser(user);
    }

    @Override
    public void updateSignature(String userid, String newSignature) {
        User user = userRepository.getUserByUserid(userid);
        user.setSignature(newSignature);
        userRepository.updateUser(user);
    }

    @Override
    public void updateNickname(String userid, String newNickname) {
        User user = userRepository.getUserByUserid(userid);
        user.setNickname(newNickname);
        userRepository.updateUser(user);
    }

    @Override
    public void updatePassword(String userid, String newPassword) {
        User user = userRepository.getUserByUserid(userid);
        String encryptedPassword = Encryptor.hmacSHA256Encrypt(newPassword, user.getSalt());
        user.setPassword(encryptedPassword);
        userRepository.updateUser(user);
    }

    @Override
    public void updateAvatar(String userid, String newAvatar) {
        User user = userRepository.getUserByUserid(userid);
        user.setAvatar(newAvatar);
        userRepository.updateUser(user);
    }

    @Override
    public boolean haveUseridIs(String userid) {
        if (userid == null || userid.isEmpty()) {
            return false;
        }
        User user = userRepository.getUserByUserid(userid);
        return user != null && userid.equals(user.getUserid());
    }

    @Override
    public void deleteUserByUsername(String username) {
        userRepository.deleteUserByUsername(username);
    }

    @Override
    public void deleteUserByUserid(String userid) {
        userRepository.deleteUserByUserid(userid);
    }

    @Override
    public String getUserid(String username) {
        User user = userRepository.getUserByUsername(username);
        if (user != null) {
            return user.getUserid();
        } else {
            return "";
        }
    }

    @Override
    public String getUsername(String userid) {
        User user = userRepository.getUserByUserid(userid);
        if (user != null) {
            return user.getUsername();
        } else {
            return "";
        }
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public User getUserByUserid(String userid) {
        return userRepository.getUserByUserid(userid);
    }

    @Override
    public PageData<User> usersUsernameLike(String username, long pageSize, long currentPage) {
        return PageData.fromPage(userRepository.usersUsernameLike(username, pageSize, currentPage));
    }

    @Override
    public PageData<User> usersNicknameLike(String nickname, long pageSize, long currentPage) {
        return PageData.fromPage(userRepository.usersNicknameLike(nickname, pageSize, currentPage));
    }

    @Override
    public PageData<User> usersUsernameAndNicknameLike(String username, String nickname, long pageSize, long currentPage) {
        return PageData.fromPage(userRepository.usersUsernameAndNicknameLike(username, nickname, pageSize, currentPage));
    }

    @Override
    public PageData<User> users(long pageSize, long currentPage) {
        return PageData.fromPage(userRepository.users(pageSize, currentPage));
    }
}
