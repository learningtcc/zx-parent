package com.ink.user.core.service.tns;

import java.math.BigDecimal;
import java.util.Date;

import com.ink.user.core.po.TnsLog;
/**
 * 
 * @ClassName: IRechargeService	
 * @Description: 账户充值接口
 * @author guojie.gao
 * @date 2015年10月20日 下午5:38:00
 * 
 */
public interface ITnsLogService {

	/**
	 * 
	 * @FunctionName insertTnsLog
	 * @Description 插入流水信息
	 * @author guojie.gao
	 * @date 2015年10月29日 上午11:20:53
	 * @version 1.0
	 * @history guojie.gao, 2015年10月29日 上午11:20:53 create
	 * 
	 * @param tnsLog
	 * @return
	 */
	public TnsLog insertTnsLog(TnsLog tnsLog);

	/**
	 * 
	 * @Title: findTnsLogByOriOrdId
	 * @Description: 根据原订单查询流水信息 创建人：guojie.gao , 2015年10月20日 下午7:12:38
	 *               修改人：guojie.gao , 2015年10月20日 下午7:12:38
	 * @param
	 * @return
	 * @throws
	 * 
	 */
	public TnsLog findTnsLogByOriOrdId(Long mchId, String oriOrdId,
			Date oriOrdDate, String oriTxnCode) throws Exception;

	/**
	 * 
	 * @Title: updateTnsLog
	 * @Description: 根据交易流水id，更新流水信息 创建人：guojie.gao , 2015年10月21日 下午5:10:26
	 *               修改人：guojie.gao , 2015年10月21日 下午5:10:26
	 * @param
	 * @return
	 * @throws
	 * 
	 */
	public int updateTnsLog(TnsLog tnsLog) throws Exception;
	
	/**
	 * 
	 * @FunctionName findTnsLogByOrdId
	 * @Description 根据订单编号，商户编号查询订单信息
	 * @author guojie.gao
	 * @date 2015年10月31日 下午3:44:25
	 * @version 1.0
	 * @history guojie.gao, 2015年10月31日 下午3:44:25 create
	 * 
	 * @param mchId
	 * @param ordId
	 * @param ordDate
	 * @param txnCode
	 * @return
	 * @throws Exception
	 */
	public TnsLog findTnsLogByOrdId(Long mchId, String ordId,
			Date ordDate, String txnCode) throws Exception;

	
	public TnsLog findTnsLogById(Long id)throws Exception;
	
	
	public TnsLog checkTnsLog(String ordId,String txnCode,String mchId);
	
	public TnsLog findTnsLogByAccUnfrozen(String ordId, BigDecimal amt, BigDecimal custFee, Date ordDate);

	public TnsLog getTnsLogbyId(String id);
}
