package com.ink.platform.api.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ink.platform.api.model.SecResource;


/**
 * 权限资源接口
 * @author lww
 *
 */
public interface ISecResourceService {

	/**
	 * 查询资源表左侧菜单数据
	 * @return
	 */
	List<SecResource> getResourceList(Map<Object,Object> map);

	/**
	 * 查询资源信息
	 * @param id
	 * @return
	 */
	SecResource selectByPrimaryKey(Long id);

	/**
	 * 查询资源树
	 * @return
	 */
	List<SecResource> resourceTree();
	List<SecResource> selectResource(SecResource secResource);
	/**
	 * 资源添加
	 * @param secResource
	 * @return
	 */
	boolean insertSelective(SecResource secResource);
	/**
	 * 资源删除
	 * @param secResource
	 * @return
	 */
	boolean deleteByPrimaryKey(SecResource secResource);
	/**
	 * 资源编辑
	 * @param secResource
	 * @return
	 */
	boolean updateByPrimaryKeySelective(SecResource secResource);
	/**
	 * 查询所有资源及操作功能
	 * @return
	 */
	List<SecResource> selectAllResource();

	/**
	 * 查询下级资源
	 * @param parentId
	 * @return
	 */
	List<SecResource> selectLowResource(String parentId);

	/**
	 * 按条件查询资源
	 * @param map
	 * @return
	 */
	List<SecResource> selectResourceByMap(HashMap<Object, Object> map);
	/**
	 * 查询某角色授权信息
	 * @param roleId
	 * @param sysCode 
	 * @return
	 */
	List<SecResource> getRoleResourceList(Map<Object, Object> roleMap);

	/**
	 * 查总数
	 * @param map
	 * @return
	 */
	int selectListCount(HashMap<Object, Object> map);

}
