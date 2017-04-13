package com.wechat.servlet;

import com.wechat.message.TextMessage;
import com.wechat.factory.AutoReplyFactory;
import com.wechat.util.MessageUtil;
import com.wechat.util.WeChatUtil;
import org.dom4j.DocumentException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

/**
 * Created by wang_ on 2016-08-21.
 */
@WebServlet(name = "SignServlet",
        urlPatterns = {"/SignServlet"})
public class SignServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        PrintWriter out = response.getWriter();
        if (WeChatUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();
        try {
            Map<String, String> map = MessageUtil.xmlToMap(request);
            String toUserName = map.get("ToUserName");
            String fromUserName = map.get("FromUserName");
            String msgType = map.get("MsgType");
            String content = map.get("Content");

            String message = null;
            if (MessageUtil.WECHAT_TEXT.equals(msgType)) {
                TextMessage textMessage = new TextMessage();
                textMessage.setFromUserName(toUserName);
                textMessage.setToUserName(fromUserName);
                textMessage.setMsgType("text");
                textMessage.setCreateTime(new Date().getTime() + "");
                textMessage.setContent(AutoReplyFactory.getContentByKeyWord(content));
                message = MessageUtil.TextMessageToXml(textMessage);
            } else if (MessageUtil.WECHAT_EVENT.equals(msgType)) {
                String event = map.get("Event");
                if (MessageUtil.WECHAT_EVENT_SUBSCRIBE.equals(event)) {
                    TextMessage textMessage = new TextMessage();
                    textMessage.setFromUserName(toUserName);
                    textMessage.setToUserName(fromUserName);
                    textMessage.setMsgType("text");
                    textMessage.setCreateTime(new Date().getTime() + "");
                    textMessage.setContent(AutoReplyFactory.getContentByKeyWord("subscribe"));
                    message = MessageUtil.TextMessageToXml(textMessage);
                } else if (MessageUtil.WECHAT_EVENT_UNSUBSCRIBE.equals(event)) {

                }
            }
            out.print(message);
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }

    }
}
