<%@ page import="com.ink.msgcenter.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=SmsCode.TABLE_ALIAS%>信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
	<h2><%=SmsCode.TABLE_ALIAS%>详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">短信ID:</td>	
			<td><c:out value="${model.smsId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">短信代码:</td>	
			<td><c:out value="${model.smsCode}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">商户ID:</td>	
			<td><c:out value="${model.merctId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">商户代码:</td>	
			<td><c:out value="${model.merctCode}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">发送序列ID:</td>	
			<td><c:out value="${model.taskId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">手机号:</td>	
			<td><c:out value="${model.mobile}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">扩展信息:</td>	
			<td><c:out value="${model.extInfo}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">状态:</td>
			<td><c:choose>
					<c:when test="${model.status  eq 0}">正常</c:when>
					<c:when test="${model.status  eq 2}">删除</c:when>
					<c:otherwise>未定义</c:otherwise>
				</c:choose></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">创建时间:</td>	
			<td><c:out value="${model.createTimeString}"/></td>
		</tr>
	</table>
	</body>
</html>