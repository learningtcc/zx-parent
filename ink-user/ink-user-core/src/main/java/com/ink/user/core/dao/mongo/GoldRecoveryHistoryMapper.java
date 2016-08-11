package com.ink.user.core.dao.mongo;

import java.util.List;

import com.ink.user.core.po.mongo.GoldRecoveryHistory;
import com.ink.user.mongo.util.GoldRecoveryHistoryFilter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;

public interface GoldRecoveryHistoryMapper  extends DAO<GoldRecoveryHistory, ObjectId>{

	
	void saveGoldRecoveryHistory(String json) throws Exception;
	
	public List<GoldRecoveryHistory> getGoldRecoveryHistorys(GoldRecoveryHistoryFilter filter) throws Exception;
	
	public long getCount(GoldRecoveryHistoryFilter filter);
}
