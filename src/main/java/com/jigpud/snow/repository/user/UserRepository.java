package com.jigpud.snow.repository.user;

import com.jigpud.snow.model.User;

/**
 * @author : jigpud
 */
public interface UserRepository {
    void saveUser(User user);
    User getUserByUsername(String username);
    User getUserByUserid(String userid);
    void updateUser(User user);
    boolean haveUserNamed(String username);
}
