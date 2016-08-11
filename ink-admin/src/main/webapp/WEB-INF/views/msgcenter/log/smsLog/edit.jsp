<%@ page import="com.ink.msgcenter.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=SmsLog.TABLE_ALIAS%>编辑</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
	<h2>修改<%=SmsLog.TABLE_ALIAS%></h2>
</div>
	<form id="form1" action="${yk}/msgcenter/SmsLog/update.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<input type="hidden" id="id" name="id" value="<c:out value="${model.id}"/>"/>
		
			
			<tr>	
				<td class="txtr" style="width: 188px">
					商户ID:
				</td>	
				<td>
				<input id="merctId" name="merctId" value="<c:out value="${model.merctId}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					商户代码:
				</td>	
				<td>
				<input id="merctCode" name="merctCode" value="<c:out value="${model.merctCode}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					通道ID:
				</td>	
				<td>
				<input id="chnId" name="chnId" value="<c:out value="${model.chnId}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					通道代码:
				</td>	
				<td>
				<input id="chnCode" name="chnCode" value="<c:out value="${model.chnCode}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					模板ID:
				</td>	
				<td>
				<input id="tempId" name="tempId" value="<c:out value="${model.tempId}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					手机号:
				</td>	
				<td>
				<input id="mobile" name="mobile" value="<c:out value="${model.mobile}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					短信内容:
				</td>	
				<td>
				<input id="smsMsg" name="smsMsg" value="<c:out value="${model.smsMsg}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					业务单号:
				</td>	
				<td>
				<input id="infoCode" name="infoCode" value="<c:out value="${model.infoCode}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					短信代码:
				</td>	
				<td>
				<input id="smsCode" name="smsCode" value="<c:out value="${model.smsCode}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					短信优先级:
				</td>	
				<td>
				<input id="priority" name="priority" value="<c:out value="${model.priority}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					短信ID:
				</td>	
				<td>
				<input id="smsId" name="smsId" value="<c:out value="${model.smsId}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					发送序列ID:
				</td>	
				<td>
				<input id="taskId" name="taskId" value="<c:out value="${model.taskId}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					发送状态:
				</td>	
				<td>
				<input id="sendStatus" name="sendStatus" value="<c:out value="${model.sendStatus}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					响应代码:
				</td>	
				<td>
				<input id="responseCode" name="responseCode" value="<c:out value="${model.responseCode}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					短信类型:
				</td>	
				<td>
				<input id="smsType" name="smsType" value="<c:out value="${model.smsType}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					状态报告:
				</td>	
				<td>
				<input id="reportStatus" name="reportStatus" value="<c:out value="${model.reportStatus}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					提交时间:
				</td>	
				<td>
				<input onClick="WdatePicker()" id="submitTimeString" value="<c:out value="${model.submitTimeString}"/>" name="submitTimeString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					发送时间:
				</td>	
				<td>
				<input onClick="WdatePicker()" id="sendTimeString" value="<c:out value="${model.sendTimeString}"/>" name="sendTimeString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					定时发送时间:
				</td>	
				<td>
				<input onClick="WdatePicker()" id="fixTimeString" value="<c:out value="${model.fixTimeString}"/>" name="fixTimeString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					状态报告时间:
				</td>	
				<td>
				<input onClick="WdatePicker()" id="reportTimeString" value="<c:out value="${model.reportTimeString}"/>" name="reportTimeString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					异常信息:
				</td>	
				<td>
				<input id="sendException" name="sendException" value="<c:out value="${model.sendException}"/>" type="text" class="input04"   />
				
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