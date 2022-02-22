package com.jigpud.snow.repository.user;

import com.jigpud.snow.mapper.UserMapper;
import com.jigpud.snow.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : jigpud
 */
@Component
public class UserRepositoryImpl implements UserRepository {
    private final UserMapper userMapper;

    @Autowired
    UserRepositoryImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public User getUserByUserid(String userid) {
        return userMapper.getUserByUserid(userid);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public boolean haveUserNamed(String username) {
        if (username == null || username.isEmpty()) {
            return false;
        }
        User maybe = getUserByUsername(username);
        return maybe != null && username.equals(maybe.getUsername());
    }
}
