//package com.ink.user.service.job;
//
//import java.math.BigDecimal;
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
//public class MchAccPeriodUpdateJob extends AbstractBaseJob{
//	private static YinkerLoger logger = YinkerLoger
//			.getLogger(MchAccPeriodUpdateJob.class);
//	@Autowired
//	private IAccAccDao accAccDao;
//	@Autowired
//	private IAccMchProofDao accMchProofDao;
//	@Autowired
//	private AccMchCacheService accMchCacheService;
//	@Override
//	public void execute(String key, String unlockURL) {
//		// 遍历所有商户
//		AccMch mch = accMchCacheService.getAccMchCacheByMchId(AtpTnsConstant.JLCMchId);
//		if(mch != null){
//			// 遍历所有商户的企业账户
//			for(String sacType : MchAccTypeConstant.mchSacTypes){
//				AccAcc accAcc = accAccDao.getAccAccByMchIdAndSacType(mch.getMchId(), sacType);
//				if(accAcc != null){
//					AccMchProof record = new AccMchProof();
//					record.setMchId(mch.getMchId());
//					record.setSacType(sacType);
//					// 遍历商户凭证，做统计
//					Date updateTime = new Date();
//					BigDecimal amt = accMchProofDao.getAmtByMchIdAndSacTypeAndTime(record, accAcc.getLastUpdateTime()
//						, updateTime);
//					if(amt == null){
//						amt = BigDecimal.ZERO;
//					}
//					logger.info(UserLoggerCnst.USER_ACCOUNT_MOUDLE, "amt = " + amt);
//					// 修改商户账户金额
//					if(accAcc.getCurBal() == null){
//						accAcc.setCurBal(BigDecimal.ZERO);
//					}
//					accAcc.setCurBal(accAcc.getCurBal().add(amt));
//					accAcc.setLastUpdateTime(updateTime);
//					accAccDao.updateByPrimaryKey(accAcc);
//				}
//			}
//			
//		}else{
//			// 记录错误日志
//			logger.error(UserLoggerCnst.USER_ACCOUNT_MOUDLE, "每天汇总商户昨日余额任务出错，商户未找到");
//
//		}
//	}
//
//}
