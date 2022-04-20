package com.travel.admin.config.redisjob;


import com.travel.admin.service.IOrderService;
import com.travel.common.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {
    public RedisKeyExpirationListener(RedisMessageListenerContainer container) {
        super(container);
    }

    @Autowired
    private IOrderService orderService;
    /**
     * 针对redis数据失效事件，进行数据处理
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
       String key=message.toString();//生效的key
        if (key!=null && key.startsWith("order")){
            //从失效key中筛选代表订单失效的key
            //截取订单号，查询订单，如果是未支付状态则取消订单
            String orderNo=key.substring(5);
            Order order = orderService.findOrderStatus(orderNo);
            if(order.getOrderstatus() == 0){
                //未支付订单
                orderService.deleteOrder(order.getId());
                System.out.println("订单号为:"+orderNo+"的订单超时未支付，取消订单");
            }
        }
    }
}