package com.ink.monitor.log.rule;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ink.monitor.core.po.MonitorInfoRule;
import com.ink.monitor.core.po.MonitorModuleRule;
import com.ink.monitor.core.query.MonitorInfoRuleQuery;
import com.ink.monitor.core.query.MonitorModuleRuleQuery;
import com.ink.monitor.core.service.IMonitorInfoRuleManager;
import com.ink.monitor.core.service.IMonitorModuleRuleManager;
import com.ink.monitor.core.service.IMonitorUserManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;
import com.ink.base.utils.dateUtil.DateFormat;
import com.ink.base.utils.dateUtil.DateUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Repository("monitorPolicy")
public class MonitorPolicy {

	@Autowired
	private JedisPool jedisPool;
	@Autowired
	private IMonitorInfoRuleManager monitorInfoRuleManager;
	@Autowired
	private IMonitorModuleRuleManager monitorModuleRuleManager;
	@Autowired
	private IMonitorUserManager monitorUserManager;

	/**
	 * 获取当前分钟内的报错次数
	 * @param sysCode
	 * @return
	 */
	public int getErrorCount(String sysCode){
		
		Jedis jedis = jedisPool.getResource();
		try{
			String logDate = getDateMinute(new Date());

			String key = "monitor-"+sysCode + ":" + logDate;
			
			String value = jedis.get(key);
			
			if(StringUtils.isBlank(value)){
				return 0;
			}
			
			return Integer.valueOf(jedis.get(key));
			
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			jedisPool.returnResource(jedis);
		}
		
		return 0;
		
	}
	
	/**
	 * 获取预警规则
	 * @param key
	 * @return
	 */
	public String getWarnRule(Jedis jedis,String sysCode,String moduleCode, String infoCode, String paramKey){
		
		String result = "";
		
		HashMap<String, String> warnMap = null;
		
		boolean isInfo = false;
		
		String key = "monitor-"+sysCode + ":" + moduleCode;
		if(StringUtils.isNotBlank(infoCode)){
			key += ":" + infoCode;
			isInfo = true;
		}
		
		Date now = new Date();
		String nowDateStr = DateUtil.formatToYYYYMMDD(now);
		
		if(jedis.exists(key)){
			
			if(!paramKey.equalsIgnoreCase(WarnConstant.CURRENT_WARN_COUNT)){
				//不为当前已报警数时可直接返回
				result = jedis.hget(key, paramKey);
			}else{
				//若取当前已报警数，判断报警时间是否发生变化，若改变则重新计数
				String errorDate =  jedis.hget(key, WarnConstant.ERROR_DATE);
				
				if(StringUtils.isBlank(errorDate) || !nowDateStr.equals(errorDate.substring(0, 10))){
					jedis.hset(key, WarnConstant.CURRENT_WARN_COUNT, "0");
					result = "0";
				}else{
					result = jedis.hget(key, WarnConstant.CURRENT_WARN_COUNT);
				}
			}
			
		}else{
			
			warnMap = new HashMap<String, String>();
			
			queryWarnRuleFromDb(sysCode, moduleCode, infoCode, warnMap, isInfo, nowDateStr);
			
			jedis.hmset(key, warnMap);
			
			result = warnMap.get(paramKey);
		}
		
		return result;
	}
	
	/**
	 * 获取预警规则
	 * @param key
	 * @return
	 */
	public Map<String, String> getWarnRule(Jedis jedis ,String sysCode,String moduleCode, String infoCode){
		
		Map<String, String> warnMap = null;
		
		boolean isInfo = false;
		
		String key = "monitor-"+sysCode + ":" + moduleCode;
		if(StringUtils.isNotBlank(infoCode)){
			key += ":" + infoCode;
			isInfo = true;
		}
		
		Date now = new Date();
		String nowDateStr = DateUtil.formatToYYYYMMDD(now);
		
		if(jedis.exists(key)){
			warnMap = jedis.hgetAll(key);
		}else{
			warnMap = new HashMap<String, String>();
			queryWarnRuleFromDb(sysCode, moduleCode, infoCode, warnMap, isInfo, nowDateStr);
			warnMap.put(WarnConstant.RD_KEY, key);
			jedis.hmset(key, warnMap);

		}
		
		return warnMap;
	}
	
