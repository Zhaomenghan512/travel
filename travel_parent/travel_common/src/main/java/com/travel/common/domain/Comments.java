package com.travel.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @description: Comments
 * @author: 赵梦涵
 * @email: 33631575@qq.com
 * @date: created by 2020/10/29
 * @copyright: zhaomenghan
 */
@Data
public class Comments {
    private Long id;
    private Long orderid;
    private Long spotsid;
    private Spots spots;
    private String transportsfeel;
    private String hotelfeel;
    private String playfeel;
    private String content;
    private Long userid;
    private Custorm custorm;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createtime;
}
