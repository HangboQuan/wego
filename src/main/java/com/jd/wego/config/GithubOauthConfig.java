package com.jd.wego.config;


import org.springframework.stereotype.Component;

/**
 * @author hbquan
 * @date 2021/4/4 19:43
 * <p>
 * 第三方登录的原理：
 * 1.A网站让用户跳转到Github
 * 2.Github要求用户登录，然后询问A网站要求获取XX权限，你是否同意？
 * 3.用户同意，Github就会重定向会A网站，同时返回一个授权码code(这里去请求回调地址的接口)
 * 4.A网站使用授权码，想Github请求令牌(在回调地址的接口中继续想Github中发送请求，请求获取令牌，令牌即TOKEN)
 * 5.Github返回令牌
 * 6.A网站使用令牌，想Github请求用户数据
 */

@Component
public class GithubOauthConfig {

    /**
     * 本地使用的第三方登录所需信息
     */
    //public static final String CLIENT_ID = "fff4de0f158b273ab1f0";

    //public static final String CLIENT_SECRET = "e68c1b7c8d49fb31a3685048906fb601a7ea09da";

    //public static final String CALLBACK = "http://localhost:8081/oauth/githubLogin";

    public static final String CLIENT_ID = "c98b1c698635496d6b8e";

    public static final String CLIENT_SECRET = "69e888393fdfec844a1894db6a8e3624e777cb2d";

    public static final String CALLBACK = "http://38617112yi.zicp.vip/oauth/githubLogin";

    /**
     * 获取code的url 这样github服务器返回一个code值 并传递给回调接口
     */
    public static final String CODE_URL = "https://github.com/login/oauth/authorize?client_id=" + CLIENT_ID + "&state=STATE&redirect_uri=" + CALLBACK;
    /**
     * 获取Github的令牌，即TOKEN，通过这个TOKEN值就可以获取到用户的个人信息
     */
    public static final String TOKEN_URL = "https://github.com/login/oauth/access_token?client_id=" + CLIENT_ID + "&client_secret=" + CLIENT_SECRET + "&code=CODE&redirect_uti=" + CALLBACK;


}
