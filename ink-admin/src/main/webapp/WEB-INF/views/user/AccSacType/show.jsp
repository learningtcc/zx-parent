
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>子账户类型信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
	<h2>子账户类型详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">子帐户种类:</td>	
			<td><c:out value="${model.sacType}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">子帐户名称:</td>	
			<td><c:out value="${model.sacName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">币种:</td>	
			<td><c:out value="${model.cur}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">日充值金额上限:</td>	
			<td><c:out value="${model.dayVarchargelmtAmt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">日充值笔数上限:</td>	
			<td><c:out value="${model.dayVarchargelmtCnt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">日消费金额上限:</td>	
			<td><c:out value="${model.dayPaylmtAmt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">日消费笔数上限:</td>	
			<td><c:out value="${model.dayPaylmtCnt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">日转入金额上限:</td>	
			<td><c:out value="${model.dayInlmtAmt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">日转入笔数上限:</td>	
			<td><c:out value="${model.dayInlmtCnt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">日转出金额上限:</td>	
			<td><c:out value="${model.dayOutlmtAmt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">日转出笔数上限:</td>	
			<td><c:out value="${model.dayOutlmtCnt}"/></td>
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
			<td class="txtr" style="width: 188px">月充值金额上限:</td>	
			<td><c:out value="${model.monVarchargelmtAmt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">月充值笔数上限:</td>	
			<td><c:out value="${model.monVarchargelmtCnt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">月消费金额上限:</td>	
			<td><c:out value="${model.monPaylmtAmt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">月消费笔数上限:</td>	
			<td><c:out value="${model.monPaylmtCnt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">月转入金额上限:</td>	
			<td><c:out value="${model.monInlmtAmt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">月转入笔数上限:</td>	
			<td><c:out value="${model.monInlmtCnt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">月转出金额上限:</td>	
			<td><c:out value="${model.monOutlmtAmt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">月转出笔数上限:</td>	
			<td><c:out value="${model.monOutlmtCnt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">月提现金额上限:</td>	
			<td><c:out value="${model.monCashlmtAmt}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">月提现笔数上限:</td>	
			<td><c:out value="${model.monCashlmtCnt}"/></td>
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