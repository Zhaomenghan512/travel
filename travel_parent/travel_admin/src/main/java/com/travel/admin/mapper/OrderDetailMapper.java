package com.travel.admin.mapper;

import com.travel.common.domain.OrderDetail;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description: OrderDetailMapper
 * @date: created by 2020/10/27 16:54
 */
public interface OrderDetailMapper {

    @Insert("insert into t_order_detail(orderid,travelerName,cardNum) " +
            "values(#{orderid},#{travelerName},#{cardNum})")
    void saveOrderDetail(OrderDetail orderDetail);

    @Delete("delete from t_order_detail where orderid=#{orderid}")
    void deleteDetailData(Long orderid);

    @Select("select * from t_order_detail where orderid=#{orderid}")
    List<OrderDetail> queryOrderDetailByOrderId(Long orderid);
}
