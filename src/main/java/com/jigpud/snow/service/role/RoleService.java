package com.jigpud.snow.service.role;

import java.util.List;

/**
 * @author : jigpud
 */
public interface RoleService {
    List<String> getAllRoles(String userid);
    void addRoles(String userid, String... roles);
    void removeRoles(String userid, String... roles);
    boolean haveRoles(String userid, String... roles);
}
