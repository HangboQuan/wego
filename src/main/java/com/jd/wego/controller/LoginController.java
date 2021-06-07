package com.jd.wego.controller;

import com.jd.wego.entity.User;
import com.jd.wego.redis.JedisService;
import com.jd.wego.redis.UserTokenKey;
import com.jd.wego.redis.VerifyCodeKey;
import com.jd.wego.service.UserService;
import com.jd.wego.utils.*;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author hbquan
 * @date 2021/3/31 16:30
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private Logger log = LoggerFactory.getLogger(RegisterController.class);


    @Autowired
    JedisService jedisService;

    @Autowired
    UserService userService;
    /**
     * 发送短信验证码
     *
     * @return
     */
    public static final String USER_TOKEN = "token";

    @GetMapping("/sendSMSCode")
    public Result<String> sendSMSCode(String userId) {

        String randomCode = "";
        try {
            // 这里里面传递的参数是在腾讯云验证平台下提供的个人秘钥：SecretId, SecretKey
            Credential cred = new Credential("AKIDMWaQ8SIQPF6g4WuqnfJwSOI9nDPRqpBf", "PbXFlQr4gQQDmeZv4w4AHBqGROnw97qI");
            HttpProfile httpProfile = new HttpProfile();
            // 调用腾讯提供的接口
            httpProfile.setEndpoint("sms.tencentcloudapi.com");
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            SmsClient client = new SmsClient(cred, "", clientProfile);
            SendSmsRequest req = new SendSmsRequest();

            String phone = "+86" + userId;
            String[] phoneNumberSet1 = new String[]{phone};
            req.setPhoneNumberSet(phoneNumberSet1);

            // 绑定的是模板id
            req.setTemplateID("907863");
            req.setSign("欣然向上");

            // 这是给固定模板里面传递的验证码,注意是数组格式
            randomCode = GenerateRandomCode.generateRandomVerificationCode();
            jedisService.setKey(VerifyCodeKey.verifyCodeKeyLogin, randomCode, randomCode);
            String[] templateParamSet1 = new String[]{randomCode};
            req.setTemplateParamSet(templateParamSet1);

            // 腾讯云提供的smsSdkAppid
            req.setSmsSdkAppid("1400500804");

            SendSmsResponse resp = client.SendSms(req);
            log.info(SendSmsResponse.toJsonString(resp));

            return Result.success(randomCode);
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();

        }
        return Result.success(randomCode);
    }

    @GetMapping("/verifyLoginInfo")
    public Result<CodeMsg> loginVerify(HttpServletResponse response, String userId, String code) {
        String verifyCode = jedisService.getKey(VerifyCodeKey.verifyCodeKeyLogin, code, String.class);
        User user = userService.selectByUserId(userId);
        if (user == null) {
            return Result.error(CodeMsg.UNREGISTER_PHONE);
        }
        if (verifyCode == null || (!verifyCode.equals(code))) {
            return Result.error(CodeMsg.VERIFY_CODE_ERROR);
        }
        // 这里证明登录成功了，拿到用户信息了，这里我应该把用户的信息放在cookie和redis中
        addCookie(response, user);
        return Result.success(CodeMsg.SUCCESS);
    }

    /**
     * 提供一个可以提供手机号+密码的方式进行登录
     */
    @GetMapping("/loginPassword")
    public Result<CodeMsg> loginPassword(String userId, String password) {
        User user = userService.selectByUserId(userId);
        if (user == null) {
            return Result.error(CodeMsg.UNREGISTER_PHONE);
        }

        if (!(user.getPassword().equals(MD5Utils.md5(password + user.getSalt())))) {
            return Result.error(CodeMsg.PASSWORD_ERROR);
        } else {
            return Result.success(CodeMsg.SUCCESS);
        }
    }

    public void addCookie(HttpServletResponse response, User user) {
        String token = CommonUtils.uuid();
        log.info("token=" + token);
        // 将token值以及user保存进redis
        jedisService.setKey(UserTokenKey.userTokenKey, token, user);
        Cookie cookie = new Cookie(USER_TOKEN, token);
        // 设置60天的有效期
        cookie.setMaxAge(UserTokenKey.userTokenKey.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);

    }

    public String getUserToken(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length <= 0) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                return cookie.getValue();
            }
        }
        return null;
    }


    public User getUserInfo(HttpServletRequest request) {
        String token = getUserToken(request, LoginController.USER_TOKEN);
        return jedisService.getKey(UserTokenKey.userTokenKey, token, User.class);
    }

    /**
     * 当用户点击退出的时候，应该退出登录，并且应该清除Cookie
     */
    @GetMapping("/logout")
    @ResponseBody
    public Result<Boolean> logout(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(LoginController.USER_TOKEN)) {
                // 马上设置该cookie过期
                String token = cookie.getValue();
                cookie.setMaxAge(0);
                cookie.setPath("/");
                // 设置完cookie过期之后，也应该清空Redis保存的Cookie值
                jedisService.del(UserTokenKey.userTokenKey, token);

            }
        }
        return Result.success(true);
    }
}
