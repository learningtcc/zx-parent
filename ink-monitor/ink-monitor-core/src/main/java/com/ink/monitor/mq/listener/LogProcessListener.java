package com.ink.monitor.mq.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ink.monitor.dubbo.EmailSender;
import com.ink.monitor.dubbo.SmsSender;
import com.ink.monitor.log.rule.WarnConstant;
import com.ink.base.log.util.LogConst;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IpUtils;
import com.ink.base.utils.dateUtil.DateUtil;
import com.ink.monitor.core.service.IMongoLogChartServiceManager;
import com.ink.monitor.log.LogInfo;
import com.ink.monitor.log.rule.MonitorPolicy;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;

/**
 * 日志监听报警器
 * @author zongtt
 * 2016年4月22日10:36:04
 */
public class LogProcessListener {
	
	private final String LOG_PREFIX = "monitor-";
	
	@Autowired
	private JedisPool jedisPool;
	@Autowired
	private MonitorPolicy monitorPolicy;
	@Autowired
	protected SmsSender smsSender;
	@Autowired
	protected EmailSender emailSender;
	@Autowired
	private IMongoLogChartServiceManager mongoLogChartServiceManager;
	
	private YinkerLogger loger = YinkerLogger.getLogger(LogProcessListener.class);
	
	/**
	 * 处理Error日志，对错误进行计数
	 * @param input
	 */
	public String handleErrorCount(byte[] input){
		
		String logStr = new String(input);
		
		LogInfo logInfo = JSON.parseObject(logStr, LogInfo.class);
		
		if(StringUtils.isBlank(logInfo.getSystemSource()) || StringUtils.isBlank(logInfo.getModuleCode())){
			return null;
		}
		
		Jedis jedis = null;
		
		try {

			//记入mongo用于图表查询
			mongoLogChartServiceManager.saveMinuteSysErrorLog(logInfo.getSystemSource(),new Date());

			jedis = jedisPool.getResource();
			//日志时间
			String logDate = monitorPolicy.getDateMinute(logInfo.getLogTime());
			//monitor:系统代码:功能代码:业务代码:日志分钟
			String errorKey = getRdKey(LOG_PREFIX,logInfo.getSystemSource(),logInfo.getModuleCode(),logInfo.getInfoCode(),logDate);
			
			Map<String, String> warnMap = monitorPolicy.getWarnRule(jedis,logInfo.getSystemSource(),logInfo.getModuleCode(),logInfo.getInfoCode());
			//上次报警时间点
			String errorDate = warnMap.get(WarnConstant.ERROR_DATE);
			if(!warnMap.containsKey(WarnConstant.SYS_CODE) || (StringUtils.isNotBlank(errorDate) && errorDate.compareTo(logDate) >= 0)){
				//未配置报警策略或日志时间小于上次报警时间
				return null;
			}

			//取最新的当前报警次数，避免取得历史报警次数
			String currentWarnCountStr = monitorPolicy.getWarnRule(jedis,logInfo.getSystemSource(),logInfo.getModuleCode(),logInfo.getInfoCode(), WarnConstant.CURRENT_WARN_COUNT);
			int currentWarnCount = Integer.valueOf(currentWarnCountStr);
			int warnFrequency = Integer.valueOf(warnMap.get(WarnConstant.WARN_FREQUENCY));
			
			if (currentWarnCount >= warnFrequency) {
				//当前报警数大于报警频次
				return null;
			}
			
			Long incr = jedis.incr(errorKey);
			/**
			 * 注释原因：若只传递第一条错误信息（错误次数为1），则后面再有报错时，无法通知下一队列
			 */
			if(incr.intValue() == 1){
				//设置3小时过期时间，3小时内扔未处理自动失效，避免因系统问题，导致rd溢出
				jedis.expire(errorKey, 10800);
			}

//			statisticChart(jedis, logInfo.getSystemSource(), logDate);

		} catch (Exception e) {
			if(jedis != null){
				jedisPool.returnBrokenResource(jedis);
			}
			throw new RuntimeException("错误日志计数异常");
		}finally {
			if(jedis != null){
				jedisPool.returnResource(jedis);
			}
		}
		
		return logStr;
	}

