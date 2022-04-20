package com.travel.admin.controller.front;


import com.travel.admin.service.ICustormService;
import com.travel.admin.service.impl.SenderService;
import com.travel.admin.service.impl.VerificationImgeService;
import com.travel.common.domain.Custorm;
import com.travel.common.util.ResResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:CustormFrontController
 * @date: created by 2020/10/26 8:15
 */
@Controller
public class CustormFrontController {

    @Autowired
    private ICustormService custormService;

    @Autowired
    private SenderService senderService;

    @Autowired
    private VerificationImgeService verificationImgeService;


    //注册账号
    @RequestMapping("/front/custorm/reg")
    @ResponseBody
    public ResResult regCustorm(@RequestBody Custorm custorm){
        try {
            //注册顾客
            custormService.regCustorm(custorm);
            //发送电子邮件
            senderService.send(custorm);
            return new ResResult(20000,"操作成功");
        }catch (DuplicateKeyException e){
            return new ResResult(50000,"该信息已被注册");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResResult(50000,"操作失败"+e.getMessage());
        }

    }

    @GetMapping("/front/getImageCode")//获取验证码图 加载网页时
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        verificationImgeService.createImage(request, response);
    }

    //登录账号
    @RequestMapping("/front/custorm/login")
    @ResponseBody
    public ResResult loginCustorm(@RequestBody Custorm custorm,
                                  HttpServletRequest req){
        try {
            //判断验证码是否合法
            boolean result = verificationImgeService.verificationCode(req, custorm.getCaptchaVal(), custorm.getCaptchaToken());
            if(!result){
                return new ResResult(50000,"操作失败","验证码输入错误");
            }
            //登录
            Custorm loginUser = custormService.loginCustorm(custorm);
            if(loginUser == null){
                return new ResResult(50000,"操作失败","用户名或者密码不正确");
            }
            return new ResResult(20000,"操作成功",loginUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResResult(50000,"操作失败");
    }

    //编辑保存用户信息
    @RequestMapping("/front/custorm/editSaveUserInfo")
    @ResponseBody
    public ResResult editSaveUserInfo(@RequestBody Custorm custorm){
        try {
            //注册顾客
            Custorm custorm1 = custormService.editSaveUserInfo(custorm);
            return new ResResult(20000,"操作成功",custorm1);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResResult(50000,"操作失败"+e.getMessage());
        }

    }

    //编辑保存用户信息
    @RequestMapping("/front/custorm/updatePwd")
    @ResponseBody
    public ResResult updatePwd(@RequestBody Custorm custorm){
        try {
            //注册顾客
            Custorm custorm1 = custormService.updatePwd(custorm);
            return new ResResult(20000,"操作成功",custorm1);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResResult(50000,"操作失败"+e.getMessage());
        }

    }

    //更新用户头像
    @RequestMapping("/front/custorm/updateHeadImg")
    @ResponseBody
    public ResResult updateHeadImg(@RequestBody Custorm custorm){
        try {
            Custorm custorm1 = custormService.updateHeadImg(custorm);
            return new ResResult(20000,"操作成功",custorm1);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResResult(50000,"操作失败"+e.getMessage());
        }

    }



}
