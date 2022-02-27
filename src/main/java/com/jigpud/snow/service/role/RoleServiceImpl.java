package com.jigpud.snow.service.role;

import com.jigpud.snow.model.Role;
import com.jigpud.snow.repository.role.RoleRepository;
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
 * 角色服务
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<String> getAllRoles(String userid) {
        Role role = roleRepository.getRole(userid);
        if (role == null) {
            return new ArrayList<>();
        } else {
            return RolesPermissionsUtil.parse(role.getRoles());
        }
    }

    @Override
    public void addRoles(String userid, String... roles) {
        log.debug("add roles: [{}] for {}", Arrays.toString(roles), userid);
        List<String> newRoles = getAllRoles(userid);
        Arrays.stream(roles)
                .filter(r -> r != null && !r.isEmpty())
                .forEach(newRoles::add);
        updateOrInsertRoles(userid, newRoles);
    }

    @Override
    public void removeRoles(String userid, String... roles) {
        log.debug("remove roles: [{}] from {}", Arrays.toString(roles), userid);
        List<String> newRoles = getAllRoles(userid).stream()
                .sequential()
                .filter(role -> Arrays.stream(roles).noneMatch(role::equals))
                .collect(Collectors.toList());
        updateOrInsertRoles(userid, newRoles);
    }

    @Override
    public boolean haveRoles(String userid, String... roles) {
        if (Arrays.stream(roles).anyMatch(role -> role == null || role.isEmpty())) {
            return false;
        }
        List<String> allRoles = getAllRoles(userid);
        return Arrays.stream(roles)
                .sequential()
                .allMatch(role -> allRoles.stream().anyMatch(role::equals));
    }

    private void updateOrInsertRoles(String userid, List<String> newRoles) {
        String newRolesStr = "";
        for (String role : newRoles) {
            newRolesStr = RolesPermissionsUtil.append(newRolesStr, role);
        }
        Role r = new Role();
        r.setUserid(userid);
        r.setRoles(newRolesStr);
        roleRepository.updateOrInsert(r);
    }
}
