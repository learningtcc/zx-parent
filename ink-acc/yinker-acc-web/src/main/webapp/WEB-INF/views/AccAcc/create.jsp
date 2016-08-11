 <%@ page import="com.yinker.user.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=AccAcc.TABLE_ALIAS%>新增</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
	<h2>新增<%=AccAcc.TABLE_ALIAS%></h2>
</div>
	<form id="form1" action="${yk}/AccAcc/save.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			
			<tr>	
				<td class="txtr" style="width: 188px">
					子帐户控制表主键：
				</td>	
				<td>
				<input id="accSacId" name="accSacId" type="text" class="input04"   />
				<span class="required">*</span>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					子帐户号：
				</td>	
				<td>
				<input id="sacId" name="sacId" type="text" class="input04"   />
				<span class="required">*</span>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					主帐户控制表主键：
				</td>	
				<td>
				<input id="accPacId" name="accPacId" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					主账号：
				</td>	
				<td>
				<input id="pacId" name="pacId" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					子帐户种类：
				</td>	
				<td>
				<input id="sacType" name="sacType" type="text" class="input04"   />
				<span class="required">*</span>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					币种：
				</td>	
				<td>
				<select id="cur" name="cur" style="width: 290px;">
					<option value="CNY">人民币</option>
				</select>
				</td>
			</tr>
			
			<!-- 
			<tr>	
				<td class="txtr" style="width: 188px">
					昨日余额 日终批处理更新：
				</td>	
				<td>
				<input id="lstBal" name="lstBal" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					当前余额：
				</td>	
				<td>
				<input id="curBal" name="curBal" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					临时余额 日终使用：
				</td>	
				<td>
				<input id="tmpBal" name="tmpBal" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					冻结金额：
				</td>	
				<td>
				<input id="frozenAmt" name="frozenAmt" type="text" class="input04"   />
				
				</td>
			</tr>
			 -->
			<tr>	
				<td class="txtr" style="width: 188px">
					开户日期：
				</td>	
				<td>
				<input onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="openDateString" name="openDateString" size="10"  readonly="readonly" style="width: 290px;"/>
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					销户日期：
				</td>	
				<td>
				<input onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" id="closeDateString" name="closeDateString" size="10"  readonly="readonly" style="width: 290px;"/>
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					状态：
				</td>	
				<td>
				<select id="status" name="status" style="width:290px">
					<option value="1">启用</option>
					<option value="2">停用</option>
					<option value="9">注销</option>
				</select>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					资金账户认证信息：
				</td>	
				<td>
				<input id="accMac" name="accMac" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					删除标识：
				</td>	
				<td>
				<select id="delFlag" name="delFlag" style="width:290px">
					<option value="0">正常</option>
					<option value="1">删除</option>
				</select>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					客户号：
				</td>	
				<td>
				<input id="uid" name="uid" type="text" class="input04"   />
				
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

