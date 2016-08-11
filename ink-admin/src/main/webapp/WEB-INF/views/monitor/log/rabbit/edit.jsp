<%@ page import="com.ink.monitor.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=MonitorMqLog.TABLE_ALIAS%>编辑</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
	<h2>修改<%=MonitorMqLog.TABLE_ALIAS%></h2>
</div>
	<form id="form1" action="${yk}/MonitorMqLog/update.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<input type="hidden" id="id" name="id" value="<c:out value="${model.id}"/>"/>
		
			
			<tr>	
				<td class="txtr" style="width: 188px">
					系统代码:
				</td>	
				<td>
				<input id="sysCode" name="sysCode" value="<c:out value="${model.sysCode}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					MQ交换器:
				</td>	
				<td>
				<input id="exchange" name="exchange" value="<c:out value="${model.exchange}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					MQ路由:
				</td>	
				<td>
				<input id="routingKey" name="routingKey" value="<c:out value="${model.routingKey}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					消息ID:
				</td>	
				<td>
				<input id="msgId" name="msgId" value="<c:out value="${model.msgId}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					MQ IP:
				</td>	
				<td>
				<input id="rabbitAddress" name="rabbitAddress" value="<c:out value="${model.rabbitAddress}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					MQ端口:
				</td>	
				<td>
				<input id="rabbitPort" name="rabbitPort" value="<c:out value="${model.rabbitPort}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					本地IP:
				</td>	
				<td>
				<input id="localAddress" name="localAddress" value="<c:out value="${model.localAddress}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					消息异常:
				</td>	
				<td>
				<input id="msgException" name="msgException" value="<c:out value="${model.msgException}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					请求流水:
				</td>	
				<td>
				<input id="requestId" name="requestId" value="<c:out value="${model.requestId}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					消息类型:
				</td>	
				<td>
				<input id="msgType" name="msgType" value="<c:out value="${model.msgType}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					字符串消息:
				</td>	
				<td>
				<input id="msgText" name="msgText" value="<c:out value="${model.msgText}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					序列化消息:
				</td>	
				<td>
				<input id="msgObject" name="msgObject" value="<c:out value="${model.msgObject}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					状态：0未解决、1已解决:
				</td>	
				<td>
				<input id="logStatus" name="logStatus" value="<c:out value="${model.logStatus}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					创建时间:
				</td>	
				<td>
				<input onClick="WdatePicker()" id="createTimeString" value="<c:out value="${model.createTimeString}"/>" name="createTimeString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					处理时间:
				</td>	
				<td>
				<input onClick="WdatePicker()" id="fixedTimeString" value="<c:out value="${model.fixedTimeString}"/>" name="fixedTimeString" size="10"  readonly="readonly"/>
				
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