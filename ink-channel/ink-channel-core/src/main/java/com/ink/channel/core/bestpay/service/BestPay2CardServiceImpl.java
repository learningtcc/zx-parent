package com.ink.channel.core.bestpay.service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.asile.epro.EPayServiceImpl;
import com.ink.channel.core.bestpay.RsaUtil;
import com.ink.channel.core.bestpay.request.EPayToCardRequestBean;
import com.ink.channel.core.bestpay.request.PayeeBankAccount;
import com.ink.channel.core.bestpay.response.EPayToCardResponseBean;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.model.in.AsilePay2CardInput;
import com.ink.channel.core.model.out.AsilePay2CardOutput;
import com.ink.channel.core.service.AsilePay2CardService;
import com.ink.channel.core.utils.Constants;
/**
 * 翼支付代付服务接口实现类
 * @author huohb
 *
 */
@Service("bestPay2CardServiceImpl")
public class BestPay2CardServiceImpl implements AsilePay2CardService{
	
	private static YinkerLogger logger = YinkerLogger.getLogger(BestPay2CardServiceImpl.class);
	/**
	 * 代付
	 */
	@Override
	public AsilePay2CardOutput pay(AsilePay2CardInput input) {
		logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BEST_PAY_CARD_CODE,"进入翼支付代付服务，卡号：" + input.getCardNo());
		//设置请求报文参数
		EPayToCardRequestBean bean = new EPayToCardRequestBean();
		bean.setCurrencyCode("RMB");
		bean.setTransactionAmount(input.getAmount().multiply(new BigDecimal(100)).toString());
		bean.setAccountCode(RsaUtil.props.getString("accountCode"));// 转出方账户号
		bean.setExternalId(input.getOrderNo());
		try {
			bean.setAccountName(new String(RsaUtil.props.getString("accountName").getBytes("ISO-8859-1"),"UTF-8"));// 转出方账户名
		} catch (UnsupportedEncodingException e) {
			logger.error(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.BEST_PAY_CARD_CODE, "转换翼支付账户名出错，不支持的编码", e, null);
			return null;
		}
		//转换bankCode
		String bankName = null;
        for (int i = 0; i < Constants.BEST_PAY_CARD_BANK_LIST.length; i++) {
            if(Constants.BEST_PAY_CARD_BANK_LIST[i][1].equals(input.getBankShort())){
            	bankName = Constants.BEST_PAY_CARD_BANK_LIST[i][0];
            }
        }
        try{
			PayeeBankAccount acc = new PayeeBankAccount();
			acc.setAccountCode(input.getCardNo());
			acc.setAreaCode("110000");
			acc.setBankCardName(input.getUserName());
			acc.setBankCode(bankName);
			acc.setCardType("1");// 借贷记标识
			acc.setCertNo(input.getIdNo());
			acc.setCertType(input.getIdType());
			acc.setPerEntFlag("1");// 0：对公，1：对私
			
			bean.setPayeeBankAccount(acc);
			//发送请求报文
			AsilePay2CardOutput out = new AsilePay2CardOutput();
			EPayToCardResponseBean res = new EPayServiceImpl().payToCard(bean);
			if(res==null){
				return null;
			}
			out.setResCode(res.getCode());
			out.setResMsg(res.getMsg());
			out.setOrgTranFlow(res.getResult());
			out.setOrderNo(input.getOrderNo());
			out.setAmount(input.getAmount().toString());
			logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BEST_PAY_CARD_CODE,"翼支付代付服务执行完毕，卡号：" + input.getCardNo());
			return out;
        }catch(Exception ex){
        	logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BEST_PAY_CARD_CODE,ex.getMessage(),ex,"");
			return null;
		}
	}
	
	
}
