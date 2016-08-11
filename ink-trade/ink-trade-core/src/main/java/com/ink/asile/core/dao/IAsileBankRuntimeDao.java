package com.ink.asile.core.dao;

import java.util.List;

import com.ink.asile.core.po.AsileBankRuntime;
import com.ink.base.EntityDao;

public interface IAsileBankRuntimeDao extends EntityDao<AsileBankRuntime, Long> {

	public List<AsileBankRuntime> findAsileBankRuntimes(AsileBankRuntime record);

	/**
	 * 查询服务不可用渠道
	 * @param queryParam
	 * @return
	 */
	List<AsileBankRuntime> getNotAvailableChannel(AsileBankRuntime queryParam);

	/**
	 * 查询所有数据
	 * @return
     */
	List<AsileBankRuntime> getAll();
	
	public int updateNotNull(AsileBankRuntime asileBankRuntime);
}
