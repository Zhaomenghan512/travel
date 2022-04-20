package com.travel.admin.controller;


import com.travel.admin.service.ICustormService;
import com.travel.common.query.CustormQuery;
import com.travel.common.util.PageList;
import com.travel.common.util.ResResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:CustormController
 * @date: created by 2020/10/26 8:15
 */
@RestController
public class CustormController {

    @Autowired
    private ICustormService custormService;


    @PostMapping("/custorm/page")
    public ResResult getCustormList(CustormQuery custormQuery){
        try {
            PageList custormList = custormService.getCustormList(custormQuery);
            return new ResResult(ResResult.CodeStatus.OK,"操作成功",custormList);
        }catch (Exception e){
            return new ResResult(ResResult.CodeStatus.ILLEGAL_REQUEST,"操作失败");
        }
    }


}
