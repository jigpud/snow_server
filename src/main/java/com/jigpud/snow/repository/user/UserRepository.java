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
    void addUser(User user);

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
     * @param pageSize 单页大小
     * @param currentPage 页码
     * @return 用户列表分页
     */
    Page<User> users(long pageSize, long currentPage);

    /**
     * 根据username模糊查询
     * @param username username
     * @param pageSize 单页大小
     * @param currentPage 页码
     * @return 用户列表分页
     */
    Page<User> usersUsernameLike(String username, long pageSize, long currentPage);

    /**
     * 根据nickname模糊查询
     * @param nickname nickname
     * @param pageSize 单页大小
     * @param currentPage 页码
     * @return 用户列表分页
     */
    Page<User> usersNicknameLike(String nickname, long pageSize, long currentPage);

    /**
     * 根据username和nickname模糊查询
     * @param username username
     * @param nickname nickname
     * @param pageSize 单页大小
     * @param currentPage 页码
     * @return 用户列表分页
     */
    Page<User> usersUsernameAndNicknameLike(String username, String nickname, long pageSize, long currentPage);

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

    /**
     * 根据关键词模糊搜索用户
     * @param keyWords 关键词
     * @param pageSize 分页大小
     * @param currentPage 页码
     * @return 模糊搜索结果分页
     */
    Page<User> blurSearch(String keyWords, long pageSize, long currentPage);
}
