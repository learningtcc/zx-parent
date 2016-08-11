package com.ink.monitor.core.task.service;

import com.alibaba.fastjson.JSONArray;
import com.ink.monitor.core.dao.IMonitorServiceRecordDao;
import com.ink.monitor.core.po.MonitorService;
import com.ink.monitor.core.po.MonitorUser;
import com.ink.monitor.core.po.MonitorUserService;
import com.ink.monitor.core.query.MonitorServiceRecordQuery;
import com.ink.monitor.dubbo.EmailSender;
import com.ink.monitor.dubbo.SmsSender;
import com.ink.base.log.util.YinkerLogger;
import com.ink.base.utils.dateUtil.DateUtil;
import com.ink.monitor.core.dao.IMonitorUserServiceDao;
import com.ink.monitor.core.po.MonitorServiceRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 监控服务模版类
 * Created by aiyungui on 2016/4/27.
 */
public abstract class MonitorServiceJobTemplate {
    YinkerLogger loger = YinkerLogger.getLogger(IMonitorServiceJobService.class);
    protected String serviceInfo ;
    @Autowired
    protected IMonitorUserServiceDao monitorUserServiceDao;
    @Autowired
    protected IMonitorServiceRecordDao monitorServiceRecordDao;
    @Autowired
    protected SmsSender smsSender;
    @Autowired
    protected EmailSender emailSender;
//    protected MailService mailService;

    public boolean operateMonitorService(MonitorService monitorService)throws Exception{
        serviceInfo ="服务类型：" + monitorService.getSourceType()
                + ",服务名称：" + monitorService.getSourceName()
                + ",服务资源路径：" + monitorService.getSourceUrl();
        //1执行服务
        Map<String,String> monitorResultMap = operateDefiniteMonitorService(monitorService);
        //2获取服务执行结果
        String monitorCode = monitorResultMap.get("monitorCode");
        String resultCode = monitorResultMap.get("resultCode");
        String monitorMes = monitorResultMap.get("monitorMes");
        boolean result = false;
        loger.info("服务监控:",serviceInfo + ",返回状态:" +resultCode+ ", 监控状态：" + monitorCode + ",监控描述：" + monitorMes);
        //3判断服务执行结果 当服务有问题时，进行记录
        if ("0".equals(resultCode)){//监控无问题，不做任何处理。
            validateMonitorService(monitorService);
            result = true;
        }else if("1".equals(resultCode)){//监控有问题
            //不在定义需要记录数据的错误编码时，记录至日志文件
            validateMonitorService(monitorService);
        }else{//需要记录至数据库
            saveErrorMonitorServiceResult(monitorService, monitorResultMap);
        }

        return result;
    }

    /**
     * 验证监控服务，检查服务上一次的检查结果是否出现错误，如出现错误则把状态更新为已解决
     * @param monitorService
     */
    protected void validateMonitorService(MonitorService monitorService) throws Exception {

        MonitorServiceRecordQuery monitorServiceRecordQuery = new MonitorServiceRecordQuery();
        monitorServiceRecordQuery.setServiceId(monitorService.getId());
        monitorServiceRecordQuery.setStatus("0");
        monitorServiceRecordQuery.setSortColumns(" id desc");

        MonitorServiceRecord monitorServiceRecord = monitorServiceRecordDao.findLastErrorServiceRecord(monitorServiceRecordQuery);
        if (validateErrorTime(monitorServiceRecord)){
            return;
        }
        if (monitorServiceRecord != null && "0".equals(monitorServiceRecord.getStatus())){
            MonitorServiceRecord updateMonitorServiceRecord = new MonitorServiceRecord();
            updateMonitorServiceRecord.setId(monitorServiceRecord.getId());
            updateMonitorServiceRecord.setServiceId(monitorServiceRecord.getServiceId());
            updateMonitorServiceRecord.setErrorCount(monitorServiceRecord.getErrorCount());
            updateMonitorServiceRecord.setStatus("1");
            updateMonitorServiceRecord.setSafeTime(new Date());
            monitorServiceRecordDao.update(updateMonitorServiceRecord);
        }

    }

