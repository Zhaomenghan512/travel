package com.travel.common.util;

import org.apache.commons.lang.RandomStringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @description: CommonUtils 工具类
 * @author: 赵梦涵
 * @email: 33631575@qq.com
 * @date: created by 2020/10/29
 * @copyright: zhaomenghan
 */
public class CommonUtils {

    /**
     * 自动产生订单号
     * @return
     */
    public static String autoGenerateOrderNum(){
        //时间（精确到毫秒）
        DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String localDate = LocalDateTime.now().format(ofPattern);
        //随机数
        String randomNumeric = RandomStringUtils.randomNumeric(8);
        return localDate+randomNumeric;
    }
}
