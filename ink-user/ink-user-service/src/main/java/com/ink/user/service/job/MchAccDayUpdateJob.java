//package com.ink.user.service.job;
//
//import java.math.BigDecimal;
//import java.util.Calendar;
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.ink.base.utils.logUtil.YinkerLoger;
//import com.ink.job.AbstractBaseJob;
//import com.ink.user.api.constants.AtpTnsConstant;
//import com.ink.user.common.constant.MchAccTypeConstant;
//import com.ink.user.common.constant.UserLoggerCnst;
//import com.ink.user.core.dao.IAccAccDao;
//import com.ink.user.core.dao.IAccMchProofDao;
//import com.ink.user.core.po.AccAcc;
//import com.ink.user.core.po.AccMch;
//import com.ink.user.core.po.AccMchProof;
//import com.ink.user.core.service.redis.AccMchCacheService;
//
//@Component
//public class MchAccDayUpdateJob extends AbstractBaseJob {
//	private static YinkerLoger logger = YinkerLoger
//			.getLogger(MchAccDayUpdateJob.class);
//	@Autowired
//	private IAccAccDao accAccDao;
//	@Autowired
//	private IAccMchProofDao accMchProofDao;
//	@Autowired
//	private AccMchCacheService accMchCacheService;
//
//	@Override
//	public void execute(String key, String unlockURL) {
//		// 遍历所有商户
//		AccMch mch = accMchCacheService.getAccMchCacheByMchId(AtpTnsConstant.JLCMchId);
//		if (mch != null) {
//			// 遍历所有商户的企业账户
//			for (String sacType : MchAccTypeConstant.mchSacTypes) {
//				AccAcc accAcc = accAccDao.getAccAccByMchIdAndSacType(
//						mch.getMchId(), sacType);
//				if (accAcc != null) {
//					AccMchProof record = new AccMchProof();
//					record.setMchId(mch.getMchId());
//					record.setSacType(sacType);
//					// 遍历商户凭证，做统计
//					Calendar calendar = Calendar.getInstance();
//					Date current = calendar.getTime();
//					int hour = calendar.get(Calendar.HOUR_OF_DAY);
//					if(hour == 0){// 防止由于服务器时间不同步，造成定时任务触发时，本机器还没有到第二天的情况
//						calendar.add(Calendar.DAY_OF_MONTH, -1);
//					}
//					calendar.set(Calendar.HOUR_OF_DAY, 0);
//					calendar.set(Calendar.MINUTE, 0);
//					calendar.set(Calendar.SECOND, 0);
//					Date startTime = calendar.getTime();
//
//					calendar.setTime(current);
//					calendar.set(Calendar.HOUR_OF_DAY, 0);
//					calendar.set(Calendar.MINUTE, 0);
//					calendar.set(Calendar.SECOND, 0);
//					if(hour == 23){// 防止由于服务器时间不同步，造成定时任务触发时，本机器还没有到第二天的情况
//						calendar.add(Calendar.DAY_OF_MONTH, 1);
//					}
//					Date endTime = calendar.getTime();					
//					BigDecimal amt = accMchProofDao.getAmtByMchIdAndSacTypeAndTime(record, startTime
//						, endTime);
//					if(amt == null){
//						amt = BigDecimal.ZERO;
//					}
//					logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE, "amt = " + amt);
//
//					// 修改商户账户金额
//					if(accAcc.getLstBal() == null){
//						accAcc.setLstBal(BigDecimal.ZERO);
//					}
//					accAcc.setLstBal(accAcc.getLstBal().add(amt));
//					accAcc.setLastUpdateTime(new Date());
//					accAccDao.updateByPrimaryKey(accAcc);
//				}
//			}
//		} else {
//			// 记录错误日志
//			logger.error(UserLoggerCnst.USER_ACCOUNT_MOUDLE, "每天汇总商户昨日余额任务出错，商户未找到");
//		}
//	}
//
//}
