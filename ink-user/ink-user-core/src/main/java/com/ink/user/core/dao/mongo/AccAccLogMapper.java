package com.ink.user.core.dao.mongo;

import java.util.List;

import com.ink.user.mongo.util.AccAccLogFilter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.DAO;

import com.ink.user.core.po.mongo.AccAccLog;

public interface AccAccLogMapper  extends DAO<AccAccLog, ObjectId>{

	
	void saveAccAccLog(String json) throws Exception;
	
	public List<AccAccLog> getAccAccLogs(AccAccLogFilter filter) throws Exception;
	
	public long getCount(AccAccLogFilter filter);

	public AccAccLog getById(long id);
}
