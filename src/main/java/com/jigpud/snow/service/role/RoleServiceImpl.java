package com.jigpud.snow.service.role;

import com.jigpud.snow.model.Role;
import com.jigpud.snow.repository.role.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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
    public List<Role> getRoleList(String userid) {
        return roleRepository.getRoleList(userid);
    }

    @Override
    public void addRoles(String userid, String... roles) {
        log.debug("add roles: {} for {}", Arrays.toString(roles), userid);
        Arrays.stream(roles).forEach(role -> roleRepository.add(userid, role));
    }

    @Override
    public void removeRoles(String userid, String... roles) {
        log.debug("remove roles: {} from {}", Arrays.toString(roles), userid);
        Arrays.stream(roles).forEach(role -> roleRepository.remove(userid, role));
    }

    @Override
    public boolean haveRoles(String userid, String... roles) {
        if (Arrays.stream(roles).anyMatch(role -> role == null || role.isEmpty())) {
            return false;
        }
        List<Role> grantedRoleList = getRoleList(userid);
        return Arrays.stream(roles)
                .sequential()
                .allMatch(role ->
                        grantedRoleList.stream()
                                .anyMatch(grantedRole ->
                                        role.equals(grantedRole.getRole())
                                )
                );
    }
}
