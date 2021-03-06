
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>v编辑</title>
<%@ include file="/gen-commons/taglibs.jsp"%>
</head>
<body>
	<div class="title01">
		<h2>修改</h2>
	</div>
	<form id="form1" action="${yk}/OperationLog/update.do" method="post">

		<table cellpadding="0" cellspacing="0" class="table00"
			style="width: 100%">
			<input type="hidden" id="id" name="id"
				value="<c:out value="${model.id}"/>" />


			<tr>
				<td class="txtr" style="width: 188px">操作类型（1：新增、2：修改、3：删除）:</td>
				<td><input id="type" name="type"
					value="<c:out value="${model.type}"/>" type="text" class="input04" />

				</td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">操作表名称:</td>
				<td><input id="tableName" name="tableName"
					value="<c:out value="${model.tableName}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">操作表主键:</td>
				<td><input id="tableId" name="tableId"
					value="<c:out value="${model.tableId}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">操作表字段:</td>
				<td><input id="tableColumn" name="tableColumn"
					value="<c:out value="${model.tableColumn}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">原始内容:</td>
				<td><input id="oldContent" name="oldContent"
					value="<c:out value="${model.oldContent}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">更新内容:</td>
				<td><input id="newContent" name="newContent"
					value="<c:out value="${model.newContent}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">创建时间:</td>
				<td><input onClick="WdatePicker()" id="createTimeString"
					value="<c:out value="${model.createTimeString}"/>"
					name="createTimeString" size="10" readonly="readonly" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">更新时间:</td>
				<td><input onClick="WdatePicker()" id="updateTimeString"
					value="<c:out value="${model.updateTimeString}"/>"
					name="updateTimeString" size="10" readonly="readonly" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">操作人:</td>
				<td><input id="operator" name="operator"
					value="<c:out value="${model.operator}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">删除标志:</td>
				<td><input id="delFlag" name="delFlag"
					value="<c:out value="${model.delFlag}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">版本号:</td>
				<td><input id="version" name="version"
					value="<c:out value="${model.version}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">备注:</td>
				<td><input id="remark" name="remark"
					value="<c:out value="${model.remark}"/>" type="text"
					class="input04" /></td>
			</tr>
			<tr>
				<td class="txtc" colspan="2"><input type="button" class="btn01"
					value="提交" onclick="$('#form1').submit();" /> <input type="button"
					class="btn01" value="取消" onclick="window.close();" /> <input
					type="reset" class="btn01" value="重置" /></td>
			</tr>
		</table>
	</form>

</body>
<script type="text/javascript">
$(function(){
	var options = {
	         beforeSubmit:   validateForm,   // pre-submit callback 表单提交前被调用的回调函数
	         success:        callBack   // post-submit callback   表单提交成功后被调用的回调函数
	     }; 
	$('#form1').ajaxForm(options);
});

window.onbeforeunload=function(){
	window.opener.document.queryForm.submit();
}

function validateForm(){
	return true;
}
function callBack(data){
	if(data==1)	{
		alert("保存成功！");
		window.close();
	}else{
		alert("保存失败！");
	}
}
</script>
</html>