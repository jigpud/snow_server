package com.jigpud.snow.service.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jigpud.snow.model.User;

/**
 * @author : jigpud
 * 用户服务
 */
public interface UserService {
    /**
     * 注册
     * @param username 电话号码
     * @param password 密码
     */
    void register(String username, String password);

    /**
     * 根据username判断用户是否存在
     * @param username 电话号码
     * @return 是否存在
     */
    boolean haveUsernameIs(String username);

    /**
     * 根据userid判断用户是否存在
     * @param userid userid
     * @return 是否存在
     */
    boolean haveUseridIs(String userid);

    /**
     * 根据username删除用户
     * @param username username
     */
    void deleteUserByUsername(String username);

    /**
     * 根据userid删除用户
     * @param userid userid
     */
    void deleteUserByUserid(String userid);

    /**
     * 登录
     * @param username 电话号码
     * @param password 密码
     * @return token
     */
    String login(String username, String password);

    /**
     * 根据username获取userid
     * @param username username
     * @return userid
     */
    String getUserid(String username);

    /**
     * 根据userid获取username
     * @param userid userid
     * @return username
     */
    String getUsername(String userid);

    /**
     * 跟新用户信息
     * @param user 用户信息
     */
    void update(User user);

    /**
     * 更新个性签名
     * @param userid userid
     * @param newSignature 新的个性签名
     */
    void updateSignature(String userid, String newSignature);

    /**
     * 更新昵称
     * @param userid userid
     * @param newNickname 新的昵称
     */
    void updateNickname(String userid, String newNickname);

    /**
     * 更新密码
     * @param userid userid
     * @param newPassword 新的密码
     */
    void updatePassword(String userid, String newPassword);

    /**
     * 更新头像
     * @param userid userid
     * @param newAvatar 新的头像地址
     */
    void updateAvatar(String userid, String newAvatar);

    /**
     * 增加获赞数
     * @param userid userid
     */
    void incrementLikes(String userid);

    /**
     * 根据username模糊查询用户列表
     * @param username username
     * @param pageCount 单页大小
     * @param page 页码
     * @return 用户列表分页
     */
    Page<User> usersUsernameLike(String username, long pageCount, long page);

    /**
     * 根据username获取用户信息
     * @param username username
     * @return 用户信息
     */
    User getUserByUsername(String username);

    /**
     * 根据userid获取用户信息
     * @param userid userid
     * @return 用户信息
     */
    User getUserByUserid(String userid);

    /**
     * 根据nickname模糊查询用户列表
     * @param nickname nickname
     * @param pageCount 单页大小
     * @param page 页码
     * @return 用户列表分页
     */
    Page<User> usersNicknameLike(String nickname, long pageCount, long page);

    /**
     * 根据username和nickname模糊查询用户列表
     * @param username username
     * @param nickname nickname
     * @param pageCount 单页大小
     * @param page 页码
     * @return 用户列表分页
     */
    Page<User> usersUsernameAndNicknameLike(String username, String nickname, long pageCount, long page);

    /**
     * 获取用户列表
     * @param pageCount 单页大小
     * @param page 页码
     * @return 用户列表分页
     */
    Page<User> users(long pageCount, long page);
}
