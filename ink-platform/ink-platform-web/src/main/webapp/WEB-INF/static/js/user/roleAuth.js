//角色授权
			  $('#roleAuth').click(function(){
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
						$('#dg_tab').datagrid('reload'); 
						$('#dg_user').datagrid('reload'); 
						$('#dg_alluser').datagrid('reload'); 
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
			      //dongzuo
		            if (row==null){
			                $.messager.alert({
			                title:'提示',
			                msg:'请选择一条记录',
			                icon:'warning'
			            });
	            	}
		            if (row.subjectType == 1) {
						$.messager.alert('角色授权', '请选择用户', 'warning');
					}else if (row){
		    		    $('#role_user_auth').dialog('open').dialog('center').dialog('setTitle','角色授权');
		                 $('#addUserOrgRole').form('load',row);
		            }
			        var	subjectId =row.id;
			  //目前之争对用户
			      
			        $('#roleTree').tree({
						url: 'userRoleSelect?subjectId=' + row.id+'&titleCode='+row.titleIdentify,
						loadFilter: function(rows){
							return convert(rows);
						},
	
					});
					function convert(rows){
						function exists(rows, parent_org_id){
							for(var i=0; i<rows.length; i++){
								if (rows[i].id == parent_org_id) return true;
							}
							return false;
						}	
						var nodes = [];
						// get the top level nodes
						for(var i=0; i<rows.length; i++){
							var row = rows[i];
							if (!exists(rows, row.parent_org_id)){
								nodes.push({
								    id:row.id,
									text:row.roleName,
									checked:row.checked
								});
							}
						}
						
						var toDo = [];
						for(var i=0; i<nodes.length; i++){
							toDo.push(nodes[i]);
						}
						while(toDo.length){
							var node = toDo.shift();	// the parent node
							// get the children nodes
							for(var i=0; i<rows.length; i++){
								var row = rows[i];
								if (row.parent_org_id == node.id){
									var child = {    
											id:row.id,
											text:row.roleName,
											checked:row.checked
											};
									if (node.children){
										node.children.push(child);
									} else {
										node.children = [child];
									}
									toDo.push(child);
								}
							}
						}
						return nodes;
					}
		  });

			  //表单提交
	        	function submitRoleAuthForm() {
	        		var row = $('#dg').datagrid('getSelected');//用户
	        		var nodes1 = $('#roleTree').tree('getChecked');	// get checked nodes       
					$.messager.confirm('Confirm','确认更改该角色吗',function(r){
						//======================选中角色=========================
		        	//	var row = $('#dg').datagrid('getSelected');//用户
						var nodes = $('#roleTree').tree('getChecked');
						var roleId = '';
						//选中角色
						for(var i=0; i<nodes.length; i++){
							if (roleId != '') roleId += ',';
							roleId += nodes[i].id;
						}
						console.info(row.titleIdentify);
						if (r){
							//=======================角色授权==============================
							$.ajax({
								url:'addOrgUserRole'  , 
								data:{"subjectId" :row.id,"roleId":roleId,"titleCode":row.titleIdentify},
								success: function(data) {
									var data = eval('(' + data + ')'); // change the JSON string to javascript object
									data = $.parseJSON(data);//String 转object
										$.messager.show({
											title: data.rstType,
											msg: data.rstMsg
										});		
										 $('#dg').datagrid('reload');//刷新父级页面
											$('#role_user_auth').dialog('close'); // close the dialog

								}
							    });
									
						}
					})
					
				}	


				