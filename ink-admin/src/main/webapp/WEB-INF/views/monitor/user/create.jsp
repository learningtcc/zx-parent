<%@ page import="com.ink.monitor.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>用户新增</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
	<script type="text/javascript" src="${yk}/gen-commons/md5.js"></script>
</head>
<body>
<div class="title01">
	<h2>新增用户</h2>
</div>
	<form id="form1" action="${yk}/monitor/user/save.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<input type="hidden" id="userId" name="userId" value="<c:out value="${model.userId}"/>"/>
			<input type="hidden" id="password" name="password" value=""/>
			<tr>	
				<td class="txtr" style="width: 188px">
					账号：
				</td>	
				<td>
				<input id="userName" name="userName" type="text" class="input04"   />*
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					姓名：
				</td>	
				<td>
				<input id="fullName" name="fullName" type="text" class="input04"   />*
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					手机号：
				</td>	
				<td>
				<input id="mobile" name="mobile" type="text" class="input04"   />*
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					邮箱：
				</td>	
				<td>
				<input id="email" name="email" type="text" class="input04"   />*
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					告警类型：
				</td>	
				<td>
					<yk:constantConvert htmlTag="checkBox" dataTag="warnType" name="warnType" sysTag="monitor"/> *
				</td>
			</tr>
			
			<tr>	
				<td class="txtc" colspan="2">
					<input type="button" class="btn01"  id = "subSave" value="提交" />
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

	$("#subSave").click(function(){
		if(validateForm()){
			$('#form1').submit();
		}
	});
});

window.onbeforeunload=function(){
	window.opener.document.queryForm.submit();
}

function validateForm(){
	if($('#userName').val()==''){
		alert("用户账号不能为空");
		return false;
	}
	if($('#fullName').val()==''){
		alert("姓名不能为空");
		return false;
	}
	if($('#mobile').val()==''){
		alert("手机号不能为空");
		return false;
	}
	if($('#email').val()==''){
		alert("邮箱不能为空");
		return false;
	}
	$("#password").val(faultylabs.MD5(faultylabs.MD5("yk123456")));
	return true;
}
function callBack(data){
	if(data==1)	{
		alert("保存成功！");
		window.close();
	}else if(data == '0-1'){
		alert("账号已存在！");
	}else{
		alert("保存失败！");
	}
}
</script>
</html>

