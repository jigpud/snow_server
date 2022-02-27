package com.jigpud.snow.repository.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.mapper.UserMapper;
import com.jigpud.snow.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author : jigpud
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserMapper userMapper;

    @Autowired
    UserRepositoryImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void saveUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public User getUserByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public User getUserByUserid(String userid) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public void updateUser(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        userMapper.update(user, queryWrapper);
    }

    @Override
    public Page<User> users(long pageCount, long page) {
        return userMapper.selectPage(new Page<>(page, pageCount), new QueryWrapper<>());
    }

    @Override
    public Page<User> usersUsernameLike(String username, long pageCount, long page) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", username);
        return userMapper.selectPage(new Page<>(page, pageCount), queryWrapper);
    }

    @Override
    public Page<User> usersNicknameLike(String nickname, long pageCount, long page) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("nickname", nickname);
        return userMapper.selectPage(new Page<>(page, pageCount), queryWrapper);
    }

    @Override
    public Page<User> usersUsernameAndNicknameLike(String username, String nickname, long pageCount, long page) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", username).like("nickname", nickname);
        return userMapper.selectPage(new Page<>(page, pageCount), queryWrapper);
    }

    @Override
    public void deleteUserByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        userMapper.delete(queryWrapper);
    }

    @Override
    public void deleteUserByUserid(String userid) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        userMapper.delete(queryWrapper);
    }
}
