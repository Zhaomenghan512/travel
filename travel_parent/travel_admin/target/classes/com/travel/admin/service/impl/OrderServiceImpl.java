package com.travel.admin.service.impl;

import com.travel.admin.mapper.CustormMapper;
import com.travel.admin.mapper.HotelMapper;
import com.travel.admin.mapper.OrderDetailMapper;
import com.travel.admin.mapper.OrderMapper;
import com.travel.admin.service.IOrderService;
import com.travel.common.domain.Custorm;
import com.travel.common.domain.Hotel;
import com.travel.common.domain.Order;
import com.travel.common.domain.OrderDetail;
import com.travel.common.query.OrderQuery;
import com.travel.common.util.CommonUtils;
import com.travel.common.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @description: OrderServiceImpl
 * @date: created by 2020/10/27 16:53
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private CustormMapper custormMapper;



    @Value("${order.timeout}")
    private Long timeout;

    @Override
    @Transactional
    public void makeScheduleOrder(Order order) {
        order.setOrderstatus(0L);//表示未支付
        order.setCreatetime(LocalDateTime.now());
        order.setOrdernum(CommonUtils.autoGenerateOrderNum());
        orderMapper.saveOrderMain(order);

        order.getTravels().forEach(travel->{
            travel.setOrderid(order.getId());
            orderDetailMapper.saveOrderDetail(travel);
        });
        //把订单号存入redis 5分钟失效
        redisService.setVal("order"+order.getOrdernum(), UUID.randomUUID().toString(),timeout);

    }

    @Override
    public List<Order> queryMyOrder(Custorm custorm) {
        return orderMapper.queryMyOrder(custorm);
    }

    @Override
    public Order findOrderStatus(String orderNo) {
        return orderMapper.findOrderStatus(orderNo);
    }

    @Override
    @Transactional
    public void deleteOrder(Long orderid) {
        //删除子表数据
        orderDetailMapper.deleteDetailData(orderid);
        //删除主表数据
        orderMapper.deleteMainData(orderid);
    }

    @Override
    public void goPayOrder(Long orderId) {
        orderMapper.goPayOrder(orderId);
    }

    @Override
    public PageList getOrderList(OrderQuery orderQuery) {
        //查询订单的总数
        Long total = orderMapper.queryTotal(orderQuery);
        //查询订单的数据
        List<Order> orderList = orderMapper.queryData(orderQuery);
        /**
         * 查询关联酒店数据 和 旅游人数 和 下单人
         */
        orderList.forEach(order->{

            Hotel hotel = hotelMapper.queryHotelById(order.getHotelid());
            order.setHotel(hotel);
            List<OrderDetail> travels = orderDetailMapper.queryOrderDetailByOrderId(order.getId());
            order.setTravels(travels);
            Custorm custorm = custormMapper.queryCustormById(order.getCreatorid());
            order.setCustorm(custorm);

        });
        PageList pageList = new PageList(orderQuery.getCurrentPage(), total, orderList);
        return pageList;
    }


}
