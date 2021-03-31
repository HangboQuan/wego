package com.jd.wego.utils;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author hbquan
 * @date 2021/3/31 14:49
 */
public class MD5Utils {

    // 使用MD5对密码进行加密操作
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }
}
