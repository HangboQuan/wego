package com.jd.wego.dao;

import com.jd.wego.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.*;

/**
 * @author hbquan
 * @date 2021/3/30 16:57
 */
@Mapper
public interface UserDao {

    String INSERT_VALUE = " user_id, nickname, password, salt, avatar, achieve_value, school, login_ip, create_time, login_type, sex, signature ";
    String TABLE_NAME = " user ";

    @Insert("insert into " + TABLE_NAME + "(" + INSERT_VALUE + ") values(#{userId}, #{nickname}, #{password}, #{salt}, #{avatar}, #{achieveValue}, #{school}, #{loginIp}, #{createTime}, #{loginType}, #{sex}, #{signature})")
    void insert(User user);

    @Select("select " + INSERT_VALUE + "from " + TABLE_NAME + " where user_id = #{userId}")
    User selectByUserId(String userId);

    /**
     * 以注解形式，只更改部分用户信息
     *
     * @param
     */
    @Update("<script>" +
            "update user " +
            "<set>" +
            "<if test ='nickname != null'>nickname = #{nickname},</if>" +
            "<if test ='password != null'>password = #{password},</if>" +
            "<if test ='salt != null'>salt = #{salt},</if>" +
            "<if test ='avatar != null'>avatar = #{avatar},</if>" +
            "<if test ='achieveValue != null'>achieve_value = #{achieveValue},</if>" +
            "<if test ='school != null'>school = #{school},</if>" +
            "<if test ='loginIp != null'>login_ip = #{loginIp},</if>" +
            "<if test ='createTime != null'>create_time = #{createTime},</if>" +
            "<if test ='loginType != null'>login_type = #{loginType},</if>" +
            "<if test ='sex != null'>sex = #{sex},</if>" +
            "<if test ='signature != null'>signature = #{signature}</if>" +
            "</set>" +
            "where user_id = #{userId}" +
            "</script>")
    void updateByUserId(User user);

    @Update("update user set achieve_value = 0")
    void resetAchieveValue();

    /**
     * 查询成就值排行前10的功能
     */
    @Select("select " + INSERT_VALUE + " from " + TABLE_NAME + " order by achieve_value desc limit 0,10")
    List<User> top10LeaderBoard();
}
