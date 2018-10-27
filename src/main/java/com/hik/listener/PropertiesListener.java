package com.hik.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 自动监听器
 *
 *
 */
public class PropertiesListener implements ServletContextListener {

    /**
     * 自动监听时钟
     */
    private PropertiesTimer rt = null;

    public void contextInitialized(ServletContextEvent event) {
        String status = "Properties listener start .";
        event.getServletContext().log(status);
        System.out.println(status);

        // 激活自动监听时钟
        rt = new PropertiesTimer(15, event.getServletContext());
        rt.start();
    }

    public void contextDestroyed(ServletContextEvent event) {
        String status = "Properties listener stop .";
        event.getServletContext().log(status);
        System.out.println(status);

        // 停止自动监听时钟
        if (rt != null) {
            rt.stop();
        }
    }
}