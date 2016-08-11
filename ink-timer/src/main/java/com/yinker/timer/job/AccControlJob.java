package com.yinker.timer.job;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yinker.user.core.dao.IAccPacDao;
import com.yinker.user.core.dao.IAccSacDao;
import com.yinker.user.core.service.redis.BaseRedis;


/**
 * 每日零点更新acc_pac,acc_sac表的交易金额和笔数
 * @author yangchen
 * @date 2016年1月29日 下午2:06:58
 */
public class AccControlJob  implements TimingJob 
{
	private static final Logger logger = LoggerFactory.getLogger(AccControlJob.class);
	@Autowired
	private IAccPacDao accPacDao;
	@Autowired
	private IAccSacDao accSacDao;
	@Autowired
	private BaseRedis baseRedis;
	
	private static final String key ="AccControlJobLock";

	public void work()
			throws Exception {
		logger.info("更新账户控制表定时任务开启");
		try {
			// 考虑多机部署情况，要去redis里面设个锁，只有抢到锁的机器才执行
			if(baseRedis.setnx(key, "") == 1){
				baseRedis.expire(key, 600);// 锁的过期时间设为600秒，10钟应该足够执行完成了吧
				if(Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == 1){
					// 更新日、月金额和笔数
					accSacDao.clearDayAndMonAmtAndCnt();
					accPacDao.clearDayAndMonAmtAndCnt();
					logger.info("更新月&日金额和笔数成功");
				}else{
					// 更新日金额和笔数
					accSacDao.clearDayAmtAndCnt();
					accPacDao.clearDayAmtAndCnt();
					logger.info("更新日金额和笔数成功");
				}
			}
		} catch (Exception e) {
			logger.error("更新账户控制表定时任务出错", e);
			// 出异常情况还没考虑好，是再执行一次还是怎样，回头商量一下再说吧
		}
	}
}
