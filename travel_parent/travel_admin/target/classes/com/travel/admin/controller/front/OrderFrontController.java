package com.travel.admin.controller.front;


import com.travel.admin.service.IOrderService;
import com.travel.common.domain.Custorm;
import com.travel.common.domain.Order;
import com.travel.common.util.ResResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @description:
 * @author:soulcoder 灵魂码仔
 * @email: 2579692606@qq.com
 * @date: created by 2020/10/27 16:17
 */
@Controller
public class OrderFrontController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/front/order/makeScheduleOrder")
    @ResponseBody
    public ResResult makeScheduleOrder(@RequestBody Order order){
        try {
            orderService.makeScheduleOrder(order);
            return new ResResult(20000,"操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResResult(50000,"操作失败",e.getMessage());
        }
    }

    //查询个人的所有的订单
    @RequestMapping("/front/order/queryMyOrder")
    @ResponseBody
    public ResResult queryMyOrder(@RequestBody Custorm custorm){
        try {
            List<Order> myOrders = orderService.queryMyOrder(custorm);
            return new ResResult(20000,"操作成功",myOrders);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResResult(50000,"操作失败",e.getMessage());
        }
    }

    //deleteOrderById
    @RequestMapping("/front/order/deleteOrderById")
    @ResponseBody
    public ResResult deleteOrderById(@RequestBody Order order){
        try {
             orderService.deleteOrder(order.getId());
            return new ResResult(20000,"操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResResult(50000,"操作失败",e.getMessage());
        }
    }
    //goPayOrder
    @RequestMapping("/front/order/goPayOrder")
    @ResponseBody
    public ResResult goPayOrder(@RequestBody Order order){
        try {
            orderService.goPayOrder(order.getId());
            return new ResResult(20000,"操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResResult(50000,"操作失败",e.getMessage());
        }
    }




}
