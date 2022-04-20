package com.travel.admin.service;



import com.travel.common.domain.PriceArea;

import java.util.List;

/**
 * @description: IPriceService
 * @date: created by 2020/10/25 21:17
 */
public interface IPriceService {
    List<PriceArea> findAllPriceArea();
}
