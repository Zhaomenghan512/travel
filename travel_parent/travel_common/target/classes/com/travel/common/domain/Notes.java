package com.travel.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @description: Notes
 * @author: 赵梦涵
 * @email: 33631575@qq.com
 * @date: created by 2020/10/29
 * @copyright: zhaomenghan
 */
@Data
public class Notes {
    private Long id;
    private String title;
    private String notesdesc;
    private String departureTime;
    private Long traveldays;
    private Long travelnums;
    private String hotelname;
    private String precost;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createtime;
    private Long viewnum;
    private Long creatorid;
    private String fmUrl;
    private Custorm custorm;
}
