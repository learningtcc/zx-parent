<%@ page import="com.ink.msgcenter.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>商户信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
	<h2>商户详情</h2>
</div>
	<input id="emailNotify" name="emailNotify" value="<c:out value="${model.emailNotify}"/>" type="hidden"   />
	<input id="smsNotify" name="smsNotify" value="<c:out value="${model.smsNotify}"/>" type="hidden"   />

	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">商户名称:</td>	
			<td><c:out value="${model.name}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">商户编号:</td>	
			<td><c:out value="${model.sn}"/></td>
		</tr>


		<tr>	
			<td class="txtr" style="width: 188px">通道类型:</td>	
			<td>
				<yk:constantConvert htmlTag="text" dataTag="channelType" paramValue="${model.channelType}" paramSplit="," emptyTip="未知" sysTag="msgcenter"/>
			</td>
		</tr>

		<div id="smsDiv" name="smsDiv">
			<tr name="smsTr1">
				<td class="txtr" style="width: 188px">短信通知方式：</td>
				<td>
					<label id="smsNotifyType"></label>
				</td>
			</tr>
			<tr name="smsTr2">
				<td class="txtr" style="width: 188px">短信状态报告url：</td>
				<td>
					<label id="smsReportUrl"></label>
				</td>
			</tr>
			<tr name="smsTr3">
				<td class="txtr" style="width: 188px">短信上行通知url：</td>
				<td>
					<label id="smsUpUrl"></label>
				</td>
			</tr>
		</div>
		<div id="emailDiv" name="emailDiv" >
			<tr name="emailTr1">
				<td class="txtr" style="width: 188px">邮件通知方式：</td>
				<td>
					<label id="emailNotifyType"></label>
				</td>
			</tr>
			<tr name="emailTr2">
				<td class="txtr" style="width: 188px">邮件发送通知url：</td>
				<td>
					<label id="emailReportUrl"></label>
				</td>
			</tr>
		</div>
		<tr>
			<td class="txtr" style="width: 188px">商户状态:</td>
			<td>
				<yk:constantConvert htmlTag="text" dataTag="del_status" paramValue="${model.status}" emptyTip="未知" sysTag="msgcenter"/>
			</td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">商户描述:</td>
			<td><c:out value="${model.remark}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">创建人ID:</td>	
			<td><c:out value="${model.creatorId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">创建时间:</td>	
			<td><c:out value="${model.createTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">创建人姓名:</td>	
			<td><c:out value="${model.creatorName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">更新人ID:</td>	
			<td><c:out value="${model.updatorId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">更新人姓名:</td>	
			<td><c:out value="${model.updatorName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">更新时间:</td>	
			<td><c:out value="${model.updateTimeString}"/></td>
		</tr>
	</table>
	</body>
<script type="text/javascript">

	$(function(){

		$("tr[name^='smsTr']").hide();
		$("tr[name*='emailTr']").hide();
		var channelType = '${model.channelType}';
		if(channelType.indexOf("1") != -1){
			isSms = true;
			$("tr[name^='smsTr']").show();
		}
		if(channelType.indexOf("2") != -1){
			isEmail = true;
			$("tr[name*='emailTr']").show();
		}

		var emailNotify =  $("#emailNotify").val();
		if(emailNotify.length >1){
			var emailJson = JSON.parse(emailNotify);
			$("#emailNotifyType").text(emailJson.notifyType);
			$("#emailReportUrl").text(emailJson.httpInfo.reportUrl);

		}

		var smsNotify =  $("#smsNotify").val();
		if(smsNotify.length >1){
			isSms = true;
			var smsJson = JSON.parse(smsNotify);
			$("#smsNotifyType").text(smsJson.notifyType);
			$("#smsReportUrl").text(smsJson.httpInfo.reportUrl);
			$("#smsUpUrl").text(emailJson.httpInfo.upUrl);
		}

	});
</script>
</html>