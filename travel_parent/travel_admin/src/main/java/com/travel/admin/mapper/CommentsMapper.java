package com.travel.admin.mapper;


import com.travel.common.domain.Comments;
import com.travel.common.domain.Order;
import com.travel.common.query.CommentsQuery;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description: CommentsMapper
 * @date: created by 2020/10/28 7:44
 */
public interface CommentsMapper {

    /**
     * 查询该用户是否对该景区 已经下过订单--用来评论判断
     * @param comments
     * @return
     */
    @Select("select * from t_order_main where creatorid=#{userid} " +
            "and spotsid=#{spotsid} and orderstatus = 1  ")
    List<Order> queryOrderByUseridAndSpots(Comments comments);

    /**
     * 保存评论
     */
    @Insert("insert into t_comments(orderid,spotsid,transportsfeel," +
            "hotelfeel,playfeel,content,userid,createtime) " +
            "values(#{orderid},#{spotsid},#{transportsfeel}," +
            "#{hotelfeel},#{playfeel},#{content},#{userid},#{createtime})")
    void saveComments(Comments comments);

    /**
     * 查询该景区的所有评论
     */
    @Select("select * from t_comments where spotsid = #{spotsid}")
    List<Comments> queryCommentsBySpotsid(Long spotsid);

    Long queryTotal(CommentsQuery commentsQuery);

    List queryData(CommentsQuery commentsQuery);

    @Delete("delete from t_comments where id=#{id}")
    void delComments(Long id);
}
