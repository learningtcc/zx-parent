<%@ page import="com.ink.monitor.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>业务监控新增</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
	<script type="text/javascript" src="${yk}/static/common/maintain.js"></script>
</head>
<body>
<div class="title01">
	<h2>业务监控新增</h2>
</div>
	<form id="form1" action="${yk}/monitor/rule/info/save.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<input type="hidden" id="id" name="id" value="<c:out value="${model.id}"/>"/>
		
			
			<tr>	
				<td class="txtr" style="width: 188px">
					平台系统<label style="color: red">*</label>：
				</td>	
				<td>
					<select id="sysCode" name="sysCode" class="select02">

					</select>
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					功能模块<label style="color: red">*</label>：
				</td>	
				<td>
					<select id="moduleCode" name="moduleCode" class="select02"></select>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					业务模块<label style="color: red">*</label>：
				</td>	
				<td>
					<select id="infoCode" name="infoCode" class="select02"></select>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					预警间隔（单位分钟）<label style="color: red">*</label>：
				</td>	
				<td>
				<input id="warnInterval" name="warnInterval" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					预警阀值<label style="color: red">*</label>：
				</td>	
				<td>
				<input id="warnThreshold" name="warnThreshold" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					预警频次<label style="color: red">*</label>：
				</td>	
				<td>
				<input id="warnFrequency" name="warnFrequency" type="text" class="input04"   />
				
				</td>
			</tr>
			<tr>
				<td class="txtc" colspan="2">
					<input type="button" class="btn01" id="subSave" value="提交" />
					<input type="button" class="btn01"  value="取消" onclick="window.close();"/>
					<input type="reset" class="btn01"  value="重置"/>
				</td>	
			</tr>
			<tr>
				<td align="left" style="left: 20px" colspan="2">
					<ul>
						<p></p>
						<li>注：</li>
						<li>1、预警间隔（单位分钟），如15表示15分钟内该模块报警超过阈值未达到频次时需报警，数值为正整数</li>
						<li>2、预警阀值，为0时无阀值限制，数值需大于0</li>
						<li>3、预警频次，频次为-1时表示不限制次数，为0时表示不报警</li>
					</ul>
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

	//ajax获取系统代码
	var systemUrl = '${yk}/monitor/dict/source/listForAjax.do';
	executeAjax(systemUrl,"sysCode");

	//平台系统联动模块代码
	$("#sysCode").change(function(){
		var optionValue = this.value;
		if (optionValue == ""){
			$("#moduleCode").empty();
			return ;
		}

		var moduleUrl = '${yk}/monitor/dict/module/listAjaxForSystemCode.do?systemCode='+optionValue;
		executeAjax(moduleUrl,"moduleCode");
	});

	//模块联动模块代码
	$("#moduleCode").change(function(){
		var optionValue = this.value;
		if (optionValue == ""){
			$("#infoCode").empty();
			return ;
		}

		var infoUrl = '${yk}/monitor/dict/info/listAjaxForModuleCode.do?moduleCode='+optionValue;
		executeAjax(infoUrl,"infoCode");
	});

	$("#subSave").click(function(){
		if(matchFrom("sysCode",/^\w+$/,"系统编码不能为空")
				&& matchFrom("moduleCode",/^\w+$/,"模块编码不能为空")
				&& matchFrom("infoCode",/^\w+$/,"业务编码不能为空")
				&& matchFrom("warnInterval",/^\d+$/,"预警间隔不能为空且必须是数值")
				&& matchFrom("warnThreshold",/^\d+$/,"预警阀值不能为空且必须是数值")
				&& matchFrom("warnFrequency",/^\d+$/,"预警频次不能为空且必须是数值")
		){
			$('#form1').submit();
		}
	});
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
	}else if(data == 0){
		alert("已添加对应业务模块监控，请重新输入");
	}else{
		alert("保存失败！");
	}
}
</script>
</html>

