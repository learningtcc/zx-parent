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
		<ul id="move_pOrg_tree" class="easyui-tree"  data-options="url:'',method:'get',animate:true,dnd:true,checkbox:true,cascadeCheck:false,"></ul>
	</div>
	<div style="margin: 20px 0;" align="center">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="getSelect()">确定</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" onclick="clearForm()">取消</a>
	</div>
	<script type="text/javascript">
	var targetId ; //目标节点id
	var target ;
	var parentOrgId;//原父组织id
	var sourceId ;
	 var row = $('#dg').datagrid('getSelected');
	//可能是下级资源编辑
		if (row == null) {
			row = $('#dg_tab').datagrid('getSelected');
		}
		if (row == null) {
			row = $('#dg_user').datagrid('getSelected');
		}
		if (row == null) {
			row = $('#dg_alluser').datagrid('getSelected');
		}
		function getSelect() {
			$.ajax({
				url : 'moveOrg',
				type : 'post',
				dataType : 'json',
				data : {
					id :row.id ,
					parentOrgId :row.orgId,
					targetId : targetId,
				},
				success : function(r) {
					 $('#move_pOrg_tree').tree('reload')
					parent.$('#org_user_tree').tree('reload');//刷新父级菜单树
					if (r) {
						$.messager.show({
							title : 'true',
							msg : '操作成功！'
						});
						 $('#win').window('close'); 
					} 
				}, 
			});
		};
		$('#move_pOrg_tree').tree({
			//dnd : true,
			url : 'addOrgVic',
			loadFilter : function(rows) {
				return convert(rows);
			},
				onLoadSuccess:function(node, data){
					$('.move_pOrg_tree').unbind('click');
				}, 
				
				onCheck : function(node,checked){
					var cknodes = $(this).tree("getChecked");
					if(cknodes.length>1){
						for(var i = 0 ; i < cknodes.length ; i++){
							$(this).tree("uncheck", cknodes[i].target);
						}
						//再选中改节点
						$(this).tree("check", node.target);
						
					}
					if(checked==true){
						
						targetId=node.id;
					}
				}
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
						remark : row.remark,
						parentOrgId : row.parentOrgId,
						subjectType : row.subjectType
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
							remark : row.remark,
							subjectType : row.subjectType,
							parentOrgId : row.parentOrgId
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
		function clearForm() {
			$('#win').window('close');
		}
	</script>
</body>
</html>