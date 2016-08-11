package com.ink.base.log.dubbo.filter;

import org.slf4j.MDC;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcResult;
import com.ink.base.log.util.LogConst;
import com.ink.base.log.util.MDCLog;
import com.ink.base.log.util.YinkerLogger;

/**
 * dubbo协议日志透传
 * @author zongtt
 * @see <dubbo:provider filter="providerLogMDCFilter"/>
 * 2016年4月18日18:12:26
 */
public class ProviderLogMDCFilter implements Filter{
	
	private final String DUBBO_SERVICE_REQUEST_LOG = "dubbo.service.request.log";
	private final String DUBBO_SERVICE_REQUEST_LOG_SEQ = "dubbo.service.request.log.seq";
	
	private YinkerLogger loger = YinkerLogger.getLogger(ProviderLogMDCFilter.class);

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		
//		loger.info("dubbo服务方法:{}，参数:{}", invocation.getMethodName(),JSON.toJSONString(invocation.getArguments()));
		
		RpcResult result = null;
		
		String requestLog = invocation.getAttachment(DUBBO_SERVICE_REQUEST_LOG);
		
		//到达服务端
		//设置MDC
		MDCLog.setMdcConfig(requestLog, "dubbo");
		
		try {
			//调用本地服务
			result = (RpcResult)invoker.invoke(invocation);
			//TODO 将日志序列加入到返回中，目前dubbo无法实现回传
			result.setAttachment(DUBBO_SERVICE_REQUEST_LOG_SEQ, MDC.get(LogConst.LOG_SEQ));
		} catch (RpcException e) {
			throw e;
		}finally {
			//清理MDC
			MDCLog.clear();
		}
		
		return result;
		
	}
}
