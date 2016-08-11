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
import com.ink.user.core.dao.mongo.GoldGrantHistoryMapper;
import com.ink.user.core.po.mongo.GoldGrantHistory;
import com.ink.user.mongo.util.GoldGrantHistoryFilter;
import com.ink.user.mongo.util.MongoHandleKeyConvertUtil;



@Service("goldGrantHistoryDao")
public class GoldGrantHistoryDaoImpl extends BasicDAO<GoldGrantHistory, ObjectId> implements GoldGrantHistoryMapper{

	@Autowired(required=true)
	public GoldGrantHistoryDaoImpl(@Qualifier(value="datastore")  Datastore ds) {
		super(ds);
	}

	@Override
	public void saveGoldGrantHistory(String json) throws Exception{
		GoldGrantHistory goldGrantHistory = JSON.parse(json, GoldGrantHistory.class);
		save(goldGrantHistory);
	}

	@Override
	public List<GoldGrantHistory> getGoldGrantHistorys(GoldGrantHistoryFilter filter) throws Exception {
		Query<GoldGrantHistory> query = null;
		query = createQuery();
		MongoHandleKeyConvertUtil.queryKeyConvert(filter, query);
		filter.pagingHandle( query);
		return query.asList();
	}

	@Override
	public long getCount(GoldGrantHistoryFilter filter) {
		Query<GoldGrantHistory> query = null;
		query = createQuery();
		MongoHandleKeyConvertUtil.queryKeyConvert(filter, query);
		return count(query);
	}

}
