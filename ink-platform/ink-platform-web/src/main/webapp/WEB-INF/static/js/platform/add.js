
		 $('#add').click(function(){
			 $('#plantformForm').form('clear');
			//添加资源
      		 $('#dlg1').dialog('open');
 	    			$('#dlg1').dialog('open').dialog('center','no').dialog('setTitle','添加资源');
 	               // $('#userForm').form('clear');
      		
 	                url = 'addResource';
		 }); 
		//资源from表单提交
			function addSubmitForm(){
			     $('#resourceForm').form('submit',{
		          url: url,
		          onSubmit: function(){
		             return $(this).form('validate');
		             //alert($(this).form('validate'))
		          },
		          success: function(result){
		             // var result = eval('('+result+')');
		              if (result=='false'){
		                  $.messager.show({
		                      title: 'Error',
		                      msg: '操作失败！'
		                  });
		              }else {
		                  $('#dlg').dialog('close'); 
		                  $('#dlg1').dialog('close'); // close the dialog
		                  $('#dg').datagrid('reload');    // reload the user data
		                  $.messager.show({
		                      title: 'true',
		                      msg: '操作成功！'
		                  });
		              }
					    parent.$('#resource_platform_tree').tree('reload');//刷新父级树菜单

		          }
		      });
		  } 

			//平台from表单提交
			function submitPlantformForm(){
				     $('#plantformForm').form('submit',{
		              url: url,
		              onSubmit: function(){
		                  return $(this).form('validate');
		              },
		              success: function(result){
		                 // var result = eval('('+result+')');
		                  if (result=='false'){
		                      $.messager.show({
		                          title: 'Error',
		                          msg: '平台名称或标识已被使用！'
		                      });
		                  }else {
		                      $('#dlg').dialog('close');   
		                      $('#dlg1').dialog('close'); // close the dialog
		                      $('#dg').datagrid('reload');    // reload the user data
		                      $.messager.show({
		                          title: 'Error',
		                          msg: '操作成功！'
		                      });
		                  }
		  			    parent.$('#resource_platform_tree').tree('reload');//刷新父级树菜单
		              }
		          });
		      }