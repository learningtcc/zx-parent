
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title> 编辑</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
	<h2>修改 </h2>
</div>
	<form id="form1" action="${yk}/trade/MchAuth/update.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<input type="hidden" id="id" name="id" value="<c:out value="${model.id}"/>"/>
		
			
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
					授权的支付方式:
				</td>	
				<td>
				<yk:constantConvert htmlTag="select" dataTag="" data="${payTypeData}" paramValue="${model.payType}" emptyTip=" " isAutoItem="false" codeTag="value" nameTag="name" name="payType" />
				<%-- <input id="payType" name="payType" value="<c:out value="${model.payType}"/>" type="text" class="input04"   /> --%>
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					运行状态:
				</td>	
				<td>
				<%-- <input id="status" name="status" value="<c:out value="${model.status}"/>" type="text" class="input04"   /> --%>
				<yk:constantConvert htmlTag="select" dataTag="" data="${mchAuthStatusData}" paramValue="${model.status}" emptyTip=" " isAutoItem="false" codeTag="code" nameTag="value" name="status" />
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					备注:
				</td>	
				<td>
				<input id="remark" name="remark" value="<c:out value="${model.remark}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<%-- <tr>	
				<td class="txtr" style="width: 188px">
					扩展字段1:
				</td>	
				<td>
				<input id="ext1" name="ext1" value="<c:out value="${model.ext1}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					扩展字段2:
				</td>	
				<td>
				<input id="ext2" name="ext2" value="<c:out value="${model.ext2}"/>" type="text" class="input04"   />
				
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
					操作人:
				</td>	
				<td>
				<input id="operator" name="operator" value="<c:out value="${model.operator}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					最后更新时间:
				</td>	
				<td>
				<input onClick="WdatePicker()" id="lastupdateTimeString" value="<c:out value="${model.lastupdateTimeString}"/>" name="lastupdateTimeString" size="10"  readonly="readonly"/>
				
				</td>
			</tr> --%>
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