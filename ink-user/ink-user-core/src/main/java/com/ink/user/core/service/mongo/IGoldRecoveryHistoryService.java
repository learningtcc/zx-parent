package com.ink.user.core.service.mongo;

import java.util.List;

import com.ink.user.mongo.util.GoldRecoveryHistoryFilter;
import com.ink.user.core.po.mongo.GoldRecoveryHistory;

public interface IGoldRecoveryHistoryService {
	
	public List<GoldRecoveryHistory> getGoldRecoveryHistorys(GoldRecoveryHistoryFilter filter) throws Exception ;
	
	public long getCount(GoldRecoveryHistoryFilter filter);
	
	public void saveGoldRecoveryHistory(String json) throws Exception;

}