    /**
     *记录监控有问题的数据
     * 检查当前服务是第几次出现问题
     *  |-第一次出现问题，记录至数据表
     *  |-第n次出现问题，更新数据表出现错误次数
     * 检查错误次数与阀值、频次。当错误次数>=阀值，且<=频次时 通知服务监控人员
     * 根据服务监控人员的配置选项，选择通知方式(邮件、短信)
     * @param monitorService 监控服务信息
     * @param monitorResultMap 监控服务返回结果
     */
    protected void saveErrorMonitorServiceResult(MonitorService monitorService, Map<String, String> monitorResultMap) throws Exception {

        MonitorServiceRecordQuery monitorServiceRecordQuery = new MonitorServiceRecordQuery();
        monitorServiceRecordQuery.setServiceId(monitorService.getId());
        monitorServiceRecordQuery.setStatus("0");
        monitorServiceRecordQuery.setSortColumns(" id desc");

        String monitorCode = monitorResultMap.get("monitorCode");
        String monitorMes = monitorResultMap.get("monitorMes");
        int errCount = 1;

        MonitorServiceRecord monitorServiceRecord = monitorServiceRecordDao.findLastErrorServiceRecord(monitorServiceRecordQuery);
        if (validateErrorTime(monitorServiceRecord)){
            monitorServiceRecord = null;
        }
//        monitorServiceRecord = monitorServiceRecordDao.findLastErrorServiceRecord(monitorServiceRecordQuery);
        if (monitorServiceRecord != null && "0".equals(monitorServiceRecord.getStatus())){//数据库中存在错误记录
            errCount = monitorServiceRecord.getErrorCount() + 1;
            MonitorServiceRecord updateMonitorServiceRecord = new MonitorServiceRecord();
            updateMonitorServiceRecord.setId(monitorServiceRecord.getId());
            updateMonitorServiceRecord.setServiceId(monitorService.getId());
            updateMonitorServiceRecord.setErrorCount(errCount);
            if (!monitorServiceRecord.getVisitStatus().contains(monitorCode)){
                updateMonitorServiceRecord.setVisitStatus(monitorServiceRecord.getVisitStatus() + ";" + monitorCode);
                updateMonitorServiceRecord.setErrorDesc(monitorServiceRecord.getErrorDesc() + ";" + monitorMes);
            }
            monitorServiceRecordDao.update(updateMonitorServiceRecord);
        }else{//不存在错误记录 新增
            MonitorServiceRecord addMonitorServiceRecord = new MonitorServiceRecord();
            addMonitorServiceRecord.setServiceId(monitorService.getId());
            addMonitorServiceRecord.setSysCode(monitorService.getSysCode());
            addMonitorServiceRecord.setSourceUrl(monitorService.getSourceUrl());
            addMonitorServiceRecord.setErrorDesc(monitorMes);
            addMonitorServiceRecord.setVisitStatus(monitorCode);
            addMonitorServiceRecord.setErrorCount(errCount);
            addMonitorServiceRecord.setStatus("0");
            monitorServiceRecordDao.save(addMonitorServiceRecord);
        }
        //判断是否需要进行通知：错误次数>=阀值，且<=频次时 通知服务监控人员
        if(errCount > monitorService.getWarnThreshold()
                && errCount <= (monitorService.getWarnThreshold() + monitorService.getWarnFrequency())){//进行通知
            MonitorUserService monitorUserService = new MonitorUserService();
            monitorUserService.setServiceId(monitorService.getId());
            List<MonitorUser> monitorUserList = monitorUserServiceDao.findServiceMonitorUser(monitorUserService);
            for (int i = 0; i < monitorUserList.size(); i++) {
                MonitorUser monitorUser = monitorUserList.get(i);
                if (monitorUser.getWarnType().contains("0") && StringUtils.isNotBlank(monitorUser.getMobile())){
                    JSONArray paramJson = new JSONArray();
                    paramJson.add(monitorService.getSourceName());
                    paramJson.add(monitorService.getSourceUrl());
                    paramJson.add(monitorCode);
                    smsSender.sendSms(monitorUser.getMobile(),paramJson.toJSONString(),false);
                }
                if (monitorUser.getWarnType().contains("1")&& StringUtils.isNotBlank(monitorUser.getEmail())){
                    JSONArray paramJson = new JSONArray();
                    paramJson.add(monitorService.getSourceName());
                    paramJson.add(monitorService.getSourceType());
                    paramJson.add(monitorService.getSourceName());
                    paramJson.add(monitorService.getSourceUrl());
                    paramJson.add(monitorCode);
                    paramJson.add(monitorMes);
                    paramJson.add(DateUtil.formatToYYYYMMDDMMHHSS(new Date()));

                    paramJson.add(DateUtil.formatToYYYYMMDDMMHHSS(new Date()));
                    emailSender.sendEmail(monitorUser.getEmail(),paramJson.toJSONString(),false);
                }
            }
        }
    }

    /**
     * 如为前一天的监控，则把前一天的状态更新为“过期未处理”
     * @param monitorServiceRecord
     * @return
     */
    public boolean validateErrorTime(MonitorServiceRecord monitorServiceRecord) {

        if (monitorServiceRecord == null){
            return false;
        }
        if (DateUtil.formatToYYYYMMDD(monitorServiceRecord.getFirstErrorTime()).equals(DateUtil.formatToYYYYMMDD(new Date()))){
            return false;
        }else{
            MonitorServiceRecord updateMonitorServiceRecord = new MonitorServiceRecord();
            updateMonitorServiceRecord.setId(monitorServiceRecord.getId());
            updateMonitorServiceRecord.setServiceId(monitorServiceRecord.getServiceId());
            updateMonitorServiceRecord.setErrorCount(monitorServiceRecord.getErrorCount());
            updateMonitorServiceRecord.setStatus("2");
            monitorServiceRecordDao.update(updateMonitorServiceRecord);
            return true;
        }

    }

    /**
     * 钩子方法
     * @param monitorService
     * @return
     * @throws Exception
     */
    public abstract Map<String,String> operateDefiniteMonitorService(MonitorService monitorService)throws Exception;

}
