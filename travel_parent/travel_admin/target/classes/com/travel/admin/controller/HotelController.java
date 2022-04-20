package com.travel.admin.controller;


import com.travel.admin.service.IHotelService;
import com.travel.common.domain.Hotel;
import com.travel.common.query.HotelQuery;
import com.travel.common.util.PageList;
import com.travel.common.util.ResResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hotel酒店
 */
@RestController
@RequestMapping("/hotel")
public class HotelController {


    @Autowired
    private IHotelService hotelService;


    //查询所有订单
    @PostMapping("/page")
    public ResResult getHotelList(HotelQuery hotelQuery){
        try {

            PageList hotelsPageList = hotelService.getHotelList(hotelQuery);
            return new ResResult(ResResult.CodeStatus.OK,"操作成功",hotelsPageList);
        }catch (Exception e){
            return new ResResult(ResResult.CodeStatus.ILLEGAL_REQUEST,"操作失败");
        }
    }

    //addSaveHotel
    @PostMapping("/addSaveHotel")
    public ResResult addSaveHotel(Hotel hotel){
        try {

            hotelService.addSaveHotel(hotel);
            return new ResResult(ResResult.CodeStatus.OK,"操作成功");
        }catch (Exception e){
            return new ResResult(ResResult.CodeStatus.ILLEGAL_REQUEST,"操作失败");
        }
    }

    //编辑保存
    @RequestMapping("/editSaveHotel")
    public ResResult editSaveHotel(Hotel hotel){
        try {
            hotelService.editSaveHotel(hotel);
            return new ResResult(20000,"操作成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResResult(50000,"操作失败");

    }

    //删除酒店
    @RequestMapping("/delHotel/{id}")
    public ResResult delHotel(@PathVariable("id") Long id){
        try {
            hotelService.delHotel(id);
            return new ResResult(20000,"操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResResult(50000,"操作失败"+e.getMessage());
        }
    }









}
