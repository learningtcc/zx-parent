package com.ink.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.platform.api.model.SecOrg;
import com.ink.platform.api.service.ISecOrgService;
import com.ink.platform.api.util.PageVO;
import com.ink.platform.core.dao.SecOrgDao;

@Service("secOrgService")
public class SecOrgServiceImpl implements ISecOrgService {

	@Autowired
	private SecOrgDao secOrgDao;
	@Override
	public List<SecOrg> selectList(PageVO<SecOrg> pagevo, SecOrg secOrg) {
		
		
		return secOrgDao.selectList(pagevo, secOrg);
	}

	@Override
	public boolean insertSelective(SecOrg secOrg) {
	    int  result = secOrgDao.insert(secOrg);
		return result >0;
	}

	@Override
	public boolean updateByPrimaryKeySelective(SecOrg secOrg) {
		int  result = secOrgDao.updateByPrimaryKey(secOrg);
		return result>0;
	}

	@Override
	public boolean deleteByPrimaryKey(String id) {
		int  result = secOrgDao.deleteByPrimaryKey(id);
		return result>0;
	}
	
	@Override
	public SecOrg selectByPrimaryKey(Long id) {
		return secOrgDao.selectByPrimaryId(id);
	}

	@Override
	public List<SecOrg> selectOrgInfoById(PageVO<SecOrg> pagevo, Long id) {
		return secOrgDao.getChildrenModues(pagevo, id);
	}

	@Override
	public List<SecOrg> getOrgUserList() {
		return secOrgDao.getOrgUserList();
	}

	@Override
	public boolean addOrgViceJob(String userId) {
		int result =secOrgDao.addOrgViceJob(userId);
		return result>0;
	}

	@Override
	public List<SecOrg> selectOrgMsg(Map<Object, Object> map) {
		
		return secOrgDao.selectOrgMsg(map);
	}

	@Override
	public int selectListCount(HashMap<Object, Object> map) {
		return secOrgDao.selectListCount(map);
	}

	@Override
	public SecOrg selectChildOrg(Map<Object, Object> maps) {
		return secOrgDao.selectChildOrg(maps);
	}

}
