package com.jd.wego.controller;

import com.google.gson.Gson;
import com.jd.wego.entity.User;
import com.jd.wego.service.UserService;
import com.jd.wego.utils.CodeMsg;
import com.jd.wego.utils.Result;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;


/**
 * @author hbquan
 * @date 2021/3/30 14:54
 */

//@EnableAutoConfiguration(exclude = {MultipartAutoConfiguration.class})
@Controller
public class UserController {

    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Value("${qiniuyun.AccessKey}")
    private String accessKey;

    @Value("${qiniuyun.SecretKey}")
    private String secretKey;

    @Value("${qiniuyun.Bucket}")
    private String bucket;

    @Value("${qiniuyun.domain.name}")
    private String domainName;

    @Autowired
    UserService userService;

    @Autowired
    LoginController loginController;

    /**
     * 默认展示用户的基本信息，
     *
     * @return
     */
    @GetMapping("/userInfo")
    @ResponseBody
    public Result<User> userInfo(HttpServletRequest request) {
        User user = loginController.getUserInfo(request);
        String userId = user.getUserId();

        return Result.success(userService.selectByUserId(userId));
    }


    @PostMapping("/update/userInfo")
    @ResponseBody
    public Result<Boolean> updateUserInfo(HttpServletResponse response, @RequestBody User user) {
        // 更新之前，需要将从前端传过来的图片信息，上传到七牛云上去，然后存入数据库的话是一个链接
        // 需要更新的是用户昵称，用户头像，用户性别，用户学校，用户的个性签名
        String nickname = user.getNickname();
        // 这里应该获取的是前端传过来的url链接
        String avatar = user.getAvatar();
        int sex = user.getSex();
        String school = user.getSchool();
        String signature = user.getSignature();
        String userId = user.getUserId();

        //User u = new User();

        user.setNickname(nickname);
        user.setAvatar(avatar);
        user.setSex(sex);
        user.setSchool(school);
        user.setSignature(signature);
        user.setUserId(userId);


        // 重新设置Cookie，即更新Redis中User的信息
        loginController.addCookie(response, user);
        userService.updateByUserId(user);

        return Result.success(true);

    }


    /**
     * 注意：这里上传的图片大小不能超过1MB，如果超过1MB，就会报错
     * 而且在postman测试工具的时候，必须要不仅选择文件，还要在key中写file, 不然会报错空指针异常
     *
     * @param file
     * @return
     */
    @PostMapping("/upload/images")
    @ResponseBody
    public Result<String> uploadImages2Qiniuyun(MultipartFile file) {
        // 默认是是将图片的存放的地址设为了华南地区
        Configuration cfg = new Configuration(Region.huanan());
        UploadManager uploadManager = new UploadManager(cfg);

        // 默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        System.out.println(upToken);
        try {
            if (Objects.isNull(file)) {
                return Result.error(CodeMsg.UPLOAD_IMAGE_EMPTY);
            }

            Response response = uploadManager.put(file.getBytes(), key, upToken);
            // 解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            // 拿到这个Key之后，然后拼接域名 + "/" + key，就是该图片的路径了
            String result = domainName + "/" + putRet.key;
            // 返回的就是图片的url地址了
            log.info("图片的存储地址是：{}", result);
            return Result.success(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
