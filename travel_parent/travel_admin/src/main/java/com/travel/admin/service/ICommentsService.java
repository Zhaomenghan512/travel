package com.travel.admin.service;



import com.travel.common.domain.Comments;
import com.travel.common.domain.Order;
import com.travel.common.query.CommentsQuery;
import com.travel.common.util.PageList;

import java.util.List;

/**
 * @description: ICommentsService
 * @date: created by 2020/10/28 7:51
 */
public interface ICommentsService {

    List<Order> queryOrderByUseridAndSpots(Comments comments);

    void saveComments(Comments comments);

    List<Comments> queryCommentsBySpotsid(Long spotsid);

    PageList getCommentsList(CommentsQuery commentsQuery);

    void delComments(Long id);
}
