package com.hik.listener;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.TimerTask;

/**
 * 自动监听任务
 *
 *
 */
public class PropertiesTask extends TimerTask {

    private ServletContext context = null;
    /**
     * 配置文件的最后更新时间
     */
    private long lastModified = 0;

    /**
     * 构造一个自动更新任务
     * @param context
     */
    public PropertiesTask(ServletContext context){
        this.context = context;
        System.out.println("A task instance is created now."); // 任务在整个 application 周期内只创建一次。
    }

    /**
     * 每次执行任务时显示一个随机数。
     */
    public void todoTestRandom(){
        System.out.println("Task running");
        context.setAttribute("random", String.valueOf(Math.random()));
        System.out.println((String)context.getAttribute("random"));
    }

    /**
     * 监听配置文件是否被更新。
     */
    public void todoTestFileStatus(){
        System.out.println("Getting file status");
        System.out.println(this.isFileUpdated("/jdbc-druid-config.properties"));
    }

    /**
     * 监听配置文件是否被更新，自动更新文件中的配置项存储到 application 变量中。
     */
    public void todo(){
        String filename = "/jdbc-druid-config.properties";
        if(this.isFileUpdated(filename)){

            System.out.println("Getting properties");
            try{
                this.loadProperties("jdbc.url", filename);
            }catch(IOException ioe){
                System.err.println(ioe.getMessage());
            }
        }
        System.out.println("Test value is: " + this.getTestProperty("jdbc.url"));
    }

    public void run() {
        todoTestRandom();
        todo();
        //todo();
    }

    /**
     * 判断物理文件是否已被更新
     * @param filename 物理文件名
     * @return 是 true 否 false
     */
    private boolean isFileUpdated(String filename){
        File file = new File(context.getRealPath(filename));
        if(file.isFile()){
            long lastUpdateTime = file.lastModified();
            if(lastUpdateTime > this.lastModified){
                System.out.println("The properties file was modified.");
                this.lastModified = lastUpdateTime;
                return true;
            }else{
                System.out.println("The properties file was not modified.");
                return false;
            }
        }else{
            System.out.println("The path does not point to a file.");
            return false;
        }
    }

    /**
     * 获取配置文件
     * @param key
     * @param filename
     * @return
     */
    public void loadProperties(String key, String filename) throws IOException {
        Properties prop = new Properties();
        InputStream stream = context.getResourceAsStream(filename);
        prop.load(stream);
        if(stream!=null){
            stream.close();
        }
        context.setAttribute(key, prop);
    }

    /**
     * 从 application 取配置项的值
     * @param key 配置项的键名
     * @return 配置项的值
     */
    public String getTestProperty(String key){
        Properties prop = (Properties)context.getAttribute(key);
        if(prop==null){
            return null;
        }else{
            return (String)prop.get(key);
        }
    }

}