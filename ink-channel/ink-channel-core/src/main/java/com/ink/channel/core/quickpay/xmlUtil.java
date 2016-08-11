package com.ink.channel.core.quickpay;

import java.math.BigDecimal;
import java.util.Map;

public class xmlUtil {

	//String tr1XML = "";
	
	public String GetXMLmeothd(String version,String txnType,String interactiveStatus,String cardNo,String expiredDate,String cvv2,String amount,String merchantId,String terminalId,String idType,String cardHolderId,String entryTime,String externalRefNumber,String key,String value,String tr3Url){
		
		
		  StringBuilder tr1XML = new StringBuilder();  
		  
		  tr1XML.append("<?xml version='1.0' encoding='UTF-8'?>");
		  tr1XML.append("<MasMessage xmlns='http://www.99bill.com/mas_cnp_merchant_interface'>");
		  tr1XML.append("<version>"+version+"</version>");
		  tr1XML.append("<TxnMsgContent>");  
		  tr1XML.append("<txnType>"+txnType+"</txnType>");  
		  tr1XML.append("<interactiveStatus>"+interactiveStatus+"</interactiveStatus>");  
		  tr1XML.append("<cardNo>"+cardNo+"</cardNo>");  
		  tr1XML.append("<expiredDate>"+expiredDate+"</expiredDate>");  
		  tr1XML.append("<cvv2>"+cvv2+"</cvv2>");  
		  tr1XML.append("<amount>"+amount+"</amount>");  
		  tr1XML.append("<merchantId>"+merchantId+"</merchantId>");  
		  tr1XML.append("<terminalId>"+terminalId+"</terminalId>");  
		  tr1XML.append("<entryTime>"+entryTime+"</entryTime>");  
		  tr1XML.append("<idType>"+idType+"</idType>");  
		  tr1XML.append("<cardHolderId>"+cardHolderId+"</cardHolderId>");
		  tr1XML.append("<externalRefNumber>"+externalRefNumber+"</externalRefNumber>");
		  tr1XML.append("<key>"+key+"</key>");
		  tr1XML.append("<value>"+value+"</value>");
		  tr1XML.append("<tr3Url>"+tr3Url+"</tr3Url>");
		  tr1XML.append("</TxnMsgContent>");
		  tr1XML.append("</MasMessage>");
		  return tr1XML.toString();  
		   
	}
	
