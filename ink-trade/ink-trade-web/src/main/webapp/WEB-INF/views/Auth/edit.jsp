
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>编辑</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
	<h2>修改</h2>
</div>
	<form id="form1" action="${yk}/Auth/update.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<input type="hidden" id="id" name="id" value="<c:out value="${model.id}"/>"/>
		
			
			<tr>	
				<td class="txtr" style="width: 188px">
					银行卡号:
				</td>	
				<td>
				<input id="cardNo" name="cardNo" value="<c:out value="${model.cardNo}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					银行卡类型:
				</td>	
				<td>
				<input id="cardType" name="cardType" value="<c:out value="${model.cardType}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					手机号:
				</td>	
				<td>
				<input id="phoneNo" name="phoneNo" value="<c:out value="${model.phoneNo}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					证件类型:
				</td>	
				<td>
				<input id="idType" name="idType" value="<c:out value="${model.idType}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					身份证号:
				</td>	
				<td>
				<input id="idNo" name="idNo" value="<c:out value="${model.idNo}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					姓名:
				</td>	
				<td>
				<input id="userName" name="userName" value="<c:out value="${model.userName}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					所属银行简码:
				</td>	
				<td>
				<input id="bankShort" name="bankShort" value="<c:out value="${model.bankShort}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					所属银行:
				</td>	
				<td>
				<input id="bankName" name="bankName" value="<c:out value="${model.bankName}"/>" type="text" class="input04"   />
				
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
				<td class="txtr" style="width: 188px">
					版本号:
				</td>	
				<td>
				<input id="version" name="version" value="<c:out value="${model.version}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					删除标识:
				</td>	
				<td>
				<input id="isDelete" name="isDelete" value="<c:out value="${model.isDelete}"/>" type="text" class="input04"   />
				
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
					最后修改时间:
				</td>	
				<td>
				<input onClick="WdatePicker()" id="lastupdateTimeString" value="<c:out value="${model.lastupdateTimeString}"/>" name="lastupdateTimeString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					请求流水号:
				</td>	
				<td>
				<input id="reqId" name="reqId" value="<c:out value="${model.reqId}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					客户号:
				</td>	
				<td>
				<input id="userId" name="userId" value="<c:out value="${model.userId}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					createTime:
				</td>	
				<td>
				<input onClick="WdatePicker()" id="createTimeString" value="<c:out value="${model.createTimeString}"/>" name="createTimeString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					支付类型:
				</td>	
				<td>
				<input id="payType" name="payType" value="<c:out value="${model.payType}"/>" type="text" class="input04"   />
				
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