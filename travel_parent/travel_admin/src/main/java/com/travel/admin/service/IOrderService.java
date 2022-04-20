package com.travel.admin.service;



import com.travel.common.domain.Custorm;
import com.travel.common.domain.Order;
import com.travel.common.query.OrderQuery;
import com.travel.common.util.PageList;

import java.util.List;

/**
 * @description:
 * @author:soulcoder 灵魂码仔
 * @email: 2579692606@qq.com
 * @date: created by 2020/10/27 16:53
 */
public interface IOrderService {
    void makeScheduleOrder(Order order);

    List<Order> queryMyOrder(Custorm custorm);
    //查询订单的状态 根据订单号
    Order findOrderStatus(String orderNo);
    //删除未支付的订单
    void deleteOrder(Long orderId);
    //支付订单
    void goPayOrder(Long orderId);

    PageList getOrderList(OrderQuery orderQuery);
}
