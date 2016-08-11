package com.ink.platform.api.service;

import java.util.List;


import com.ink.platform.api.model.SecRoleResourceRelation;

/**
 * 角色资源关系接口
 * @author lww
 *
 */
public interface ISecRoleResourceService {


	//查询角色授权时：已拥有的资源
	List<SecRoleResourceRelation> getRoleResourceList(String roleId);
}
