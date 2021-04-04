package com.jd.wego.controller;

import com.jd.wego.entity.User;
import com.jd.wego.redis.JedisService;
import com.jd.wego.redis.VerifyCodeKey;
import com.jd.wego.service.UserService;
import com.jd.wego.utils.CodeMsg;
import com.jd.wego.utils.GenerateRandomCode;
import com.jd.wego.utils.MD5Utils;
import com.jd.wego.utils.Result;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            jedisService.setKey(VerifyCodeKey.verifyCodeKeyLogin, randomCode, randomCode);
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

    @GetMapping("/verifyLoginInfo")
    public Result<CodeMsg> loginVerify(String userId, String code){
        String verifyCode = jedisService.getKey(VerifyCodeKey.verifyCodeKeyLogin, code, String.class);
        User user = userService.selectByUserId(userId);
        if(user == null){
            return Result.error(CodeMsg.UNREGISTER_PHONE);
        }
        if(verifyCode == null || (!verifyCode.equals(code))){
            return Result.error(CodeMsg.VERIFY_CODE_ERROR);
        }
        return Result.success(CodeMsg.SUCCESS);
    }

    /**
     *  提供一个可以提供手机号+密码的方式进行登录
     */
    @GetMapping("/loginPassword")
    public Result<CodeMsg> loginPassword(String userId, String password){
        User user = userService.selectByUserId(userId);
        if(user == null){
            return Result.error(CodeMsg.UNREGISTER_PHONE);
        }

        if(!(user.getPassword().equals(MD5Utils.md5(password + user.getSalt())))){
            return Result.error(CodeMsg.PASSWORD_ERROR);
        }else{
            return Result.success(CodeMsg.SUCCESS);
        }


    }
}
