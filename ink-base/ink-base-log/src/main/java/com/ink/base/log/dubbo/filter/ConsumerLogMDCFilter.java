package com.ink.base.log.dubbo.filter;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.MDC;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcInvocation;
import com.alibaba.dubbo.rpc.RpcResult;
import com.ink.base.log.util.LogConst;
import com.ink.base.log.util.RequestLog;
import com.ink.base.log.util.YinkerLogger;

/**
 * dubbo协议日志透传
 * @author zongtt
 * @see <dubbo:consumer filter="consumerLogMDCFilter"/>
 * 2016年4月18日18:12:26
 */
public class ConsumerLogMDCFilter implements Filter{
	
	private final String DUBBO_SERVICE_REQUEST_LOG = "dubbo.service.request.log";
	private final String DUBBO_SERVICE_REQUEST_LOG_SEQ = "dubbo.service.request.log.seq";
	
	private YinkerLogger logger = YinkerLogger.getLogger(getClass());
	
	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		
		RpcResult result = null;
		
		//客户端调用服务接口之前传入日志信息
		RequestLog log = RequestLog.getInstance();
		
		RpcInvocation rpcInvocation = (RpcInvocation) invocation;
		HashMap<String, String> attachments = new HashMap<String,String>();
		attachments.put(DUBBO_SERVICE_REQUEST_LOG, log.encodeBase64());
		rpcInvocation.addAttachments(attachments);
		//调用远程服务
		result = (RpcResult)invoker.invoke(invocation);
		
		//TODO 目前dubbo无法取回结果
		String logSeq = result.getAttachment(DUBBO_SERVICE_REQUEST_LOG_SEQ);
		if(StringUtils.isNotBlank(logSeq)){
			MDC.put(LogConst.LOG_SEQ, logSeq);
		}
		return result;
		
	}
}