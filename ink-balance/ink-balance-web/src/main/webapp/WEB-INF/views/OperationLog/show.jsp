<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>信息</title>
<%@ include file="/gen-commons/taglibs.jsp"%>
</head>
<body>
	<div class="title01">
		<h2>详情</h2>
	</div>
	<table cellpadding="0" cellspacing="0" class="table00"
		style="width: 100%">
		<tr>
			<td class="txtr" style="width: 188px">操作类型（1：新增、2：修改、3：删除）:</td>
			<td><c:out value="${model.type}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">操作表名称:</td>
			<td><c:out value="${model.tableName}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">操作表主键:</td>
			<td><c:out value="${model.tableId}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">操作表字段:</td>
			<td><c:out value="${model.tableColumn}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">原始内容:</td>
			<td><c:out value="${model.oldContent}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">更新内容:</td>
			<td><c:out value="${model.newContent}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">创建时间:</td>
			<td><c:out value="${model.createTimeString}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">更新时间:</td>
			<td><c:out value="${model.updateTimeString}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">操作人:</td>
			<td><c:out value="${model.operator}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">删除标志:</td>
			<td><c:out value="${model.delFlag}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">版本号:</td>
			<td><c:out value="${model.version}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">备注:</td>
			<td><c:out value="${model.remark}" /></td>
		</tr>
	</table>
</body>
</html>