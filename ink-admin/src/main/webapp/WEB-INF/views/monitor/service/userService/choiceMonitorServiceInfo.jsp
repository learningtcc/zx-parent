<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<%@ include file="/gen-commons/taglibs.jsp" %>
	<link rel="stylesheet" href="${yk}/static/ztree/style/zTreeStyle.css" type="text/css"></link>
	<script type="text/javascript" src="${yk}/static/ztree/jquery.ztree.all-3.1.js"></script>
	<script type="text/javascript" src="${yk}/static/common/maintain.js"></script>
	<title>人员服务监控维护</title>

<SCRIPT type="text/javascript">


var setting = {
	check: {
		enable: true
	},
	data: {
		simpleData: {
			enable:true,
			idKey: "id",
			pIdKey: "pid",
			rootPId: "",
			showLine : true,                  //是否显示节点间的连线
			checkable : true                  //每个节点上是否显示 CheckBox
		}
	}
};

$(document).ready(function(){
	var options = {
		beforeSubmit:   validateForm,   // pre-submit callback 表单提交前被调用的回调函数
		success:        callBack   // post-submit callback   表单提交成功后被调用的回调函数
	};
	$('#form1').ajaxForm(options);

	$.post("${yk}/monitor/service/userService/findMonitorServiceTree.do?userId="+<c:out value="${userId}"/>,function(data){
		if(data != null){
//			alert(data);
			var zNodes = eval(data);
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);

		}
	});

	$("#subSave").click(function(){
		onCheck();
//		var serviceIds = $("#serviceIds").val();
//		if(serviceIds.length < 1 ){
//			alert("请选择监控的服务模块");
//			return false;
//		}
		$('#form1').submit();
	});
});
function onCheck(){

	var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
	var nodes=treeObj.getCheckedNodes(true);
	var serviceIds = "";
	for(var i=0;i< nodes.length;i++){
		var checkType = nodes[i].checkType;
		if("1"==checkType){
			serviceIds += ";" + nodes[i].id;
		}
	}
	$("#serviceIds").val(serviceIds);
}

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
</SCRIPT>

</head>

<body style="background-color: #eef9ff; margin-left:0px; margin-top:0px">
<form action="${yk}/monitor/service/userService/save.do" id="form1" name="form1" method="post">
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<input type="hidden" name="userId" id="userId" value="<c:out value="${userId}"/>"/>
		<input type="hidden" name="serviceIds" id="serviceIds" value=""/>
		<tr>
			<td class="txtr" style="width: 188px">
				用户名：
			</td>
			<td>
				<c:out value="${fullName}"/>

			</td>
		</tr>
		<tr>

			<td colspan="2" align="center">
				<div class="content_wrap">
					<div class="zTreeDemoBackground left">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
				</div>

			</td>
		</tr>
		<tr>
			<td class="txtc" colspan="2">
				<input type="button" class="btn01" id="subSave" value="提交" />
				<input type="button" class="btn01"  value="取消" onclick="window.close();"/>
			</td>
		</tr>
	</table>

</form>

</body>
</html>

