package com.ink.asile.core.dao;

import java.util.List;

import com.ink.asile.core.po.AsileInfo;
import com.ink.base.EntityDao;

public interface IAsileInfoDao extends EntityDao<AsileInfo, Long> {

	/**
	 * 
	* @Title: findAsileInfos
	* @Description: 条件查询AsileInfo
	* @param @param record
	* @param @return    
	* @return List<AsileInfo>    
	* @throws
	 */
	public List<AsileInfo> findAsileInfos(AsileInfo record);
	AsileInfo findAsileInfoByName(String name);
	public int updateNotNull(AsileInfo asileInfo);
}
