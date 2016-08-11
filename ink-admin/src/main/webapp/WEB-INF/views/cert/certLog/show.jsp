<%@ page import="com.ink.cert.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>加解密日志信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
	<h2>加解密日志信息详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">商户号:</td>	
			<td><c:out value="${model.merchantCode}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">证书号:</td>	
			<td><c:out value="${model.certCode}"/></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">证书名称:</td>
			<td><c:out value="${model.certName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">证书类型:</td>	
			<td>
				<yk:constantConvert htmlTag="text" dataTag="certType" paramValue="${model.certType}" emptyTip="" sysTag="cert"/>
			</td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">算法类型:</td>	
			<td>
				<yk:constantConvert htmlTag="text" dataTag="arithmeticType" paramValue="${model.arithmeticType}" emptyTip="" sysTag="cert"/>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">操作类型:</td>	
			<td>
				<yk:constantConvert htmlTag="text" dataTag="operateType" paramValue="${entry.operateType}" emptyTip="" sysTag="cert"/>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">密钥:</td>	
			<td><c:out value="${model.secretKey}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">原始消息:</td>	
			<td><c:out value="${model.oldMsg}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">响应消息:</td>	
			<td><c:out value="${model.newMsg}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">执行结果:</td>
			<td>
				<yk:constantConvert htmlTag="text" dataTag="resultCode" paramValue="${model.resultCode}" emptyTip="" sysTag="cert"/>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">结果信息:</td>	
			<td><c:out value="${model.resultMsg}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">请求流水:</td>
			<td><c:out value="${model.requestId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">操作时间:</td>	
			<td><c:out value="${model.operateTimeString}"/></td>
		</tr>
	</table>
	</body>
</html>