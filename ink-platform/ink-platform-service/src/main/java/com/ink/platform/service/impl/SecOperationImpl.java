package com.ink.platform.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.platform.api.model.SecOperation;
import com.ink.platform.api.service.ISecOperationService;
import com.ink.platform.api.util.PageVO;
import com.ink.platform.api.util.SecurityConstant;
import com.ink.platform.core.dao.SecOperationDao;

@Service("secOperationService")
public class SecOperationImpl implements ISecOperationService{
	@Autowired 
	private SecOperationDao operationDao;
	@Override
	public List<SecOperation> selectList(PageVO<SecOperation> pagevo,SecOperation secOperation) {
		secOperation.setDelFlag(SecurityConstant.Ndelete);
		List<SecOperation> secRoleList = operationDao.selectList(pagevo, secOperation);
        int total = operationDao.selectListCount(secOperation);
        pagevo.setList(secRoleList);
        pagevo.setTotal(total);
        return pagevo.getList();
	}

	@Override
	public boolean insertSelective(SecOperation record) {
		int count= operationDao.insertSelective(record);
		return count > 0;
	}

	@Override
	public boolean updateByPrimaryKeySelective(SecOperation record) {
		int count = operationDao.updateByPrimaryKeySelective(record);
		record.setDelFlag(SecurityConstant.Ndelete);
		return count > 0;
	}

	@Override
	public boolean deleteByPrimaryKey(SecOperation secOperation) {
		Long id = secOperation.getId();
		int count = operationDao.deleteByPrimaryKey(id);
		return count > 0;
	}

	@Override
	public List<SecOperation> selectAllOperatin(HashMap<Object, Object> map) {
		
		return operationDao.selectAllOperatin(map);
	}

	@Override
	public SecOperation selectOperationDetail(String operationId) {
		return operationDao.selectByPrimaryKey(operationId);
	}

}
