package com.ink.platform.core.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ink.platform.api.model.SecOrg;
import com.ink.platform.api.util.PageVO;

/**
 * 组织机构dao层
 * @author lww
 *
 */
@Repository("secOrgDao")
public interface SecOrgDao {
	/**
	 * @Description 组织机构列表查询
	 * @param SecOrg
	 * @author zhangyuanyang
	 */
	int selectListCount(HashMap<Object, Object> map);
	List<SecOrg> selectList(@Param("pageVO")PageVO<SecOrg> pageVo,SecOrg secOrg);
	/**
	 * 操作添加
	 */
	int insert(SecOrg record);
	/**
	 * 根据主键修改
	 */
	int updateByPrimaryKey(SecOrg record);
	/**
	 * 根据主键删除
	 */
	int deleteByPrimaryKey(String id);
	/**
	 * 详情查询
	 */
	SecOrg selectByPrimaryId(Long id);
	/**
	 * 查询下级机构信息
	 * @param id
	 * @return
	 */
	List<SecOrg> getChildrenModues(@Param("pageVO")PageVO<SecOrg> pageVo,@Param("id")Long id);
	/**
	 * 查询：所有下属机构
	 * @param map
	 * @return
	 */
	SecOrg selectChildOrg(Map<Object, Object> map);
	/**
	 * 查询用户：树
	 * @return
	 */
	List<SecOrg> getOrgUserList();
	/**
	 * 设置副岗
	 * @param userId
	 * @return
	 */
	int addOrgViceJob(String userId);
	/**
	 * 按条件查询
	 * @param map
	 * @return
	 */
	List<SecOrg> selectOrgMsg(Map<Object, Object> map);
	

}
