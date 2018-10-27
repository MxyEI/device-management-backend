package com.hik.Thread;

import java.io.File;

import java.util.Properties;

import org.apache.log4j.Logger;


/**
 * 读取实时的配置文件信息
 *
 */
public class SysProperties {

    private static Logger log = Logger.getLogger(SysProperties.class);

    private static Properties SysLocalPropObject = null;

    //配置文件路径
    private static String defaultPropFileName = "/jdbc-druid-config.properties";
    //文件更新标识
    protected long lastModifiedData = -1;

    private static SysProperties instance;

    public static SysProperties getInstance(){
        if(instance == null){
            instance = new SysProperties();
        }
        return instance;
    }


    /**
     * @description 私有构造器启动一个线程实时监控配置文件
     */
    private SysProperties() {
        SysLocalPropObject = new Properties();
        String tempPath = this.getClass().getResource(defaultPropFileName).getFile();
        File tempFile = new File(tempPath);
        final String filePath;
        if(tempFile.exists()) {
            filePath = tempPath;
        } else {
            filePath = "/jdbc-druid-config.properties";
        }

        final SysProperties self = this;
        File propertyFile = new File(filePath);


        //循环监控配置文件的变化，一旦发现文件发生变化了则重读配置文件
        Thread t = new Thread() {
            public void run() {
                while (true) {
                    //间隔1秒
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                    }

                    try {
                        File propertyFile = new File(filePath);
                        if (self.lastModifiedData != propertyFile.lastModified()) {
                            log.info("配置文件更新了");
                        }
                    } catch (Exception e) {

                    }
                }
            }
        };
        t.start();
    }


}

