
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
	<h2>详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">银行卡号:</td>	
			<td><c:out value="${model.cardNo}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">银行卡类型:</td>	
			<td><c:out value="${model.cardType}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">手机号:</td>	
			<td><c:out value="${model.phoneNo}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">证件类型:</td>	
			<td><c:out value="${model.idType}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">身份证号:</td>	
			<td><c:out value="${model.idNo}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">姓名:</td>	
			<td><c:out value="${model.userName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">所属银行简码:</td>	
			<td><c:out value="${model.bankShort}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">所属银行:</td>	
			<td><c:out value="${model.bankName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">状态:</td>	
			<td><c:out value="${model.status}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">版本号:</td>	
			<td><c:out value="${model.version}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">删除标识:</td>	
			<td><c:out value="${model.isDelete}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">备注:</td>	
			<td><c:out value="${model.remark}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">商户号:</td>	
			<td><c:out value="${model.mchId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">最后修改时间:</td>	
			<td><c:out value="${model.lastupdateTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">请求流水号:</td>	
			<td><c:out value="${model.reqId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">客户号:</td>	
			<td><c:out value="${model.userId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">createTime:</td>	
			<td><c:out value="${model.createTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">支付类型:</td>	
			<td><c:out value="${model.payType}"/></td>
		</tr>
	</table>
	</body>
</html>