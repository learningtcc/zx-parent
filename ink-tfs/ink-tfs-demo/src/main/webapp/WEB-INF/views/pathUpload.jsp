<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/gen-commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript">
    </script>
</head>
<body>

<h1>springMVC包装类上传文件</h1>
<form name="userForm" action="<c:url value="/tfs/uploadFilePath.do"/>"  method="post" >
<div id="newUpload">
    <input type="text" id="filePath" name="filePath" value="">
</div>
<input type="submit" value="上传" >
</form>
</body>
</html>