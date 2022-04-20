package com.travel.common.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: RestTemplateUtil 工具类
 * @author: 赵梦涵
 * @email: 33631575@qq.com
 * @date: created by 2020/10/29
 * @copyright: zhaomenghan
 */
public class RestTemplateUtil {

    //不传递参数 返回List对象
    public static List getAdminData(String url, RestTemplate restTemplate){
        ResResult resResult = restTemplate.getForObject(url, ResResult.class);
        List result = (List)resResult.getData();
        return result;
    }

    //不传递参数 返回普通对象
    public static Object getAdminObjectData(String url, RestTemplate restTemplate){
        ResResult resResult = restTemplate.getForObject(url, ResResult.class);
        Map map = (Map)resResult.getData();
        return map;
    }
    //不传递参数 返回集合对象
    public static List<Object> getAdminListData(String url, RestTemplate restTemplate){
        ResResult resResult = restTemplate.getForObject(url, ResResult.class);
        List list = (List)resResult.getData();
        return list;
    }

    //传递参数 返回对象
    public static ResResult getAdminObjectData(String url, String param, RestTemplate restTemplate){
        ResResult resResult = restTemplate.getForObject(url, ResResult.class,param);
       return resResult;
    }


    // 传递对象参数
    public static ResResult getAdminObjData(String url, Object paramObj, RestTemplate restTemplate){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        ResResult resResult = restTemplate.postForEntity(url,paramObj, ResResult.class).getBody();
        return resResult;
    }


    //分页查询 传递参数
    public static PageList getAdminPageData(String url, Object paramObj, RestTemplate restTemplate){
          HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());
            ResResult resResult = restTemplate.postForEntity(url,paramObj, ResResult.class).getBody();
            Map map = (Map)resResult.getData();
            LinkedHashMap result =  (LinkedHashMap)map.get("pageList");
            Integer total = (Integer)result.get("total");
            List rows = (List)result.get("rows");
            Integer currentPage =(Integer) result.get("currentPage");
            PageList pageList = new PageList(Long.parseLong(currentPage+""),Long.parseLong(total+""),rows);
        return pageList;
    }

}
