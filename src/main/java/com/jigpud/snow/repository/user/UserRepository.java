package com.jigpud.snow.repository.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.model.User;

/**
 * @author : jigpud
 * 存储用户
 */
public interface UserRepository {
    /**
     * 保存用户
     * @param user user
     */
    void saveUser(User user);

    /**
     * 通过username获取用户
     * @param username username
     * @return user
     */
    User getUserByUsername(String username);

    /**
     * 根据userid获取用户
     * @param userid userid
     * @return user
     */
    User getUserByUserid(String userid);

    /**
     * 更新用户
     * @param user newUser
     */
    void updateUser(User user);

    /**
     * 分页获取所有用户
     * @param pageCount 单页大小
     * @param page 页码
     * @return 用户列表分页
     */
    Page<User> users(long pageCount, long page);

    /**
     * 根据username模糊查询
     * @param username username
     * @param pageCount 单页大小
     * @param page 页码
     * @return 用户列表分页
     */
    Page<User> usersUsernameLike(String username, long pageCount, long page);

    /**
     * 根据nickname模糊查询
     * @param nickname nickname
     * @param pageCount 单页大小
     * @param page 页码
     * @return 用户列表分页
     */
    Page<User> usersNicknameLike(String nickname, long pageCount, long page);

    /**
     * 根据username和nickname模糊查询
     * @param username username
     * @param nickname nickname
     * @param pageCount 单页大小
     * @param page 页码
     * @return 用户列表分页
     */
    Page<User> usersUsernameAndNicknameLike(String username, String nickname, long pageCount, long page);

    /**
     * 根据username删除一个用户
     * @param username username
     */
    void deleteUserByUsername(String username);

    /**
     * 根据userid删除一个用户
     * @param userid userid
     */
    void deleteUserByUserid(String userid);
}
