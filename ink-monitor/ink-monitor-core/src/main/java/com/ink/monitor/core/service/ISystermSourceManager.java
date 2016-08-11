/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.monitor.core.service;

import com.ink.monitor.core.po.SystermSource;
import com.ink.base.IBaseManager;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 public interface ISystermSourceManager extends IBaseManager<SystermSource, Integer>{

 /**
  * 更新信息
  * @param systermSource 参数
  * @return 更新行数
  */
 void updateInfo(SystermSource systermSource);

 /**
  * 删除信息
  * @param id
  */
 void deleteInfo(Integer id,String status);
}