	private String getRdKey(String prefix, String sysCode, String moduleCode, String infoCode,String logTime) {
		String key = prefix + sysCode + ":" + moduleCode;
		if(StringUtils.isNotBlank(infoCode)){
			key += ":" + infoCode;
		}
		
		key += "#"+logTime;
		return key;
	}
	
	/**
	 * 验证错误是否符合告警策略，若符合则报警
	 * @param errorLog
	 */
	public void handleWarnError(String errorLog){
		
		LogInfo logInfo = JSON.parseObject(errorLog, LogInfo.class);
		
		String logDateStr = monitorPolicy.getDateMinute(logInfo.getLogTime());
		
		String errorKey = getRdKey(LOG_PREFIX,logInfo.getSystemSource(),logInfo.getModuleCode(),logInfo.getInfoCode(),logDateStr);
		
		//系统代码
		String sysCode = logInfo.getSystemSource();
		//功能代码
		String moduleCode = logInfo.getModuleCode();
		//业务代码
		String infoCode = logInfo.getInfoCode();
		
		Jedis jedis = null;
		
		try {
			jedis = jedisPool.getResource();
			Map<String, String> warnMap = monitorPolicy.getWarnRule(jedis,sysCode, moduleCode, infoCode);
			
			//上次报警时间点
			String errorDate = warnMap.get(WarnConstant.ERROR_DATE);
			if(!warnMap.containsKey(WarnConstant.SYS_CODE) || (StringUtils.isNotBlank(errorDate) && errorDate.compareTo(logDateStr) >= 0)){
				//未配置报警策略或日志时间小于上次报警时间
				return;
			}
			//设置log MDC日志链信息
			setMdcConfig(logInfo);
			
			int warnInterval = Integer.valueOf(warnMap.get(WarnConstant.WARN_INTERVAL));
			int warnThreshold = Integer.valueOf(warnMap.get(WarnConstant.WARN_THRESHOLD));
			int warnFrequency = Integer.valueOf(warnMap.get(WarnConstant.WARN_FREQUENCY));
			int id = Integer.valueOf(warnMap.get(WarnConstant.ID));
			
			String ruleKey = errorKey.split("#")[0];
			//获取报警总次数
			int errorCount = getErrorCount(logInfo, errorKey, jedis, warnInterval, ruleKey);

			String currentWarnCountStr = monitorPolicy.getWarnRule(jedis,logInfo.getSystemSource(),logInfo.getModuleCode(),logInfo.getInfoCode(), WarnConstant.CURRENT_WARN_COUNT);
			int currentWarnCount = StringUtils.isBlank(currentWarnCountStr)? 0 : Integer.valueOf(currentWarnCountStr);
			
			if(errorCount > warnThreshold && currentWarnCount < warnFrequency){
				//报错次数大于阀值时，进行报警
				monitorPolicy.updatetWarnPolicy(jedis,ruleKey, id, infoCode,logDateStr);
				sendMessage(warnMap,logInfo);
			}

			jedis.expire(errorKey, warnInterval * 60);
			
		} catch (Exception e) {
			if(jedis != null){
				jedisPool.returnBrokenResource(jedis);
			}
		}finally {
			MDC.clear();
			if(jedis != null){
				jedisPool.returnResource(jedis);
			}
		}
	}

