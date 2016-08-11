package com.ink.route.dao;

import com.ink.base.EntityDao;
import com.ink.route.api.model.po.BasicBank;

public interface IBasicBankDao extends EntityDao<BasicBank, Long> {
	public int updateNotNull(BasicBank basicBank);
}
