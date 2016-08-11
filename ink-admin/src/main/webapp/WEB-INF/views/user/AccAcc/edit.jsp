<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>账户信息编辑</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
	<h2>修改账户信息</h2>
</div>
	<form id="form1" action="${yk}/user/AccAcc/update.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<input type="hidden" id="id" name="id" value="<c:out value="${model.id}"/>"/>
		
			
			
		<input id="createTimeString" value="<c:out value="${model.createTimeString}"/>" name="createTimeString" size="10"  type="hidden"/>
				
				
			
			
		<input id="lastUpdateTimeString" value="<c:out value="${model.lastUpdateTimeString}"/>" name="lastUpdateTimeString" size="10" type="hidden"/>
				
			
			<tr>	
				<td class="txtr" style="width: 188px">
					子帐户控制表主键:
				</td>	
				<td>
				<input id="accSacId" name="accSacId" value="<c:out value="${model.accSacId}"/>" readonly="readonly" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					子帐户号:
				</td>	
				<td>
				<input id="sacId" name="sacId" value="<c:out value="${model.sacId}"/>" readonly="readonly" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					主帐户控制表主键:
				</td>	
				<td>
				<input id="accPacId" name="accPacId" value="<c:out value="${model.accPacId}"/>" readonly="readonly" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					主账号:
				</td>	
				<td>
				<input id="pacId" name="pacId" value="<c:out value="${model.pacId}"/>" readonly="readonly" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					子帐户种类:
				</td>	
				<td>
				<input id="sacType" name="sacType" value="<c:out value="${model.sacType}"/>" readonly="readonly" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					币种:
				</td>	
				<td>
				<input id="cur" name="cur" value="<c:out value="${model.cur}"/>" readonly="readonly" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					昨日余额 日终批处理更新:
				</td>	
				<td>
				<input id="lstBal" name="lstBal" value="<c:out value="${model.lstBal}"/>" class="input04"  readonly="readonly" />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					当前余额:
				</td>	
				<td>
				<input id="curBal" name="curBal" value="<c:out value="${model.curBal}"/>" class="input04"  readonly="readonly" />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					临时余额 日终使用:
				</td>	
				<td>
				<input id="tmpBal" name="tmpBal" value="<c:out value="${model.tmpBal}"/>" readonly="readonly" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					冻结金额:
				</td>	
				<td>
				<input id="frozenAmt" name="frozenAmt" value="<c:out value="${model.frozenAmt}"/>" readonly="readonly" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					开户日期:
				</td>	
				<td>
				<input onClick="WdatePicker()" id="openDateString" value="<c:out value="${model.openDateString}"/>" name="openDateString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					销户日期:
				</td>	
				<td>
				<input id="closeDateString" value="<c:out value="${model.closeDateString}"/>" name="closeDateString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					状态 :
				</td>	
				<td>
				<select id="status" name="status" style="width:290px">
					<option value="1" <c:if test="${model.status == '1'}">selected="selected"</c:if> >启用</option>
					<option value="2" <c:if test="${model.status == '2'}">selected="selected"</c:if> >停用</option>
					<option value="9" <c:if test="${model.status == '9'}">selected="selected"</c:if> >注销</option>
				</select>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					资金账户认证信息:
				</td>	
				<td>
				<input id="accMac" name="accMac" value="<c:out value="${model.accMac}"/>" readonly="readonly" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					删除标识 :
				</td>	
				<td>
				<select id="delFlag" name="delFlag" style="width:290px">
					<option value="0" <c:if test="${model.delFlag == '0'}">selected="selected"</c:if> >正常</option>
					<option value="1" <c:if test="${model.delFlag == '1'}">selected="selected"</c:if> >删除</option>
				</select>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					客户号:
				</td>	
				<td>
				<input id="uid" name="uid" value="<c:out value="${model.uid}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<input id="version" name="version" value="<c:out value="${model.version}"/>" type="hidden"   />
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
	var sacType = $("#sacType").val();
	if($.trim(sacType).length != 4){
		alert('子帐户种类长度错误!');
		$('#sacType').focus();
		return false;
	}
	var pac_id = $("#pac_id").val();
	if($.trim(pac_id).length == ""){
		alert('主账号不能为空!');
		$('#pac_id').focus();
		return false;
	}
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