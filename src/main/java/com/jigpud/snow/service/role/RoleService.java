package com.jigpud.snow.service.role;

import com.jigpud.snow.model.Role;

import java.util.List;

/**
 * @author : jigpud
 */
public interface RoleService {
    List<Role> getRoleList(String userid);
    void addRoles(String userid, String... roles);
    void removeRoles(String userid, String... roles);
    boolean haveRoles(String userid, String... roles);
}
