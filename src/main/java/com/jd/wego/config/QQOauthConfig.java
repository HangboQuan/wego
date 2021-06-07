package com.jd.wego.config;

import org.springframework.stereotype.Component;

/**
 * @author hbquan
 * @date 2021/4/5 20:04
 */
@Component
public class QQOauthConfig {

    public static final String APP_KEY = "101944926";

    public static final String APP_SECRET = "7914276030f202292e7df758a1b825c6";

    //public static final String APP_KEY = "101813698";

    //public static final String APP_SECRET = "7265da646c6cf33f6326f6844c1c4ef3";

    public static final String REDIRECT_URL = "http://localhost:8081/oauth/qqLogin";

    /**
     * 调用该接口获取code值 display 属性默认是PC网站接入使用
     */
    public static final String CODE_URL = "https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=" + APP_KEY + "&redirect_uri=" + REDIRECT_URL + "&state=state";

    /**
     * 根据上述链接返回的code值，继续想QQ服务器发送请求，获取TOKEN值即令牌
     */
    public static final String TOKEN_URL = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id=" + APP_KEY + "&client_secret=" + APP_SECRET + "&code=CODE&redirect_uri=" + REDIRECT_URL;

    /**
     * 根据上面的请求，获取到令牌之后，然后去请求获取到用户的信息
     */
    public static final String USER_OPENID_URL = "https://graph.qq.com/oauth2.0/me?access_token=TOKEN";
}
