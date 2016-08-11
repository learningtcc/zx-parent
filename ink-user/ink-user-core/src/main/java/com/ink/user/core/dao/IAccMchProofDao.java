package com.ink.user.core.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ink.base.EntityDao;
import com.ink.user.core.po.AccMchProof;

/**
 * 商户凭证表dao层
 * @author yangchen
 * @date 2016年5月24日 下午6:01:32
 */
public interface IAccMchProofDao  extends EntityDao<AccMchProof, Integer>{
	
	public List<AccMchProof> findAccMchProofs(AccMchProof record);
	
	public BigDecimal getAmtByMchIdAndSacType(AccMchProof record);
	
	public BigDecimal getAmtByMchIdAndSacTypeAndTime(AccMchProof record, Date startDate, Date endDate);
   
}