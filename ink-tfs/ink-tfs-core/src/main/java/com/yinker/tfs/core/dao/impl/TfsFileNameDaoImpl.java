/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.yinker.tfs.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.yinker.base.dao.BaseIbatisDao;
import com.yinker.tfs.core.dao.ITfsFileNameDao;
import com.yinker.tfs.core.po.TfsFileName;

import java.util.HashMap;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("tfsFileNameDao")
public class TfsFileNameDaoImpl extends BaseIbatisDao<TfsFileName,Long> implements ITfsFileNameDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "TfsFileName";
	}
	
	@Override
	protected void prepareObjectForSave(TfsFileName entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	@Override
	public TfsFileName findByFileName(String fileName, String suffix,String sourceCode, String moduleCode) {
		Map param=new HashMap();
		if (fileName!=null)
			param.put("fileName",fileName);
		if(suffix!=null)
			param.put("suffix",suffix);
		if(sourceCode!=null)
			param.put("sourceCode",sourceCode);
		if(moduleCode!=null)
			param.put("moduleCode",moduleCode);
		return (TfsFileName)getSqlSession().selectOne(getIbatisSqlMapNamespace()+".findByFileName", param);
	}
}
