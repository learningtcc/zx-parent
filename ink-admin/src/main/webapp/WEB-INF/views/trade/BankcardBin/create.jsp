
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>新增</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
	<h2>新增</h2>
</div>
	<form id="form1" action="${yk}/trade/BankcardBin/save.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<input type="hidden" id="id" name="id" value="<c:out value="${model.id}"/>" />
		
			
			<tr>	
				<td class="txtr" style="width: 188px">
					银行名称：
				</td>	
				<td>
				<input id="bankName" name="bankName" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					银行简码：
				</td>	
				<td>
				<input id="bankSimpleCode" name="bankSimpleCode" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					机构代码：
				</td>	
				<td>
				<input id="bankOrg" name="bankOrg" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					发卡行标识：
				</td>	
				<td>
				<input id="cardBin" name="cardBin" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					 发卡行标识长度：
				</td>	
				<td>
				<input id="cardBinLen" name="cardBinLen" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					卡类型：
				</td>	
				<td>
				<yk:constantConvert  htmlTag="select" dataTag="" data="${cardbinData}" paramValue="${entry.cardCategory}" emptyTip="1" codeTag="code"  isAutoItem="false" nameTag="value" name="cardCategory" />
				<!-- <input id="cardCategory" name="cardCategory" type="text" class="input04"   /> -->
				
				</td>
			</tr>
			
			<!-- <tr>	
				<td class="txtr" style="width: 188px">
					修改时间：
				</td>	
				<td>
				<input onClick="WdatePicker()" id="modifyTimeString" name="modifyTimeString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					创建时间：
				</td>	
				<td>
				<input onClick="WdatePicker()" id="createTimeString" name="createTimeString" size="10"  readonly="readonly"/>
				
				</td>
			</tr> -->
			
			<tr>	
				<td class="txtr" style="width: 188px">
					卡名：
				</td>	
				<td>
				<input id="cardName" name="cardName" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					卡号长度：
				</td>	
				<td>
				<input id="cardNoLen" name="cardNoLen" type="text" class="input04"   />
				
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

