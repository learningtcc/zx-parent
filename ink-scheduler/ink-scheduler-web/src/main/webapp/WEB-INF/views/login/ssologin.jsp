<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'success.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
    function  submit(){
        document.form1.submit();
    }

</script>
  </head>
  
  <body onload="submit()">

  <%--<body >--%>
     <br>
     <form method="post" id="form1" name="form1" action="https://sso.yinker.com:9443/sso/login">
         <input type="hidden" name="service" id="service" value="${service}">
         <input type="hidden" name="username" id="username" value="${username}">
         <%--<input type="hidden" name="lt" id="lt" value="${loginTicket}">--%>
     </form>
  </body>
</html>
