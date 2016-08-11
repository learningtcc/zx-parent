package com.ink.route.dao;

import java.util.List;

import com.ink.base.EntityDao;
import com.ink.route.api.model.po.AsileBankRuntime;

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
