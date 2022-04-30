package com.jigpud.snow.repository.role;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jigpud.snow.mapper.RoleMapper;
import com.jigpud.snow.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : jigpud
 */
@Slf4j
@Repository
public class RoleRepositoryImpl implements RoleRepository {
    private final RoleMapper rolesMapper;

    @Autowired
    RoleRepositoryImpl(RoleMapper rolesMapper) {
        this.rolesMapper = rolesMapper;
    }

    @Override
    public List<Role> getRoleList(String userid) {
        return rolesMapper.selectList(userQueryWrapper(userid));
    }

    @Override
    public void add(String userid, String role) {
        rolesMapper.insertIgnore(userid, role);
    }

    @Override
    public boolean have(String userid, String role) {
        return rolesMapper.exists(userAndRoleQueryWrapper(userid, role));
    }

    @Override
    public void remove(String userid, String role) {
        rolesMapper.delete(userAndRoleQueryWrapper(userid, role));
    }

    private QueryWrapper<Role> userQueryWrapper(String userid) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        return queryWrapper;
    }

    private QueryWrapper<Role> userAndRoleQueryWrapper(String userid, String role) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        queryWrapper.eq("role", role);
        return queryWrapper;
    }
}
