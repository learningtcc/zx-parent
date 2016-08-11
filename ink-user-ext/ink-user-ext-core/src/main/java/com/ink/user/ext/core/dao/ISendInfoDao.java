/**    
 * <p> Copyright (c) 2015-2025 银客网.</p>
 * <p>All Rights Reserved. 保留所有权利. </p>
 */
package com.ink.user.ext.core.dao;

import java.util.List;

import com.ink.base.EntityDao;
import com.ink.user.ext.core.po.SendInfo;

/**
 * @author
 * @version 1.0
 * @since 1.0
 */
 

public interface ISendInfoDao extends EntityDao<SendInfo, java.lang.Long>{

	List<String> getFilePathListByDate(String start, String end);

	int updateByFilePath(String filePath, Integer status);

}