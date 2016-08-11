<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="static/js/easyui/1.4.2/themes/bootstrap/easyui.css"
	rel="stylesheet" type="text/css" />
<link href="static/js/easyui/1.4.2/themes/icon.css" rel="stylesheet"
	type="text/css" />
<link href="static/css/main.css" rel="stylesheet" type="text/css" />
<script src="static/js/jquery/1.8.0/jquery.min.js"
	type="text/javascript"></script>
<script src="static/js/easyui/1.4.2/jquery.easyui.min.js"
	type="text/javascript"></script>
<script src="static/js/global.js" type="text/javascript"></script>
<script src="static/js/easyui/1.4.2/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<div class="easyui-panel" style="padding: 5px">
		<ul id="menu_tree" class="easyui-tree"
			data-options="url:'',method:'get',animate:true,checkbox:true,cascadeCheck:false,"></ul>
	</div>
	<div style="margin: 20px 0;" align="center">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="getSelect()">确定</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" onclick="clearForm()">取消</a>
	</div>
	<input type="hidden" id="resourceId" value="${resourceId }">
	<script type="text/javascript">
	var row = $('#dg').datagrid('getSelected');

	if (row == null) {
		row = $('#lowResource').datagrid('getSelected');
	}
		$('#menu_tree').tree({
			url : 'operationTCheck?resourceId=' + row.id+'&pid='+row.pid,

			loadFilter : function(rows) {
				return convert(rows);
			},
			//删除原有的副岗
			onCheck : function getChecked() {
				var nodes = $('#menu_tree').tree('getChecked', 'unchecked');
				var userOwnJop = '';
				for (var i = 0; i < nodes.length; i++) {
					if (userOwnJop != '')
						userOwnJop += ',';
					userOwnJop += nodes[i].id;
				}
			},

		});
		function convert(rows) {
			function exists(rows, parentOrgId) {
				for (var i = 0; i < rows.length; i++) {
					if (rows[i].id == parentOrgId)
						return true;
				}
				return false;
			}
			var nodes = [];
			// get the top level nodes
			for (var i = 0; i < rows.length; i++) {
				var row = rows[i];
				if (!exists(rows, row.parentOrgId)) {
					nodes.push({
						id : row.id,
						text : row.operationName,
						checked : row.checked

					});
				}
			}

			var toDo = [];
			for (var i = 0; i < nodes.length; i++) {
				toDo.push(nodes[i]);
			}
			while (toDo.length) {
				var node = toDo.shift(); // the parent node
				// get the children nodes
				for (var i = 0; i < rows.length; i++) {
					var row = rows[i];
					if (row.parentOrgId == node.id) {
						var child = {
							id : row.id,
							text : row.operationName,
							checked : row.checked
						};
						if (node.children) {
							node.children.push(child);
						} else {
							node.children = [ child ];
						}
						toDo.push(child);
					}
				}
			}
			return nodes;
		}
		var userId = $("#userId").val();
		function getSelect() {
			var nodes = $('#menu_tree').tree('getChecked');
			var operationId = '';
			for (var i = 0; i < nodes.length; i++) {
				if (operationId != '')
					operationId += ',';
				operationId += nodes[i].id;
			}
			
			$.messager.confirm('Confirm', '确认进行该操作嘛', function(r) {
				console.info(operationId);
				if (r) {
					$.ajax({
						url : 'ruthResourceOperation',
						data : {
							"operationId" : operationId,
							"resourceId" : row.id,
							"pid" : row.pid,
						},
						success : function(data) {
							var data = eval('(' + data + ')'); // change the JSON string to javascript object
							data = $.parseJSON(data);//String 转object
								$.messager.show({
									title: data.rstType,
									msg: data.rstMsg
								});		
							$('#win').window('close');
							$('#dg').datagrid('reload');
							parent.$('#dg').datagrid('reload');//刷新父级页面
							$('#lowResource').datagrid('reload');//刷新父级页面
							
							//	parent.$('#org_user_tree').tree('reload');//刷新父级菜单树
						}
					});
				}
			})
			

		}
		function clearForm() {
			$('#win').window('close');
		}
	</script>
</body>
</html>