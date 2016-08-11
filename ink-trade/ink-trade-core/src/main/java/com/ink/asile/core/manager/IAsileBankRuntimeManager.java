package com.ink.asile.core.manager;

import java.util.List;

import com.ink.asile.core.po.AsileBankRuntime;
import com.ink.base.IBaseManager;

public interface IAsileBankRuntimeManager extends
		IBaseManager<AsileBankRuntime, Long> {
	public List<AsileBankRuntime> findAsileBankRuntimes(AsileBankRuntime record);

	/**
	 * 查询服务不可用渠道
	 * @param queryParam
	 * @return
     */
	List<AsileBankRuntime> getNotAvailableChannel(AsileBankRuntime queryParam);
	
	public int updateNotNull(AsileBankRuntime asileBankRuntime);
}
