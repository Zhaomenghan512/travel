package com.travel.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 字符串类型写操作 设置<string,object>
     */
    public void setVal(String key, Object val) {
        stringRedisTemplate.opsForValue().set(key, (String)val);
    }

    /**
     * 字符串类型写操作 设置<string,object>
     *
     * @param expire 过期时间
     */
    public void setVal(String key, Object val, Long expire) {
        stringRedisTemplate.opsForValue().set(key, (String)val, expire, TimeUnit.SECONDS);

    }
    /**
     * 字符串类型读操作 获取缺省为string的反序列化对象
     */
    public String getVal(String key) {
        return getVal(key,String.class);
    }

    /**
     * 字符串类型读操作 获取反序列化对象
     */
    public <T> T getVal(String key,Class cls) {
        return (T)stringRedisTemplate.opsForValue().get(key);
    }
}