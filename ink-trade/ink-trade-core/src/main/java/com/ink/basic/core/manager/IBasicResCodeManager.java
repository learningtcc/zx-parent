package com.ink.basic.core.manager;


import com.ink.base.IBaseManager;
import com.ink.basic.core.po.BasicResCode;

public interface IBasicResCodeManager extends IBaseManager<BasicResCode, Long> {
	public int updateNotNull(BasicResCode basicResCode);
}
