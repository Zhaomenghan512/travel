package com.travel.common.domain;

import lombok.Data;

/**
 * @description: City城市类
 * @author: 赵梦涵
 * @email: 33631575@qq.com
 * @date: created by 2020/10/29
 * @copyright: zhaomenghan
 */
@Data
public class City {
    private Long id;
    private String name;
    private Long parentid;
}
