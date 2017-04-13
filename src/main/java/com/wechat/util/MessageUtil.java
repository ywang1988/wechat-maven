package com.wechat.util;

import com.thoughtworks.xstream.XStream;
import com.wechat.message.TextMessage;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wang_ on 2016-08-21.
 */
public class MessageUtil {
    public static final String WECHAT_TEXT = "text";
    public static final String WECHAT_IMAGE = "image";
    public static final String WECHAT_VOICE = "voice";
    public static final String WECHAT_SHORTVIDEO = "shortvideo";
    public static final String WECHAT_LOCATION = "location";
    public static final String WECHAT_LINK = "link";
    public static final String WECHAT_EVENT = "event";
    public static final String WECHAT_EVENT_SUBSCRIBE = "subscribe";
    public static final String WECHAT_EVENT_UNSUBSCRIBE = "unsubscribe";
    public static final String WECHAT_EVENT_SCAN = "SCAN";
    public static final String WECHAT_EVENT_LOCATION = "LOCATION";
    public static final String WECHAT_EVENT_VIEW = "VIEW";
    public static final String WECHAT_EVENT_CLICK = "CLICK";

    /**
     *
     * @param request
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Map<String, String> xmlToMap(HttpServletRequest request)
                                        throws IOException, DocumentException {
        Map<String, String> map = new HashMap<>();

        SAXReader reader = new SAXReader();
        InputStream ins = request.getInputStream();
        Document document = reader.read(ins);
        Element root = document.getRootElement();
        List<Element> list = root.elements();
        for (Element e : list) {
            map.put(e.getName(), e.getText());
        }
        ins.close();
        return map;
    }

    /**
     *
     * @param message
     * @return
     */
    public static String TextMessageToXml(TextMessage message) {
        XStream xStream = new XStream();
        xStream.alias("xml", message.getClass());
        return xStream.toXML(message);
    }
}
