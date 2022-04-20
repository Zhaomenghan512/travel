package com.travel.common.domain;

import lombok.Data;

/**
 * @description: OrderDetail 订单详情
 * @author: 赵梦涵
 * @email: 33631575@qq.com
 * @date: created by 2020/10/29
 * @copyright: zhaomenghan
 */
@Data
public class OrderDetail {
    private Long id;
    private Long orderid;
    private String travelerName;
    private String cardNum;
}
