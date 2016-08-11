package com.ink.channel.core.quickpay.service;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.stereotype.Service;

import com._99bill.www.apipay.services.BatchPayWS.BatchPayServiceLocator;
import com.bill99.seashell.common.util.MD5Util;
import com.bill99.seashell.domain.dto.complatible.BankRequestBean;
import com.bill99.seashell.domain.dto.complatible.BankResponseBean;
import com.ink.channel.core.model.in.AsilePay2CardInput;
import com.ink.channel.core.model.out.AsilePay2CardOutput;
import com.ink.channel.core.service.AsilePay2CardService;
import com.ink.channel.core.utils.Constants;
import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.cnst.ChannelConstants;

/**
 * 快钱代付服务接口实现类
 * @author huohb
 *
 */
@Service("masPay2CardServiceImpl")
public class MasPay2CardServiceImpl implements AsilePay2CardService {
	
	private static YinkerLogger logger = YinkerLogger.getLogger(MasPay2CardServiceImpl.class);

	/**
	 * 代付
	 */
	@Override
	public AsilePay2CardOutput pay(AsilePay2CardInput input) {
		try{
			logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.QUICKM_PAY_CARD_CODE,"进入快钱代付接口");
			//客户编号所对应的密钥。。在账户邮箱中获取
	    	String key = "J8A4CKE7H3HE8NUZ";
	    	//城市,中文字符 主要只需要城市名，不需要省份名。也不要带"市""自治区（县）"等
	    	String province_city = "nj";
	    	//银行名称 请填写银行的标准名,详见接口文档
	    	String bankName = input.getBankShort();
	        for (int i = 0; i < Constants.MAS_PAY_CARD_BANK_LIST.length; i++) {
	            if(Constants.MAS_PAY_CARD_BANK_LIST[i][1].equals(input.getBankShort())){
	            	bankName = Constants.MAS_PAY_CARD_BANK_LIST[i][0];
	            }
	        }
	    	//银行卡开户行的名称
	//    	String  kaihuhang = input.getBranchBankName();//TODO:
	    	//收款人姓名,收款人的姓名，必须与银行卡账户名相同
	    	String creditName = input.getUserName();
	    	//银行卡号  
	    	String bankCardNumber = input.getCardNo();
	    	//交易金额  整数或小数  小数为两位以内  但小数点的最后一位不能为0 如：0.10不行。单位为人民币元  
	    	String amount = input.getAmount().toString() ; 
	    	//交易备注
	    	//String description = input.getRemark();    
	    	//订单号
	    	String orderId = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
	    	//组合字符串。。必须按照此顺序组串
	    	String macVal= bankCardNumber + amount + orderId + key;
	    	String mac = null;
			try {
				mac = MD5Util.md5Hex(macVal.getBytes("gb2312")).toUpperCase();
			} catch (UnsupportedEncodingException e1) {
				logger.error(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.QUICKM_PAY_CARD_CODE, "快钱代付接口组合字符串不支持gb2312编码", e1, null);
			}
	
	    	BankRequestBean requestBean = new BankRequestBean();
	    	requestBean.setProvince_city(province_city);
	    	requestBean.setBankName(bankName);
	    	requestBean.setKaihuhang("");
	    	requestBean.setCreditName(creditName);
	    	requestBean.setBankCardNumber(bankCardNumber);
	    	requestBean.setAmount(Double.parseDouble(amount));
	    	requestBean.setDescription("");
	    	requestBean.setOrderId(orderId);
	    	requestBean.setMac(mac);
	    	
	    	logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.QUICKM_PAY_CARD_CODE,"快钱代付接口请求参数："+ToStringBuilder.reflectionToString(requestBean));
	    	BankRequestBean[] queryArray = new BankRequestBean[1];
	    	queryArray[0] = requestBean;
	
	    	String merchant_id = "10012138842";
	    	String merchant_ip = "192.168.8.1";
	    	BatchPayServiceLocator locator = new BatchPayServiceLocator();
	    	BankResponseBean[] responseBean = new BankResponseBean[1];
	    	try {
				responseBean = locator.getBatchPayWS().bankPay(queryArray,merchant_id,merchant_ip);
			} catch (Exception e) {
				logger.error(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.QUICKM_PAY_CARD_CODE, "快钱代付接口调用失败", e, null);
			}
	    	AsilePay2CardOutput out = new AsilePay2CardOutput();
	    	out.setResCode(responseBean[0].getFailureCause());
	    	out.setResMsg(responseBean[0].isResultFlag() ? "成功" : "失败");
	    	out.setOrgTranFlow(responseBean[0].getDealId());
	    	out.setOrderNo(input.getOrderNo());
	    	out.setAmount(responseBean[0].getAmount()+"");
			return out;
		}catch(Exception ex){
			logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.QUICKM_PAY_CARD_CODE,ex.getMessage(),ex,null);
        }
    	logger.info(ChannelConstants.LOGGER_MODULE_NAME, ChannelConstants.QUICKM_PAY_CARD_CODE,"快钱代付接口调用完毕");
        return null;
	}
	/*private static String[][] SUPPORT_BANK_LIST = {{ "中国民生银行", "CMBC" },
		 	{ "广东发展银行", "GDB" },{ "中国工商银行", "ICBC" },
            { "中国农业银行", "ABC" }, { "中国银行", "BOC" },
            { "中国建设银行", "CCB" },{ "中国邮政储蓄银行", "PSBC" },
            { "招商银行", "CMB" },{ "中国光大银行", "CEB" },
            { "兴业银行", "CIB" }, { "平安银行", "PAB" }};*/
	
}
