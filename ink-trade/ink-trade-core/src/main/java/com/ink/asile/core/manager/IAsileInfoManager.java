package com.ink.asile.core.manager;

import java.util.List;

import com.ink.asile.core.po.AsileInfo;
import com.ink.base.IBaseManager;


public interface IAsileInfoManager  extends IBaseManager<AsileInfo, Long>{

	public List<AsileInfo> findAsileInfos(AsileInfo record);
	AsileInfo findAsileInfoByName(String name);
	
	public int updateNotNull(AsileInfo asileInfo);
}
