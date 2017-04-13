package com.wechat.menu;

import com.wechat.button.ParentButton;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wang_ on 2016-08-22.
 */
public class Menu implements Serializable{
    private List<ParentButton> buttons;

    public List<ParentButton> getButtons() {
        return buttons;
    }

    public void setButtons(List<ParentButton> buttons) {
        this.buttons = buttons;
    }

}
