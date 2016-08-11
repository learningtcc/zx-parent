package com.ink.channel.core.jdPay.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.IdCodeGenerator;
import com.ink.cert.api.client.DataSecurityClient;
import com.ink.cert.api.constant.ResultConstant;
import com.ink.cert.api.module.MsgOutput;
import com.ink.channel.core.cnst.ChannelConstants;
import com.ink.channel.core.enums.SystemCodeEnums;
import com.ink.channel.core.ldyspay.LdysConstant;
import com.ink.channel.core.utils.SpringContextHolder;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class JDHttpClientUtils {
    private static YinkerLogger logger = YinkerLogger.getLogger(JDHttpClientUtils.class);

    /**
     * 发起交易请求
     * 
     * @param tradeType
     * @return 返回交易结果
     * @throws Exception
     */
    public static Map<String, Object> trade(Map<String, Object> params,String merchantNo) {
        Map<String, Object> map = new HashMap<>();
        String respStr = "";
        try {
        	//在证书中心获取   配置的信息
	        IdCodeGenerator certificateUtil = (IdCodeGenerator) SpringContextHolder.getBean("certificateUtil");
	        String asileNo=certificateUtil.getIpMapsConfig().get(JDConstant.Asile_NO);
	        String version = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+JDConstant.VERSION);
	        String merchant = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+JDConstant.merchantNo);
	        String terminal = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+JDConstant.terminal);
	        String url = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+JDConstant.url);
	        String md5 = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+JDConstant.md5_key);
	        String charset = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+JDConstant.charset);
	        String certCode = ((IdCodeGenerator) SpringContextHolder.getBean("certificateUtil")).getIpMapsConfig().get(merchantNo+asileNo+JDConstant.jdCert);
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, "JD快捷支付发送交易请求加密开始，待加密数据：" + params);
            String data = FTL.doString(params, "http_des_req_data.xml");
            // 将请求参数XML中的data元素用DES加密,DES密钥是在商户在网银在线后台设置的
            String dataDES = "";
            MsgOutput output = ((DataSecurityClient) SpringContextHolder.getBean(DataSecurityClient.class)).dataEncrypt(
                            merchantNo, certCode, data);
            if (ResultConstant.EXECUTE_SUCCESS.equals(output.getResultCode())) {
                logger.info(ChannelConstants.LOGGER_MODULE_NAME,"DES加密成功！"+output.getResultCode()+output.getResultMsg());
                dataDES = output.getMessage().toString();
            } else {
                logger.error("DES加密失败！"+output.getResultCode()+output.getResultMsg());
                map.put("code","EEE0001");// 交易返回码
                map.put("desc", "报文加密异常！");// 交易返回码描述
                return map;
            }
            String signMD5 = MD5.md5(version + merchant + terminal + dataDES, md5);
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, "【DATA】：" + data + "【SIGNMD5】：" + signMD5);
            String reqXML = getReqXML(dataDES, signMD5,merchant,version,charset,terminal);
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, "【reqXML】：" + reqXML);
            // 最后将xml用base64加密
            String reqParams = new String(Base64.encode(reqXML.getBytes()));
            // 发送请求到网银在线快捷支付地址
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, "JD快捷支付发送交易请求加密结束，密文-【" + reqParams + "】");
            respStr = process(charset, reqParams, url);
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, "JD快捷支付处理交易结果Base64解码开始，待解码数据-【" + respStr + "】");
        } catch (ConnectTimeoutException connectTimeoutException) {
            logger.error(ChannelConstants.LOGGER_MODULE_NAME, "", connectTimeoutException);
            map.put("code", ChannelConstants.REQUEST_EXCEPTION_CODE);// 交易返回码
            map.put("desc", ChannelConstants.REQUEST_EXCEPTION_MSG);// 交易返回码描述
            return map;
        } catch (SocketTimeoutException socketTimeoutException) {
            map.put("code", ChannelConstants.RESPONSE_EXCEPTION_CODE);// 交易返回码
            map.put("desc", ChannelConstants.RESPONSE_EXCEPTION_MSG);// 交易返回码描述
            logger.error(ChannelConstants.LOGGER_MODULE_NAME, "", socketTimeoutException);
            return map;
        } catch (Exception e) {
        	map.put("code", SystemCodeEnums.JD_FAILE_CODE.getCode());// 交易返回码
            map.put("desc", SystemCodeEnums.JD_FAILE_CODE.getMsg());// 交易返回码描述
            logger.error(ChannelConstants.LOGGER_MODULE_NAME, "JD快捷支付发送交易请求异常，请求信息：" + params, e);
        }
        return operate(respStr,merchantNo);
    }

    /**
     * 处理交易结果
     * 
     * @param respStr
     * @throws Exception
     */
    public static Map<String, Object> operate(String respStr,String merchantNo){
    	 Map<String, Object> resultMap = new HashMap<String, Object>();
    	 resultMap.put("exceptionCode",LdysConstant.successCode);
    	try {
	        IdCodeGenerator certificateUtil = (IdCodeGenerator) SpringContextHolder.getBean("certificateUtil");
	        String asileNo=certificateUtil.getIpMapsConfig().get(JDConstant.Asile_NO);
	        String certCode = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+JDConstant.jdCert);
	        String md5 = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+JDConstant.md5_key);
	        if (StringUtils.isEmpty(respStr)) {
	            return resultMap;
	        }
           
            // 数据格式是resp=XML数据
            String temResp = respStr.substring(respStr.indexOf("=") + 1);
            // 快捷支付数据先base64解码
            com.sun.org.apache.xml.internal.security.Init.init();
            temResp = new String(Base64.decode(temResp));
            // 解析xml中的数据
            Map<String, String> respParams = XmlMsg.parse(temResp);
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, "JD快捷支付处理交易结果Base64解码开始，" + "版本号|商户号|终端号|交易数据|数据签名"
                            + respParams.get("chinabank.version") + "|" + respParams.get("chinabank.merchant") + "|"
                            + respParams.get("chinabank.terminal") + "|" + respParams.get("chinabank.data") + "|"
                            + respParams.get("chinabank.sign"));
            // 验证数据签名的合法性。版本号+商户号+终端号+交易数据使用md5加密和数据签名比较，md5密钥在网银在线商户后台设置
            if (!MD5.verify(respParams.get("chinabank.version") + respParams.get("chinabank.merchant")
                            + respParams.get("chinabank.terminal") + respParams.get("chinabank.data"), md5,
                            respParams.get("chinabank.sign"))) {
                resultMap.put("msg", "数据签名sign不合法");
                logger.info(ChannelConstants.LOGGER_MODULE_NAME,
                                "JD快捷支付处理交易结果<DATA>失败，数据签名sign不合法【" + respParams.get("chinabank.sign") + "】");
                return resultMap;
            }
            logger.info(ChannelConstants.LOGGER_MODULE_NAME,
                            "JD快捷支付处理交易结果MD5解密开始，待解密数据-【" + respParams.get("chinabank.data") + "】");
            // 使用DES解密data交易数据,des密钥网银在线商户后台设置
            // String dataDES = DES.decrypt(respParams.get("chinabank.data"), des, respParams.get("xml.charset"));
            String dataDES="";
            MsgOutput output = ((DataSecurityClient) SpringContextHolder.getBean(DataSecurityClient.class)).dataDecrypt(
                            merchantNo, certCode, respParams.get("chinabank.data"));
            if (ResultConstant.EXECUTE_SUCCESS.equals(output.getResultCode())) {
                logger.info("DES加密成功！"+output.getResultCode()+output.getResultMsg());
                dataDES = output.getMessage().toString();
            } else {
            	 resultMap.put("exceptionCode",LdysConstant.decodeCode);
                logger.error(ChannelConstants.LOGGER_MODULE_NAME,"京东快捷支付DES解密失败！"+output.getResultCode()+output.getResultMsg());
                resultMap.put("code",SystemCodeEnums.JD_FAILE_CODE.getCode());// 交易返回码
                resultMap.put("desc", "京东快捷支付报文解密异常！");// 交易返回码描述
                return resultMap;
            }
            Map<String, String> dataParams = XmlMsg.parse(dataDES);
            resultMap.put("type", dataParams.get("data.trade.type"));// 交易类型
            resultMap.put("id", dataParams.get("data.trade.id"));// 交易号
            resultMap.put("oid", dataParams.get("data.trade.oid"));// 原交易跟踪号
            resultMap.put("amount", dataParams.get("data.trade.amount"));// 交易金额
            resultMap.put("currency", dataParams.get("data.trade.currency"));// 交易货币
            resultMap.put("date", dataParams.get("data.trade.date"));// 交易日期
            resultMap.put("time", dataParams.get("data.trade.time"));// 交易时间
            resultMap.put("note", dataParams.get("data.trade.note"));// 交易备注
            resultMap.put("status", dataParams.get("data.trade.status"));// 交易状态
            resultMap.put("url", dataParams.get("data.trade.url"));// 跳转URL
            resultMap.put("code", dataParams.get("data.return.code"));// 交易返回码
            resultMap.put("desc", dataParams.get("data.return.desc"));// 交易返回码描述
            logger.info(ChannelConstants.LOGGER_MODULE_NAME, "JD快捷支付处理交易结果MD5解密结束，<DATA>数据：" + resultMap.toString());
        } catch (Exception e) {
        	resultMap.put("exceptionCode",LdysConstant.decodeCode);
            logger.error(ChannelConstants.LOGGER_MODULE_NAME, "JD快捷支付解密返回的报文失败，密文【" + respStr + "】", e);
            e.printStackTrace();
            resultMap.put("code",SystemCodeEnums.JD_FAILE_CODE.getCode());
            resultMap.put("desc",SystemCodeEnums.JD_FAILE_CODE.getMsg());
        }
        return resultMap;
    }

    private static String getReqXML(String dataElmtDES, String signMD5,String merchant,String version,String charset,String terminal) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("charset", charset);
        params.put("version", version);
        params.put("merchant", merchant);
        params.put("terminal", terminal);
        params.put("data", dataElmtDES);
        params.put("sign", signMD5);
        return FTL.doString(params, "http_des_req.xml");
    }

