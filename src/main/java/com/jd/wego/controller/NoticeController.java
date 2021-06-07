package com.jd.wego.controller;

import com.jd.wego.entity.Notice;
import com.jd.wego.entity.User;
import com.jd.wego.service.NoticeService;
import com.jd.wego.service.UserService;
import com.jd.wego.utils.CodeMsg;
import com.jd.wego.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hbquan
 * @date 2021/5/23 16:21
 */
@Controller
public class NoticeController {

    @Autowired
    LoginController loginController;

    @Autowired
    NoticeService noticeService;

    @Autowired
    UserService userService;

    /**
     * 异步通知和前端联调设计思路：notice表中有一个has_read属性，has_read 0表示的是未读，
     * 1 表示的是已读，如果有未读的消息，则给前端返回true，没有未读的消息，则给前端返回false
     * 前端拿到true之后，应该出现红点，当用户点击红点的时候，后端逻辑给前端返回和该用户相关的
     * 数据，并将所有的数据has_read属性设置为1表示已读
     */
    @GetMapping("/hasReadNotice")
    @ResponseBody
    public Result<Boolean> hasReadIsZero(HttpServletRequest request) {
        User user = loginController.getUserInfo(request);
        if (user == null) {
            return Result.error(CodeMsg.NOT_LOGIN);
        }
        //User user = userService.selectByUserId("18392710807");
        //通过数据库查询notice表中是否有has_read属性是否为0,0表示的是未读
        int count = noticeService.countNoticeHasRead(user.getUserId());
        if (count > 0) {
            return Result.success(true);
        } else {
            return Result.success(false);
        }
    }

    @GetMapping("/notice/list")
    @ResponseBody
    public Result<List<Notice>> noticeList(HttpServletRequest request) {
        User user = loginController.getUserInfo(request);
        if (user == null) {
            return Result.error(CodeMsg.NOT_LOGIN);
        }
        //User user = userService.selectByUserId("18392710807");
        noticeService.updateAllNoticeHasRead(user.getUserId());
        List<Notice> notices = noticeService.noticeList(user.getUserId());
        return Result.success(notices);
    }
}
