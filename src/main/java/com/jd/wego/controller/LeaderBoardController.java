package com.jd.wego.controller;

import com.jd.wego.entity.User;
import com.jd.wego.service.UserService;
import com.jd.wego.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @author hbquan
 * @date 2021/5/6 18:36
 * <p>
 * 实现排行榜的功能
 */
@Controller
public class LeaderBoardController {

    @Autowired
    UserService userService;


    /**
     * 返回成就值排行
     *
     * @return
     */
    @GetMapping("/TopAchieve")
    @ResponseBody
    public Result<List<User>> Top10LeaderBoard() {
        return Result.success(userService.top10LeaderBoard());
    }
}
