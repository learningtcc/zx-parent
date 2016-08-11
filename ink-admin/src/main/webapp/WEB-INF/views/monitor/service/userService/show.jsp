<%@ page import="com.ink.monitor.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=MonitorUserService.TABLE_ALIAS%>信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
	<h2><%=MonitorUserService.TABLE_ALIAS%>详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">服务id:</td>	
			<td><c:out value="${model.serviceId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">用户id:</td>	
			<td><c:out value="${model.userId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">平台系统编号:</td>	
			<td><c:out value="${model.sysCode}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">创建时间:</td>	
			<td><c:out value="${model.createTimeString}"/></td>
		</tr>
	</table>
	</body>
</html>