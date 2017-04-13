package com.wechat.button;

import com.wechat.base.ChildButton;

/**
 * 二级菜单(view类型)
 * Created by wang_ on 2016-08-22.
 */
public class ViewButton extends ChildButton {
    private String url; // view路径值

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
