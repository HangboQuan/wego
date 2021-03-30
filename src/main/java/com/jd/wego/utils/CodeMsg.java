package com.jd.wego.utils;

import lombok.Data;

/**
 * @author hbquan
 * @date 2021/3/30 15:59
 */
@Data
public class CodeMsg {

    private int code;
    private String msg;

    public CodeMsg(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public static CodeMsg SUCCESS = new CodeMsg(0, "SUCCESS");
    public static CodeMsg VERIFY_CODE_ERROR = new CodeMsg(100000, "手机验证码错误");
}
