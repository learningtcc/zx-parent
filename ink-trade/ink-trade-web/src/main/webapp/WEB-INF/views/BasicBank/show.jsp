
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
		<%-- <tr>	
			<td class="txtr" style="width: 188px">isDelete:</td>	
			<td><c:out value="${model.isDelete}"/></td>
		</tr> --%>
		<tr>	
			<td class="txtr" style="width: 188px">银行名称:</td>	
			<td><c:out value="${model.bankName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">银行简码:</td>	
			<td><c:out value="${model.bankShortName}"/></td>
		</tr>
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">版本号:</td>	 -->
<%-- 			<td><c:out value="${model.version}"/></td> --%>
<!-- 		</tr> -->
		
		<%-- <tr>	
			<td class="txtr" style="width: 188px">createTime:</td>	
			<td><c:out value="${model.createTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">createrId:</td>	
			<td><c:out value="${model.createrId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">createrName:</td>	
			<td><c:out value="${model.createrName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">updateTime:</td>	
			<td><c:out value="${model.updateTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">updaterId:</td>	
			<td><c:out value="${model.updaterId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">updaterName:</td>	
			<td><c:out value="${model.updaterName}"/></td>
		</tr> --%>
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">银行城市简码:</td>	 -->
<%-- 			<td><c:out value="${model.bankCityCode}"/></td> --%>
<!-- 		</tr> -->
		<%-- <tr>	
			<td class="txtr" style="width: 188px">bankCcpcCode:</td>	
			<td><c:out value="${model.bankCcpcCode}"/></td>
		</tr> --%>
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">银行级别:</td>	 -->
<%-- 			<td><c:out value="${model.bankLevel}"/></td> --%>
<!-- 		</tr> -->
<!-- 		<tr>	 -->
			<td class="txtr" style="width: 188px">备注:</td>	
			<td><c:out value="${model.remark}"/></td>
		</tr>
		<%-- <tr>	
			<td class="txtr" style="width: 188px">subBankName:</td>	
			<td><c:out value="${model.subBankName}"/></td>
		</tr> --%>
	</table>
	</body>
</html>