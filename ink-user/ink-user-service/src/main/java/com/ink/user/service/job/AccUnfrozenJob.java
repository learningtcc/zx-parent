package com.ink.user.service.job;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.config.service.support.spring.CustomizedPropertyConfigurer;
import com.ink.job.AbstractBaseJob;
import com.ink.user.api.constants.AtpTnsConstant;
import com.ink.user.api.model.in.AccWithdrawCancelInput;
import com.ink.user.api.service.IAccWithdrawCancelService;
import com.ink.user.core.po.TnsAuth;
import com.ink.user.core.po.TnsLog;
import com.ink.user.core.service.tns.ITnsAuthService;
import com.ink.user.core.service.tns.ITnsLogService;
import com.ink.user.util.DateUtils;

/**
 * 过期提现申请解冻任务
 * 
 * @author yangchen
 * @date 2016年5月16日 上午10:48:10
 */
@Component
public class AccUnfrozenJob extends AbstractBaseJob {
	private static final YinkerLogger logger = YinkerLogger.getLogger(AccUnfrozenJob.class);
	@Autowired
	private CustomizedPropertyConfigurer customizedPropertyConfigurer;
	@Autowired
	private ITnsLogService tnsLogService;
	@Autowired
	private ITnsAuthService tnsAuthService;
	@Autowired
	private IAccWithdrawCancelService accWithdrawCancelService;
	@Autowired
	private IdCodeGenerator idCodeGenerator;
	@Override
	public void execute() {
		logger.info("自动解冻账户过期冻结金额任务开始");
		try {
			Date endTime = new Date();
			// 获取配置时间，以分钟为单位
			Long time = Long.valueOf((String) customizedPropertyConfigurer.getProperty("accUnfrozenTime"));
			Long millSeconds = time * 60 * 1000;// 转为毫秒
			 endTime.setTime(endTime.getTime() - millSeconds);
			Date startTime = new Date();
			startTime.setTime(endTime.getTime() - millSeconds);
			List<TnsAuth> tnsAuths = tnsAuthService.getUnfrozenTnsAuth(startTime, endTime);
			if (tnsAuths != null) {
				for (TnsAuth tnsAuth : tnsAuths) {
					// 如果已被冲正或授权完成，则不撤销
					if (tnsAuth.getRevFlg().equals("Y") || tnsAuth.getAuthStatus() == 1) {
						continue;
					}
					// 查询原单信息，注意filler1扩展字段里存了商户ID(mch_id)
					TnsLog oriTnsLog = tnsLogService.getTnsLogbyId(String.valueOf(tnsAuth.getTnsLogId()));
					// 如果原单信息不为空
					if (oriTnsLog != null) {
						AccWithdrawCancelInput dto = new AccWithdrawCancelInput();
						dto.setTxnCode(AtpTnsConstant.ACC_AWC);
						dto.setMchId(oriTnsLog.getMchId().toString());
						dto.setOrdId(idCodeGenerator.getId());
						dto.setOriOrdId(oriTnsLog.getOrdId());
						dto.setOriTxnCode(oriTnsLog.getTxnCode());
						dto.setOriTradeDate(DateUtils.getDateTime(oriTnsLog.getOrdDate()));
						dto.setTradeDate(DateUtils.getCurrDateTimeStr());
						accWithdrawCancelService.exec(dto);
					}
				}
			}

		} catch (Exception e) {
			logger.error("自动解冻账户过期冻结金额任务出错", e);
			// 出异常情况还没考虑好，是再执行一次还是怎样，回头商量一下再说吧
		}
	}

}
