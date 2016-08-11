<%@ page import="com.ink.msgcenter.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>邮件通道信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
	<h2>邮件通道详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">通道名称:</td>	
			<td><c:out value="${model.name}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">通道代码:</td>
			<td><c:out value="${model.chnCode}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">发件服务器:</td>	
			<td><c:out value="${model.smtpHost}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">发送端口:</td>	
			<td><c:out value="${model.smtpPort}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">发送邮箱:</td>	
			<td><c:out value="${model.mailAddr}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">发送邮箱密码:</td>	
			<td><c:out value="${model.mailPwd}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">通道备注:</td>	
			<td><c:out value="${model.remark}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">通道优先级:</td>
			<td><c:out value="${model.priorityLevel}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">状态:</td>
			<td>
				<yk:constantConvert htmlTag="text" dataTag="del_status" paramValue="${model.status}" emptyTip="未知" sysTag="msgcenter"/>
			</td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">创建人姓名:</td>	
			<td><c:out value="${model.creatorName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">创建时间:</td>	
			<td><c:out value="${model.createTimeString}"/></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">更新人姓名:</td>	
			<td><c:out value="${model.updatorName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">更新时间:</td>	
			<td><c:out value="${model.updateTimeString}"/></td>
		</tr>
	</table>
	</body>
</html>