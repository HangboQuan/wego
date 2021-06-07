package com.jd.wego.utils;

import lombok.Data;

/**
 * @author hbquan
 * @date 2021/3/30 14:55
 */
@Data
public class Result<T> {

    private int code;
    private String msg;
    private T data;

    public Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    public Result(CodeMsg cm) {
        if (null == cm) {
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }


    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    public static <T> Result<T> error(CodeMsg cm) {
        return new Result<T>(cm);
    }


}
