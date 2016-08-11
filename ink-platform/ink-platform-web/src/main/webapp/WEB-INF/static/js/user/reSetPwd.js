//重置密码

		$('#resetPwd').click(function() {
			var selRow = $('#dg').datagrid("getSelections");//返回选中多行  
			/*if (selRow == null) {
				selRow = $('#dg_tab').datagrid('getSelections');
			}*/
			if (selRow.length  ==0) {
				selRow = $('#dg_user').datagrid('getSelections');
			}
			if (selRow.length  ==0) {
				selRow = $('#dg_alluser').datagrid('getSelections');
			}
			if (selRow.length > 1) {
				$('#dg').datagrid('reload', {
					singleSelect : true,
				});
				$.messager.alert('提示', '请选择一条记录!', 'warning');
				return false;
			}
			var row = $('#dg').datagrid('getSelected');
			//可能是下级资源编辑
			/*if (row == null) {
				row = $('#dg_tab').datagrid('getSelected');
			}*/
			if (row == null) {
				row = $('#dg_user').datagrid('getSelected');
			}
			if (row == null) {
				row = $('#dg_alluser').datagrid('getSelected');
			}
			if (row == null) {
				$.messager.alert({
					title : '提示',
					msg : '请选择一条记录',
					icon : 'warning'
				});
			}
			if (row) {
				$.messager.alert('确认', '确认重置', 'warning', function() {
					$.ajax({
						url : 'resetPwd?userId=' + row.id,
						success : function(result) {
							if (result) {
								alert('重置成功，初始密码为12345！');
							}
						}
					});
				});
			}
		});
		