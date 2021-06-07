package com.jd.wego.controller;

import com.jd.wego.entity.Fans;
import com.jd.wego.entity.User;
import com.jd.wego.redis.FansKey;
import com.jd.wego.redis.FollowKey;
import com.jd.wego.redis.JedisService;
import com.jd.wego.service.FansService;
import com.jd.wego.service.UserService;
import com.jd.wego.utils.CodeMsg;
import com.jd.wego.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hbquan
 * @date 2021/4/21 9:33
 */
@Controller
public class FansController {

    private static final Logger log = LoggerFactory.getLogger(FansController.class);

    @Autowired
    FansService fansService;

    @Autowired
    JedisService jedisService;

    @Autowired
    LoginController loginController;

    @Autowired
    UserService userService;

    @GetMapping("/fans/list")
    @ResponseBody
    public Result<List<User>> fansList(HttpServletRequest request) {

        User user = loginController.getUserInfo(request);

        // User user = userService.selectByUserId("18392710807");
        if (user == null) {
            return Result.error(CodeMsg.ERROR);
        } else {
            String userId = user.getUserId();
            String realKey = FansKey.fansKey.getPrefix() + userId;
            Set<String> set = jedisService.smembers(realKey);
            List<User> usersList = new ArrayList<>();
            if (!set.isEmpty()) {
                // 这个set里面全部存储的userId,注意是String类型,然后根据这个来查询出User的信息
                for (String str : set) {
                    User u = userService.selectByUserId(str);
                    usersList.add(u);
                }
                log.info("从Redis中获取我的粉丝列表");
            } else {
                // 如果从Redis拿不到数据的话，就要从mysql中取数据
                List<Fans> fansList = fansService.selectAllFansByUserId(userId);
                for (Fans fans : fansList) {
                    User u = userService.selectByUserId(fans.getFansId());
                    usersList.add(u);
                }
                log.info("从mysql中获取粉丝列表");
            }
            return Result.success(usersList);
        }
    }
}