	/**
	 * 发送预警消息
	 * @param warnMap
	 * @param log
	 */
	private void sendMessage(Map<String, String> warnMap,LogInfo log) {
		String emailJson = warnMap.get(WarnConstant.EMAIL_LIST);
		if(StringUtils.isNotBlank(emailJson)){
			List<String> emailList = JSON.parseArray(emailJson, String.class);
			for (String email : emailList) {
				try {
					JSONArray paramJson = new JSONArray();
					paramJson.add(log.getLogLevel());
					paramJson.add(DateUtil.formatToYYYYMMDDMMHHSS(log.getLogTime()));
					paramJson.add(log.getUserName());
					paramJson.add(log.getRequestIp());
					paramJson.add(log.getRequestId());
					paramJson.add(log.getServerIp());
					paramJson.add(log.getReqContext());
					paramJson.add(log.getUrl());
					paramJson.add(warnMap.get(WarnConstant.SYS_NAME));
					paramJson.add(warnMap.get(WarnConstant.MODULE_NAME));
					paramJson.add(warnMap.get(WarnConstant.INFO_NAME));
					paramJson.add(log.getLogClass());
					paramJson.add(log.getTradeId());
					paramJson.add(log.getMsg());
					paramJson.add(DateUtil.formatToYYYYMMDDMMHHSS(new Date()));
					emailSender.sendEmail(email,paramJson.toJSONString(),true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		String mobileJson = warnMap.get(WarnConstant.MOBILE_LIST);
		if(StringUtils.isNotBlank(mobileJson)){
			List<String> mobileList = JSON.parseArray(mobileJson, String.class);
			for (String mobile : mobileList) {
				try {
					JSONArray paramJson = new JSONArray();
					String logName = warnMap.get(WarnConstant.SYS_NAME) + "-" + warnMap.get(WarnConstant.MODULE_NAME);

					if (StringUtils.isNotBlank(warnMap.get(WarnConstant.INFO_NAME))){
						logName = "-" + warnMap.get(WarnConstant.INFO_NAME);
					}
					paramJson.add(logName);
					paramJson.add(log.getServerIp());
					smsSender.sendSms(mobile, paramJson.toJSONString(), true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 获取总报警次数
	 * @param logInfo
	 * @param errorKey
	 * @param jedis
	 * @param warnInterval
	 * @param ruleKey
	 * @return
	 */
	private int getErrorCount(LogInfo logInfo, String errorKey, Jedis jedis, int warnInterval, String ruleKey) {
		//将报错时间作为基准时间计算报警策略
		Date logDate = logInfo.getLogTime();
		String errorTmpKey = errorKey;
		String[] errorTempKeys = new String[warnInterval];
		//有效报错次数
		int errorCount = 0;

		for(int i = 0 ; i < warnInterval; i++){
			if( i != 0){
				//向前取一分钟
				logDate = getBeforeMinute(logDate,1);
				errorTmpKey = ruleKey + "#" + monitorPolicy.getDateMinute(logDate);
			}
			errorTempKeys[i] = errorTmpKey;
		}

		//从redis中一次获取当前预警间隔所有的key
		List<String> minuteCountList = jedis.mget(errorTempKeys);
		if (minuteCountList == null || minuteCountList.isEmpty()){
			return errorCount;
		}

		for (String warnCount : minuteCountList){
			if (StringUtils.isBlank(warnCount)){
				continue;
			}
			//累加报错次数
			errorCount += Integer.valueOf(warnCount);
		}
		return errorCount;
	}

	/**
	 * 获取钱minute分钟
	 * @param date
	 * @param minute
	 * @return
	 */
	private Date getBeforeMinute(Date date, int minute){  
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, -minute);  
        return c.getTime();  
	}
	
	/**
	 * 图表统计，用于统计整个系统
	 */
	private void statisticChart(Jedis jedis, String sysCode,String logDate){

		String dayKey = "monitor-"+sysCode+":"+logDate.substring(0,9);
		String hourKey = "monitor-"+sysCode+":"+logDate.substring(0,12);
		String minuteKey = "monitor-"+sysCode+":"+logDate;
		//当前分钟的报错数
		jedis.incr(minuteKey);
		jedis.expire(minuteKey, 60);
	}
	
	private void setMdcConfig(LogInfo log){
		
		MDC.put(LogConst.USER_ID, "");
		MDC.put(LogConst.USER_NAME, log.getUserName());
		MDC.put(LogConst.REQ_ID, log.getRequestId());
		MDC.put(LogConst.REQ_REMOTEADDR, log.getRequestIp());
		MDC.put(LogConst.REQ_SERVERIP, IpUtils.getLocalAddress());
		MDC.put(LogConst.REQ_CONTEXT, log.getReqContext());
		MDC.put(LogConst.REQ_URI, log.getUrl());
		MDC.put(LogConst.LOG_SEQ, "1");
    	//获取当前应用的context
    	MDC.put(LogConst.CONTEXT, loger.getLogContext());
		
	}
}