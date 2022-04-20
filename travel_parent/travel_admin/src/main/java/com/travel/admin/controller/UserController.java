package com.travel.admin.controller;

import com.travel.admin.dto.LoginUser;
import com.travel.admin.service.ISysUserService;
import com.travel.common.domain.SysMenu;
import com.travel.common.domain.SysUser;
import com.travel.common.query.UserQuery;
import com.travel.common.util.PageList;
import com.travel.common.util.ResResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ISysUserService sysUserService;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;


    @GetMapping(value = "/info")
    public ResResult<LoginUser> info() throws Exception {
        // 获取认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 获取个人信息
        String username = authentication.getName();
        SysUser sysUser = sysUserService.get(username);

        // 封装并返回结果
        LoginUser loginInfo = new LoginUser();
        loginInfo.setName(sysUser.getUsername());
        loginInfo.setAvatar(sysUser.getIcon());
        loginInfo.setNickName(sysUser.getNickName());
        //返回用户对应的菜单 根据userid查询
        List<SysMenu> menuList = sysUserService.findByUserId(sysUser.getId());
        loginInfo.setMenuList(menuList);

        return new ResResult<LoginUser>(ResResult.CodeStatus.OK, "获取用户信息", loginInfo);
    }





    /**
     * 获取所有用户的信息(分页)
     */
    @GetMapping(value = "/page")
    public ResResult page(UserQuery userQuery) throws Exception {
        //返回用户对应的菜单 根据userid查询
        PageList pageList = sysUserService.findPage(userQuery);
        return new ResResult (ResResult.CodeStatus.OK, "获取用户信息", pageList);
    }

    @PostMapping(value = "/saveUser")
    public ResResult saveUser(@RequestBody SysUser sysUser) throws Exception {
        try {
            sysUserService.saveUser(sysUser);
            return  new ResResult<Map>(ResResult.CodeStatus.OK,
                    "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return  new ResResult<Map>(ResResult.CodeStatus.FAIL,
                    "保存失败");
        }
    }

    @PutMapping(value = "/updateUser")
    public ResResult updateUser(@RequestBody SysUser sysUser) throws Exception {
        try {
            sysUserService.updateUser(sysUser);
            return  new ResResult<Map>(ResResult.CodeStatus.OK,
                    "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return  new ResResult<Map>(ResResult.CodeStatus.FAIL,
                    "修改失败");
        }
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public ResResult deleteUser(@PathVariable("id") Long id) throws Exception {
        try {
            sysUserService.deleteUserById(id);
            return  new ResResult<Map>(ResResult.CodeStatus.OK,
                    "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return  new ResResult<Map>(ResResult.CodeStatus.FAIL,
                    "删除失败");
        }
    }

    //保存用户角色 saveUserRole


    @PostMapping(value = "/saveUserRole")
    public ResResult saveUserRole(@RequestBody List<Map> userRoleList) throws Exception {
        try {
            sysUserService.saveUserRole(userRoleList);
            return  new ResResult<Map>(ResResult.CodeStatus.OK,
                    "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return  new ResResult<Map>(ResResult.CodeStatus.FAIL,
                    "保存失败");
        }
    }
}
