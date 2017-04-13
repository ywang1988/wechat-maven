package com.wechat.listener;

import com.wechat.factory.AutoReplyFactory;
import com.wechat.factory.MenuFactory;
import com.wechat.util.SystemConfig;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by wang_ on 2016-08-04.
 */
@WebListener
public class StartUpListener implements ServletContextListener {

    public StartUpListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        /**
         * 初始化配置文件
         */
        SystemConfig.init();

        /**
         * 信息回复初始化
         */
        AutoReplyFactory.init();

        /**
         * 菜单初始化
         */
        MenuFactory.init();

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
