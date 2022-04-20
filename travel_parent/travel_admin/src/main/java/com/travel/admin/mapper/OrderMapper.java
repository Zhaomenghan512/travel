package com.travel.admin.mapper;


import com.travel.common.domain.Custorm;
import com.travel.common.domain.Order;
import com.travel.common.query.OrderQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @description: OrderMapper
 * @date: created by 2020/10/27 16:54
 */
public interface OrderMapper {


    @Insert("insert into t_order_main(ordernum,spotsid,spotsname," +
            "traveltime,spotstel,adultsnum,childnum," +
            "linkname,linktel,linkemail,hotelid," +
            "hotelnum,hotellinktel,hotelprice," +
            "totalprice,orderstatus,createtime,creatorid) " +
            "values(#{ordernum},#{spotsid},#{spotsname}," +
            "#{traveltime},#{spotstel},#{adultsnum}," +
            "#{childnum},#{linkname},#{linktel},#{linkemail}," +
            "#{hotelid},#{hotelnum},#{hotellinktel},#{hotelprice}," +
            "#{totalprice},#{orderstatus},#{createtime},#{creatorid})")
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    void saveOrderMain(Order order);

    @Select("select * from t_order_main where creatorid = #{id}")
    List<Order> queryMyOrder(Custorm custorm);

    @Select("select * from t_order_main where ordernum = #{orderNo}")
    Order findOrderStatus(String orderNo);

    @Delete("delete from t_order_main where id = #{orderid}")
    void deleteMainData(Long orderid);

    @Update("update t_order_main set orderstatus = 1 where id=#{orderId}")
    void goPayOrder(Long orderId);

    Long queryTotal(OrderQuery orderQuery);

    List queryData(OrderQuery orderQuery);
}
