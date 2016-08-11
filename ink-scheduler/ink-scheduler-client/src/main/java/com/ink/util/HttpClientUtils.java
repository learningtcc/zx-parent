package com.ink.util;

import com.ink.base.log.util.YinkerLogger;
import net.sf.json.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


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
    public static Map<String,String> execute(String postUrl , Map<String , Object> map,int timeout){
        loger.info("timer","httpPost","开始请求HttpClient,参数[postURL: "+postUrl+"],[map: "+map+"]");
        Map<String,String> resultMap = new HashMap<String, String>();
        /** 参数校验 */
        if(null == postUrl || "".equals(postUrl)){
            loger.debug("请求URL为空！");
            resultMap.put("result","false");
            resultMap.put("errorMSG","请求URL为空！");
            return resultMap;
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
            statusCode= httpClient.executeMethod(postMethod);
            loger.info("timer","execute","HttpClient Return Code : " + statusCode);
            if(statusCode == HttpStatus.SC_OK){
                byte[] responseBody = postMethod.getResponseBody();
                JSONObject resultJson = JSONObject.fromObject(new String(responseBody));
                resultMap.put("result",resultJson.getString("result"));
            }
        } catch (UnknownHostException e){
            loger.info("timer","execute","http connect timeout:"+postUrl);
        }catch (HttpException e) {
            loger.error("timer","httpException: "+e.getMessage());
        } catch (IOException e) {
            loger.error("timer","IOException: "+e.getMessage());
        }
        return resultMap;
    }

}
