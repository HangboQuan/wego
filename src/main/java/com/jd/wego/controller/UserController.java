package com.jd.wego.controller;

import com.google.gson.Gson;
import com.jd.wego.entity.User;
import com.jd.wego.service.UserService;
import com.jd.wego.utils.Result;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author hbquan
 * @date 2021/3/30 14:54
 */
@Controller
public class UserController {

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

    @GetMapping("/update/userInfo")
    @ResponseBody
    public Result<Boolean> updateUserInfo(User user){
        // 更新之前，需要将从前端传过来的图片信息，上传到七牛云上去，然后存入数据库的话是一个链接
        // 需要更新的是用户昵称，用户头像，用户性别，用户学校，用户的个性签名
        String nickname = user.getNickname();
        // 这里应该获取的是前端传过来的url链接
        String avatar = user.getAvatar();
        int sex = user.getSex();
        String school = user.getSchool();
        String signature = user.getSignature();

        User u = new User();
        u.setNickname(nickname);
        u.setAvatar(avatar);
        u.setSex(sex);
        u.setSchool(school);
        u.setSignature(signature);
        userService.updateByUserId(u);
        return Result.success(true);

    }


    /**
     * 注意：这里上传的图片大小不能超过1MB，如果超过1MB，就会报错
     * @param file
     * @return
     */
    @PostMapping("/upload/images")
    @ResponseBody
    public Result<String> uploadImages2Qiniuyun(MultipartFile file){
        //默认是是将图片的存放的地址设为了华南地区
        Configuration cfg = new Configuration(Region.huanan());
        UploadManager uploadManager = new UploadManager(cfg);

        // 默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        System.out.println(upToken);
        try{
            Response response = uploadManager.put(file.getBytes(), key, upToken);
            // 解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            // 拿到这个Key之后，然后拼接域名 + "/" + key，就是该图片的路径了
            String result = domainName + "/" + putRet.key;
            // 返回的就是图片的url地址了
            return Result.success(result);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
