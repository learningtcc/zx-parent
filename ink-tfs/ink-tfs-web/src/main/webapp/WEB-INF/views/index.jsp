<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/gen-commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="Cache-Control" content="no-cache" />
	<title>银客网文件管理系统</title>
	<!--
<link href="${ctx}/css/common/joyManagerSystem.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/scripts/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${ctx}/scripts/md5.js"></script>
-->

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

			var userName = $('#username').val();
			//var password = hex_md5($('#password').val()).toUpperCase();
			var password = $('#password').val();

			$.post("${yk}/login/checkUser.do",{userName: userName, password:password},function(data){
				if(data.result == "error"){
					alert("用户名或密码错误！")
				}else{
					$('#password').val(password);
					$('#loginForm').submit();
				}
			});
		}
//		window.onload = function(){
//			var h = document.documentElement.clientHeight;
//			document.getElementById('divbody').style.height = h+'px';
//		}
	</script>
</head>

<body>
<form action="<c:url value="/login/ssologin.do"/>" id="loginForm"  method="post">
	<div class="divbody" id="divbody" >
		<div class="loginArea">
			<div class="loginPic00">
				<img alt="" height="70" src="${yk}/gen-commons/images/common/xu-loginPic00.png" width="400" /></div>
			<div class="loginbox">
				<div class="bd">
					<h2>登录系统管理平台</h2>
					<div class="field">
						<label for="username"></label>
						<input id="username" class="login-text" maxlength="32" name="username" tabindex="1" type="text" autocomplete="off" />
					</div>
					<div class="field">
						<label for="" class="login-text2"></label>
						<input id="password" class="login-text" maxlength="32" name="password" tabindex="1" type="password" autocomplete="off" /></div>

					<div class="submit">
						<input class="S-Submit" name="Submit1" type="button" value="" onclick="formValidate()"/></div>
				</div>
			</div>
			<!--
			<div class="login-bg">
				<img alt="" height="252" src="" width="480" /></div>
		</div>-->
		<div class="loginFooter">
			Copyright 京ICP备12023672号-5  |  京公网安备 11010502025073 号 ® 2014 YINKER.com  |  银客金融信息服务（北京）有限公司 版权所有</div>
	</div>
</form>
</body>

</html>
