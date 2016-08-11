
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>子账户类型新增</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
	<h2>新增子账户类型</h2>
</div>
	<form id="form1" action="${yk}/user/AccSacType/save.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<!-- 
			<tr>	
				<td class="txtr" style="width: 188px">
					创建时间：
				</td>	
				<td>
				<input onClick="WdatePicker()" id="createTimeString" name="createTimeString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					最后修改时间：
				</td>	
				<td>
				<input onClick="WdatePicker()" id="lastUpdateTimeString" name="lastUpdateTimeString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			 -->
			<tr>	
				<td class="txtr" style="width: 188px">
					子帐户种类：
				</td>	
				<td>
				<input id="sacType" name="sacType" type="text" class="input04"  required="true" />
				<span class="required">*</span>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					子帐户名称：
				</td>	
				<td>
				<input id="sacName" name="sacName" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					币种：
				</td>	
				<td>
				<select id="cur" name="cur">
					<option value="CNY">CNY</option>
				</select>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日充值金额上限：
				</td>	
				<td>
				<input id="dayVarchargelmtAmt" name="dayVarchargelmtAmt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日充值笔数上限：
				</td>	
				<td>
				<input id="dayVarchargelmtCnt" name="dayVarchargelmtCnt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日消费金额上限：
				</td>	
				<td>
				<input id="dayPaylmtAmt" name="dayPaylmtAmt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日消费笔数上限：
				</td>	
				<td>
				<input id="dayPaylmtCnt" name="dayPaylmtCnt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日转入金额上限：
				</td>	
				<td>
				<input id="dayInlmtAmt" name="dayInlmtAmt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日转入笔数上限：
				</td>	
				<td>
				<input id="dayInlmtCnt" name="dayInlmtCnt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日转出金额上限：
				</td>	
				<td>
				<input id="dayOutlmtAmt" name="dayOutlmtAmt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日转出笔数上限：
				</td>	
				<td>
				<input id="dayOutlmtCnt" name="dayOutlmtCnt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日提现金额上限：
				</td>	
				<td>
				<input id="dayCashlmtAmt" name="dayCashlmtAmt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日提现笔数上限：
				</td>	
				<td>
				<input id="dayCashlmtCnt" name="dayCashlmtCnt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					月充值金额上限：
				</td>	
				<td>
				<input id="monVarchargelmtAmt" name="monVarchargelmtAmt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					月充值笔数上限：
				</td>	
				<td>
				<input id="monVarchargelmtCnt" name="monVarchargelmtCnt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					月消费金额上限：
				</td>	
				<td>
				<input id="monPaylmtAmt" name="monPaylmtAmt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					月消费笔数上限：
				</td>	
				<td>
				<input id="monPaylmtCnt" name="monPaylmtCnt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					月转入金额上限：
				</td>	
				<td>
				<input id="monInlmtAmt" name="monInlmtAmt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					月转入笔数上限：
				</td>	
				<td>
				<input id="monInlmtCnt" name="monInlmtCnt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					月转出金额上限：
				</td>	
				<td>
				<input id="monOutlmtAmt" name="monOutlmtAmt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					月转出笔数上限：
				</td>	
				<td>
				<input id="monOutlmtCnt" name="monOutlmtCnt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					月提现金额上限：
				</td>	
				<td>
				<input id="monCashlmtAmt" name="monCashlmtAmt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					月提现笔数上限：
				</td>	
				<td>
				<input id="monCashlmtCnt" name="monCashlmtCnt" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					状态 ：
				</td>	
				<td>
				<select id="status" name="status" style="width:172px">
					<option value="1">启用</option>
					<option value="2">停用</option>
					<option value="9">注销</option>
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
	var sacType = $("#sacType").val();
	if($.trim(sacType).length != 4){
		alert('子帐户种类长度错误!');
		$('#sacType').focus();
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

