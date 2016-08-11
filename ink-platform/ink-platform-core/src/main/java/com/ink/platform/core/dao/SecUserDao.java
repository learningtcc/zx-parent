package com.ink.platform.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ink.platform.api.model.SecOrg;
import com.ink.platform.api.model.SecUser;
import com.ink.platform.api.util.PageVO;

/**
 * 用户dao处理层
 * @author lww
 *
 */
@Repository("secUserDao")
public interface SecUserDao {

	/**
	 * 用户查询
	 * @param map
	 * @return
	 */
	 public SecUser queryUserByMap(Map<Object, Object> map);
	 /**
	  * 查询用户信息
	  * @param secUser
	  * @return
	  */
	 public SecUser queryUserByuser(SecUser secUser);
	 /**
	  * 新增用户
	  * @param user
	  * @return
	  */
	 public Integer insertUser(SecUser user);
	/**
	 * 更新用户
	 * @param map
	 * @return
	 */
	public Integer updateUserByUser(SecUser user);
	/**
	 * 按条件查询用户总数
	 * @param map
	 * @return
	 */
	int selectListCount(Map<Object, Object> map);
	
	/**
	 * 组织机构下：人员菜单树
	 * @return
	 */
	SecUser getUserText( String orgId);
	/**
	 * 查询用户所属上级机构信息
	 * @param map
	 * @return
	 */
	List<SecUser> selectUserParentOrg(Map<Object, Object> map);
	/**
	 * 查询所有用户信息
	 * @param pagevo
	 * @param orgId
	 * @return
	 */
	public List<SecUser> selectAllUserByOrgList(@Param("pageVO")PageVO<SecOrg> pagevo, @Param("orgId")String orgId);
	/**
	 * 创建人信息
	 * @param secUser
	 * @return
	 */
	public SecUser selectCreatorInfo(SecUser secUser);
	/**
	 * 查询用户角色，组织，基本信息
	 * @param map
	 * @return
	 */
	public List<SecUser> selectUserParentOrgRole(Map<Object, Object> map);
	/**
	 * 按条件查询用户
	 * @param map
	 * @return
	 */
	public List<SecUser> queryUserByMsg(Map<Object, Object> map);
	/**
	 * 查询所有用户
	 * @param map
	 * @return
	 */
	public List<SecUser> selectAllUser(Map<Object, Object> map);
}