//    private static void getProperties(String merchantNo) {
//
//        IdCodeGenerator certificateUtil = (IdCodeGenerator) SpringContextHolder.getBean("certificateUtil");
//        // 配置中心获取配置信息
//        String asileNo=((IdCodeGenerator) SpringContextHolder.getBean("certificateUtil")).getIpMapsConfig().get(JDConstant.Asile_NO);
//        // version = JDConfigurationUtil.getInstance().getValue(JDPayConfig.JD_KEY_VSERSION);
//        version = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+JDConstant.VERSION);
//        // merchant = JDConfigurationUtil.getInstance().getValue(JDPayConfig.JD_KEY_MERCHANT_ID);
//        merchant = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+JDConstant.merchantNo);
//        // terminal = JDConfigurationUtil.getInstance().getValue(JDPayConfig.JD_KEY_TERMINAL_ID);
//        terminal = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+JDConstant.terminal);
//        // url = JDConfigurationUtil.getInstance().getValue(JDPayConfig.JD_PAYAUTHENTICATION_URL);
//        url = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+JDConstant.url);
//        // md5 = JDConfigurationUtil.getInstance().getValue(JDPayConfig.JD_KEY_MD5);
//        md5 = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+JDConstant.md5_key);
//        // charset = JDConfigurationUtil.getInstance().getValue(JDPayConfig.JD_KEY_CHARSET);
//        charset = certificateUtil.getIpMapsConfig().get(merchantNo+asileNo+JDConstant.charset);
//    }
    /**
     * 发送http 请求
     * @param charset
     * @param req
     * @param url
     * @return
     * @throws ConnectTimeoutException
     * @throws SocketTimeoutException
     * @throws Exception
     */
    public static String process(String charset, String req, String url) throws ConnectTimeoutException, SocketTimeoutException ,Exception{
        String resp = null;
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;
        InputStream in = null;
        try {
            httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            if (url.indexOf("https") != -1) {
                KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                in = JDHttpClientUtils.class.getClassLoader().getResourceAsStream("jdPay/quick.keystore");
                logger.info(ChannelConstants.LOGGER_MODULE_NAME, "秘钥路径："
                                + JDHttpClientUtils.class.getClassLoader().getResource("jdPay/quick.keystore"));

                keyStore.load(in, "chinabank".toCharArray());
                SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(keyStore, new TrustSelfSignedStrategy())
                                .build();
                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext);
                httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            }
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000).setConnectTimeout(60000)
                            .build();// 设置请求和传输超时时间
            httpPost.setConfig(requestConfig);
            List<NameValuePair> reqPair = new ArrayList<NameValuePair>();
            reqPair.add(new BasicNameValuePair("charset", charset));
            reqPair.add(new BasicNameValuePair("req", req));
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(reqPair, charset);
            httpPost.setEntity(urlEncodedFormEntity);
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                InputStream is = responseEntity.getContent();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int ch = 0;
                while ((ch = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, ch);
                }
                byte bytes[] = baos.toByteArray();
                resp = new String(bytes, charset);
            }

            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new Exception(response.getStatusLine().toString() + "|" + resp);
            }
        } catch (ConnectTimeoutException connectTimeoutException) {
            throw connectTimeoutException;
        } catch (SocketTimeoutException socketTimeoutException) {
        	throw socketTimeoutException;
        } catch (Exception e) {
        	throw e;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                httpPost.releaseConnection();
                if (httpClient != null) {
                    try {
                        httpClient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
            	throw e;
            }
        }
        return resp;
    }
}
