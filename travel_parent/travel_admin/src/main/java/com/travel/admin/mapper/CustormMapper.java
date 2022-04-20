package com.travel.admin.mapper;

import com.travel.common.domain.Custorm;
import com.travel.common.query.CustormQuery;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @description: CustormMapper
 */
public interface CustormMapper {

    @Insert("insert into t_custorm(username,password,email," +
            "tel,createtime,totalpoints,headImg) " +
            "values(#{username},#{password},#{email},#{tel},#{createtime},#{totalpoints},#{headImg})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void regCustorm(Custorm custorm);


    Custorm loginCustorm(Custorm custorm);

    @Update("update t_custorm set username=#{username},tel=#{tel},email=#{email} where id=#{id}")
    void editSaveUserInfo(Custorm custorm);

    @Select("select * from t_custorm where id = #{id}")
    Custorm queryCustormById(Long id);

    @Update("update t_custorm set password=#{password} where id=#{id}")
    void updatePwd(Custorm custorm);
    @Update("update t_custorm set headImg=#{headImg} where id=#{id}")
    void updateHeadImg(Custorm custorm);

    Long queryTotal(CustormQuery custormQuery);

    List queryData(CustormQuery custormQuery);

}
