package com.ink.scheduler.core.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.ink.base.log.util.YinkerLogger;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;


public abstract class HttpClientUtils {

    private static YinkerLogger loger=  YinkerLogger.getLogger(HttpClientUtils.class) ;
    
    /**
     * @Title: execute
     * @Description: HttpClient Execute post
     * @param postUrl 请求URL
     * @param map 参数集合
     * @return 
     * @return Object
     * @throws
     */
    @SuppressWarnings("rawtypes")
    public static int execute(String postUrl , Map<String , Object> map,int timeout){
        Map<String,Object> resultMap = new HashMap<String, Object>();
        loger.info("timer","httpPost","开始请求HttpClient,参数[postURL: "+postUrl+"],[map: "+map+"]");
        /** 参数校验 */
        if(null == postUrl || "".equals(postUrl)){
            loger.debug("请求URL为空！");
//            resultMap.put(TaskJobConstants.JOB_HTTP_STATUS_CODE,HttpStatus.SC_NOT_FOUND);
//            return resultMap;
            return  HttpStatus.SC_NOT_FOUND;
        }
        // 返回对象
        Object obj = null;
        // 执行postMethod
        int statusCode = 0;
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        HttpClient httpClient=null;
        PostMethod postMethod=null;
        try{
            httpClient = new HttpClient(connectionManager);
            //设置连接超时时间
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
            //设置请求超时时间
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeout);
            postMethod = new PostMethod(postUrl);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        /** 循环遍历map参数,使用addParameter放入PostMethod中 */
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        if(null!=map){
            Set<?> set  = map.entrySet();
            Iterator<?> it = set.iterator();
            // 将表单的值放入postMethod中
            while (it.hasNext()) {
                Map.Entry me = (Map.Entry) it.next();
                postMethod.addParameter(me.getKey()+"",me.getValue() + "");
            }
        }
        try {
            statusCode = httpClient.executeMethod(postMethod);
//            resultMap.put(TaskJobConstants.JOB_HTTP_STATUS_CODE,statusCode);
//            resultMap.put(TaskJobConstants.JOB_REQ_ID,postMethod.getResponseBody().toString());
            loger.info("timer","execute","HttpClient post:"+postUrl+" Return Code : " + statusCode);
        } catch (UnknownHostException e){
//            resultMap.put(TaskJobConstants.JOB_HTTP_STATUS_CODE,HttpStatus.SC_INTERNAL_SERVER_ERROR);
            loger.info("timer","execute","http connect timeout:"+postUrl);
        }catch (HttpException e) {
//            resultMap.put(TaskJobConstants.JOB_HTTP_STATUS_CODE,HttpStatus.SC_INTERNAL_SERVER_ERROR);
            loger.error("timer","httpException: "+e.getMessage());
        } catch (IOException e) {
//            resultMap.put(TaskJobConstants.JOB_HTTP_STATUS_CODE,HttpStatus.SC_INTERNAL_SERVER_ERROR);
            loger.error("timer","IOException: "+e.getMessage());
        }
        return statusCode;
    }

    /**
     * @Title: execute
     * @Description: HttpClient Execute get
     * @param getUrl 请求URL
     * @return
     * @return Object
     * @throws
     */
    @SuppressWarnings("rawtypes")
    public static int execute(String getUrl ,int timeout){
        loger.info("timer","httpGet","开始请求HttpClient,参数[getUrl: "+getUrl);
        /** 参数校验 */
        if(null == getUrl || "".equals(getUrl)){
            loger.debug("请求URL为空！");
            return HttpStatus.SC_NOT_FOUND;
        }
        // 返回对象
        Object obj = null;
        // 执行postMethod
        int statusCode = 0;
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        HttpClient httpClient=null;
        GetMethod getMethod=null;
        try{
            httpClient = new HttpClient(connectionManager);
            //设置连接超时时间
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
            //设置请求超时时间
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeout);
            getMethod = new GetMethod(getUrl);
        }catch(Exception ex){
            loger.error("timer","httpException: "+ex.getMessage());
        }
        try {
            statusCode = httpClient.executeMethod(getMethod);
            loger.info("timer","execute","HttpClient Return Code : " + statusCode);
        } catch (UnknownHostException e){
            loger.info("timer","execute","http connect timeout:"+getUrl);
        }catch (HttpException e) {
            loger.error("timer","httpException: "+e.getMessage());
        } catch (IOException e) {
            loger.error("timer","IOException: "+e.getMessage());
        }
        return statusCode;
    }
    
    /**
     * Post方法
     * @param postUrl   请求地址
     * @param content  请求数据
     * @param type  请求类型
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static int execute(String postUrl , String content, String type, String encoding,int timeout) throws UnsupportedEncodingException{
        /** 参数校验 */
        if(StringUtils.isBlank(postUrl) || StringUtils.isBlank(content) || StringUtils.isBlank(type)){
            loger.info("HttpClient=Post发起=缺失必要参数！");
            return HttpStatus.SC_NOT_FOUND;
        }
        // 返回对象
        Object obj = null;
        // 执行postMethod
        int statusCode = 0;
        MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        HttpClient httpClient = new HttpClient(connectionManager);
        //设置连接超时时间
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
        //设置请求超时时间
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeout);
        PostMethod postMethod = new PostMethod(postUrl);
        /** 循环遍历map参数,使用addParameter放入PostMethod中 */
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);
        postMethod.setRequestEntity(new StringRequestEntity(content, type, encoding));
        
        try {
            long startTime = System.currentTimeMillis();
            statusCode = httpClient.executeMethod(postMethod);
            long endTime = System.currentTimeMillis();
            loger.info("timer","execute","HttpClient=Post=请求得到响应("+statusCode+")，请求时长("+(endTime-startTime)+")");
        } catch (UnknownHostException e){
            loger.info("timer","execute","http connect timeout:"+postUrl);
        }catch (HttpException e) {
            e.printStackTrace();
            loger.error("timer","httpException: "+e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            loger.error("timer","IOException: "+e.getMessage());
        }
        return statusCode;
    }
}
