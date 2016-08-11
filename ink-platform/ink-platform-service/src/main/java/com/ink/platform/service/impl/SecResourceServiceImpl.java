package com.ink.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.platform.api.model.SecResource;
import com.ink.platform.api.service.ISecResourceService;
import com.ink.platform.api.util.SecurityConstant;
import com.ink.platform.core.dao.SecResourceDao;

@Service("secResourceService")
public class SecResourceServiceImpl implements ISecResourceService {

	@Autowired
	private SecResourceDao  secResourceDao;


	@Override
	public List<SecResource> getResourceList(Map<Object,Object> map) {
		return secResourceDao.getResourceList( map);
	}
	@Override
	public SecResource selectByPrimaryKey(Long id) {
		return secResourceDao.selectByPrimaryKey(id);
	}
	@Override
	public List<SecResource> resourceTree() {
		return secResourceDao.resourceTree();
	}
	@Override
	public List<SecResource> selectResource(SecResource resource) {
		return secResourceDao.selectResource(resource);
	}
	@Override
	public boolean insertSelective(SecResource record) {
		int count= secResourceDao.insertSelective(record);
		return count > 0;
	}
	@Override
	public boolean deleteByPrimaryKey(SecResource record) {
		int count = secResourceDao.deleteByPrimaryKey(record);
		record.setDelFlag(SecurityConstant.Ndelete);
		return count > 0;
	}
	@Override
	public boolean updateByPrimaryKeySelective(SecResource record) {
		int count = secResourceDao.updateByPrimaryKeySelective(record);
		record.setDelFlag(SecurityConstant.Ndelete);
		return count > 0;
	}
	@Override
	public List<SecResource> selectAllResource() {
		
		return secResourceDao.selectAllResource();
	}
	@Override
	public List<SecResource> selectLowResource(String parentId) {
		return secResourceDao.getChildrenModues(parentId);
	}
	@Override
	public List<SecResource> selectResourceByMap(HashMap<Object, Object> map) {
		return secResourceDao.selectResourceByMap(map);
	}
	@Override
	public List<SecResource> getRoleResourceList(Map<Object, Object> roleMap) {
		
		return secResourceDao.getRoleResourceList(roleMap);
	}
	@Override
	public int selectListCount(HashMap<Object, Object> map) {
		return secResourceDao.selectListCount(map);
	}
	/*@Override
	public List<SecResource> getResourceAndOperation(String userId) {
		return secResourceDao.getResourceAndOperation(userId);
	}*/
}
