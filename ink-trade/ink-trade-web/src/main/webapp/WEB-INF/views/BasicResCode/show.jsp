
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
			<td class="txtr" style="width: 188px">平台响应码:</td>	
			<td><c:out value="${model.resCode}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">平台响应信息:</td>	
			<td><c:out value="${model.resMsg}"/></td>
		</tr>
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">是否客户原因:</td>	 -->
<%-- 			<td><c:out value="${model.isCustReason}"/></td> --%>
<!-- 		</tr> -->
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">是否可以重提:</td>	 -->
<%-- 			<td><c:out value="${model.isAgain}"/></td> --%>
<!-- 		</tr> -->
		
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
		<tr>	
			<td class="txtr" style="width: 188px">备注:</td>	
			<td><c:out value="${model.remark}"/></td>
		</tr>
	</table>
	</body>
</html>