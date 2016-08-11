package com.ink.monitor.core.service.impl;

import com.google.common.collect.Maps;
import com.ink.base.utils.dateUtil.DateUtil;
import com.ink.monitor.core.service.IMongoLogChartServiceManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统错误日志Mongo图表分析实现类
 * Created by Lenovo on 2016/7/19.
 */
@Service("mongoLogChartServiceManager")
public class MongoLogChartServiceManagerImpl implements IMongoLogChartServiceManager {

    protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    @Autowired
    private MongoTemplate mongoTemplate;
    private static String logChartName = "log_chart_";
    /**
     * 记录分钟系统日志到mongo
     *
     * @param sysCode
     * @param logDate
     */
    @Override
    public void saveMinuteSysErrorLog(String sysCode, Date logDate) {

        try{
            if (StringUtils.isBlank(sysCode) ||  null == logDate){
                return ;
            }

            String minuteStr = DateUtil.getDateMinute(logDate);
            minuteStr = minuteStr + ":00";
            logDate = DateUtil.formatToyyyyMMddHHmmss(minuteStr);

            boolean isHave = false;
            String collectionName = logChartName + sysCode;
            Query query = null;
            if (mongoTemplate.collectionExists(collectionName)){//检查是否存在
                query = new Query();
                Criteria criteria = Criteria.where("log_error_date").is(logDate);
                query.addCriteria(criteria);
                List<Map> dataList = mongoTemplate.find(query, Map.class,  collectionName);
                if (!(dataList == null || dataList.isEmpty())){
                    isHave = true;
                }
            }

            if (isHave){//存在则更新
                Update update = new Update();
                update.inc("error_count",1);
                mongoTemplate.updateFirst(query,update,collectionName);
            }else{//不存在则删除
                Map<String,Object>  dataMap = Maps.newHashMap();
                dataMap.put("sysCode",sysCode);
                dataMap.put("log_error_date",logDate);
                dataMap.put("error_count",1);
                mongoTemplate.insert(dataMap,logChartName+sysCode);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 统计时间间隔内系统共出错次数
     *
     * @param sysCode
     * @param startMinute
     * @param endMinute
     * @return
     */
    @Override
    public int countSysErrorLog(String sysCode, Date startMinute, Date endMinute) {

        if (StringUtils.isBlank(sysCode) || null == startMinute || null == endMinute){
            return 0;
        }

        String collectionName = logChartName + sysCode;
        if (!mongoTemplate.collectionExists(collectionName)){
            return 0;
        }

        Query query = new Query();
        Criteria criteria = Criteria.where("log_error_date").gte(startMinute);
        criteria.lt(endMinute);
        query.addCriteria(criteria);

        List<Map> dataList = mongoTemplate.find(query, Map.class,  collectionName);

        int count = 0;
        for(Map dataMap : dataList){
            int error_count = (int) dataMap.get("error_count");
            count += error_count;
        }
        return count;
    }


}
