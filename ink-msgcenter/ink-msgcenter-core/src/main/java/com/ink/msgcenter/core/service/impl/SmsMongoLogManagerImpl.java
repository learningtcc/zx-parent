/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.msgcenter.core.service.impl;

import com.ink.base.page.Page;
import com.ink.msgcenter.core.po.SmsMongoLog;
import com.ink.msgcenter.core.query.SmsMongoLogQuery;
import com.ink.msgcenter.core.service.ISmsMongoLogManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaojie
 * @version 1.0
 * @since 1.0
 */
 
@Service("smsMongoLogManager")
@Transactional
public class SmsMongoLogManagerImpl  implements ISmsMongoLogManager{

	
	@Autowired
	MongoTemplate mongoTemplate;
	@Override
	public Page<SmsMongoLog> findSmsMongoLogInfo(SmsMongoLogQuery logQuery)
			throws Exception {
		String collectionName = "sms_log_" + logQuery.getMerctId();
        if (!mongoTemplate.collectionExists(collectionName)){
            return new Page(logQuery,0);
        }

        return find(logQuery);
	}
	@Override
    public SmsMongoLog getById(String MerctCode,String _id) throws Exception {
        String collectionName = "sms_log_" + MerctCode;
        if (!mongoTemplate.collectionExists(collectionName)){
            return null;
        }
        return mongoTemplate.findById(_id, SmsMongoLog.class,collectionName);
    }

    private Page<SmsMongoLog> find(SmsMongoLogQuery logQuery)throws Exception{
        String collectionName = "sms_log_" + logQuery.getMerctId();
        int offset = logQuery.getPageSize() * (logQuery.getPageNumber() - 1);
        int pageSize = logQuery.getPageSize();
        Query query = changeQueryVar(logQuery);
        int count = (int) mongoTemplate.count(query,  collectionName);
        query.skip(offset);
        query.limit(pageSize);
        List<SmsMongoLog> list = new ArrayList<SmsMongoLog>();
        if (count>0) list= mongoTemplate.find(query, SmsMongoLog.class,  collectionName);
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
    private Query changeQueryVar(SmsMongoLogQuery logQuery){

        Query query = new Query();
        if (StringUtils.isNotEmpty(logQuery.getId())){//ID
            query.addCriteria(Criteria.where("id").is(logQuery.getId()));
        }
        if (StringUtils.isNotEmpty(logQuery.getChnCode())){//通道代码
            query.addCriteria(Criteria.where("chnCode").is(logQuery.getChnCode()));
        }
        if (logQuery.getChnId()!=null){//通道ID
            query.addCriteria(Criteria.where("chnId").is(logQuery.getChnId()));
        }
        if (StringUtils.isNotEmpty(logQuery.getInfoCode())) {//业务单号
            query.addCriteria(Criteria.where("infoCode").is(logQuery.getInfoCode()));
        }
        if (StringUtils.isNotEmpty(logQuery.getMobile())){//手机号
            query.addCriteria(Criteria.where("mobile").is(logQuery.getMobile()));
        }
        if (StringUtils.isNotEmpty(logQuery.getResponseCode())){//响应代码
            query.addCriteria(Criteria.where("responseCode").is(logQuery.getResponseCode()));
        }
        if (StringUtils.isNotEmpty(logQuery.getSendStatus())){//发送状态
            query.addCriteria(Criteria.where("sendStatus").is(logQuery.getSendStatus()));
        }
        if (StringUtils.isNotEmpty(logQuery.getSmsType())) {//短信类型
            query.addCriteria(Criteria.where("smsType").is(logQuery.getSmsType()));
        } 
        if (StringUtils.isNotEmpty(logQuery.getSmsCode())) {//短信代码
            query.addCriteria(Criteria.where("smsCode").is(logQuery.getSmsCode()));
        }
        if (StringUtils.isNotEmpty(logQuery.getReportStatus())) {//状态报告
            query.addCriteria(Criteria.where("reportStatus").is(logQuery.getReportStatus()));
        }
        if (logQuery.getTempId()!=null){//模板ID
            query.addCriteria(Criteria.where("tempId").is(logQuery.getTempId()));
        }

        query.with(new Sort(Sort.Direction.DESC,"submitTime"));

        return query;
    }

}
