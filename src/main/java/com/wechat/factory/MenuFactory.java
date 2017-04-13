package com.wechat.factory;

import com.wechat.base.ChildButton;
import com.wechat.button.ClickButton;
import com.wechat.button.ParentButton;
import com.wechat.button.ViewButton;
import com.wechat.menu.Menu;
import com.wechat.util.SystemConfig;
import com.wechat.util.XmlUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wang_ on 2016-08-22.
 */
public class MenuFactory {
    private static Logger logger = Logger.getLogger(MenuFactory.class);
    private static String fileSource;
    private static MenuFactory instance;
    private static Menu menu = new Menu();

    public MenuFactory() {
        this.build();
    }

    public static MenuFactory getInstance() {
        if (instance == null) {
            instance = new MenuFactory();
        }
        return instance;
    }

    public static void init() {
        init(SystemConfig.MENU_ROOT);
    }

    public static void init(String file) {
        fileSource = file;
        getInstance();
    }

    private void build() {
        Document doc = null;
        try {
            logger.info("--------The menu begin init---------");
            doc = XmlUtil.getDocument(fileSource);
            Element root = doc.getRootElement();
            List<Element> complexbuttons = root.elements("complexbutton");
            if (complexbuttons!=null && complexbuttons.size()>0) {
                List<ParentButton> parentButtons = new ArrayList<>();
                for (Element complexbutton : complexbuttons) {
                    ParentButton parentButton = new ParentButton();
                    String name = complexbutton.attributeValue("name");
                    List<Element> buttons = complexbutton.elements();
                    if (buttons!=null && buttons.size()>0) {
                        List<ChildButton> childButtons = this.loadMenuFromXml(buttons);
                        parentButton.setName(name);
                        parentButton.setSub_button(childButtons);
                        parentButtons.add(parentButton);
                    }
                }
                menu.setButtons(parentButtons);
            }
            logger.info("--------The menu init success---------");
        } catch (Exception e) {
            logger.error("--------The menu init failure:" +e.getMessage()+ "---------");
            e.printStackTrace();
        }
    }

    /**
     *
     * @param buttons
     * @return
     */
    private List<ChildButton> loadMenuFromXml(List<Element> buttons) {
        List<ChildButton> childButtons = new ArrayList<>();
        for (Element button : buttons) {
            String type = button.attributeValue("type");
            if ("click".equals(type)) {
                ClickButton clickButton = new ClickButton();
                clickButton.setName(button.elementTextTrim("name"));
                clickButton.setType(type);
                clickButton.setKey(button.elementTextTrim("key"));
                childButtons.add(clickButton);
            } else if ("view".equals(type)) {
                ViewButton viewButton = new ViewButton();
                viewButton.setName(button.elementTextTrim("name"));
                viewButton.setType(type);
                viewButton.setUrl(button.elementTextTrim("url"));
                childButtons.add(viewButton);
            }
        }

        return childButtons;
    }

    public static String getMenu() {
        System.out.println("menu:" + menu);
        return JSONObject.fromObject(menu).toString();
    }
}
