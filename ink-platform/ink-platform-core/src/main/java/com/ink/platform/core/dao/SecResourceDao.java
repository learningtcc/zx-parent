package com.ink.platform.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ink.platform.api.model.SecResource;

/**
 * 资源dao处理层
 * @author lww
 *
 */
@Repository("secResourceDao")
public interface SecResourceDao {

	/**
	 * 查询数据库菜单列表
	 * @param userId 
	 * @param sysCode 
	 * @return
	 */
	List<SecResource> getResourceList(Map<Object,Object> map);
	/**
	 * @Description 资源列表查询总数
	 * @param map
	 * @author zhangyuanyang
	 */
	int selectListCount(HashMap<Object, Object> map);

	/**
	 * 根据主键删除
	 */
	int deleteByPrimaryKey(SecResource record);
	/**
	 * 详情查询
	 */
	SecResource selectByPrimaryKey(Long id);
	/**
	 * 查询资源树
	 */
	List<SecResource> resourceTree();
	/**
	 * 按条件查询资源信息
	 * @param map
	 * @return
	 */
	List<Object> getFuzzyQuery(HashMap<Object, Object> map);
	/**
	 * 查询资源对象
	 */
	List<SecResource> selectResource(SecResource resource);
	/**
	 * 添加资源
	 */
	int insertSelective(SecResource record);
	/**
	 * 根据主键修改
	 */
	int updateByPrimaryKeySelective(SecResource record);
	/**
	 * 查询所有资源
	 * @return
	 */
	List<SecResource> selectAllResource();
	/**
	 * 查询下级资源
	 * @param parentId
	 * @return
	 */
	List<SecResource> getChildrenModues(String parentId);
	/**
	 * 按条件查询
	 * @param map
	 * @return
	 */
	List<SecResource> selectResourceByMap(HashMap<Object, Object> map);
	/**
	 * 查询角色授权信息
	 * @param roleId
	 * @param sysCode 
	 * @return
	 */
	List<SecResource> getRoleResourceList(Map<Object, Object> roleMap);

}
