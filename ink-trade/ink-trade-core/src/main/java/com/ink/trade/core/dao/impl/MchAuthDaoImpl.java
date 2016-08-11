/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.trade.core.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.trade.core.dao.IMchAuthDao;
import com.ink.trade.core.po.MchAuth;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@Repository("mchAuthDao")
public class MchAuthDaoImpl extends BaseIbatisDao<MchAuth, java.lang.Long> implements IMchAuthDao {

    @Override
    public String getIbatisSqlMapNamespace() {
        return "MchAuth";
    }

    @Override
    public MchAuth getByMchIdPayType(String mchId, String payType) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("mchId", mchId);
        param.put("payType", payType);
        Object obj = getSqlSession().selectOne(getIbatisSqlMapNamespace().concat(".getByMchIdPayType"), param);
        return (MchAuth) obj;
    }

    @Override
    protected void prepareObjectForSave(MchAuth entity) {
        // if(entity.getId()) == null) {
        // entity.setId(IdGenerator.genUUIDStr().longValue());
        // }
    }
    @Override
    public int updateNotNull(MchAuth mchAuth){
		return getSqlSession().update("MchAuth.updateNotNull",mchAuth);
	}
}
