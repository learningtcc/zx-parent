package com.ink.basic.core.dao;

import com.ink.base.EntityDao;
import com.ink.basic.core.po.BasicBank;

public interface IBasicBankDao extends EntityDao<BasicBank, Long> {
	public int updateNotNull(BasicBank basicBank);
}
