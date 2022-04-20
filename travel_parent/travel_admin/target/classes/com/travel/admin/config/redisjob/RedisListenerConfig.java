package com.travel.admin.config.redisjob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisListenerConfig {
 
    @Autowired
    private RedisTemplate redisTemplate;
 
    /**
     * 处理乱码
     * @return
     */
    @Bean
    public RedisTemplate redisTemplateInit() {
        // key序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //val实例化
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
 
        return redisTemplate;
    }
 
 
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
 
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }
 
}