package com.ink.asile.core.manager;


import com.ink.asile.core.po.AsileResCode;
import com.ink.base.IBaseManager;

public interface IAsileResCodeManager extends IBaseManager<AsileResCode, Long> {
	/**
	 * 获取渠道与平台响应码关联关系 
	 * @param AsileCode
	 * @param AsileResCode
	 * @return
	 */
	 
    public AsileResCode findByAsileCodeAndAsileResCode(String channelId, String resCode);

    public int updateNotNull(AsileResCode asileResCode);
}
