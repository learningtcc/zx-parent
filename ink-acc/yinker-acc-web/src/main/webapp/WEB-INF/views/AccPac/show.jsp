<%@ page import="com.yinker.user.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>主账户控制信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
	<h2>主账户详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">创建人:</td>	
			<td><c:out value="${model.owner}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">创建人所属机构:</td>	
			<td><c:out value="${model.ownerGroup}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">创建时间:</td>	
			<td><c:out value="${model.createTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">最后修改时间:</td>	
			<td><c:out value="${model.lastUpdateTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">主账号:</td>	
			<td><c:out value="${model.pacId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">商户信息表主键:</td>	
			<td><c:out value="${model.accProdId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">产品编号(帐户级别):</td>	
			<td>
				<c:choose>
					<c:when test="${model.prodId eq '0001'}">未实名账户</c:when>
					<c:when test="${model.prodId eq '0002'}">已实名账户</c:when>
					<c:when test="${model.prodId eq '0003'}">VIP帐户</c:when>
					<c:when test="${model.prodId eq '0004'}">公司账户</c:when>
					<c:otherwise>未定义</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">客户类型 :</td>	
			<td>
				<c:choose>
					<c:when test="${model.custType == 0}">个人</c:when>
					<c:when test="${model.custType == 1}">单位</c:when>
					<c:otherwise>未定义</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">日收入金额累计:</td>	
			<td><c:out value="${model.dayInAmt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">日收入笔数累计:</td>	
			<td><c:out value="${model.dayInCnt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">日支出金额累计:</td>	
			<td><c:out value="${model.dayPayAmt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">日支出笔数累计:</td>	
			<td><c:out value="${model.dayPayCnt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">月收入金额累计:</td>	
			<td><c:out value="${model.monInAmt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">月收入笔数累计:</td>	
			<td><c:out value="${model.monInCnt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">月支出金额累计:</td>	
			<td><c:out value="${model.monPayAmt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">月支出笔数累计:</td>	
			<td><c:out value="${model.monPayCnt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">日免签支付金额累计:</td>	
			<td><c:out value="${model.dayNopswdAmt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">日免签支付笔数累计:</td>	
			<td><c:out value="${model.dayNopswdCnt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">日收入金额上限:</td>	
			<td><c:out value="${model.dayInlmtAmt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">日收入笔数上限:</td>	
			<td><c:out value="${model.dayInlmtCnt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">日支出金额上限:</td>	
			<td><c:out value="${model.dayPaylmtAmt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">日支出笔数上限:</td>	
			<td><c:out value="${model.dayPaylmtCnt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">月收入金额上限:</td>	
			<td><c:out value="${model.monInlmtAmt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">月收入笔数上限:</td>	
			<td><c:out value="${model.monInlmtCnt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">月支出金额上限:</td>	
			<td><c:out value="${model.monPaylmtAmt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">月支出笔数上限:</td>	
			<td><c:out value="${model.monPaylmtCnt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">日免签支付金额上限:</td>	
			<td><c:out value="${model.dayNopswdlmtAmt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">日免签支付笔数上限:</td>	
			<td><c:out value="${model.dayNopswdlmtCnt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">开户日期:</td>	
			<td><c:out value="${model.openDateString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">销户日期:</td>	
			<td><c:out value="${model.closeDateString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">状态</td>	
			<td>
				<c:choose>
					<c:when test="${model.status eq '1'}">启用</c:when>
					<c:when test="${model.status eq '2'}">停用</c:when>
					<c:when test="${model.status eq '9'}">注销</c:when>
					<c:otherwise>未定义</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">版本:</td>	
			<td><c:out value="${model.version}"/></td>
		</tr>
	</table>
	</body>
</html>