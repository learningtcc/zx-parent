package com.ink.platform.core.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ink.platform.api.model.SecOperation;
import com.ink.platform.api.util.PageVO;

/**
 * 操作dao层
 * @author lww
 *
 */
@Repository("secOperationDao")
public interface SecOperationDao {
	/**
	 * @Description 操作查询总数
	 * @param secLog
	 * @author zhangyuanyang
	 */
	int selectListCount(@Param("queryEntity")SecOperation secOperation);
	/**
	 * 分页查询
	 * @param pageVo
	 * @param secOperation
	 * @return
	 */
	List<SecOperation> selectList(@Param("pageVO")PageVO<SecOperation> pageVo,@Param("queryEntity")SecOperation secOperation);
	/**
	 * 操作添加
	 */
	int insertSelective(SecOperation record);
	/**
	 * 根据主键修改
	 */
	int updateByPrimaryKeySelective(SecOperation record);
	/**
	 * 根据主键删除
	 */
	int deleteByPrimaryKey(Long id);
	/**
	 * 详情查询
	 */
	SecOperation selectByPrimaryKey(String id);
	/**
	 * 以条件查询操作项
	 * @param map
	 * @return
	 */
	List<SecOperation> selectAllOperatin(HashMap<Object, Object> map);
	/**
	 * 以条件查询操作项：查询某资源拥有的操作信息
	 * @param map
	 * @return
	 */
	List<SecOperation> selectOperationsByMap(HashMap<Object, Object> map);
}
