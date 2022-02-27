package com.jigpud.snow.repository.role;

import com.jigpud.snow.model.Role;

/**
 * @author : jigpud
 * 角色管理
 */
public interface RoleRepository {
    /**
     * 获取用户角色信息
     * @param userid userid
     * @return 角色
     */
    Role getRole(String userid);

    /**
     * 更新或新增角色信息
     * @param role 角色
     */
    void updateOrInsert(Role role);
}
