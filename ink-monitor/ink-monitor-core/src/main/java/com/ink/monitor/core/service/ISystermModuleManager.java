/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.service;

import com.ink.monitor.core.po.SystermModule;
import com.ink.base.IBaseManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface ISystermModuleManager extends IBaseManager<SystermModule, Integer>{
 /**
  * 更新信息
  * @param systermModule 参数
  * @return 更新行数
  */
 void updateInfo(SystermModule systermModule);

 /**
  * 删除信息
  * @param id
  */
 void deleteInfo(Integer id,String status);
}
