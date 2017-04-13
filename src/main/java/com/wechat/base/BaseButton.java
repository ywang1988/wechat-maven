package com.wechat.base;

import java.io.Serializable;

/**
 * Created by wang_ on 2016-08-22.
 */
public class BaseButton implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
