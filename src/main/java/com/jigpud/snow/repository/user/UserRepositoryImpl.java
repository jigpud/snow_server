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
        return userMapper.selectOne(usernameQueryWrapper(username));
    }

    @Override
    public User getUserByUserid(String userid) {
        return userMapper.selectOne(useridQueryWrapper(userid));
    }

    @Override
    public void updateUser(User user) {
        String userid = user.getUserid();
        userMapper.update(user, useridQueryWrapper(userid));
    }

    @Override
    public Page<User> users(long pageCount, long page) {
        return userMapper.selectPage(new Page<>(page, pageCount), new QueryWrapper<>());
    }

    @Override
    public Page<User> usersUsernameLike(String username, long pageCount, long page) {
        return userMapper.selectPage(new Page<>(page, pageCount), usernameLikeQueryWrapper(username));
    }

    @Override
    public Page<User> usersNicknameLike(String nickname, long pageCount, long page) {
        return userMapper.selectPage(new Page<>(page, pageCount), nicknameLikeQueryWrapper(nickname));
    }

    @Override
    public Page<User> usersUsernameAndNicknameLike(String username, String nickname, long pageCount, long page) {
        return userMapper.selectPage(new Page<>(page, pageCount), usernameAndNicknameLikeQueryWrapper(username, nickname));
    }

    @Override
    public void deleteUserByUsername(String username) {
        userMapper.delete(usernameQueryWrapper(username));
    }

    @Override
    public void deleteUserByUserid(String userid) {
        userMapper.delete(useridQueryWrapper(userid));
    }

    @Override
    public Page<User> blurSearch(String keyWords, long pageCount, long page) {
        return userMapper.selectPage(new Page<>(page, pageCount), blurQueryWrapper(keyWords));
    }

    private QueryWrapper<User> usernameQueryWrapper(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return queryWrapper;
    }

    private QueryWrapper<User> useridQueryWrapper(String userid) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        return queryWrapper;
    }

    private QueryWrapper<User> usernameLikeQueryWrapper(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", username);
        return queryWrapper;
    }

    private QueryWrapper<User> nicknameLikeQueryWrapper(String nickname) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("nickname", nickname);
        return queryWrapper;
    }

    private QueryWrapper<User> usernameAndNicknameLikeQueryWrapper(String username, String nickname) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", username)
                .or()
                .like("nickname", nickname);
        return queryWrapper;
    }

    private QueryWrapper<User> blurQueryWrapper(String keyWords) {
        return nicknameLikeQueryWrapper(keyWords);
    }
}
