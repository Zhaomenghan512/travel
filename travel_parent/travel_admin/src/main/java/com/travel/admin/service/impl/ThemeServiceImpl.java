package com.travel.admin.service.impl;


import com.travel.admin.mapper.ThemeMapper;
import com.travel.admin.service.IThemeService;
import com.travel.common.domain.Theme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description: TODO
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class ThemeServiceImpl implements IThemeService {

    @Autowired
    private ThemeMapper themeMapper;


    @Override
    public List<Theme> findAllTheme() {
        return themeMapper.findAllTheme();
    }
}
