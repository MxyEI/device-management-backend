package com.hik.listener;

import javax.servlet.ServletContext;
import java.util.Date;
import java.util.Timer;

/**
 * 自动监听时钟
 *
 *
 */
public class PropertiesTimer {

    private final Timer timer = new Timer();
    private final int sec;
    private ServletContext context = null;

    public PropertiesTimer(int seconds, ServletContext context) {
        sec = seconds;
        this.context = context;
    }

    /**
     * 启动自动监听任务
     */
    public void start() {

        // 取得当前日期时间
        Date date = new Date();

        // 执行自动监听计划
        timer.schedule(new PropertiesTask(this.context), date, sec * 1000);
    }

    /**
     * 停止自动监听任务
     */
    public void stop() {
        timer.cancel();

    }

}