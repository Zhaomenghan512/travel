package com.travel.admin.service;


import com.travel.common.domain.Hotel;
import com.travel.common.query.HotelQuery;
import com.travel.common.util.PageList;

import java.util.List;

/**
 * @description: TODO
 */
public interface IHotelService {
    //查询所有的酒店
    PageList getHotelList(HotelQuery hotelQuery);
    //新增保存酒店
    void addSaveHotel(Hotel hotel);
    //删除酒店
    void delHotel(Long id);
    //编辑保存酒店
    void editSaveHotel(Hotel hotel);

    List<Hotel> findAll();


    Hotel queryHotelById(Long hotelid);
}