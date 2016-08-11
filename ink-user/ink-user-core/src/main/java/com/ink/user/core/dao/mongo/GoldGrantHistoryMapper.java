package com.ink.user.core.dao.mongo;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;

import com.ink.user.core.po.mongo.GoldGrantHistory;
import com.ink.user.mongo.util.GoldGrantHistoryFilter;

public interface GoldGrantHistoryMapper  extends DAO<GoldGrantHistory, ObjectId>{

	
	void saveGoldGrantHistory(String json) throws Exception;
	
	public List<GoldGrantHistory> getGoldGrantHistorys(GoldGrantHistoryFilter filter) throws Exception;
	
	public long getCount(GoldGrantHistoryFilter filter);
}
