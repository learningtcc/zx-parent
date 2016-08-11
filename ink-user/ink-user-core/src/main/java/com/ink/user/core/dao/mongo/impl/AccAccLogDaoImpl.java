package com.ink.user.core.dao.mongo.impl;

import java.util.List;

import com.ink.user.mongo.util.AccAccLogFilter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.json.JSON;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.user.core.dao.mongo.AccAccLogMapper;
import com.ink.user.core.po.mongo.AccAccLog;
import com.ink.user.mongo.util.MongoHandleKeyConvertUtil;

@Service("accAccLogDao")
public class AccAccLogDaoImpl extends BasicDAO<AccAccLog, ObjectId> implements
		AccAccLogMapper {
	
	@Autowired
	private IdCodeGenerator idCodeGenerator;
	
	@Autowired(required = true)
	public AccAccLogDaoImpl(@Qualifier(value = "datastore")Datastore ds) {
		super(ds);
	}

	@Override
	public void saveAccAccLog(String json) throws Exception {
		AccAccLog accAccLog = JSON.parse(json, AccAccLog.class);
		accAccLog.setId(Long.valueOf(idCodeGenerator.getId()));
		save(accAccLog);
	}

	@Override
	public List<AccAccLog> getAccAccLogs(AccAccLogFilter filter) throws Exception {
		Query<AccAccLog> query = null;
		query = createQuery();
		MongoHandleKeyConvertUtil.queryKeyConvert(filter, query);
		filter.pagingHandle(query);
		return query.asList();
	}

	@Override
	public long getCount(AccAccLogFilter filter) {
		Query<AccAccLog> query = null;
		query = createQuery();
		MongoHandleKeyConvertUtil.queryKeyConvert(filter, query);
		return count(query);
	}

	@Override
	public AccAccLog getById(long id) {
		Query<AccAccLog> query = null;
		query = createQuery();
		query = query.field("id").equal(id);
		return query.get();
	}

}
