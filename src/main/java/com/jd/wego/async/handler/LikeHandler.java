package com.jd.wego.async.handler;

import com.jd.wego.async.EventHandler;
import com.jd.wego.async.EventModel;
import com.jd.wego.async.EventType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author hbquan
 * @date 2021/4/14 15:53
 */
@Component
public class LikeHandler implements EventHandler {
    @Override
    public void doHandler(EventModel eventModel) {
        System.out.println("hello,world");
        System.out.println(eventModel.toString());
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        // 只关注点赞的事件
        return Arrays.asList(EventType.LIKE);
    }
}
