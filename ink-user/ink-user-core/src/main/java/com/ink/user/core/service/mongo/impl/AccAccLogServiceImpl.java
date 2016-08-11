package com.ink.user.core.service.mongo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.user.core.dao.mongo.AccAccLogMapper;
import com.ink.user.core.po.mongo.AccAccLog;
import com.ink.user.core.service.mongo.IAccAccLogService;
import com.ink.user.mongo.util.AccAccLogFilter;

@Service
public class AccAccLogServiceImpl implements IAccAccLogService{

	@Autowired
	private AccAccLogMapper accAccLogDao;
	
	@Override
	public List<AccAccLog> getAccAccLogs(AccAccLogFilter filter) throws Exception {
		return accAccLogDao.getAccAccLogs(filter);
	}

	@Override
	public void saveAccAccLog(String json) throws Exception {
		accAccLogDao.saveAccAccLog(json);
		
	}

	@Override
	public long getCount(AccAccLogFilter filter) {
		return accAccLogDao.getCount(filter);
	}

	@Override
	public AccAccLog getById(long id) {
		return accAccLogDao.getById(id);
	}

}
