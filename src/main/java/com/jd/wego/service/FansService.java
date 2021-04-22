package com.jd.wego.service;

import com.jd.wego.entity.Fans;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author hbquan
 * @date 2021/4/21 9:26
 */
public interface FansService {

    void insertFans(Fans fans);

    void deleteFans(String userId, String followId);

    List<Fans> selectAllFansByUserId(String userId);
}
