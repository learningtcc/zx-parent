<%@ page import="com.ink.monitor.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>日志监控信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
	<h2>日志监控详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">平台系统:</td>	
			<td>
				<yk:code2name tableName="systerm_source" columnName="name" where="code=?" paramValue="${model.source}" emptyTip="${model.source}" serviceName="monitorDubboBaseService"/>
			</td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">功能模块:</td>
			<td><yk:code2name tableName="systerm_module" columnName="name" where="code=?" paramValue="${model.module}" emptyTip="${model.module}" serviceName="monitorDubboBaseService"/></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">业务模块:</td>
			<td><yk:code2name tableName="systerm_info" columnName="name" where="code=?" paramValue="${model.infoId}" emptyTip="${model.infoId}" serviceName="monitorDubboBaseService"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">日志级别:</td>	
			<td><c:out value="${model.logLevel}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">日志时间:</td>	
			<td><c:out value="${model.logTime}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">交易流水:</td>	
			<td><c:out value="${model.tradeId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">请求系统:</td>	
			<td><c:out value="${model.reqContext}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">响应系统:</td>	
			<td><c:out value="${model.resContext}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">用户ID:</td>
			<td><c:out value="${model.userId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">用户帐号:</td>	
			<td> <yk:code2name tableName="monitor_user" columnName="fullName" where="userName=?" paramValue="${model.userName}" emptyTip="${model.userName}" serviceName="monitorDubboBaseService"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">请求IP:</td>
			<td><c:out value="${model.reqIp}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">响应IP:</td>
			<td><c:out value="${model.serverIp}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">请求流水:</td>	
			<td><c:out value="${model.requestId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">请求URL:</td>	
			<td><c:out value="${model.requestUrl}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">SESSIONID:</td>	
			<td><c:out value="${model.sessionId}"/></td>
		</tr>

		<tr>
			<td class="txtr" style="width: 188px">线程名称:</td>	
			<td><c:out value="${model.threadName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">触发类:</td>	
			<td><c:out value="${model.logger}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">日志消息:</td>	
			<td><c:out value="${model.message}"/></td>
		</tr>
	</table>
	</body>
</html>