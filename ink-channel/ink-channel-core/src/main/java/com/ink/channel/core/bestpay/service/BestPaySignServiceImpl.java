package com.ink.channel.core.bestpay.service;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Service;

import com.ink.base.log.util.YinkerLogger;
import com.ink.channel.core.asile.epro.EPayHttpUtil;
import com.ink.channel.core.bestpay.RsaUtil;
import com.ink.channel.core.bestpay.request.EPaySignRequestBean;
import com.ink.channel.core.bestpay.request.FasBankAccount;
import com.ink.channel.core.bestpay.response.EPaySignResponseBean;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.model.in.AsileAuthorityInput;
import com.ink.channel.core.model.out.AsileAuthorityOutput;
import com.ink.channel.core.service.AsileAuthorityService;
import com.ink.channel.core.utils.Constants;
/**
 * 翼支付【签约服务】接口  <p>
 * 翼支付提供XML&JSON两套报文，因他们XML解析目前存在问题<br>
 * 临时用JSON当做请求报文格式，后期核心账户体系全部统一用XML<br>
 * 且XML参数需要修改为一个请求报文对象。<br>
 */
@Service("bestPaySignService")
public class BestPaySignServiceImpl implements AsileAuthorityService{
	private YinkerLogger logger = YinkerLogger.getLogger(BestPaySignServiceImpl.class);
	
	@Override
    public AsileAuthorityOutput authorize(AsileAuthorityInput authority) {
		try{
	    	logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BEST_PAY_FAS_SING_CODE,"进入翼支付签约接口");
	        FasBankAccount acc = new FasBankAccount();
	        acc.setBankCode(getBankCode(authority.getBankShort()));
	        /**1 :借记卡卡2:信用卡(贷记卡)**/
	        String cardType= authority.getCardType().equals("1")?"2":"1";
	        acc.setCardType(cardType);
	        acc.setAccountCode(authority.getCardNo());
	        acc.setBankCardName(authority.getUserName());
	        acc.setCertNo(authority.getIdNo());
	        acc.setCertType(this.getIdType(authority.getIdType()));
	        acc.setMobile(authority.getPhoneNo());
	        //网点区域编码
	        acc.setAreaCode("110000");
	        acc.setPerEntFlag("1");
	        EPaySignResponseBean signResponseBean= this.sign(authority.getReqId(), acc);
	        if(signResponseBean==null){
	        	return null;
	        }
	        AsileAuthorityOutput authorityOut=new AsileAuthorityOutput();
	        authorityOut.setResCode(signResponseBean.getCode());
	        authorityOut.setResMsg(signResponseBean.getMsg());
	        authorityOut.setIdentityId(signResponseBean.getSignId());
	        logger.info(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BEST_PAY_FAS_SING_CODE,"结束翼支付签约接口");
	        return authorityOut;
        }catch(Exception ex){
        	logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BEST_PAY_FAS_SING_CODE,ex.getMessage(),ex,"");
        	return null;
        }
        
    }
    /**
     * 根据银行名称简写获取银行编码
     * @param bankName
     * @return
     */
    private static String getBankCode(String bankshort){
        String bankCode = "";
        for (int i = 0; i < Constants.BEST_PAY_CARD_BANK_LIST.length; i++) {
            if(Constants.BEST_PAY_CARD_BANK_LIST[i][1].equals(bankshort)){
            	bankCode = Constants.BEST_PAY_CARD_BANK_LIST[i][0];
            	break;
            }
        }
        return bankCode;
    }
    
    /**
     * 翼支付签约
     * @param orderNo
     * @param bankAccount
     */
    public EPaySignResponseBean sign(String orderNo,FasBankAccount bankAccount){
        EPaySignRequestBean signReq = new EPaySignRequestBean();
        signReq.setBankAccount(bankAccount);
        String netWorkNature = null;// 网点性质
        String payeeName = null;// 收款单位名称
        try {
            netWorkNature = new String(RsaUtil.props.getString("netWorkNature").getBytes("ISO-8859-1"),"UTF-8");
            payeeName = new String(RsaUtil.props.getString("payeeName").getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logger.error(ChannelConstants.LOGGER_MODULE_NAME,ChannelConstants.BEST_PAY_FAS_SING_CODE,"结束翼支付签约接口",e,"");
            return null;
        }
        signReq.setPayeeName(payeeName);
        signReq.setNetWorkNature(netWorkNature);
        signReq.setEbkType("01");// 企业类型
        signReq.setNetWorkAreaCode("110000");// 网点区域编码
        signReq.setUserFullName(bankAccount.getBankCardName());
        signReq.setValidateType("01");// 验证类型 01:无扣费验证；02:扣1分钱验证；03:无验证
        signReq.setExtOrderSeq(orderNo);// 外部订单
        signReq.setArpType("01");// 代收付类型
        
        String url = RsaUtil.props.getString("fas")+"/service/sign";
        return EPayHttpUtil.postJson(url, signReq, EPaySignResponseBean.class);
    }
    /**
     * 获取身份证id
     * @param idType
     * @return
     */
    public String getIdType(String idType){
        String returnIdType="00";
        if(idType.equals("01")){
            returnIdType="00";
            }
        if(idType.equals("02")){
            returnIdType="03";
        }
        if(idType.equals("03")){
            returnIdType="02";
        }
        if(idType.equals("06")){
            returnIdType="01";
        }
        
        return returnIdType;
    }
    

}
