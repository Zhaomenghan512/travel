package com.travel.admin.mapper;

import com.travel.common.domain.Theme;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description: ThemeMapper
 * @date: created by 2020/10/25 21:16
 */
public interface ThemeMapper {
    //找到所有的主题
    @Select("select * from t_theme")
    List<Theme> findAllTheme();
}
