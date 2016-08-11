//package com.ink.user.core.dao.mongo.impl;
//
//import org.bson.types.ObjectId;
//import org.mongodb.morphia.Datastore;
//import org.mongodb.morphia.dao.BasicDAO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//import com.alibaba.dubbo.common.json.JSON;
//import com.ink.user.core.dao.mongo.AccBatchLogMapper;
//import AccBatchLog;
//
//@Service("accBatchLogDao")
//public class AccBatchLogDaoImpl extends BasicDAO<AccBatchLog, ObjectId> implements AccBatchLogMapper{
//	
//	@Autowired(required=true)
//	public AccBatchLogDaoImpl(@Qualifier(value="datastore")  Datastore ds) {
//		super(ds);
//	}
//
//	@Override
//	public void saveAccBatchLog(String json) throws Exception{
//		AccBatchLog accBatchLog = JSON.parse(json, AccBatchLog.class);
//		save(accBatchLog);
//	}
//}
