package com.ink.channel.core.epro;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

public class ConfigurationUtil {
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
}
