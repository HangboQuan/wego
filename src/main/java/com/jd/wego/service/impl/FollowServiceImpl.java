package com.jd.wego.service.impl;

import com.jd.wego.dao.FollowDao;
import com.jd.wego.entity.Fans;
import com.jd.wego.entity.Follow;
import com.jd.wego.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hbquan
 * @date 2021/4/21 9:30
 */
@Service
public class FollowServiceImpl implements FollowService {

    @Autowired(required = false)
    FollowDao followDao;


    @Override
    public void insertFollow(Follow follow) {
        followDao.insertFollow(follow);
    }

    @Override
    public void deleteFollow(String userId, String followId) {
        followDao.deleteFollow(userId, followId);
    }

    @Override
    public List<Follow> selectAllFollowByUserId(String userId) {
        return followDao.selectAllFollowByUserId(userId);
    }

    @Override
    public List<Follow> selectAllFollowByFollowId(String followId) {
        return followDao.selectAllFollowByFollowId(followId);
    }
}
