package com.ink.user.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ink.base.EntityDao;
import com.ink.user.core.po.AccCard;
import com.ink.user.core.po.AccCust;

public interface IAccCardDao extends EntityDao<AccCard, Integer>{
	int deleteByPrimaryKey(Long id);

	int insert(AccCard record);

	int insertSelective(AccCard record);

	AccCard selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(AccCard record);

	int updateByPrimaryKey(AccCard record);
	
    AccCard selectByUidAndCustType(@Param("custType") String custType,
                    @Param("uid") String uid);
	/**
	 * 
	 * @Title: getPersonalAccCard
	 * @Description: 个人账户银行卡查询
	 * @param custType
	 *            客户类型
	 * @param custId
	 *            客户编号
	 * @param mchId
	 *            商户编号
	 * @return List<AccCard>
	 * @throws
	 * 
	 */
	public List<AccCard> getPersonalAccCard(@Param("custType") String custType,
			@Param("custId") String custId, @Param("mchId") Long mchId);

	/**
	 * 
	 * @Title: selectAccCardByMchIdAndCardNo
	 * @Description: 根据商户编号、银行卡号查询卡信息
	 * @param @param mchId
	 * @param @param cardNo
	 * @return AccCust
	 * @throws
	 * 
	 */
	public List<AccCard> selectAccCardByMchIdAndCardNo(@Param("uid") Long uid,
			@Param("cardNo") String cardNo);
	
	/**
	 * 
	 * @FunctionName selectAccCardByMchIdAndCardNoAndStatus
	 * @Description 银行卡解绑查询有效的卡信息
	 * @author guojie.gao
	 * @date 2015年12月7日 下午4:53:08
	 * @version 1.0
	 * @history guojie.gao, 2015年12月7日 下午4:53:08 create
	 * 
	 * @param uid
	 * @param cardNo
	 * @param status
	 * @return
	 */
	public AccCard selectAccCardByMchIdAndCardNoAndStatus(@Param("uid") Long uid,
			@Param("cardNo") String cardNo, @Param("status")int status);

	/**
	 * 
	 * @Title: updateAccCardStatus
	 * @Description: 根据商户编号、银行卡号更改status
	 * @param @param mchId
	 * @param @param cardNo
	 * @return integer
	 * @throws
	 * 
	 */
	public int updateAccCardStatus(@Param("uid") Long uid,
			@Param("cardNo") String cardNo, @Param("status") Integer status,
			@Param("version") Integer version);

	/**
	 * 
	 * @FunctionName checkBindCard
	 * @Description 一个客户只能绑定一张卡
	 * @author guojie.gao
	 * @date 2015年12月23日 下午4:16:52
	 * @version 1.0
	 * @history guojie.gao, 2015年12月23日 下午4:16:52 create
	 * 
	 * @param uid
	 * @return
	 */
	public AccCard checkBindCard(@Param("uid")Long uid, @Param("status")Integer status);
	
	/**
	 * 
	 * @Title: checkAccCardIsExist
	 * @Description: 根据商户号、银行卡号查询卡信息是否存在（应存在）
	 * @param cardNo
	 * @param mchId
	 * @return AccCard
	 * @throws
	 * 
	 */
	public AccCard checkAccCardIsExist(Long uid, String cardNo);
	/**
	 * 
	 * @Title: insertAccCard
	 * @Description: 新增银行卡信息
	 * @param AccCard
	 * @return integer
	 * @throws
	 * 
	 */
	public AccCard insertAccCard(Object dto, AccCust accCust) throws Exception;
	
	public AccCard insertAccCard(AccCard accCard) throws Exception;
	/**
	 * 
	 * @Title: updateAccCardStatus
	 * @Description: 更新银行卡删除标识（解绑：9）
	 * @param mchId
	 * @param cardNo
	 * @param delFlag
	 * @return integer
	 * @throws
	 * 
	 */
	public void updateAccCardStatus(Long uid, String cardNo, String status,int version);
	
	public int updateAccCard(AccCard accCard);
	/**
	 * 
	 * @FunctionName checkBindCard
	 * @Description 一个客户只能绑定一张卡
	 * @author guojie.gao
	 * @date 2015年12月23日 下午4:14:52
	 * @version 1.0
	 * @history guojie.gao, 2015年12月23日 下午4:14:52 create
	 * 
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	public AccCard checkBindCard(Long uid,int status);
}
