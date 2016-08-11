<!DOCTYPE html>
 <%@ page contentType="text/html; charset=UTF-8" %>
<html>
	<head>
		<meta charset="UTF-8">
		<title> 银客权限管理平台</title>
		<link rel="stylesheet" type="text/css" href="static/css/css.css"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script src="static/js/jquery/1.8.0/jquery.min.js" type="text/javascript"></script>
		
	</head>
	
	<body>
		<form action="doLogin" id="ff" method="post" >
		             <input type="hidden" name="service" id="service" value="${service}">
		
			<div class="Index_sign" id="divbody">
				<div class="Index_sign_Bgx"><img src="static/images/Img_left.png" class="Bgx_left"/><a href="X#" class="Logo_img"><img src="static/images/Logo_img.png" /></a></div>
				<div class="loginArea">
					<div class="loginPic00"></div>
					<div class="loginbox">
						<div class="bd">
						
							<h2><img alt="" height="40" width="190" src="static/images/Img1.png" ></h2>
	 <span  style="display:inline;margin-left:120px;color:red">${errMsg }</span>
							
							<div class="field">
								<label for="username">用户名：</label>
								<input id="username" class="login-text" maxlength="32" name="userName" tabindex="1" type="text"  autocomplete="off">
							</div>
							<div class="field">
								<label for="">密码：</label>
								<input id="password" class="login-text" maxlength="32" name="password" tabindex="1" type="password" autocomplete="off"></div>
								<div class="field">
								<label for="">验证码：</label>
								<input onblur="checkcode()" type="text" style="width: 100px;" id="enCode" name="enCode" class="login-text login-yzm" maxlength="4" autocomplete="off"placeholder="请输入验证码"/>
								<img id="code" class="code" src="imagGennerate" onclick="changd(this)" title="点击更换验证码"></div>
							<div class="submit">
								<input class="S-Submit"  type="button" onclick="submit()">
							</div>
						</div>
					</div>
				<div class="loginFooter">
					京ICP备12023672号-5  |  京公网安备 11010502025073 号 © 2014 YINKER.com  |  银客金融信息服务（北京）有限公司 版权所有 </div>
			</div>

		</div></form>
		<script type="text/javascript"> 
		function changd(tag){        	 
		    tag.src="imagGennerate?td="+new Date().getTime();
		 }
		$(document).keydown(function(e) {
			if (e.which == 13){				
				 $('#ff').submit();
			}
			
			})
		
		
	</script>
	</body>
</html>
