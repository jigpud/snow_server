package com.jigpud.snow.service.admin;

/**
 * @author : jigpud
 * 提供admin相关的服务
 */
public interface AdminService {
    boolean isAdmin(String token);
    void login(String token);
    void logout(String token);
}
