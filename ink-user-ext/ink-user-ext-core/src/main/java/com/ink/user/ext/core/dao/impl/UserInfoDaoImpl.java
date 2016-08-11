/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.ext.core.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.user.ext.core.dao.IUserInfoDao;
import com.ink.user.ext.core.po.UserInfo;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */

@Repository("userInfoDao")
public class UserInfoDaoImpl extends BaseIbatisDao<UserInfo, Long> implements IUserInfoDao {

	@Override
	public String getIbatisSqlMapNamespace() {
		return "UserInfo";
	}

	@Override
	protected void prepareObjectForSave(UserInfo entity) {
		// if(entity.getId()) == null) {
		// entity.setId(IdGenerator.genUUIDStr().longValue());
		// }
	}

	@Override
	public UserInfo getUserInfoByCustIdMchId(Long custId, Long mchId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custId", custId);
		map.put("mchId", mchId);
		return getSqlSession().selectOne(getIbatisSqlMapNamespace() + ".getUserInfoByCustIdMchId",map);
	}

}
