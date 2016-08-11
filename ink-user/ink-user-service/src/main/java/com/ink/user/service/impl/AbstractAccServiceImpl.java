package com.ink.user.service.impl;


import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.ink.user.service.check.api.CheckChainManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ink.user.api.model.InterestBean;
import com.ink.user.core.po.ReqLog;
import com.ink.user.core.po.TnsLog;
import com.ink.user.core.po.TnsTxn;
import com.ink.user.service.check.api.CheckChainExcuteService;
import com.ink.user.service.check.api.Checker;
import com.ink.user.service.check.api.CheckerContainer;

/**
 * 
 * @author guojie.gao 信息验证
 * @param <R>
 * @param <T>
 */
public abstract class AbstractAccServiceImpl<R, T> {

	@Autowired
	private CheckChainManageService checkChainManageService;
	@Autowired
	private CheckChainExcuteService checkChainExcuteService;
	@Autowired
	protected ThreadPoolTaskExecutor taskExecutor;
	
	public abstract void insertTnsLog(TnsLog tnsLog, ReqLog reqLog, String json, TnsTxn tnsTxn)
			throws Exception;

	/**
	 * 检查，各种检查
	 * @param txnCode
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> check(String txnCode, String json) throws Exception{
		CheckerContainer container = checkChainManageService.getCheckerContainer(txnCode);
		Map<String, String> dtoMap = JSON.parseObject(json,
				new TypeReference<TreeMap<String, String>>() {
				});
		Map<String, Object> map = new HashMap<String, Object>();
		return checkChainExcuteService.executeCheck(container, txnCode, dtoMap, map, getExtendCheckers());
	}

	/**
	 * 获取扩展检查器，需要做额外检查的服务重新此方法
	 * @return
	 */
	protected Checker[] getExtendCheckers() {
		return null;
	}

	// 批量接口使用
	public Map<String, Object> interestCheck(InterestBean interest,
			String txnCode, String mchId) throws Exception {
		JSONObject json = (JSONObject) JSON.toJSON(interest);
		json.put("txnCode", txnCode);
		json.put("mchId", mchId);
		Map<String, Object> map = check(txnCode, json.toJSONString());
		return map;
	}


	/**
	 * 启动异步线程
	 * 
	 * @param dto
	 */
	public void callAsynProcess(final T dto) {
		taskExecutor.execute(new Runnable() {

			@Override
			public void run() {
				doAsynProcess(dto);
			}
		});
	}

	/**
	 * 启动完异步线程需要做的事 使用callAsynProcess方法启动异步线程的子类务必重写此方法
	 * 
	 * @param dto
	 */
	protected void doAsynProcess(T dto) {
	};

}
