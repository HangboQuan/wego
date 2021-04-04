package com.jd.wego.controller;

import com.jd.wego.config.GithubOauthConfig;
import com.jd.wego.utils.CodeMsg;
import com.jd.wego.utils.HttpClientUtils;
import com.jd.wego.utils.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hbquan
 * @date 2021/4/4 20:23
 */
@RestController
@RequestMapping("/oauth")
public class GithubOauthController {

    /**
     * 请求授权，获取到CODE值
     * @return
     */
    @GetMapping("/login")
    public String GithubLogin(){
        return "redirect:" + GithubOauthConfig.CODE_URL;
    }

    @GetMapping("/githubLogin")
    public Result<CodeMsg> callback(String code, String state){
        System.out.println("code=" + code);
        System.out.println("state=" + state);
        if(StringUtils.isEmpty(code) || StringUtils.isEmpty(state)){
            return Result.error(CodeMsg.GITHUB_CODE_OR_STATE_EMPTY);
        }else{
            String token_url = GithubOauthConfig.TOKEN_URL.replace("CODE", code);

            String responseStr = HttpClientUtils.doGet(token_url);
            System.out.println("responseStr=" + responseStr);

            // 从返回值中解析出token值
            String token = HttpClientUtils.getMap(responseStr).get("access_token");
            System.out.println("token=" + token);

            // 请求获取用户个人信息
            String userInfoUrl = GithubOauthConfig.USER_INFO_URL.replace("TOKEN", token);
            String userResponse = HttpClientUtils.doGet(userInfoUrl);
            if (StringUtils.isEmpty(userResponse)) {
                return Result.error(CodeMsg.ERROR);
            }
            return Result.success(CodeMsg.SUCCESS);
        }
    }
}
