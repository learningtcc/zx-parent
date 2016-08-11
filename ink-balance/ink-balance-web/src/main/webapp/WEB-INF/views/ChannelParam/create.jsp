
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
	<form id="form1" action="${yk}/ChannelParam/save.do" method="post">

		<table cellpadding="0" cellspacing="0" class="table00"
			style="width: 100%">
			<input type="hidden" id="id" name="id"
				value="<c:out value="${model.id}"/>" />


			<tr>
				<td class="txtr" style="width: 188px">渠道名称：</td>
				<td><input id="name" name="name" type="text" class="input04" />

				</td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">渠道编号：</td>
				<td><input id="no" name="no" type="text" class="input04" />

				</td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">支付渠道商户号：</td>
				<td><input id="channelMerchantNo" name="channelMerchantNo"
					type="text" class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">渠道类型：</td>
				<td><input id="type" name="type" type="text" class="input04" />

				</td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">日切开始时间：</td>
				<td><input
					onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"
					id="cutDayStart" name="cutDayStartString" readonly="readonly" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">日切结束时间：</td>
				<td><input
					onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"
					id="cutDayEnd" name="cutDayEndString" readonly="readonly" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">文件定位：</td>
				<td><input id="fileAddress" name="fileAddress" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">文件获取方式：</td>
				<td><input id="fileGetModel" name="fileGetModel" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">
					驻留天数(对于未匹配的记录，继续参与对账的天数)：</td>
				<td><input id="resideDays" name="resideDays" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">备注：</td>
				<td><input id="remark" name="remark" type="text"
					class="input04" /></td>
			</tr>



			<tr>
				<td class="txtr" style="width: 188px">版本号：</td>
				<td><input id="version" name="version" type="text"
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
<script src="${yk}/gen-commons/jquery.validate.js"></script>
<script type="text/javascript">
$(function(){
    $("#form1").validate({
        rules:{
        	name:{
        		required:true
            },
            no:{
                required:true
            },
            channelMerchantNo:{
            	required:true
            },
            type:{
            	required:true,
            	number:true 
            },     
            cutDayStartString:{
                required:true 
            },
            cutDayEndString:{
                required:true
            }
        },
        messages:{
        	name:{
        		required:'<span style="color: red;">请输入渠道名称</span>'
            },
            no:{
                required:'<span style="color: red;">请输入渠道编号</span>'
            },
            channelMerchantNo:{
            	required:'<span style="color: red;">请输入渠道商户号</span>'
            },
            type:{
            	 required:'<span style="color: red;">请输入渠道类型</span>',
                 number:'<span style="color: red;">请输入正确的渠道类型</span>' 
            },
            cutDayStartString:{
                required:'<span style="color: red;">请输入日切开始时间</span>'
            },
            cutDayEndString:{
                required:'<span style="color: red;">请输入日切结束时间</span>'
            }
        }
    });    

});
$(function () {
    var options = {
        beforeSubmit: validateForm,   // pre-submit callback 表单提交前被调用的回调函数
        success: callBack   // post-submit callback   表单提交成功后被调用的回调函数
    };
    $('#form1').ajaxForm(options);
});
window.onbeforeunload = function () {
    window.opener.document.queryForm.submit();
}
function validateForm() {
	if($("#form1").valid()){
		return true;
	 }
	 return false;
}
function callBack(data){
	if(data=="SUCCESS")	{
		alert("保存成功！");
		window.close();
	}else{
		alert("保存失败！");
	}
}
</script>
</html>

