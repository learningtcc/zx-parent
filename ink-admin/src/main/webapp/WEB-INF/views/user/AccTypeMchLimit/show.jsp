
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>账户控制信息信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
	<h2>账户控制信息详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">子帐户种类:</td>	
			<td><yk:code2name columnName="sac_name" tableName="acc_sac_type" where=" sac_type=? " paramValue="${model.sacType}"></yk:code2name></div></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">商户号:</td>	
			<td>${model.mchId }</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">商户名称:</td>	
			<td><yk:code2name columnName="mch_name" tableName="acc_mch" where=" mch_id=? " paramValue="${model.mchId}"></yk:code2name></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">日提现金额上限:</td>	
			<td><c:out value="${model.dayCashlmtAmt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">日提现笔数上限:</td>	
			<td><c:out value="${model.dayCashlmtCnt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">状态:</td>	
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
			<td class="txtr" style="width: 188px">version:</td>	
			<td><c:out value="${model.version}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">最后修改时间:</td>	
			<td><c:out value="${model.lastUpdateTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">创建时间:</td>	
			<td><c:out value="${model.createTimeString}"/></td>
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
	</table>
	</body>
</html>