package com.jd.wego.dao;

import com.jd.wego.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author hbquan
 * @date 2021/3/30 16:57
 */
@Mapper
public interface UserDao {

    @Insert("insert into user(user_id, nickname, password, salt, avatar, achieve_value, school, login_ip, create_time, login_type) values(#{userId}, #{nickname}, #{password}, #{salt}, #{avatar}, #{achieveValue}, #{school}, #{loginIp}, #{createTime}, #{loginType})")
    void insert(User user);

    @Select("select * from user where user_id = #{userId}")
    User selectByUserId(String userId);

    /**
     * 以注解形式，只更改部分用户信息
     * @param user
     */
    @Update("<script>" +
            "update user " +
            "<set>" +
            "<if test ='nickname != null'>nickname = #{nickname},</if>" +
            "<if test ='password != null'>password = #{password},</if>" +
            "<if test ='salt' != null>salt = #{salt},</if>" +
            "<if test ='avatar != null'>avatar = #{avatar},</if>" +
            "<if test ='achieveValue != null'>achieve_value = #{achieveValue},</if>" +
            "<if test ='school != null'>school = #{school},</if>" +
            "<if test ='loginIp != null'>login_ip = #{loginIp},</if>" +
            "<if test ='createTime != null'>create_time = #{createTime},</if>" +
            "<if test ='loginType != null'>login_type = #{loginType}</if>" +
            "</set>" +
            "where user_id = #{userId}" +
            "</script>")
    void updateByUserId(User user);
}
