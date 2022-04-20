package com.travel.admin.mapper;


import com.travel.common.domain.Hotel;
import com.travel.common.query.HotelQuery;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface HotelMapper {

    //查询总数
    Long queryTotal(HotelQuery hotelQuery);
    //分页查询数据
    List queryData(HotelQuery hotelQuery);
    //新增保存酒店
    @Insert("insert into t_hotel(hotelname,hotelprice) values (#{hotelname},#{hotelprice})")
    void addSaveHotel(Hotel hotel);
    //删除酒店
    @Delete("delete from t_hotel where id=#{id}")
    void delHotel(Long id);
    //编辑保存酒店
    @Update("update t_hotel set hotelname=#{hotelname},hotelprice=#{hotelprice} where id=#{id}")
    void editSaveHotel(Hotel hotel);

    @Select("select * from t_hotel")
    List<Hotel> findAll();

    @Select("select * from t_hotel where id=#{hotelid}")
    Hotel queryHotelById(Long hotelid);
}
