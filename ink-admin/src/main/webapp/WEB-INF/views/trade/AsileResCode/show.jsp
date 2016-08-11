
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
			<td class="txtr" style="width: 188px">通道名称:</td>	
			<td><c:out value="${model.asileName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">通道id:</td>	
			<td><c:out value="${model.asileCode}"/></td>
		</tr>
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">通道支付方式:</td>	 -->
<%-- 			<td><c:out value="${model.asilePayType}"/></td> --%>
<!-- 		</tr> -->
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">交易类型:</td>	 -->
<%-- 			<td><c:out value="${model.transType}"/></td> --%>
<!-- 		</tr> -->
		<tr>	
			<td class="txtr" style="width: 188px">通道响应码:</td>	
			<td><c:out value="${model.asileResCode}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">通道响应信息:</td>	
			<td><c:out value="${model.asileResMsg}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">平台响应码:</td>	
			<td><c:out value="${model.resCode}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">平台响应信息:</td>	
			<td><c:out value="${model.resMsg}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">备注:</td>	
			<td><c:out value="${model.remark}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">创建时间:</td>	
			<td><c:out value="${model.createTimeString}"/></td>
		</tr>
		<%-- <tr>	
			<td class="txtr" style="width: 188px">创建人id:</td>	
			<td><c:out value="${model.createrId}"/></td>
		</tr> --%>
		<tr>	
			<td class="txtr" style="width: 188px">创建人:</td>	
			<td><c:out value="${model.createrName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">更新时间:</td>	
			<td><c:out value="${model.updateTimeString}"/></td>
		</tr>
		<%-- <tr>	
			<td class="txtr" style="width: 188px">updaterId:</td>	
			<td><c:out value="${model.updaterId}"/></td>
		</tr> --%>
		<tr>	
			<td class="txtr" style="width: 188px">updaterName:</td>	
			<td><c:out value="${model.updaterName}"/></td>
		</tr>
		<%-- <tr>	
			<td class="txtr" style="width: 188px">是否删除:</td>	
			<td><c:out value="${model.isDelete}"/></td>
		</tr> --%>
	</table>
	</body>
</html>