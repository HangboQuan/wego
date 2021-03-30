package com.jd.wego.utils;

import com.jd.wego.controller.RegisterController;

import java.util.Random;

/**
 * @author hbquan
 * @date 2021/3/30 16:36
 */
public class GenerateRandomCode {

    /**
     * 随机生成一个6位数的验证码
     * @return
     */
    public static String generateRandomVerificationCode(){

        Random random = new Random();

        int num = random.nextInt(999999);
        String randomCode = num + "";
        System.out.println("你的验证码为:" + randomCode);

        return randomCode;

    }


}
