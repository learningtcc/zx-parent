package com.ink.user.service.check.api;


public interface CheckChainManageService {
	/**
	 * 根据交易代码获取检查链的第一个容器
	 * @param txnCode
	 * @return
	 */
	public CheckerContainer getCheckerContainer(String txnCode);
}
