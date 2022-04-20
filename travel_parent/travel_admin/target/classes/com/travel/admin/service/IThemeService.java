package com.travel.admin.service;


import com.travel.common.domain.Theme;

import java.util.List;

/**
 * @description: IThemeService
 * @date: created by 2020/10/25 21:17
 */
public interface IThemeService {
    List<Theme> findAllTheme();
}
