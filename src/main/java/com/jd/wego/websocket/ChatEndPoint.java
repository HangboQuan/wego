package com.jd.wego.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jd.wego.entity.Message;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hbquan
 * @date 2021/5/20 13:36
 */
@Component
@ServerEndpoint(value = "/chat/{userId}")
public class ChatEndPoint {

    private Session session;

    private static Map<String, ChatEndPoint> onlineUsers = new ConcurrentHashMap<>();

    private static Logger logger = LoggerFactory.getLogger(ChatEndPoint.class);

    private static int onlineCount = 0;

    private String userId = "";


    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;
        // 这里是将其加入到线程安全的Map中
        if (onlineUsers.containsKey(userId)) {
            onlineUsers.remove(userId);
            onlineUsers.put(userId, this);
        } else {
            onlineUsers.put(userId, this);
            onlineUsers.put("18392710807", this);
            logger.info(userId + "成功上线");
            addOnlineCount();
        }
    }

    @OnMessage
    /**
     * 用户之间一对一消息发送,这个message参数是从哪传递过来的？
     */
    public void onMessage(String message, Session session) {
        try {
            // 将message字符串进行反序列化
            System.out.println("message------------" + message);
            System.out.println(message);
            Message messA = JSONObject.parseObject(message, Message.class);
            // 这里相当于是上面请求路径上的userId
            String toId = messA.getToId();
            String messageContent = messA.getMessageContent();
            String fromId = messA.getFromId();
            logger.info(fromId + "向" + toId + "发送消息：" + messageContent);

            // 这里应该重新构建一个新的Message对象
            //Message messB = new Message();
            System.out.println(fromId);
            if (!Strings.isBlank(fromId)) {
                onlineUsers.get(fromId).session.getBasicRemote().sendText(message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose() {
        if (onlineUsers.containsKey(userId)) {
            // 移除该客户端对象
            onlineUsers.remove(userId);
            // 在线用户数减1
            subOnlineCount();
        }
    }

    public static synchronized void addOnlineCount() {
        ChatEndPoint.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        ChatEndPoint.onlineCount--;
    }

    public static synchronized int onlineCount() {
        return onlineCount;
    }
}
