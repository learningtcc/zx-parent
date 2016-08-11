
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>发送信息编辑</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
	<h2>修改发送信息</h2>
</div>
	<form id="form1" action="${yk}/userext/SendInfo/update.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<input type="hidden" id="id" name="id" value="<c:out value="${model.id}"/>"/>
		
			
			<tr>	
				<td class="txtr" style="width: 188px">
					文件id:
				</td>	
				<td>
				<input id="fileId" name="fileId" value="<c:out value="${model.fileId}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			<tr>
			<td class="txtr" style="width: 188px">
				发送时间:
			</td>	
			<td>
				<select id="sendType" name="sendType">
					<option value="0" <c:if test="${model.sendType eq '0' }">selected="selected"</c:if>>及时发送</option>
					<option value="1" <c:if test="${model.sendType eq '1' }">selected="selected"</c:if>>延时发送</option>
				</select>
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
					发送类型:
				</td>	
				<td>
				<input id="sendType" name="sendType" value="<c:out value="${model.sendType}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					操作员名称:
				</td>	
				<td>
				<input id="operatorName" name="operatorName" value="<c:out value="${model.operatorName}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			
			<c:if test="${model.filePath != null }">
				<tr>	
				<td class="txtr" style="width: 188px">
					已上传文件:
				</td>	
				<td>
				<div style="width: 100px;">${model.filePath }</div>
				</td>
			</tr>
			</c:if>
			<tr>	
				<td class="txtr" style="width: 188px">
					从新上传文件:
				</td>	
				<td>
					<input id="file" name="ffile" type="file" class="input04"   />
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					活动信息:
				</td>	
				<td>
				<input id="eventInfo" name="eventInfo" value="<c:out value="${model.eventInfo}"/>" type="text" class="input04"   />
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					商户号:
				</td>	
				<td>
					<input id="mchId" name="mchId" value="<c:out value="${model.mchId}"/>" type="text" class="input04"   />
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					状态:
				</td>	
				<td>
				<input id="status" name="status" value="<c:out value="${model.status}"/>" type="text" class="input04"   />
				
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