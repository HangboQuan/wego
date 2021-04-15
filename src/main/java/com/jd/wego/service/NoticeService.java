package com.jd.wego.service;

import com.jd.wego.entity.Notice;

/**
 * @author hbquan
 * @date 2021/4/14 20:59
 */
public interface NoticeService {

    void insertNotice(Notice notice);

    Notice selectNotice();
}