	public String GetCheXiaoXml(String version,String txnType,String orignalTxnType,String interactiveStatus,String amount,String merchantId,String terminalId,String entryTime,String externalRefNumber,String refNumber){
		
		StringBuilder getCheXtr1XML = new StringBuilder();  
		  
		getCheXtr1XML.append("<?xml version='1.0' encoding='UTF-8'?>");
		getCheXtr1XML.append("<MasMessage xmlns='http://www.99bill.com/mas_cnp_merchant_interface'>");
		getCheXtr1XML.append("<version>"+version+"</version>");
		getCheXtr1XML.append("<TxnMsgContent>");  
		getCheXtr1XML.append("<txnType>"+txnType+"</txnType>");  
		getCheXtr1XML.append("<orignalTxnType>"+orignalTxnType+"</orignalTxnType>");  
		getCheXtr1XML.append("<interactiveStatus>"+interactiveStatus+"</interactiveStatus>");   
		getCheXtr1XML.append("<amount>"+amount+"</amount>");  
		getCheXtr1XML.append("<merchantId>"+merchantId+"</merchantId>");  
		getCheXtr1XML.append("<terminalId>"+terminalId+"</terminalId>");  
		getCheXtr1XML.append("<entryTime>"+entryTime+"</entryTime>");  
		getCheXtr1XML.append("<externalRefNumber>"+externalRefNumber+"</externalRefNumber>");  
		getCheXtr1XML.append("<refNumber>"+refNumber+"</refNumber>");
		getCheXtr1XML.append("</TxnMsgContent>");
		getCheXtr1XML.append("</MasMessage>");
		return getCheXtr1XML.toString();  
		
	}
	
public String GetPaybackXml(String version,String txnType,String orignalTxnType,String origRefNumber,String interactiveStatus,String amount,String merchantId,String terminalId,String entryTime,String externalRefNumber){
		
		StringBuilder getCheXtr1XML = new StringBuilder();  
		  
		getCheXtr1XML.append("<?xml version='1.0' encoding='UTF-8'?>");
		getCheXtr1XML.append("<MasMessage xmlns='http://www.99bill.com/mas_cnp_merchant_interface'>");
		getCheXtr1XML.append("<version>"+version+"</version>");
		getCheXtr1XML.append("<TxnMsgContent>");  
		getCheXtr1XML.append("<txnType>"+txnType+"</txnType>");  
		getCheXtr1XML.append("<orignalTxnType>"+orignalTxnType+"</orignalTxnType>"); 
		getCheXtr1XML.append("<origRefNumber>"+origRefNumber+"</origRefNumber>"); 
		getCheXtr1XML.append("<interactiveStatus>"+interactiveStatus+"</interactiveStatus>");   
		getCheXtr1XML.append("<amount>"+amount+"</amount>");  
		getCheXtr1XML.append("<merchantId>"+merchantId+"</merchantId>");  
		getCheXtr1XML.append("<terminalId>"+terminalId+"</terminalId>");  
		getCheXtr1XML.append("<entryTime>"+entryTime+"</entryTime>");  
		getCheXtr1XML.append("<externalRefNumber>"+externalRefNumber+"</externalRefNumber>");  
		getCheXtr1XML.append("</TxnMsgContent>");
		getCheXtr1XML.append("</MasMessage>");
		return getCheXtr1XML.toString();  
		
	}
	public String GetQueryLSXml(String version,String externalRefNumber,String txnType,String merchantId){
	StringBuilder getQueryXML = new StringBuilder();  
	getQueryXML.append("<?xml version='1.0' encoding='UTF-8'?>");
	getQueryXML.append("<MasMessage xmlns='http://www.99bill.com/mas_cnp_merchant_interface'>");
	getQueryXML.append("<version>"+version+"</version>");
	getQueryXML.append("<QryTxnMsgContent>");  	
	getQueryXML.append("<externalRefNumber>"+externalRefNumber+"</externalRefNumber>");  
	getQueryXML.append("<txnType>"+txnType+"</txnType>");  
	getQueryXML.append("<merchantId>"+merchantId+"</merchantId>");  
	getQueryXML.append("</QryTxnMsgContent>");  
	getQueryXML.append("</MasMessage>");
	return getQueryXML.toString();  
}


	public String GetQueryXml(String version,String externalRefNumber,String txnType,String merchantId,String terminalId){
		StringBuilder getQueryXML = new StringBuilder();  
		getQueryXML.append("<?xml version='1.0' encoding='UTF-8'?>");
		getQueryXML.append("<MasMessage xmlns='http://www.99bill.com/mas_cnp_merchant_interface'>");
		getQueryXML.append("<version>"+version+"</version>");
		getQueryXML.append("<QryTxnMsgContent>");  
		getQueryXML.append("<externalRefNumber>"+externalRefNumber+"</externalRefNumber>");  
		getQueryXML.append("<txnType>"+txnType+"</txnType>");  
		getQueryXML.append("<merchantId>"+merchantId+"</merchantId>");  
		getQueryXML.append("<terminalId>"+terminalId+"</terminalId>");
		getQueryXML.append("</QryTxnMsgContent>");  
		getQueryXML.append("</MasMessage>");
		return getQueryXML.toString();  
	}
    
