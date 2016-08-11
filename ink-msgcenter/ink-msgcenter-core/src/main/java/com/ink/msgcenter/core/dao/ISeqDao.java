package com.ink.msgcenter.core.dao;

import com.ink.base.EntityDao;

public interface ISeqDao extends EntityDao<Object, java.lang.Long>{
	
	public String get5Seq(String seqCode);
}
