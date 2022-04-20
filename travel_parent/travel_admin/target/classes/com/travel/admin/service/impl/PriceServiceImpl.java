package com.travel.admin.service.impl;


import com.travel.admin.mapper.PriceMapper;
import com.travel.admin.service.IPriceService;
import com.travel.common.domain.PriceArea;
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
public class PriceServiceImpl implements IPriceService {

    @Autowired
    private PriceMapper priceMapper;

    @Override
    public List<PriceArea> findAllPriceArea() {
        return priceMapper.findAllPriceArea();
    }
}
