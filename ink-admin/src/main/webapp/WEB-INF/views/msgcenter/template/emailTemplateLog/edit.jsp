<%@ page import="com.ink.msgcenter.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=EmailTemplateLog.TABLE_ALIAS%>编辑</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
	<h2>修改<%=EmailTemplateLog.TABLE_ALIAS%></h2>
</div>
	<form id="form1" action="${yk}/msgcenter/EmailTemplateLog/update.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<input type="hidden" id="id" name="id" value="<c:out value="${model.id}"/>"/>
		
			
			<tr>	
				<td class="txtr" style="width: 188px">
					模板ID:
				</td>	
				<td>
				<input id="templateId" name="templateId" value="<c:out value="${model.templateId}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
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
					模板名称:
				</td>	
				<td>
				<input id="tempName" name="tempName" value="<c:out value="${model.tempName}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					模板内容:
				</td>	
				<td>
				<input id="tempContent" name="tempContent" value="<c:out value="${model.tempContent}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					模板类型:
				</td>	
				<td>
				<input id="tempType" name="tempType" value="<c:out value="${model.tempType}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					模板参数:
				</td>	
				<td>
				<input id="tempParam" name="tempParam" value="<c:out value="${model.tempParam}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					解析方式:
				</td>	
				<td>
				<input id="parseMethod" name="parseMethod" value="<c:out value="${model.parseMethod}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					模板备注:
				</td>	
				<td>
				<input id="tempRemark" name="tempRemark" value="<c:out value="${model.tempRemark}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					修改内容:
				</td>	
				<td>
				<input id="logRemark" name="logRemark" value="<c:out value="${model.logRemark}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					操作人ID:
				</td>	
				<td>
				<input id="operatorId" name="operatorId" value="<c:out value="${model.operatorId}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					操作人姓名:
				</td>	
				<td>
				<input id="operatorName" name="operatorName" value="<c:out value="${model.operatorName}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					操作时间:
				</td>	
				<td>
				<input onClick="WdatePicker()" id="operateTimeString" value="<c:out value="${model.operateTimeString}"/>" name="operateTimeString" size="10"  readonly="readonly"/>
				
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