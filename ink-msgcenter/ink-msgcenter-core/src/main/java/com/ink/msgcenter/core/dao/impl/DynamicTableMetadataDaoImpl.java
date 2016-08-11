package com.ink.msgcenter.core.dao.impl;

import com.ink.base.dao.BaseIbatisDao;
import com.ink.msgcenter.core.dao.IDynamicTableMetadataDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 *动态表元数据处理持久化实现类
 * Created by aiyungui on 2016/5/16.
 */
@Repository("dynamicTableMetadataDao")
public class DynamicTableMetadataDaoImpl extends BaseIbatisDao<HashMap,Long> implements IDynamicTableMetadataDao {

    @Override
    public void createTableForSmsMerchantLog(Map<String, String> paraMap) throws Exception {
        this.getSqlSession().update("DynamicTableMetadata.createTableForSmsMerchantLog", paraMap);
    }

    @Override
    public void createTableForEmailMerchantLog(Map<String, String> paraMap) throws Exception {
        this.getSqlSession().update("DynamicTableMetadata.createTableForEmailMerchantLog", paraMap);
    }
}
