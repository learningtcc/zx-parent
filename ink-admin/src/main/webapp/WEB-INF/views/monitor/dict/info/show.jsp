<%@ page import="com.ink.monitor.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>业务模块信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
	<h2>业务模块详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>
			<td class="txtr" style="width: 188px">平台系统:</td>
			<td>
				<yk:code2name tableName="systerm_source" columnName="name" where="code=?" paramValue="${model.systermCode}" emptyTip="${model.systermCode}" serviceName="monitorDubboBaseService"/>
			</td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">功能模块:</td>
			<td>
				<yk:code2name tableName="systerm_module" columnName="name" where="code=?" paramValue="${model.moduleCode}" emptyTip="${model.moduleCode}" serviceName="monitorDubboBaseService"/>
			</td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">业务模块名:</td>
			<td><c:out value="${model.name}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">业务模块码:</td>
			<td><c:out value="${model.code}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">状态:</td>	
			<td>
				<yk:constantConvert htmlTag="text" dataTag="status" paramValue="${model.status}" emptyTip="未知" sysTag="monitor" />
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">创建时间:</td>	
			<td><c:out value="${model.createTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">更新时间:</td>	
			<td><c:out value="${model.updateTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">操作人:</td>	
			<td><yk:code2name tableName="monitor_user" columnName="fullName" where="userid=?" paramValue="${model.opUser}" emptyTip="${model.opUser}" serviceName="monitorDubboBaseService" /></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">描述:</td>	
			<td><c:out value="${model.opDesc}"/></td>
		</tr>
	</table>

</body>
</html>