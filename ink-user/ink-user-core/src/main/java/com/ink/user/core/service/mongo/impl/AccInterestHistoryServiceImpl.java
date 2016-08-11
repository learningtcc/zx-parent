package com.ink.user.core.service.mongo.impl;

import java.util.List;

import com.ink.user.core.dao.mongo.AccInterestHistoryMapper;
import com.ink.user.core.po.mongo.AccInterestHistory;
import com.ink.user.core.service.mongo.IAccInterestHistoryService;
import com.ink.user.mongo.util.AccInterestHistoryFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccInterestHistoryServiceImpl implements IAccInterestHistoryService {

	@Autowired
	private AccInterestHistoryMapper accInterestHistoryDao;
	
	@Override
	public List<AccInterestHistory> getAccInterestHistorys(AccInterestHistoryFilter filter) throws Exception {
		return accInterestHistoryDao.getAccInterestHistorys(filter);
	}

	@Override
	public long getCount(AccInterestHistoryFilter filter) {
		return accInterestHistoryDao.getCount(filter);
	}

}
