
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>账户控制编辑</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
	<h2>修改账户控制</h2>
</div>
	<form id="form1" action="${yk}/user/AccPac/update.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<input type="hidden" id="id" name="id" value="<c:out value="${model.id}"/>"/>
		
			
			
				<input id="owner" name="owner" value="<c:out value="${model.owner}"/>" type="hidden" class="input04"   />
				<input id="ownerGroup" name="ownerGroup" value="<c:out value="${model.ownerGroup}"/>" type="hidden" class="input04"   />
				<input  id="createTimeString" value="<c:out value="${model.createTimeString}"/>" name="createTimeString" size="10"  type="hidden"/>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					主账号:
				</td>	
				<td>
				<input id="pacId" name="pacId" value="<c:out value="${model.pacId}"/>" type="text" class="input04"  required="true" />
				<span class="required">*</span>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					商户信息表主键:
				</td>	
				<td>
				<input id="accProdId" name="accProdId" value="<c:out value="${model.accProdId}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					产品编号(帐户级别):
				</td>	
				<td>
				<select id="prodId" name="prodId">
					<option value="0001" <c:if test="${model.prodId eq '0001' }">selected="selected"</c:if>>未实名账户</option>
					<option value="0002" <c:if test="${model.prodId eq '0002' }">selected="selected"</c:if>>已实名账户</option>
					<option value="0003" <c:if test="${model.prodId eq '0003' }">selected="selected"</c:if>>VIP帐户</option>
					<option value="0004" <c:if test="${model.prodId eq '0004' }">selected="selected"</c:if>>公司账户</option>
				</select>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					客户类型 :
				</td>	
				<td>
				<select id="custType" name="custType">
					<option value="0" <c:if test="${model.custType eq '0' }">selected="selected"</c:if>>个人</option>
					<option value="1" <c:if test="${model.custType eq '1' }">selected="selected"</c:if>>单位</option>
				</select>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日收入金额累计:
				</td>	
				<td>
				<input id="dayInAmt" name="dayInAmt" value="<c:out value="${model.dayInAmt}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日收入笔数累计:
				</td>	
				<td>
				<input id="dayInCnt" name="dayInCnt" value="<c:out value="${model.dayInCnt}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日支出金额累计:
				</td>	
				<td>
				<input id="dayPayAmt" name="dayPayAmt" value="<c:out value="${model.dayPayAmt}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日支出笔数累计:
				</td>	
				<td>
				<input id="dayPayCnt" name="dayPayCnt" value="<c:out value="${model.dayPayCnt}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					月收入金额累计:
				</td>	
				<td>
				<input id="monInAmt" name="monInAmt" value="<c:out value="${model.monInAmt}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					月收入笔数累计:
				</td>	
				<td>
				<input id="monInCnt" name="monInCnt" value="<c:out value="${model.monInCnt}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					月支出金额累计:
				</td>	
				<td>
				<input id="monPayAmt" name="monPayAmt" value="<c:out value="${model.monPayAmt}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					月支出笔数累计:
				</td>	
				<td>
				<input id="monPayCnt" name="monPayCnt" value="<c:out value="${model.monPayCnt}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日免签支付金额累计:
				</td>	
				<td>
				<input id="dayNopswdAmt" name="dayNopswdAmt" value="<c:out value="${model.dayNopswdAmt}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日免签支付笔数累计:
				</td>	
				<td>
				<input id="dayNopswdCnt" name="dayNopswdCnt" value="<c:out value="${model.dayNopswdCnt}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日收入金额上限:
				</td>	
				<td>
				<input id="dayInlmtAmt" name="dayInlmtAmt" value="<c:out value="${model.dayInlmtAmt}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日收入笔数上限:
				</td>	
				<td>
				<input id="dayInlmtCnt" name="dayInlmtCnt" value="<c:out value="${model.dayInlmtCnt}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日支出金额上限:
				</td>	
				<td>
				<input id="dayPaylmtAmt" name="dayPaylmtAmt" value="<c:out value="${model.dayPaylmtAmt}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日支出笔数上限:
				</td>	
				<td>
				<input id="dayPaylmtCnt" name="dayPaylmtCnt" value="<c:out value="${model.dayPaylmtCnt}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					月收入金额上限:
				</td>	
				<td>
				<input id="monInlmtAmt" name="monInlmtAmt" value="<c:out value="${model.monInlmtAmt}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					月收入笔数上限:
				</td>	
				<td>
				<input id="monInlmtCnt" name="monInlmtCnt" value="<c:out value="${model.monInlmtCnt}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					月支出金额上限:
				</td>	
				<td>
				<input id="monPaylmtAmt" name="monPaylmtAmt" value="<c:out value="${model.monPaylmtAmt}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					月支出笔数上限:
				</td>	
				<td>
				<input id="monPaylmtCnt" name="monPaylmtCnt" value="<c:out value="${model.monPaylmtCnt}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日免签支付金额上限:
				</td>	
				<td>
				<input id="dayNopswdlmtAmt" name="dayNopswdlmtAmt" value="<c:out value="${model.dayNopswdlmtAmt}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日免签支付笔数上限:
				</td>	
				<td>
				<input id="dayNopswdlmtCnt" name="dayNopswdlmtCnt" value="<c:out value="${model.dayNopswdlmtCnt}"/>" type="text" class="input04"   />
				
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
				<input onClick="WdatePicker()" id="closeDateString" value="<c:out value="${model.closeDateString}"/>" name="closeDateString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					状态 :
				</td>	
				<td>
				<select id="status" name="status">
					<option value="1" <c:if test="${model.status eq '1' }">selected="selected"</c:if>>启用</option>
					<option value="2" <c:if test="${model.status eq '2' }">selected="selected"</c:if>>停用</option>
					<option value="9" <c:if test="${model.status eq '9' }">selected="selected"</c:if>>注销</option>
				</select>
				</td>
			</tr>
			
			
				<input id="version" name="version" value="<c:out value="${model.version}"/>" type="hidden" class="input04"   />
				
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