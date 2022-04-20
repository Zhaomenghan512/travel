package com.travel.admin.service;


import com.travel.common.domain.Spots;
import com.travel.common.query.SpotsQuery;
import com.travel.common.util.PageList;

/**
 * @description: TODO
 */
public interface ISpotsService {

    void saveSpots(Spots spots);

    PageList getSpotsPageList(SpotsQuery spotsQuery);

    void updateSaveSpots(Spots spots);

    void deleteSpotsById(Long id);

    Spots getSpotById(SpotsQuery spotsQuery);
}