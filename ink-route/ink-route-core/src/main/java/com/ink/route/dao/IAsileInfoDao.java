package com.ink.route.dao;

import java.util.List;

import com.ink.base.EntityDao;
import com.ink.route.api.model.po.AsileInfo;

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
