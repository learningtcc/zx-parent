<%@ page import="com.ink.msgcenter.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>商户新增</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
	<script type="text/javascript" src="${yk}/static/common/maintain.js"></script>
</head>
<body>
<div class="title01">
	<h2>新增商户</h2>
</div>
	<form id="form1" action="${yk}/msgcenter/merchant/merchantInfo/save.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<input type="hidden" id="id" name="id" value="<c:out value="${model.id}"/>"/>
			<input id="status" name="status" type="hidden"  value="0"  />
			<input id="smsNotify" name="smsNotify" type="hidden"  value=""  />
			<input id="emailNotify" name="emailNotify" type="hidden"  value=""  />
			<tr>
				<td class="txtr" style="width: 188px">
					商户名称<label style="color: red">*</label>：
				</td>	
				<td>
				<input id="name" name="name" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					商户编号<label style="color: red">*</label>：
				</td>	
				<td>
				<input id="sn" name="sn" type="text" class="input04"   />
				
				</td>
			</tr>

			<tr>	
				<td class="txtr" style="width: 188px">
					通道类型<label style="color: red">*</label>：
				</td>	
				<td>
					<yk:constantConvert htmlTag="checkBox" dataTag="channelType" name="channelType" sysTag="msgcenter"/>
				</td>
			</tr>
			<div id="smsDiv" name="smsDiv">
				<tr name="smsTr1" >
					<td class="txtr" style="width: 188px">短信通知方式：</td>
					<td>
						<yk:constantConvert htmlTag="select" dataTag="notifyType" name="smsNotifyType" sysTag="msgcenter"/>
					</td>
				</tr>
				<tr name="smsTr2"  >
					<td class="txtr" style="width: 188px">短信状态报告url：</td>
					<td>
						<input id="smsReportUrl" name="smsReportUrl" type="text" class="input04"   />
					</td>
				</tr>
				<tr name="smsTr3" >
					<td class="txtr" style="width: 188px">短信上行通知url：</td>
					<td>
						<input id="smsUpUrl" name="smsUpUrl" type="text" class="input04"   />
					</td>
				</tr>
			</div>
			<div id="emailDiv" name="emailDiv" >
				<tr name="emailTr1"  >
					<td class="txtr" style="width: 188px">邮件通知方式：</td>
					<td>
						<yk:constantConvert htmlTag="select" dataTag="notifyType" name="emailNotifyType" sysTag="msgcenter"/>
					</td>
				</tr>
				<tr name="emailTr2" >
					<td class="txtr" style="width: 188px">邮件发送通知url：</td>
					<td>
						<input id="emailReportUrl" name="emailReportUrl" type="text" class="input04"   />
					</td>
				</tr>
			</div>
			<tr>
				<td class="txtr" style="width: 188px">
					商户描述：
				</td>
				<td>
					<textarea rows="3" cols="40" name="remark" id="remark"></textarea>
				</td>
			</tr>

			<tr>	
				<td class="txtc" colspan="2">
					<input type="button" class="btn01" id="subSave" value="提交" />
					<input type="button" class="btn01"  value="取消" onclick="window.close();"/>
					<input type="reset" class="btn01"  value="重置"/>
				</td>	
			</tr>
		</table>
	</form>
	
</body>
<script type="text/javascript">
	var isEmail = false;
	var isSms = false;
$(function(){
	var options = {
	         beforeSubmit:   validateForm,   // pre-submit callback 表单提交前被调用的回调函数
	         success:        callBack   // post-submit callback   表单提交成功后被调用的回调函数
	     }; 
	$('#form1').ajaxForm(options);

	$("tr[name^='smsTr']").hide();
	$("tr[name*='emailTr']").hide();

	$("form #channelType").click(function(){
		if(this.value == '1'){
			isSms = this.checked;
			$("tr[name^='smsTr']").toggle();
		}else{
			isEmail = this.checked;
			$("tr[name*='emailTr']").toggle();
		}
	});

	$("#subSave").click(function(){
		var result = false;
		if (validateLengthFrom("name", 1, "通道名称不能为空")
				&&validateLengthFrom("sn", 1, "请选择通道类型")) {

			if(!(isSms || isEmail)){
				alert("请选择通道类型");
			}
			if(isSms){
				var smsNotifyType = $("#smsNotifyType").val();
				if(smsNotifyType.length > 0){
					if(validateLengthFrom("smsReportUrl", 1, "短信状态报告url不能为空")){
						result = true;
						var smsNotify = "{\"notifyType\":\"" + smsNotifyType +"\"," +
								"\"httpInfo\":{\"reportUrl\":\"" + $("#smsReportUrl").val() + "\"," +
								" \"upUrl\":\"" + $("#smsUpUrl").val() + "\"}}";
						$("#smsNotify").val(smsNotify);
					}else{
						result = false;
					}
				}else{
					result = true;
				}
			}
			if(isEmail){
				var emailNotifyType = $("#emailNotifyType").val();
				if(emailNotifyType.length > 0){
					if(validateLengthFrom("emailReportUrl", 1, "邮件发送通知url不能为空")){
						result = true;
						var emailNotify = "{\"notifyType\":\"" + emailNotifyType +"\"," +
								"\"httpInfo\":{\"reportUrl\":\"" + $("#emailReportUrl").val() + "\"}}";
						$("#emailNotify").val(emailNotify);
					}else{
						result = false;
					}
				}else{
					result = true;
				}
			}
		}
		if(result){
			$("#form1").submit();
		}
	});

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
	}else if(data==0)	{
		alert("商户编号重复，请重新输入！");
	}else{
		alert("保存失败！");
	}
}
</script>
</html>

