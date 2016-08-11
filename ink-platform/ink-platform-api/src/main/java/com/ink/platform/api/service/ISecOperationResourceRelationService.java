package com.ink.platform.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ink.platform.api.model.SecOperation;

/**
 * 操作资源关系接口
 * @author lww
 *
 */
public interface ISecOperationResourceRelationService {

	/**
	 * 新增操作
	 * @param map
	 * @return
	 */
	boolean addOperation(Map<Object, Object> map);

	/**
	 * 按条件查询功能
	 * @param map
	 * @return
	 */
	List<SecOperation> selectOperations(HashMap<Object, Object> map);

	/**
	 * 更新关联关系
	 * @param map
	 * @return
	 */
	boolean updateRelation(HashMap<Object, Object> map);

}
