package com.jigpud.snow.util.shiro;

import com.jigpud.snow.service.permission.PermissionService;
import com.jigpud.snow.service.role.RoleService;
import com.jigpud.snow.service.token.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author : jigpud
 * 验证token完成认证，查询数据库为subject添加角色与权限
 */
@Slf4j
public class JWTRealm extends AuthorizingRealm {
    private final RoleService roleService;
    private final PermissionService permissionService;
    private final TokenService tokenService;

    public JWTRealm(
            RoleService roleService,
            PermissionService permissionService,
            TokenService tokenService
    ) {
        this.roleService = roleService;
        this.permissionService = permissionService;
        this.tokenService = tokenService;

        // 设置认证缓存
        setAuthenticationCachingEnabled(true);
        setCacheManager(new MemoryConstrainedCacheManager());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String token = principals.getPrimaryPrincipal().toString();
        log.debug("doGetAuthorizationInfo: {}", token);
        String userid = tokenService.getUserid(token);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 添加角色
        roleService.getAllRoles(userid).forEach(authorizationInfo::addRole);

        // 添加权限
        permissionService.getAllPermissions(userid).forEach(authorizationInfo::addStringPermission);

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String jwtToken = token.getCredentials().toString();
        log.debug("doGetAuthenticationInfo: {}", jwtToken);
        if (tokenService.verify(jwtToken)) {
            return new SimpleAuthenticationInfo(jwtToken, jwtToken, "jwtRealm");
        }
        return null;
    }

    @Override
    public void onLogout(PrincipalCollection principals) {
        String jwtToken = principals.getPrimaryPrincipal().toString();
        log.debug("onLogout: {}", jwtToken);
        String userid = tokenService.getUserid(jwtToken);
        tokenService.expireRefreshToken(userid);
    }
}