	public String getRefund(String amount,String entryTime,String externalRefNumber,String origRefNumber,String merchantId,String terminalId){
	    StringBuilder getCheXtr1XML = new StringBuilder();  
        
        getCheXtr1XML.append("<?xml version='1.0' encoding='UTF-8'?>");
        getCheXtr1XML.append("<MasMessage xmlns='http://www.99bill.com/mas_cnp_merchant_interface'>");
        getCheXtr1XML.append("<version>1.0</version>");
        getCheXtr1XML.append("<TxnMsgContent>");  
        getCheXtr1XML.append("<txnType>RFD</txnType>");  
        getCheXtr1XML.append("<interactiveStatus>TR1</interactiveStatus>");   
        getCheXtr1XML.append("<amount>"+amount+"</amount>");  
        getCheXtr1XML.append("<merchantId>"+merchantId+"</merchantId>");  
        getCheXtr1XML.append("<terminalId>"+terminalId+"</terminalId>");  
        getCheXtr1XML.append("<entryTime>"+entryTime+"</entryTime>");  
        getCheXtr1XML.append("<externalRefNumber>"+externalRefNumber+"</externalRefNumber>");  
        getCheXtr1XML.append("<origRefNumber>"+origRefNumber+"</origRefNumber>");
        getCheXtr1XML.append("</TxnMsgContent>");
        getCheXtr1XML.append("</MasMessage>");
        return getCheXtr1XML.toString(); 
	}
	
	public String getTxnQuery(String txnType,String externalRefNumber,String merchantId){
        StringBuilder txnQuery = new StringBuilder();  
        
        txnQuery.append("<?xml version='1.0' encoding='UTF-8'?>");
        txnQuery.append("<MasMessage xmlns='http://www.99bill.com/mas_cnp_merchant_interface'>");
        txnQuery.append("<version>1.0</version>");
        txnQuery.append("<QryTxnMsgContent>");  
        txnQuery.append("<txnType>"+txnType+"</txnType>");  
        txnQuery.append("<merchantId>"+merchantId+"</merchantId>");  
        txnQuery.append("<externalRefNumber>"+externalRefNumber+"</externalRefNumber>");  
        txnQuery.append("</QryTxnMsgContent>");
        txnQuery.append("</MasMessage>");
        return txnQuery.toString(); 
    }
	
	public String getCardinfoQuery(String txnType,String cardNo){
        StringBuilder txnQuery = new StringBuilder();  
        
        txnQuery.append("<?xml version='1.0' encoding='UTF-8'?>");
        txnQuery.append("<MasMessage xmlns='http://www.99bill.com/mas_cnp_merchant_interface'>");
        txnQuery.append("<version>1.0</version>");
        txnQuery.append("<QryCardContent>");  
        txnQuery.append("<txnType>"+txnType+"</txnType>");  
        txnQuery.append("<cardNo>"+cardNo+"</cardNo>");  
        txnQuery.append("</QryCardContent>");
        txnQuery.append("</MasMessage>");
        return txnQuery.toString(); 
    }
	
	public String getUnbind(String merchantId,String customerId,String pan,String storablePan,String bankId){
	  //Tr1����ƴ��
	    String str1Xml = "";
	        str1Xml += "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	        str1Xml += "<MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\">";
	        str1Xml += "<version>1.0</version>";
	        str1Xml += "<PciDeleteContent>";
	        str1Xml += "<merchantId>"+merchantId+"</merchantId>";
	        str1Xml += "<customerId>" + customerId + "</customerId>";
	        str1Xml += "<pan>" + pan + "</pan>";
	        str1Xml += "<storablePan>" + storablePan + "</storablePan>";
	        str1Xml += "<bankId>" + bankId + "</bankId>";
	        str1Xml += "</PciDeleteContent>";
	        str1Xml += "</MasMessage>";
	        return str1Xml;
	}
	
