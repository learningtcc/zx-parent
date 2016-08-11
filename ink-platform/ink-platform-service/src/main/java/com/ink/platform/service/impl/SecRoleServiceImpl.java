package com.ink.platform.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.platform.api.model.SecRole;
import com.ink.platform.api.model.SecRoleResourceRelation;
import com.ink.platform.api.service.ISecRoleService;
import com.ink.platform.api.util.PageVO;
import com.ink.platform.api.util.SecurityConstant;
import com.ink.platform.core.dao.SecRoleDao;
import com.ink.platform.core.dao.SecRoleResourceRelationDao;

@Service("secRoleService")
public class SecRoleServiceImpl implements ISecRoleService{
	
	@Autowired 
	private SecRoleDao secRoleDao;
	@Autowired 
	private SecRoleResourceRelationDao secRoleResourceRelationDao;

	@Override
	public List<SecRole> selectList(PageVO<SecRole> pagevo, SecRole secRole) {
		secRole.setDelFlag(SecurityConstant.Ndelete);
		List<SecRole> secRoleList = secRoleDao.selectList(pagevo, secRole);
        int total = secRoleDao.selectListCount(secRole);
        pagevo.setList(secRoleList);
        pagevo.setTotal(total);
        return pagevo.getList();
	}

	
	@Override
	public boolean updateByPrimaryKeySelective(SecRole record) {
		int count = secRoleDao.updateByPrimaryKeySelective(record);
		record.setDelFlag(SecurityConstant.Ndelete);
		return count > 0;
	}

	@Override
	public boolean deleteByPrimaryKey(SecRole secRole) {
		Long id = secRole.getId();
		int count = secRoleDao.deleteByPrimaryKey(id);
		return count > 0;
	}


	@Override
	public boolean insertSelective(SecRole record) {
		int count= secRoleDao.insertSelective(record);
		return count > 0;
	}

	@Override
	public boolean roleAuth(SecRoleResourceRelation secRoleResourceRelation) {
		int count= secRoleResourceRelationDao.empower(secRoleResourceRelation);
		return count > 0;
	}


	@Override
	public List<SecRole> selectUserRole(Map<Object, Object> map) {
		
		return secRoleDao.selectUserRole(map);
	}

	@Override
	public void deleteByRoleId(Long roleId) {
		secRoleResourceRelationDao.deleteByRoleId(roleId);
	}


	@Override
	public List<SecRole> selectAllRole() {
		return secRoleDao.selectAllRole();
	}


	@Override
	public SecRole selectRoleById(String roleId) {
		return secRoleDao.selectRoleById(roleId);
	}


	@Override
	public List<SecRole> selecRoleByMap(HashMap<Object, Object> map) {
		return secRoleDao.selecRoleByMap(map);
	}

}
