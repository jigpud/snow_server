package com.jigpud.snow.repository.permission;

import com.jigpud.snow.model.Permission;

/**
 * @author : jigpud
 * 权限管理
 */
public interface PermissionRepository {
    /**
     * 获取用户权限
     * @param userid userid
     * @return permission
     */
    Permission getPermission(String userid);

    /**
     * 更新或新增用户权限
     * @param permission 权限
     */
    void updateOrInsert(Permission permission);
}
