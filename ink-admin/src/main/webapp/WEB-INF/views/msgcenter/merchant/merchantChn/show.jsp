<%@ page import="com.ink.msgcenter.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=MerchantChn.TABLE_ALIAS%>信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
	<h2><%=MerchantChn.TABLE_ALIAS%>详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">商户ID:</td>	
			<td><c:out value="${model.merctId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">商户代码:</td>	
			<td><c:out value="${model.merctCode}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">通道类型:</td>	
			<td><c:out value="${model.chnType}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">通道ID:</td>	
			<td><c:out value="${model.chnId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">通道代码:</td>	
			<td><c:out value="${model.chnCode}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">状态(0正常1商户停用2通道停用):</td>	
			<td><c:out value="${model.status}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">创建人ID:</td>	
			<td><c:out value="${model.creatorId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">创建人姓名:</td>	
			<td><c:out value="${model.creatorName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">创建时间:</td>	
			<td><c:out value="${model.createTimeString}"/></td>
		</tr>
	</table>
	</body>
</html>