<%@ page import="com.ink.monitor.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>业务模块新增</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
	<script type="text/javascript" src="${yk}/static/common/maintain.js"></script>
</head>
<body>
<div class="title01">
	<h2>新增业务模块</h2>
</div>
	<form id="form1" action="${yk}/monitor/dict/info/save.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<input type="hidden" id="id" name="id" value="<c:out value="${model.id}"/>"/>
			<tr>
				<td class="txtr" style="width: 188px">
					平台系统<label style="color: red">*</label>：
				</td>
				<td>
					<select id="systermCode" name="systermCode" class="select02">

					</select>
				</td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">
					功能模块<label style="color: red">*</label>：
				</td>
				<td>
					<select id="moduleCode" name="moduleCode" class="select02"></select>
				</td>
			</tr>
			<tr>
				<td class="txtr" style="width: 188px">
					业务模块名<label style="color: red">*</label>：
				</td>	
				<td>
				<input id="name" name="name" type="text" class="input04"  required="true" />
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					业务模块码<label style="color: red">*</label>：
				</td>	
				<td>
				<input id="code" name="code" type="text" class="input04"  required="true" />

				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					状态<label style="color: red">*</label>：
				</td>	
				<td>
					<yk:constantConvert htmlTag="select" dataTag="status" name="status" paramValue="0" sysTag="monitor"/>
				</td>
			</tr>

			<tr>	
				<td class="txtr" style="width: 188px">
					描述：
				</td>	
				<td>
					<input id="opDesc" name="opDesc" type="text" class="input04"   />
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
$(function(){
	var options = {
	         beforeSubmit:   validateForm,   // pre-submit callback 表单提交前被调用的回调函数
	         success:        callBack   // post-submit callback   表单提交成功后被调用的回调函数
	     }; 
	$('#form1').ajaxForm(options);

	var systemUrl = '${yk}/monitor/dict/source/listForAjax.do';
	executeAjax(systemUrl,"systermCode");

	//平台系统联动模块代码
	$("#systermCode").change(function(){
		var optionValue = this.value;
		if (optionValue == ""){
			$("#moduleCode").empty();
			return ;
		}

		var moduleUrl = '${yk}/monitor/dict/module/listAjaxForSystemCode.do?systemCode='+optionValue;
		executeAjax(moduleUrl,"moduleCode");

		$("#moduleCode").change(function(){
			var optionValue = this.value;
			$("#code").val(optionValue);
		});
		$("#subSave").click(function() {
			if (validateLengthFrom("systermCode", 1, "平台系统不能为空")
					&&validateLengthFrom("moduleCode", 1, "功能模块名不能为空")
					&&validateLengthFrom("name", 1, "业务模块名不能为空")
					&& validateLengthFrom("code", 1, "业务模块码不能为空")
					&& validateLengthFrom("status", 1, "请选择状态")
			) {
				$('#form1').submit();
			}
		});
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
	}else if(data == 0){
		alert("平台内功能模块码重复，请重新输入");
	}else{
		alert("保存失败！");
	}
}
</script>
</html>

