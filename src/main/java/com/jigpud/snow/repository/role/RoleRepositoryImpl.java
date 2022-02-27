package com.jigpud.snow.repository.role;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jigpud.snow.mapper.RoleMapper;
import com.jigpud.snow.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public Role getRole(String userid) {
        return rolesMapper.selectOne(getQueryWrapper(userid));
    }

    @Override
    public void updateOrInsert(Role role) {
        QueryWrapper<Role> queryWrapper = getQueryWrapper(role.getUserid());
        if (rolesMapper.exists(queryWrapper)) {
            rolesMapper.update(role, queryWrapper);
        } else {
            rolesMapper.insert(role);
        }
    }

    private QueryWrapper<Role> getQueryWrapper(String userid) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        return queryWrapper;
    }
}
