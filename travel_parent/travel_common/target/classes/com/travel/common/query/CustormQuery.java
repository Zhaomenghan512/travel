package com.travel.common.query;

import lombok.Data;

/**
 * @description: CustormQuery 顾客查询条件
 * @author: 赵梦涵
 * @email: 33631575@qq.com
 * @date: created by 2020/10/29
 * @copyright: zhaomenghan
 */
@Data
public class CustormQuery extends  BaseQuery {

    private String account;
    private String username;
    private String password;
}
