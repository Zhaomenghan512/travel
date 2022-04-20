package com.travel.admin.service;


import com.travel.common.domain.SysMenu;

import java.util.List;

/**
 * @author: soulcoder
 * @datetime: 2020/6/16 21:57
 * @description: TODO
 */
public interface ISysMenuService {
    //查询所有的菜单
    public List<SysMenu> findAllMenu();
    //保存菜单
    void saveMenu(SysMenu sysMenu);
    //删除菜单
    void removeMenuById(Long id);
    //修改菜单
    void updateMenuById(SysMenu sysMenu);
}
