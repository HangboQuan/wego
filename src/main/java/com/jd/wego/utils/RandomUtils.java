package com.jd.wego.utils;

import java.util.Random;

/**
 * @author hbquan
 * @date 2021/3/31 15:08
 */
public class RandomUtils {

    /**
     * 随机生成一个6为的字符串，用来做salt
     */
    public static String randomSalt() {
        String result = "";
        for (int i = 0; i < 6; i++) {
            // 生成的都是小写字母
            int val = (int) (Math.random() * 26 + 97);
            result = result + (char) val;
        }
        return result;
    }

    /**
     * 随机生成一个5位的字符串，用来作为用户的部分昵称： 然后拼接上：用户 + result + 号
     * 而且生成的随机数字保证在10000-99999之间
     */

    public static int randomNickName() {
        Random random = new Random();
        int value = random.nextInt(90000) + 10000;
        return value;
    }


}

