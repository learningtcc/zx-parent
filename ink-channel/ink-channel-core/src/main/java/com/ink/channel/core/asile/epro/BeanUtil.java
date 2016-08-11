package com.ink.channel.core.asile.epro;

import java.security.GeneralSecurityException;

import com.ink.channel.core.bestpay.BestPayUtil;
import com.ink.channel.core.bestpay.RsaUtil;
import com.ink.channel.core.bestpay.request.EPayBaseRequestBean;
import com.ink.channel.core.utils.GetLocalIpUtil;
import com.ink.channel.core.utils.JaxbUtil;

/**
 * 翼支付Bean工具类
 * @author huohb
 *
 */
public class BeanUtil {
	
	/**
	 * 填充父类公用属性
	 * @param bean
	 */
	public static void wrapBaseReqBean(EPayBaseRequestBean bean){
		String reqIp = GetLocalIpUtil.getInstance().getLocalIp();
        String reqSeq = BestPayUtil.getSerialNumber(BestPayUtil.getIpArray(reqIp)[3]);
        String platCode = RsaUtil.props.getString("platCode");
        String custCode = RsaUtil.props.getString("custCode");
        bean.setReqIp(reqIp);
        bean.setReqSeq(reqSeq);
        bean.setPlatCode(platCode);
        bean.setCustCode(custCode);
	}
	
	/**
	 * 根据对象生成要发送到翼支付的XML
	 * @param o
	 * @return
	 */
	public static String generateJsonToPost(Object o){
		return  BestPayUtil.getJSONFromXml(generateXMLToPost(o)).toString();
	}
	/**
	 * 根据对象生成要发送到翼支付的XML
	 * @param o
	 * @return
	 */
	public static String generateXMLToPost(Object o){
		StringBuffer xml = new StringBuffer();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        xml.append("<root>");
        // 商户平台编码
        xml.append("<platformCode>").append(RsaUtil.props.getString("platCode")).append("</platformCode>");
        // 公钥信息 Base64字符串
        try {
			xml.append("<cert>").append(RsaUtil.getPublicKey()).append("</cert>");
		} catch (GeneralSecurityException e1) {
			e1.printStackTrace();
		}
        String signXml = JaxbUtil.convertToNoDeclareXml(o);
        // 明文数据/签名数据.end
        String signStr="";
        // 得到签名串
		try {
			signStr = RsaUtil.sign(BestPayUtil.getJSONFromXml(signXml).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
        xml.append(signXml.toString());
        xml.append("<sign>").append(signStr).append("</sign>");
        xml.append("</root>");
        return xml.toString();
	}
	

}
