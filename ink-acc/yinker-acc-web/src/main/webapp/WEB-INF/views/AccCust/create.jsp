<%@ page import="com.yinker.user.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>客户新增</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
	<h2>新增客户</h2>
</div>
	<form id="form1" action="${yk}/AccCust/save.do" method="post">
	
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
					商户编号 ：
				</td>	
				<td>
				<input id="mchId" name="mchId" type="number" class="input04"   />
				<span class="required">*</span>
				<span id="mchIdSpan" style="color: red"></span>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					第三方客户号：
				</td>	
				<td>
				<input id="custId" name="custId" type="number" class="input04"   />
				<span id="custIdSpan" style="color: red"></span>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					客户类型 ：
				</td>	
				<td>
				<select id="custType" name="custType" style="width:172px;">
					<option value="0">个人</option>
					<option value="1">单位</option>
				</select>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					证件类型 ：
				</td>	
				<td>
				<select id="idType" name="idType">
					<option value="01">身份证</option>
					<option value="02">户口本</option>
					<option value="03">军人身份证</option>
					<option value="04">港、澳居民往来内地通行证</option>
					<option value="05">台湾居民来往大陆通行证 </option>
					<option value="06">护照</option>
					<option value="07">工商营业执照</option>
					<option value="08">法人证书</option>
					<option value="09">组织机构代码证</option>
					<option value="10">其他</option>
				</select>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					证件号码：
				</td>	
				<td>
				<input id="idNo" name="idNo" type="text" class="input04"   />*
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					主账号：
				</td>	
				<td>
				<input id="pacId" name="pacId" type="number" class="input04"   />*
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					姓名：
				</td>	
				<td>
				<input id="custName" name="custName" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					性别：
				</td>	
				<td>
				<select id="sex" name="sex" style="width: 290px;">
					<option value="M">男</option>
					<option value="F">女</option>
					<option value="O">未知</option>
				</select>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					出生日期：
				</td>	
				<td>
				<input onClick="WdatePicker()" id="birthdayString" name="birthdayString" size="10"  readonly="readonly"/>
				
				</td>
			</tr> 
			
			<tr>	
				<td class="txtr" style="width: 188px">
					联系移动电话：
				</td>	
				<td>
				<input id="mblNo" name="mblNo" type="number" class="input04"   />*
				
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
					邮编：
				</td>	
				<td>
				<input id="zipcode" name="zipcode" type="number" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					联系地址：
				</td>	
				<td>
				<input id="address" name="address" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					状态 ：
				</td>	
				<td>
				<select id="status" name="status" style="width:172px">
					<option value="1"  >启用</option>
					<option value="2"  >停用</option>
					<option value="9"  >注销</option>
				</select>
				
				</td>
			</tr>
			
			<input id="delFlag" name="delFlag" value="0" type="hidden"></input>
			<input id="uid" name="uid" value="0" type="hidden"></input>
			<input id="thirdMemberNo" name="thirdMemberNo" value="0" type="hidden"></input>
			<!-- 
			<tr>	
				<td class="txtr" style="width: 188px">
					删除标识：
				</td>	
				<td>
				<select id="delFlag" name="delFlag" style="width:172px">
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
				<input id="uid" name="uid" type="number" class="input04"  required="true" />
				<span class="required">*</span>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					民生会员号：
				</td>	
				<td>
				<input id="thirdMemberNo" name="thirdMemberNo" type="text" class="input04"   />
				
				</td>
			</tr> -->
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
	
	var idType = $("#idType").val();
	if (idType == '01') {
		var idTypeReg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
		if (!idTypeReg.test($('#idNo').val())) {
			alert("身份证输入不合法！");
			$('#idNo').focus();
			return false;
		}
	}
	if($.trim($("#email").val()).length > 0){
		var emailReg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		if (!emailReg.test($("#email").val())) {
			alert("邮箱格式不正确!");
			$("#email").focus();
			return false;
		}
	}
	var mchId = $("#uid").val();
	if($.trim(mchId) == ""){
		alert('客户号不能为空!');
		$('#uid').focus();
		return false;
	}
	
	var pacId = $("#pacId").val();
	if(pacId.length < 1){
		alert("主账号不能为空!");
		$('#pacId').focus();
		return false;
	}
	
	return true;
}
	function callBack(data) {
		if (data == 1) {
			alert("保存成功！");
			window.close();
		} else {
			alert("保存失败！");
		}
	}
	
	$("#custId").change(function(){
		$("#sacTypeSpan").text("");
		if($("#mchId").val().length > 1){
			$.post("${yk}/AccCust/checkExist.do",{mchId : $("#mchId").val(), custId : $("#custId").val()},function(data){
				console.info(data);
				if(data == "1"){
					alert("该商户的此客户号已存在");
					$("#custIdSpan").text("客户号已存在,请重新填写!");
				}
			});
		}
	});
	
	$("#mchId").change(function(){
		$("#mchIdSpan").text("");
		if($("#custId").val().length > 1){
			$.post("${yk}/AccCust/checkExist.do",{mchId : $("#mchId").val(), custId : $("#custId").val()},function(data){
				console.info(data);
				if(data == "1"){
					alert("该商户的此客户号已存在");
					$("#mchIdSpan").text("客户号已存在,请重新填写!");
				}
			});
		}
	});	
</script>
</html>

