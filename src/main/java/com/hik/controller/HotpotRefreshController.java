package com.hik.controller;

import com.hik.Thread.SysProperties;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@RequestMapping("/hotpotrefresh")
public class HotpotRefreshController {

    /**
     * 重新加载配置文件
     */

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public void refresh(HttpServletRequest request){
        ExecutorService pool = Executors.newSingleThreadExecutor();
        pool.execute(new Runnable() {
            public void run() {
                XmlWebApplicationContext context =
                        (XmlWebApplicationContext) WebApplicationContextUtils
                                .getWebApplicationContext(request.getServletContext());
                context.refresh();
            }
        });

        pool.shutdown();

    }

    @RequestMapping(value = "/property", method = RequestMethod.GET)
    public void refreshprop(){
        SysProperties sysProperties= SysProperties.getInstance();
    }

}
