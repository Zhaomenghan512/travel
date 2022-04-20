package com.travel.common.util;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: PageList 分页对象
 * @author: 赵梦涵
 * @email: 33631575@qq.com
 * @date: created by 2020/10/29
 * @copyright: zhaomenghan
 */
@Data
public class PageList {
    //总共的条数
    private Long total;
    //每一页显示的数据
    private List rows = new ArrayList();

    //每页显示的条数
    private Long pageSize = 10L;

    //当前页
    private Long currentPage;

    //总页数
    private Long totalPage;

    //首页
    private Long firstPage=1L;

    //尾页
    private Long endPage;

    public PageList(){}

    public PageList(Long currentPage,Long total,List rows){
        this.total = total;
        this.rows = rows;
        this.firstPage = 1L;
        this.endPage = total % pageSize == 0?total / pageSize: (total / pageSize) +1;
        this.totalPage = this.endPage;
        this.currentPage = currentPage;
    }

}
