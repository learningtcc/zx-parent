package com.ink.user.core.service.mongo.impl;

import java.util.List;

import com.ink.user.mongo.util.GoldRecoveryHistoryFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.user.core.dao.mongo.GoldRecoveryHistoryMapper;
import com.ink.user.core.po.mongo.GoldRecoveryHistory;
import com.ink.user.core.service.mongo.IGoldRecoveryHistoryService;

@Service
public class GoldRecoveryHistoryServiceImpl implements IGoldRecoveryHistoryService{

	@Autowired
	private GoldRecoveryHistoryMapper goldRecoveryHistoryDao;
	
	@Override
	public List<GoldRecoveryHistory> getGoldRecoveryHistorys(GoldRecoveryHistoryFilter filter) throws Exception {
		return goldRecoveryHistoryDao.getGoldRecoveryHistorys(filter);
	}

	@Override
	public long getCount(GoldRecoveryHistoryFilter filter) {
		return goldRecoveryHistoryDao.getCount(filter);
	}

	@Override
	public void saveGoldRecoveryHistory(String json) throws Exception {
		goldRecoveryHistoryDao.saveGoldRecoveryHistory(json);
	}

}
