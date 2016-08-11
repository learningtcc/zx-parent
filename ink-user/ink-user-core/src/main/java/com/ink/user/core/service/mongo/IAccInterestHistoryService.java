package com.ink.user.core.service.mongo;

import java.util.List;

import com.ink.user.mongo.util.AccInterestHistoryFilter;
import com.ink.user.core.po.mongo.AccInterestHistory;

public interface IAccInterestHistoryService {
	
	public List<AccInterestHistory> getAccInterestHistorys(AccInterestHistoryFilter filter) throws Exception ;

	public long getCount(AccInterestHistoryFilter filter);
	
}
