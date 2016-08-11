<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<title>Insert title here</title>
</head>
<script type="text/javascript">
  function  submit(){
        document.form1.submit();
    } 
</script>
<body onload="submit()">

<form method="post" id="form1" name="form1" action="https://demo.micmiu.com:9443/sso/logout">
    <input type="hidden" name="service" id="service" value="${loginOutService}">
</form>
</body>
</html>