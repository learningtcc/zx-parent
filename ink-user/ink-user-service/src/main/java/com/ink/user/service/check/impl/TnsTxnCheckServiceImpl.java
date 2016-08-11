package com.ink.user.service.check.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ink.user.core.po.TnsTxn;
import com.ink.user.core.service.tns.ITnsTxnService;
import com.ink.user.service.check.api.Checker;
import com.ink.user.service.check.bean.Constants;

/**
 * 服务是否存在检查
 * @author yangchen
 * @date 2016年5月24日 下午4:58:30
 */
@Service("tnsTxnCheckService")
public class TnsTxnCheckServiceImpl extends AbstractCheck implements Checker{
	@Autowired
	private ITnsTxnService tnsTxnService;
	
	@Override
	public Map<String, Object> check(String txnCode, Map<String, String> dtoMap, Map<String, Object> map)
			throws Exception {
		TnsTxn tnsTxn = tnsTxnService.checkTnsTxn(txnCode);
		return putMap(map, Constants.TnsTxn, tnsTxn);
	}

}
