<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@ include file="/gen-commons/taglibs.jsp"%>
<link rel="stylesheet" href="../static/ztree/style/zTreeStyle.css"
	type="text/css"></link>
<script type="text/javascript"
	src="../static/ztree/jquery.ztree.all-3.1.js"></script>

<title>左侧菜单</title>

<SCRIPT type="text/javascript">

var setting = {
	data: {
		simpleData: {
			enable:true,
			idKey: "id",
			pIdKey: "pid",
			rootPId: ""
		}
	},
	callback: {
		onClick: function(event, treeId, treeNode) {
			//alert(treeId+"----"+treeNode.id);
			if(!treeNode.isParent){
				window.parent.treeClick(treeId, treeNode);
			}
		}
	}
};

$(document).ready(function(){
	$.post("${yk}/common/menu.do",function(data){
		if(data != null){
			var zNodes = eval(data);
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		}
	});
});
</SCRIPT>

</head>

<body
	style="background-color: #eef9ff; margin-left: 0px; margin-top: 0px">
	<div class="content_wrap">
		<div class="zTreeDemoBackground left">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
	</div>
</body>
</html>

