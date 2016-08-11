
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>编辑</title>
<%@ include file="/gen-commons/taglibs.jsp"%>
</head>
<body>
	<div class="title01">
		<h2>修改</h2>
	</div>
	<form id="form1" action="${yk}/CheckBalance/update.do" method="post">

		<table cellpadding="0" cellspacing="0" class="table00"
			style="width: 100%">
			<input type="hidden" id="id" name="id"
				value="<c:out value="${model.id}"/>" />


			<tr>
				<td class="txtr" style="width: 188px">关联主键（平台或渠道数据表主键）:</td>
				<td><input id="refId" name="refId"
					value="<c:out value="${model.refId}"/>" type="text" class="input04" />

				</td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">调账对象（1：渠道、2：平台）:</td>
				<td><input id="balanceSource" name="balanceSource"
					value="<c:out value="${model.balanceSource}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">调账方向（1：正、2：负）:</td>
				<td><input id="balanceDirection" name="balanceDirection"
					value="<c:out value="${model.balanceDirection}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">调账金额:</td>
				<td><input id="balanceAmt" name="balanceAmt"
					value="<c:out value="${model.balanceAmt}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">渠道编号:</td>
				<td><input id="channelNo" name="channelNo"
					value="<c:out value="${model.channelNo}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">平台订单号:</td>
				<td><input id="platformOrderNo" name="platformOrderNo"
					value="<c:out value="${model.platformOrderNo}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">交易流水号:</td>
				<td><input id="transNo" name="transNo"
					value="<c:out value="${model.transNo}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">调账状态（1、调账成功 2、调账失败）:</td>
				<td><input id="balanceStatus" name="balanceStatus"
					value="<c:out value="${model.balanceStatus}"/>" type="text"
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
				<td class="txtr" style="width: 188px">调账说明:</td>
				<td><input id="balanceNote" name="balanceNote"
					value="<c:out value="${model.balanceNote}"/>" type="text"
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