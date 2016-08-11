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
//import com.ink.user.core.dao.mongo.TnsMsgLogMapper;
//import TnsMsgLog;
//
//@Service("tnsMsgLogDao")
//public class TnsMsgLogDaoImpl  extends BasicDAO<TnsMsgLog, ObjectId> implements TnsMsgLogMapper{
//	
//	@Autowired(required=true)
//	protected TnsMsgLogDaoImpl( @Qualifier(value="datastore")  Datastore ds) {
//		super(ds);
//		
//	}
//
//	@Override
//	public void saveTnsMsgLog(String json) throws Exception{
//		TnsMsgLog tnsMsgLog = JSON.parse(json, TnsMsgLog.class);
//		save(tnsMsgLog);
//	}
//}
