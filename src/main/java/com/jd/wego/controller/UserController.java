package com.jd.wego.controller;

import com.jd.wego.entity.User;
import com.jd.wego.service.UserService;
import com.jd.wego.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author hbquan
 * @date 2021/3/30 14:54
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/update/userInfo")
    @ResponseBody
    public Result<User> updateUserInfo(){
        User user = new User();
        user.setNickname("18892974688");
        userService.updateByUserId(user);
        return Result.success(user);
    }
}
