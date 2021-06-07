package com.jd.wego.async;


import java.util.HashMap;
import java.util.Map;

/**
 * @author hbquan
 * @date 2021/4/14 14:51
 * 封装成一个完整的事件模型
 */
public class EventModel {

    /**
     * 事件类型
     */
    private EventType eventType;

    /**
     * 谁来触发该事件
     */
    private String actorId;

    /**
     * entityType和entityId标识了被触发的对象
     */
    private int entityType;

    private int entityId;

    /**
     * 触发事件后对谁产生了影响
     */
    private String entityOwnerId;

    /**
     * 扩展字段
     */
    private Map<String, String> exts = new HashMap<String, String>();

    public EventModel() {

    }

    public EventModel(EventType eventType) {
        this.eventType = eventType;
    }

    public EventType getEventType() {
        return eventType;
    }

    public EventModel setEventType(EventType eventType) {
        this.eventType = eventType;
        return this;
    }

    public String getActorId() {
        return actorId;
    }

    public EventModel setActorId(String actorId) {
        this.actorId = actorId;
        return this;
    }

    public int getEntityType() {
        return entityType;
    }

    public EventModel setEntityType(int entityType) {
        this.entityType = entityType;
        return this;
    }

    public int getEntityId() {
        return entityId;
    }

    public EventModel setEntityId(int entityId) {
        this.entityId = entityId;
        return this;
    }

    public String getEntityOwnerId() {
        return entityOwnerId;
    }

    public EventModel setEntityOwnerId(String entityOwnerId) {
        this.entityOwnerId = entityOwnerId;
        return this;
    }


    public Map<String, String> getExts() {
        return exts;
    }

    public EventModel setExts(Map<String, String> exts) {
        this.exts = exts;
        return this;
    }


    /**
     * 重写扩展字段
     */
    public String getExts(String key) {
        return exts.get(key);
    }

    public EventModel setExts(String key, String value) {
        exts.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        return "EventModel{" +
                "eventType=" + eventType +
                ", actorId='" + actorId + '\'' +
                ", entityType=" + entityType +
                ", entityId=" + entityId +
                ", entityOwnerId='" + entityOwnerId + '\'' +
                ", exts=" + exts +
                '}';
    }
}
