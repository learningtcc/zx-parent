package com.ink.platform.core.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ink.platform.api.model.SecUserOrgRelation;

/**
 * 用户组织关系dao处理层
 * @author lww
 *
 */
@Repository("secUserOrgRelationDao")
public interface SecUserOrgRelationDao {

	/**
	 * 新增关系
	 * @param secUserOrgRelation
	 * @return
	 */
	boolean insertSelective(SecUserOrgRelation secUserOrgRelation);
	/**
	 * 设置副岗
	 * @param secUserOrgRelation
	 * @return
	 */
	int addOrgViceJob(SecUserOrgRelation secUserOrgRelation);
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
	int updateUserOrgRelation(Map<Object, Object> map);
	/**
	 * 删除用户组织关系
	 * @param map
	 * @return
	 */
	
	int deleteUserOrgRelationByMap(Map<Object, Object> map);
}
