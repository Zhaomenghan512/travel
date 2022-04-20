package com.travel.admin.service.impl;

import com.travel.common.domain.Custorm;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class SenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    public String send(Custorm custorm) throws MessagingException {
        String userJsonStr = JSONObject.toJSONString(custorm);
        //发送邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        messageHelper.setFrom(senderEmail);
        messageHelper.setTo(custorm.getEmail());
        messageHelper.setSubject("凤凰旅游网温馨提示信息");
        messageHelper.setText("<h1>恭喜</h1><p>"+custorm.getUsername()+"注册成功！</p><br/>  <p style='float:right;'>By：赵梦涵</p>", true);
        javaMailSender.send(mimeMessage);

        return "发送成功";
    }
}