package com.ink.channel.core.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class HttpClientUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    /**
     * @throws SocketTimeoutException
     * @throws ConnectTimeoutException
     * @throws HttpException
     * @throws IOException
     * @Title: execute
     * @Description: HttpClient Execute
     * @param postUrl
     *            请求URL
     * @param map
     *            参数集合
     * @return
     * @return Object
     * @throws
     */
    @SuppressWarnings("rawtypes")
    public static Object execute(String postUrl, Map<String, Object> map) throws ConnectTimeoutException,
                    SocketTimeoutException, HttpException,IOException,Exception {
        logger.info("开始请求HttpClient,参数[postURL: " + postUrl + "],[map: " + map + "]");
        /** 参数校验 */
        if (null == postUrl || "".equals(postUrl)) {
            logger.debug("请求URL为空！");
            return null;
        }
        // 返回对象
        Object obj = null;
        // 执行postMethod
        int statusCode = 0;
        // MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        HttpClient httpClient = null;
        PostMethod postMethod = null;
        try {
            httpClient = new HttpClient();
            postMethod = new PostMethod(postUrl);
            /** 循环遍历map参数,使用addParameter放入PostMethod中 */
            postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
            httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(1000 * 60);
            // httpClient.getHttpConnectionManager().getParams().setSoTimeout(1000 * 60);
            if (null != map) {
                Set<?> set = map.entrySet();
                Iterator<?> it = set.iterator();
                // 将表单的值放入postMethod中
                while (it.hasNext()) {
                    Map.Entry me = (Map.Entry) it.next();
                    postMethod.addParameter(me.getKey() + "", me.getValue() + "");
                }
                // postMethod.setRequestEntity(new StringRequestEntity("", "text/xml", "UTF-8"));
            }
            statusCode = httpClient.executeMethod(postMethod);
            logger.info("HttpClient Return Code : " + statusCode);
        } catch (ConnectTimeoutException connectTimeoutException) {
            throw connectTimeoutException;
        } catch (SocketTimeoutException socketTimeoutException) {
            throw socketTimeoutException;
        } catch (ConnectException connectException) {
            throw new ConnectTimeoutException();
        } catch (HttpException e) {
        	throw e;
        } catch (IOException e) {
        	throw e;
        }
        // HttpClient对于要求接受后继服务的请求，像POST和PUT等不能自动处理转发 ( 301或者302 )
        if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
            // 从头中取出转向的地址
            try {
                Header locationHeader = postMethod.getResponseHeader("location");
                String location = null;
                if (locationHeader != null) {
                    location = locationHeader.getValue();
                    logger.info("The page was redirected to: " + location);
                } else {
                    logger.info("Location field value is null.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            } finally {
                postMethod.releaseConnection();
            }
            return null;
        } else {
            try {
                obj = postMethod.getResponseBodyAsString();
                logger.info("得到返回结果集: " + obj);
            } catch (Exception e) {
                logger.info("请求HttpClient发生错误!");
                e.printStackTrace();
                throw e;
            } finally {
                postMethod.releaseConnection();
            }
        }
        return obj;
    }

    /**
     * Post方法
     * 
     * @param postUrl
     *            请求地址
     * @param text
     *            请求数据
     * @param type
     *            请求类型
     * @return
     * @throws UnsupportedEncodingException
     * @throws ConnectTimeoutException
     * @throws SocketTimeoutException
     */
    public static Object execute(String postUrl, String content, String type, String encoding)
                    throws UnsupportedEncodingException, ConnectTimeoutException, SocketTimeoutException {
        /** 参数校验 */
        if (StringUtils.isBlank(postUrl) || StringUtils.isBlank(content) || StringUtils.isBlank(type)) {
            logger.info("HttpClient=Post发起=缺失必要参数！");
            return null;
        }

        // 返回对象
        Object obj = null;
        // 执行postMethod
        int statusCode = 0;
        // MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
        HttpClient httpClient= new HttpClient();;
        PostMethod postMethod = new PostMethod(postUrl);
        /** 循环遍历map参数,使用addParameter放入PostMethod中 */
        postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, encoding);
        postMethod.setRequestEntity(new StringRequestEntity(content, type, encoding));
        try {
            long startTime = System.currentTimeMillis();
            statusCode = httpClient.executeMethod(postMethod);
            long endTime = System.currentTimeMillis();
            logger.info("HttpClient=Post=请求得到响应(" + statusCode + ")，请求时长(" + (endTime - startTime) + ")");
            if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
                // 从头中取出转向的地址
                Header locationHeader = postMethod.getResponseHeader("location");
                String location = null;
                if (locationHeader != null) {
                    location = locationHeader.getValue();
                    logger.info("The page was redirected to: " + location);
                } else {
                    logger.info("Location field value is null.");
                }
                return null;
            } else {
                obj = postMethod.getResponseBodyAsString();
                logger.info("HttpClient=Post=响应结果:[" + obj + "]");
            }
        } catch (ConnectTimeoutException connectTimeoutException) {
            throw connectTimeoutException;
        } catch (SocketTimeoutException socketTimeoutException) {
            throw socketTimeoutException;
        } catch (ConnectException connectException) {
            throw new ConnectTimeoutException();
        } catch (HttpException e) {
            e.printStackTrace();
            logger.error("httpException: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("IOException: " + e.getMessage());
        } finally {
            postMethod.releaseConnection();
        }
        return obj;
    }

}