	/**
	 * 设置监控策略
	 * 为新增时 则直接插进行缓存,
	 * @param sysCode 平台系统码
	 * @param moduleCode 功能模块码
	 * @param infoCode 业务模块码
	 * @param status 状态 0为1正常 1停用
	 * @param saveFlag 是否新增
	 */
	public void saveWarnRule(String sysCode,String moduleCode,String infoCode,String status, int saveFlag){

		Jedis jedis = jedisPool.getResource();
		try{
			Map<String, String> warnMap = new HashMap<String,String>();

			String key = "monitor-"+sysCode + ":" + moduleCode;
			boolean isInfo = false;
			if(StringUtils.isNotBlank(infoCode)){
				key += ":" + infoCode;
				isInfo = true;
			}

			Date now = new Date();
			String nowDateStr = DateUtil.formatToYYYYMMDD(now);

			if (0 == saveFlag){//新增则直接进行缓存
				queryWarnRuleFromDb(sysCode,moduleCode,infoCode,warnMap,isInfo,nowDateStr);
				warnMap.put(WarnConstant.RD_KEY, key);
				jedis.hmset(key, warnMap);
			}else{
				if (jedis.exists(key)){
					jedis.del(key);
				}
				if (1 == saveFlag){//更新
					if ("1".equals(status)){//停用则设置为null
						warnMap.put(WarnConstant.RD_KEY, key);
						jedis.hmset(key, warnMap);
					}else if ("0".equals(status)){//正常则检查缓存，如存在且且不为null则不做操作，负责重新缓存
						queryWarnRuleFromDb(sysCode,moduleCode,infoCode,warnMap,isInfo,nowDateStr);
						warnMap.put(WarnConstant.RD_KEY, key);
						jedis.hmset(key, warnMap);
					}
				}

			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			jedisPool.returnResource(jedis);
		}

	}

	/**
	 * 从数据库中查询预警规则
	 * @param sysCode
	 * @param moduleCode
	 * @param infoCode
	 * @param warnMap
	 * @param isInfo
	 * @param nowDateStr
	 */
	private void queryWarnRuleFromDb(String sysCode, String moduleCode, String infoCode, Map<String, String> warnMap,
			boolean isInfo, String nowDateStr) {
		//查询数据
		if(isInfo){
			MonitorInfoRuleQuery query = new MonitorInfoRuleQuery();
			query.setSysCode(sysCode);
			query.setModuleCode(moduleCode);
			query.setInfoCode(infoCode);
			query.setStatus("0");
			List<Map<String,Object>> infoRuleList = monitorInfoRuleManager.queryMonitorInfo(query);
			if(infoRuleList != null && infoRuleList.size() > 0){
				Map<String,Object> infoRule = infoRuleList.get(0);
				
				warnMap.put(WarnConstant.ID, String.valueOf(infoRule.get("id")));
				warnMap.put(WarnConstant.SYS_CODE, String.valueOf(infoRule.get("sysCode")));
				warnMap.put(WarnConstant.MODULE_CODE, String.valueOf(infoRule.get("moduleCode")));
				warnMap.put(WarnConstant.INFO_CODE, String.valueOf(infoRule.get("infoCode")));
				warnMap.put(WarnConstant.SYS_NAME, String.valueOf(infoRule.get("sysName")));
				warnMap.put(WarnConstant.MODULE_NAME, String.valueOf(infoRule.get("moduleName")));
				warnMap.put(WarnConstant.INFO_NAME, String.valueOf(infoRule.get("infoName")));
				if(infoRule.get("currentWarnCount") == null){
					warnMap.put(WarnConstant.CURRENT_WARN_COUNT, "0");
				}else{
					warnMap.put(WarnConstant.CURRENT_WARN_COUNT, String.valueOf(infoRule.get("currentWarnCount")));
				}
				warnMap.put(WarnConstant.WARN_FREQUENCY, String.valueOf(infoRule.get("warnFrequency")));
				warnMap.put(WarnConstant.WARN_INTERVAL, String.valueOf(infoRule.get("warnInterval")));
				warnMap.put(WarnConstant.WARN_THRESHOLD, String.valueOf(infoRule.get("warnThreshold")));
			}
		}else{
			MonitorModuleRuleQuery query = new MonitorModuleRuleQuery();
			query.setSysCode(sysCode);
			query.setModuleCode(moduleCode);
			query.setStatus("0");
			List<Map<String,Object>> moduleRuleList = monitorModuleRuleManager.queryMonitorInfo(query);
			if(moduleRuleList != null && moduleRuleList.size() > 0){
				Map<String,Object> moduleRule = moduleRuleList.get(0);
				
				warnMap.put(WarnConstant.ID, String.valueOf(moduleRule.get("id")));
				warnMap.put(WarnConstant.SYS_CODE, String.valueOf(moduleRule.get("sysCode")));
				warnMap.put(WarnConstant.MODULE_CODE, String.valueOf(moduleRule.get("moduleCode")));
				warnMap.put(WarnConstant.SYS_NAME, String.valueOf(moduleRule.get("sysName")));
				warnMap.put(WarnConstant.MODULE_NAME, String.valueOf(moduleRule.get("moduleName")));
				if(moduleRule.get("currentWarnCount") == null){
					warnMap.put(WarnConstant.CURRENT_WARN_COUNT, "0");
				}else{
					warnMap.put(WarnConstant.CURRENT_WARN_COUNT, String.valueOf(moduleRule.get("currentWarnCount")));
				}
				warnMap.put(WarnConstant.WARN_FREQUENCY, String.valueOf(moduleRule.get("warnFrequency")));
				warnMap.put(WarnConstant.WARN_INTERVAL, String.valueOf(moduleRule.get("warnInterval")));
				warnMap.put(WarnConstant.WARN_THRESHOLD, String.valueOf(moduleRule.get("warnThreshold")));
				
			}
		}
		
		setMonitorEmailAndMobile(sysCode, moduleCode, infoCode, warnMap);
	}

	/**
	 * 设置监控功能的用户邮箱和手机
	 * @param systemSource
	 * @param module
	 * @param infoCode
	 * @param rule
	 */
	private void setMonitorEmailAndMobile(String systemSource, String module, String infoCode, Map<String, String> warnMap) {
		//人员监控
		List<String> emailList = new ArrayList<String>();
		List<String> mobileList = new ArrayList<String>();
		
		List<HashMap<String, String>> userList = monitorUserManager.queryUserByPolicy(systemSource, module);
		
		for (HashMap<String, String> map : userList) {
			
			String infoCodeTemp = map.get("infoCode");
			
			if(StringUtils.isNotBlank(infoCodeTemp) && !infoCodeTemp.equalsIgnoreCase(infoCode)){
				//表示该记录的用户不关注此业务
				continue;
			}
			
			String warnTypeTemp = map.get("warnType");
			if(StringUtils.isNotBlank(warnTypeTemp)){
				
				String mobile = map.get("mobile");
				String email = map.get("email");
				
				if(warnTypeTemp.contains("0") && StringUtils.isNotBlank(mobile)){
					mobileList.add(mobile);
				}
				
				if(warnTypeTemp.contains("1") && StringUtils.isNotBlank(email)){
					emailList.add(email);
				}
			}
		}
		
		warnMap.put(WarnConstant.EMAIL_LIST, JSON.toJSONString(emailList));
		warnMap.put(WarnConstant.MOBILE_LIST, JSON.toJSONString(mobileList));
	}
	
	//更新已报警次数
	public void updatetWarnPolicy(Jedis jedis,String key,Integer id, String infoCode,String logDate){
		
		//更新redis中的数值
		jedis.hincrBy(key, WarnConstant.CURRENT_WARN_COUNT, 1);
		//设置最新告警时间，目的是为避免该时间点之前的报错信息继续告警
		jedis.hset(key, WarnConstant.ERROR_DATE, logDate);
		
		if(StringUtils.isNotBlank(infoCode)){
			MonitorInfoRule rule = monitorInfoRuleManager.getById(id);
			rule.setCurrentWarnCount(Integer.valueOf(jedis.hget(key, WarnConstant.CURRENT_WARN_COUNT)));
			monitorInfoRuleManager.update(rule);
		}else{
			MonitorModuleRule rule = monitorModuleRuleManager.getById(id);
			rule.setCurrentWarnCount(Integer.valueOf(jedis.hget(key, WarnConstant.CURRENT_WARN_COUNT)));
			monitorModuleRuleManager.update(rule);
		}
	}
	
	//更新已报警次数
//	public void updatetWarnCount(String key,Integer id, String infoCode){
//		
//		Jedis jedis = jedisPool.getResource();
//		//更新redis中的数值
//		jedis.hincrBy(key, WarnConstant.CURRENT_WARN_COUNT, 1);
//		
//		if(StringUtils.isNotBlank(infoCode)){
//			MonitorInfoRule rule = monitorInfoRuleManager.getById(id);
//			rule.setCurrentWarnCount(Integer.valueOf(jedis.hget(key, WarnConstant.CURRENT_WARN_COUNT)));
//			monitorInfoRuleManager.update(rule);
//		}else{
//			MonitorModuleRule rule = monitorModuleRuleManager.getById(id);
//			rule.setCurrentWarnCount(Integer.valueOf(jedis.hget(key, WarnConstant.CURRENT_WARN_COUNT)));
//			monitorModuleRuleManager.update(rule);
//		}
//		jedisPool.returnResource(jedis);
//		
//	}
		
	public String getDateMinute(Date logTime){
		DateFormat format = DateFormat.getInstance("yyyy-MM-dd HH:mm");
		return format.format(logTime);
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}
}