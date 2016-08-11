/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.service.impl;

import com.ink.monitor.core.dao.ISystermInfoDao;
import com.ink.monitor.core.po.SystermInfo;
import com.ink.monitor.core.query.MonitorUserRuleQuery;
import com.ink.monitor.core.query.SystermInfoQuery;
import com.ink.monitor.log.rule.MonitorPolicy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ink.base.BaseManager;
import com.ink.base.EntityDao;
import com.ink.monitor.core.po.MonitorUserRule;
import com.ink.monitor.core.service.IMonitorUserRuleManager;
import com.ink.monitor.core.dao.IMonitorUserRuleDao;

import java.util.*;


/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 
@Service("monitorUserRuleManager")
@Transactional
public class MonitorUserRuleManagerImpl extends BaseManager<MonitorUserRule,Integer> implements IMonitorUserRuleManager{

	@Autowired
	private IMonitorUserRuleDao monitorUserRuleDao;
	@Autowired
	private ISystermInfoDao systermInfoDao;
	@Autowired
	private MonitorPolicy monitorPolicy;

	public EntityDao<MonitorUserRule, Integer> getEntityDao() {
		return this.monitorUserRuleDao;
	}

	@Override
	public List findMonitorInfoTree(MonitorUserRule monitorUserRule) throws Exception {
		return monitorUserRuleDao.findMonitorInfoTree( monitorUserRule);
	}

	/**
	 * 保存用户前端选择监控数据
	 *
	 * @param monitorUserRule
	 * @return
	 * @throws Exception
	 */
	@Override
	public int saveMonitorUserRule(MonitorUserRule monitorUserRule) throws Exception {
		//1先删除当前用户所有的监控数据
		MonitorUserRuleQuery userRuleQuery = new MonitorUserRuleQuery();
		userRuleQuery.setUserId(monitorUserRule.getUserId());
		List<MonitorUserRule> orgUserRuleList = monitorUserRuleDao.find(userRuleQuery);
		MonitorUserRule userRule = new MonitorUserRule();
		userRule.setUserId(monitorUserRule.getUserId());
		monitorUserRuleDao.deleteMonitorUserRuleByUserId(userRule);

		String[] infoCodes = null;
		String[] moduleCodes = null;
		if (StringUtils.isBlank(monitorUserRule.getInfoCode()))//检查是否原则了业务模块
			infoCodes = new String[0];
		else
			infoCodes = monitorUserRule.getInfoCode().substring(1).split(";");

		if (StringUtils.isBlank(monitorUserRule.getInfoCode()))//检查是否选择了功能模块
			moduleCodes = new String[0];
		else
			moduleCodes = monitorUserRule.getModuleCode().substring(1).split(";");

		List<MonitorUserRule> monitorUserRuleList = new ArrayList<MonitorUserRule>();
		int num = 0 ;
		if (moduleCodes.length != 0 ){//用户没有选择数据
			MonitorUserRule saveInfo;
			for (int i = 0; i < moduleCodes.length; i++){//循环取得选中模块
				String[] checkModuleCode = moduleCodes[i].split(",");
				int moduleCount = 0;
				List<MonitorUserRule> infoCodeList = new ArrayList<MonitorUserRule>();
				for (int j = 0; j < infoCodes.length; j++) {//用户选中业务
					String[] checkInfoCode = infoCodes[j].split(",");
					if(checkInfoCode[1].equals(checkModuleCode[1])){
						moduleCount ++;
						saveInfo = new MonitorUserRule();
						saveInfo.setInfoCode(checkInfoCode[2]);
						saveInfo.setModuleCode(checkInfoCode[1]);
						saveInfo.setSysCode(checkInfoCode[0]);
						saveInfo.setUserId(monitorUserRule.getUserId());
						infoCodeList.add(saveInfo);
					}
				}
				if(moduleCount == Integer.parseInt(checkModuleCode[2])||infoCodeList.isEmpty()){//当全选模块内容时，只存储模块信息
					saveInfo = new MonitorUserRule();
					saveInfo.setModuleCode(checkModuleCode[1]);
					saveInfo.setSysCode(checkModuleCode[0]);
					saveInfo.setUserId(monitorUserRule.getUserId());
					monitorUserRuleList.add(saveInfo);
				}else{
					monitorUserRuleList.addAll(infoCodeList);
				}
			}
			//2插入用户页面选择数据
			num = monitorUserRuleDao.saveBatch(monitorUserRuleList);
		}

		Set<MonitorUserRule> monitorUserRuleSet  = new HashSet<MonitorUserRule>();
		monitorUserRuleSet.addAll(monitorUserRuleList);
		monitorUserRuleSet.addAll(orgUserRuleList);

		Set<String> cacheSet =  new HashSet<String>();
		//3更新redis缓存
		for (MonitorUserRule ruleInfo : monitorUserRuleSet) {
			String key = ruleInfo.getSysCode() + ruleInfo.getModuleCode() + ruleInfo.getInfoCode();
			if (cacheSet.contains(key)){
				continue;
			}
			cacheSet.add(key);
			if (StringUtils.isBlank(ruleInfo.getInfoCode())){//缓存功能模块时，把其下所有业务都缓存一次
				SystermInfoQuery infoQuery = new SystermInfoQuery();
				infoQuery.setSystermCode(ruleInfo.getSysCode());
				infoQuery.setModuleCode(ruleInfo.getModuleCode());
				List<SystermInfo> systermInfoLsit = systermInfoDao.find(infoQuery);
				for (SystermInfo systermInfo : systermInfoLsit){
					monitorPolicy.saveWarnRule(systermInfo.getSystermCode(),systermInfo.getModuleCode(),
							systermInfo.getCode(),"0",1);
				}
			}

			monitorPolicy.saveWarnRule(ruleInfo.getSysCode(),ruleInfo.getModuleCode(),
					ruleInfo.getInfoCode(),"0",1);

		}
		return num;
	}

	@Override
	public int deleteMonitorUserRule(MonitorUserRule monitorUserRule) {
		return monitorUserRuleDao.deleteMonitorUserRule(monitorUserRule);
	}
}
