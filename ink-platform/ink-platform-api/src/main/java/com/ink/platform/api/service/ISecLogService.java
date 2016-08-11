package com.ink.platform.api.service;

import java.util.List;

import com.ink.platform.api.model.SecLog;
import com.ink.platform.api.util.PageVO;


/**
 * 操作日志接口
 * @author lww
 *
 */
public interface ISecLogService {

	/**
	 * 查询日志信息
	 * @param pagevo
	 * @param secLog
	 * @return
	 */
	List<SecLog> selectList(PageVO<SecLog> pagevo, SecLog secLog);

	/**
	 * 新增日志记录
	 * @param seclog
	 */
	void insertSelective(SecLog seclog);
    
}
