package com.travel.admin.mapper;


import com.travel.common.domain.PriceArea;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description: PriceMapper
 * @date: created by 2020/10/25 21:15
 */
public interface PriceMapper {

    @Select("select * from t_price")
    List<PriceArea> findAllPriceArea();
}
