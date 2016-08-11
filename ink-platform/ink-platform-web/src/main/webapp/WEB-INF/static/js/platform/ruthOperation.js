$('#ruthOperation').click(function(){
		var selRow = $('#dg').datagrid("getSelections");//返回选中多行  
		if (selRow.length  ==0) {
			selRow =$('#lowResource').datagrid('getSelections');
		}
		if (selRow.length > 1) {
			$('#dg').datagrid('reload', {
				singleSelect : true,
			});
			$('#lowResource').datagrid('reload'); 
			$.messager.alert('提示', '请选择一条记录!', 'warning');
			return false;
		}
			var row = $('#dg').datagrid('getSelected');
			if (row == null) {
				row = $('#lowResource').datagrid('getSelected');
			}
			if (row==null){
                $.messager.alert({
                title:'提示',
                msg:'请选择一条记录',
                icon:'warning'
            });
            }
			if (row){
			$('#win').window({href:'ruthOperation?resourceId='+row.id,title:'操作选项授权'});
		    $('#win').window('open');
			}
            }); 
		