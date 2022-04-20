package com.travel.common.query;

import lombok.Data;

/**
 * @description: UserQuery 用户查询条件
 * @author: 赵梦涵
 * @email: 33631575@qq.com
 * @date: created by 2020/10/29
 * @copyright: zhaomenghan
 */
@Data
public class UserQuery extends BaseQuery {

    //登录用户id
    private Integer id;

    private String username;

    private String email;



}
