
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>账户控制新增</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
	<h2>新增主账户信息</h2>
</div>
	<form id="form1" action="${yk}/user/AccPac/save.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<input type="hidden" id="id" name="id" value="<c:out value="${model.id}"/>"/>
			<tr>	
				<td class="txtr" style="width: 188px">
					主账号：
				</td>	
				<td>
				<input id="pacId" name="pacId" type="text" class="input04"  required="true" />
				<span class="required">*</span>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					商户信息表主键：
				</td>	
				<td>
				<input id="accProdId" name="accProdId" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					产品编号(帐户级别) ：
				</td>	
				<td>
				<select id="prodId" name="prodId">
					<option value="0001" >未实名账户</option>
					<option value="0002" >已实名账户</option>
					<option value="0003" >VIP帐户</option>
					<option value="0004" >公司账户</option>
				</select>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					客户类型 ：
				</td>	
				<td>
				<select id="custType" name="custType">
					<option value="0" >个人</option>
					<option value="1" >单位</option>
				</select>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日收入金额累计：
				</td>	
				<td>
				<input id="dayInAmt" name="dayInAmt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日收入笔数累计：
				</td>	
				<td>
				<input id="dayInCnt" name="dayInCnt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日支出金额累计：
				</td>	
				<td>
				<input id="dayPayAmt" name="dayPayAmt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日支出笔数累计：
				</td>	
				<td>
				<input id="dayPayCnt" name="dayPayCnt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					月收入金额累计：
				</td>	
				<td>
				<input id="monInAmt" name="monInAmt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					月收入笔数累计：
				</td>	
				<td>
				<input id="monInCnt" name="monInCnt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					月支出金额累计：
				</td>	
				<td>
				<input id="monPayAmt" name="monPayAmt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					月支出笔数累计：
				</td>	
				<td>
				<input id="monPayCnt" name="monPayCnt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日免签支付金额累计：
				</td>	
				<td>
				<input id="dayNopswdAmt" name="dayNopswdAmt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日免签支付笔数累计：
				</td>	
				<td>
				<input id="dayNopswdCnt" name="dayNopswdCnt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日收入金额上限：
				</td>	
				<td>
				<input id="dayInlmtAmt" name="dayInlmtAmt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日收入笔数上限：
				</td>	
				<td>
				<input id="dayInlmtCnt" name="dayInlmtCnt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日支出金额上限：
				</td>	
				<td>
				<input id="dayPaylmtAmt" name="dayPaylmtAmt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日支出笔数上限：
				</td>	
				<td>
				<input id="dayPaylmtCnt" name="dayPaylmtCnt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					月收入金额上限：
				</td>	
				<td>
				<input id="monInlmtAmt" name="monInlmtAmt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					月收入笔数上限：
				</td>	
				<td>
				<input id="monInlmtCnt" name="monInlmtCnt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					月支出金额上限：
				</td>	
				<td>
				<input id="monPaylmtAmt" name="monPaylmtAmt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					月支出笔数上限：
				</td>	
				<td>
				<input id="monPaylmtCnt" name="monPaylmtCnt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日免签支付金额上限：
				</td>	
				<td>
				<input id="dayNopswdlmtAmt" name="dayNopswdlmtAmt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日免签支付笔数上限：
				</td>	
				<td>
				<input id="dayNopswdlmtCnt" name="dayNopswdlmtCnt" type="text" class="input04"   />
				
				</td>
			</tr>
			<tr>	
				<td class="txtr" style="width: 188px">
					开户日期:
				</td>	
				<td>
				<input onClick="WdatePicker()" id="openDateString"  name="openDateString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					销户日期:
				</td>	
				<td>
				<input onClick="WdatePicker()" id="closeDateString"  name="closeDateString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			<tr>	
				<td class="txtr" style="width: 188px">
					状态 ：
				</td>	
				<td>
				<input id="status" name="status" type="text" class="input04"   />
				<select id="status" name="status">
					<option value="1" >启用</option>
					<option value="2" >停用</option>
					<option value="9" >注销</option>
				</select>
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

