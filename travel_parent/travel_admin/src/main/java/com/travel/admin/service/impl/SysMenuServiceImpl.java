package com.travel.admin.service.impl;



import com.travel.admin.mapper.MenuMapper;
import com.travel.admin.mapper.UserMapper;
import com.travel.admin.service.ISysMenuService;
import com.travel.common.domain.SysMenu;
import com.travel.common.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * @author: soulcoder
 * @datetime: 2020/6/16 21:37
 * @description: TODO
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class SysMenuServiceImpl implements ISysMenuService {


    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private UserMapper userMapper;


    /**
     *  @description:  查询所有菜单
     *  @params:  null
     *  @return  返回菜单集合
     *
     */
    @Override
    public List<SysMenu> findAllMenu() {
        return menuMapper.findAllMenus();
    }

    /**
     *  @description: 保存一级菜单
     *  @params:  sysMenu
     *  @return  是否保存成功
     *
     */
    @Override
    @Transactional
    public void saveMenu(SysMenu sysMenu) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String username = (String)authentication.getPrincipal();
        SysUser sysUser = userMapper.selectByUsername(username);
        Long userId = sysUser.getId();
        sysMenu.setCreateBy(userId);
        sysMenu.setCreateDate(new Date());
        sysMenu.setUpdateDate(new Date());
        sysMenu.setUpdateBy(userId);

        menuMapper.saveMenu(sysMenu);
    }

    /**
     *  @description:  删除菜单
     *  @params:  id
     *  @return   是否删除成功
     *
     */
    @Override
    @Transactional
    public void removeMenuById(Long id) {
        menuMapper.removeMenuById(id);
    }

    /**
     *  @description:  修改菜单
     *  @params:  id
     *  @return   是否修改成功
     *
     */
    @Override
    @Transactional
    public void updateMenuById(SysMenu sysMenu) {
        menuMapper.updateMenuById(sysMenu);
    }


}
