package com.jd.wego.service;

import com.jd.wego.entity.Notice;

import java.util.*;

/**
 * @author hbquan
 * @date 2021/4/14 20:59
 */
public interface NoticeService {

    void insertNotice(Notice notice);

    Notice selectNotice();

    void updateAllNoticeHasRead(String userId);

    int countNoticeHasRead(String userId);

    List<Notice> noticeList(String userId);
}
