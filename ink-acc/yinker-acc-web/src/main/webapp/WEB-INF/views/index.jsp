<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="Cache-Control" content="no-cache" />
	<title>银客支付系统账户管理</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
	<script type="text/javascript" src="${yk}/gen-commons/md5.js"></script>

	<script type="text/javascript">
		if (top.location != self.location){
			top.location=self.location;
		}

		function formValidate(){

			if($('#username').val()==''){
				alert('请输入用户名!');
				return ;
			}

			if($('#password').val()==''){
				alert('请输入密码!');
				return ;
			}

			var username = $('#username').val();
			var password = $('#password').val();

			$.ajax( {
				type : 'GET',
				data:{username: username, password:password},
				contentType : 'application/json',
				url : "${yk}/login/checkUser.do",
				dataType : 'json',
				success : function(data) {
					if(data.result == "error"){
						alert("用户名或密码错误！")
					}else{
						$('#password').val(password);
						$('#loginForm').submit();
					}
				},
				error : function() {
					alert("系统出错，请联系管理员")
				}
			})
		}
		function keyLogin(){
			if (event.keyCode==13)  //回车键的键值为13
				formValidate();
		}

	</script>
</head>

<body style="background-color: #2d70af" onkeydown="keyLogin()">
<form action="<c:url value="/login/ssologin.do"/>" id="loginForm"  method="post">
	<div class="divbody">
		<div class="loginArea">
			<div class="loginPic00">
				<img alt="" height="70" src="${yk}/gen-commons/images/common/xu-loginPic002.png" width="372" /></div>
			<div class="loginbox">
				<div class="bd">
					<h2>登录银客支付系统账户管理平台</h2>
					<div class="field">
						<label for="username"></label><input id="username" class="login-text" maxlength="32" name="username" tabindex="1" type="text" />
					</div>
					<div class="field">
						<label for="" class="login-text2"></label><input id="password" class="login-text" maxlength="32" name="password" tabindex="1" type="password" autocomplete="off" /></div>

					<div class="submit">
						<input class="S-Submit" name="Submit1" type="button" value="" onclick="formValidate()"/></div>
				</div>
			</div>
			<!--
			<div class="login-bg">
				<img alt="" height="252" src="" width="480" /></div>
		</div>-->
		<div class="loginFooter">Copyright ©银客网</div>
	</div>
</form>
</body>

</html>
