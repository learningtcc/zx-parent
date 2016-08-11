<%@ page import="com.ink.msgcenter.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=SmsTemplate.TABLE_ALIAS%>信息</title>
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
	<h2><%=SmsTemplate.TABLE_ALIAS%>详情</h2>
</div>
	<table class="box" style="width: 100%">
		<tr><th colspan="6">基本信息</th></tr>
		<tr>	
			<th>商户ID:</th>	
			<td>
			<yk:code2name columnName="name" tableName="merchant_info" where=" id=? " paramValue="${model.merctId}" serviceName="msgcenterDubboBaseService"/>
			</td>
			<th>商户代码:</th>	
			<td><c:out value="${model.merctCode}"/></td>
			<th>模板名称:</th>	
			<td><c:out value="${model.tempName}"/></td>
		</tr>
		<tr>	
			<th>模板内容:</th>	
			<td colspan="5">
    				${model.tempContent}
			</td>
		</tr>
		<tr>	
			<th>解析方式:</th>	
			<td>
				<yk:constantConvert htmlTag="text" dataTag="parseMethod" name="parseMethod" paramValue="${model.parseMethod}" emptyTip="未定义" sysTag="msgcenter"/>
			</td>
			<th>模板备注:</th>	
			<td><c:out value="${model.tempRemark}"/></td>
			<th>模板参数:</th>	
			<td><c:out value="${model.tempParam}"/></td>
		</tr>
		
		<tr>	
			<th>状态:</th>	
			<td>
				<yk:constantConvert htmlTag="text" dataTag="templateStatus" name="status" paramValue="${model.status}" emptyTip="未定义" sysTag="msgcenter"/>
			</td>
			<th>创建人:</th>	
			<td><c:out value="${model.creatorName}"/></td>
			<th>创建时间:</th>	
			<td><fmt:formatDate value="${model.createTime}" pattern="yyyy-mm-dd HH:mm:ss"/></td>
			
		<tr><td colspan="6">&nbsp;</td></tr>
		<tr><th colspan="6">操作日志</th></tr>
		<tr>
			<th colspan="2">操作时间:</th>
			<th>操作人</th>
			<th colspan="2">操作内容</th>
			<th colspan="1">操作</th>
		</tr>
		<tr>
			<td colspan="2">${log.operateTimeString }</td>
			<td>${log.operatorName }</td>
			<td colspan="2">修改了${log.logRemark }</td>
			<td colspan="1"><font color="red"><a href="javascript:;" onclick="showLog('${model.id}')">详情..</a></font></td>
		</tr>
		<tr><th colspan="6">更多历史</th></tr>
		<c:forEach var="entry" items="${page.result}" varStatus="v">
		<tr>
			<td colspan="2">${entry.operateTimeString }</td>
			<td>${entry.operatorName }</td>
			<td colspan="2">修改了${entry.logRemark }</td>
			<td colspan="1"><font color="red"><a href="javascript:;" onclick="showHistoryLog('${entry.id}')">详情..</a></font></td>
		</tr>
		</c:forEach>
		<tr><td colspan="6"><yk:page url="${yk}/SmsTemplateLog/list.do" id="logForm" submitForm="queryForm" joy="true"/></td></tr>
	</table>
	<form id="queryForm" name="queryForm" action="<c:url value="/SmsTemplate/show.do?id=${model.id }"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	</form>
	</body>
<script type="text/javascript">
	function showLog(id) {
		window.open('${yk}/msgcenter/SmsTemplate/showLog.do?id='
				+ id, '','resizable=yes,location=no,scrollbars');
	}
	
	function showHistoryLog(id) {
		window.open('${yk}/msgcenter/SmsTemplate/showHistoryLog.do?id='
				+ id, '','resizable=yes,location=no,scrollbars');
	}
</script>
</html>