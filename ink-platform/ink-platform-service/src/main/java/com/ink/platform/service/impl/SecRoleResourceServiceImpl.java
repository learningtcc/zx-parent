package com.ink.platform.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.platform.api.model.SecRoleResourceRelation;
import com.ink.platform.api.service.ISecRoleResourceService;
import com.ink.platform.core.dao.SecRoleResourceRelationDao;

@Service("secRoleResourceService")
public class SecRoleResourceServiceImpl implements ISecRoleResourceService {

	@Autowired
	private SecRoleResourceRelationDao  roleResourceRelationDao;
	
	@Override
	public List<SecRoleResourceRelation> getRoleResourceList(String roleId) {
		
		return roleResourceRelationDao.getRoleResourceList(Long.parseLong(roleId)) ;
	}

	}
