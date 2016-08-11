<%@ page import="com.yinker.scheduler.core.po.*" %>
<%@ page import="com.ink.scheduler.core.po.TimerTaskJobLog" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=TimerTaskJobLog.TABLE_ALIAS%>编辑</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
	<h2>修改<%=TimerTaskJobLog.TABLE_ALIAS%></h2>
</div>
	<form id="form1" action="${yk}/TimerTaskJobLog/update.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<input type="hidden" id="id" name="id" value="<c:out value="${model.id}"/>"/>
		
			
			<tr>	
				<td class="txtr" style="width: 188px">
					任务ID:
				</td>	
				<td>
				<input id="jobId" name="jobId" value="<c:out value="${model.jobId}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					任务开始时间:
				</td>	
				<td>
				<input onClick="WdatePicker()" id="startTimeString" value="<c:out value="${model.startTimeString}"/>" name="startTimeString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					任务结束时间:
				</td>	
				<td>
				<input onClick="WdatePicker()" id="endTimeString" value="<c:out value="${model.endTimeString}"/>" name="endTimeString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					任务执行结果:
				</td>	
				<td>
				<input id="status" name="status" value="<c:out value="${model.status}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					业务流水ID:
				</td>	
				<td>
				<input id="reqId" name="reqId" value="<c:out value="${model.reqId}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					错误信息:
				</td>	
				<td>
				<input id="errorMsg" name="errorMsg" value="<c:out value="${model.errorMsg}"/>" type="text" class="input04"   />
				
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
	return true;
}
function callBack(data){
	if(data==1)	{
		alert("保存成功！");
		window.close();
	}else{
		alert("保存失败！");
	}
}
</script>
</html>