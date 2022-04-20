package com.travel.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description: 配置图片访问路径
 * @date: created by 2020/9/3 9:11
 */
@Configuration
public class MyPicConfig implements WebMvcConfigurer {

    @Value("${file.upload.spotsFmPath}")
    private String spotsFmUploadPath;


    @Value("${file.upload.headImgPath}")
    private String headImgUploadPath;

    @Value("${file.upload.path}")
    private String uploadNotesPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/spots/**").addResourceLocations("file:"+spotsFmUploadPath+"/");
        registry.addResourceHandler("/upload/headimg/**").addResourceLocations("file:"+headImgUploadPath+"/");
        registry.addResourceHandler("/upload/notes/**").addResourceLocations("file:"+uploadNotesPath+"/notes/");
    }
}