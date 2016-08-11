/*//编辑平台
	 $('#edit').combobox({  
         valueField:'value',    
         textField:'text',    
         panelHeight:'auto',  
         onChange:function(newValue,oldValue){  
        	 var row = $('#dg').datagrid('getSelected');
        		if(row==null){
            		row =$('#dg_tab').datagrid('getSelected');
            	}
        	 if (row==null){
        		 $.messager.alert({
        			 title:'提示',
        			 msg:'请选择一条记录',
        			 icon:'warning'
        		 });
        	 }
        	 if (row){
        		 //编辑平台
        		 if(newValue=='1'){
        			 url='editPlantform?id='+row.id;
        			 $('#dlg').dialog('open').dialog('center').dialog('setTitle','编辑平台');
        			 $('#plantformForm').form('load',row);
     			    parent.$('#resource_platform_tree').tree('reload');//刷新父级树菜单
        		 }else{
        			 //编辑资源
        			  url='editResource?id='+row.id;
                      $('#dlg1').dialog('open').dialog('center').dialog('setTitle','编辑资源');
                      $('#resourceForm').form('load',row);
      			    parent.$('#resource_platform_tree').tree('reload');//刷新父级树菜单

        		 }
        	 }


         } 
     }); */

$('#edit').click(function(){
	var selRow = $('#dg').datagrid("getSelections");//返回选中多行  
	if (selRow.length  ==0) {
		selRow =$('#lowResource').datagrid('getSelections');
	}
	if (selRow.length > 1) {
		$('#dg').datagrid('reload', {
			singleSelect : true,
		});
		$('#lowResource').datagrid('reload', {
			singleSelect : true,
		});
		$.messager.alert('提示', '请选择一条记录!', 'warning');
		return false;
	}
	var row ;
	 row = $('#dg').datagrid('getSelected');
	if(row==null){
		row =$('#lowResource').datagrid('getSelected');
	}
	if (row==null){
		$.messager.alert({
			title:'提示',
			msg:'请选择一条记录',
			icon:'warning'
		});
	}
	if (row){
		 console.info(row.remark);
		 $("tr").removeClass("hide");
		 $("#dd").removeClass('hide');
		 $("#cc").addClass('hide');
		 row.createTime = new Date(row.createTime).dateFormat ("yyyy-MM-dd hh:mm:ss");
		 if(row.updateTime!=null){
				row.updateTime = new Date(row.updateTime).dateFormat ("yyyy-MM-dd hh:mm:ss");
		 }
		//编辑资源
		url='editResource?id='+row.id;
		$('#dlg1').dialog('open').dialog('center').dialog('setTitle','编辑资源');
		$('#resourceForm').form('load',row);
		parent.$('#resource_platform_tree').tree('reload');//刷新父级树菜单

	}
})