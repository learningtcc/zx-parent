<%@ page import="com.yinker.tfs.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=TfsFileName.TABLE_ALIAS%>信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
	<h2><%=TfsFileName.TABLE_ALIAS%>详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">系统代码:</td>	
			<td><c:out value="${model.sourceCode}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">模块代码:</td>	
			<td><c:out value="${model.moduleCode}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">tfs文件名:</td>	
			<td><c:out value="${model.tfsName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">文件原始名:</td>	
			<td><c:out value="${model.fileName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">文件后缀:</td>	
			<td><c:out value="${model.suffix}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">文件状态 0：正常；1：隐藏:</td>	
			<td><c:out value="${model.status}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">创建时间:</td>	
			<td><c:out value="${model.createTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">更新时间:</td>	
			<td><c:out value="${model.updateTimeString}"/></td>
		</tr>
	</table>
	</body>
</html>