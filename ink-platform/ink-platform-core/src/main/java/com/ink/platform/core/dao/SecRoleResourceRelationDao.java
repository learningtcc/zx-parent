package com.ink.platform.core.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ink.platform.api.model.SecRoleResourceRelation;


/**
 * 角色资源关系dao处理层
 * @author lww
 *
 */
@Repository("secRoleResourceRelationDao")
public interface SecRoleResourceRelationDao {
	/**
	 * 角色授权
	 */
	int empower(SecRoleResourceRelation secRoleResourceRelation);
	/**
	 * 删除角色相关
	 * @param roleId
	 */
	void deleteByRoleId(Long roleId);
	/**
	 * 查询角色关联信息
	 * @param roleId
	 * @return
	 */
	List<SecRoleResourceRelation> getRoleResourceList(long roleId);

}
