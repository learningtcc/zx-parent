
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>商户新增</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
	<h2>新增商户</h2>
</div>
	<form id="form1" action="${yk}/user/AccMch/save.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<input type="hidden" id="id" name="id" value="<c:out value="${model.id}"/>"/>
		
			
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
					商户编号：
				</td>	
				<td>
				<input id="mchId" name="mchId" type="number" class="input04"  required="true" />
				<span class="required">*</span>
				<span id="mchIdSpan" style="color: red"></span>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					客户号：
				</td>	
				<td>
				<input id="custId" name="custId" type="number" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					商户名称：
				</td>	
				<td>
				<input id="mchName" name="mchName" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					商户类别表主键：
				</td>	
				<td>
				<input id="accMccId" name="accMccId" type="number" class="input04"   />
				
				</td>
			</tr>
			<!--
			<tr>	
				<td class="txtr" style="width: 188px">
					商户类别：
				</td>	
				<td>
				<input id="mcc" name="mcc" type="text" class="input04"   />
				
				</td>
			</tr>  -->
			
			<tr>	
				<td class="txtr" style="width: 188px">
					商户性质 ：
				</td>	
				<td>
				<select id="mchNature" name="mchNature">
					<option value="1">国营</option>
					<option value="2">股份制</option>
					<option value="3">集体</option>
					<option value="4">中外合资、合作</option>
					<option value="5">外商独资</option>
					<option value="6">私营合伙</option>
					<option value="7">私营独资</option>
					<option value="8">个体</option>
					<option value="9">其他</option>
				</select>
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					组织机构代码：
				</td>	
				<td>
				<input id="orgCode" name="orgCode" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					注册登记号：
				</td>	
				<td>
				<input id="regNo" name="regNo" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					注册资本：
				</td>	
				<td>
				<input id="regAmt" name="regAmt" type="number" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					注册地址：
				</td>	
				<td>
				<input id="regAddress" name="regAddress" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					营业地址：
				</td>	
				<td>
				<input id="busAddress" name="busAddress" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					业务联系人：
				</td>	
				<td>
				<input id="contact" name="contact" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					联系移动电话：
				</td>	
				<td>
				<input id="mblNo" name="mblNo" type="number" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					联系固定电话：
				</td>	
				<td>
				<input id="telNo" name="telNo" type="number" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					联系邮箱：
				</td>	
				<td>
				<input id="email" name="email" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					状态：
				</td>	
				<td>
				<select id="status" name="status" style="width:172px">
					<option value="1">启用</option>
					<option value="2">停用</option>
					<option value="3">待审核</option>
					<option value="4">审核拒绝</option>
					<option value="9">注销</option>
				</select>
				</td>
			</tr>
			<input id="delFlag" name="delFlag" value="0" type="hidden"></input>
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
	var mchId = $("#mchId").val();
	if($.trim(mchId) == ""){
		alert('商户编号不能为空!');
		$('#mchId').focus();
		return false;
	}
	
	var mblReg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
	if(!mblReg.test($("#mblNo").val())) 
	{ 
	    alert('请输入有效的手机号码！');
	    $('#mblNo').focus();
	    return false; 
	}
	
	if($.trim($("#email").val()).length > 0){
		var emailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		if (!emailReg.test($("#email").val())) {
			alert("邮箱格式不正确!");
			$("#email").focus();
			return false;
		}
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

$("#mchId").change(function(){
	$("#mchIdSpan").text("");
	if($("#mchId").val().length > 1){
		$.post("${yk}/user/AccMch/checkExist.do",{mchId : $("#mchId").val()},function(data){
			console.info(data);
			if(data == "1"){
				alert("该商户编号已存在");
				$("#mchIdSpan").text("该商户编号已存在,请重新填写!");
			}
		});
	}
});
</script>
</html>

