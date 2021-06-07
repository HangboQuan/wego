package com.jd.wego.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hbquan
 * @date 2021/4/4 20:39
 */
public class HttpClientUtils {

    /**
     * 利用Java发请求
     *
     * @param url
     * @return
     */
    public static String doGet(String url) {
        // 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建一个Get请求对象实例, 注意这里的url必须是以http或者https开头
        HttpGet httpGet = new HttpGet(url);
        try {
            // 发送http请求
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                // 状态码是200表示是请求成功, 获取响应体
                HttpEntity httpEntity = response.getEntity();

                return EntityUtils.toString(httpEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 利用Java发送请求，并且设置请求头
     *
     * @param url
     * @param token
     * @return
     */
    public static String doGetHeader(String url, String token) {
        // 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建一个Get请求对象实例, 注意这里的url必须是以http或者https开头
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Authorization", "token " + token);
        try {
            // 发送http请求
            CloseableHttpResponse response = httpClient.execute(httpGet);
            System.out.println("response=" + response);
            System.out.println("responseCode=" + response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() == 200) {
                // 状态码是200表示是请求成功, 获取响应体
                HttpEntity httpEntity = response.getEntity();

                return EntityUtils.toString(httpEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


    public static Map<String, String> getMap(String httpEntity) {
        Map<String, String> map = new HashMap<>();
        // 以&来解析字符串
        String[] result = httpEntity.split("\\&");
        for (String s : result) {
            String[] split = s.split("=");
            // 将字符串存入map中
            if (split.length == 1) {
                map.put(split[0], null);
            } else {
                map.put(split[0], split[1]);
            }
        }
        return map;
    }


    public static Map<String, String> getMapJSON(String response) {
        Map<String, String> map = new HashMap<>();
        // 将字符串转为JSON对象
        JSONObject jsonObject = JSONObject.parseObject(response);
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            String value = String.valueOf(entry.getValue());
            map.put(key, value);

        }

        return map;
    }

    public static void main(String[] args) {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://wegocoder.top/article/13");
        try {
            //发送一个get请求
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = response.getEntity();
                System.out.println(EntityUtils.toString(httpEntity));
                System.out.println("-------------------");
                //报错代码
                getMap(EntityUtils.toString(httpEntity));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
