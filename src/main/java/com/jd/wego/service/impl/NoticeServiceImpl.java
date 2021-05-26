package com.jd.wego.service.impl;

import com.jd.wego.dao.NoticeDao;
import com.jd.wego.entity.Notice;
import com.jd.wego.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hbquan
 * @date 2021/4/14 21:00
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired(required = false)
    NoticeDao noticeDao;

    @Override
    public void insertNotice(Notice notice) {
        noticeDao.insertNotice(notice);
    }

    @Override
    public Notice selectNotice() {
        return noticeDao.selectAllNotice();
    }

    @Override
    public void updateAllNoticeHasRead(String userId) {
        noticeDao.updateAllNoticeHasRead(userId);
    }

    @Override
    public int countNoticeHasRead(String userId) {
        return noticeDao.countNoticeHasRead(userId);
    }

    @Override
    public List<Notice> noticeList(String userId) {
        return noticeDao.noticeList(userId);
    }
}
