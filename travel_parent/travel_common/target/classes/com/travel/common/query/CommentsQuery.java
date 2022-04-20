package com.travel.common.query;

import lombok.Data;

/**
 * @description: CommentsQuery 评论查询
 * @author: 赵梦涵
 * @email: 33631575@qq.com
 * @date: created by 2020/10/29
 * @copyright: zhaomenghan
 */
@Data
public class CommentsQuery extends BaseQuery{

    private String content;

    private String  username;
}
