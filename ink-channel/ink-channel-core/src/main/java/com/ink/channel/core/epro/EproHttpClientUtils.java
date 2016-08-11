package com.ink.channel.core.epro;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class EproHttpClientUtils {
	 private static final Logger LOGGER = LoggerFactory.getLogger(EproHttpClientUtils.class);
	/**
     * get方法执行
     * 
     * @param url
     * @param params
     * @return
	 * @throws ConnectTimeoutException 
	 * @throws SocketTimeoutException 
     */
    public static Map<String, String> executeGetMethod(String url, Map<String, String> params) throws ConnectTimeoutException, SocketTimeoutException {
        Map<String, String> result = new HashMap<String, String>();
        String customError = ""; // 自定义，非接口返回
        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod();
        try {
            // 获取请求的带有参数的url
            url = getParamsHandle(params, url);// AES.encryptToBase64(jsonStr, merchantAESKey);
            LOGGER.debug("executeGeturl  : " + url);
            // 初始化请求方法
            getMethod = new GetMethod(url);
            int statusCode = httpClient.executeMethod(getMethod);
            // 解析http请求响应
            String responseBody = getMethod.getResponseBodyAsString();
            result = ConfigurationUtil.parseHttpResponseBody(statusCode, responseBody);
        }catch(ConnectTimeoutException connectTimeoutException){
        	throw connectTimeoutException;
        }catch(SocketTimeoutException socketTimeoutException){
			throw socketTimeoutException;
        }catch (Exception e) {
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
    public static String getParamsHandle(Map<String, String> params, String url) throws Exception {
        String merchantPrivateKey = ConfigurationUtil.getMerchantPrivateKey();
        String merchantAESKey = ConfigurationUtil.getMerchantAESKey();
        String yeepayPublicKey = ConfigurationUtil.getYeepayPublicKey();
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
                dataMap.put(key, ConfigurationUtil.String2Int(params.get(key)));

            } else {
                dataMap.put(key, ConfigurationUtil.formatString(params.get(key)));
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

        url = url + "?merchantaccount=" + URLEncoder.encode(ConfigurationUtil.getMerchantAccount(), "UTF-8") + "&data="
                        + URLEncoder.encode(data, "UTF-8") + "&encryptkey=" + URLEncoder.encode(encryptkey, "UTF-8");
        LOGGER.debug("getMethodUrl : " + url);
        return url;
    }
    /**
     * 执行post方法
     * 
     * @param url
     * @param params
     * @return
     * @throws ConnectTimeoutException 
     * @throws SocketTimeoutException 
     */
    public static Map<String, String> executePostMethod(String url, Map<String, String> params) throws ConnectTimeoutException, SocketTimeoutException {
        String customError;
        Map<String, String> result = new HashMap<String, String>();
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(url);
        try {
            // 设置post请求方法参数
            postMethod.setRequestBody(paramsHandle(params));
            //post请求执行
            int statusCode = httpClient.executeMethod(postMethod);
            //获取响应
            byte[] responseByte = postMethod.getResponseBody();
            String responseBody = new String(responseByte, "UTF-8");
            //解析http请求返回
            result = ConfigurationUtil.parseHttpResponseBody(statusCode, responseBody);
        }catch(ConnectTimeoutException connectTimeoutException){
        	throw connectTimeoutException;
        }catch(SocketTimeoutException socketTimeoutException){
			throw socketTimeoutException;
        }catch(ConnectException  connectException){
        	throw new ConnectTimeoutException();
        }catch (Exception e) {
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
     * post方法参数处理
     */
    public static NameValuePair[] paramsHandle(Map<String, String> params) throws Exception {
        String merchantaccount = ConfigurationUtil.getMerchantAccount();
        String merchantPrivateKey = ConfigurationUtil.getMerchantPrivateKey();
        String merchantAESKey = ConfigurationUtil.getMerchantAESKey();
        String yeepayPublicKey = ConfigurationUtil.getYeepayPublicKey();

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
                dataMap.put(key, ConfigurationUtil.String2Int(params.get(key)));

            } else {
                dataMap.put(key, ConfigurationUtil.formatString(params.get(key)));
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
}
