package com.ink.balance.core.dao.impl;

import org.springframework.stereotype.Repository;

import com.ink.balance.core.dao.IOperationLogDao;
import com.ink.balance.core.po.OperationLog;
import com.ink.base.dao.BaseIbatisDao;

/**
 * 
 * @author 作者 :bo.chen
 * @version 创建时间：2016年3月29日 上午11:46:53
 * @description 描述：操作日志数据实现dao
 * 
 */
@Repository("operationLogDao")
public class OperationLogDaoImpl extends BaseIbatisDao<OperationLog, Integer>
		implements IOperationLogDao {
	
	@Override
	public String getIbatisSqlMapNamespace() {
		return "OperationLog";
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return 0;
	}

	@Override
	public int insert(OperationLog record) {
		return 0;
	}

	@Override
	public int insertSelective(OperationLog record) {
		return 0;
	}

	@Override
	public OperationLog selectByPrimaryKey(Long id) {
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(OperationLog record) {
		return 0;
	}

	@Override
	public int updateByPrimaryKey(OperationLog record) {
		return 0;
	}



}
