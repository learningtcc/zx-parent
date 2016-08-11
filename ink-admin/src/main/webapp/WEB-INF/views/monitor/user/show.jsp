<%@ page import="com.ink.monitor.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>用户信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
	<h2>用户详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">账号:</td>	
			<td><c:out value="${model.userName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">姓名:</td>	
			<td><c:out value="${model.fullName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">手机号:</td>	
			<td><c:out value="${model.mobile}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">邮箱:</td>	
			<td><c:out value="${model.email}"/></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">告警类型:</td>
			<td>
				<yk:constantConvert htmlTag="text" dataTag="warnType" paramValue="${model.warnType}" paramSplit="," emptyTip="未知" sysTag="monitor"/>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">状态:</td>	
			<td>
				<yk:constantConvert htmlTag="text" dataTag="status" paramValue="${model.status}" emptyTip="未知" sysTag="monitor"/>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">创建时间:</td>	
			<td><c:out value="${model.createTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">更新时间:</td>	
			<td><c:out value="${model.updateTimeString}"/></td>
		</tr>
	</table>
	</body>
</html>