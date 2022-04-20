package com.travel.admin.mapper;

import com.travel.common.domain.Notes;
import com.travel.common.query.NotesQuery;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @description: NotesMapper
 * @author: 赵梦涵
 * @email: 33631575@qq.com
 * @date: created by 2020/10/29
 * @copyright: zhaomenghan
 */
public interface NotesMapper {
    @Insert("insert into t_notes(title,notesdesc,departureTime,traveldays," +
            "precost,content,createtime,viewnum,creatorid," +
            "fmUrl,travelnums,hotelname) " +
            "values(#{title},#{notesdesc},#{departureTime}" +
            ",#{traveldays},#{precost},#{content},#{createtime}," +
            "#{viewnum},#{creatorid},#{fmUrl},#{travelnums},#{hotelname})")
    void saveNotes(Notes notes);


    Long queryTotal(NotesQuery notesQuery);


    List queryData(NotesQuery notesQuery);

    @Select("select * from t_notes where id=#{id}")
    Notes getNotesById(NotesQuery notesQuery);

    @Update("update t_notes set viewnum=viewnum+1 where id=#{id}")
    void updateNotesViewNum(Long id);
}
