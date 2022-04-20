package com.travel.admin.mapper;


import com.travel.common.domain.Level;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description: LevelMapper
 * @date: created by 2020/10/25 21:15
 */
public interface LevelMapper {

    @Select("select * from t_level")
    List<Level> findAllLevel();
}
