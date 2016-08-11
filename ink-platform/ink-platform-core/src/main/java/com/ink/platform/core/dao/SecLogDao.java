package com.ink.platform.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ink.platform.api.model.SecLog;
import com.ink.platform.api.util.PageVO;

/**
 * 日志记录dao层
 * @author lww
 *
 */
@Repository("secLogDao")
public interface SecLogDao {
	/**
	 * @Description 日志分页查询
	 * @param secLog
	 * @author zhangyuanyang
	 */
	int selectListCount(@Param("queryEntity")SecLog secLog);
	/**
	 * 分页
	 * @param pageVo
	 * @param secLog
	 * @return
	 */
	List<SecLog> selectList(@Param("pageVO")PageVO<SecLog> pageVo,@Param("queryEntity")SecLog secLog);
	/**
	 * 新增日志
	 * @param seclog
	 */
	void insertSelective(SecLog seclog);

}
