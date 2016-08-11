package com.ink.platform.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ink.platform.api.model.SecRole;
import com.ink.platform.api.model.SecRoleResourceRelation;
import com.ink.platform.api.util.PageVO;

/**
 * 角色相关接口
 * @author lww
 *
 */
public interface ISecRoleService {

	/**
	 * 分页查询角色列表
	 * @param pagevo
	 * @param secRole
	 * @return
	 */
	List<SecRole> selectList(PageVO<SecRole> pagevo, SecRole secRole);
	
	/**
	 * 新增角色
	 * @param secRole
	 * @return
	 */
	boolean insertSelective(SecRole secRole);

	/**
	 * 更新角色信息s
	 * @param secRole
	 * @return
	 */
	boolean updateByPrimaryKeySelective(SecRole secRole);

	/**
	 * 删除角色信息
	 * @param secRole
	 * @return
	 */
	boolean deleteByPrimaryKey(SecRole secRole);

	/**
	 * 角色授权：给角色资源属性
	 * @param secRoleResourceRelation
	 * @return
	 */
	boolean roleAuth(SecRoleResourceRelation secRoleResourceRelation);

	/**
	 * 按条件查询用户角色
	 * @param map
	 * @return
	 */
	List<SecRole> selectUserRole(Map<Object, Object> map);

	/**
	 * 删除角色资源关联信息
	 * @param roleId
	 */
	void deleteByRoleId(Long roleId);

	/**
	 * 查询所有角色
	 * @return
	 */
	List<SecRole> selectAllRole();
	
	/**
	 * 查询角色详情
	 * @param roleId
	 * @return
	 */
	SecRole selectRoleById(String roleId);
	
	/**
	 * 按条件查询
	 * @param map
	 * @return
	 */
	List<SecRole> selecRoleByMap(HashMap<Object, Object> map);
}
