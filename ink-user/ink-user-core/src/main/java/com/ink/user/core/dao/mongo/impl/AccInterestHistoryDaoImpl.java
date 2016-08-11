package com.ink.user.core.dao.mongo.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.json.JSON;
import com.ink.user.core.dao.mongo.AccInterestHistoryMapper;
import com.ink.user.core.po.mongo.AccInterestHistory;
import com.ink.user.mongo.util.AccInterestHistoryFilter;
import com.ink.user.mongo.util.MongoHandleKeyConvertUtil;

@Service("accInterestHistoryDao")
public class AccInterestHistoryDaoImpl extends BasicDAO<AccInterestHistory, ObjectId>
		implements AccInterestHistoryMapper {

	@Autowired(required = true)
	public AccInterestHistoryDaoImpl(@Qualifier(value = "datastore") Datastore ds) {
		super(ds);
	}

	@Override
	public void saveAccInterestHistory(String json) throws Exception {
		AccInterestHistory accInterestHistory = JSON.parse(json, AccInterestHistory.class);
		save(accInterestHistory);
	}

	@Override
	public List<AccInterestHistory> getAccInterestHistorys(AccInterestHistoryFilter filter) throws Exception {
		Query<AccInterestHistory> query = null;
		query = createQuery();
		MongoHandleKeyConvertUtil.queryKeyConvert(filter, query);
		filter.pagingHandle(query);
		return query.asList();
	}

	@Override
	public long getCount(AccInterestHistoryFilter filter) {
		Query<AccInterestHistory> query = null;
		query = createQuery();
		MongoHandleKeyConvertUtil.queryKeyConvert(filter, query);
		return count(query);
	}

}
