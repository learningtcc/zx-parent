package com.ink.user.core.dao;

import com.ink.user.core.po.AccCardProof;
import com.ink.base.EntityDao;

public interface IAccCardProofDao extends EntityDao<AccCardProof, Integer>{
	
	int insert(AccCardProof record);

    int insertSelective(AccCardProof record);

    public void insertAccCardProof(AccCardProof accCardProof, String txnCode);
}
