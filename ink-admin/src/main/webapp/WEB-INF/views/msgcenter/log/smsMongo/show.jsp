<%@ page import="com.ink.msgcenter.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=SmsLog.TABLE_ALIAS%>信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
 <style>
table.box {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.box th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
	width: 58px;
}
table.box td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
</style>
</head>
<body>
	<div class="title01">
	<h2><%=SmsLog.TABLE_ALIAS%>详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="box" style="width: 100%">
		<tr>	
			<th class="txtr">商户名称:</th>	
			<td><yk:code2name columnName="name" tableName="merchant_info" where="id = ?" paramValue="${model.merctId}" serviceName="msgcenterDubboBaseService"/></td>
			<th class="txtr">商户代码:</th>	
			<td><c:out value="${model.merctCode}"/></td>
		</tr>
		<tr>
			<th class="txtr">通道名称:</th>	
			<td><yk:code2name columnName="name" tableName="sms_channel" where="id = ?" paramValue="${model.chnId}" serviceName="msgcenterDubboBaseService"/></td>
			<th class="txtr">通道代码:</th>	
			<td><c:out value="${model.chnCode}"/></td>
		</tr>
		<tr>	
			<td class="txtr">模板ID:</td>	
			<td><c:out value="${model.tempId}"/></td>
		<%-- 	<td class="txtr">模板名称:</td>	
			<td><yk:code2name columnName="temp_name" tableName="email_template" where="id = ?" paramValue="${model.tempId}"></yk:code2name></td> --%>
			<td class="txtr">手机号:</td>	
			<td><c:out value="${model.mobile}"/></td>
		</tr>
		<tr>
			<td class="txtr">业务单号:</td>	
			<td><c:out value="${model.infoCode}"/></td>
			<td class="txtr">短信ID:</td>	
			<td><c:out value="${model.smsId}"/></td>
		</tr>
		<tr>
		<td class="txtr">短信内容:</td>	
			<td colspan="7"><c:out value="${model.smsMsg}"/></td>
		</tr>
		<%-- <tr>	
			<td class="txtr">短信代码:</td>	
			<td><c:out value="${model.smsCode}"/></td>
			<td class="txtr">短信优先级:</td>	
			<td><c:out value="${model.priority}"/></td>
			
			<td class="txtr">发送序列ID:</td>	
			<td><c:out value="${model.taskId}"/></td>
		</tr> --%>
		<tr>	
			<td class="txtr">发送状态:</td>	
			<td><c:choose>
						<c:when test="${model.sendStatus == 0}">待发送</c:when>
						<c:when test="${model.sendStatus == 1}">已发送</c:when>
						<c:when test="${model.sendStatus == 2}">发送失败</c:when>
						<c:otherwise>未定义</c:otherwise>
					</c:choose></td>
			<td class="txtr">响应代码:</td>	
			<td><c:out value="${model.responseCode}"/></td>
		</tr>
		<tr>
			<td class="txtr">短信类型:</td>	
			<td><c:choose>
						<c:when test="${model.smsType == 0}">上行短信</c:when>
						<c:when test="${model.smsType == 1}">下行短信</c:when>
						<c:otherwise>未定义</c:otherwise>
					</c:choose></td>
			<%-- <td class="txtr">状态报告:</td>	
			<td><c:out value="${model.reportStatus}"/></td> --%>
			
			<td class="txtr">提交时间:</td>	
			<td><c:out value="${model.submitTimeString}"/></td>
		</tr>	
		<tr>
			<td class="txtr">发送时间:</td>	
			<td><c:out value="${model.sendTimeString}"/></td>
			<td class="txtr">定时发送时间:</td>	
			<td><c:out value="${model.fixTimeString}"/></td>
			<%-- <td class="txtr">状态报告时间:</td>	
			<td><c:out value="${model.reportTimeString}"/></td> --%>
		</tr>
		<tr>	
			<td class="txtr">异常信息:</td>	
			<td colspan="7"><c:out value="${model.sendException}"/></td>
		</tr>
	</table>
	</body>
</html>