package com.travel.common.util;

import lombok.Data;

/**
 * @description: AjaxResponseBody 返回对象
 * @author: 赵梦涵
 * @email: 33631575@qq.com
 * @date: created by 2020/10/29
 * @copyright: zhaomenghan
 */
@Data
public class AjaxResponseBody {
    private String status="200";
    private String msg="操作成功";
    private Object resultObj;

    public AjaxResponseBody(String msg) {
        this.status = "500";
        this.msg = msg;
    }

    public AjaxResponseBody() {
    }
}
