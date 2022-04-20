package com.travel.common.query;

import lombok.Data;

/**
 * @description: SpotsQuery 景区查询条件
 * @author: 赵梦涵
 * @email: 33631575@qq.com
 * @date: created by 2020/10/29
 * @copyright: zhaomenghan
 */
@Data
public class SpotsQuery  extends  BaseQuery {
    private Long id;
    private String name;
    private Long levelid;
    private Long themeid;
    private Long cityid;
    private Long pricefwid;
    private String orderByType="id";

}
