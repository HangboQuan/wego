package com.jd.wego.controller;

import com.alibaba.druid.util.StringUtils;
import com.jd.wego.config.QQOauthConfig;
import com.jd.wego.utils.CodeMsg;
import com.jd.wego.utils.HttpClientUtils;
import com.jd.wego.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hbquan
 * @date 2021/4/5 20:30
 */
@Controller
@RequestMapping("/oauth")
public class QQOauthController {

    private static Logger logger = LoggerFactory.getLogger(QQOauthController.class);

    /**
     * 该接口暂未调试成功，原因是：腾讯必须要在QQ互联中审核通过之后才能使用该功能，没有审核通过的话，
     * 访问的话包100008，该网站尚未开通QQ登录功能
     *
     * @return
     */
    @GetMapping("/qq")
    public String QQLogin() {
        // 调用这个URL，去获取CODE值
        return "redirect:" + QQOauthConfig.CODE_URL;
    }


    /**
     * 该接口暂时为调试成功，原因是：腾讯必须要在QQ互联中审核通过之后才能使用该功能
     *
     * @param code
     * @param state
     * @return
     */
    @GetMapping("/qqLogin")
    @ResponseBody
    public Result<Boolean> callback(String code, String state) {
        // 目前这里无法获取到code和state，原因是这个必须需要腾讯审核通过之后才允许
        logger.info("code=" + code);
        logger.info("state=" + state);
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(state)) {
            return Result.error(CodeMsg.ERROR);
        } else {
            String token_url = QQOauthConfig.TOKEN_URL.replace("CODE", code);
            String responseStr = HttpClientUtils.doGet(token_url);
            if (responseStr == null) {
                return Result.error(CodeMsg.ERROR);
            }
            logger.info("response=" + responseStr);
            String token = HttpClientUtils.getMap(responseStr).get("access_token");
            logger.info("token=" + token);

            String userInfoUrl = QQOauthConfig.USER_OPENID_URL.replace("TOKEN", token);
            String userResponse = HttpClientUtils.doGet(userInfoUrl);
            if (userResponse == null) {
                return Result.error(CodeMsg.ERROR);
            }

        }
        return Result.success(true);
    }
}
