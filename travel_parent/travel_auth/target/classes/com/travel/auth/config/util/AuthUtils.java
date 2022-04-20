package com.travel.auth.config.util;


import com.travel.auth.config.constant.ConstantKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: AuthUtils
 * @author: 赵梦涵
 * @email: 33631575@qq.com
 * @date: created by 2020/10/29
 * @copyright: zhaomenghan
 */
public class AuthUtils {

    public static Long getLoginUserId(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        Claims claims = Jwts.parser()
                .setSigningKey(ConstantKey.SIGNING_KEY)
                .parseClaimsJws(authorization.replace("ITFXQ ", "")).getBody();
        String userId=claims.getId();
        return Long.parseLong(userId);
    }

    /**
     * 得到用户名
     * @return
     */
    public static String getLoginUsername(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String username = (String)authentication.getPrincipal();
        return username;
    }
}
