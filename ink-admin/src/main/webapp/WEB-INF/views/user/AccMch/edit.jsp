
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>商户编辑</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
	<h2>修改商户</h2>
</div>
	<form id="form1" action="${yk}/user/AccMch/update.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<input type="hidden" id="id" name="id" value="<c:out value="${model.id}"/>"/>
			
				<input id="createTimeString" value="<c:out value="${model.createTimeString}"/>" name="createTimeString" size="10"  type="hidden"/>
				
			<!-- 
			<tr>	
				<td class="txtr" style="width: 188px">
					最后修改时间:
				</td>	
				<td>
				<input id="lastUpdateTimeString" value="<c:out value="${model.lastUpdateTimeString}"/>" name="lastUpdateTimeString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			 -->
			<tr>	
				<td class="txtr" style="width: 188px">
					商户编号:
				</td>	
				<td>
				<input id="mchId" name="mchId" value="<c:out value="${model.mchId}"/>" type="number" class="input04"  required="true" />
				<span class="required">*</span>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					客户号:
				</td>	
				<td>
				<input id="custId" name="custId" value="<c:out value="${model.custId}"/>" type="number" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					商户名称:
				</td>	
				<td>
				<input id="mchName" name="mchName" value="<c:out value="${model.mchName}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					商户类别表主键:
				</td>	
				<td>
				<input id="accMccId" name="accMccId" value="<c:out value="${model.accMccId}"/>" type="number" class="input04"   />
				
				</td>
			</tr>
			<!--  
			<tr>	
				<td class="txtr" style="width: 188px">
					商户类别:
				</td>	
				<td>
				<input id="mcc" name="mcc" value="<c:out value="${model.mcc}"/>" type="text" class="input04"   />
				
				</td>
			</tr>-->
			<input id="mcc" name="mcc" value="<c:out value="${model.mcc}"/>" type="hidden" class="input04"   />
			<tr>	
				<td class="txtr" style="width: 188px">
					商户性质 :
				</td>	
				<td>
				<select id="mchNature" name="mchNature">
					<option value="1" <c:if test="${model.mchNature == '1'}">selected="selected"</c:if>>国营</option>
					<option value="2" <c:if test="${model.mchNature == '2'}">selected="selected"</c:if>>股份制</option>
					<option value="3" <c:if test="${model.mchNature == '3'}">selected="selected"</c:if>>集体</option>
					<option value="4" <c:if test="${model.mchNature == '4'}">selected="selected"</c:if>>中外合资、合作</option>
					<option value="5" <c:if test="${model.mchNature == '5'}">selected="selected"</c:if>>外商独资</option>
					<option value="6" <c:if test="${model.mchNature == '6'}">selected="selected"</c:if>>私营合伙</option>
					<option value="7" <c:if test="${model.mchNature == '7'}">selected="selected"</c:if>>私营独资</option>
					<option value="8" <c:if test="${model.mchNature == '8'}">selected="selected"</c:if>>个体</option>
					<option value="9" <c:if test="${model.mchNature == '9'}">selected="selected"</c:if>>其他</option>
				</select>
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					组织机构代码:
				</td>	
				<td>
				<input id="orgCode" name="orgCode" value="<c:out value="${model.orgCode}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					注册登记号:
				</td>	
				<td>
				<input id="regNo" name="regNo" value="<c:out value="${model.regNo}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					注册资本:
				</td>	
				<td>
				<input id="regAmt" name="regAmt" value="<c:out value="${model.regAmt}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					注册地址:
				</td>	
				<td>
				<input id="regAddress" name="regAddress" value="<c:out value="${model.regAddress}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					营业地址:
				</td>	
				<td>
				<input id="busAddress" name="busAddress" value="<c:out value="${model.busAddress}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					业务联系人:
				</td>	
				<td>
				<input id="contact" name="contact" value="<c:out value="${model.contact}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					联系移动电话:
				</td>	
				<td>
				<input id="mblNo" name="mblNo" value="<c:out value="${model.mblNo}"/>" type="number" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					联系固定电话:
				</td>	
				<td>
				<input id="telNo" name="telNo" value="<c:out value="${model.telNo}"/>" type="number" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					联系邮箱:
				</td>	
				<td>
				<input id="email" name="email" value="<c:out value="${model.email}"/>" type="text" class="input04"   />
				
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
					<option value="3" <c:if test="${model.status == '3'}">selected="selected"</c:if>>待审核</option>
					<option value="4" <c:if test="${model.status == '4'}">selected="selected"</c:if>>审核拒绝</option>
					<option value="9" <c:if test="${model.status == '9'}">selected="selected"</c:if>>注销</option>
				</select>
				</td>
			</tr>
			
			<input id="delFlag" name="delFlag" type="hidden" value="${model.delFlag }"></input>
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
</script>
</html>