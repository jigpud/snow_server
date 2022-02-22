package com.jigpud.snow.service.admin;

import org.springframework.stereotype.Component;

/**
 * @author : jigpud
 */
@Component
public class AdminServiceImpl implements AdminService {
    @Override
    public boolean isAdmin(String token) {
        return false;
    }

    @Override
    public void login(String token) {

    }

    @Override
    public void logout(String token) {

    }
}
