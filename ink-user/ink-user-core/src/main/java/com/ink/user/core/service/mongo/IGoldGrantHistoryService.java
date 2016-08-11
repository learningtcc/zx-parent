package com.ink.user.core.service.mongo;

import java.util.List;

import com.ink.user.core.po.mongo.GoldGrantHistory;
import com.ink.user.mongo.util.GoldGrantHistoryFilter;

public interface IGoldGrantHistoryService {

	public List<GoldGrantHistory> getGoldGrantHistorys(GoldGrantHistoryFilter filter) throws Exception ;
	
	public long getCount(GoldGrantHistoryFilter filter);
	
	public void saveGoldGrantHistory(String json) throws Exception;
}
