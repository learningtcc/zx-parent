
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
			<td class="txtr" style="width: 188px">银行名称:</td>	
			<td><c:out value="${model.bankName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">银行简码:</td>	
			<td><c:out value="${model.bankSimpleCode}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">机构代码:</td>	
			<td><c:out value="${model.bankOrg}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">发卡行标识:</td>	
			<td><c:out value="${model.cardBin}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px"> 发卡行标识长度:</td>	
			<td><c:out value="${model.cardBinLen}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">卡类型:</td>	
			<td>
			<yk:constantConvert htmlTag="text" dataTag="" data="${cardbinData}" paramValue="${model.cardCategory}" emptyTip=" " codeTag="code" nameTag="value"/>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">修改时间:</td>	
			<td><c:out value="${model.modifyTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">创建时间:</td>	
			<td><c:out value="${model.createTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">卡名:</td>	
			<td><c:out value="${model.cardName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">卡号长度:</td>	
			<td><c:out value="${model.cardNoLen}"/></td>
		</tr>
	</table>
	</body>
</html>