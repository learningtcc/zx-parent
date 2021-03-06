
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
	<form id="form1" action="${yk}/CheckChannel/update.do" method="post">

		<table cellpadding="0" cellspacing="0" class="table00"
			style="width: 100%">
			<input type="hidden" id="id" name="id"
				value="<c:out value="${model.id}"/>" />


			<tr>
				<td class="txtr" style="width: 188px">对账日期:</td>
				<td><input onClick="WdatePicker()" id="checkDateString"
					value="<c:out value="${model.checkDateString}"/>"
					name="checkDateString" size="10" readonly="readonly" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">交易日期:</td>
				<td><input onClick="WdatePicker()" id="tradeDateString"
					value="<c:out value="${model.tradeDateString}"/>"
					name="tradeDateString" size="10" required="true"
					readonly="readonly" /> <span class="required">*</span></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">渠道编号:</td>
				<td><input id="channelNo" name="channelNo"
					value="<c:out value="${model.channelNo}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">支付渠道商户号:</td>
				<td><input id="channelMerchantNo" name="channelMerchantNo"
					value="<c:out value="${model.channelMerchantNo}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">渠道总金额:</td>
				<td><input id="channelAmount" name="channelAmount"
					value="<c:out value="${model.channelAmount}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">渠道总笔数:</td>
				<td><input id="channelCount" name="channelCount"
					value="<c:out value="${model.channelCount}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">平台总金额:</td>
				<td><input id="platformAmount" name="platformAmount"
					value="<c:out value="${model.platformAmount}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">平台总笔数:</td>
				<td><input id="platformCount" name="platformCount"
					value="<c:out value="${model.platformCount}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">平台单边笔数:</td>
				<td><input id="platformSideCount" name="platformSideCount"
					value="<c:out value="${model.platformSideCount}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">渠道单边笔数:</td>
				<td><input id="channelSideCount" name="channelSideCount"
					value="<c:out value="${model.channelSideCount}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">对账结果状态（1、匹配 2、未匹配
					3、数据异常）此值不会改变，处理状态可变:</td>
				<td><input id="checkStatus" name="checkStatus"
					value="<c:out value="${model.checkStatus}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">
					差异总笔数（包括了单边，生成后不会变，未处理差异笔数会改变）:</td>
				<td><input id="differenceCount" name="differenceCount"
					value="<c:out value="${model.differenceCount}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">渠道对账文件名称:</td>
				<td><input id="channelCheckFile" name="channelCheckFile"
					value="<c:out value="${model.channelCheckFile}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">文件来源(1:跑批、2：上传):</td>
				<td><input id="fileSource" name="fileSource"
					value="<c:out value="${model.fileSource}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">处理状态,0待处理1处理完成:</td>
				<td><input id="adjustStatus" name="adjustStatus"
					value="<c:out value="${model.adjustStatus}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">未处理差异笔数:</td>
				<td><input id="unhandleCount" name="unhandleCount"
					value="<c:out value="${model.unhandleCount}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">处理说明（变更处理状态时写）:</td>
				<td><input id="handleNotes" name="handleNotes"
					value="<c:out value="${model.handleNotes}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">备注:</td>
				<td><input id="remark" name="remark"
					value="<c:out value="${model.remark}"/>" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">创建时间:</td>
				<td><input onClick="WdatePicker()" id="createDateString"
					value="<c:out value="${model.createDateString}"/>"
					name="createDateString" size="10" readonly="readonly" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">更新时间:</td>
				<td><input onClick="WdatePicker()" id="updateDateString"
					value="<c:out value="${model.updateDateString}"/>"
					name="updateDateString" size="10" readonly="readonly" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">操作员:</td>
				<td><input id="operatorId" name="operatorId"
					value="<c:out value="${model.operatorId}"/>" type="text"
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