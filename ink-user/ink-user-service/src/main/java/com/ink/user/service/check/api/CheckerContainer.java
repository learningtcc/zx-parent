package com.ink.user.service.check.api;


/**
 * 装检查其的容器
 * @author yangchen
 * @date 2016年2月23日 下午7:20:18
 */
public class CheckerContainer {
	
	private CheckerContainer next;
	private Checker checker;
	
	public CheckerContainer next(){
		return next;
	}
	
	public Checker getChecker(){
		return checker;
	}

	public CheckerContainer getNext() {
		return next;
	}

	public void setNext(CheckerContainer next) {
		this.next = next;
	}

	public void setChecker(Checker checker) {
		this.checker = checker;
	}
	
	
}
