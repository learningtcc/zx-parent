	  
	     $('#delete').click(function(){
	    	 //parent. parent.$('#tt').tabs('close','getSelected')
	    	 console.info('12');
	  
	        var row = $('#dg').datagrid('getSelections');
	        if (row.length  ==0) {
        		row =$('#dg_tab').datagrid('getSelections');
        	}
	        if (row.length  ==0) {
        		row =$('#dg_user').datagrid('getSelections');
			}
	        if (row.length  ==0) {
        		row =$('#dg_alluser').datagrid('getSelections');
			}
	        if (row.length  ==0) {
	                $.messager.alert({
	                title:'提示',
	                msg:'请选择一条记录',
	                icon:'warning'
	            });
	                return false;
            }
	         //   alert(row.id);
	          //  alert(row.orgName);
            if (row){
            	$.messager.confirm('删除', '确认删除吗', function(r) {
        			if (r) {
        				$.each(row, function(i, data) {
        					 if(data.subjectType=='2'){
        						 url='deleteUser?userId='+data.id+'&orgId='+data.orgId+'&titleIdentify='+data.titleIdentify
        					 }else if(data.subjectType=='1'){
        						 url='deleteOrg?orgId='+data.id 
        					 }
        					 $.ajax({
        							url:url,    
        							success: function(b) {
        								if(b){
        									$.messager.show({
        				                        title: 'true',
        				                        msg: '操作成功！'
        				                    });
        									console.info(data.username);
        									 parent. $('#tt').tabs('close', data.orgName);
        									 parent. $('#tt').tabs('close', data.username);
        								}else{
        									$.messager.show({
        				                        title: 'fasle',
        				                        msg: '操作失败！'
        				                    });
        								}
        								$('#dg').datagrid('reload');  
        			        			$('#dg_tab').datagrid('reload');  
        			        			$('#dg_user').datagrid('reload');  
        			        			$('#dg_alluser').datagrid('reload'); 
        							}
        					    });
        				});
        			}
                //	alert(treeRow.subjectType);
        		/*	$('#dg').datagrid('reload');  
        			$('#dg_tab').datagrid('reload');  
        			$('#dg_user').datagrid('reload');  
        			$('#dg_alluser').datagrid('reload');  */
        			
			    //$('#dg').datagrid('deleteRow',$('#dg').datagrid('getRowIndex',row));
			    parent.$('#org_user_tree').tree('reload');//刷新父级菜单树
			  // parent. parent.$('#tt').tabs('close','getSelected')

                });
            }
		});