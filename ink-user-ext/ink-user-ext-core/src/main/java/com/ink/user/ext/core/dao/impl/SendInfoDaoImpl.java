/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.ext.core.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.user.ext.core.dao.ISendInfoDao;
import com.ink.user.ext.core.po.SendInfo;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Repository("sendInfoDao")
public class SendInfoDaoImpl extends BaseIbatisDao<SendInfo,java.lang.Long> implements ISendInfoDao{
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "SendInfo";
	}
	
	@Override
	protected void prepareObjectForSave(SendInfo entity) {
//		if(entity.getId()) == null) {
//			entity.setId(IdGenerator.genUUIDStr().longValue());
//		}
	}

	@Override
	public List<String> getFilePathListByDate(String start, String end) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", "'"+start+"'");
		map.put("end", "'"+end+"'");
		return getSqlSession().selectList(getIbatisSqlMapNamespace()+".getFilePathListByDate",map);
	}

	@Override
	public int updateByFilePath(String filePath, Integer status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("filePath", filePath);
		map.put("status", status);
		return getSqlSession().update(getIbatisSqlMapNamespace()+".updateByFilePath",map);
	}

}
