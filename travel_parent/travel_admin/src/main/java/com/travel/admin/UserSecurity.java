package com.travel.admin;

import com.travel.common.domain.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Set;

//登录用户封装
public class UserSecurity extends User {

    SysUser loginUser;
    private String token;

    public UserSecurity(SysUser user, Set<GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(),true,true,true,true, authorities);
        this.loginUser = user;
    }

    public SysUser getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(SysUser loginUser) {
        this.loginUser = loginUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
