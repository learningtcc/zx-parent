
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title> 信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
	<h2> 详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">渠道号:</td>	
			<td>
			<yk:constantConvert htmlTag="text" dataTag="" data="${asileData}" paramValue="${model.channelNo}" emptyTip=" " codeTag="asileCode" nameTag="asileName"/>
			<%-- <c:out value="${model.channelNo}"/> --%>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">优先级，数值越小，优先级越高:</td>	
			<td><c:out value="${model.priority}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">创建时间:</td>	
			<td><c:out value="${model.createTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">最后更新时间:</td>	
			<td><c:out value="${model.lastUpdateTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">操作者:</td>	
			<td><c:out value="${model.operator}"/></td>
		</tr>
	</table>
	</body>
</html>