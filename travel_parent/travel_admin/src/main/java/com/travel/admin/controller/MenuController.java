package com.travel.admin.controller;


import com.travel.admin.service.ISysMenuService;
import com.travel.common.domain.SysMenu;
import com.travel.common.util.ResResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "menu")
public class MenuController {


    @Autowired
    private ISysMenuService sysMenuService;

    /**
     * 查询所有的菜单
     */
    @GetMapping(value = "findAllMenu")
    public ResResult findAllMenu() {

        List<SysMenu> allMenuLists = sysMenuService.findAllMenu();
        Map mp = new HashMap();
        mp.put("allMenusList",allMenuLists);
        return  new ResResult<Map>(ResResult.CodeStatus.OK,
                "获取菜单信息", mp);
    }

    /**
     *  @description: 保存菜单
     *  @params:
     *  @return
     *
     */
    @PostMapping("/saveMenu")
    public ResResult saveMenu(@RequestBody SysMenu sysMenu){
        try {
            sysMenuService.saveMenu(sysMenu);
            return  new ResResult<Map>(ResResult.CodeStatus.OK,
                    "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return  new ResResult<Map>(ResResult.CodeStatus.FAIL,
                    "保存失败");
        }

    }

    /**
     *  @description: 删除菜单 /menu/delete/id
     *  @params:  id
     *  @return   ResResult
     *
     */
    @DeleteMapping("/delMenu/{id}")
    public ResResult removeMenuById(@PathVariable Long id) {
        try {
            sysMenuService.removeMenuById(id);
            return  new ResResult<Map>(ResResult.CodeStatus.OK,
                    "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return  new ResResult<Map>(ResResult.CodeStatus.FAIL,
                    "删除失败");
        }
    }

    @PutMapping("/updateMenu")
    public ResResult updateById(@RequestBody  SysMenu sysMenu) {
        try {
            sysMenuService.updateMenuById(sysMenu);
            return  new ResResult<Map>(ResResult.CodeStatus.OK,
                    "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return  new ResResult<Map>(ResResult.CodeStatus.FAIL,
                    "修改失败");
        }
    }


}
