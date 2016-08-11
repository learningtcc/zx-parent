package com.ink.route.manager;

import java.util.List;

import com.ink.base.IBaseManager;
import com.ink.route.api.model.po.AsileInfo;


public interface IAsileInfoManager  extends IBaseManager<AsileInfo, Long>{

	public List<AsileInfo> findAsileInfos(AsileInfo record);
	AsileInfo findAsileInfoByName(String name);
	
	public int updateNotNull(AsileInfo asileInfo);
}
