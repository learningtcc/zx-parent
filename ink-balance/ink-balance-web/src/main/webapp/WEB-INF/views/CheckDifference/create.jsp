
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新增</title>
<%@ include file="/gen-commons/taglibs.jsp"%>
</head>
<body>
	<div class="title01">
		<h2>新增</h2>
	</div>
	<form id="form1" action="${yk}/CheckDifference/save.do" method="post">

		<table cellpadding="0" cellspacing="0" class="table00"
			style="width: 100%">
			<input type="hidden" id="id" name="id"
				value="<c:out value="${model.id}"/>" />


			<tr>
				<td class="txtr" style="width: 188px">支付渠道（1：）：</td>
				<td><input id="channelNo" name="channelNo" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">支付渠道商户号：</td>
				<td><input id="channelMerchantNo" name="channelMerchantNo"
					type="text" class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">差错类型(1:渠道单边，2平台单边 3、差错 )：
				</td>
				<td><input id="differenceType" name="differenceType"
					type="text" class="input04" required="true" /> <span
					class="required">*</span></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">差错来源（1：渠道 2：平台）：</td>
				<td><input id="differenceSource" name="differenceSource"
					type="text" class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">对账总表主键：</td>
				<td><input id="refMainrecordId" name="refMainrecordId"
					type="text" class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">流水号：</td>
				<td><input id="seqNo" name="seqNo" type="text" class="input04" />

				</td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">平台订单号：</td>
				<td><input id="platformOrderNo" name="platformOrderNo"
					type="text" class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">金额：</td>
				<td><input id="amount" name="amount" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">状态、交易状态 1、待支付 2、支付成功
					3、支付失败：</td>
				<td><input id="status" name="status" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">日期：</td>
				<td><input onClick="WdatePicker()" id="dateString"
					name="dateString" size="10" readonly="readonly" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">处理状态,0待处理1处理完成 2、挂起：</td>
				<td><input id="handleStatus" name="handleStatus" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">处理说明：</td>
				<td><input id="handleNotes" name="handleNotes" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">createDate：</td>
				<td><input onClick="WdatePicker()" id="createDateString"
					name="createDateString" size="10" readonly="readonly" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">updateDate：</td>
				<td><input onClick="WdatePicker()" id="updateDateString"
					name="updateDateString" size="10" readonly="readonly" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">操作者ID：</td>
				<td><input id="operatorId" name="operatorId" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">删除标志：</td>
				<td><input id="delFlag" name="delFlag" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">版本号：</td>
				<td><input id="version" name="version" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">备注：</td>
				<td><input id="remark" name="remark" type="text"
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

