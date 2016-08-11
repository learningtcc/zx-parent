package com.ink.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.platform.api.model.SecOperation;
import com.ink.platform.api.service.ISecOperationResourceRelationService;
import com.ink.platform.core.dao.SecOperationDao;
import com.ink.platform.core.dao.SecOperationResourceRelationDao;

@Service("secOperationResourceRelationService")
public class SecOperationResourceRelationServiceImpl implements ISecOperationResourceRelationService {

	@Autowired
	private SecOperationResourceRelationDao operationResourceDao;
	@Autowired
	private SecOperationDao  secOperationDao;
	
	@Override
	public boolean addOperation(Map<Object, Object> map) {
		int result =operationResourceDao.addOperation(map);
		return result>0;
	}

	@Override
	public List<SecOperation> selectOperations(HashMap<Object, Object> map) {
		return secOperationDao.selectOperationsByMap(map);
	}

	@Override
	public boolean updateRelation(HashMap<Object, Object> map) {
		return operationResourceDao.updateRelation(map);
	}

}
