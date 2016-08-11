package com.ink.platform.api.service;

import java.util.List;
import java.util.Map;

import com.ink.platform.api.model.SecOrg;
import com.ink.platform.api.model.SecUser;
import com.ink.platform.api.util.PageVO;

/**
 * 用户相关接口
 * @author lww
 *
 */
public interface ISecUserService {

	/**
	 * 用户查询
	 * @param map
	 * @return
	 */
	public SecUser queryUserByMap(Map<Object, Object> map);
	/**
	 * 用户查询
	 * @param secUser
	 * @return
	 */
	public SecUser queryUserByuser(SecUser secUser);

	/**
	 * 分页查询用户
	 * @param map
	 * @return
	 */
	public List<SecUser>  queryUserByPageList(Map<Object, Object> map);
	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	public boolean insertUser(SecUser user);
	/**
	 * 更新用户
	 * @param map
	 * @return
	 */
	public boolean updateUserByMap(SecUser user);

	/**
	 * 组织机构下：人员菜单树
	 * @return
	 */
	SecUser getUserText( String orgId);
	/**
	 * 查询用户所属：所有上级机构
	 * @param long1
	 * @return
	 */
	List<SecUser> selectUserParentOrg(Map<Object, Object> map);
	/**
	 * 查询组织机构下：所有用户信息
	 * @param pagevo
	 * @param orgId
	 * @return
	 */
	public List<SecUser> selectAllUserByOrgList(PageVO<SecOrg> pagevo, String orgId);

	/**
	 * 查询创建人信息
	 * @param secUser
	 * @return
	 */
	public SecUser selectCreatorInfo(SecUser secUser);
	/**
	 * 查询拥有角色属性的个人信息
	 * @param map
	 * @return
	 */
	public List<SecUser> selectUserParentOrgRole(Map<Object, Object> map);
	/**
	 * 查询所有用户
	 * @param map
	 * @return
	 */
	public List<SecUser> selectAllUser(Map<Object, Object> map);
	/**
	 * 查用户总数
	 * @param map
	 * @return
	 */
	public int selectListCount(Map<Object, Object> map);
}
