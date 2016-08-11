

function editUser() {
	var selRow = $('#dg').datagrid("getSelections");//返回选中多行  
	
	if (selRow.length  ==0) {
		selRow = $('#dg_tab').datagrid('getSelections');
	}
	if (selRow.length  ==0) {
		selRow = $('#dg_user').datagrid('getSelections');
	}
	if (selRow.length  ==0) {
		selRow = $('#dg_alluser').datagrid('getSelections');
	}
	console.info(selRow);
	if (selRow.length > 1) {
	
		$('#dg').datagrid('reload', {
			singleSelect : true,
		});
		$('#dg_tab').datagrid('reload'); 
		$('#dg_user').datagrid('reload'); 
		$('#dg_alluser').datagrid('reload'); 
		$.messager.alert('提示', '请选择一条记录!', 'warning');
		return false;
	}
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
	if (row == null) {
		$.messager.alert({
			title : '提示',
			msg : '请选择一条记录',
			icon : 'warning'
		});
	}
	if (row) {
		if (row.delFlag == "1") {
			$.messager.alert('编辑用户', '删除用户不得修改', 'warning');
		}
		if (row.subjectType == 1) {
			$.messager.alert('编辑用户', '请选择用户', 'warning');
		}else{
			//console.info(row.remark);
			row.createTime = new Date(row.createTime).dateFormat("yyyy-MM-dd hh:mm:ss")
			if(row.updateTime!=null){
				row.updateTime = new Date(row.updateTime).dateFormat("yyyy-MM-dd hh:mm:ss")
			}
			//编辑用户
			$('#edit_user').dialog('open').dialog('center').dialog('setTitle', '编辑用户');
			url = 'editUserMsg?id=' + row.id +'&titleIdentify='+row.titleIdentify;
			$('#editUserForm').form('load', row);
		}

	}
}
function editOrg() {
	var selRow = $('#dg').datagrid("getSelections");//返回选中多行  
	if (selRow.length  ==0) {
		selRow = $('#dg_tab').datagrid('getSelections');
	}
	/*if (selRow == null) {
		selRow = $('#dg_user').datagrid('getSelections');
	}*/
	if (selRow.length > 1) {
		$('#dg').datagrid('reload', {
			singleSelect : true,
		});
		$('#dg_tab').datagrid('reload'); 
		$.messager.alert('提示', '请选择一条记录!', 'warning');
		return false;
	}
	var row = $('#dg').datagrid('getSelected');
	//可能是下级资源编辑
	if (row == null) {
		row = $('#dg_tab').datagrid('getSelected');
	}
	/*if (row == null) {
		row = $('#dg_user').datagrid('getSelected');
	}*/

	if (row == null) {
		$.messager.alert({
			title : '提示',
			msg : '请选择一条记录',
			icon : 'warning'
		});
	}
	if (row) {
		if (row.delFlag == 1) {
			$.messager.alert('编辑机构', '删除机构不得修改', 'warning');
			
	}
		if (row.subjectType == "1") {
			
			row.createTime = $.myTime.UnixToDate(
					parseInt(row.createTime), true);
			/*   row.feeEndTime=$.myTime.UnixToDate(parseInt(row.feeEndTime),true); */
			url = 'updateOrg?id=' + row.id;
			$('#edit_org').dialog('open').dialog('center')
			.dialog('setTitle', '编辑机构');
			$('#editOrgForm').form('load', row);
			 $('#editOrgTree').combotree({
					url: 'addOrgVic',
					loadFilter: function(rows){
						return convertOrgTree(rows);
					}
				});
		} else {
			$.messager.alert('编辑机构', '请选择组织机构', 'warning');
		}

	}
}
//编辑机构form表单提交
function submitEditOrgForm(){
	$('#editOrgForm').form('submit',{
		url: url,
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(result){
			if (result=='false'){
				$.messager.show({
					title: 'Error',
					msg: '操作失败！'
				});
			}else {
				$('#edit_org').dialog('close');        // close the dialog
				$('#dg').datagrid('reload');    // reload the user data
				$.messager.show({
					title: 'true',
					msg: '操作成功！'
				});
			}
			parent.$('#org_user_tree').tree('reload');//刷新父级菜单树
		}
	});
}
//编辑用户
function submitEditUserForm(){
	$('#editUserForm').form('submit',{
		url : url,
		onSubmit : function() {
			return $(this).form('validate');
		},
		success : function(result) {
			// var result = eval('('+result+')');
			if (result == 'false') {
				$.messager.show({
					title : 'Error',
					msg : '操作失败！'
				});
			} else if(result =='true'){					
				$.messager.show({
					title : 'true',
					msg : '操作成功！'
				});
				$('#edit_user').dialog('close'); // close the dialog
				$('#dg').datagrid('reload'); // reload the user data
				parent.$('#org_user_tree').tree('reload');//刷新父级菜单树
				$('#userForm').form('clear');
			}
		}
	});
}