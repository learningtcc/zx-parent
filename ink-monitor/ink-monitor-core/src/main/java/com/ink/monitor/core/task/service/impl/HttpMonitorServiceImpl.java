package com.ink.monitor.core.task.service.impl;

import com.ink.monitor.core.po.MonitorService;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.page.Page;
import com.ink.base.page.PageRequest;
import com.ink.monitor.core.task.service.IMonitorServiceJobService;
import com.ink.monitor.core.task.service.MonitorServiceJobTemplate;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * http服务监控
 * 使用httpClient框架实现
 * Created by aiyungui on 2016/4/27.
 */
@Service("httpMonitorService")
public class HttpMonitorServiceImpl extends MonitorServiceJobTemplate implements IMonitorServiceJobService {

    private YinkerLogger loger = YinkerLogger.getLogger(IMonitorServiceJobService.class);

    @Override
    public void executeMonitorService(MonitorService monitorService) throws Exception {
        operateMonitorService(monitorService);
    }

    /**
     * 钩子方法
     * @param monitorService
     * @return
     * @throws Exception
     */
    @Override
    public Map<String, String> operateDefiniteMonitorService(MonitorService monitorService) throws Exception {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String monitorCode = "error";
        String monitorMes = "请输入正确的监控网址";
        String resultCode = "2";
        Map<String,String> monitorResultMap = new HashMap<String,String>();
        try {
            httpClient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(monitorService.getSourceUrl());
            //设置请求和传输超时时间
            RequestConfig requestConfig = RequestConfig.custom().
                    setSocketTimeout(5000).
                    setConnectTimeout(5000).build();
            httpget.setConfig(requestConfig);
            response = httpClient.execute(httpget);
            StatusLine statusLine = response.getStatusLine();

            monitorCode = String.valueOf(statusLine.getStatusCode());
            monitorMes = statusLine.getReasonPhrase();
            if (statusLine.getStatusCode() == 200){
                resultCode = "0";//统一标识成功
            }else if(statusLine.getStatusCode()>=400){
                resultCode = "2";//错误标识
            }else{
                resultCode = "1";//未正常响应，但不是错误标识
            }
        } catch (Exception e) {
           e.printStackTrace();
            resultCode = "2";
            loger.info("服务监控",serviceInfo + "出现异常：" + e.getMessage());
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

        monitorResultMap.put("resultCode",resultCode);
        monitorResultMap.put("monitorCode",monitorCode);
        monitorResultMap.put("monitorMes",monitorMes);
        return monitorResultMap;
    }


    @Override
    public MonitorService getById(Integer id) {
        return null;
    }

    @Override
    public MonitorService getById(Integer id, boolean masterMark) throws DataAccessException {
        return null;
    }

    @Override
    public int save(MonitorService entity) {
        return 0;
    }

    @Override
    public int saveBatch(List<MonitorService> list) {
        return 0;
    }

    @Override
    public int removeById(Integer id) {
        return 0;
    }

    @Override
    public int update(MonitorService entity) {
        return 0;
    }

    @Override
    public List<MonitorService> find(PageRequest query) {
        return null;
    }

    @Override
    public Page<MonitorService> findPage(PageRequest query) {
        return null;
    }
}
