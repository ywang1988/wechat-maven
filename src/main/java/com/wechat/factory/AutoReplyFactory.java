package com.wechat.factory;

import com.wechat.util.SystemConfig;
import com.wechat.util.XmlUtil;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wang_ on 2016-08-22.
 */
public class AutoReplyFactory {
    private static Logger logger = Logger.getLogger(AutoReplyFactory.class);
    private static String fileSource;
    private static AutoReplyFactory instance;
    private static Map<String, String> replys = new HashMap<>();

    public AutoReplyFactory() {
        this.build();
    }

    public static AutoReplyFactory getInstance() {
        if (instance == null) {
            instance = new AutoReplyFactory();
        }
        return instance;
    }

    public static void init() {
        init(SystemConfig.AUTOREPLY_ROOT);
    }

    public static void init(String file) {
        fileSource = file;
        getInstance();
    }

    public void build() {
        Document doc = null;
        try {
            logger.info("--------The autoReply begin init---------");
            doc = XmlUtil.getDocument(fileSource);
            Element root = doc.getRootElement();
            List<Element> wx_autoreplys = root.elements("wx-autoreply");
            if (wx_autoreplys != null) {
                for (Element wx_autoreply : wx_autoreplys) {
                    String type = wx_autoreply.attributeValue("type");
                    List<Element> autoreplys = wx_autoreply.elements();
                    if (autoreplys != null) {
                        loadReplyFormXml(type, autoreplys);
                    }
                }
            }
            logger.info("--------The autoReply init success---------");
        } catch (Exception e) {
            logger.error("--------The autoReply init failure:" +e.getMessage()+ "---------");
            e.printStackTrace();
        }
    }

    /**
     *
     * @param type
     * @param autoreplys
     */
    private void loadReplyFormXml(String type, List<Element> autoreplys) {
        for (Element autoreply : autoreplys) {
            Element rule = autoreply.element("rule");
            Element content = autoreply.element("content");
            String[] rules = rule.getTextTrim().split(",");
            for (String _rule : rules) {
                if ("other".equals(type) || "subscribe".equals(type)) _rule = type;
                replys.put(_rule, content.getTextTrim());
            }
        }
    }

    /**
     *
     * @param key
     * @return
     */
    public static String getContentByKeyWord(String key) {
        if (key == null) return "";
        key = key.trim();
        String content = replys.containsKey(key) ? replys.get(key) : replys.get("other");
        String[] reply = content.split(";");
        StringBuffer sb = new StringBuffer();
        for (String r : reply) {
            sb.append(r).append("\n");
        }
        return sb.toString();
    }

}
