package com.jd.wego.service;

import com.jd.wego.entity.Fans;
import com.jd.wego.entity.Follow;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author hbquan
 * @date 2021/4/21 9:27
 */
public interface FollowService {


    void insertFollow(Follow follow);

    void deleteFollow(String userId, String followId);

    List<Follow> selectAllFollowByUserId(String userId);

    List<Follow> selectAllFollowByFollowId(String followId);
}
