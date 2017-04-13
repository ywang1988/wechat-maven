package com.wechat.button;

import com.wechat.base.BaseButton;
import com.wechat.base.ChildButton;

import java.util.List;

/**
 * 一级菜单
 * Created by wang_ on 2016-08-22.
 */
public class ParentButton extends BaseButton {
    private List<ChildButton> sub_button;

    public List<ChildButton> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<ChildButton> sub_button) {
        this.sub_button = sub_button;
    }


}
