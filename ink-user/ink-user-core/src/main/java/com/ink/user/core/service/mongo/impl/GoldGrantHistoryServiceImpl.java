package com.ink.user.core.service.mongo.impl;

import java.util.List;

import com.ink.user.core.dao.mongo.GoldGrantHistoryMapper;
import com.ink.user.core.po.mongo.GoldGrantHistory;
import com.ink.user.core.service.mongo.IGoldGrantHistoryService;
import com.ink.user.mongo.util.GoldGrantHistoryFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoldGrantHistoryServiceImpl implements IGoldGrantHistoryService {

	@Autowired
	private GoldGrantHistoryMapper goldGrantHistoryDao;
	
	@Override
	public List<GoldGrantHistory> getGoldGrantHistorys(GoldGrantHistoryFilter filter) throws Exception {
		return goldGrantHistoryDao.getGoldGrantHistorys(filter);
	}

	@Override
	public long getCount(GoldGrantHistoryFilter filter) {
		return goldGrantHistoryDao.getCount(filter);
	}

	@Override
	public void saveGoldGrantHistory(String json) throws Exception {
		goldGrantHistoryDao.saveGoldGrantHistory(json);
	}

}
