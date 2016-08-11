<%@ page import="com.yinker.scheduler.core.po.*" %>
<%@ page import="com.ink.scheduler.core.po.TimerTaskJob" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=TimerTaskJob.TABLE_ALIAS%>新增</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
	<h2>新增<%=TimerTaskJob.TABLE_ALIAS%></h2>
</div>
	<form id="form1" action="${yk}/job/save.do" method="post">
        <input  id="isConcurrent" name="isConcurrent" type="hidden" class="input04"  value="1" />
        <input  id="status" name="status" type="hidden" class="input04"  value="1" />
        <input  id="runCount" name="runCount" type="hidden" class="input04"  value="0" />
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<input type="hidden" id="jobId" name="jobId" value="<c:out value="${model.jobId}"/>"/>
			<tr>
				<td class="txtr" style="width: 188px">
					任务描述：
				</td>
				<td>
					<input id="description" name="description" type="text" class="input04"   />

				</td>
			</tr>
			<tr>
				<td class="txtr" style="width: 188px">
					任务url：
				</td>	
				<td>
				<input id="jobName" name="jobName" type="text" class="input04"  required="true" />
				<span class="required">*</span>
				</br><span>任务名称不能重复</span>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
                    任务分组：
				</td>	
				<td>
				<input id="jobGroup" name="jobGroup" type="text" class="input04"  required="true" />
				<span class="required">*</span>
				</td>
			</tr>
			<tr>
				<td class="txtr" style="width: 188px">
					任务名称：
				</td>
				<td>
					<input id="jobUrl" name="jobUrl" type="text" class="input04"  required="true" />
					<span class="required">*</span>
				</td>
			</tr>
            <tr>
                <td class="txtr" style="width: 188px">
                    任务调度状态：
                </td>
                <td>
                    <select id="jobStatus" name="jobStatus">
                        <option value="0">运行</option>
                        <option value="1">暂停</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="txtr" style="width: 188px">
                    任务运行时间表达式：
                </td>
                <td>
                    <input id="cronExpression" name="cronExpression" type="text" class="input04"   />
                    <span class="required">*</span>
					</br><span>eg: "0 0 12 * * ?" 每天中午12点触发  (quartz)</span>
                </td>
            </tr>
            <tr>
                <td class="txtr" style="width: 188px">
                    任务预计执行时间(单位秒)：
                </td>
                <td>
                    <input id="jobExecuteTime" name="jobExecuteTime" type="text" class="input04"   />
                    <span class="required">*</span>
                </td>
            </tr>
            <tr>
                <td class="txtr" style="width: 188px">
                    任务有效时间：
                </td>
                <td>
                    <input onClick="WdatePicker()" id="validityPeriodString" name="validityPeriodString" size="10"  readonly="readonly"/>

                </td>
            </tr>
			<tr>
				<td class="txtr" style="width: 188px">
					任务调用的方法名：
				</td>	
				<td>
				<input id="methodName" name="methodName" type="text" class="input04"  value="execute" disabled=true  />
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
	var jobName = $("#jobName").val().trim();
    var jobGroup = $("#jobGroup").val().trim();
    var cronExpression = $("#cronExpression").val().trim();
    var jobExecuteTime = $("#jobExecuteTime").val().trim();
	var jobUrl = $("#jobUrl").val().trim();
	if( jobName == "" ){
        alert("任务执行url不能为空");
		return false;
	}
    if( jobGroup == "" ){
        alert("任务分组不能为空");
        return false;
    }
    if( cronExpression == "" ){
        alert("时间表达式不能为空");
        return false;
    }
    if( jobExecuteTime == "" ){
        alert("任务预计执行时间不能为空");
        return false;
    }
	if( jobUrl == "" ){
		alert("任务名称不能为空");
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

