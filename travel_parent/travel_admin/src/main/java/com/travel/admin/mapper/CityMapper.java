package com.travel.admin.mapper;

import com.travel.common.domain.City;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description: CityMapper
 */
public interface CityMapper {

    @Select("select * from t_city")
    List<City> queryAllCity();
}
