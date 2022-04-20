package com.travel.admin.service.impl;


import com.travel.admin.mapper.HotelMapper;
import com.travel.admin.service.IHotelService;
import com.travel.common.domain.Hotel;
import com.travel.common.query.HotelQuery;
import com.travel.common.util.PageList;
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
public class HotelServiceImpl implements IHotelService {

    @Autowired
    private HotelMapper hotelMapper;


    //查询所有酒店信息
    @Override
    public PageList getHotelList(HotelQuery hotelQuery) {
        //查询酒店的总数
        Long total = hotelMapper.queryTotal(hotelQuery);
        //查询酒店的数据
        List hotelList = hotelMapper.queryData(hotelQuery);
        PageList pageList = new PageList(hotelQuery.getCurrentPage(), total, hotelList);
        return pageList;
    }

    //新增保存酒店
    @Override
    public void addSaveHotel(Hotel hotel) {
        hotelMapper.addSaveHotel(hotel);
    }

    //根据id删除酒店
    @Override
    public void delHotel(Long id) {
        hotelMapper.delHotel(id);
    }

    //编辑保存酒店
    @Override
    public void editSaveHotel(Hotel hotel) {
        hotelMapper.editSaveHotel(hotel);
    }

    @Override
    public List<Hotel> findAll() {
        return hotelMapper.findAll();
    }

    @Override
    public Hotel queryHotelById(Long hotelid) {
        return hotelMapper.queryHotelById(hotelid);
    }


}
