package com.jd.wego.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jd.wego.entity.Message;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * @author hbquan
 * @date 2021/5/20 13:36
 */
@Component
@ServerEndpoint(value = "/chat")
public class ChatEndPoint {

    private Session session;

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
    }

    @OnMessage
    /**
     * 用户之间一对一消息发送
     */
    public void onMessage(String message, Session session){
        try{
            // 将message字符串进行序列化
            Message mess = (Message)JSON.parse(message);
            String fromId = mess.getFromId();
            String toId = mess.getToId();
            String messageContent = mess.getMessageContent();

            session.getBasicRemote().sendText(message);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(){

    }
}
