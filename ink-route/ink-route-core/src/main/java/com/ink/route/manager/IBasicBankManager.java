package com.ink.route.manager;

import com.ink.base.IBaseManager;
import com.ink.route.api.model.po.BasicBank;

public interface IBasicBankManager extends IBaseManager<BasicBank, Long> {
	public int updateNotNull(BasicBank basicBank);
}
