<%@ page import="com.yinker.user.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=AccTypeMchLimit.TABLE_ALIAS%>编辑</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
	<h2>修改<%=AccTypeMchLimit.TABLE_ALIAS%></h2>
</div>
	<form id="form1" action="${yk}/AccTypeMchLimit/update.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<input type="hidden" id="id" name="id" value="<c:out value="${model.id}"/>"/>
		
			
			<tr>	
				<td class="txtr" style="width: 188px">
					子帐户种类:
				</td>	
				<td>
				<yk:select codeName="sac_name" table="acc_sac_type" codeValue="sac_type" id="sacType" name="sacType" select="${model.sacType }" where=" status = 1"></yk:select>
				<span class="required">*</span>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					商户:
				</td>	
				<td>
				<yk:select codeName="mch_name" table="acc_mch" codeValue="mch_id" id="mchId" name="mchId" select="${model.mchId }" where=" del_flag != 1"></yk:select>
				<span class="required">*</span>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日提现金额上限:
				</td>	
				<td>
				<input id="dayCashlmtAmt" name="dayCashlmtAmt" value="<c:out value="${model.dayCashlmtAmt}"/>" type="number" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					日提现笔数上限:
				</td>	
				<td>
				<input id="dayCashlmtCnt" name="dayCashlmtCnt" value="<c:out value="${model.dayCashlmtCnt}"/>" type="number" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					状态 :
				</td>	
				<td>
				<select id="status" name="status" style="width:172px">
					<option value="1" <c:if test="${model.status == '1'}">selected="selected"</c:if>>启用</option>
					<option value="2" <c:if test="${model.status == '2'}">selected="selected"</c:if>>停用</option>
					<option value="9" <c:if test="${model.status == '9'}">selected="selected"</c:if>>注销</option>
				</select>
				</td>
			</tr>
			
			<input id="delFlag" name="delFlag" type="hidden" value="${model.delFlag }"></input>
			
			<input id="version" name="version" value="<c:out value="${model.version}"/>" type="hidden" class="input04"   />
			<input onClick="WdatePicker()" id="createTimeString" value="<c:out value="${model.createTimeString}"/>" name="createTimeString" size="10"  type="hidden"/>
				
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
	
	$("#sacType").change(function(){
		$("#sacTypeSpan").text("");
		if($("#mchId").val().length > 1){
			$.post("${yk}/AccTypeMchLimit/checkExist.do",{mchId : $("#mchId").val(), sacType : $("#sacType").val()},function(data){
				console.info(data);
				if(data == "1"){
					alert("该商户的子账户种类已存在");
					$("#sacTypeSpan").text("该商户的子账户种类已存在,重新选择!");
				}
			});
		}
	});
	
	$("#mchId").change(function(){
		$("#mchIdSpan").text("");
		if($("#sacType").val().length > 1){
			$.post("${yk}/AccTypeMchLimit/checkExist.do",{mchId : $("#mchId").val(), sacType : $("#sacType").val()},function(data){
				console.info(data);
				if(data == "1"){
					alert("该商户的子账户种类已存在");
					$("#mchIdSpan").text("该商户的子账户种类已存在,重新选择!");
				}
			});
		}
	});
});

window.onbeforeunload=function(){
	window.opener.document.queryForm.submit();
}

function validateForm(){
	var sacType = $("#sacType").val();
	if(sacType.length<1){
		alert("子账户类型不能为空!");
		return false;
	}
	
	var mchId = $("#mchId").val();
	if(mchId.length < 1){
		alert("商户不能为空!");
		return false;
	}
	
	var dayCashlmtAmt = $("#dayCashlmtAmt").val();
	if(dayCashlmtAmt.length < 1){
		$("#dayCashlmtAmt").val(0);
	}
	
	var dayCashlmtCnt = $("#dayCashlmtCnt").val();
	if(dayCashlmtCnt.length < 1){
		$("#dayCashlmtCnt").val(0);
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