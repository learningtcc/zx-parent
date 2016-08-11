package com.ink.user.core.dao;

import com.ink.user.core.po.AccAcc;
import com.ink.base.EntityDao;
import com.ink.user.core.po.AccProof;

/**
 * 资金凭证表dao层
 * @author yangchen
 * @date 2016年5月24日 下午6:00:42
 */
public interface IAccProofDao extends EntityDao<AccProof, Long>{
	
	int insert(AccProof record);

    int insertSelective(AccProof record);
    
    public void insertAccProof(AccAcc accAcc, String txnCode);
}
