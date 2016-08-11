
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">通道名称:</td>	
			<td><c:out value="${model.asileName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">通道id:</td>	
			<td><c:out value="${model.asileCode}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">银行名称:</td>	
			<td><c:out value="${model.bankName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">银行id:</td>	
			<td><c:out value="${model.bankCode}"/></td>
		</tr>
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">通道产品编码:</td>	 -->
<%-- 			<td><c:out value="${model.asileProductCode}"/></td> --%>
<!-- 		</tr> -->
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">通道产品名称:</td>	 -->
<%-- 			<td><c:out value="${model.asileProductName}"/></td> --%>
<!-- 		</tr> -->
		<tr>	
			<td class="txtr" style="width: 188px">备注:</td>	
			<td><c:out value="${model.remark}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">创建时间:</td>	
			<td><c:out value="${model.createTimeString}"/></td>
		</tr>
		<%-- <tr>	
			<td class="txtr" style="width: 188px">createrId:</td>	
			<td><c:out value="${model.createrId}"/></td>
		</tr> --%>
		<tr>	
			<td class="txtr" style="width: 188px">创建人:</td>	
			<td><c:out value="${model.createrName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">修改时间:</td>	
			<td><c:out value="${model.updateTimeString}"/></td>
		</tr>
		<%-- <tr>	
			<td class="txtr" style="width: 188px">updaterId:</td>	
			<td><c:out value="${model.updaterId}"/></td>
		</tr> --%>
		<tr>	
			<td class="txtr" style="width: 188px">修改人:</td>	
			<td><c:out value="${model.updaterName}"/></td>
		</tr>
		<%-- <tr>	
			<td class="txtr" style="width: 188px">是否删除:</td>	
			<td><c:out value="${model.isDelete}"/></td>
		</tr> --%>
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">版本号:</td>	 -->
<%-- 			<td><c:out value="${model.version}"/></td> --%>
<!-- 		</tr> -->
		<%-- <tr>	
			<td class="txtr" style="width: 188px">isActive:</td>	
			<td><c:out value="${model.isActive}"/></td>
		</tr> --%>
		<tr>	
			<td class="txtr" style="width: 188px">不可用开始时间:</td>	
			<td><c:out value="${model.inactivaStartTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">不可用结束时间:</td>	
			<td><c:out value="${model.inactivaEndTimeString}"/></td>
		</tr>
	</table>
	</body>
</html>