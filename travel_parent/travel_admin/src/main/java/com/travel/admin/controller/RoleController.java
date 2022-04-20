package com.travel.admin.controller;


import com.travel.admin.service.ISysRoleService;
import com.travel.common.domain.SysRole;
import com.travel.common.query.RoleQuery;
import com.travel.common.util.PageList;
import com.travel.common.util.ResResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "role")
public class RoleController {

    @Autowired
    private ISysRoleService sysRoleService;


    /**
     *  不分页
     * @return {@link ResResult}
     */
    @GetMapping(value = "/list")
    public ResResult list() throws Exception {
        //返回用户对应的菜单 根据userid查询
        List<SysRole> allRoles = sysRoleService.queryAllRole();
        return new ResResult (ResResult.CodeStatus.OK, "获取所有角色的信息", allRoles);
    }


    /**
     * 获取所有角色信息
     * @return {@link ResResult}
     */
    @GetMapping(value = "/page")
    public ResResult page(RoleQuery sysRoleQuery) throws Exception {
        //返回用户对应的菜单 根据userid查询
        PageList pageList = sysRoleService.listRole(sysRoleQuery);
        return new ResResult (ResResult.CodeStatus.OK, "获取角色的信息", pageList);
    }

    @PostMapping(value = "/saveRole")
    public ResResult saveRole(@RequestBody SysRole sysRole) throws Exception {
        try {
            sysRoleService.saveRole(sysRole);
            return   new ResResult (ResResult.CodeStatus.OK, "保存角色和权限", null);
        } catch (Exception e) {
            e.printStackTrace();
            return   new ResResult (ResResult.CodeStatus.OK, "保存失败角色和权限", null);
        }
    }

    @PutMapping(value = "/updateRole")
    public ResResult updateRole(@RequestBody SysRole sysRole) throws Exception {
        try {
            sysRoleService.updateRole(sysRole);
            return   new ResResult (ResResult.CodeStatus.OK, "保存角色和权限", null);
        } catch (Exception e) {
            e.printStackTrace();
            return   new ResResult (ResResult.CodeStatus.OK, "保存失败角色和权限", null);
        }
    }

    @DeleteMapping(value = "/deleteRole/{id}")
    public ResResult deleteRole(@PathVariable("id") Long id) throws Exception {
        try {
            sysRoleService.removeRole(id);
            return   new ResResult (ResResult.CodeStatus.OK, "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return   new ResResult (ResResult.CodeStatus.OK, "删除失败", null);
        }
    }


}
