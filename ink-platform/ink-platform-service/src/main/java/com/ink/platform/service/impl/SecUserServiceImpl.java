package com.ink.platform.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.platform.api.model.SecOrg;
import com.ink.platform.api.model.SecUser;
import com.ink.platform.api.service.ISecUserService;
import com.ink.platform.api.util.PageVO;
import com.ink.platform.core.dao.SecUserDao;

@Service("secUserService")
public class SecUserServiceImpl implements ISecUserService {
	
	@Autowired
	private  SecUserDao secUserDao;
	@Override
	public SecUser queryUserByMap(Map<Object, Object> map) {
		
		return secUserDao.queryUserByMap(map);
	}

	@Override
	public boolean insertUser(SecUser user) {
		Integer result = secUserDao.insertUser(user);
		return result > 0;
	}

	@Override
	public boolean updateUserByMap(SecUser user) {
		Integer result = secUserDao.updateUserByUser(user);
		
		return result > 0;
	}

	@Override
	public SecUser queryUserByuser(SecUser secUser) {
		return secUserDao.queryUserByuser(secUser);
	}

	@Override
	public SecUser getUserText(String orgId) {
		
		return secUserDao.getUserText(orgId);
	}

	@Override
	public List<SecUser> queryUserByPageList(Map<Object, Object> map) {
		return secUserDao.queryUserByMsg(map);
	}

	@Override
	public List<SecUser> selectUserParentOrg(Map<Object, Object> map) {
		return secUserDao.selectUserParentOrg(map);
	}

	
	@Override
	public List<SecUser> selectAllUserByOrgList(PageVO<SecOrg> pagevo, String orgId) {
		// TODO Auto-generated method stub
		return secUserDao.selectAllUserByOrgList(pagevo,orgId);
	}

	@Override
	public SecUser selectCreatorInfo(SecUser secUser) {
		// TODO Auto-generated method stub
		return secUserDao.selectCreatorInfo(secUser);
	}

	@Override
	public List<SecUser> selectUserParentOrgRole(Map<Object, Object> map) {
		return secUserDao.selectUserParentOrgRole(map);
	}

	@Override
	public List<SecUser> selectAllUser(Map<Object, Object> map) {
		return secUserDao.selectAllUser(map);
	}

	@Override
	public int selectListCount(Map<Object, Object> map) {
		return secUserDao.selectListCount(map);
	}

	
}
