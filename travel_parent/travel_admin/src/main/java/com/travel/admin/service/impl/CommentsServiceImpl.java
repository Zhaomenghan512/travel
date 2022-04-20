package com.travel.admin.service.impl;


import com.travel.admin.mapper.CommentsMapper;
import com.travel.admin.mapper.CustormMapper;
import com.travel.admin.mapper.SpotsMapper;
import com.travel.admin.service.ICommentsService;
import com.travel.common.domain.Comments;
import com.travel.common.domain.Custorm;
import com.travel.common.domain.Order;
import com.travel.common.domain.Spots;
import com.travel.common.query.CommentsQuery;
import com.travel.common.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: ICommentsService
 * @date: created by 2020/10/28 7:53
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class CommentsServiceImpl implements ICommentsService {

    @Autowired
    private CommentsMapper commentsMapper;

    @Autowired
    private CustormMapper custormMapper;

    @Autowired
    private SpotsMapper spotsMapper;

    @Override
    public List<Order> queryOrderByUseridAndSpots(Comments comments) {
        return commentsMapper.queryOrderByUseridAndSpots(comments);
    }

    @Override
    public void saveComments(Comments comments) {
        comments.setCreatetime(LocalDateTime.now());
        commentsMapper.saveComments(comments);
    }

    @Override
    public List<Comments> queryCommentsBySpotsid(Long spotsid) {
        List<Comments> comments = commentsMapper.queryCommentsBySpotsid(spotsid);
        comments = comments.stream().map(comment ->{
            Custorm custorm = custormMapper.queryCustormById(comment.getUserid());
            comment.setCustorm(custorm);
            return comment;
        }).collect(Collectors.toList());
        return comments;
    }

    //分页数据
    @Override
    public PageList getCommentsList(CommentsQuery commentsQuery) {
        //查询评论的总数
        Long total = commentsMapper.queryTotal(commentsQuery);
        //查询评论的数据
        List<Comments> commentsList = commentsMapper.queryData(commentsQuery);
        commentsList.forEach(comment->{
            //显示景区
            Spots spot = spotsMapper.getSpotById(comment.getSpotsid());
            comment.setSpots(spot);
            //评论人
            Custorm custorm = custormMapper.queryCustormById(comment.getUserid());
            comment.setCustorm(custorm);
        });

        PageList pageList = new PageList(commentsQuery.getCurrentPage(), total, commentsList);
        return pageList;
    }

    @Override
    public void delComments(Long id) {
        commentsMapper.delComments(id);
    }
}
