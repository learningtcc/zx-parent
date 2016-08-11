package com.ink.user.core.dao.mongo;

import java.util.List;

import com.ink.user.core.po.mongo.AccInterestHistory;
import com.ink.user.mongo.util.AccInterestHistoryFilter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;

public interface AccInterestHistoryMapper  extends DAO<AccInterestHistory, ObjectId>{

	
	void saveAccInterestHistory(String json) throws Exception;
	
	public List<AccInterestHistory> getAccInterestHistorys(AccInterestHistoryFilter filter) throws Exception;
	
	public long getCount(AccInterestHistoryFilter filter);
}
