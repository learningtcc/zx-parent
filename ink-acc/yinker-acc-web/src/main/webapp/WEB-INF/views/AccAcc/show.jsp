<%@ page import="com.yinker.user.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>账户信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
	<h2>账户详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">子帐户号:</td>	
			<td><c:out value="${model.sacId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">主账号:</td>	
			<td><c:out value="${model.pacId}"/></td>
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
			<td class="txtr" style="width: 188px">昨日余额:</td>	
			<td><c:out value="${model.lstBal}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">当前余额:</td>	
			<td><c:out value="${model.curBal}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">临时余额:</td>	
			<td><c:out value="${model.tmpBal}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">冻结金额:</td>	
			<td><c:out value="${model.frozenAmt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">开户日期:</td>	
			<td><fmt:formatDate value="${entry.openDate}" pattern="yyyy-mm-dd HH:mm:ss"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">销户日期:</td>	
			<td><fmt:formatDate value="${entry.closeDate}" pattern="yyyy-mm-dd HH:mm:ss"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">状态 :</td>	
			<td>
				<c:choose>
					<c:when test="${model.status == 1 }">启用</c:when>
					<c:when test="${model.status == 2 }">停用</c:when>
					<c:when test="${model.status == 9 }">注销</c:when>
					<c:otherwise>未识别</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">删除标识 :</td>	
			<td>
				<c:choose>
					<c:when test="${model.delFlag == 0}">正常</c:when>
					<c:when test="${model.delFlag == 1}">删除</c:when>
					<c:otherwise>未识别</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">版本:</td>	
			<td><c:out value="${model.version}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">创建时间:</td>	
			<td><fmt:formatDate value="${model.createTime}" pattern="yyyy-mm-dd HH:mm:ss"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">最后修改时间:</td>	
			<td><fmt:formatDate value="${model.lastUpdateTime}" pattern="yyyy-mm-dd HH:mm:ss"/></td>
		</tr>
	</table>
	</body>
</html>