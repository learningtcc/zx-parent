
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>发送信息信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
	<h2>发送信息详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">文件id:</td>	
			<td><c:out value="${model.fileId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">发送时间:</td>	
			<td><c:out value="${model.sendTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">发送类型:</td>	
			<td>
				<c:choose>
					<c:when test="${model.sendType eq 0 }">及时发送</c:when>
					<c:when test="${model.sendType eq 1 }">延时发送</c:when>
					<c:otherwise>未定义</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">操作员名称:</td>	
			<td><c:out value="${model.operatorName}"/></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">文件路径:</td>	
			<td><div style="width: 100px;"><c:out value="${model.filePath}" escapeXml="false"></c:out></div></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">活动信息:</td>	
			<td><c:out value="${model.eventInfo}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">商户号:</td>	
			<td><c:out value="${model.mchId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">状态:</td>	
			<td>
				<c:choose>
					<c:when test="${model.status == 0 }">已保存</c:when>
					<c:when test="${model.status == 1 }">已发送</c:when>
					<c:otherwise>未定义</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</table>
	</body>
</html>