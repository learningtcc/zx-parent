package com.ink.basic.core.dao;


import com.ink.base.EntityDao;
import com.ink.basic.core.po.BasicResCode;


public interface IBasicResCodeDao extends EntityDao<BasicResCode, Long> {
	public int updateNotNull(BasicResCode basicResCode);
}