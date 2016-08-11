<%@ page import="com.yinker.user.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>交易代码信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
	<h2>交易详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">交易代码:</td>	
			<td><c:out value="${model.txnCode}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">交易名称:</td>	
			<td><c:out value="${model.txnName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">交易性质:</td>	
			<td>
				<c:choose>
					<c:when test="${model.txnNature eq '1'}">充值</c:when>
					<c:when test="${model.txnNature eq '2'}">消费</c:when>
					<c:when test="${model.txnNature eq '3'}">资金转入</c:when>
					<c:when test="${model.txnNature eq '4'}">资金转出</c:when>
					<c:when test="${model.txnNature eq '5'}">汇入</c:when>
					<c:when test="${model.txnNature eq '6'}">汇出</c:when>
					<c:when test="${model.txnNature eq '7'}">手续费</c:when>
					<c:when test="${model.txnNature eq '8'}">取现</c:when>
					<c:when test="${model.txnNature eq '9'}">其他</c:when>
					<c:otherwise>未定义</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">运行级别 :</td>	
			<td><c:out value="${model.runLv}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">交易金额方向 :</td>	
			<td><c:out value="${model.dir}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">冲正标识:</td>	
			<td><c:out value="${model.revAllowFlg}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">手续费收取标识:</td>	
			<td><c:out value="${model.feeFlg}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">最小交易金额 不含手续费:</td>	
			<td><c:out value="${model.minAmt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">最大交易金额 不含手续费:</td>	
			<td><c:out value="${model.maxAmt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">允许的交易渠道:</td>	
			<td><c:out value="${model.channel}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">默认子帐户顺序 :</td>	
			<td><c:out value="${model.accOrder}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">允许的账户状态,:</td>	
			<td><c:out value="${model.accStatus}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">状态:</td>	
			<td>
				<c:choose>
					<c:when test="${model.status eq '1'}">启用</c:when>
					<c:when test="${model.status eq '2'}">停用</c:when>
					<c:when test="${model.status eq '3'}">注销</c:when>
					<c:otherwise>未定义</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">版本:</td>	
			<td><c:out value="${model.version}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">创建时间:</td>	
			<td><c:out value="${model.createTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">最后修改时间:</td>	
			<td><c:out value="${model.lastUpdateTimeString}"/></td>
		</tr>
	</table>
	</body>
</html>