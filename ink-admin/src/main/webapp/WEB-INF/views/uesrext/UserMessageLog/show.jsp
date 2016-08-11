
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>用户消息日志信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
	<h2>用户消息日志详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">文件号:</td>	
			<td><c:out value="${model.fileId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">用户id:</td>	
			<td><c:out value="${model.userId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">商户号:</td>	
			<td><c:out value="${model.mchId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">姓名:</td>	
			<td><c:out value="${model.name}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">手机号:</td>	
			<td><c:out value="${model.phone}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">发送状态:</td>	
			<td>
				<c:choose>
					<c:when test="${model.status eq '0' }">已提交</c:when>
					<c:when test="${model.status eq '1' }">发送成功</c:when>
					<c:when test="${model.status eq '2' }">发送失败</c:when>
				</c:choose>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">营销活动信息:</td>	
			<td><c:out value="${model.eventInfo}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">信息渠道:</td>	
			<td><c:out value="${model.msgChannel}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">信息模板:</td>	
			<td><c:out value="${model.msgTemplate}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">创建时间:</td>	
			<td><c:out value="${model.createTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">更新时间:</td>	
			<td><c:out value="${model.updateTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">删除标识:</td>	
			<td><c:out value="${model.delFlag}"/></td>
		</tr>
	</table>
	</body>
</html>