package com.jigpud.snow.repository.permission;

import com.jigpud.snow.model.Permission;

import java.util.List;

/**
 * @author : jigpud
 * 权限管理
 */
public interface PermissionRepository {
    /**
     * 获取用户权限
     * @param userid userid
     * @return 权限列表
     */
    List<Permission> getPermissionList(String userid);

    /**
     * 增加一条权限记录
     * @param userid 用户
     * @param permission 权限
     */
    void add(String userid, String permission);

    /**
     * 是否有改权限记录
     * @param userid 用户
     * @param permission 权限
     * @return 是否存在
     */
    boolean have(String userid, String permission);

    /**
     * 删除该权限记录
     * @param userid 用户
     * @param permission 权限
     */
    void remove(String userid, String permission);
}
