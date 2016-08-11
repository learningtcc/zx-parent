package com.ink.channel.core.epro;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import java.util.HashMap;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 投资通接口范例--API版
 * 
 * @author ：zhouxiang
 * @since ：2015.11.2
 */
@Deprecated
public class TZTService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TZTService.class);
   // private static Log LOGGER = LogFactory.getLog(TZTService.class);
    /**
     * 取得商户编号
     */
    public static String getMerchantAccount() {
        return Configuration.getInstance().getValue("merchantAccount");
    }

    /**
     * 取得商户私钥
     */
    public static String getMerchantPrivateKey() {
        return Configuration.getInstance().getValue("merchantPrivateKey");
    }

    /**
     * 取得商户AESKey
     */
    public static String getMerchantAESKey() {
        return (RandomUtil.getRandom(16));
    }

    /**
     * 取得易宝公玥
     */
    public static String getYeepayPublicKey() {
        return Configuration.getInstance().getValue("yeepayPublicKey");
    }

    /**
     * 格式化字符串
     */
    public static String formatString(String text) {
        return (text == null ? "" : text.trim());
    }

    /**
     * String2Integer
     */
    public static int String2Int(String text) throws NumberFormatException {
        return text == null ? 0 : Integer.valueOf(text);
    }

    /**
     * 绑卡请求接口请求地址
     */
    public static String getBindBankcardURL() {
        return Configuration.getInstance().getValue("bindBankcardURL");
    }

    /**
     * 绑卡确认接口请求地址
     */
    public static String getConfirmBindBankcardURL() {
        return Configuration.getInstance().getValue("confirmBindBankcardURL");
    }

    /**
     * 支付接口请求地址
     */
    public static String getDirectBindPayURL() {
        return Configuration.getInstance().getValue("directBindPayURL");
    }

    /**
     * 订单查询请求地址
     */
    public static String getPaymentQueryURL() {
        return Configuration.getInstance().getValue("paymentQueryURL");
    }

    /**
     * 取现接口请求地址
     */
    public static String getWithdrawURL() {
        return Configuration.getInstance().getValue("withdrawURL");
    }

    /**
     * 取现查询请求地址
     */
    public static String getQueryWithdrawURL() {
        return Configuration.getInstance().getValue("queryWithdrawURL");
    }

    /**
     * 取现查询请求地址
     */
    public static String getQueryAuthbindListURL() {
        return Configuration.getInstance().getValue("queryAuthbindListURL");
    }

    /**
     * 银行卡信息查询请求地址
     */
    public static String getBankCardCheckURL() {
        return Configuration.getInstance().getValue("bankCardCheckURL");
    }

    /**
     * 支付清算文件下载请求地址
     */
    public static String getPayClearDataURL() {
        return Configuration.getInstance().getValue("payClearDataURL");
    }

    /**
     * 单笔退款请求地址
     */
    public static String getRefundURL() {
        return Configuration.getInstance().getValue("refundURL");
    }

    /**
     * 退款查询请求地址
     */
    public static String getRefundQueryURL() {
        return Configuration.getInstance().getValue("refundQueryURL");
    }

    /**
     * 退款清算文件请求地址
     */
    public static String getRefundClearDataURL() {
        return Configuration.getInstance().getValue("refundClearDataURL");
    }

    /**
     * 银行卡解绑接口请求地址
     */
    public static String getUnbindBankcardURL() {
        return Configuration.getInstance().getValue("unbindBankcardURL");
    }

    /**
     * 4.4 支付结果查询请求地址
     */
    public static String getPayapiQueryURL() {
        return Configuration.getInstance().getValue("payapiQueryURL");
    }

    /**
     * 4.2.1 支付请求接口请求地址
     */
    public static String getPayNeedSmsURL() {
        return Configuration.getInstance().getValue("payNeedSmsURL");
    }

    /**
     * 4.2.2 发送短信验证码接口请求地址
     */
    public static String getSmsSendURL() {
        return Configuration.getInstance().getValue("smsSendURL");
    }

    /**
     * 4.2.3 确认支付请求地址
     */
    public static String getSmsConfirmURL() {
        return Configuration.getInstance().getValue("smsConfirmURL");
    }

    /**
     * 余额查询接口请求地址
     */
    public static String getDrawValidAmountURL() {
        return Configuration.getInstance().getValue("drawValidAmountURL");
    }

    /**
     * 解析http请求返回
     */
    public static Map<String, String> parseHttpResponseBody(int statusCode, String responseBody) throws Exception {

        String merchantPrivateKey = getMerchantPrivateKey();
        String yeepayPublicKey = getYeepayPublicKey();

        Map<String, String> result = new HashMap<String, String>();
        String customError = "";

        if (statusCode != 200) {
            customError = "Request failed, response code : " + statusCode;
            result.put("customError", customError);
            return (result);
        }

        Map<String, String> jsonMap = JSON.parseObject(responseBody, new TypeReference<TreeMap<String, String>>() {
        });

        if (jsonMap.containsKey("error_code")) {
            result = jsonMap;
            return (result);
        }
        String dataFromYeepay = formatString(jsonMap.get("data"));
        String encryptkeyFromYeepay = formatString(jsonMap.get("encryptkey"));
        // 检验数字签名
        boolean signMatch = EncryUtil.checkDecryptAndSign(dataFromYeepay, encryptkeyFromYeepay, yeepayPublicKey,
                        merchantPrivateKey);
        if (!signMatch) {
            customError = "Sign not match error";
            result.put("customError", customError);
            return (result);
        }
        // 解密易宝公钥
        String yeepayAESKey = RSA.decrypt(encryptkeyFromYeepay, merchantPrivateKey);
        // 解密报文
        String decryptData = AES.decryptFromBase64(dataFromYeepay, yeepayAESKey);
        result = JSON.parseObject(decryptData, new TypeReference<TreeMap<String, String>>() {
        });

        return (result);
    }

    /**
     * bindBankcard() : 绑卡请求接口
     * 
     * @param 必须参数
     *            ：
     * @param identityid
     *            用户标识
     * @param identitytype
     *            用户标识类型 0： IMEI 1： MAC 地址 2：用户 ID 3：用户 Email 4：用户手机号 5：用户身份证号6：用户纸质订单协议号
     * @param cardno
     *            银行卡号
     * @param idcardtype
     *            证件类型 固定值 01
     * @param idcardno
     *            证件号
     * @param username
     *            持卡人姓名
     * @param phone
     *            银行预留手机号
     * 
     */
    public Map<String, String> bindBankCard(Map<String, String> params) {
        LOGGER.debug("##### bindBankcard()  #####");
        String bindBankcardURL = getBindBankcardURL();
        params.put("merchantaccount", getMerchantAccount());
        return this.executePostMethod(bindBankcardURL, params);

    }

    /**
     * cofirmBindBankcard() : 4.1.2 确定绑卡接口
     * 
     * @param 必须参数
     *            ：
     * @param requestid
     *            绑卡请求号
     * @param validatecode
     *            短信验证码
     * @return
     */

    public Map<String, String> confirmBindBankcard(Map<String, String> params) {
        LOGGER.debug("##### confirmBindBankcard() #####");
        String confirmBindBankcardURL = getConfirmBindBankcardURL();
        LOGGER.debug("confirmBindBankcardURL : " + confirmBindBankcardURL);
        params.put("merchantaccount", getMerchantAccount());
        return this.executePostMethod(confirmBindBankcardURL, params);
    }

    /**
     * directBindPay() : 4.3 支付接口--不发送短验
     * 
     * @param 必填参数
     *            ：
     * @param orderid
     *            商户订单号
     * @param transtime
     *            交易时间
     * @param amount
     *            交易金额
     * @param productname
     *            商品名称
     * @param identityid
     *            用户标识
     * @param identitytype
     *            用户标识类型
     * @param card_top
     *            卡号前 6 位
     * @param card_last
     *            卡号后 4 位
     * @param callbackurl
     *            回调地址
     * @param userip
     *            用户请求ip
     * @return
     */
    public Map<String, String> directBindPay(Map<String, String> params) {
        LOGGER.debug("##### directBindPay() #####");
        String directBindPayURL = getDirectBindPayURL();
        params.put("merchantaccount", getMerchantAccount());
        return this.executePostMethod(directBindPayURL, params);
    }

    /**
     * decryptCallbackData() : 解密支付回调参数data
     *
     */

    public static Map<String, String> decryptCallbackData(String data, String encryptkey) {

        LOGGER.debug("##### decryptCallbackData() #####");
        String merchantaccount = getMerchantAccount();
        String merchantPrivateKey = getMerchantPrivateKey();
        String merchantAESKey = getMerchantAESKey();
        String yeepayPublicKey = getYeepayPublicKey();
        LOGGER.debug("data : " + data);
        LOGGER.debug("encryptkey : " + encryptkey);
        Map<String, String> callbackResult = new HashMap<String, String>();
        String customError = "";
        try {
            boolean signMatch = EncryUtil.checkDecryptAndSign(data, encryptkey, yeepayPublicKey, merchantPrivateKey);
            if (!signMatch) {
                customError = "Sign not match error";
                callbackResult.put("customError", customError);
                return callbackResult;
            }
            String yeepayAESKey = RSA.decrypt(encryptkey, merchantPrivateKey);
            String decryptData = AES.decryptFromBase64(data, yeepayAESKey);
            callbackResult = JSON.parseObject(decryptData, new TypeReference<TreeMap<String, String>>() {
            });
        } catch (Exception e) {
            customError = "Caught an Exception. " + e.toString();
            callbackResult.put("customError", customError);
            e.printStackTrace();
        }
        LOGGER.debug("callbackResult : " + callbackResult);
        return (callbackResult);
    }

    /**
     * queryByOrder() : 5.1 交易记录查询接口
     * 
     * @param orderid
     *            客户订单号
     * @param yborderid
     *            易宝交易流水号
     * @return
     */
    public Map<String, String> queryByOrder(String orderid, String yborderid) {
        LOGGER.debug("##### queryByOrder() #####");
        String merchantaccount = getMerchantAccount();
        String paymentQueryURL = getPaymentQueryURL();
        Map<String, String> params = new HashMap<String, String>();
        params.put("merchantaccount", merchantaccount);
        params.put("orderid", orderid);
        params.put("yborderid", yborderid);
        return this.executeGetMethod(paymentQueryURL, params);
    }

    /**
     * withdraw() : 提现接口
     * 
     * @param params
     * @param requestid
     *            商 户提现订单请求号
     * @param identityid
     *            用户标识
     * @param identitytype
     *            用户标识类型
     * @param card_top
     *            银行卡前6位
     * @param card_last
     *            银行卡后4位
     * @param amount
     *            提现金额
     * @param currency
     *            币种 人民币 156
     * @return
     */
    public static Map<String, String> withdraw(Map<String, String> params) {

        LOGGER.debug("##### withdraw() #####");
        Map<String, String> result = new HashMap<String, String>();
        String customError = ""; // 自定义，非接口返回

        String merchantaccount = getMerchantAccount();
        String merchantPrivateKey = getMerchantPrivateKey();
        String merchantAESKey = getMerchantAESKey();
        String yeepayPublicKey = getYeepayPublicKey();
        String withdrawURL = getWithdrawURL();

        String requestid = formatString(params.get("requestid"));
        String identityid = formatString(params.get("identityid"));
        String card_top = formatString(params.get("card_top"));
        String card_last = formatString(params.get("card_last"));
        String drawtype = formatString(params.get("drawtype"));
        String imei = formatString(params.get("imei"));
        String userip = formatString(params.get("userip"));
        String ua = formatString(params.get("ua"));

        int amount = 0;
        int identitytype = 0;
        int currency = 0;
        try {
            if (params.get("identitytype") == null) {
                throw new Exception("identitytype is null!!!!!");
            } else {
                identitytype = String2Int(params.get("identitytype"));
            }

            if (params.get("amount") == null) {
                throw new Exception("amount is null!!!!!");
            } else {
                amount = String2Int(params.get("amount"));
            }

            currency = String2Int(params.get("currency"));

        } catch (Exception e) {
            StringBuffer buffer = new StringBuffer();
            buffer.append("error - the following parameters must be int  : ");
            buffer.append("[amount = " + formatString(params.get("amount")) + "], ");
            buffer.append("[identitytype = " + formatString(params.get("identitytype")) + "], ");
            buffer.append("[currency = " + formatString(params.get("currency")) + "]. ");
            e.printStackTrace();
            customError = buffer.toString();
            result.put("customError", customError);
            return (result);
        }

        TreeMap<String, Object> dataMap = new TreeMap<String, Object>();
        dataMap.put("merchantaccount", merchantaccount);
        dataMap.put("requestid", requestid);
        dataMap.put("identityid", identityid);
        dataMap.put("identitytype", identitytype);
        dataMap.put("card_top", card_top);
        dataMap.put("card_last", card_last);
        dataMap.put("amount", amount);
        dataMap.put("currency", currency);
        dataMap.put("drawtype", drawtype);
        dataMap.put("imei", imei);
        dataMap.put("userip", userip);
        dataMap.put("ua", ua);

        String sign = EncryUtil.handleRSA(dataMap, merchantPrivateKey);
        dataMap.put("sign", sign);

        LOGGER.debug("withdrawURL : " + withdrawURL);
        LOGGER.debug("dataMap : " + dataMap);

        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(withdrawURL);

        try {
            String jsonStr = JSON.toJSONString(dataMap);
            String data = AES.encryptToBase64(jsonStr, merchantAESKey);
            String encryptkey = RSA.encrypt(merchantAESKey, yeepayPublicKey);

            NameValuePair[] datas = { new NameValuePair("merchantaccount", merchantaccount),
                            new NameValuePair("data", data), new NameValuePair("encryptkey", encryptkey) };

            postMethod.setRequestBody(datas);
            int statusCode = httpClient.executeMethod(postMethod);
            byte[] responseByte = postMethod.getResponseBody();
            String responseBody = new String(responseByte, "UTF-8");
            LOGGER.debug("responseBody : " + responseBody);
            result = parseHttpResponseBody(statusCode, responseBody);

        } catch (Exception e) {
            customError = "Caught an Exception. " + e.toString();
            result.put("customError", customError);
            e.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }
        LOGGER.debug("result : " + result);
        return (result);
    }

    /**
     * queryWithdraw() : 提现查询接口
     * 
     * @param requestid
     *            商户提现订单号
     * @param ybdrawflowid
     *            易 宝 流 水号
     * @return
     */
    public static Map<String, String> queryWithdraw(String requestid, String ybdrawflowid) {

        LOGGER.debug("##### queryWithdraw() #####");

        String merchantaccount = getMerchantAccount();
        String merchantPrivateKey = getMerchantPrivateKey();
        String merchantAESKey = getMerchantAESKey();
        String yeepayPublicKey = getYeepayPublicKey();
        String queryWithdrawURL = getQueryWithdrawURL();

        TreeMap<String, Object> dataMap = new TreeMap<String, Object>();
        dataMap.put("merchantaccount", merchantaccount);
        dataMap.put("requestid", requestid);
        dataMap.put("ybdrawflowid", ybdrawflowid);

        String sign = EncryUtil.handleRSA(dataMap, merchantPrivateKey);
        dataMap.put("sign", sign);

        LOGGER.debug("requestid : " + requestid + "ybdrawflowid : " + ybdrawflowid + "merchantaccount : "
                        + merchantaccount);
        LOGGER.debug("merchantPrivateKey : " + merchantPrivateKey + "yeepayPublicKey : " + yeepayPublicKey);
        LOGGER.debug("queryWithdrawURL : " + queryWithdrawURL);
        LOGGER.debug("dataMap : " + dataMap);

        Map<String, String> result = new HashMap<String, String>();
        String customError = ""; // 自定义参数，非接口返回。

        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod();
        try {
            String jsonStr = JSON.toJSONString(dataMap);
            String data = AES.encryptToBase64(jsonStr, merchantAESKey);
            String encryptkey = RSA.encrypt(merchantAESKey, yeepayPublicKey);

            String url = queryWithdrawURL + "?merchantaccount=" + URLEncoder.encode(merchantaccount, "UTF-8")
                            + "&data=" + URLEncoder.encode(data, "UTF-8") + "&encryptkey="
                            + URLEncoder.encode(encryptkey, "UTF-8");

            getMethod = new GetMethod(url);
            int statusCode = httpClient.executeMethod(getMethod);
            String responseBody = getMethod.getResponseBodyAsString();
            result = parseHttpResponseBody(statusCode, responseBody);

        } catch (Exception e) {
            customError = "Caught an Exception. " + e.toString();
            result.put("customError", customError);
            e.printStackTrace();
        } finally {
            getMethod.releaseConnection();
        }
        LOGGER.debug("result : " + result);
        return (result);
    }

    /**
     * queryAuthbindList() : 绑卡查询接口
     * 
     * @param identityid
     *            用户标识
     * @param identitytype
     *            用户标识类型
     * @return
     */
    public Map<String, String> queryAuthbindList(String identityid, String identitytype) {
       LOGGER.debug("##### queryAuthbindList() #####");
        String queryAuthbindListURL = getQueryAuthbindListURL();
        Map<String, String> params = new HashMap<String, String>();
        params.put("merchantaccount", getMerchantAccount());
        params.put("identityid", identityid);
        params.put("identitytype", identitytype);
        return this.executeGetMethod(queryAuthbindListURL, params);
    }

    /**
     * bankCardCheck() : 银行卡信息查询接口
     * @param cardno 银行卡号
     * @return
     */
    public Map<String, String> bankCardCheck(String cardno) {
        LOGGER.debug("##### bankCardCheck() #####");
        String bankCardCheckURL = getBankCardCheckURL();
        Map<String, String> params = new HashMap<String, String>();
        params.put("merchantaccount", getMerchantAccount());
        params.put("cardno", cardno);
        return this.executePostMethod(bankCardCheckURL, params);
    }

    /**
     * getPathOfPayClearData()：获取清算数据
     *
     * 返回说明：
     *
     * filePath - 批量查询结果文件的路径 error_code - 错误返回码 error - 错误信息 customError - 自定义，非接口返回
     *
     */

    public static Map<String, String> getPathOfPayClearData(String startdate, String enddate, String sysPath) {

        LOGGER.debug("##### getPathOfPayClearData() #####");
        String merchantaccount = getMerchantAccount();
        String merchantPrivateKey = getMerchantPrivateKey();
        String merchantAESKey = getMerchantAESKey();
        String yeepayPublicKey = getYeepayPublicKey();
        String payClearDataURL = getPayClearDataURL();

        TreeMap<String, Object> dataMap = new TreeMap<String, Object>();
        dataMap.put("merchantaccount", merchantaccount);
        dataMap.put("startdate", startdate);
        dataMap.put("enddate", enddate);

        String sign = EncryUtil.handleRSA(dataMap, merchantPrivateKey);
        dataMap.put("sign", sign);

        Map<String, String> queryResult = new HashMap<String, String>();
        String filePath = "";
        String error_code = "";
        String error = "";
        String customError = "";

        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod();

        try {
            String jsonStr = JSON.toJSONString(dataMap);
            String data = AES.encryptToBase64(jsonStr, merchantAESKey);
            String encryptkey = RSA.encrypt(merchantAESKey, yeepayPublicKey);

            String url = payClearDataURL + "?merchantaccount=" + URLEncoder.encode(merchantaccount, "UTF-8") + "&data="
                            + URLEncoder.encode(data, "UTF-8") + "&encryptkey="
                            + URLEncoder.encode(encryptkey, "UTF-8");

            getMethod = new GetMethod(url);

            int statusCode = httpClient.executeMethod(getMethod);

            if (statusCode != 200) {
                customError = "Get request failed, response code = " + statusCode;
                queryResult.put("customError", customError);
                return (queryResult);
            }

            InputStream responseStream = getMethod.getResponseBodyAsStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream, "UTF-8"));
            // BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream));

            String line = reader.readLine();
            if (line.startsWith("{")) {
                Map<String, Object> jsonMap = JSON.parseObject(line, TreeMap.class);

                if (jsonMap.containsKey("error_code")) {
                    error_code = formatString((String) jsonMap.get("error_code"));
                    error = formatString((String) jsonMap.get("error"));
                } else {
                    String dataFromYeepay = formatString((String) jsonMap.get("data"));
                    String encryptkeyFromYeepay = formatString((String) jsonMap.get("encryptkey"));

                    String yeepayAESKey = RSA.decrypt(encryptkeyFromYeepay, merchantPrivateKey);
                    String decryptData = AES.decryptFromBase64(dataFromYeepay, yeepayAESKey);
                    Map<String, Object> decryptDataMap = JSON.parseObject(decryptData, TreeMap.class);

                    error_code = formatString((String) decryptDataMap.get("error_code"));
                    error = formatString((String) decryptDataMap.get("error"));

                    LOGGER.debug("decryptData : " + decryptData);
                }
            } else {
                String outputFilePath = sysPath + File.separator + "clearData";
                File file = new File(outputFilePath);
                file.mkdir();

                String time = String.valueOf(System.currentTimeMillis());
                String fileName = "payClearData_" + startdate + "_" + enddate + "_" + time + ".txt";
                String absolutePathOfOutputFile = outputFilePath + File.separator + fileName;
                filePath = absolutePathOfOutputFile;

                File outputFile = new File(absolutePathOfOutputFile);
                FileWriter fileWriter = new FileWriter(outputFile);
                BufferedWriter writer = new BufferedWriter(fileWriter);

                LOGGER.debug("filePath : " + filePath);

                writer.write(line);
                writer.write(System.getProperty("line.separator"));
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.write(System.getProperty("line.separator"));
                }

                writer.close();
            }
        } catch (Exception e) {
            customError = "Caught an Exception. " + e.toString();
            e.printStackTrace();
        } finally {
            getMethod.releaseConnection();
        }

        queryResult.put("filePath", filePath);
        queryResult.put("error_code", error_code);
        queryResult.put("error", error);
        queryResult.put("customError", customError);

        return (queryResult);
    }

    /**
     * refund() : 单笔退款方法
     */
    /**
     * 
     * @param params
     * @param amount 退款金额
     * @param currency 交易币种 人民币：156
     * @param orderid 唯一退货订单号，最长 50位
     * @param origyborderid 原易宝交易流水号
     * @return
     */
    public Map<String, String> refund(Map<String, String> params) {
        LOGGER.debug("##### refund() #####");
        String refundURL = getRefundURL();
        params.put("merchantaccount", getMerchantAccount());
        return this.executePostMethod(refundURL, params);
    }

    /**
     *  refundQuery() : 退款查询
     * @param orderid 客户退货订单号
     * @return
     */
    public Map<String, String> refundQuery(String orderid) {

        LOGGER.debug("##### refundQuery() #####");
        String merchantaccount = getMerchantAccount();
        String refundQueryURL = getRefundQueryURL();

        Map<String, String> params = new HashMap<String, String>();
        params.put("merchantaccount", merchantaccount);
        params.put("orderid", orderid);
        return this.executeGetMethod(refundQueryURL, params);
    }

    /**
     * getPathOfRefundClearData()
     *
     */

    public static Map<String, String> getPathOfRefundClearData(String startdate, String enddate, String sysPath) {

        LOGGER.debug("##### getPathOfRefundClearData() #####");
        String merchantaccount = getMerchantAccount();
        String merchantPrivateKey = getMerchantPrivateKey();
        String merchantAESKey = getMerchantAESKey();
        String yeepayPublicKey = getYeepayPublicKey();
        String refundClearDataURL = getRefundClearDataURL();

        TreeMap<String, Object> dataMap = new TreeMap<String, Object>();
        dataMap.put("merchantaccount", merchantaccount);
        dataMap.put("startdate", startdate);
        dataMap.put("enddate", enddate);

        String sign = EncryUtil.handleRSA(dataMap, merchantPrivateKey);
        dataMap.put("sign", sign);

        LOGGER.debug("refundClearDataURL : " + refundClearDataURL);
        LOGGER.debug("dataMap : " + dataMap);

        Map<String, String> queryResult = new HashMap<String, String>();
        String filePath = "";
        String error_code = "";
        String error = "";
        String customError = "";

        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod();

        try {
            String jsonStr = JSON.toJSONString(dataMap);
            String data = AES.encryptToBase64(jsonStr, merchantAESKey);
            String encryptkey = RSA.encrypt(merchantAESKey, yeepayPublicKey);

            String url = refundClearDataURL + "?merchantaccount=" + URLEncoder.encode(merchantaccount, "UTF-8")
                            + "&data=" + URLEncoder.encode(data, "UTF-8") + "&encryptkey="
                            + URLEncoder.encode(encryptkey, "UTF-8");

            getMethod = new GetMethod(url);
            int statusCode = httpClient.executeMethod(getMethod);

            if (statusCode != 200) {
                customError = "Get request failed, response code = " + statusCode;
                queryResult.put("customError", customError);
                return (queryResult);
            }

            InputStream responseStream = getMethod.getResponseBodyAsStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream, "UTF-8"));
            // BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream));

            String line = reader.readLine();
            if (line.startsWith("{")) {
                Map<String, Object> jsonMap = JSON.parseObject(line, TreeMap.class);

                if (jsonMap.containsKey("error_code")) {
                    error_code = formatString((String) jsonMap.get("error_code"));
                    error = formatString((String) jsonMap.get("error"));
                } else {
                    String dataFromYeepay = formatString((String) jsonMap.get("data"));
                    String encryptkeyFromYeepay = formatString((String) jsonMap.get("encryptkey"));

                    String yeepayAESKey = RSA.decrypt(encryptkeyFromYeepay, merchantPrivateKey);
                    String decryptData = AES.decryptFromBase64(dataFromYeepay, yeepayAESKey);
                    Map<String, Object> decryptDataMap = JSON.parseObject(decryptData, TreeMap.class);

                    error_code = formatString((String) decryptDataMap.get("error_code"));
                    error = formatString((String) decryptDataMap.get("error"));

                    LOGGER.debug("decryptData : " + decryptData);
                }
            } else {
                String outputFilePath = sysPath + File.separator + "clearData";
                File file = new File(outputFilePath);
                file.mkdir();

                String time = String.valueOf(System.currentTimeMillis());
                String fileName = "refundClearData_" + startdate + "_" + enddate + "_" + time + ".txt";
                String absolutePathOfOutputFile = outputFilePath + File.separator + fileName;
                filePath = absolutePathOfOutputFile;

                File outputFile = new File(absolutePathOfOutputFile);
                FileWriter fileWriter = new FileWriter(outputFile);
                BufferedWriter writer = new BufferedWriter(fileWriter);

                LOGGER.debug("filePath : " + filePath);

                writer.write(line);
                writer.write(System.getProperty("line.separator"));
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.write(System.getProperty("line.separator"));
                }

                writer.close();
            }
        } catch (Exception e) {
            customError = "Caught an Exception. " + e.toString();
            e.printStackTrace();
        } finally {
            getMethod.releaseConnection();
        }

        queryResult.put("filePath", filePath);
        queryResult.put("error_code", error_code);
        queryResult.put("error", error);
        queryResult.put("customError", customError);

        return (queryResult);
    }

    /**
     * payapiQueryByOrderid() : 4.4 支付结果查询
     * @param orderid 客户订单号
     * @return
     */
    public Map<String, String> payapiQueryByOrderid(String orderid) {
        LOGGER.debug("##### payapiQueryByOrderid() #####");
        Map<String, String> params = new HashMap<String, String>();
        String merchantaccount = getMerchantAccount();
        String payapiQueryURL = getPayapiQueryURL();
        params.put("merchantaccount", merchantaccount);
        params.put("orderid", orderid);
        return this.executeGetMethod(payapiQueryURL, params);
    }

    /**
     * 银行卡解绑
     * 
     * @param bingId
     * @param identityId
     * @param identitytype
     * @return
     */

    public Map<String, String> unbindBankCard(String bindId, String identityId, String identitytype) {
        LOGGER.debug("##### unbindBankCard() #####");
        String unbindBankcardURL = getUnbindBankcardURL();
        Map<String, String> params = new HashMap<String, String>();
        params.put("bindid", bindId);
        params.put("identityid", identityId);
        params.put("identitytype", identitytype);
        params.put("merchantaccount", getMerchantAccount());
        return this.executePostMethod(unbindBankcardURL, params);
    }

    /**
     * 可提现余额查询
     * 
     * @return
     */
    public Map<String, String> queryDrawValidAmount() {
        LOGGER.debug("##### queryDrawValidAmount() #####");
        Map<String, String> params = new HashMap<String, String>();
        params.put("merchantaccount", getMerchantAccount());
        return this.executeGetMethod(getDrawValidAmountURL(), params);

    }

    /**
     * post方法参数处理
     */
    public NameValuePair[] paramsHandle(Map<String, String> params) throws Exception {
        String merchantaccount = getMerchantAccount();
        String merchantPrivateKey = getMerchantPrivateKey();
        String merchantAESKey = getMerchantAESKey();
        String yeepayPublicKey = getYeepayPublicKey();

        Set<String> keys = params.keySet();
        // 利用treemap对请求参数排序
        TreeMap<String, Object> dataMap = new TreeMap<String, Object>();
        // 获得资源包 ，读取配置文件中int参数列表
        ResourceBundle rb = ResourceBundle.getBundle("epro/params");
        // 通过资源包拿到所有的key
        Enumeration<String> allKey = rb.getKeys();
        // 遍历key 得到 value
        List<String> paramsList = new ArrayList<String>();
        while (allKey.hasMoreElements()) {
            String key = allKey.nextElement();
            String value = (String) rb.getString(key);
            paramsList.add(value);
        }
        // 判断是否包含非空字段
        for (String key : keys) {
            if (paramsList.contains(key)) {
                if (key.trim().equals("identitytype") && params.get(key) == null) {
                    throw new Exception("identitytype is null!!!!!");
                } else if (key.trim().equals("transtime") && params.get(key) == null) {
                    throw new Exception("transtime is null!!!!!");
                } else if (key.trim().equals("amount") && params.get(key) == null) {
                    throw new Exception("amount is null!!!!!");
                }
                dataMap.put(key, String2Int(params.get(key)));

            } else {
                dataMap.put(key, formatString(params.get(key)));
            }
        }
        // 使用商户私钥生成数字签名
        String sign = EncryUtil.handleRSA(dataMap, merchantPrivateKey);
        // 将数字签名加入到请求参数中
        dataMap.put("sign", sign);
        String jsonStr = JSON.toJSONString(dataMap);
        // 使用merchantAESKey对请求参数加密
        LOGGER.debug("请求参数："+jsonStr);
        String data = AES.encryptToBase64(jsonStr, merchantAESKey);
        // 使用yeepayPublicKey加密merchantAESKey
        String encryptkey = RSA.encrypt(merchantAESKey, yeepayPublicKey);

        NameValuePair[] datas = { new NameValuePair("merchantaccount", merchantaccount),
                        new NameValuePair("data", data), new NameValuePair("encryptkey", encryptkey) };

        LOGGER.debug("加密后datas:" + datas);
        return datas;
    }

    /**
     * 执行post方法
     * 
     * @param url
     * @param params
     * @return
     */
    public Map<String, String> executePostMethod(String url, Map<String, String> params) {
        String customError;
        Map<String, String> result = new HashMap<String, String>();
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        try {
            // 设置post请求方法参数
            postMethod.setRequestBody(new TZTService().paramsHandle(params));
            //post请求执行
            int statusCode = httpClient.executeMethod(postMethod);
            //获取响应
            byte[] responseByte = postMethod.getResponseBody();
            String responseBody = new String(responseByte, "UTF-8");
            //解析http请求返回
            result = parseHttpResponseBody(statusCode, responseBody);
        } catch (Exception e) {
            customError = "Caught Exception!" + e.toString();
            result.put("customError", customError);
            LOGGER.debug(result.toString());
            e.printStackTrace();
        } finally {
            //释放连接
            postMethod.releaseConnection();
        }
        LOGGER.debug("result : " + result);
        return (result);
    }

    /**
     * get方法执行
     * 
     * @param url
     * @param params
     * @return
     */
    public Map<String, String> executeGetMethod(String url, Map<String, String> params) {
        Map<String, String> result = new HashMap<String, String>();
        String customError = ""; // 自定义，非接口返回
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod();
        try {
            // 获取请求的带有参数的url
            url = this.getParamsHandle(params, url);// AES.encryptToBase64(jsonStr, merchantAESKey);
            LOGGER.debug("executeGeturl  : " + url);
            // 初始化请求方法
            getMethod = new GetMethod(url);
            int statusCode = httpClient.executeMethod(getMethod);
            // 解析http请求响应
            String responseBody = getMethod.getResponseBodyAsString();
            result = parseHttpResponseBody(statusCode, responseBody);
        } catch (Exception e) {
            customError = "Caught an Exception. " + e.toString();
            result.put("customError", customError);
            e.printStackTrace();
        } finally {
            getMethod.releaseConnection();
        }
        LOGGER.debug("result : " + result);
        return (result);
    }

    /**
     * get参数排序、加密转化成url处理
     * 
     * @param params
     * @param url
     * @return
     * @throws Exception
     */
    public String getParamsHandle(Map<String, String> params, String url) throws Exception {
        String merchantPrivateKey = getMerchantPrivateKey();
        String merchantAESKey = getMerchantAESKey();
        String yeepayPublicKey = getYeepayPublicKey();
        Set<String> keys = params.keySet();
        // 使用TreeMap对请求参数排序
        TreeMap<String, Object> dataMap = new TreeMap<String, Object>();
        // 获得资源包 ，读取配置文件中int参数列表
        ResourceBundle rb = ResourceBundle.getBundle("epro/params");
        // 通过资源包拿到所有的key
        Enumeration<String> allKey = rb.getKeys();
        // 遍历key 得到 value
        List<String> paramsList = new ArrayList<String>();
        while (allKey.hasMoreElements()) {
            String key = allKey.nextElement();
            String value = (String) rb.getString(key);
            paramsList.add(value);
        }
        for (String key : keys) {
            if (paramsList.contains(key)) {
                if (key.trim().equals("identitytype") && params.get(key) == null) {
                    throw new Exception("identitytype is null!!!!!");
                } else if (key.trim().equals("transtime") && params.get(key) == null) {
                    throw new Exception("transtime is null!!!!!");
                } else if (key.trim().equals("amount") && params.get(key) == null) {
                    throw new Exception("amount is null!!!!!");
                }
                dataMap.put(key, String2Int(params.get(key)));

            } else {
                dataMap.put(key, formatString(params.get(key)));
            }
        }
        // 使用商户私钥生成数字签名
        String sign = EncryUtil.handleRSA(dataMap, merchantPrivateKey);
        // 将数字签名放到请求参数中
        dataMap.put("sign", sign);
        String jsonStr = JSON.toJSONString(dataMap);
        LOGGER.debug("请求参数:"+jsonStr);
        // 使用merchantAESKey加密请求参数
        String data = AES.encryptToBase64(jsonStr, merchantAESKey);
        // 使用易宝公钥加密merchantAESKey
        String encryptkey = RSA.encrypt(merchantAESKey, yeepayPublicKey);

        url = url + "?merchantaccount=" + URLEncoder.encode(getMerchantAccount(), "UTF-8") + "&data="
                        + URLEncoder.encode(data, "UTF-8") + "&encryptkey=" + URLEncoder.encode(encryptkey, "UTF-8");
        LOGGER.debug("getMethodUrl : " + url);
        return url;
    }

}
