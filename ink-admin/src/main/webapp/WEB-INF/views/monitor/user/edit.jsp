<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>用户编辑</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
	<h2>修改用户</h2>
</div>
	<form id="form1" action="${yk}/monitor/user/update.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<input type="hidden" id="userId" name="userId" value="<c:out value="${model.userId}"/>"/>
			<input id="status" name="status" value="<c:out value="${model.status}"/>" type="hidden" class="input04"   />
			<input id="createTimeString" value="<c:out value="${model.createTimeString}"/>" name="createTimeString" type="hidden"/>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					账号:
				</td>	
				<td>
				<input id="userName" name="userName" value="<c:out value="${model.userName}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					姓名:
				</td>	
				<td>
				<input id="fullName" name="fullName" value="<c:out value="${model.fullName}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					手机号:
				</td>	
				<td>
				<input id="mobile" name="mobile" value="<c:out value="${model.mobile}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					邮箱:
				</td>	
				<td>
				<input id="email" name="email" value="<c:out value="${model.email}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					告警类型：
				</td>	
				<td>
					<yk:constantConvert htmlTag="checkBox" dataTag="warnType" name="warnType" paramValue="${model.warnType}" paramSplit="," sysTag="monitor"/>*
				</td>
			</tr>
			
			<tr>	
				<td class="txtc" colspan="2">
					<input type="button" class="btn01"  value="提交" onclick="$('#form1').submit();"/>
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
	return true;
}
function callBack(data){
	if(data==1)	{
		alert("更新成功！");
		window.close();
	}else if(data == '0-1'){
		alert("账号已存在！");
	}else{
		alert("更新失败！");
	}
}
</script>
</html>