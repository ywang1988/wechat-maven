package com.wechat.button;

import com.wechat.base.ChildButton;

/**
 * 二级菜单(click类型)
 * Created by wang_ on 2016-08-22.
 */
public class ClickButton extends ChildButton {
    private String key; // key值

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
