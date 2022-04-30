package com.jigpud.snow.service.permission;

import com.jigpud.snow.model.Permission;

import java.util.List;

/**
 * @author : jigpud
 */
public interface PermissionService {
    List<Permission> getPermissionList(String userid);
    void grantPermissions(String userid, String... permissions);
    void revokePermissions(String userid, String... permissions);
    boolean haveGranted(String userid, String... permissions);
}
