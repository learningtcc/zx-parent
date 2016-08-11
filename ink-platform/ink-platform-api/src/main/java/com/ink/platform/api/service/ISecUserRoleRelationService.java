package com.ink.platform.api.service;

import java.util.List;


import com.ink.platform.api.model.SecRole;
import com.ink.platform.api.model.SecUserRoleRelation;

/**
 * 用户角色关系接口
 * @author lww
 *
 */

public interface ISecUserRoleRelationService {

	/**
	 * 给组织机构 ：或用户授予角色
	 * @param secUserRoleRelation
	 * @return
	 */
	boolean addOrgUserRole(SecUserRoleRelation secUserRoleRelation);


	/**
	 * 查询未拥有的：角色
	 * @param role
	 * @return
	 */
	List<SecRole> selectNoOwnedRole(SecUserRoleRelation role);

	/**
	 *更新删除用户角色
	 * @param secUserRoleRelation
	 * @return
	 */
	boolean updateUserRole(SecUserRoleRelation secUserRoleRelation);
	
}
