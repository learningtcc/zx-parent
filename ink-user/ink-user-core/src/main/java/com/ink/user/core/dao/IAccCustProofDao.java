package com.ink.user.core.dao;

import com.ink.base.EntityDao;
import com.ink.user.core.po.AccCustProof;

/**
 * 客户凭证dao层
 * @author yangchen
 * @date 2016年5月24日 下午6:10:34
 */
public interface IAccCustProofDao extends EntityDao<AccCustProof, Integer>{

	int insert(AccCustProof record);

    int insertSelective(AccCustProof record);
    
    public void insertAccCustProof(AccCustProof accCustProof, String txnCode);
}
