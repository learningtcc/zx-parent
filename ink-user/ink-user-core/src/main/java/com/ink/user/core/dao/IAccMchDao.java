package com.ink.user.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ink.base.EntityDao;
import com.ink.user.core.po.AccMch;

/**
 * 商户表dao层
 * @author yangchen
 * @date 2016年5月24日 下午6:10:17
 */
public interface IAccMchDao extends EntityDao<AccMch, Long>{
	
	int deleteByPrimaryKey(Long id);

    int insert(AccMch record);

    int insertSelective(AccMch record);

    AccMch selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AccMch record);

    int updateByPrimaryKey(AccMch record);
    
	/**
	 * 
	 * @Title: queryByMchId 
	 * @Description: 根据商户号查询商户信息
	 * @param @param mchId
	 * @param @return
	 * @return AccMch
	 * @throws
	 *
	 */
	public AccMch findByMchId(@Param("mchId")Long mchId);
	
	/**
	 * 
	 * @Title: checkAccMch
	 * @Description: 根据商户号检查商户信息
	 * @param @param mchId
	 * @param @return
	 * @param @throws Exception
	 * @return map
	 * @throws
	 * 
	 */
	public AccMch checkAccMch(Long mchId);
	
	public List<AccMch> selectAll();
}
