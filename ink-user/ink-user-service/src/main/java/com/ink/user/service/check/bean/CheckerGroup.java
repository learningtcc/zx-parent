package com.ink.user.service.check.bean;

import java.util.List;

import com.ink.user.service.check.api.Checker;

/**
 * 检查器组合，包含一组检查器
 * @author yangchen
 * @date 2016年2月24日 上午10:34:00
 */
public class CheckerGroup {

	private List<Checker> group;

	public List<Checker> getGroup() {
		return group;
	}

	public void setGroup(List<Checker> group) {
		this.group = group;
	}
	
	
}
