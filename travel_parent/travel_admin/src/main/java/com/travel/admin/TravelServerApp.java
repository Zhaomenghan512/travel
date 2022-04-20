package com.travel.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @description: TravelServerApp
 * @author: 赵梦涵
 * @date: created by 2020/10/24 17:58
 */
@SpringBootApplication(scanBasePackages = "com.travel.admin")
@MapperScan("com.travel.admin.mapper")
public class TravelServerApp {


    public static void main(String[] args) {
        SpringApplication.run(TravelServerApp.class, args);
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
