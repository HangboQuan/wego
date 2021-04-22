package com.jd.wego.dao;

import com.jd.wego.entity.Fans;
import com.jd.wego.entity.Follow;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author hbquan
 * @date 2021/4/21 9:16
 */
@Mapper
public interface FollowDao {

    String TABLE_NAME = " follow ";
    String INSERT_VALUE = " user_id, follow_id, created_time ";
    String SELECT_VALUE = " id, user_id, follow_id, created_time ";

    @Insert("insert into " + TABLE_NAME + "(" + INSERT_VALUE + ")values(#{userId}, #{followId}, #{createdTime})")
    void insertFollow(Follow follow);

    @Delete("delete from " + TABLE_NAME + " where user_id = #{userId} and follow_id = #{followId}")
    void deleteFollow(String userId, String followId);

    @Select("select " + SELECT_VALUE + " from " + TABLE_NAME + "where user_id = #{userId}")
    List<Follow> selectAllFollowByUserId(String userId);

    @Select("select " + SELECT_VALUE + " from " + TABLE_NAME + "where follow_id = #{followId}")
    List<Follow> selectAllFollowByFollowId(String followId);
}
