package com.travel.admin.service;


import com.travel.common.domain.City;

import java.util.List;

/**
 * 查询城市
 */
public interface ICityService {

    //查询所有的城市
    List<City> queryAllCity();
}
