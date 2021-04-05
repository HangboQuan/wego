package com.jd.wego.utils;

/**
 * @author hbquan
 * @date 2021/4/5 17:21
 */


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 通用的工具类
 */

public class CommonUtils {

    public static Date githubDateToDate(String createTime){
        char[] ch = createTime.toCharArray();
        for(int i = 0; i < ch.length; i++){
            if(ch[i] >= 'A' && ch[i] <= 'Z'){
                ch[i] = ' ';
            }
        }
        String value = String.valueOf(ch);
        Date createDate = null;
        try{
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            createDate = sf.parse(value);
        }catch (Exception e){
            e.printStackTrace();
        }
        return createDate;
    }

    public static void main(String[] args) {
        System.out.println(githubDateToDate("2021-04-05T06:44:06Z"));
    }
}
