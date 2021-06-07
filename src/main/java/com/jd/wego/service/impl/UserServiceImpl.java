package com.jd.wego.service.impl;

import com.jd.wego.dao.UserDao;
import com.jd.wego.entity.User;
import com.jd.wego.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author hbquan
 * @date 2021/3/30 16:58
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    UserDao userDao;

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public User selectByUserId(String userId) {
        return userDao.selectByUserId(userId);
    }

    @Override
    public void updateByUserId(User user) {
        userDao.updateByUserId(user);
    }

    @Override
    public void resetAchieveValue() {
        userDao.resetAchieveValue();
    }

    @Override
    public List<User> top10LeaderBoard() {
        return userDao.top10LeaderBoard();
    }


}
