package com.ink.user.service.check.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ink.user.service.check.api.CheckChainManageService;
import com.ink.user.service.check.api.CheckerContainer;
import com.ink.user.service.check.bean.CheckerGroup;
import com.ink.user.service.check.bean.CheckerGroupLink;
import com.ink.user.service.check.api.Checker;

/**
 * 检查链的管理注册器
 * @author yangchen
 * @date 2016年2月24日 下午1:39:36
 */
public class CheckChainManager implements CheckChainManageService {
	
	private Map<String ,CheckerGroupLink> checkerGroupLinkMap;

	private Map<String, CheckerContainer> map = new HashMap<String, CheckerContainer>();

	public void setCheckerGroupLinkMap(Map<String, CheckerGroupLink> checkerGroupLinkMap) {
		this.checkerGroupLinkMap = checkerGroupLinkMap;
	}
	
	/**
	 * 根据交易代码获取检查链的第一个容器
	 * @param txnCode
	 * @return
	 */
	public CheckerContainer getCheckerContainer(String txnCode){
		if( map.containsValue(txnCode)){
			return map.get(txnCode);
		}else{
			CheckerContainer container = initContainer(txnCode);
			return container;
		}
	}

	/**
	 * 初始化一个容器
	 * @param txnCode
	 * @return
	 */
	private CheckerContainer initContainer(String txnCode) {
		CheckerGroupLink link = checkerGroupLinkMap.get(txnCode);
		CheckerContainer container = new CheckerContainer();
		CheckerContainer currentContainer = container;
		for(CheckerGroup group : link.getGroups()){
			List<Checker> list = group.getGroup();
			for( int i = 0; i < list.size()  ; i++){
				currentContainer.setChecker(list.get(i));
				currentContainer.setNext(new CheckerContainer());
				currentContainer = currentContainer.getNext();
			}
		}
		return container;
	}
	
	
}
