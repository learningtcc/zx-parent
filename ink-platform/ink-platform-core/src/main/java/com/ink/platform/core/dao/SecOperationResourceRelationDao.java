package com.ink.platform.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ink.platform.api.model.SecOperationResourceRelation;

/**
 * 操作：资源关系处理dao层
 * @author lww
 *
 */
@Repository("secOperationResourceRelationDao")
public interface SecOperationResourceRelationDao {

	/**
	 * 新增caozuo
	 * @param map
	 * @return
	 */
	int addOperation(Map<Object, Object> map);

	/**
	 *	按条件查询操作功能
	 * @param map
	 * @return
	 */
	List<SecOperationResourceRelation> selectOperations(HashMap<Object, Object> map);

	/**
	 * 删除关联关系
	 * @param map
	 * @return
	 */
	boolean updateRelation(HashMap<Object, Object> map);

}
