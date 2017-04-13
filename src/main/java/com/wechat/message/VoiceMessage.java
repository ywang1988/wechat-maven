package com.wechat.message;

import com.wechat.base.BaseMessage;

/**
 * Created by wang_ on 2016-08-22.
 */
public class VoiceMessage extends BaseMessage {
    private String mediaId;
    private String format;
    private String recognition;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

    @Override
    public String toString() {
        return "VoiceMessage{" +
                "mediaId='" + mediaId + '\'' +
                ", format='" + format + '\'' +
                ", recognition='" + recognition + '\'' +
                "} " + super.toString();
    }
}
