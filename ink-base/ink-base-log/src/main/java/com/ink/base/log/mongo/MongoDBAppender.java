package com.ink.base.log.mongo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.MDC;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.ink.base.log.util.LogConst;

import ch.qos.logback.classic.spi.ILoggingEvent;

@Service("mongoDBAppender")
public class MongoDBAppender extends MongoDBAppenderBase<ILoggingEvent> {
   private  DateFormat df =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
    @Override
    protected BasicDBObject toMongoDocument(ILoggingEvent eventObject) {
       final BasicDBObject doc = new BasicDBObject();
     //  final  org.bson.Document  doc=new org.bson.Document();
         if (StringUtils.isNotBlank(source)){//来源
           doc.append("source", source);
         }else{
           doc.append("source", MDC.get(LogConst.REQ_SOURCE));
         }
        doc.append("logLevel", eventObject.getLevel().toString());                         //           级别
        doc.append("logTime", df.format(new Date()));                                       //        时间
        doc.append("logSeq",eventObject.getMDCPropertyMap().get(LogConst.LOG_SEQ));        //        日志序列
        doc.append("reqContext",eventObject.getMDCPropertyMap().get(LogConst.REQ_CONTEXT));//        请求工程
        doc.append("resContext",eventObject.getMDCPropertyMap().get(LogConst.CONTEXT));    //        响应工程
        doc.append("userId",eventObject.getMDCPropertyMap().get(LogConst.USER_ID));        //        用户ID
        doc.append("userName",eventObject.getMDCPropertyMap().get(LogConst.USER_NAME));    //        用户账号
        doc.append("reqIp",eventObject.getMDCPropertyMap().get(LogConst.REQ_REMOTEADDR));  //        用户IP
        doc.append("serverIp",eventObject.getMDCPropertyMap().get(LogConst.REQ_SERVERIP)); //        服务IP
        doc.append("requestId",eventObject.getMDCPropertyMap().get(LogConst.REQ_ID));      //        请求流水
        doc.append("requestUrl",eventObject.getMDCPropertyMap().get(LogConst.REQ_URI));    //        请求URL
        doc.append("sessionId",eventObject.getMDCPropertyMap().get(LogConst.SESSION_ID));  //        SESSIONID
        doc.append("module",eventObject.getMDCPropertyMap().get("op.module"));                    // 模块CODE
        doc.append("infoId",eventObject.getMDCPropertyMap().get("op.infoId"));
        doc.append("tradeId",eventObject.getMDCPropertyMap().get("tread.id"));  // 业务CODE
        doc.append("threadName", eventObject.getThreadName());
        doc.append("logger", eventObject.getLoggerName());
        doc.append("message", eventObject.getFormattedMessage());
        return doc;
    }
}
