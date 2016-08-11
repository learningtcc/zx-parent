
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
	<form id="form1" action="${yk}/ChannelData/save.do" method="post">

		<table cellpadding="0" cellspacing="0" class="table00"
			style="width: 100%">
			<input type="hidden" id="id" name="id"
				value="<c:out value="${model.id}"/>" />


			<tr>
				<td class="txtr" style="width: 188px">商户编号：</td>
				<td><input id="merchantNo" name="merchantNo" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">支付渠道（1：）：</td>
				<td><input id="channelNo" name="channelNo" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">平台订单号：</td>
				<td><input id="platformOrderNo" name="platformOrderNo"
					type="text" class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">交易流水号：</td>
				<td><input id="transNo" name="transNo" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">订单金额：</td>
				<td><input id="amt" name="amt" type="text" class="input04" />

				</td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">实收金额：</td>
				<td><input id="receivedAmt" name="receivedAmt" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">交易时间：</td>
				<td><input onClick="WdatePicker()" id="transTimeString"
					name="transTimeString" size="10" readonly="readonly" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">交易状态 1、待支付 2、支付成功 3、支付失败：
				</td>
				<td><input id="transStatus" name="transStatus" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">
					支付方式（1：代收、2：代付、3：网银、4：快捷）：</td>
				<td><input id="payMethod" name="payMethod" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">对账状态 0：初始 1：未匹配 2：已匹配
					3、差错：</td>
				<td><input id="checkStatus" name="checkStatus" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">驻留标识(0:非驻留，1：驻留)：</td>
				<td><input id="resideFlag" name="resideFlag" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">驻留截止日：</td>
				<td><input onClick="WdatePicker()" id="resideToDateString"
					name="resideToDateString" size="10" readonly="readonly" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">创建时间：</td>
				<td><input onClick="WdatePicker()" id="createTimeString"
					name="createTimeString" size="10" readonly="readonly" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">更新时间：</td>
				<td><input onClick="WdatePicker()" id="updateTimeString"
					name="updateTimeString" size="10" readonly="readonly" /></td>
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

