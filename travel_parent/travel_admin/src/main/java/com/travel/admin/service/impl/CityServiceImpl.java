package com.travel.admin.service.impl;
import com.travel.admin.mapper.CityMapper;
import com.travel.admin.service.ICityService;
import com.travel.common.domain.City;
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
public class CityServiceImpl implements ICityService {

    @Autowired
    private CityMapper cityMapper;


    @Override
    public List<City> queryAllCity() {
        return cityMapper.queryAllCity();
    }
}
