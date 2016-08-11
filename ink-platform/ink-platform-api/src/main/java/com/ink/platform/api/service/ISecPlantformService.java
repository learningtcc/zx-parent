package com.ink.platform.api.service;

import java.util.HashMap;
import java.util.List;

import com.ink.platform.api.model.SecPlantform;


/**
 * 平台管理接口
 * @author lww
 *
 */
public interface ISecPlantformService {
	
	/**
	 * 平台列表
	 * @param id
	 * @return
	 */
	List<SecPlantform> select(Long id);

	/**
	 * 新增平台
	 * @param secPlantform
	 * @return
	 */
	boolean insertSelective(SecPlantform secPlantform);

	/**
	 * 删除某平台
	 * @param secPlantform
	 * @return
	 */
	boolean deleteByPrimaryKey(SecPlantform secPlantform);

	/**
	 * 更新平台信息
	 * @param secPlantform
	 * @return
	 */
	boolean updateByPrimaryKeySelective(SecPlantform secPlantform);

	/**
	 * 平台详情
	 * @param id
	 * @return
	 */
	SecPlantform selectPlantDetail(Long id);

	/**
	 * 验重，校验平台名称，标识是否已被使用
	 * @param map
	 * @return
	 */
	List<SecPlantform> selectByMap(HashMap<Object, Object> map);

	/**
	 * 平台总数
	 * @param map
	 * @return
	 */
	int selectTotal(HashMap<Object, Object> map);

}
