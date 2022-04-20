package com.travel.admin.service;






import com.travel.common.domain.SysRole;
import com.travel.common.query.RoleQuery;
import com.travel.common.util.PageList;

import java.util.List;

/**
 * @author: soulcoder
 * @datetime: 2020/6/16 21:57
 * @description: TODO
 */
public interface ISysRoleService {

    //分页查询角色
    PageList listRole(RoleQuery sysRoleQuery);
    //保存角色
    void saveRole(SysRole sysRole);
    //删除角色
    public void removeRole(Long roleId);

    //更新角色
    void updateRole(SysRole sysRole);

    //查询所有角色
    List<SysRole> queryAllRole();
}
