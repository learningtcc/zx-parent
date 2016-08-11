package com.ink.platform.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ink.platform.api.model.SecOrg;
import com.ink.platform.api.util.PageVO;


/**
 * 组织机构接口
 * @author lww
 *
 */
public interface ISecOrgService {
	
	/***
	 * 查询组织机构列表
	 * @param pagevo
	 * @param secOrg
	 * @return
	 */
	List<SecOrg> selectList(PageVO<SecOrg> pagevo, SecOrg secOrg);

	/**
	 * 新增组织机构
	 * @param secOrg
	 * @return
	 */
	boolean insertSelective(SecOrg secOrg);

	/**
	 * 更新组织机构
	 * @param secOrg
	 * @return
	 */
	boolean updateByPrimaryKeySelective(SecOrg secOrg);

	/**
	 * 删除组织机构
	 * @param id
	 * @return
	 */
	boolean deleteByPrimaryKey(String id);
	/**
	 * 详情查询
	 */
	SecOrg selectByPrimaryKey(Long id);
	/**
	 * 查询下级机构信息
	 * @param id
	 * @return
	 */
	List<SecOrg> selectOrgInfoById(PageVO<SecOrg> pagevo, Long id);

	/**
	 * 查询组织机构下用户树
	 * @return
	 */
	List<SecOrg> getOrgUserList();
	/**
	 * 设置副岗
	 * @param userId
	 * @return
	 */
	boolean addOrgViceJob(String userId);

	/**
	 * 按名称查询组织信息
	 * @param map
	 * @return
	 */
	List<SecOrg> selectOrgMsg(Map<Object, Object> map);

	/**
	 * 查询总数
	 * @param map
	 * @return
	 */
	int selectListCount(HashMap<Object, Object> map);

	/**
	 * 查询某组织机构：下所有组织
	 * @param maps
	 * @return
	 */
	SecOrg selectChildOrg(Map<Object, Object> maps);
 

}
