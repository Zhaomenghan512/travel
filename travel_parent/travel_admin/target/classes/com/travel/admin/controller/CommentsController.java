package com.travel.admin.controller;


import com.travel.admin.service.ICommentsService;
import com.travel.common.query.CommentsQuery;
import com.travel.common.util.PageList;
import com.travel.common.util.ResResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:CommentsController
 * @date: created by 2020/10/26 8:15
 */
@RestController
public class CommentsController {

    @Autowired
    private ICommentsService commentsService;


    @PostMapping("/comments/page")
    public ResResult getCommentsList(CommentsQuery commentsQuery){
        try {
            PageList commentsList = commentsService.getCommentsList(commentsQuery);
            return new ResResult(ResResult.CodeStatus.OK,"操作成功",commentsList);
        }catch (Exception e){
            return new ResResult(ResResult.CodeStatus.ILLEGAL_REQUEST,"操作失败");
        }
    }

    //删除评论
    @RequestMapping("/comments/delComments/{id}")
    public ResResult delComments(@PathVariable("id") Long id){
        try {
            commentsService.delComments(id);
            return new ResResult(20000,"操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResResult(50000,"操作失败"+e.getMessage());
        }
    }


}
