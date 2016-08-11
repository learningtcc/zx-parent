<%@ page import="com.yinker.user.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>交易代码编辑</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
	<h2>修改交易代码</h2>
</div>
	<form id="form1" action="${yk}/TnsTxn/update.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<input type="hidden" id="id" name="id" value="<c:out value="${model.id}"/>"/>
		
			
			
				<input onClick="WdatePicker()" id="createTimeString" value="<c:out value="${model.createTimeString}"/>" name="createTimeString" size="10"  type="hidden"/>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					交易代码:
				</td>	
				<td>
				<input id="txnCode" name="txnCode" value="<c:out value="${model.txnCode}"/>" type="text" class="input04"  required="true" />
				<span class="required">*</span>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					交易名称:
				</td>	
				<td>
				<input id="txnName" name="txnName" value="<c:out value="${model.txnName}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					交易性质 :
				</td>	
				<td>
				
				<select id="txnNature" name="txnNature">
					<option value="1" <c:if test="${model.txnNature eq '1'}">selected="selected"</c:if>>充值</option>
					<option value="2" <c:if test="${model.txnNature eq '2'}">selected="selected"</c:if>>消费</option>
					<option value="3" <c:if test="${model.txnNature eq '3'}">selected="selected"</c:if>>资金转入</option>
					<option value="4" <c:if test="${model.txnNature eq '4'}">selected="selected"</c:if>>资金转出</option>
					<option value="5" <c:if test="${model.txnNature eq '5'}">selected="selected"</c:if>>汇入</option>
					<option value="6" <c:if test="${model.txnNature eq '6'}">selected="selected"</c:if>>汇出</option>
					<option value="7" <c:if test="${model.txnNature eq '7'}">selected="selected"</c:if>>手续费</option>
					<option value="8" <c:if test="${model.txnNature eq '8'}">selected="selected"</c:if>>取现</option>
					<option value="9" <c:if test="${model.txnNature eq '9'}">selected="selected"</c:if>>其他</option>
				</select>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					运行级别:
				</td>	
				<td>
				<select id="runLv" name="runLv">
					<option value="1" <c:if test="${model.runLv eq '1'}">selected="selected"</c:if>>联机</option>
					<option value="2" <c:if test="${model.runLv eq '2'}">selected="selected"</c:if>>批处理</option>
					<option value="3" <c:if test="${model.runLv eq '3'}">selected="selected"</c:if>>联机/批处理</option>
				</select>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					交易金额方向 :
				</td>	
				<td>
				<select id="dir" name="dir">
					<option value="D" <c:if test="${model.dir eq 'D'}">selected="selected"</c:if>>借</option>
					<option value="C" <c:if test="${model.dir eq 'C'}">selected="selected"</c:if>>贷</option>
					<option value="O" <c:if test="${model.dir eq 'O'}">selected="selected"</c:if>>其他</option>
				</select>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					冲正标识 :
				</td>	
				<td>
				<select id="revAllowFlg" name="revAllowFlg">
					<option value="0" <c:if test="${model.revAllowFlg eq '0'}">selected="selected"</c:if>>不允许</option>
					<option value="1" <c:if test="${model.revAllowFlg eq '1'}">selected="selected"</c:if>>允许</option>
				</select>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					手续费收取标识:
				</td>	
				<td>
				<select id="feeFlg" name="feeFlg">
					<option value="0" <c:if test="${model.feeFlg eq '0'}">selected="selected"</c:if>>不允许</option>
					<option value="1" <c:if test="${model.feeFlg eq '1'}">selected="selected"</c:if>>允许</option>
				</select>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					最小交易金额 :
				</td>	
				<td>
				<input id="minAmt" name="minAmt" value="<c:out value="${model.minAmt}"/>" type="text" class="input04"  required="true" />
				<span class="required">*</span>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					最大交易金额 :
				</td>	
				<td>
				<input id="maxAmt" name="maxAmt" value="<c:out value="${model.maxAmt}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					允许的交易渠道 :
				</td>	
				<td>
				<input id="channel" name="channel" value="<c:out value="${model.channel}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					默认子帐户顺序 :
				</td>	
				<td>
				<input id="accOrder" name="accOrder" value="<c:out value="${model.accOrder}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					允许的账户状态:
				</td>	
				<td>
				<input id="accStatus" name="accStatus" value="<c:out value="${model.accStatus}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					状态 :
				</td>	
				<td>
				<select id="status" name="status">
					<option value="1" <c:if test="${model.status eq '1'}">selected="selected"</c:if>>启用</option>
					<option value="2" <c:if test="${model.status eq '2'}">selected="selected"</c:if>>停用</option>
					<option value="9" <c:if test="${model.status eq '3'}">selected="selected"</c:if>>注销</option>
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