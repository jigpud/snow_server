package com.jigpud.snow.repository.permission;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jigpud.snow.mapper.PermissionMapper;
import com.jigpud.snow.model.Permission;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public List<Permission> getPermissionList(String userid) {
        return permissionMapper.selectList(userQueryWrapper(userid));
    }

    @Override
    public void add(String userid, String permission) {
        permissionMapper.insertIgnore(userid, permission);
    }

    @Override
    public boolean have(String userid, String permission) {
        return permissionMapper.exists(userAndPermissionQueryWrapper(userid, permission));
    }

    @Override
    public void remove(String userid, String permission) {
        permissionMapper.delete(userAndPermissionQueryWrapper(userid, permission));
    }

    private QueryWrapper<Permission> userQueryWrapper(String userid) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        return queryWrapper;
    }

    private QueryWrapper<Permission> userAndPermissionQueryWrapper(String userid, String permission) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userid", userid);
        queryWrapper.eq("permission", permission);
        return queryWrapper;
    }
}
