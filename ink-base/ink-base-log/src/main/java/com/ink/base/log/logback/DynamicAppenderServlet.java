package com.ink.base.log.logback;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.ink.base.log.mongo.MongoDBAppender;
import com.ink.config.service.support.spring.CustomizedPropertyConfigurer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 动态添加logback appender
 * Created by aiyungui on 2016/7/13.
 */
public class DynamicAppenderServlet extends HttpServlet {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(DynamicAppenderServlet.class);
    private String host;
    private String dbName;
    private String port ;
    private String userName;
    private String password;
    private String source;
    private String collectionName;
    private boolean reLoad = false;
    private MongoDBAppender mongoDBAppender;

    @Override
    public void init() throws ServletException {
        super.init();

        addAppender();
    }

    /**
     * 添加mongo 的appender
     */
    private void addAppender(){
       try{
           //初始化mongo参数
           initMongoParam();
           //当传输的参数为空 则不添加mongoAppender
           if (StringUtils.isBlank(host)|| StringUtils.isBlank(dbName)||StringUtils.isBlank(collectionName)){
               throw(new Exception("mongo日志参数为空，请检查")) ;
           }

           LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
           Logger logger = context.getLogger("ROOT");
           mongoDBAppender = new MongoDBAppender();
           mongoDBAppender.setContext(context);
           mongoDBAppender.setHost(host);
           mongoDBAppender.setPort(Integer.valueOf(port));
           mongoDBAppender.setDbName(dbName);
           mongoDBAppender.setUsername(userName);
           mongoDBAppender.setPassword(password);
           mongoDBAppender.setSource(source);
           mongoDBAppender.setCollectionName(collectionName);
           mongoDBAppender.start();
           logger.addAppender(mongoDBAppender);
           log.info("添加logback的mongoAppender成功");
       }catch (Exception e){
           log.error("添加logback的mongoAppender失败," + toString(),e);
           mongoDBAppender = null;
           try {
               if (!reLoad){
                   Thread.sleep(10000);
                   reLoad = true;
                   addAppender();
               }
           } catch (InterruptedException e1) {
               e1.printStackTrace();
           }
       }
    }

    /**
     * 初始化Mongo参数
     */
    private void initMongoParam() {
        CustomizedPropertyConfigurer configure = (CustomizedPropertyConfigurer) LogApplicationContext.getBean("customizedPropertyConfigurer");
        host = configure.getProperty("logback.mongo.host");
        dbName = configure.getProperty("logback.mongo.dbName");
        port = configure.getProperty("logback.mongo.port");
        userName = configure.getProperty("logback.mongo.userName");
        password = configure.getProperty("logback.mongo.password");
        source = configure.getProperty("logback.mongo.source");
        collectionName = configure.getProperty("logback.mongo.collectionName");

        if (StringUtils.isBlank(port)){
            port = "27017";
        }

        if (StringUtils.isBlank(userName)){
            userName = null;
        }
        if (StringUtils.isBlank(password)){
            password = null;
        }
        if(StringUtils.isBlank(collectionName)){
            collectionName = "all_log";
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        if(mongoDBAppender != null){
            mongoDBAppender.stop();
        }
    }

    @Override
    public String toString() {
        return "DynamicAppenderServlet{" +
                "collectionName='" + collectionName + '\'' +
                ", host='" + host + '\'' +
                ", dbName='" + dbName + '\'' +
                ", port='" + port + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
