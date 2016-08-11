package com.ink.user.core.dao;

import java.util.Date;
import java.util.List;

import com.ink.user.core.po.TnsAuth;
import com.ink.base.EntityDao;

public interface ITnsAuthDao extends EntityDao<TnsAuth, Integer>{
	int deleteByPrimaryKey(Long id);

	int insertSelective(TnsAuth record);

	TnsAuth selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(TnsAuth record);

	int updateByPrimaryKey(TnsAuth record);

	
	/**
	 * 
	 * @Title: selectByTnsLogId 
	 * @Description: 根据交易流水编号，查询授权信息
	 * 创建人：guojie.gao ,  2015年10月20日  下午2:30:37
	 * 修改人：guojie.gao ,  2015年10月20日  下午2:30:37
	 * @param tnsLogId
	 * @return TnsAuth
	 * @throws
	 *
	 */
	public TnsAuth selectByTnsLogId(Long tnsLogId);
	
	
	/**
	 * 
	 * @Title: updateByTnsLogId 
	 * @Description: 根据交易流水编号，更新授权金额和可授权余额
	 * @param @param tnsLogId
	 * @param @return
	 * @return int
	 * @throws
	 *
	 */
	public int updateByTnsLogId(TnsAuth tnsAuth);
	
	public List<TnsAuth> getUnfrozenTnsAuth(Date startTime, Date endTime);

}