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
//import com.ink.user.core.dao.mongo.TnsActivityLogMapper;
//import TnsActivityLog;
//
//@Service("tnsActivityLogDao")
//public class TnsActivityLogDaoImpl extends BasicDAO<TnsActivityLog, ObjectId>
//		implements TnsActivityLogMapper {
//	@Autowired(required = true)
//	protected TnsActivityLogDaoImpl(@Qualifier(value = "datastore") Datastore ds) {
//		super(ds);
//
//	}
//
//	@Override
//	public void saveTnsActivityLog(String json) throws Exception{
//		TnsActivityLog tnsActivityLog = JSON.parse(json, TnsActivityLog.class);
//		save(tnsActivityLog);
//	}
//
//}
