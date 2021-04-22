package com.jd.wego.service.impl;

import com.jd.wego.dao.FansDao;
import com.jd.wego.entity.Fans;
import com.jd.wego.service.FansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hbquan
 * @date 2021/4/21 9:28
 */
@Service
public class FansServiceImpl implements FansService {

    @Autowired(required = false)
    FansDao fansDao;

    @Override
    public void insertFans(Fans fans) {
        fansDao.insertFans(fans);
    }

    @Override
    public void deleteFans(String userId, String fansId) {
        fansDao.deleteFans(userId, fansId);
    }

    @Override
    public List<Fans> selectAllFansByUserId(String userId) {
        return fansDao.selectAllFansByUserId(userId);
    }
}
