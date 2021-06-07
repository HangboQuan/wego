package com.jd.wego.controller;

import com.jd.wego.entity.User;
import com.jd.wego.redis.JedisService;
import com.jd.wego.redis.VerifyCodeKey;
import com.jd.wego.service.UserService;
import com.jd.wego.utils.*;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;

import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
;import java.util.Date;

/**
 * @author hbquan
 * @date 2021/3/30 15:48
 */
@RestController
@RequestMapping(value = "/register")
public class RegisterController {

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
    @GetMapping("/sendSMSCode")
    public Result<CodeMsg> sendSMSCode(String userId) {

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
            String randomCode = GenerateRandomCode.generateRandomVerificationCode();
            jedisService.setKey(VerifyCodeKey.verifyCodeKeyRegister, randomCode, randomCode);
            String[] templateParamSet1 = new String[]{randomCode};
            req.setTemplateParamSet(templateParamSet1);

            // 腾讯云提供的smsSdkAppid
            req.setSmsSdkAppid("1400500804");

            SendSmsResponse resp = client.SendSms(req);
            log.info(SendSmsResponse.toJsonString(resp));

            return Result.success(CodeMsg.SUCCESS);
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
        }
        return Result.success(CodeMsg.SUCCESS);
    }


    /**
     * 这里的参数传递应该是传递的是校验码,校验验证码，判断用户填的校验码和用Redis临时存储的验证码是否匹配
     *
     * @param code
     * @return
     */
    @GetMapping("/verifyRegisterInfo")
    public Result<CodeMsg> registerVerify(String code, String userId, String password) {
        String verifyCode = jedisService.getKey(VerifyCodeKey.verifyCodeKeyRegister, code, String.class);
        if (verifyCode == null) {
            return Result.error(CodeMsg.VERIFY_CODE_ERROR);
        }

        // 判断该手机号是否注册过了
        User u = userService.selectByUserId(userId);
        if (u != null) {
            return Result.error(CodeMsg.DUPLICATE_REGISTRY);
        }
        // 随机生成一个6位数的小写字符串
        String salt = RandomUtils.randomSalt();
        String nickname = "用户" + RandomUtils.randomNickName() + "号";

        User user = new User();
        user.setUserId(userId);
        user.setSalt(salt);
        user.setPassword(MD5Utils.md5(password + salt));
        user.setNickname(nickname);
        user.setLoginIp("phone");
        user.setCreateTime(new Date());
        userService.insert(user);
        return Result.success(CodeMsg.SUCCESS);


    }

}
