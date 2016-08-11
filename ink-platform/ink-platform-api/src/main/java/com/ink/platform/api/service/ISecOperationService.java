package com.ink.platform.api.service;

import java.util.HashMap;
import java.util.List;

import com.ink.platform.api.model.SecOperation;
import com.ink.platform.api.util.PageVO;


/**
 * 操作管理接口
 * @author lww
 *
 */
public interface ISecOperationService {

	/**
	 * 操作列表
	 * @param pagevo
	 * @param secOperation
	 * @return
	 */
	List<SecOperation> selectList(PageVO<SecOperation> pagevo,SecOperation secOperation);

	/**
	 * 新增操作
	 * @param secOperation
	 * @return
	 */
	boolean insertSelective(SecOperation secOperation);

	/**
	 * 更新操作信息
	 * @param secOperation
	 * @return
	 */
	boolean updateByPrimaryKeySelective(SecOperation secOperation);

	/**
	 * 删除操作
	 * @param secOperation
	 * @return
	 */
	boolean deleteByPrimaryKey(SecOperation secOperation);

	/**
	 * 以条件查询操作项
	 * @param map
	 * @return
	 */
	List<SecOperation> selectAllOperatin(HashMap<Object, Object> map);
	/**
	 * 操作详情
	 * @param operationId
	 * @return
	 */
	SecOperation selectOperationDetail(String operationId);

}
