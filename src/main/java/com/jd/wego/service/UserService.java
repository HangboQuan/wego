package com.jd.wego.service;

import com.jd.wego.entity.User;
import org.apache.ibatis.annotations.Insert;

/**
 * @author hbquan
 * @date 2021/3/30 16:58
 */
public interface UserService {

    void insert(User user);

    User selectByUserId(String userId);

}
