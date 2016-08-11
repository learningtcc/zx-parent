package com.ink.msgcenter.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.msgcenter.core.dao.ISeqDao;
import com.ink.msgcenter.core.service.ISeqManager;

@Service("seqManager")
@Transactional
public class SeqManagerImpl implements ISeqManager{

	@Autowired
	private ISeqDao seqDao;
	
	public ISeqDao getEntityDao() {
		return this.seqDao;
	}
	
	@Override
	public String get5Seq(String seqCode) {
		return seqDao.get5Seq(seqCode);
	}

}
