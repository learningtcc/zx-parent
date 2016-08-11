package com.ink.user.core.dao;

import java.util.List;

import com.ink.base.EntityDao;
import com.ink.user.core.po.AccSacType;

/**
 * 账户类型表dao层
 * @author yangchen
 * @date 2016年5月24日 下午5:59:37
 */
public interface IAccSacTypeDao extends EntityDao<AccSacType, Long>{
	int deleteByPrimaryKey(Long id);

    int insert(AccSacType record);

    int insertSelective(AccSacType record);

    AccSacType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AccSacType record);

    int updateByPrimaryKey(AccSacType record);

    /**
     * @FunctionName selectAccSacTypeBySacType
     * @Description 依据子账户种类查询子账户信息
     * @author baiyu
     * @date 2015年10月22日 下午2:06:24
     * @version 1.0
     * @history baiyu, 2015年10月22日 下午2:06:24 create
     * 
     * @param sacType
     * @return
     */
    public AccSacType selectAccSacTypeBySacType(String sacType);
    
    /**
     * @FunctionName findAccSacTypeAll
     * @Description 查询所有子账户类型的信息
     * @author baiyu
     * @date 2015年11月18日 下午5:56:47
     * @version 1.0
     * @history baiyu, 2015年11月18日 下午5:56:47 create
     * 
     * @return
     */
    public List<AccSacType> findAccSacTypeAll(AccSacType record);

}
