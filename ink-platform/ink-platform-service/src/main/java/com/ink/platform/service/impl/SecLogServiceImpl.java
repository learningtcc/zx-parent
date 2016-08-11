package com.ink.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.platform.api.model.SecLog;
import com.ink.platform.api.service.ISecLogService;
import com.ink.platform.api.util.PageVO;
import com.ink.platform.api.util.SecurityConstant;
import com.ink.platform.core.dao.SecLogDao;


@Service("secLogService")
public class SecLogServiceImpl implements ISecLogService{
	@Autowired 
	private SecLogDao secLogDao;
//查询操作日志
	@Override
	public List<SecLog> selectList(PageVO<SecLog> pagevo, SecLog secLog) {
		secLog.setDelFlag(SecurityConstant.Ndelete);
		List<SecLog> secLogList = secLogDao.selectList(pagevo, secLog);
        int total = secLogDao.selectListCount(secLog);
        pagevo.setList(secLogList);
        pagevo.setTotal(total);
        return pagevo.getList();
	}
//自动添加操作日志
	@Override
	public void insertSelective(SecLog seclog) {
		secLogDao.insertSelective(seclog);
		
	}


}
