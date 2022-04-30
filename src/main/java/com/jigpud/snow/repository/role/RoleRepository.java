package com.jigpud.snow.repository.role;

import com.jigpud.snow.model.Role;

import java.util.List;

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
    List<Role> getRoleList(String userid);

    /**
     * 新增一条角色记录
     * @param userid 用户
     * @param role 角色
     */
    void add(String userid, String role);

    /**
     * 是否存在该条角色记录
     * @param userid 用户
     * @param role 角色
     * @return 是否存在
     */
    boolean have(String userid, String role);

    /**
     * 删除该条角色记录
     * @param userid 用户
     * @param role 角色
     */
    void remove(String userid, String role);
}
