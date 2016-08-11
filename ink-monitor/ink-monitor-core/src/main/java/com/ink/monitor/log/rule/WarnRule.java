package com.ink.monitor.log.rule;

import java.util.List;

import org.apache.commons.lang.StringUtils;

public class WarnRule {
	
	private Integer id;
	
	private String systemSource;
	private String moduleCode;
	private String infoCode;

	// 预警间隔
	private Integer warnInterval;
	// 预警阀值
	private Integer warnThreshold;
	// 预警频次
	private Integer warnFrequency;
	// 当前已报警次数
	private Integer currentWarnCount;
	
	private List<String> mobileList;
	
	private List<String> emailList;
	/**
	 * 缓存加载日期，每隔一天自动将当前已报警次数清零
	 */
	private String loadDate;

	public Integer getWarnInterval() {
		return warnInterval;
	}

	public void setWarnInterval(Integer warnInterval) {
		this.warnInterval = warnInterval;
	}

	public Integer getWarnThreshold() {
		return warnThreshold;
	}

	public void setWarnThreshold(Integer warnThreshold) {
		this.warnThreshold = warnThreshold;
	}

	public Integer getWarnFrequency() {
		return warnFrequency;
	}

	public void setWarnFrequency(Integer warnFrequency) {
		this.warnFrequency = warnFrequency;
	}

	public Integer getCurrentWarnCount() {
		if(currentWarnCount == null){
			return 0;
		}
		return currentWarnCount;
	}

	public void setCurrentWarnCount(Integer currentWarnCount) {
		this.currentWarnCount = currentWarnCount;
	}

	public String getSystemSource() {
		return systemSource;
	}

	public void setSystemSource(String systemSource) {
		this.systemSource = systemSource;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getInfoCode() {
		return infoCode;
	}

	public void setInfoCode(String infoCode) {
		this.infoCode = infoCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getRdKey(){
		String key = "monitor-"+systemSource + "-" + moduleCode;
		if(StringUtils.isNotBlank(infoCode)){
			key = "-" + infoCode;
		}
		return key;
	}

	public List<String> getMobileList() {
		return mobileList;
	}

	public void setMobileList(List<String> mobileList) {
		this.mobileList = mobileList;
	}

	public List<String> getEmailList() {
		return emailList;
	}

	public void setEmailList(List<String> emailList) {
		this.emailList = emailList;
	}

	public String getLoadDate() {
		return loadDate;
	}

	public void setLoadDate(String loadDate) {
		this.loadDate = loadDate;
	}
}