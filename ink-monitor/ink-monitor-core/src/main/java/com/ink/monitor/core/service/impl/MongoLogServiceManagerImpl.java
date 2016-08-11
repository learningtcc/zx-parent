/**
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.service.impl;

import com.ink.base.page.Page;
import com.ink.monitor.core.po.MongoLog;
import com.ink.monitor.core.query.MongoLogQuery;
import com.ink.monitor.core.service.IMongoLogServiceManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 从mongoDb中获取日志数据
 * Created by aiyungui on 2016/4/28.
 */
@Service("mongoLogServiceManager")
public class MongoLogServiceManagerImpl implements IMongoLogServiceManager{

    protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public Page<MongoLog> findSystemLogInfo(MongoLogQuery logQuery) throws Exception {

        try{
            if (logQuery ==null || StringUtils.isBlank(logQuery.getSource())){
                return new Page(logQuery,0);
            }
            String collectionName = "all_log_" + logQuery.getSource();
            if (!mongoTemplate.collectionExists(collectionName)){
                return new Page(logQuery,0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return find(logQuery);

    }

    @Override
    public MongoLog getById(String source,String _id) throws Exception {
        String collectionName = "all_log_" + source;
        if (!mongoTemplate.collectionExists(collectionName)){
            return null;
        }
        return mongoTemplate.findById(_id, MongoLog.class,collectionName);
    }


    /**
     * 查询mongo日志信息
     * @param logQuery 日志查询条件
     * @return
     * @throws Exception
     */
    private Page<MongoLog> find(MongoLogQuery logQuery)throws Exception{
        String collectionName = "all_log_" + logQuery.getSource();
        int offset = logQuery.getPageSize() * (logQuery.getPageNumber() - 1);
        int pageSize = logQuery.getPageSize();
        Query query = changeQueryVar(logQuery);
        int count = (int) mongoTemplate.count(query,  collectionName);
        query.skip(offset);
        query.limit(pageSize);
        List<MongoLog> list = new ArrayList<MongoLog>();
        if (count>0) list= mongoTemplate.find(query, MongoLog.class,  collectionName);
        Page page= new Page(logQuery,count);
        page.setTotalCount(count);
        page.setResult(list);

        return page;
    }

    /**
     * 转换查询条件
     * @param logQuery
     * @return
     */
    private Query changeQueryVar(MongoLogQuery logQuery){

        Query query = new Query();
        Criteria criteria = null;
        if (StringUtils.isNotEmpty(logQuery.getStartLogTime())){//日志时间
            criteria = Criteria.where("logTime").gte(logQuery.getStartLogTime());
        }
        if (StringUtils.isNotEmpty(logQuery.getEndLogTime())){
            if (criteria == null){
                criteria = Criteria.where("logTime").lte(logQuery.getEndLogTime());
            }else{
                criteria.lte(logQuery.getEndLogTime());
            }
        }
        if (criteria != null){
            query.addCriteria(criteria);
        }
        if (StringUtils.isNotEmpty(logQuery.getLogLevel())){//请求系统
            query.addCriteria(Criteria.where("logLevel").is(logQuery.getLogLevel()));
        }
        if (StringUtils.isNotEmpty(logQuery.getReqContext())) {//请求系统
            query.addCriteria(Criteria.where("reqContext").is(logQuery.getReqContext()));
        }
        if (StringUtils.isNotEmpty(logQuery.getResContext())){//响应系统
            query.addCriteria(Criteria.where("resContext").is(logQuery.getResContext()));
        }
        if (StringUtils.isNotEmpty(logQuery.getLogSeq())){//交易流水
            query.addCriteria(Criteria.where("logSeq").is(logQuery.getLogSeq()));
        }
        if (StringUtils.isNotEmpty(logQuery.getUserName())){//用户账号
            query.addCriteria(Criteria.where("userName").is(logQuery.getUserName()));
        }
        if (StringUtils.isNotEmpty(logQuery.getReqIp())) {//用户IP
            query.addCriteria(Criteria.where("reqIp").is(logQuery.getReqIp()));
        }
        if (StringUtils.isNotEmpty(logQuery.getServerIp())){//服务IP
            query.addCriteria(Criteria.where("serverIp").is(logQuery.getServerIp()));
        }
        if (StringUtils.isNotEmpty(logQuery.getRequestId())) {//请求流水
            query.addCriteria(Criteria.where("requestId").is(logQuery.getRequestId()));
        }
        if (StringUtils.isNotEmpty(logQuery.getModule())) {//功能代码
            query.addCriteria(Criteria.where("module").is(logQuery.getModule()));
        }
        if (StringUtils.isNotEmpty(logQuery.getInfoId())){//业务代码
            query.addCriteria(Criteria.where("infoId").is(logQuery.getInfoId()));
        }
        if (StringUtils.isNotEmpty(logQuery.getRequestUrl())){//请求URL
            query.addCriteria(Criteria.where("requestUrl").is(logQuery.getRequestUrl()));
        }
        if (StringUtils.isNotEmpty(logQuery.getMessage())){//日志消息
            query.addCriteria(Criteria.where("message").regex(".*?" + logQuery.getMessage() + ".*"));
        }

        query.with(new Sort(Sort.Direction.DESC,"logTime"));

        return query;
    }


}
