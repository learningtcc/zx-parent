package com.ink.platform.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ink.platform.api.model.SecRole;
import com.ink.platform.api.model.SecUserRoleRelation;
import com.ink.platform.api.util.PageVO;

/**
 * 角色dao层
 * @author lww
 *
 */
@Repository("secRoleDao")
public interface SecRoleDao {
	/**
	 *  查询角色总数
	 * @param secRole
	 * @author zhangyuanyang
	 */
	int selectListCount(@Param("queryEntity")SecRole secRole);
	/**
	 * 分页查询角色列表
	 * @param pageVo
	 * @param secRole
	 * @return
	 */
	List<SecRole> selectList(@Param("pageVO")PageVO<SecRole> pageVo,@Param("queryEntity")SecRole secRole);
	/**
	 * 操作添加
	 */
	int insertSelective(SecRole record);
	/**
	 * 根据主键修改
	 */
	int updateByPrimaryKeySelective(SecRole record);
	/**
	 * 根据主键删除
	 */
	int deleteByPrimaryKey(Long id);
	/**
	 * 详情查询
	 */
	List<Long> selectByPrimaryKey(Long id);

	/**
	 * 查询用户所有角色属性
	 * @param map
	 * @return
	 */
	List<SecRole> selectUserRole(Map<Object, Object> map);
	/**
	 * 查询所有角色
	 * @return
	 */
	List<SecRole> selectAllRole();
	
	/**
	 * 为组合机构或用户：查询未拥有角色
	 * @param secUserRoleRelation
	 * @return
	 */
	List<SecRole> selectNoOwnedRole(SecUserRoleRelation role);
	/**
	 * 查询角色详情
	 * @param roleId
	 * @return
	 */
	SecRole selectRoleById(String roleId);
	/**
	 * 按条件查询：验重
	 * @param map
	 * @return
	 */
	List<SecRole> selecRoleByMap(HashMap<Object, Object> map);
}
