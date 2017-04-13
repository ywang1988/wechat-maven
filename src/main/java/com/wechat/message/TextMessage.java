package com.wechat.message;

import com.wechat.base.BaseMessage;

/**
 * Created by wang_ on 2016-08-21.
 */
public class TextMessage extends BaseMessage {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        content = content;
    }

    @Override
    public String toString() {
        return "TextMessage{" +
                "content='" + content + '\'' +
                "} " + super.toString();
    }
}
