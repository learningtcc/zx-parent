<%@ page import="com.ink.scheduler.core.po.*" %>
<%@ page import="com.ink.scheduler.core.po.TimerTaskJob" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=TimerTaskJob.TABLE_ALIAS%>编辑</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
	<h2>修改<%=TimerTaskJob.TABLE_ALIAS%></h2>
</div>
	<form id="form1" action="${yk}/job/update.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<input type="hidden" id="jobId" name="jobId" value="<c:out value="${model.jobId}"/>"/>
		
			
			<tr>	
				<td class="txtr" style="width: 188px">
					任务名称:
				</td>	
				<td>
					<c:out value="${model.jobName}"/>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					任务分组:
				</td>	
				<td>
					<c:out value="${model.jobGroup}"/>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					任务状态:
				</td>	
				<td>
					<c:if test="${model.status=='0'}">禁用</c:if>
					<c:if test="${model.status=='1'}">启用</c:if>
					<c:if test="${model.status=='2'}">删除</c:if>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					任务调度状态:
				</td>	
				<td>
					<c:if test="${model.jobStatus=='0'}">运行</c:if>
					<c:if test="${model.jobStatus=='1'}">暂停</c:if>
				</td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">
					任务是否有状态（并发）:
				</td>	
				<td>
					<c:if test="${model.isConcurrent=='1'}">有状态</c:if>
				</td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">
					任务调用类名:
				</td>
				<td>
					<c:out value="${model.jobClass}"/>
				</td>
			</tr>
			<tr>
				<td class="txtr" style="width: 188px">
					启动时间:
				</td>	
				<td>
					<fmt:formatDate value="${model.firstTime}" type="both" pattern="yyyy/MM/dd hh:mm:ss"/>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					前一次运行时间:
				</td>	
				<td>
					<fmt:formatDate value="${model.previousTime}" type="both" pattern="yyyy/MM/dd hh:mm:ss"/>
				</td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">
					任务url:
				</td>	
				<td>
					<c:out value="${model.jobUrl}"/>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					任务执行次数:
				</td>	
				<td>
					<c:out value="${model.runCount}"/>
				</td>
			</tr>
			<tr>
				<td class="txtr" style="width: 188px">
					任务有效时间:
				</td>
				<td>
					<input onClick="WdatePicker()" value="<c:out value="${model.validityPeriodString}"/>" id="validityPeriodString" name="validityPeriodString" size="10"  readonly="readonly"/>
                    <span class="required">*</span>
                </td>
			</tr>
			<tr>
				<td class="txtr" style="width: 188px">
					任务描述:
				</td>
				<td>
					<input id="description" name="description" value="<c:out value="${model.description}"/>" type="text" class="input04"   />
                </td>
			</tr>
			<tr>
				<td class="txtr" style="width: 188px">
					任务运行时间表达式:
				</td>	
				<td>
				<input id="cronExpression" name="cronExpression" value="<c:out value="${model.cronExpression}"/>" type="text" class="input04"   />
                    <span class="required">*</span>
				</td>
			</tr>
			<tr>
				<td class="txtr" style="width: 188px">
					任务预计执行时间（单位秒）:
				</td>
				<td>
					<input id="jobExecuteTime" name="jobExecuteTime" value="<c:out value="${model.jobExecuteTime}"/>" type="text" class="input04"   />
                    <span class="required">*</span>
				</td>
			</tr>
			<tr>	
				<td class="txtc" colspan="2">
					<input type="button" class="btn01"  value="提交" onclick="$('#form1').submit();"/>
					<input type="button" class="btn01"  value="取消" onclick="window.close();"/>
					<input type="reset" class="btn01"  value="重置"/>
				</td>	
			</tr>
		</table>
	</form>
	
</body>
<script type="text/javascript">
$(function(){
	var options = {
	         beforeSubmit:   validateForm,   // pre-submit callback 表单提交前被调用的回调函数
	         success:        callBack   // post-submit callback   表单提交成功后被调用的回调函数
	     }; 
	$('#form1').ajaxForm(options);
});

window.onbeforeunload=function(){
	window.opener.document.queryForm.submit();
}

function validateForm(){
	var cronExpression = $.trim($("#cronExpression").val());
	var jobExecuteTime = $.trim($("#jobExecuteTime").val());
	if( cronExpression == "" ){
		alert("任务时间表达式不能为空");
		return false;
	}
	if( jobExecuteTime == "" ){
		alert("任务预计执行时间不能为空");
		return false;
	}
	return true;
}
function callBack(data){
	if(data.result == "true")	{
		alert("保存成功！");
		window.close();
	}else{
		alert(data.errorMsg);
	}
}
</script>
</html>