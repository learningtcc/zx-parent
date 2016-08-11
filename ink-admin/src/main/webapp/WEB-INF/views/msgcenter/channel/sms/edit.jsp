<%@ page import="com.ink.msgcenter.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>邮件通道编辑</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
	<script type="text/javascript" src="${yk}/static/common/maintain.js"></script>
</head>
<body>
<div class="title01">
	<h2>修改邮件通道</h2>
</div>
	<form id="form1" action="${yk}/msgcenter/channel/sms/update.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<input type="hidden" id="id" name="id" value="<c:out value="${model.id}"/>"/>
			<input id="status" name="status" type="hidden" value="<c:out value="${model.status}"/>" />
			<input id="creatorId" name="creatorId" value="<c:out value="${model.creatorId}"/>" type="hidden"  />
			<input id="creatorName" name="creatorName" value="<c:out value="${model.creatorName}"/>" type="hidden"  />
			<input id="createTimeString" value="<c:out value="${model.createTimeString}"/>" name="createTimeString" type="hidden"/>
			<input id="priorityLevel" name="priorityLevel" type="hidden"  value="<c:out value="${model.priorityLevel}" />"/>
			<tr>	
				<td class="txtr" style="width: 188px">
					通道名称<label style="color: red">*</label>:
				</td>	
				<td>
				<input id="name" name="name" value="<c:out value="${model.name}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					通道代码<label style="color: red">*</label>:
				</td>	
				<td>
				<input id="chnCode" name="chnCode" value="<c:out value="${model.chnCode}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					通道类型<label style="color: red">*</label>:
				</td>	
				<td>
					<yk:constantConvert htmlTag="select" name="chnType" dataTag="chnType" paramValue="${model.chnType}" emptyTip="${model.chnType}" sysTag="msgcenter"/>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					通道参数<label style="color: red">*</label>:
				</td>	
				<td>
				<input id="chnParam" name="chnParam" value="<c:out value="${model.chnParam}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					通道备注:
				</td>	
				<td>
					<textarea rows="3" cols="40" name="remark" id="remark"><c:out value="${model.remark}"/></textarea>
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
	var result = false;
	if (validateLengthFrom("name", 1, "通道名称")
			&&validateLengthFrom("chnType", 1, "请选择通道类型")
			&& validateLengthFrom("chnParam", 1, "通道参数不能为空")

	) {
		result = true ;
	}
	return result;
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