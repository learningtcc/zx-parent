package com.ink.base.service;

import java.util.List;

/**
 * 动态数据执行接口
 * Created by Lenovo on 2016/8/1.
 */
public interface IDynamicDataManager {

    List queryData(String sql, String[] values);
}
