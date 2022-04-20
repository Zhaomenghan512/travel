package com.travel.admin.mapper;



import com.travel.common.domain.SysRole;
import com.travel.common.query.RoleQuery;

import java.util.List;

/**
 * @author: soulcoder
 * @datetime: 2020/6/16 21:24
 * @description: TODO
 */
public interface RoleMapper {

    //查询总数
    Long queryTotal(RoleQuery sysRoleQuery);
    //查询分页的数据
    List<SysRole> queryData(RoleQuery sysRoleQuery);
    //保存角色
    void saveRole(SysRole sysRole);
    //保存角色权限中间表
    void saveRoleMenu(List roleMenuList);
    //删除角色表
    void removeRoleById(Long roleId);
    //删除中间表
    void removeRoleMenu(Long roleId);
    //查询所有角色
    List<SysRole> queryAllRole();
}
