package com.jigpud.snow.service.permission;

import com.jigpud.snow.model.Permission;
import com.jigpud.snow.repository.permission.PermissionRepository;
import com.jigpud.snow.util.rolespermissions.RolesPermissionsUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : jigpud
 */
@Slf4j
@Service
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;

    @Autowired
    PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public List<String> getAllPermissions(String userid) {
        Permission permission = permissionRepository.getPermission(userid);
        if (permission == null) {
            return new ArrayList<>();
        } else {
            return RolesPermissionsUtil.parse(permission.getPermissions());
        }
    }

    @Override
    public void grantPermissions(String userid, String... permissions) {
        log.debug("grant permissions: [{}] for {}", Arrays.toString(permissions), userid);
        List<String> newPermissions = getAllPermissions(userid);
        Arrays.stream(permissions)
                .filter(permission -> permission != null && !permission.isEmpty())
                .forEach(newPermissions::add);
        updateOrInsertNewPermissions(userid, newPermissions);
    }

    @Override
    public void revokePermissions(String userid, String... permissions) {
        log.debug("revoke permissions: [{}] from {}", Arrays.toString(permissions), userid);
        List<String> newPermissions = getAllPermissions(userid).stream()
                .sequential()
                .filter(permission -> Arrays.stream(permissions).noneMatch(permission::equals))
                .collect(Collectors.toList());
        updateOrInsertNewPermissions(userid, newPermissions);
    }

    @Override
    public boolean haveGranted(String userid, String... permissions) {
        if (Arrays.stream(permissions).anyMatch(permission -> permission == null || permission.isEmpty())) {
            return false;
        }
        List<String> allPermissions = getAllPermissions(userid);
        return Arrays.stream(permissions)
                .sequential()
                .allMatch(permission -> allPermissions.stream().anyMatch(permission::equals));
    }

    private void updateOrInsertNewPermissions(String userid, List<String> newPermissions) {
        String newPermissionsStr = "";
        for (String permission : newPermissions) {
            newPermissionsStr = RolesPermissionsUtil.append(newPermissionsStr, permission);
        }
        Permission p = new Permission();
        p.setUserid(userid);
        p.setPermissions(newPermissionsStr);
        permissionRepository.updateOrInsert(p);
    }
}
