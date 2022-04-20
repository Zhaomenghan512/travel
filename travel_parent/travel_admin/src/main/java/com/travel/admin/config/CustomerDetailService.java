package com.travel.admin.config;



import com.travel.admin.UserSecurity;
import com.travel.admin.service.ISysUserService;
import com.travel.common.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * 需要自定义UserDetailsService实现spring security的UserDetailsService接口
 */
@Service
public class CustomerDetailService implements UserDetailsService {

    @Autowired
    private ISysUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询出用户
        SysUser user = userService.get(username);
        if(user != null) {
            //构建所有权限集合==ROLE_角色+权限
            HashSet<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

            //返回更多信息的登录对象
            return new UserSecurity(user,authorities);
        }else{
            return null;
        }
    }
}
