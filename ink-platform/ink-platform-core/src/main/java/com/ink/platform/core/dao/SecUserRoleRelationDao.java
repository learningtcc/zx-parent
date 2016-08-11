package com.ink.platform.core.dao;


import org.springframework.stereotype.Repository;

import com.ink.platform.api.model.SecUserRoleRelation;

/**
 * 用户角色关系dao处理层
 * @author lww
 *
 */
@Repository("secUserRoleRelationDao")
public interface SecUserRoleRelationDao {

	/**
	 * 更新用户角色关系
	 * @param secUserOrgRelation
	 * @return
	 */
	boolean updateUserRoleRelation(SecUserRoleRelation secUserOrgRelation);

	/**
	 * 为组合机构或用户：授予角色
	 * @param secUserRoleRelation
	 * @return
	 */
	int addOrgUserRole(SecUserRoleRelation secUserRoleRelation);

	/**
	 * 更新删除用户角色
	 * @param secUserRoleRelation
	 * @return
	 */
	boolean updateUserRole(SecUserRoleRelation secUserRoleRelation);
}
