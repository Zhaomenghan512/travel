package com.travel.common.query;

import lombok.Data;

/**
 * @description: OrderQuery 订单查询条件
 * @author: 赵梦涵
 * @email: 33631575@qq.com
 * @date: created by 2020/10/29
 * @copyright: zhaomenghan
 */
@Data
public class OrderQuery  extends BaseQuery{
    private String ordernum;
}
