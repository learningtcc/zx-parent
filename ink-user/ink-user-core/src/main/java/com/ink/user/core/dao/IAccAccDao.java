package com.ink.user.core.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ink.user.core.po.AccAcc;
import org.apache.ibatis.annotations.Param;

import com.ink.base.EntityDao;
import com.ink.base.page.Page;
import com.ink.base.page.PageRequest;
import com.ink.user.core.po.AccCust;

/**
 * accAcc账户表DAO层
 * @author yangchen
 * @date 2016年5月24日 下午5:33:19
 */
public interface IAccAccDao extends EntityDao<AccAcc, Long>{
	/**
	 * 根据主键删除
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * 插入数据
	 * @param record
	 * @return
	 */
	int insert(AccAcc record);

	/**
	 * 根据主键更新（行锁）
	 * @param record
	 * @return
	 */
//	int updateByPrimaryKeySelective(AccAcc record);

	/**
	 * 根据主键更新
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(AccAcc record);

	/**
	 * 开户
	 * @param accCust 客户信息
	 * @param sacType 账户类型
	 * @return
	 * @throws Exception 
	 */
	public AccAcc createAccAcc(AccCust accCust, String sacType) throws Exception;

	/**
	 * 根据主账户号和账户类型查询
	 * @param pacId
	 * @param sacType
	 * @return
	 * @throws Exception
	 */
	public AccAcc selectAccAccByPacIdAndSacType(Long pacId, String sacType)
			throws Exception;

	/**
	 * 根据主账户号和账户类型查询（行锁）
	 * @param pacId
	 * @param sacType
	 * @return
	 * @throws Exception
	 */
	public AccAcc selectAccAccByPacIdAndSacTypeWithBLOBs(Long pacId,
			String sacType) throws Exception;

	/**
	 * 更新账户金额
	 * @param accAcc
	 * @param amt
	 * @return
	 * @throws Exception
	 */
	public int updateAccAcc(AccAcc accAcc, BigDecimal amt) throws Exception;
	
	public int updateForFrozenByPrimaryKeyWithBLOBs(AccAcc accAcc, BigDecimal amt) throws Exception;
	
	public int updateForWithdrawAcceptByPrimaryKeyWithBLOBs(AccAcc accAcc, BigDecimal amt) throws Exception;

	/**
	 * 账户类型为空时查询所有余额
	 * @param pacId
	 * @param sacType
	 * @return
	 * @throws Exception
     */
	public  List<AccAcc> selectListByPacIdAndSacType(Long pacId,
			String sacType)throws Exception;
	
	public AccAcc checkByPacIdAndSacType(AccCust accCust,
			String sacType) throws Exception;
	  int insertSelective(AccAcc record);
	
	/**
	 * 
	 * @Title: getAccAccByPacId
	 * @Description: 根据pac_id查资金账户
	 * @param:AccAcc
	 * @return List<AccAcc>
	 * @throws
	 * 
	 */
	public List<AccAcc> getAccAccByPacId(@Param("pacId") String pacId);

	/**
	 * 
	 * @FunctionName updateAccAccByPacIdAndSacType
	 * @Description 根据主账号id和子账号类型进行更新账户余额信息
	 * @author guojie.gao
	 * @date 2015年11月12日 下午5:52:36
	 * @version 1.0
	 * @history guojie.gao, 2015年11月12日 下午5:52:36 create
	 * 
	 * @param pacId
	 * @param sacType
	 * @return
	 */
//	public int updateAccAccByPacIdAndSacType(AccAcc accacc);

	/**
	 * 根据客户类型、证件类型、证件号查询
	 * @param custType
	 * @param idType
	 * @param idNo
	 * @return
	 */
	public List<AccAcc> selectAccAccByCustTypeAndIdTypeAndIdNo(
			@Param("custType") Integer custType,
			@Param("idType") Integer idType, @Param("idNo") String idNo);

	public Page<AccAcc> findCustPage(PageRequest<?> query);
	
	public Page<AccAcc> findMchPage(PageRequest<?> query);

	public int updateMchAccount(BigDecimal amt, Long mchId, String type) throws Exception;

	public int initLastUpdateTime(Date date);
	public int initAccMac(Date date);
}
