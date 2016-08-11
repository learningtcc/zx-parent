<%@ page import="com.ink.monitor.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=MonitorModuleRule.TABLE_ALIAS%>信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
	<h2><%=MonitorModuleRule.TABLE_ALIAS%>详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">平台系统:</td>
			<td>
				<yk:code2name tableName="systerm_source" columnName="name" where="code=?" paramValue="${model.sysCode}" emptyTip="${model.sysCode}" serviceName="monitorDubboBaseService"/>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">功能模块:</td>
			<td>
				<yk:code2name tableName="systerm_module" columnName="name" where="code=?" paramValue="${model.moduleCode}" emptyTip="${model.moduleCode}" serviceName="monitorDubboBaseService"/>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">预警间隔（单位分钟）:</td>
			<td><c:out value="${model.warnInterval}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">预警阀值:</td>
			<td><c:out value="${model.warnThreshold}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">预警频次:</td>
			<td><c:out value="${model.warnFrequency}"/></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">当前已报警次数:</td>
			<td><c:out value="${model.currentWarnCount}"/></td>
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
			<td align="left" style="left: 20px" colspan="2">
				<ul>
					<p></p>
					<li>注：</li>
					<li>1、预警间隔（单位分钟），如15表示15分钟内该模块报警超过阈值未达到频次时需报警，数值为正整数</li>
					<li>2、预警阀值，为0时无阀值限制，数值需大于0</li>
					<li>3、预警频次，频次为-1时表示不限制次数，为0时表示不报警</li>
				</ul>

			</td>
		</tr>
	</table>
	</body>
</html>