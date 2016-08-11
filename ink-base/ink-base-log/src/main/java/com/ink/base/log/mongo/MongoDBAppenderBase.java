package com.ink.base.log.mongo;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.ink.base.log.util.LogConst;
import com.mongodb.*;
import org.apache.commons.lang.StringUtils;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public abstract class MongoDBAppenderBase<E> extends
        UnsynchronizedAppenderBase<E> {

    private Mongo mongo;
    private DB mongoDb;
    private Map<String,DBCollection> collectionMap ;
    private String host ; //  主机地址
    private int port = 27017;   //端口
    private String dbName ;  //库名
    protected String collectionName;   //表名
    private String username;
    private String password;
    protected String source;    //系统来源
    private int connectionsPerHost = 10;
    private int threadsAllowedToBlockForConnectionMultiplier = 5;
    private int maxWaitTime = 1000 * 60 * 2;
    private int connectTimeout;
    private int socketTimeout;
    private boolean autoConnectRetry;
    private boolean slaveOk;
    private boolean safe;
    private int w;
    private int wtimeout;
    private boolean fsync;
    private Level level = Level.INFO; //日志入库级别列表

    public void setLevel(String logLevel) {
        level = Level.valueOf(logLevel);
    }

    @Override
    public void start() {
        try {
            collectionMap = new HashMap<String,DBCollection>();
            connectToMongoDB();
            super.start();
        } catch (UnknownHostException e) {
            addError( "Error connecting to MongoDB server: " + host + ":" + port,e);
        }
    }

    private void connectToMongoDB() throws UnknownHostException {
        mongo = new Mongo(new ServerAddress(host, port), buildOptions());
        mongoDb = mongo.getDB(dbName);
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password))
            mongoDb.authenticate(username, password.toCharArray());

    }
    private MongoOptions buildOptions() {
        final MongoOptions options = new MongoOptions();
//        options.connectionsPerHost = connectionsPerHost;
//        options.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
//        options.maxWaitTime = maxWaitTime;
//        options.connectTimeout = 30000;
//        options.socketTimeout = socketTimeout;
//        options.autoConnectRetry = autoConnectRetry;
//        options.slaveOk = slaveOk;
//        options.safe = safe;
//        options.w = w;
//        options.wtimeout = wtimeout;
//        options.fsync = fsync;
        options.setSocketKeepAlive(true); //长连接
        return options;
    }
    protected abstract BasicDBObject toMongoDocument(E event);

    @Override
    protected void append(E eventObject) {
        ILoggingEvent eventObject2=(ILoggingEvent) eventObject;
        //level 入库级别格式   DEBUG,INFO,ERROR   包含的入库，其它的不处理
        if(eventObject2.getLevel().isGreaterOrEqual(level)){
            BasicDBObject bo=  toMongoDocument(eventObject);

            String sysCode = source;
            if (StringUtils.isBlank(sysCode)){
                sysCode = eventObject2.getMDCPropertyMap().get(LogConst.REQ_SOURCE);
            }
            if (StringUtils.isBlank(sysCode)){
                return;
            }
            DBCollection eventsCollection = collectionMap.get(sysCode);
            if (eventsCollection == null){
                eventsCollection = mongoDb.getCollection(collectionName+"_"+sysCode);
                collectionMap.put(sysCode,eventsCollection);
            }

            if(bo!=null)eventsCollection.insert(bo);
        }else{
            return ;
        }
    }
    @Override
    public void stop() {
        if (mongo != null)
            mongo.close();
        super.stop();
    }
    public void setHost(String host) {
        this.host = host;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConnectionsPerHost(int connectionsPerHost) {
        this.connectionsPerHost = connectionsPerHost;
    }

    public void setThreadsAllowedToBlockForConnectionMultiplier(
            int threadsAllowedToBlockForConnectionMultiplier) {
        this.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
    }

    public void setMaxWaitTime(int maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public void setAutoConnectRetry(boolean autoConnectRetry) {
        this.autoConnectRetry = autoConnectRetry;
    }

    public void setSlaveOk(boolean slaveOk) {
        this.slaveOk = slaveOk;
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setWtimeout(int wtimeout) {
        this.wtimeout = wtimeout;
    }

    public void setFsync(boolean fsync) {
        this.fsync = fsync;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


}