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
	<input type="hidden" id="userId" value="${userId }">
	<script type="text/javascript">
		$('#menu_tree').tree({
			url : 'addOrgVic?userId=' + '${userId }',

			loadFilter : function(rows) {
				return convert(rows);
			},
			//删除原有的副岗
			onCheck : function getChecked() {
				var nodes = $('#menu_tree').tree('getChecked', 'unchecked');
				var userOwnJop = '';
				for (var i = 0; i < nodes.length; i++) {
					//如果取消的是主岗：则提示
					console.info(nodes[i].titleIdentify);
					if (nodes[i].titleIdentify == "1") {
						$.messager.alert('设置副岗', '主岗职位不能删除', 'warning');
						var mm = $('#menu_tree').tree('find', nodes[i].id);
						$('#menu_tree').tree('check', mm.target);
					} else if (nodes[i].titleIdentify == "2") {
						//如果是副岗，则删除关联关系
						userOwnJop = nodes[i].id;
						$.messager.confirm('设置副岗', '确认更改该副岗吗', function(r) {
							if (r) {
								 return true;
							}
						})
					}
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
						text : row.orgName,
						state : row.state,
						away : row.away,
						state : row.state,
						url : row.url,
						checked : row.checked,
						titleIdentify : row.titleIdentify
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
							text : row.orgName,
							state : row.state,
							away : row.away,
							state : row.state,
							url : row.url,
							checked : row.checked,
							titleIdentify : row.titleIdentify

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
			var orgId = '';
			for (var i = 0; i < nodes.length; i++) {
				if (orgId != '')
					orgId += ',';
				orgId += nodes[i].id;
			}
			$.ajax({
				url : 'addOrgViceJob',
				data : {
					"orgId" : orgId,
					"userId" : userId
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
						parent.$('#org_user_tree').tree('reload');//刷新父级菜单树
				}
			});

		}
		function clearForm() {
			$('#win').window('close');
		}
	</script>
</body>
</html>