	/**
	 * 付款到银行账户
	 * @return
	 */
	public String getPayToBank(Map<String,Object> param){
		StringBuffer xmlBuf = new StringBuffer();
		xmlBuf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xmlBuf.append("<MasMessage xmlns=\"http://www.99bill.com/mas_cnp_merchant_interface\">");
		xmlBuf.append("<version>1.0</version>");
		xmlBuf.append("<BankRequestBean>");
		xmlBuf.append("<amount>").append((BigDecimal)param.get("amount")).append("</amount>");// 金额
		xmlBuf.append("<bankCardNumber>").append(param.get("bankCardNo")).append("</bankCardNumber>");// 银行卡号
		xmlBuf.append("<bankName>").append(param.get("bankName")).append("</bankName>");// 银行名称
		xmlBuf.append("<creditName>").append(param.get("creditName")).append("</creditName>");// 收款人姓名
		xmlBuf.append("<description>").append(param.get("description")).append("</description>");// 交易备注
		xmlBuf.append("<kaihuhang>").append(param.get("kaihuhang")).append("</kaihuhang>");// 开户行
		xmlBuf.append("<mac>").append(param.get("mac")).append("</mac>");// 加密字符串，用来安全校验
		xmlBuf.append("<orderId>").append(param.get("orderId")).append("</orderId>");// 订单号
		xmlBuf.append("<province_city>").append(param.get("province_city")).append("</province_city>");// 银行卡所在城市
		xmlBuf.append("</BankRequestBean>");
		xmlBuf.append("</MasMessage>");
		return xmlBuf.toString();
	}
	
	
	public static void main(String[] args) throws Exception {
    	
       
        // 本地起的https服务
        String httpsUrl = "https://sandbox.99bill.com:9445/cnp/void";
        String tuiurl = "https://sandbox.99bill.com:9445/cnp/refund";
        // 传输文本
        String xmlStr = "<?xml version='1.0' encoding='UTF-8'?><MasMessage xmlns='http://www.99bill.com/mas_cnp_merchant_interface'><version>1.0</version><TxnMsgContent><txnType>PUR</txnType><interactiveStatus>TR1</interactiveStatus><cardNo>4380880000000007</cardNo><expiredDate>0117</expiredDate><cvv2>222</cvv2><amount>100</amount><merchantId>812051545110011</merchantId><terminalId>00006034</terminalId><entryTime>20151015175458</entryTime><idType>0</idType><cardHolderId>320981198207085841</cardHolderId><externalRefNumber>2015101787575489</externalRefNumber><key>cellPhone</key><value>13655163768</value><tr3Url>http://localhost:8090/CNP/resultTr3.jsp</tr3Url></TxnMsgContent></MasMessage>";
        String xmlStr1 = "<?xml version='1.0' encoding='UTF-8'?><MasMessage xmlns='http://www.99bill.com/mas_cnp_merchant_interface'><version>1.0</version><TxnMsgContent><txnType>VTX</txnType><orignalTxnType>PUR</orignalTxnType><interactiveStatus>TR1</interactiveStatus><amount>100</amount><merchantId>812051545110011</merchantId><terminalId>00006034</terminalId><entryTime>20151022185255</entryTime><externalRefNumber>20151022172756</externalRefNumber><refNumber>000012577855</refNumber><tr3Url>http://101.227.69.165:8801/CNP/resultTr3.jsp</tr3Url></TxnMsgContent></MasMessage>";
        String xmlStr2 = "<?xml version='1.0' encoding='UTF-8' standalone='yes'?><MasMessage xmlns='http://www.99bill.com/mas_cnp_merchant_interface'><version>1.0</version><TxnMsgContent><txnType>PUR</txnType><interactiveStatus>TR2</interactiveStatus><amount>100</amount><merchantId>812051545110011</merchantId><terminalId>00006034</terminalId><entryTime>20151023143945</entryTime><externalRefNumber>20151023143945</externalRefNumber><transTime>20151023143947</transTime><refNumber>000012580250</refNumber><responseCode>00</responseCode><responseTextMessage>交易成功</responseTextMessage><cardOrg>CU</cardOrg><issuer>中国银行</issuer><storableCardNo>4380880007</storableCardNo><authorizationCode>389925</authorizationCode></TxnMsgContent></MasMessage>";
        String respXmlCut = xmlStr2.substring(xmlStr2.indexOf("<TxnMsgContent>"),xmlStr2.indexOf("</TxnMsgContent>"));
        // 发起请求000012578395
        //CheXiaoPost.sendPost(tuiurl, xmlStr2);
        System.out.println("respXmlCut"+respXmlCut);
    }
	
}
