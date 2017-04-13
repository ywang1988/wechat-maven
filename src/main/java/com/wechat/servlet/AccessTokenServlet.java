package com.wechat.servlet;

import com.wechat.thread.TokenThread;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * Created by wang_ on 2016-08-22.
 */
@WebServlet(name = "AccessTokenServlet",
        urlPatterns = {"/AccessTokenServlet"},
        loadOnStartup = 1)
public class AccessTokenServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void init(ServletConfig config) throws ServletException {
        //启动定时获取access_token的线程
        new Thread(new TokenThread()).start();
    }

}
