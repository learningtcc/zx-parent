package com.ink.user.core.service.mongo;

import java.util.List;

import com.ink.user.mongo.util.AccAccLogFilter;
import com.ink.user.core.po.mongo.AccAccLog;

public interface IAccAccLogService {

	public void saveAccAccLog(String json) throws Exception;
	
	public List<AccAccLog> getAccAccLogs(AccAccLogFilter filter) throws Exception;

	public long getCount(AccAccLogFilter filter);
	
	public AccAccLog getById(long id);
}
