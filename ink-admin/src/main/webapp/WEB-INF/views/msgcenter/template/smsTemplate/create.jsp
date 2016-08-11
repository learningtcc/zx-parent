<%@ page import="com.ink.msgcenter.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=SmsTemplate.TABLE_ALIAS%>新增</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
	<h2>新增<%=SmsTemplate.TABLE_ALIAS%></h2>
</div>
	<form id="form1" action="${yk}/msgcenter/SmsTemplate/save.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<input type="hidden" id="id" name="id" value="<c:out value="${model.id}"/>"/>
		
			<tr>	
				<td class="txtr" style="width: 188px">
					商户：
				</td>	
				<td>
				<yk:select codeName="name" table="merchant_info" codeValue="id" id="merctId" name="merctId"  serviceName="msgcenterDubboBaseService"/>
				
				</td>
			</tr>
			<!-- 
			<tr>	
				<td class="txtr" style="width: 188px">
					商户代码：
				</td>	
				<td>
				<input id="merctCode" name="merctCode" type="text" class="input04"   />
				
				</td>
			</tr>
			 -->
			<tr>	
				<td class="txtr" style="width: 188px">
					模板名称：
				</td>	
				<td>
				<input id="tempName" name="tempName" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					模板内容：
				</td>	
				<td>
				<textarea rows="10" cols="20" id="tempContent" name="tempContent"></textarea>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					解析方式 ：
				</td>	
				<td>
				<select id="parseMethod" name="parseMethod">
					<option value="1">顺序解析</option>
					<option value="2">JSON解析</option>
				</select>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					模板备注：
				</td>	
				<td>
				<input id="tempRemark" name="tempRemark" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					模板参数：
				</td>	
				<td>
				<input id="tempParam" name="tempParam" type="text" class="input04"   />
				
				</td>
			</tr>
			<input id="status" name="status" type="hidden" value="0"></input>
			
			<!-- 
			<tr>	
				<td class="txtr" style="width: 188px">
					creatorId：
				</td>	
				<td>
				<input id="creatorId" name="creatorId" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					creatorName：
				</td>	
				<td>
				<input id="creatorName" name="creatorName" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					createTime：
				</td>	
				<td>
				<input onClick="WdatePicker()" id="createTimeString" name="createTimeString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			 -->
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

