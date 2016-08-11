<%@ page import="com.ink.cert.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>证书新增</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
	<script type="text/javascript" src="${yk}/static/common/maintain.js"></script>
</head>
<body>
<div class="title01">
	<h2>新增证书</h2>
</div>
	<form id="form1" action="${yk}/cert/certInfo/save.do" method="post" enctype="multipart/form-data">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<tr>
				<td class="txtr" style="width: 188px">
					商户号<label style="color: red">*</label>：
				</td>	
				<td>
				<input id="merchatCode" name="merchatCode" type="text" class="input04"   />
				</td>
			</tr>
			<tr >
				<td class="txtr" style="width: 188px">
					证书编号<label style="color: red">*</label>：
				</td>
				<td>
					<input id="certCode" name="certCode" type="text" class="input04"   />
				</td>
			</tr>
			<tr >
				<td class="txtr" style="width: 188px">
					证书名称<label style="color: red">*</label>：
				</td>
				<td>
					<input id="certName" name="certName" type="text" class="input04"   />
				</td>
			</tr>
			<tr>
				<td class="txtr" style="width: 188px">
					加解密方式<label style="color: red">*</label>:
				</td>	
				<td>
					<yk:constantConvert htmlTag="select" dataTag="endecryType" paramValue="" name="endecryType" isAutoItem="false" emptyTip="0" sysTag="cert"/>
				</td>
			</tr>
			<tr name="certTr" >
				<td class="txtr" style="width: 188px">
					证书类型<label style="color: red">*</label>：
				</td>	
				<td>
					<yk:constantConvert htmlTag="select" dataTag="certType" paramValue="" name="certType" sysTag="cert"/>
				</td>
			</tr>
			<tr name="certTr" >
				<td class="txtr" style="width: 188px">
					证书文件<label style="color: red">*</label>：
				</td>
				<td>
					<input type="file" name="certFile" id="certFile"/>
				</td>
			</tr>
			<tr name="secretTr" style="display:none">
				<td class="txtr" style="width: 188px">
					算法类型<label style="color: red">*</label>：
				</td>	
				<td>
					<yk:constantConvert htmlTag="select" dataTag="arithmeticType" paramValue="" name="arithmeticType" sysTag="cert"/>
				</td>
			</tr>
			<tr>
				<td class="txtr" style="width: 188px">
					密钥：
				</td>
				<td>
					<input id="secretKey" name="secretKey" type="text" class="input04"   />
				</td>
			</tr>
			<tr>
				<td class="txtc" colspan="2">
					<input type="button" class="btn01" id="subSave"  value="提交"/>
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
	       //  beforeSubmit:   validateForm,   // pre-submit callback 表单提交前被调用的回调函数
	         success:        callBack   // post-submit callback   表单提交成功后被调用的回调函数
	     }; 
	$('#form1').ajaxForm(options);

	$("#subSave").click(function(){
		if(validateForm()){
			$('#form1').submit();
		}

	});

	$("#endecryType").change(function(){
		$("tr[name=certTr]").toggle();
		$("tr[name=secretTr]").toggle();
	});

});

window.onbeforeunload=function(){
	window.opener.document.queryForm.submit();
}

function validateForm(){
	if(validateLengthFrom("merchatCode",1,"请输入商户编号")
		&&validateLengthFrom("endecryType",1,"请选择加密方式")
		&&validateLengthFrom("certCode",1,"请输入证书编号")
		&&validateLengthFrom("certName",1,"请输入证书名称")){
		var endecryType = $("#endecryType").val();
		if(endecryType == '0'){
			if(validateLengthFrom("certType",1,"请选择证书类型")
					&&validateLengthFrom("certFile",1,"请选择证书文件")){
					var certType = $("#certType").val();
					var certFile = $("#certFile").val();
					if(certFile.lastIndexOf(certType) < 0){
						alert("请根据证书类型上传正确的证书文件");
						return false;
					}
					return true;
			}
		}else if(endecryType == '1'){
			if(validateLengthFrom("arithmeticType",1,"请选择算法类型")){
				return true;
			}
		}
	}

	return false;
}
function callBack(data){
	if(data==1)	{
		alert("保存成功！");
		window.close();
	}else if(data==2)	{
		alert("已添加对应的商户编号及证书编号，请检查！");
//		window.close();
	} else{
		alert("保存失败！");
	}
}
</script>
</html>

