package com.ink.channel.core.bestpay.service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.asile.epro.EPayHttpUtil;
import com.ink.channel.core.bestpay.RsaUtil;
import com.ink.channel.core.bestpay.request.EPayFasRequestBean;
import com.ink.channel.core.bestpay.request.PayeeAccount;
import com.ink.channel.core.bestpay.response.EPayFasResponseBean;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.model.in.AsilePayAccountIn;
import com.ink.channel.core.model.out.AsilePayAccountOut;
import com.ink.channel.core.service.AsilePay2AccountService;

/**
 * 翼支付【签约实时代收入账】接口<p>
 * 调用此接口前必须保证用户在翼支付签约过(调用过签约接口)<br>
 * 通过签约ID传递到此接口，从而完成整个的代收过程。<br>
 */
@Service("bestPayFasService")
public class BestPayFasServiceImpl implements AsilePay2AccountService{
    
	private YinkerLogger logger = YinkerLogger.getLogger(BestPayFasServiceImpl.class);
    /**
	 * 翼支付签约之后的代扣支付
	 * @param req
	 * @return
	 */
	public EPayFasResponseBean fasBean(EPayFasRequestBean req){
        PayeeAccount acc = new PayeeAccount();
        acc.setAccountCode(RsaUtil.props.getString("accountCode"));
        try {
			acc.setAccountName(new String(RsaUtil.props.getString("accountName").getBytes("ISO-8859-1"),"UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BEST_PAY_ACCOUNT_CODE, "代扣异常",e1,"UnsupportedEncodingException");
			return null;
		}
        req.setPayeeAccount(acc);
        String url = RsaUtil.props.getString("fas")+"/service/signAgentReceiveToAccount";
        return EPayHttpUtil.postJson(url, req, EPayFasResponseBean.class);
	}
	
	@Override
	public AsilePayAccountOut payAccount(AsilePayAccountIn asilePayAccountIn) {
		try{
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BEST_PAY_ACCOUNT_CODE,"进入翼支付签约代扣接口");
			EPayFasRequestBean bean = new EPayFasRequestBean();
			int asileAmount=asilePayAccountIn.getAmount().multiply(new BigDecimal(100)).intValue();
			bean.setAmount(String.valueOf(asileAmount));//金额
			//bean.setSignId(asilePayAccountIn.getIdentityid());//签约id
	//		bean.setSignId("20160316112504000000000000008786");
			bean.setExtOrderSeq(asilePayAccountIn.getOrderNo());//外部系统订单号
			bean.setCurrencyCode(RsaUtil.props.getString("currencyCode"));
			bean.setAccountCode(asilePayAccountIn.getAccountNo());
			EPayFasResponseBean res = this.fasBean(bean);
			AsilePayAccountOut asilePayAccountOut=new AsilePayAccountOut();
			if(res==null){
				return null;
			}
			asilePayAccountOut.setResMsg(res.getMsg());
			asilePayAccountOut.setResCode(res.getCode());
			asilePayAccountOut.setOrgTranFlow(res.getResult());
			asilePayAccountOut.setOrderNo(asilePayAccountIn.getOrderNo());
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BEST_PAY_ACCOUNT_CODE,"结束翼支付签约代扣接口");
			return asilePayAccountOut;
		}catch(Exception ex){
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BEST_PAY_ACCOUNT_CODE,ex.getMessage(),ex,"");
			return null;
		}
	}
}