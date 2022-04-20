package com.travel.admin.mapper;

import com.travel.common.domain.Spots;
import com.travel.common.query.SpotsQuery;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @description: SpotsMapper
 */
public interface SpotsMapper {

    Long queryTotal(SpotsQuery spotsQuery);

    List queryData(SpotsQuery spotsQuery);

    /**
     * 保存景区
     */
    @Insert("insert into t_spots(name,address,opentime,price," +
            "tel,spotsDesc,info,purchaseinfo," +
            "features,spotsid,levelid,themeid," +
            "createtime,creatorid,fmUrl) " +
            "values(#{name},#{address},#{opentime},#{price}," +
            "#{tel},#{spotsDesc},#{info},#{purchaseinfo}," +
            "#{features},#{spotsid},#{levelid},#{themeid}," +
            "#{createtime},#{creatorid},#{fmUrl})")
    void saveSpots(Spots spots);

    @Update("update t_spots set name=#{name},address=#{address},opentime=#{opentime}," +
            "price=#{price},tel=#{tel},spotsDesc=#{spotsDesc},info=#{info}," +
            "purchaseinfo=#{purchaseinfo},features=#{features}," +
            "spotsid=#{spotsid},levelid=#{levelid}," +
            "themeid=#{themeid},fmUrl=#{fmUrl} " +
            " where id=#{id}")
    void updateSaveSpots(Spots spots);

    @Delete("delete from t_spots where id=#{id}")
    void deleteSpotsById(Long id);

    Spots getSpotById(Long id);
}
