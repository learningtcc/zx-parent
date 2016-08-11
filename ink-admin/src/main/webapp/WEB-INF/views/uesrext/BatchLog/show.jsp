
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>批次信息信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
	<h2>批次信息详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">文件号:</td>	
			<td><c:out value="${model.fileId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">批次号:</td>	
			<td><c:out value="${model.batchId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">条目数:</td>	
			<td><c:out value="${model.itemCount}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">发送时间:</td>	
			<td><c:out value="${model.sendTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">发送类型:</td>	
			<td>
				<c:choose>
					<c:when test="${entry.sendType eq 0 }">及时发送</c:when>
					<c:when test="${entry.sendType eq 1 }">延时发送</c:when>
					<c:otherwise>未定义</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">消息类型:</td>	
			<td>
				<c:choose>
					<c:when test="${entry.msgType eq '0'}">短信</c:when>
					<c:otherwise>未定义</c:otherwise>
				</c:choose>
			</td>
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
	</table>
	</body>
</html>