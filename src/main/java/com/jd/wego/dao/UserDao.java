package com.jd.wego.dao;

import com.jd.wego.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author hbquan
 * @date 2021/3/30 16:57
 */
@Mapper
public interface UserDao {

    @Insert("insert into user(user_id, nickname, password, salt, avatar, achieve_value, school, login_ip) values(#{userId}, #{nickname}, #{password}, #{salt}, #{avatar}, #{achieveValue}, #{school}, #{loginIp})")
    void insert(User user);

    @Select("select * from user where user_id = #{userId}")
    User selectByUserId(String userId);
}
