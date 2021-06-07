package com.jd.wego.entity;

import lombok.Data;

/**
 * @author hbquan
 * @date 2021/5/20 16:54
 */
@Data
public class Message {

    int messageId;

    String fromId;

    String toId;

    String messageContent;

    int hasRead;

    String conversationId;


}
