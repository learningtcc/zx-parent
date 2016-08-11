package com.ink.platform.api.service;

import java.util.Map;

import com.ink.platform.api.model.SecUserOrgRelation;


public interface ISecUserOrgRelationService {

	/**
	 * 新增关联关系
	 * @param secUserOrgRelation
	 * @return
	 */

	boolean insertSelective(SecUserOrgRelation secUserOrgRelation);


	/**
	 * 设置副岗
	 * @param secUserOrgRelation
	 * @return
	 */
	boolean addOrgViceJob(SecUserOrgRelation secUserOrgRelation);

	/**
	 * 查询用户组织关系
	 * @param secUserOrgRelation 
	 * @return
	 */
	SecUserOrgRelation selectSecUserOrgRelation(SecUserOrgRelation secUserOrgRelation);
	/**
	 * 更新用户组织关系
	 * @param map
	 * @return
	 */
	boolean updateUserOrgRelation(Map<Object, Object> map);

	/**
	 * 删除用户组织机构关系
	 * @param map
	 * @return
	 */
	boolean deleteUserOrgRelationByMap(Map<Object, Object> map);
}
