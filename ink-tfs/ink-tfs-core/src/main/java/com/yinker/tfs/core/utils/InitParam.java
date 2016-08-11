package com.yinker.tfs.core.utils;

import com.yinker.tfs.core.po.SystermSource;
import com.yinker.tfs.core.query.SystermSourceQuery;
import com.yinker.tfs.core.service.ISystermSourceManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 初始化系统代码
 * @author haoyunfeng
 * @date 2016/5/27
 */
@Component
public class InitParam implements InitializingBean {

    @Autowired
    private ISystermSourceManager systermSourceManager;

    private static Map<String,String> sourceMap = new HashMap<String,String>();

    @Override
    public void afterPropertiesSet() throws Exception {
        List<SystermSource> sources = systermSourceManager.find(new SystermSourceQuery());
        for(SystermSource source : sources){
            sourceMap.put(source.getCode(),source.getName());
        }
    }

    public static Map<String, String> getSourceMap() {
        return sourceMap;
    }

    public static void setSourceMap(Map<String, String> sourceMap) {
        InitParam.sourceMap = sourceMap;
    }
}
