package com.yinker.timer.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yinker.trade.api.rule.IAsileRoute;

/**
 * 通道银行失败率检测job
 * 
 * @author yangchen
 * @date 2016年4月8日 上午11:23:16
 */
public class AsileBankFailTestJob {
	private static final Logger logger = LoggerFactory
			.getLogger(AsileBankFailTestJob.class);

	@Autowired
	private IAsileRoute asileRoute;

	public void work() throws Exception {
		logger.info("自动检测通道银行失败率任务开启");
		asileRoute.testFailRate();
	}
}
