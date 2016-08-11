<%@ page import="com.ink.monitor.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=MonitorMqLog.TABLE_ALIAS%>信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
	<h2><%=MonitorMqLog.TABLE_ALIAS%>详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">系统代码:</td>	
			<td><c:out value="${model.sysCode}"/></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">应用名称:</td>
			<td><c:out value="${model.appName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">MQ交换器:</td>	
			<td><c:out value="${model.exchange}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">MQ路由:</td>	
			<td><c:out value="${model.routingKey}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">消息ID:</td>	
			<td><c:out value="${model.msgId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">MQ IP:</td>	
			<td><c:out value="${model.rabbitAddress}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">MQ端口:</td>	
			<td><c:out value="${model.rabbitPort}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">本地IP:</td>	
			<td><c:out value="${model.localAddress}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">消息异常:</td>	
			<td><c:out value="${model.msgException}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">请求流水:</td>	
			<td><c:out value="${model.requestId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">消息类型:</td>	
			<td>
				<c:out value="${model.msgType}"/>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">字符串消息:</td>	
			<td><c:out value="${model.msgText}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">序列化消息:</td>	
			<td><c:out value="${model.msgObject}"/></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">类名:</td>
			<td><c:out value="${model.className}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">状态:</td>
			<td><yk:constantConvert htmlTag="text" dataTag="mq_status" paramValue="${model.logStatus}" emptyTip="未知" sysTag="monitor"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">创建时间:</td>	
			<td><c:out value="${model.createTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">处理时间:</td>	
			<td><c:out value="${model.fixedTimeString}"/></td>
		</tr>
	</table>
	</body>
</html>