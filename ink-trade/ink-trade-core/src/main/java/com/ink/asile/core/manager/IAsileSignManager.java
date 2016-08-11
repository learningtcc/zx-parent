package com.ink.asile.core.manager;

import com.ink.asile.core.po.AsileSign;
import com.ink.base.IBaseManager;

public interface IAsileSignManager  extends IBaseManager<AsileSign, Long>{

	/**
	 * 
	 * @Description 主要根据auth表中cid & channel & payType
	 * @author xuguoqi
	 * @date 2016年6月22日 上午10:49:26
	 * @param asileSign
	 * @return
	 */
    AsileSign selectSignIdByChannel(AsileSign asileSign);
    
    public int updateNotNull(AsileSign asileSign);
}
