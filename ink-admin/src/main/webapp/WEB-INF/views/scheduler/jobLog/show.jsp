<%@ page import="com.ink.scheduler.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=TimerTaskJobLog.TABLE_ALIAS%>信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
	<h2><%=TimerTaskJobLog.TABLE_ALIAS%>详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">任务ID:</td>	
			<td><c:out value="${model.jobId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">任务开始时间:</td>	
			<td><c:out value="${model.startTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">任务结束时间:</td>	
			<td><c:out value="${model.endTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">任务执行结果:</td>	
			<td><c:out value="${model.status}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">业务流水ID:</td>	
			<td><c:out value="${model.reqId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">错误信息:</td>	
			<td><c:out value="${model.errorMsg}"/></td>
		</tr>
	</table>
	</body>
</html>