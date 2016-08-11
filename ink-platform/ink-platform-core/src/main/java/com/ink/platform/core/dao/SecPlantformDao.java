package com.ink.platform.core.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ink.platform.api.model.SecPlantform;


/**
 * 平台dao处理层
 * @author lww
 *
 */
@Repository("secPlantformDao")
public interface SecPlantformDao {

	/**
	 * 根据主键修改
	 */
	int updateByPrimaryKeySelective(SecPlantform record);
	/**
	 * 根据主键删除
	 */
	int deleteByPrimaryKey(SecPlantform record);
	/**
	 * 详情查询
	 */
	SecPlantform selectByPrimaryKey(Long id);
	/**
	 * 根据id查一条
	 */
	List<SecPlantform> select(Long id);
	/**
	 * 平台添加
	 */
	boolean insertSelective(SecPlantform secPlantform);
	/**
	 * 验重，校验新增条件是否重复
	 * @param map
	 * @return
	 */
	List<SecPlantform> selectByMap(HashMap<Object, Object> map);
	/**
	 * 按条件查询总数
	 * @param map
	 * @return
	 */
	int selectTotal(HashMap<Object, Object> map);
	
}
