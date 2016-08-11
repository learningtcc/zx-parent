<%@ page import="com.yinker.scheduler.core.po.*" %>
<%@ page import="com.ink.scheduler.core.po.TimerTaskJob" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=TimerTaskJob.TABLE_ALIAS%>信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
	<h2><%=TimerTaskJob.TABLE_ALIAS%>详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">任务名称:</td>	
			<td><c:out value="${model.jobName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">任务分组:</td>
			<td><c:out value="${model.jobGroup}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">任务状态:</td>
			<td>
				<c:if test="${model.status=='0'}">禁用</c:if>
				<c:if test="${model.status=='1'}">启用</c:if>
				<c:if test="${model.status=='2'}">删除</c:if>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">任务调度状态:</td>
			<td>
				<c:if test="${model.jobStatus=='0'}">运行</c:if>
				<c:if test="${model.jobStatus=='1'}">暂停</c:if>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">任务是否有状态（并发）:</td>
			<td><c:out value="${model.isConcurrent}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">任务描述:</td>
			<td><c:out value="${model.description}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">任务调用类名:</td>
			<td><c:out value="${model.jobClass}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">任务调用的方法名:</td>
			<td><c:out value="${model.methodName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">启动时间:</td>
			<td><fmt:formatDate value="${model.firstTime}" type="both" pattern="yyyy/MM/dd hh:mm:ss"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">前一次运行时间:</td>
			<td><fmt:formatDate value="${model.previousTime}" type="both" pattern="yyyy/MM/dd hh:mm:ss"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">任务有效时间:</td>
			<td><fmt:formatDate value="${model.validityPeriod}" type="both" pattern="yyyy/MM/dd hh:mm:ss"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">任务url:</td>
			<td><c:out value="${model.jobUrl}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">任务执行次数:</td>
			<td><c:out value="${model.runCount}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">任务运行时间表达式:</td>
			<td><c:out value="${model.cronExpression}"/></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">任务预计执行时间:</td>
			<td><c:out value="${model.jobExecuteTime}"/></td>
		</tr>
	</table>
	</body>
</html>