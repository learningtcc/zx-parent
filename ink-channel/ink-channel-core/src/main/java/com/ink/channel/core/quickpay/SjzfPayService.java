package com.ink.channel.core.quickpay;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
@Deprecated
public class SjzfPayService {
    private final static Logger LOGGER = LoggerFactory.getLogger(SjzfPayService.class);
    
    public SjzfPayService(){
        System.setProperty("jsse.enableSNIExtension", "false"); 
    }
    /**
     * 鉴权获取短信验证码
     * @param xml
     * @return
     */
    public String getValidateCode(String xml) {
        String url = Configuration.getInstance().getValue("validateCode");
        LOGGER.debug("URL:"+url);
        LOGGER.debug("开始执行方法 getValidateCode(String xml)");
        LOGGER.debug("鉴权并获取验证码：  " + xml);
        String result = "";
        try {
            result = Post.sendPost(url, xml);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        LOGGER.debug("鉴权结果：" + result);
        return result;
    }
    /**
     * 银行卡鉴权
     * 前置条件获取鉴权短信验证
     * @param xml
     * @return
     */
    public String authentication(String xml) {
        String url = Configuration.getInstance().getValue("authURL");
        LOGGER.debug("开始执行方法 authentication(String xml)");
        LOGGER.debug("鉴权内容：  " + xml);
        String result = "";
        try {
            result = Post.sendPost(url, xml);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
        LOGGER.debug("鉴权结果：" + result);
        return result;
    }
   /**
    * 鉴权完毕之后一键支付
    * @param xml
    * @return
    */
   public String onePay(String xml){
       String url=Configuration.getInstance().getValue("onePay");
       LOGGER.debug("开始执行方法 onePay(String xml)");
       LOGGER.debug("url:"+url);
       LOGGER.debug("交易内容：  " + xml);
       String result = "";
       try {
           result = Post.sendPost(url, xml);
       } catch (Exception e) {
           e.printStackTrace();
           LOGGER.error(e.getMessage());
       }
       LOGGER.debug("交易结果：" + result);
       return result;
   }
   /**
    * quickAuth 快捷支付鉴权
    * @param xml
    * @return
    */
   public String quickAuth(String xml){
       String quickAuth=Configuration.getInstance().getValue("quickAuth");
       LOGGER.debug("开始执行方法 quickAuth(String xml)");
       LOGGER.debug("快捷支付鉴权内容：  " + xml);
       String result = "";
       try {
           result = Post.sendPost(quickAuth, xml);
       } catch (Exception e) {
           e.printStackTrace();
           LOGGER.error(e.getMessage());
       }
       LOGGER.debug("鉴权结果：" + result);
       return result;
   }
   /**
    * 快捷支付
    * 前置条件需要快捷支付鉴权之后 
    * @param xml
    * @return
    */
   public String quickPay(String xml){
       String quickUrl=Configuration.getInstance().getValue("quickPayURL");
       LOGGER.debug("开始执行方法 quickPay(String xml)");
       LOGGER.debug("交易内容：  " + xml);
       String result = "";
       try {
           result = Post.sendPost(quickUrl, xml);
       } catch (Exception e) {
           e.printStackTrace();
           LOGGER.error(e.getMessage());
       }
       LOGGER.debug("交易结果：" + result);
       return result;
   }
   /**
    * 退货(退非当天的订单)
    * @param xml
    * @return
    */
   public String refund(String xml){
       String refundUrl=Configuration.getInstance().getValue("refundURL");
       LOGGER.debug("开始执行方法 refund(String xml)");
       LOGGER.debug("取消订单内容：  " + xml);
       String result = "";
       try {
           result = Post.sendPost(refundUrl, xml);
       } catch (Exception e) {
           e.printStackTrace();
           LOGGER.error(e.getMessage());
       }
       LOGGER.debug("操作结果：" + result);
       return result;
   }
   /**
    * 撤销交易（只能撤销是当天发生的交易）
    * @return
    */
   public String unDo(String xml ){
       String unDoURL=Configuration.getInstance().getValue("unDoURL");
       LOGGER.debug("开始执行方法 refund(String xml)");
       LOGGER.debug("取消订单内容：  " + xml);
       String result = "";
       try {
           result = Post.sendPost(unDoURL, xml);
       } catch (Exception e) {
           e.printStackTrace();
           LOGGER.error(e.getMessage());
       }
       LOGGER.debug("操作结果：" + result);
       return result;
   }
   /**
    * 交易记录查询
    * @param xml
    * @return
    */
   public String txnQuery(String xml){
       String txnQueryURL=Configuration.getInstance().getValue("txnQueryURL");
       LOGGER.debug("开始执行方法 txnQuery(String xml)");
       LOGGER.debug("查询订单内容：  " + xml);
       String result = "";
       try {
           result = Post.sendPost(txnQueryURL, xml);
       } catch (Exception e) {
           e.printStackTrace();
           LOGGER.error(e.getMessage());
       }
       LOGGER.debug("操作结果：" + result);
       return result;
   }
   /**
    * 银行卡信息查询
    * @param xml
    * @return
    */
   public String cardInfoQuery(String xml){
       String cardQueryURL=Configuration.getInstance().getValue("cardInfoQueryURL");
       LOGGER.debug("开始执行方法 cardInfoQuery(String xml)");
       LOGGER.debug("查询内容：  " + xml);
       String result = "";
       try {
           result = Post.sendPost(cardQueryURL, xml);
       } catch (Exception e) {
           e.printStackTrace();
           LOGGER.error(e.getMessage());
       }
       LOGGER.debug("操作结果：" + result);
       return result;
   }
   /**
    * 解绑
    * @param xml
    * @return
    */
   public String unbind(String xml){
       String unBind=Configuration.getInstance().getValue("unBindURL");
       LOGGER.debug("开始执行方法 unbind(String xml)");
       LOGGER.debug("解操绑定内容：  " + xml);
       String result = "";
       try {
           result = Post.sendPost(unBind, xml);
       } catch (Exception e) {
           e.printStackTrace();
           LOGGER.error(e.getMessage());
       }
       LOGGER.debug("操作结果：" + result);
       return result;
   }
/**
 * 快捷支付再次支付（一键支付升级版）
 * @return
 */
  public String quickPayAgain(String xml){
      String quickPayAgainURL=Configuration.getInstance().getValue("quickPayURL");
      LOGGER.debug("开始执行再次支付方法 quickPayAgain(String xml)");
      LOGGER.debug("支付内容：  " + xml);
      String result = "";
      try {
          result = Post.sendPost(quickPayAgainURL, xml);
      } catch (Exception e) {
          e.printStackTrace();
          LOGGER.error(e.getMessage());
      }
      LOGGER.debug("操作结果：" + result);
      return result;
  }
  /**
   * 付款到银行卡账户
   * @param xml
   * @return
   */
  public String payToBank(String xml){
	  String payToBankURL=Configuration.getInstance().getValue("payToBank");
      LOGGER.debug("开始执行付款到银行卡账户方法 payToBank(String xml)");
      LOGGER.debug("请求内容：  " + xml);
      String result = "";
      try {
          result = Post.sendPost(payToBankURL, xml);
      } catch (Exception e) {
          e.printStackTrace();
          LOGGER.error(e.getMessage());
      }
      LOGGER.debug("操作结果：" + result);
      return result;
  }
}
