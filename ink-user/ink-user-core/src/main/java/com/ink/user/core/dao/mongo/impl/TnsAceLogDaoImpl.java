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
//import com.ink.user.core.dao.mongo.TnsAceLogMapper;
//import TnsAceLog;
//
//@Service("tnsAceLogDao")
//public class TnsAceLogDaoImpl extends BasicDAO<TnsAceLog, ObjectId> implements TnsAceLogMapper{
//
//	@Autowired(required=true)
//	public TnsAceLogDaoImpl(@Qualifier(value="datastore")  Datastore ds) {
//		super(ds);
//	}
//
//	@Override
//	public void saveTnsAceLog(String json) throws Exception{
//		TnsAceLog tnsAceLog = JSON.parse(json, TnsAceLog.class);
//		save(tnsAceLog);
//	}
//}
