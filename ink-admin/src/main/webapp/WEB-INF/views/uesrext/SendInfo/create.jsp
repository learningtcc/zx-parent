
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>发送信息新增</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
	<h2>新增发送信息</h2>
</div>
	<form id="form1" action="${yk}/userext/SendInfo/save.do" method="post" enctype=”multipart/form-data”>
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<input type="hidden" id="id" name="id" value="<c:out value="${model.id}"/>"/>
			<input id="fileId" name="fileId" type="hidden" class="input04"  value="${fileId }" />
			
			<tr>	
				<td class="txtr" style="width: 188px">
					发送类型：
				</td>	
				<td>
				<select id="sendType" name="sendType" onchange="sendTypeSe()" style="width: 180px;">
					<option value="0">及时发送</option>
					<option value="1">延时发送</option>
				</select>
				</td>
			</tr>
			
			<tr id="sendTimeTr" style="display: none">	
				<td class="txtr" style="width: 188px">
					发送时间：
				</td>	
				<td>
				<input style="width: 180px;" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="sendTimeString" name="sendTimeString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					操作员名称：
				</td>	
				<td>
				<input id="operatorName" name="operatorName" value="${userName }" type="text" class="input04" readonly="readonly"  />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					上传文件：
				</td>	
				<td>
				<input id="ffile" name="ffile" type="file" class="input04" accept=".txt" />
				
				</td>
			</tr>
			<tr>	
				<td class="txtr" style="width: 188px">
					商户号：
				</td>	
				<td>
				<input id="mchId" name="mchId" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					活动信息：
				</td>	
				<td>
				<input id="eventInfo" name="eventInfo" type="text" class="input04"   />
				
				</td>
			</tr>
			<!-- 	
			<tr>	
				<td class="txtr" style="width: 188px">
					状态：
				</td>	
				<td>
				<input id="status" name="status" type="text" class="input04"   />
				
				</td>
			</tr>
			 -->
			 <input id="type" name="type" type="hidden"></input>		
			<tr>	
				<td class="txtc" colspan="2">
					<!-- <input type="button" class="btn01"  value="保存" onclick="$('#form1').submit();"/> -->
					<input type="button" class="btn01"  value="保存并发送" onclick="saveAndSend()"/>
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
	var ffile = $('#ffile').val();
	console.info(ffile);
	if(ffile.trim().length < 1){
		alert("请选择文件!");
		return false;
	}
	var mchId = $('#mchId').val();
	console.info(mchId);
	if(mchId.trim().length < 1){
		alert("商户号不能为空!");
		return false;
	}
	return true;
}
function callBack(data){
	if(data==1)	{
		alert("保存成功！");
		window.close();
	}else if(data==2){
		alert("保存并发送成功!");
		window.close();
	}else{
		alert("保存失败！");
	}
}


function sendTypeSe(){
	var value=$("#sendType").val();
	console.info(value);
	if(value == 'A'){
		$('#sendTimeTr').hide();
	}else{
		$('#sendTimeTr').show();
	}
}

function saveAndSend(){
	$("#type").val("send");
	$('#form1').submit();
}
</script>
</html>

