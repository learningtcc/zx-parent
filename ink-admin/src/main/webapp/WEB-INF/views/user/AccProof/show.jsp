
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>账户流水信息信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
	<h2>账户流水信息详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">创建时间:</td>	
			<td><c:out value="${model.createTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">最后修改时间:</td>	
			<td><c:out value="${model.lastUpdateTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">uid:</td>	
			<td><c:out value="${model.uid}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">主账号:</td>	
			<td><c:out value="${model.pacId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">子帐户号:</td>	
			<td><c:out value="${model.sacId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">子帐户种类:</td>	
			<td><c:out value="${model.sacType}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">币种:</td>	
			<td><c:out value="${model.cur}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">之前金额:</td>	
			<td><c:out value="${model.beforeBal}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">当前余额:</td>	
			<td><c:out value="${model.curBal}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">txnCode:</td>	
			<td><c:out value="${model.txnCode}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">修改金额:</td>	
			<td><c:out value="${model.modifyBal}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">预留字段1:</td>	
			<td><c:out value="${model.filler1}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">预留字段2:</td>	
			<td><c:out value="${model.filler2}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">预留字段3:</td>	
			<td><c:out value="${model.filler3}"/></td>
		</tr>
	</table>
	</body>
</html>