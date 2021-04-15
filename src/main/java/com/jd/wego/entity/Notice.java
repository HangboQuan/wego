package com.jd.wego.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author hbquan
 * @date 2021/4/14 20:48
 */
@Data
public class Notice {

    private int id;

    /**
     * 发送方ID
     */
    private String fromId;

    /**
     * 接收方ID
     */
    private String toId;

    private String content;

    private Date createdDate;

    private int hasRead;

    /**
     * 这次对话的ID
     */
    private String conversationId;
}
