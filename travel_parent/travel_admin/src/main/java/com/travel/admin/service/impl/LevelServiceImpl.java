package com.travel.admin.service.impl;


import com.travel.admin.mapper.LevelMapper;
import com.travel.admin.service.ILevelService;
import com.travel.common.domain.Level;
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
public class LevelServiceImpl implements ILevelService {

    @Autowired
    private LevelMapper levelMapper;


    @Override
    public List<Level> findAllLevel() {
        return levelMapper.findAllLevel();
    }
}
