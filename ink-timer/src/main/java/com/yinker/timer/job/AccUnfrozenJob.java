package com.yinker.timer.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.yinker.user.api.model.in.ACC39080Input;
import com.yinker.user.api.model.out.RetOutput;
import com.yinker.user.core.po.TnsLog;
import com.yinker.user.core.service.redis.AccUnfrozenRedisService;
import com.yinker.user.core.service.redis.BaseRedis;
import com.yinker.user.core.service.tns.ITnsLogService;
import com.yinker.user.service.adaptor.ObjAdapter;
import com.yinker.user.util.CustomizedPropertyPlaceholderConfigurer;
import com.yinker.user.util.DateUtils;
import com.yinker.user.util.IdWorker;

public class AccUnfrozenJob {
	private static final Logger logger = LoggerFactory
			.getLogger(AccControlJob.class);

	@Autowired
	private BaseRedis baseRedis;
	@Autowired
	private AccUnfrozenRedisService accUnfrozenRedisService;
	@Autowired
	@Qualifier("acc39080")
	private ObjAdapter<RetOutput, ACC39080Input> acc39080;
	@Autowired
	private ITnsLogService tnsLogService;
	//@Autowired
	private CustomizedPropertyPlaceholderConfigurer propertyConfigurer;

	public void work() throws Exception {
		logger.info("自动解冻账户过期冻结金额任务开始");
		try {

			List<TnsLog> tnsLogList = accUnfrozenRedisService
					.getAllTnsLogFromRedisMap();
			for (TnsLog tnsLog : tnsLogList) {
				// 交易时间
				String ordDateStr = DateUtils.getDateTime(tnsLog.getOrdDate());
				// 配置时间
				@SuppressWarnings("static-access")
				Long time = Long.valueOf((String) propertyConfigurer
						.getContextProperty("accUnfrozenTime"));
				// 判断冻结金额已冻结时间是否大于配置时间（小时计算）
				if (DateUtils.dateDiff(ordDateStr,
						DateUtils.getCurrDateTimeStr()) > time) {
					// 检查是否存在ACC30060或ACC39080的TnsLog存在
					TnsLog tnsLogCheck = tnsLogService
							.findTnsLogByAccUnfrozen(
									tnsLog.getOriOrdId(), tnsLog.getAmt(),
									tnsLog.getCustFee(), tnsLog.getOrdDate());
					if (tnsLogCheck == null) {
						ACC39080Input dto = new ACC39080Input();
						dto.setTxnCode("ACC39080");
						dto.setMchId(tnsLog.getMchId().toString());
						dto.setOrdId(IdWorker.getNextId().toString());
						dto.setOriOrdId(tnsLog.getOrdId());
						dto.setOriTxnCode("ACC30050");
						dto.setOriTradeDate(DateUtils.getDateTime(tnsLog.getOrdDate()));
						dto.setTradeDate(DateUtils.getCurrDateTimeStr());
						dto.setDepositType("APT0000001");
						acc39080.exec(dto);
					}
				}
			}

		} catch (Exception e) {
			logger.error("自动解冻账户过期冻结金额任务出错", e);
			// 出异常情况还没考虑好，是再执行一次还是怎样，回头商量一下再说吧
		}
	}
	


}
