package com.travel.admin.service;


import com.travel.common.domain.SysMenu;
import com.travel.common.domain.SysUser;
import com.travel.common.query.UserQuery;
import com.travel.common.util.PageList;

import java.util.List;
import java.util.Map;

public interface ISysUserService {
    /**
     *  @description:  根据用户名得到用户
     *  @params:  username
     *  @return  返回用户信息
     *
     */

    SysUser get(String username);

    /**
     *  @description: 根据用户id查询菜单
     *  @Params:   userId
     *  @Return   List<SysMenu>
     *
     */
    List<SysMenu> findByUserId(Long userId);

    /**
     *  @description: 分页查询用户列表
     *  @Params:   userQuery
     *  @Return   PageList<SysUser>
     *
     */
    PageList findPage(UserQuery userQuery);


    /**
     *  @description: 保存用户
     *  @Params:   umsAdmin
     *  @Return   void
     *
     */
    void saveUser(SysUser sysUser);

    /**
     *  @description: 删除数据deleteUserById
     *  @Params:   id
     *  @Return   void
     *
     */
    void deleteUserById(Long id);
    /**
     *  @description: 修改数据
     *  @Params:   sysUser
     *  @Return   void
     *
     */
    void updateUser(SysUser sysUser);
    /**
     *  @description: 保存用户角色
     *  @Params:   List<Map> mp
     *  @Return   void
     *
     */
    void saveUserRole(List<Map> mp);
}
