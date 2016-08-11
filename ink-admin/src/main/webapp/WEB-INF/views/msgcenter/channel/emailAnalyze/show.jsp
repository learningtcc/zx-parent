<%@ page import="com.ink.msgcenter.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=EmailAnalyze.TABLE_ALIAS%>信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
	<h2><%=EmailAnalyze.TABLE_ALIAS%>详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">通道ID:</td>	
			<td><c:out value="${model.chnId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">通道代码:</td>	
			<td><c:out value="${model.chnCode}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">发送总数:</td>	
			<td><c:out value="${model.sendCount}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">成功总数:</td>	
			<td><c:out value="${model.successCount}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">失败总数:</td>	
			<td><c:out value="${model.failCount}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">统计日期:</td>	
			<td><c:out value="${model.analyzeDateString}"/></td>
		</tr>
	</table>
	</body>
</html>