package com.ink.user.core.service.tns;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ink.user.core.po.TnsAuth;
import com.ink.user.core.po.TnsLog;
import com.ink.user.core.po.AccCust;

public interface ITnsAuthService {

	
	/**
	 * 
	 * @Title: insertTnsAuth 
	 * @Description: 增加授权信息
	 * 创建人：guojie.gao ,  2015年10月20日  下午6:20:49
	 * 修改人：guojie.gao ,  2015年10月20日  下午6:20:49
	 * @param
	 * @return
	 * @throws
	 *
	 */
	public void insertTnsAuth(TnsLog tnsLog, AccCust accCust) throws Exception;
	
	/**
	 * 
	 * @Title: checkTnsAuth 
	 * @Description: 根据交易流水编号查询授权信息
	 * 创建人：guojie.gao ,  2015年10月20日  下午7:24:26
	 * 修改人：guojie.gao ,  2015年10月20日  下午7:24:26
	 * @param
	 * @return
	 * @throws
	 *
	 */
	public TnsAuth checkTnsAuth(Long tnsLogId) throws Exception;
	
	/**
	 * 
	 * @Title: updateTnsAuth 
	 * @Description: 根据流水编号更新授权表信息
	 * 创建人：guojie.gao ,  2015年10月20日  下午7:26:17
	 * 修改人：guojie.gao ,  2015年10月20日  下午7:26:17
	 * @param
	 * @return
	 * @throws
	 *
	 */
	public void updateTnsAuth(BigDecimal bal, long id, int version) throws Exception;
	
	/**
	 * 
	 * @Title: updateTnsAuthByrevFlg 
	 * @Description: 更新冲正标志
	 * 创建人：guojie.gao ,  2015年10月21日  上午11:23:50
	 * 修改人：guojie.gao ,  2015年10月21日  上午11:23:50
	 * @param
	 * @return
	 * @throws
	 *
	 */
	public int updateTnsAuthByrevFlg(long id, int version) throws Exception;
	
	/**
	 * 查询一段时间内未解冻的授权信息
	 * @return
	 */
	public List<TnsAuth> getUnfrozenTnsAuth(Date startTime, Date endTime);
}
