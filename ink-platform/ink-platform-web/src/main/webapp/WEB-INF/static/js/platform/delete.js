
//删除
$('#delete').click(function(){
	var treeType =treeRow.attributes.treeType
	var url;

	var row = $('#dg').datagrid('getSelections');
	if (row.length  ==0) {
		row =$('#lowResource').datagrid('getSelections');
	}
	if (row==null){
		$.messager.alert({
			title:'提示',
			msg:'请选择一条记录',
			icon:'warning'
		});
	}
	if (row){
		console.info(row.id);
		$.messager.confirm('删除', '确认删除吗', function(r) {
			if (r) {
				$.each(row, function(i, data) {
					$.ajax({
						url: 'deleteResource?id='+data.id,
						success: function(b) {
							if(b){
								$.messager.show({
			                        title: 'true',
			                        msg: '操作成功！'
			                    });
								 parent. $('#tt').tabs('close', data.resourceName);
							}else{
								$.messager.show({
			                        title: 'fasle',
			                        msg: '操作失败！'
			                    });
							}
						
						    parent.$('#resource_platform_tree').tree('reload');//刷新父级树菜单
						}
					});
				});

				parent.$('#resource_platform_tree').tree('reload');//刷新父级树菜单
			}
			$('#dg').datagrid('reload'); 
			$('#lowResource').datagrid('reload');   
			parent.$('#resource_platform_tree').tree('reload');//刷新父级树菜单

			//$('#dg').datagrid('deleteRow',$('#dg').datagrid('getRowIndex',row));
		});
	}
});