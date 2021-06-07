package com.jd.wego.async;

import java.util.*;

/**
 * @author hbquan
 * @date 2021/4/14 15:50
 */
public interface EventHandler {

    /**
     * 处理EventModel
     */
    void doHandler(EventModel eventModel);

    List<EventType> getSupportEventTypes();
}
