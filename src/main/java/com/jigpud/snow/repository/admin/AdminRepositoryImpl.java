package com.jigpud.snow.repository.admin;

import com.jigpud.snow.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : jigpud
 */
@Component
public class AdminRepositoryImpl implements AdminRepository {
    private final AdminMapper adminMapper;

    @Autowired
    AdminRepositoryImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public boolean isAdmin(String userid) {
        return adminMapper.isAdmin(userid) == 1;
    }
}
