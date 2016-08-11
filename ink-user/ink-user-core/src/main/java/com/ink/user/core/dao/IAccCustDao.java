package com.ink.user.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ink.base.EntityDao;
import com.ink.user.core.po.AccCust;
import com.ink.user.core.po.ReqLog;

/**
 * 客户表dao层
 * @author yangchen
 * @date 2016年5月24日 下午6:10:49
 */
public interface IAccCustDao extends EntityDao<AccCust, Long>{
	int deleteByPrimaryKey(Long id);

	int insert(AccCust record);

	int insertSelective(AccCust record);

	AccCust selectByPrimaryKey(Long id);
	AccCust selectByuid(Long uid);
	int updateByPrimaryKeySelective(AccCust record);

	int updateByPrimaryKey(AccCust record);
    AccCust selectByMchIdAndCustType(@Param("mchId")Long mchId,@Param("custType")int custType);
	/**
	 * 
	 * @Title: selectAccCustInfo
	 * @Description: 综合账户信息查询，根据客户类型、证件类型、证件号码
	 * @param @param mchId
	 * @param @param idType
	 * @param @param idNo
	 * @return AccCust
	 * @throws
	 * 
	 */
	public List<AccCust> selectAccCustInfo(@Param("idType") String idType, @Param("idNo") String idNo);

	/**
	 * @throws Exception 
	 * 
	 * @Title: selectAccCustByCustIdAndMchId
	 * @Description: 根据商户编号、客户号查用户信息
	 * @param @param mchId
	 * @param @param custId
	 * @return AccCust
	 * @throws
	 * 
	 */
	public AccCust selectAccCustByCustIdAndMchId(@Param("mchId") Long mchId,
			@Param("custId") String custId) throws Exception;
	
	/**
	 * 
	 * @Title: selectAccCustAllInfo
	 * @Description: 查询客户信息表，暂时用户主键查询
	 * @param @param AccCustVO
	 * @return List<AccCust>
	 * @throws
	 * 
	 */
	public List<AccCust> selectAccCustAllInfo(AccCust accCust);
	
    /**
     * 
     * @Title: selectAccCustByCustTypeAndCustIdAndMchId
     * @Description: 根据客户类型、商户编号、客户号查用户信息
     * @param @param mchId
     * @param @param custId
     * @return AccCust
     * @throws
     * 
     */
    public AccCust selectAccCustByCustTypeAndCustIdAndMchId(@Param("custType")Integer custType, @Param("mchId") String mchId,
            @Param("custId") String custId);

    
	public AccCust checkAccCust(Long mchId, String custId);
	/**
	 * 
	 * @Title: insertAccCust
	 * @Description: 个人账户开户（ACC38040）
	 * @param @param accCust
	 * @param @throws Exception
	 * @return Integer
	 * @throws
	 * 
	 */
	public AccCust insertAccCust(ReqLog reqLog, Object dto)throws Exception;
	
	public void updateAccCust(AccCust accCust);

	AccCust existCheckAccCust(Long mchId, String custId);
    void add(AccCust accCust,String cardNo,String cardType) throws Exception;
}
