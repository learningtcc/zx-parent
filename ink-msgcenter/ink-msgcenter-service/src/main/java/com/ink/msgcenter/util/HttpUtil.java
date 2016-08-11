package com.ink.msgcenter.util;

import com.ink.base.log.util.YinkerLogger;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * htttp请求工具类
 * Created by aiyungui on 2016/5/23.
 */
public class HttpUtil {

    private static YinkerLogger logger = YinkerLogger.getLogger(HttpUtil.class);
    /**
     * post请求url  不带参数
     * @param sourceUrl
     * @return
     * @throws Exception
     */
    public static String sendHttpPost(String sourceUrl){

        return sendHttpPost(sourceUrl,new HashMap<String, String>());
    }

    /**
     * post请求url  带参数
     * @param sourceUrl
     * @return
     * @throws Exception
     */
    public static String sendHttpPost(String sourceUrl,Map<String,String> paraMap){

        String result = "error,请求路径有问题，请检查";
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;

        try {
            if (StringUtils.isBlank(sourceUrl)){
                throw (new Exception("url请求地址为空"));
            }

            httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(sourceUrl);
            //设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().
                    setSocketTimeout(5000).
                    setConnectTimeout(5000).
                    setConnectionRequestTimeout(5000).build();
            httpPost.setConfig(requestConfig);

            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            Set<String> keySet = paraMap.keySet();
            for(String key : keySet){
                nvps.add(new BasicNameValuePair(key, paraMap.get(key)));
            }

            httpPost.setEntity(new UrlEncodedFormEntity(nvps,"UTF-8"));
            response = httpClient.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            if (statusLine.getStatusCode() != 200){
                result = statusLine.getStatusCode() + "," +  statusLine.getReasonPhrase();
            }else{
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // 将源码流保存在一个byte数组当中，因为可能需要两次用到该流，
                    byte[] bytes = EntityUtils.toByteArray(entity);
                    // 如果头部Content-Type中包含了编码信息
                    String charSet = EntityUtils.getContentCharSet(entity);
                    if (StringUtils.isBlank(charSet)) {
                        String regEx = "(?=<meta).*?(?<=charset=[\\'|\\\"]?)([[a-z]|[A-Z]|[0-9]|-]*)";
                        Pattern pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
                        Matcher matcher =pattern.matcher(new String(bytes,"UTF-8"));
                        if ( matcher.find() && matcher.groupCount() == 1) {
                            charSet = matcher.group(1);
                        }
                    }

                    if(StringUtils.isNotBlank(charSet)){
                        result = new String(bytes, charSet);
                    }
                }
            }

            logger.info("","请求成功，请求URL：" + sourceUrl + ";返回状态:" + statusLine.getStatusCode()
                    + ";返回信息：" + statusLine.getReasonPhrase());
        } catch (Exception e) {
            logger.error("","请求失败，请求URL：" + sourceUrl + ";" + e.getMessage(),e);
        }finally{
            try {
                if (response != null)
                    response.close();
                if (httpClient != null)
                    httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
