<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>修改密码</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
	<script type="text/javascript" src="${yk}/gen-commons/md5.js"></script>
</head>
<body>
<div class="title01">
	<h2>修改密码</h2>
</div>
	<form id="form1" action="${yk}/monitor/user/updatePassword.do" method="post">
		<input id="oldPassword" name="oldPassword" value="" type="hidden" class="input04"   />
		<input id="newPassword" name="newPassword" value="" type="hidden" class="input04"   />
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">

			<tr>	
				<td class="txtr" style="width: 188px">
					旧密码:
				</td>	
				<td>
				<input id="oldPassword1" name="oldPassword1" value="" type="password" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					新密码:
				</td>	
				<td>
				<input id="newPassword1" name="newPassword1" value="" type="password" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					再次输入新密码:
				</td>	
				<td>
				<input id="reNewPassword" name="reNewPassword" value="" type="password" class="input04"   />
				
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
	$("#subSave").click(function(){
		if(validateForm()){
			$('#form1').submit();
		}
	});
	$('#form1').ajaxForm(options);
});

window.onbeforeunload=function(){
	window.opener.document.queryForm.submit();
}

function validateForm(){
	var oldPassword = $("#oldPassword1").val();
	var newPassword = $("#newPassword1").val();
	var reNewPassword = $("#reNewPassword").val();
	if(oldPassword ==''){
		alert("旧密码不能为空");
		return false;
	}
	if(newPassword ==''){
		alert("新密码不能为空");
		return false;
	}
	if(reNewPassword ==''){
		alert("请再次输入新密码");
		return false;
	}

	if(reNewPassword != newPassword){
		alert("两次输入新密码不同，请检查");
		return false;
	}
	if(oldPassword == newPassword){
		alert("新密码与旧密码不能相同，请检查");
		return false;
	}
	oldPassword = faultylabs.MD5(faultylabs.MD5(oldPassword));
	newPassword = faultylabs.MD5(faultylabs.MD5(newPassword));

	$('#oldPassword').val(oldPassword);
	$('#newPassword').val(newPassword);

	return true;
}
function callBack(data){
	if(data==1)	{
		alert("更新成功！");
		window.close();
	}else if(data == 0){
		alert("旧密码输入错误！");
	}else{
		alert("更新失败！");
	}
}
</script>
</html>