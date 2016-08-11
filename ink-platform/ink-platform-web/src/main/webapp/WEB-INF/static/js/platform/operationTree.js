		var node =parent.$('#resource_platform_tree').tree('getSelected');//刷新父级树菜单
		var resourceId = node.id;
//操作选项
$('#operationTree').combotree({
			url: 'operationTree?resourceId='+resourceId,
			required: true,
			loadFilter: function(rows){
				return convert(rows);
			},
			
		});
		function convert(rows){
			function exists(rows, id){
				 return true;		
			}	
			var nodes = [];
			for(var i=0; i<rows.length; i++){
				var row = rows[i];
				
				if (exists(rows, row.id)){
					nodes.push({
					    id:row.id,
						text:row.operationName,
						checked:row.checked
					});
				}
			}
			return nodes;
		}
		