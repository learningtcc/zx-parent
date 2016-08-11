package com.ink.tfs.client;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author haoyunfeng
 * @date 2016/5/17
 */
public class HttpClientUtils {

    /**
     * 上传byte
     * @param postUrl
     * @param contentType
     * @param encoding
     * @param timeout
     * @param objectByte
     * @return
     */
    public static Map<String, Object> execute(String postUrl, String contentType, String encoding, int timeout, byte[] objectByte) {
        return post(postUrl, new ByteArrayRequestEntity(objectByte, contentType), encoding, timeout);
    }
    /**
     * 上传inputStream
     * @param postUrl
     * @param contentType
     * @param encoding
     * @param timeout
     * @param tfsEntity
     * @return
     */
    public static Map<String, Object> execute(String postUrl, String contentType, String encoding, int timeout, TFSEntity tfsEntity) {
        return postStream(postUrl, new InputStreamRequestEntity(tfsEntity.getInputStream(), contentType), encoding, timeout,tfsEntity);
    }


    /**
     * 下载byte
     * @param postUrl
     * @param map
     * @param timeout
     * @return
     */
    public static Map<String, Object> execute(String postUrl,Map<String, Object> map, int timeout) {
        return post(postUrl,map,timeout);
    }

    /**
     * httpclient工具类
     *
     * @param postUrl
     * @param requestEntity
     * @param encoding
     * @param timeout
     * @return
     */
    private static Map<String, Object> post(String postUrl, RequestEntity requestEntity, String encoding, int timeout) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        /** 参数校验 */
        if (null == postUrl || "".equals(postUrl)) {
            resultMap.put("result", "false");
            resultMap.put("errorMSG", "请求URL为空！");
            return resultMap;
        }
        // 执行postMethod
        int statusCode = 0;
        //http连接池
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        HttpClient httpClient = new HttpClient(connectionManager);
        //设置连接超时时间
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
        //设置请求超时时间
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeout);
        PostMethod postMethod = new PostMethod(postUrl);

        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);
        postMethod.setRequestEntity(requestEntity);

        try {
            statusCode = httpClient.executeMethod(postMethod);
            if (HttpStatus.SC_OK == statusCode) {
                resultMap = JSONObject.parseObject(new String(postMethod.getResponseBody()));
            }else {
                resultMap.put("result", "false");
                resultMap.put("errorMSG", "http请求失败，返回码："+statusCode);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultMap;
    }
    /**
     * httpclient工具类
     *
     * @param postUrl
     * @param requestEntity
     * @param encoding
     * @param timeout
     * @return
     */
    private static Map<String, Object> postStream(String postUrl, RequestEntity requestEntity, String encoding, int timeout,TFSEntity tfsEntity) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        /** 参数校验 */
        if (null == postUrl || "".equals(postUrl)) {
            resultMap.put("result", "false");
            resultMap.put("errorMSG", "请求URL为空！");
            return resultMap;
        }
        // 执行postMethod
        int statusCode = 0;
        //http连接池
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        HttpClient httpClient = new HttpClient(connectionManager);
        //设置连接超时时间
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
        //设置请求超时时间
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeout);
        PostMethod postMethod = new PostMethod(postUrl);

        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);
        postMethod.setRequestEntity(requestEntity);
        postMethod.addRequestHeader("fileName",tfsEntity.getFileName());
        postMethod.addRequestHeader("sourceCode",tfsEntity.getSource());
        postMethod.addRequestHeader("moduleCode",tfsEntity.getModule());
        postMethod.addRequestHeader("uploader",tfsEntity.getUploader());


        try {
            statusCode = httpClient.executeMethod(postMethod);
            if (HttpStatus.SC_OK == statusCode) {
                resultMap = JSONObject.parseObject(new String(postMethod.getResponseBody()));
            }else {
                resultMap.put("result", "false");
                resultMap.put("errorMSG", "http请求失败，返回码："+statusCode);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    /**
     * httpclient工具类
     *
     * @param postUrl
     * @param map
     * @param timeout
     * @return
     */
    public static Map<String, Object> post(String postUrl, Map<String, Object> map, int timeout) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        /** 参数校验 */
        if (null == postUrl || "".equals(postUrl)) {
            resultMap.put("result", "false");
            resultMap.put("errorMSG", "请求URL为空！");
            return resultMap;
        }
        // 返回对象
        Object obj = null;
        // 执行postMethod
        int statusCode = 0;
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        HttpClient httpClient = null;
        PostMethod postMethod = null;
        try {
            httpClient = new HttpClient(connectionManager);
            //设置连接超时时间
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
            //设置请求超时时间
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeout);
            postMethod = new PostMethod(postUrl);
            /** 循环遍历map参数,使用addParameter放入PostMethod中 */
            postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
            if (null != map) {
                Set<?> set = map.entrySet();
                Iterator<?> it = set.iterator();
                // 将表单的值放入postMethod中
                while (it.hasNext()) {
                    Map.Entry me = (Map.Entry) it.next();
                    postMethod.addParameter(me.getKey() + "", me.getValue() + "");
                }
            }
            statusCode = httpClient.executeMethod(postMethod);
            if (statusCode == HttpStatus.SC_OK) {
                resultMap.put("result", "true");
                resultMap.put("resultByte", postMethod.getResponseBody());
            }else {
                resultMap.put("result", "false");
                resultMap.put("errorMSG", "http请求失败，返回码："+statusCode);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
