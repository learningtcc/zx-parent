package com.ink.msgcenter.core.dao;

import java.util.Map;

/**
 * 动态表元数据处理持久化类
 * Created by aiyungui on 2016/5/16.
 */
public interface IDynamicTableMetadataDao {

    void createTableForSmsMerchantLog(Map<String,String> paraMap)throws Exception;

    void createTableForEmailMerchantLog(Map<String,String> paraMap)throws Exception;
}
