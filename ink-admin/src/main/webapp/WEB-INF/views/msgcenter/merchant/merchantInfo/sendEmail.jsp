<%@ page import="com.ink.msgcenter.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>发送邮件</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
	<script type="text/javascript" src="${yk}/static/common/maintain.js"></script>
</head>
<body>
<div class="title01">
	<h2>发送邮件</h2>
</div>
	<form id="form1" action="${yk}/msgcenter/merchant/merchantInfo/sendEmail.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<tr>
				<td class="txtr" style="width: 188px">
					商户名称<label style="color: red">*</label>:
				</td>	
				<td>
					<yk:select codeName="name" table="merchant_info" codeValue="sn" id="merctCode" name="merctCode" select="${merctId }" where=" status != 2 " serviceName="msgcenterDubboBaseService"/>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					邮件模版<label style="color: red">*</label>:
				</td>	
				<td>
					<select id="tempId" name="tempId"></select>
				</td>
			</tr>
			<tr>
				<td class="txtr" style="width: 188px">
					业务单号:
				</td>	
				<td>
					<input id="bizId" name="bizId" type="text" class="input04"   />
				</td>
			</tr>
			<tr>
				<td class="txtr" style="width: 188px">
					发送邮箱<label style="color: red">*</label>:
				</td>
				<td>
					<input id="email" name="email" type="text" class="input04"   />
				</td>
			</tr>
			<tr>
				<td class="txtr" style="width: 188px">
					参数:
				</td>
				<td>
					<textarea rows="5" cols="40" id="paramJson" name="paramJson"></textarea>
				</td>
			</tr>

			<tr>
				<td class="txtc" colspan="2">
					<input type="button" class="btn01"  value="预览消息"  onclick="showMessage()"/>
					<input type="button" class="btn01" id="subSave" value="提交" />
					<input type="button" class="btn01"  value="取消" onclick="window.close();"/>
					<input type="reset" class="btn01"  value="重置"/>
				</td>	
			</tr>
		</table>
	</form>
<%--模态弹出窗口--%>
<div id="fade" class="black_overlay"></div>
<div id="ModalDiv" class="white_content" style="overflow: scroll">
	<div id="showMessage"></div>
	<div class="clearfix bbt">
		<a id="restore" class="fl restore" onclick="CloseDiv('ModalDiv','fade')">关　闭</a>
	</div>
</div>
</body>
<script type="text/javascript">

$(function(){
	$("#merctCode").change(function(){
		var optionValue = this.value;
		if (optionValue == ""){
			$("#tempId").empty();
			$("#showMessage").empty();
			$("#paramJson").val("");
			return ;
		}

		var moduleUrl = '${yk}/msgcenter/EmailTemplate/listForMerctCode.do?merctCode='+optionValue;
		executeAjax(moduleUrl,"tempId");
	});

	$("#subSave").click(function(){
		if (validateLengthFrom("merctCode", 1, "请选择商户")
				&&validateLengthFrom("tempId", 1, "请选择邮件模版")
				&& matchFrom("email", /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9])+$/, "发送邮箱:请输入正确的邮箱地址")
		) {
			var url = $("#form1").attr("action");
			var merctCode = $("#merctCode").val();
			var tempId = $("#tempId").val();
			var bizId = $("#bizId").val();
			var email = $("#email").val();
			var paramJson = $("#paramJson").val();
			url += "?merctCode="+merctCode +"&tempId="+tempId+"&bizId="+bizId;
			url += "&email="+email + "&paramJson="+paramJson;
			$.ajax( {
				type : 'POST',
				contentType : 'application/json',
				url : url,
				dataType : 'json',
				success : function(data) {
					if(data.retCode != "RT0000"){
						alert("发送失败，msgId:"+data.msgId+"错误信息：" + data.retMsg);
					}else{
						alert("发送成功");
					}
				},
				error : function() {
					alert("系统出错，请联系管理员")
				}
			});
		}
	});

	$("#tempId").change(function(){
		var tempId = $(this).val();
		if(tempId.length < 1){
			$("#showMessage").empty();
			$("#paramJson").val("");
			return false;
		}
		var url =   '${yk}/msgcenter/EmailTemplate/findEmailTemplate.do?id='+$(this).val();
		$.ajax( {
			type : 'GET',
			contentType : 'application/json',
			url : url,
			dataType : 'json',
			success : function(data) {
				$("#paramJson").val(data.tempParam);
			},
			error : function() {
				alert("系统出错，请联系管理员")
			}
		});
	});
});

	function showMessage(){

		var tempId = $("#tempId").val();
		var merctCode = $("#merctCode").val();
		var showMessage = $("#showMessage");
		if(merctCode.length <1 || tempId.length<1){
			alert("请选选择模版");
			return false;
		}
		var paramJson = $("#paramJson").val();

		var url =   '${yk}/msgcenter/EmailTemplate/findTemplateText.do?tempParam='+paramJson+'&id='+ tempId;
		$.ajax( {
			type : 'POST',
			contentType : 'application/json',
			url : url,
			dataType : 'json',
			success : function(data) {
				showMessage.html("");
				showMessage.append(data.tempContent);
				ShowDiv('ModalDiv','fade');
			},
			error : function() {
				alert("系统出错，请联系管理员")
			}
		});
	}
</script>
</html>