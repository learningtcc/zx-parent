<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/gen-commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>

</head>
<br>

<h1>tfs上传成功</h1>
<c:forEach var="tfs" items="${tfs}">
    tfs文件名： ${tfs.key}
    </br>
    上传文件名： ${tfs.value}
    </br>
    </br>
</c:forEach>
</body>
</html>