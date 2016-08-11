package com.ink.basic.core.manager;

import com.ink.base.IBaseManager;
import com.ink.basic.core.po.BasicBank;

public interface IBasicBankManager extends IBaseManager<BasicBank, Long> {
	public int updateNotNull(BasicBank basicBank);
}
