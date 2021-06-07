package com.jd.wego.utils;


import java.util.Random;

/**
 * @author hbquan
 * @date 2021/3/30 16:36
 */
public class GenerateRandomCode {

    /**
     * 随机生成一个6位数的验证码
     *
     * @return
     */
    public static String generateRandomVerificationCode() {

        Random random = new Random();
        StringBuilder randomCode = new StringBuilder();
        // 修改了生成随机验证码可能不是6位数的bug
        /*int num = random.nextInt(999999);
        String randomCode = num + "";*/
        for (int i = 0; i < 6; i++) {
            randomCode.append(random.nextInt(10));
        }
        System.out.println("你的验证码为:" + randomCode.toString());
        return randomCode.toString();

    }

}
