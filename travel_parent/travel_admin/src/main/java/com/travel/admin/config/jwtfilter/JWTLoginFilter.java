package com.travel.admin.config.jwtfilter;


import com.travel.admin.UserSecurity;
import com.travel.auth.config.constant.ConstantKey;
import com.travel.common.domain.SysUser;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 验证用户名密码正确后，生成一个token，并将token返回给客户端
 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法
 * attemptAuthentication ：接收并解析用户凭证。
 * successfulAuthentication ：用户成功登录后，这个方法会被调用，我们在这个方法里生成token。
 */

public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private ThreadLocal<Map<String,String>> threadLocal = new ThreadLocal<>();

    /**Json转化工具*/
    @Autowired
    private ObjectMapper objectMapper;

    private AuthenticationManager authenticationManager;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // 接收并解析用户凭证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            SysUser user = new ObjectMapper().readValue(req.getInputStream(), SysUser.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 用户成功登录后，这个方法会被调用，我们在这个方法里生成token
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        // builder the token
        String token = null;
        try {
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
            // 定义存放角色集合的对象
            List roleList = new ArrayList<>();
            for (GrantedAuthority grantedAuthority : authorities) {
                roleList.add(grantedAuthority.getAuthority());
            }
            Calendar calendar = Calendar.getInstance();
            Date now = calendar.getTime();
            // 设置签发时间
            calendar.setTime(new Date());
            // 设置过期时间
            calendar.add(Calendar.MINUTE, 100);// 100分钟
            Date time = calendar.getTime();
            UserSecurity userDetails = (UserSecurity)auth.getPrincipal();
            token = Jwts.builder()
                    .setSubject(auth.getName() + "-" + roleList)
                    .setId(userDetails.getLoginUser().getId()+"")//设置用户id
                    .setIssuedAt(now)//签发时间
                    .setExpiration(time)//过期时间
                    .signWith(SignatureAlgorithm.HS512, ConstantKey.SIGNING_KEY) //采用什么算法是可以自己选择的，不一定非要采用HS512
                    .compact();


            System.out.println("管理员 " + userDetails.getUsername() + " 登录");
            Map<String,Object> map=new HashMap<>(2);
            map.put("code", 20000);
            map.put("message", "登录成功");
            userDetails.setToken(token);
            map.put("data",userDetails);
            // map.put("token","Soul " + token);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(map));
            // 登录成功后，返回token到header里面
            response.addHeader("Authorization",token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        String password = this.getBodyParams(request).get(super.SPRING_SECURITY_FORM_PASSWORD_KEY);
        if(!StringUtils.isEmpty(password)){
            return password;
        }
        return super.obtainPassword(request);
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        String username = this.getBodyParams(request).get(super.SPRING_SECURITY_FORM_USERNAME_KEY);
        if(!StringUtils.isEmpty(username)){
            return username;
        }
        return super.obtainUsername(request);
    }

    /**
     * 获取body参数  body中的参数只能获取一次
     * @param request
     * @return
     */
    private Map<String,String> getBodyParams(HttpServletRequest request){
        Map<String,String> bodyParams =  threadLocal.get();
        if(bodyParams==null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try (InputStream is = request.getInputStream()) {
                bodyParams = objectMapper.readValue(is, Map.class);
            } catch (IOException e) {
            }
            if(bodyParams==null) bodyParams = new HashMap<>();
            threadLocal.set(bodyParams);
        }

        return bodyParams;
    }

}
