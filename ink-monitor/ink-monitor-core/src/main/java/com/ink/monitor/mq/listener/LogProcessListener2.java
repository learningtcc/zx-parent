package com.ink.monitor.mq.listener;

/**
 * 日志监听报警器
 * @author zongtt
 * 2016年4月22日10:36:04
 */
public class LogProcessListener2 {
	
//	private final String LOG_PREFIX = "monitor-";
//	
//	@Autowired
//	private JedisPool jedisPool;
//	@Autowired
//	private MonitorPolicy monitorPolicy;
//	@Autowired
//	private MailService mailService;
//	
//	private YinkerLoger loger = YinkerLoger.getLogger(LogProcessListener2.class);
//	
//	/**
//	 * 处理Error日志，对错误进行计数
//	 * @param parms
//	 */
//	public String handleErrorCount(byte[] input){
//		
//		loger.info("处理开始");
//		//传递给下一个队列的结果
//		String result = null;
//		
//		String logStr = new String(input);
//		
//		LogInfo logInfo = JSON.parseObject(logStr, LogInfo.class);
//		
//		if(StringUtils.isBlank(logInfo.getSystemSource()) || StringUtils.isBlank(logInfo.getModuleCode())){
//			return null;
//		}
//		
//		Jedis jedis = null;
//		
//		try {
//			jedis = jedisPool.getResource();
//			loger.info("申请连接1："+jedis);
//			//日志时间
//			String logDate = getDateMinute(logInfo.getLogTime());
//			
//			String key = getRdKey(LOG_PREFIX,logInfo.getSystemSource(),logInfo.getModuleCode(),logInfo.getInfoCode(),logDate);
//			
//			loger.info("计数key:"+key);
//			
//			Map<String, String> warnMap = monitorPolicy.getWarnRule(jedis,logInfo.getSystemSource(),logInfo.getModuleCode(),logInfo.getInfoCode());
//			if(!warnMap.containsKey(WarnConstant.SYS_CODE)){
//				//未配置报警策略
//				return null;
//			}
//			
//			//取最新的当前报警次数，避免取得历史报警次数
//			String currentWarnCountStr = monitorPolicy.getWarnRule(jedis,logInfo.getSystemSource(),logInfo.getModuleCode(),logInfo.getInfoCode(), WarnConstant.CURRENT_WARN_COUNT);
//			int currentWarnCount = Integer.valueOf(currentWarnCountStr);
//			int warnFrequency = Integer.valueOf(warnMap.get(WarnConstant.WARN_FREQUENCY));
//			
//			if (currentWarnCount >= warnFrequency) {
//				//当前报警数大于报警频次
//				return null;
//			}
//			
//			
//			
//			result = key;
//			
//			//读取上次采集时间
//			String lastDate = warnMap.get(logInfo.getServerIp());
//			
//			if(StringUtils.isBlank(lastDate) || logDate.compareTo(lastDate) > 0){
//				//日志时间大于上次采集时间时，更新最新的采集时间
//				String rdKey = warnMap.get(WarnConstant.RD_KEY);
//				//记录最新采集时间
//				jedis.hset(rdKey, WarnConstant.SERVER_IP, logInfo.getServerIp());
//				jedis.hset(rdKey, logInfo.getServerIp(), logDate);
//				//最新的报警信息
//				jedis.hset(rdKey, WarnConstant.LAST_ERROR, logStr);
//			}
//			
//			Long incr = jedis.incr(key);
//			/**
//			 * 注释原因：若只传递第一条错误信息（错误次数为1），则后面再有报错时，无法通知下一队列
//			 */
//			if(incr > 1){
//				//减少告警端的数据量,告警端按分钟处理即可,保证报错日期过期后再读取
//				result = null;
//			}else{
//				//设置3小时过期时间，3小时内扔未处理自动失效，避免因系统问题，导致rd溢出
//				loger.info("生命周期："+key);
//				jedis.expire(key, 10800);
//			}
//			
//		} catch (Exception e) {
//			if(jedis != null){
//				jedisPool.returnBrokenResource(jedis);
//			}
//			loger.info(e.getMessage());
//			loger.info("###################"+result);
//			throw new RuntimeException("错误日志计数异常");
//		}finally {
//			if(jedis != null){
//				jedisPool.returnResource(jedis);
//				loger.info("释放连接1:"+jedis);
//			}
//		}
//		
//		return result;
//	}
//
//	private String getRdKey(String prefix, String sysCode, String moduleCode, String infoCode,String logTime) {
//		String key = prefix + sysCode + "-" + moduleCode;
//		if(StringUtils.isNotBlank(infoCode)){
//			key += "-" + infoCode;
//		}
//		
//		key += "#"+logTime;
//		return key;
//	}
//	
//	/**
//	 * 验证错误是否符合告警策略，若符合则报警
//	 * @param errorKey
//	 */
//	public String handleWarnError(String errorKey){
//		
////		System.out.println("处理线程"+Thread.currentThread().getName()+"收到消息:"+System.currentTimeMillis()+" key:" +error);
//		
//		//error格式monitor-1001-100101#2016-04-26 13:40
//		String[] paramArray = errorKey.split("#");
//		//code格式monitor-1001-100101
//		String[] codeArray = paramArray[0].split("-");
//		String logTime = paramArray[1];
//		
//		//系统代码
//		String sysCode = codeArray[1];
//		//功能代码
//		String moduleCode = codeArray[2];
//		//业务代码
//		String infoCode = "";
//		if(codeArray.length == 4){
//			infoCode = codeArray[3];
//		}
//		
//		Jedis jedis = null;
//		
//		try {
//			jedis = jedisPool.getResource();
//			loger.info("申请连接2："+jedis);
//			Map<String, String> warnMap = monitorPolicy.getWarnRule(jedis,sysCode, moduleCode, infoCode);
//			
//			if(!warnMap.containsKey(WarnConstant.SYS_CODE)){//未配置报警规则
//				//立即置为过期
//				jedis.expire(errorKey, 1);
//				return null;
//			}
//			
//			//读取记录的最新采集时间
//			String serverIp = warnMap.get(WarnConstant.SERVER_IP);
//			String lastDate = warnMap.get(serverIp);
//			
//			Date before1Min = getBeforeMinute(new Date(), 1);
//			String before1MinStr = getDateMinute(before1Min);
//			
//			if(StringUtils.isNotBlank(lastDate) && logTime.compareTo(lastDate) >= 0 && logTime.compareTo(before1MinStr) > 0){
////				loger.info("");
//				//日志时间不小于采集时间时，重新入队列下次处理
//				//为了避免因采集不到最新的时间导致最后一分钟日志无法处理的情况，当前日期1分钟之内的日志重新入队列，即1分钟之前的数据需处理
////				System.out.println("处理线程"+Thread.currentThread().getName()+"日志时间"+logTime+" 采集时间"+lastDate+"    未过期重新入队列: " +error);
//				return errorKey;
//			}
//			
//			int warnInterval = Integer.valueOf(warnMap.get(WarnConstant.WARN_INTERVAL));
//			int warnThreshold = Integer.valueOf(warnMap.get(WarnConstant.WARN_THRESHOLD));
//			int warnFrequency = Integer.valueOf(warnMap.get(WarnConstant.WARN_FREQUENCY));
//			int id = Integer.valueOf(warnMap.get(WarnConstant.ID));
//			
//			//对当前消息按照间隔时间重新设置有效期，避免下一个时间点计算时该时间点出现漏统计的情况
//			//同时避免key长期有效
////			jedis.expire(errorKey, warnInterval * 60);
//			
//			String ruleKey = paramArray[0];
//			//将报错时间作为基准时间计算报警策略
//			Date warnDate = getDateMinute(logTime);
//			String warnKey = errorKey;
//			
//			//有效报错次数
//			int errorCount = 0;
//			
//			//对当前消息按照间隔时间重新设置有效期，避免下一个时间点计算时该时间点出现漏统计的情况
//					//同时避免key长期有效
//			loger.info("KEY失效"+warnKey);
//			jedis.expire(warnKey, warnInterval * 60);
//			
//			for(int i = 0 ; i < warnInterval; i++){
//				if( i != 0){
//					//向前取一分钟
//					warnDate = getBeforeMinute(warnDate,1);
//					warnKey = ruleKey + "#" + getDateMinute(warnDate);
//				}
//				String warnCount = jedis.get(warnKey);
//				if(StringUtils.isNotBlank(warnCount)){
//					//累加报错次数
//					errorCount += Integer.valueOf(warnCount);
//				}
//			}
//			
//			String currentWarnCountStr = jedis.hget(paramArray[0], WarnConstant.CURRENT_WARN_COUNT);
//			
//			int currentWarnCount = StringUtils.isBlank(currentWarnCountStr)? 0 : Integer.valueOf(currentWarnCountStr);
////			System.out.println("处理线程"+Thread.currentThread().getName()+"key="+error+" 报错数"+errorCount +"当前告警数="+currentWarnCountStr);
//			
//			if(errorCount > warnThreshold && currentWarnCount < warnFrequency){
//				//报错次数大于阀值时，进行报警，正常不注释
////				monitorPolicy.updatetWarnCount(ruleKey, id, infoCode);
//				
//				sendMessage(warnMap);
//				
//			}
//		} catch (Exception e) {
//			if(jedis != null){
//				jedisPool.returnBrokenResource(jedis);
//			}
//			loger.info(e.getMessage());
//			loger.info("$$$$$$$$$$$$$$$$$$$$"+errorKey);
//			//重新入队列
//			return errorKey;
//		}finally {
//			if(jedis != null){
//				jedisPool.returnResource(jedis);
//				loger.info("释放连接2:"+jedis);
//			}
//		}
//		
//		
//		loger.info("处理完成"+errorKey);
//		
//		return null;
//	}
//
//	private void sendMessage(Map<String, String> warnMap) {
//		String emailJson = warnMap.get(WarnConstant.EMAIL_LIST);
//		String lastError = warnMap.get(WarnConstant.LAST_ERROR);
//		if(StringUtils.isNotBlank(emailJson) && StringUtils.isNotBlank(lastError)){
//			List<String> emailList = JSON.parseArray(emailJson, String.class);
//			LogInfo log = JSON.parseObject(lastError, LogInfo.class);
//			for (String email : emailList) {
//				try {
//					mailService.sendMail("日志报警", log.toString("\n"), email);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//	
//	private String getDateMinute(Date logTime){
//		DateFormat format = DateFormat.getInstance("yyyy-MM-dd HH:mm");
//		return format.format(logTime);
//	}
//	
//	private Date getDateMinute(String logTime){
//		DateFormat format = DateFormat.getInstance("yyyy-MM-dd HH:mm");
//		try {
//			return format.parse(logTime);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	public Date getBeforeMinute(Date date, int minute){  
//        Calendar c = Calendar.getInstance();
//        c.setTime(date);
//        c.add(Calendar.MINUTE, -minute);  
//        return c.getTime();  
//  } 
}