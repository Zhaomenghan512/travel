package com.travel.admin.service.impl;

import com.travel.admin.mapper.RoleMapper;
import com.travel.admin.mapper.UserMapper;
import com.travel.admin.service.ISysRoleService;
import com.travel.common.domain.SysMenu;
import com.travel.common.domain.SysRole;
import com.travel.common.domain.SysUser;
import com.travel.common.query.RoleQuery;
import com.travel.common.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author: soulcoder
 * @datetime: 2020/6/16 21:37
 * @description: TODO
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;


    /**
     *  @description:  分页数据
     *  @params:  sysRoleQuery
     *  @return  PageList
     */
    @Override
    public PageList listRole(RoleQuery sysRoleQuery) {
        PageList pageList = new PageList();
        Long total = roleMapper.queryTotal(sysRoleQuery);
        List<SysRole> roles = roleMapper.queryData(sysRoleQuery);
        pageList.setTotal(total);
        pageList.setRows(roles);
        return pageList;
    }

    //保存角色
    @Override
    @Transactional
    public void saveRole(SysRole sysRole) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String username = (String)authentication.getPrincipal();
        SysUser sysUser = userMapper.selectByUsername(username);
        Long userId = sysUser.getId();
        sysRole.setCreateBy(userId);
        sysRole.setCreateDate(new Date());
        sysRole.setUpdateBy(userId);
        sysRole.setUpdateDate(new Date());
        //保存角色表 sys_role
        roleMapper.saveRole(sysRole);

        //保存中间表 sys_role_menu
        List roleMenuList = new ArrayList();
        List<SysMenu> menuList = sysRole.getMenuList();
        for (SysMenu sysMenu : menuList) {
            Map mp = new HashMap();
            Long menuId = sysMenu.getId();
            Long roleId = sysRole.getId();
            mp.put("menuId",menuId);
            mp.put("roleId",roleId);
            roleMenuList.add(mp);
        }
        roleMapper.saveRoleMenu(roleMenuList);
    }

    //更新角色
    @Override
    public void updateRole(SysRole sysRole) {
        //先删除
        removeRole(sysRole.getId());
        //在插入
        saveRole(sysRole);
    }

    //查询所有角色信息
    @Override
    public List<SysRole> queryAllRole() {
        return roleMapper.queryAllRole();
    }

    //删除角色
    @Transactional
    public void removeRole(Long roleId){
        //删除角色菜单权限中间表
        roleMapper.removeRoleMenu(roleId);
        //删除角色表
        roleMapper.removeRoleById(roleId);
    }
}
