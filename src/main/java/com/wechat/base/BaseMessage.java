package com.wechat.base;

import java.io.Serializable;

/**
 * Created by wang_ on 2016-08-22.
 */
public class BaseMessage implements Serializable {
    private String toUserName;
    private String fromUserName;
    private String createTime;
    private String msgType;
    private String msgId;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        fromUserName = fromUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        msgType = msgType;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        msgId = msgId;
    }

    @Override
    public String toString() {
        return "BaseMessage{" +
                "ToUserName='" + toUserName + '\'' +
                ", FromUserName='" + fromUserName + '\'' +
                ", CreateTime='" + createTime + '\'' +
                ", MsgType='" + msgType + '\'' +
                ", MsgId='" + msgId + '\'' +
                '}';
    }
}
