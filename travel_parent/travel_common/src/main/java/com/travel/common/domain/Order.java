package com.travel.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: Order 订单
 * @author: 赵梦涵
 * @email: 33631575@qq.com
 * @date: created by 2020/10/29
 * @copyright: zhaomenghan
 */
@Data
public class Order {
    private Long id;
    private Long spotsid;
    private String spotsname;
    private String spotstel;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd",timezone = "GMT+8")
    private LocalDate traveltime;
    private Long adultsnum;
    private Long childnum;
    private double hotelprice;
    private double totalprice;

    private List<OrderDetail> travels = new ArrayList();
    private String ordernum;
    private String linkname;
    private String linktel;
    private String linkemail;
    private Long hotelid;
    private Long hotelnum;
    private String hotellinktel;
    private Hotel hotel;

    private Long orderstatus;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createtime;
    private Long creatorid;
    private Custorm custorm;



}
