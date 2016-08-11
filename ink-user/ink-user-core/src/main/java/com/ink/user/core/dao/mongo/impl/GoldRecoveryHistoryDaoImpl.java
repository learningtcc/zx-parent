package com.ink.user.core.dao.mongo.impl;

import java.util.List;

import com.ink.user.core.dao.mongo.GoldRecoveryHistoryMapper;
import com.ink.user.core.po.mongo.GoldRecoveryHistory;
import com.ink.user.mongo.util.GoldRecoveryHistoryFilter;
import com.ink.user.mongo.util.MongoHandleKeyConvertUtil;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.json.JSON;


@Service("goldRecoveryHistoryDao")
public class GoldRecoveryHistoryDaoImpl extends BasicDAO<GoldRecoveryHistory, ObjectId> implements GoldRecoveryHistoryMapper {

	@Autowired(required=true)
	public GoldRecoveryHistoryDaoImpl(@Qualifier(value="datastore")  Datastore ds) {
		super(ds);
	}

	@Override
	public void saveGoldRecoveryHistory(String json) throws Exception {
		GoldRecoveryHistory goldRecoveryHistory = JSON.parse(json,GoldRecoveryHistory.class);
		save(goldRecoveryHistory);
	}

	@Override
	public List<GoldRecoveryHistory> getGoldRecoveryHistorys(GoldRecoveryHistoryFilter filter) throws Exception {
		Query<GoldRecoveryHistory> query = null;
		query = createQuery();
		MongoHandleKeyConvertUtil.queryKeyConvert(filter, query);
		filter.pagingHandle( query);
		return query.asList();
	}

	@Override
	public long getCount(GoldRecoveryHistoryFilter filter) {
		Query<GoldRecoveryHistory> query = null;
		query = createQuery();
		MongoHandleKeyConvertUtil.queryKeyConvert(filter, query);
		return count(query);
	}

}
