package com.jd.wego.dao;

import com.jd.wego.entity.Notice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author hbquan
 * @date 2021/4/14 20:51
 */
@Mapper
public interface NoticeDao {

    String TABLE_NAME = " notice ";
    String INSERT_VALUE = " from_id, to_id, content, created_date, has_read, conversation_id ";
    String SELECT_VALUE = " id " + INSERT_VALUE;

    @Insert("insert into" + TABLE_NAME + "(" + INSERT_VALUE + ") values(#{fromId}, #{toId}, #{content}, " +
            "#{createdDate}, #{hasRead}, #{conversationId})" )
    void insertNotice(Notice notice);

    @Select("select " + SELECT_VALUE + " from " + TABLE_NAME)
    Notice selectAllNotice();

}
