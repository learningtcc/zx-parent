//添加机构	//添加用户
var url;
var parentOrgId  =$("#orgId").val();

function addUser(){
	//添加用户
	$('#user—dlg').dialog('open');
	$('#user—dlg').dialog('open').dialog('center').dialog('setTitle','添加用户');
	url = 'addUser?parentOrgId='+parentOrgId;

	 $('#userOrgTree').combotree({
			url: 'addOrgVic',
			loadFilter: function(rows){
				return convertOrgTree(rows);
			}
		});
}

function addOrg(){
	//添加机构
	$('#dlg').dialog('open');
	$('#dlg').dialog('open').dialog('center').dialog('setTitle','添加机构');
	url = 'addOrg';
	 $('#orgTree').combotree({
			url: 'addOrgVic',
			loadFilter: function(rows){
				return convertOrgTree(rows);
			}
		});
}
//from表单提交
function submitForm(){
	$('#addOrgForm').form('submit',{
		url: url,
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(result){
			console.info(result);
			if (result){
				$('#dlg').dialog('close');        // close the dialog
				$('#dg').datagrid('reload');    // reload the user data
				$.messager.show({
					title: 'true',
					msg: '操作成功！'
				});

			}else {
				$.messager.show({
					title: 'Error',
					msg: '操作失败！'
				});
			}
			parent.$('#org_user_tree').tree('reload');//刷新父级菜单树
		}
	});
}
//添加用户表单提交
function submitUserForm(){
	$('#userForm').form('submit',{
		url: url,
		onSubmit: function(){
			return $(this).form('validate');
		},
		//dataType: "json",
		success: function(data){
			var data = eval('(' + data + ')'); // change the JSON string to javascript object
			//console.info(typeof data);
			//console.info(data.rstType);
			data = $.parseJSON(data);//String 转object
			console.info(data.rstType);
			var title = data.rstType;
			if(title){
				$.messager.show({
					title:title,
					msg: data.errMsg
				});
				$('#user—dlg').dialog('close'); 

			}else{
				$.messager.alert(title, data.errMsg, 'warning');
				$('#userForm').form('clear');
			}			
			parent.$('#org_user_tree').tree('reload');//刷新父级菜单树
		}

	});
}