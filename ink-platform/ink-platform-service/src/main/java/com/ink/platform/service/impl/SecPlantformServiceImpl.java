package com.ink.platform.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.platform.api.model.SecPlantform;
import com.ink.platform.api.service.ISecPlantformService;
import com.ink.platform.api.util.SecurityConstant;
import com.ink.platform.core.dao.SecPlantformDao;

@Service("secPlantformService")
public class SecPlantformServiceImpl implements ISecPlantformService {
	@Autowired
	private SecPlantformDao secPlantformDao ;
	@Override
	public List<SecPlantform> select(Long id) {
		List<SecPlantform> secPlantform=secPlantformDao.select(id);
		return secPlantform;
	}
	@Override
	public boolean insertSelective(SecPlantform secPlantform) {
		return secPlantformDao.insertSelective(secPlantform);
	}
	@Override
	public boolean deleteByPrimaryKey(SecPlantform record) {
		int count = secPlantformDao.deleteByPrimaryKey(record);
		record.setDelFlag(SecurityConstant.Ndelete);
		return count > 0;
	}
	@Override
	public boolean updateByPrimaryKeySelective(SecPlantform record) {
		int count = secPlantformDao.updateByPrimaryKeySelective(record);
		record.setDelFlag(SecurityConstant.Ndelete);
		return count > 0;
	}
	@Override
	public SecPlantform selectPlantDetail(Long id) {
		return secPlantformDao.selectByPrimaryKey(id);
	}
	@Override
	public List<SecPlantform> selectByMap(HashMap<Object, Object> map) {
		return secPlantformDao.selectByMap(map);
		
	}
	@Override
	public int selectTotal(HashMap<Object, Object> map) {
		return secPlantformDao.selectTotal(map);
	}

}
