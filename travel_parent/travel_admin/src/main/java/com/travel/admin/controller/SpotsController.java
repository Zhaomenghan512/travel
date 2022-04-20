package com.travel.admin.controller;

import com.travel.admin.service.ISpotsService;
import com.travel.common.domain.Spots;
import com.travel.common.query.SpotsQuery;
import com.travel.common.util.PageList;
import com.travel.common.util.ResResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:SpotsController
 * @date: created by 2020/10/26 8:15
 */
@Controller
public class SpotsController {

    @Autowired
    private ISpotsService spotsService;
    //分页查询数据
    @RequestMapping("/spots/page")
    @ResponseBody
    public ResResult getSpotsPageList(SpotsQuery spotsQuery){
        try {
            PageList spotsPageList = spotsService.getSpotsPageList(spotsQuery);
            return new ResResult(20000,"操作成功",spotsPageList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResResult(50000,"操作失败");
    }

    //新增保存景区
    @RequestMapping("/spots/saveSpots")
    @ResponseBody
    public ResResult saveSpots(Spots spots, HttpServletRequest request){
        try {
            spotsService.saveSpots(spots);
            return new ResResult(20000,"操作成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResResult(50000,"操作失败");

    }

    //修改保存景区
    @RequestMapping("/spots/updateSaveSpots")
    @ResponseBody
    public ResResult updateSaveSpots(  Spots spots){
        try {
            spotsService.updateSaveSpots(spots);
            return new ResResult(20000,"操作成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResResult(50000,"操作失败");

    }
    //删除景区
    @RequestMapping("/spots/deleteSpots/{id}")
    @ResponseBody
    public ResResult deleteSpots(@PathVariable("id") Long id){
        try {
            spotsService.deleteSpotsById(id);
            return new ResResult(20000,"操作成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResResult(50000,"操作失败");

    }


}
