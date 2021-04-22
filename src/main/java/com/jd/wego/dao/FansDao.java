package com.jd.wego.dao;

import com.jd.wego.entity.Fans;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.swing.event.TableModelListener;
import java.util.*;

/**
 * @author hbquan
 * @date 2021/4/21 9:15
 */
@Mapper
public interface FansDao {

    String TABLE_NAME = " fans ";
    String INSERT_VALUE = " user_id, fans_id, created_time ";
    String SELECT_VALUE = " id, user_id, fans_id, created_time ";

    @Insert("insert into " + TABLE_NAME + "(" + INSERT_VALUE + ")values(#{userId}, #{fansId}, #{createdTime})")
    void insertFans(Fans fans);

    @Delete("delete from " + TABLE_NAME + " where user_id = #{userId} and fans_id = #{fansId}")
    void deleteFans(String userId, String fansId);

    @Select("select " + SELECT_VALUE + " from " + TABLE_NAME + "where user_id = #{userId}")
    List<Fans> selectAllFansByUserId(String userId);

}
