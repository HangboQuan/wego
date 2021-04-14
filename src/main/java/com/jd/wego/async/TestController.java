package com.jd.wego.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.jd.wego.async.EventType.LIKE;

/**
 * @author hbquan
 * @date 2021/4/14 17:08
 */
@Controller
public class TestController {

    @Autowired
    EventProducer eventProducer;

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        EventModel eventModel = new EventModel();
        eventModel.setEventType(LIKE).setActorId("1").setEntityId(2).setEntityType(3).setEntityOwnerId("4")
            .setExts("questionId", "2");
        eventProducer.fireEvent(eventModel);
        return "i love you";
    }


}
