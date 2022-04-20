package com.travel.common.domain;

import lombok.Data;

import java.time.LocalDate;

/**
 * @description: Custorm 顾客
 * @author: 赵梦涵
 * @email: 33631575@qq.com
 * @date: created by 2020/10/29
 * @copyright: zhaomenghan
 */
@Data
public class Custorm {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String headImg;
    private String tel;
    private LocalDate createtime;
    private Long totalpoints;
    private String captchaVal;
    private String captchaToken;
    private String account;
}
