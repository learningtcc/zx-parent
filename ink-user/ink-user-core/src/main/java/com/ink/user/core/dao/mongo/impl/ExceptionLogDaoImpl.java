//package com.ink.user.core.dao.mongo.impl;
//
//import java.util.List;
//
//import org.bson.types.ObjectId;
//import org.mongodb.morphia.Datastore;
//import org.mongodb.morphia.dao.BasicDAO;
//import org.mongodb.morphia.query.Query;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Repository;
//
//import com.ink.pats.atp.comply.api.dao.exception.ExceptionLogMapper;
//import com.ink.pats.atp.comply.mongo.util.MongoDBPagingHandle;
//import com.ink.pats.atp.comply.mongo.util.MongoHandleKeyConvertUtil;
//import com.ink.pats.atp.model.mongo.ExceptionLog;
//import com.ink.pats.atp.model.mongo.filter.ExceptionLogFilter;
//
//@Repository("exceptionLogDao")
//public class ExceptionLogDaoImpl extends BasicDAO<ExceptionLog, ObjectId>implements ExceptionLogMapper{
//
//	@Autowired(required=true)
//	protected ExceptionLogDaoImpl(@Qualifier(value="datastore")  Datastore ds) {
//		super(ds);
//	}
//
//	@Override
//	public void saveExceptionLog(ExceptionLog exceptionLog) {
//		save(exceptionLog);
//	}
//
//	@Override
//	public List<ExceptionLog> getExceptionLog(ExceptionLogFilter filter) throws Exception{
//		Query<ExceptionLog> query = null;
//		query = createQuery();
//		MongoHandleKeyConvertUtil.queryKeyConvert(filter, query);
//		MongoDBPagingHandle.pagingHandle( query, filter);
//		return query.asList();
//	}
//
//}
//
