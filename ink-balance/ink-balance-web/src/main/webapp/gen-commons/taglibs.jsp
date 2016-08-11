<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.yinker.com/tags/yk" prefix="yk"%>

<c:set var="base"
	value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />
<c:set var="yk" value="${pageContext.request.contextPath}" />

<link href="${yk}/gen-commons/yinker-gen.css" rel="stylesheet"
	type="text/css" />
<script src="${yk}/gen-commons/jquery.js"></script>
<script src="${yk}/gen-commons/yinker-gen.js"></script>
<script src="${yk}/gen-commons/My97DatePicker/WdatePicker.js"></script>
<script src="${yk}/gen-commons/jquery.form.js"></script>