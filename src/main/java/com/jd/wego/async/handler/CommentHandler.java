package com.jd.wego.async.handler;

import com.jd.wego.async.EventHandler;
import com.jd.wego.async.EventModel;
import com.jd.wego.async.EventType;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author hbquan
 * @date 2021/4/14 19:13
 */
@Component
public class CommentHandler implements EventHandler {
    @Override
    public void doHandler(EventModel eventModel) {

    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.COMMNET);
    }
}
