package com.jigpud.snow.repository.permission;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jigpud.snow.mapper.PermissionMapper;
import com.jigpud.snow.model.Permission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author : jigpud
 */
@Slf4j
@Repository
public class PermissionRepositoryImpl implements PermissionRepository {
    private final PermissionMapper permissionMapper;

    @Autowired
    PermissionRepositoryImpl(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @Override
    public Permission getPermission(String userid) {
        return permissionMapper.selectOne(getQueryWrapper(userid));
    }

    @Override
    public void updateOrInsert(Permission permission) {
        QueryWrapper<Permission> queryWrapper = getQueryWrapper(permission.getUserid());
        if (permissionMapper.exists(queryWrapper)) {
            permissionMapper.update(permission, queryWrapper);
        } else {
            permissionMapper.insert(permission);
        }
    }

    private QueryWrapper<Permission> getQueryWrapper(String userid) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        return queryWrapper;
    }
}
