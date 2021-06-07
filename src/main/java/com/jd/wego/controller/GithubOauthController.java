package com.jd.wego.controller;

import com.jd.wego.config.GithubOauthConfig;
import com.jd.wego.entity.User;
import com.jd.wego.redis.UserTokenKey;
import com.jd.wego.service.UserService;
import com.jd.wego.utils.CodeMsg;
import com.jd.wego.utils.CommonUtils;
import com.jd.wego.utils.HttpClientUtils;
import com.jd.wego.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author hbquan
 * @date 2021/4/4 20:23
 */
@Controller
@RequestMapping("/oauth")
public class GithubOauthController {

    private static Logger logger = LoggerFactory.getLogger(GithubOauthController.class);

    @Autowired
    UserService userService;

    @Autowired
    LoginController loginController;

    /**
     * 请求授权，获取到CODE值
     *
     * @return
     */
    @GetMapping("/login")
    public String GithubLogin() {
        return "redirect:" + GithubOauthConfig.CODE_URL;
    }

    @GetMapping("/githubLogin")
    @ResponseBody
    public Result<User> callback(HttpServletResponse response, String code, String state) {
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(state)) {
            return Result.error(CodeMsg.GITHUB_CODE_OR_STATE_EMPTY);
        } else {
            String token_url = GithubOauthConfig.TOKEN_URL.replace("CODE", code);
            String responseStr = HttpClientUtils.doGet(token_url);
            if (responseStr == null) {
                return Result.error(CodeMsg.GITHUB_REQUEST_TOKEN_EMPTY);
            }
            logger.info("response=" + responseStr);
            // 从返回值中解析出token值
            String token = HttpClientUtils.getMap(responseStr).get("access_token");
            logger.info("token=" + token);


            // 请求获取用户个人信息
            String userInfoUrl = "https://api.github.com/user";
            // 注意这里不能简单的发送一个Github请求就算了，这里要把token值设置到Header中，否则无法获取到用户的信息
            String userResponse = HttpClientUtils.doGetHeader(userInfoUrl, token);
            if (userResponse == null) {
                return Result.error(CodeMsg.GITHUB_REQUEST_USER_INFO_EMPTY);
            }
            logger.info("userResponse=" + userResponse);


            // 登录成功之后，要解析Response，将有用的信息要写入到数据中
            User user = new User();
            Map<String, String> map = HttpClientUtils.getMapJSON(userResponse);
            user.setUserId(map.get("id"));
            // 设置用户昵称
            user.setNickname(map.get("login"));
            user.setPassword(map.get("node_id"));
            user.setAvatar(map.get("avatar_url"));
            user.setCreateTime(CommonUtils.githubDateToDate(map.get("updated_at")));
            user.setLoginIp("github");
            if (userService.selectByUserId(user.getUserId()) == null) {
                userService.insert(user);
            }
            loginController.addCookie(response, user);
            return Result.success(user);
        }
    }


}
