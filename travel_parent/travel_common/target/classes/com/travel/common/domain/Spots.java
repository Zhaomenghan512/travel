package com.travel.common.domain;

import lombok.Data;

import java.time.LocalDate;

/**
 * @description: Spots 景区
 * @author: 赵梦涵
 * @email: 33631575@qq.com
 * @date: created by 2020/10/29
 * @copyright: zhaomenghan
 */
@Data
public class Spots {
    private Long id;
    private String name;
    private String address;
    private String opentime;
    private Double price;
    private String tel;
    private String spotsDesc;
    private String info;
    private String purchaseinfo;
    private String features;
    private Long spotsid;
    private City city;
    private Long levelid;
    private Level level;
    private Long themeid;
    private Theme theme;
    private LocalDate createtime;
    private Long salenum;
    private Long creatorid;
    private String fmUrl;


}
