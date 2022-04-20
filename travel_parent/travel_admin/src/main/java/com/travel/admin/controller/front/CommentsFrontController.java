package com.travel.admin.controller.front;


import com.travel.admin.service.ICommentsService;
import com.travel.common.domain.Comments;
import com.travel.common.domain.Order;
import com.travel.common.util.ResResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @description: CommentsFrontController
 * @date: created by 2020/10/28 7:42
 */
@Controller
public class CommentsFrontController {

    @Autowired
    private ICommentsService commentsService;


    //注册账号
    @RequestMapping("/front/comments/save")
    @ResponseBody
    public ResResult saveComments(@RequestBody Comments comment){
        try {
            //如果该用户 没有已支付的订单，不能进行评论的
            List<Order> orders = commentsService.queryOrderByUseridAndSpots(comment);
            if(orders.size()==0){
                return new ResResult(50000,"必须对该景区已支付的订单,才能进行评论");
            }
            commentsService.saveComments(comment);
            return new ResResult(20000,"操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResResult(50000,"操作失败"+e.getMessage());
        }

    }


    @RequestMapping("/front/comments/queryAllCommentsBySpotsid")
    @ResponseBody
    public ResResult queryAllCommentsBySpotsid(@RequestBody Comments comment){
        try {
            List<Comments> comments = commentsService.queryCommentsBySpotsid(comment.getSpotsid());
            return new ResResult(20000,"操作成功",comments);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResResult(50000,"操作失败"+e.getMessage());
        }

    }

}
