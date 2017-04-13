package com.wechat.message;

import com.wechat.base.BaseMessage;

/**
 * Created by wang_ on 2016-08-22.
 */
public class LinkMessage extends BaseMessage {
    private String title;
    private String description;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "LinkMessage{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                "} " + super.toString();
    }
}
