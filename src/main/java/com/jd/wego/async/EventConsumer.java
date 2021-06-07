package com.jd.wego.async;

import com.alibaba.fastjson.JSON;
import com.jd.wego.redis.CommonKey;
import com.jd.wego.redis.JedisService;
import com.jd.wego.redis.LikeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author hbquan
 * @date 2021/4/14 15:54
 * 这是一个消费者，监听消息队列,如果消息队列中存在事件，则会立即消费
 */
@Service
public class EventConsumer implements InitializingBean, ApplicationContextAware {

    @Autowired
    JedisService jedisService;

    private static Logger logger = LoggerFactory.getLogger(EventConsumer.class);
    private ApplicationContext applicationContext;
    /**
     * 通过map来实现简单的消息分发 事件类型 事件实现类
     */
    private Map<EventType, List<EventHandler>> config = new HashMap<>();


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 在包含BeanFactory的所有bean属性都已设置并满足BeanFactoryAware，
     * ApplicationContextAware等之后，调用此方法。
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // 首先从应用上下文中找出那些实现了EventHandler的接口
        Map<String, EventHandler> beans = applicationContext.getBeansOfType(EventHandler.class);
        logger.info("实现了EventHandler接口的有:{} ", beans);
        if (beans != null) {
            for (Map.Entry<String, EventHandler> entry : beans.entrySet()) {
                // 获取和该事件相关的所有类型
                List<EventType> eventHandlers = entry.getValue().getSupportEventTypes();
                for (EventType type : eventHandlers) {
                    // 从所有的类型中寻找符合条件的type，如果符合条件则将其加入到map中
                    if (!config.containsKey(type)) {
                        config.put(type, new ArrayList<>());
                    }
                    config.get(type).add(entry.getValue());
                }
            }
        }
        // 将需要处理的类型和那个类来处理都装进map了，另启动一个线程来监听该行为
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    List<String> strs = jedisService.brpop(LikeKey.LIKE_ASYNC_KEY, CommonKey.EVENT_LIKE_QUEUE);
                    logger.info("即将处理的EventModel为{}", strs);
                    for (String str : strs) {
                        //遍历的时候 返回的是key,value;所以将所有的key过滤掉
                        if (str.equals(LikeKey.LIKE_ASYNC_KEY.getPrefix() + CommonKey.EVENT_LIKE_QUEUE)) {
                            continue;
                        }
                        // 从消息队列获取到EventModel对象
                        EventModel eventModel = JSON.parseObject(str.replace("'\'", ""), EventModel.class);
                        if (!config.containsKey(eventModel.getEventType())) {
                            logger.info("{}是不能识别的事件类型", eventModel.getEventType());
                            continue;
                        }
                        //能识别事件,然后就是一个个的处理事件
                        for (EventHandler handler : config.get(eventModel.getEventType())) {
                            logger.info("开始处理{}的事件", eventModel.getEventType());
                            handler.doHandler(eventModel);
                            logger.info("处理{}的事件完毕", eventModel.getEventType());
                        }

                    }
                }
            }
        }).start();


    }


}
