package com.jigpud.snow.service.permission;

import com.jigpud.snow.model.Permission;
import com.jigpud.snow.repository.permission.PermissionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public List<Permission> getPermissionList(String userid) {
        List<Permission> permissionList = permissionRepository.getPermissionList(userid);
        if (permissionList != null) {
            return permissionList;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void grantPermissions(String userid, String... permissions) {
        log.debug("grant permissions: {} for {}", Arrays.toString(permissions), userid);
        Arrays.stream(permissions).forEach(permission -> permissionRepository.add(userid, permission));
    }

    @Override
    public void revokePermissions(String userid, String... permissions) {
        log.debug("revoke permissions: {} from {}", Arrays.toString(permissions), userid);
        Arrays.stream(permissions).forEach(permission -> permissionRepository.remove(userid, permission));
    }

    @Override
    public boolean haveGranted(String userid, String... permissions) {
        if (Arrays.stream(permissions).anyMatch(permission -> permission == null || permission.isEmpty())) {
            return false;
        }
        List<Permission> grantedPermissionList = getPermissionList(userid);
        return Arrays.stream(permissions)
                .sequential()
                .allMatch(permission ->
                        grantedPermissionList.stream()
                                .anyMatch(grantedPermission ->
                                        permission.equals(grantedPermission.getPermission())
                                )
                );
    }
}
