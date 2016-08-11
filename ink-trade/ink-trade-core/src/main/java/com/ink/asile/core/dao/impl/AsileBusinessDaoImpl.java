/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.asile.core.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ink.asile.core.dao.IAsileBusinessDao;
import com.ink.asile.core.po.AsileBusiness;
import com.ink.base.dao.BaseIbatisDao;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("asileBusinessDao")
public class AsileBusinessDaoImpl extends BaseIbatisDao<AsileBusiness,java.lang.Long> implements IAsileBusinessDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "AsileBusiness";
	}
	
	@Override
	protected void prepareObjectForSave(AsileBusiness entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}
	public AsileBusiness findByAsileCodeBusinessCode(String asileCode,String businessCode){
	    Map<String, String> param=new HashMap<String, String>();
	    param.put("asileCode", asileCode);
	    param.put("businessCode", businessCode);
	   return getSqlSessionSlave().selectOne("AsileBusiness.findByAsileCodeBusinessCode",param);
	}
	@Override
	public int updateNotNull(AsileBusiness asileBusiness){
		return getSqlSession().update("AsileBusiness.updateNotNull",asileBusiness);
	}
}
