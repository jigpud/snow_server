package com.jigpud.snow.service.permission;

import java.util.List;

/**
 * @author : jigpud
 */
public interface PermissionService {
    List<String> getAllPermissions(String userid);
    void grantPermissions(String userid, String... permissions);
    void revokePermissions(String userid, String... permissions);
    boolean haveGranted(String userid, String... permissions);
}
