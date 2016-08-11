package com.ink.channel.core.asile.epro;

import java.io.UnsupportedEncodingException;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.bestpay.RsaUtil;
import com.ink.channel.core.bestpay.request.EPayFasRequestBean;
import com.ink.channel.core.bestpay.request.EPaySignRequestBean;
import com.ink.channel.core.bestpay.request.EPayToCardRequestBean;
import com.ink.channel.core.bestpay.request.EPayTransDetailQueryRequestBean;
import com.ink.channel.core.bestpay.request.EPayTransIntegratedQueryRequestBean;
import com.ink.channel.core.bestpay.request.FasBankAccount;
import com.ink.channel.core.bestpay.request.PayeeAccount;
import com.ink.channel.core.bestpay.response.EPayFasResponseBean;
import com.ink.channel.core.bestpay.response.EPaySignResponseBean;
import com.ink.channel.core.bestpay.response.EPayToCardResponseBean;
import com.ink.channel.core.bestpay.response.EPayTransDetailQueryResponseBean;
import com.ink.channel.core.bestpay.response.EPayTransIntegratedQueryResponseBean;
import com.ink.channel.core.cnst.ChannelConstants;

/**
 * 翼支付服务接口
 * @author huohb
 *
 */
public class EPayServiceImpl{
	
	private static YinkerLogger logger = YinkerLogger.getLogger(EPayServiceImpl.class);
	
	/**
	 * 翼支付签约
	 * @param orderNo
	 * @param bankAccount
	 */
	public EPaySignResponseBean sign(String orderNo,FasBankAccount bankAccount){
		logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BEST_PAY_CARD_CODE,"进入翼支付签约接口");
		EPaySignRequestBean signReq = new EPaySignRequestBean();
		signReq.setBankAccount(bankAccount);
		String netWorkNature = null;// 网点性质
		String payeeName = null;// 收款单位名称
		try {
			netWorkNature = new String(RsaUtil.props.getString("netWorkNature").getBytes("ISO-8859-1"),"UTF-8");
			payeeName = new String(RsaUtil.props.getString("payeeName").getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		signReq.setPayeeName(payeeName);
		signReq.setNetWorkNature(netWorkNature);
        signReq.setEbkType("01");// 企业类型
        signReq.setNetWorkAreaCode("110000");// 网点区域编码
        signReq.setUserFullName(bankAccount.getBankCardName());
        signReq.setValidateType("01");// 验证类型 01:无扣费验证；02:扣1分钱验证；03:无验证
        signReq.setExtOrderSeq(orderNo);// 外部订单
        signReq.setArpType("02");// 代收付类型
        
        String url = RsaUtil.props.getString("fas")+"/service/sign";
        return EPayHttpUtil.postJson(url, signReq, EPaySignResponseBean.class);
	}
	/**
	 * 翼支付签约之后的代扣支付
	 * @param req
	 * @return
	 */
	public EPayFasResponseBean fas(EPayFasRequestBean req){
		logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BEST_PAY_ACCOUNT_CODE,"进入翼支付签约代扣接口");
        PayeeAccount acc = new PayeeAccount();
        req.setCurrencyCode("RMB");// 币种
        acc.setAccountCode(RsaUtil.props.getString("accountCode"));
        try {
			acc.setAccountName(new String(RsaUtil.props.getString("accountName").getBytes("ISO-8859-1"),"UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
        req.setPayeeAccount(acc);

        String url = RsaUtil.props.getString("fas")+"/service/signAgentReceiveToAccount";
        
        return EPayHttpUtil.postJson(url, req, EPayFasResponseBean.class);
	}
	/**
	 * 付款到银行卡账户
	 * @param req
	 * @return
	 */
	public EPayToCardResponseBean payToCard(EPayToCardRequestBean req){
		
		logger.info("进入翼支付付款到银行卡账户接口");
        String url = RsaUtil.props.getString("fas")+"/service/payToBank";
        
        return EPayHttpUtil.postJson(url, req, EPayToCardResponseBean.class);
		
	}
	/**
	 * 交易明细查询
	 * @param req
	 * @return
	 */
	public EPayTransDetailQueryResponseBean transDetailQuery(EPayTransDetailQueryRequestBean req){
        String url = RsaUtil.props.getString("fas")+"/service/transDetailQuery";
        
        return EPayHttpUtil.postJson(url, req, EPayTransDetailQueryResponseBean.class);
	}

	/**
	 * 交易明细查询
	 * @param req
	 * @return
	 */
	public EPayTransIntegratedQueryResponseBean transIntegratedQuery(EPayTransIntegratedQueryRequestBean req){
        String url = RsaUtil.props.getString("fas")+"/service/transIntegratedQuery";
        
        return EPayHttpUtil.postJson(url, req, EPayTransIntegratedQueryResponseBean.class);
	}
}
