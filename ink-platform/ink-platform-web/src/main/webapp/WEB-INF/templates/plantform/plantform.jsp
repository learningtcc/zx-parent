<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="static/js/easyui/1.4.2/themes/bootstrap/easyui.css"
	rel="stylesheet" type="text/css" />
<link href="static/js/easyui/1.4.2/themes/icon.css" rel="stylesheet"
	type="text/css" />
<link href="static/css/main.css" rel="stylesheet" type="text/css" />
<script src="static/js/jquery/1.8.0/jquery.min.js"
	type="text/javascript"></script>
<script src="static/js/easyui/1.4.2/jquery.easyui.min.js"
	type="text/javascript"></script>
<script src="static/js/global.js" type="text/javascript"></script>
<script src="static/js/easyui/1.4.2/locale/easyui-lang-zh_CN.js"></script>
<style type="text/css">
			.hide{display: none;}
		</style>
</head>
<body>
 
		<table class="">
			<tr>
				<td>
					<span>平台名称：</span>
					 <input id="plantformName" class="easyui-textbox" type="text">
				 </td>
				<td><span>状态：</span> <input class="easyui-combobox" id="status"
					name=""
					data-options="
										valueField: 'value',
										textField: 'text',
										data: [{
											value: '1',
											text: '启用'
										},{
											value: '2',
											text: '停用'
										},{
											value: '9',
											text: '注销'
										}]" />
				</td>
				<td><span>创建日期：</span> <input id="createTime" type="text"
					class="easyui-datebox" style="width: 100px"></td>
				<td><span>至：</span> <input id="updateTime" type="text"
					class="easyui-datebox" style="width: 100px"></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="javascript:void(0)" class="easyui-linkbutton"
					id="search">查询</a> <a href="javascript:void(0)"
					class="easyui-linkbutton" id="clear">清空</a></td>
			</tr>
		</table>
		<div>
			<a href="javascript:void(0)" class="easyui-linkbutton" id="add">新增</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" id="edit">编辑</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" id="delete">删除</a>
			<!-- <a href="javascript:void(0)" class="easyui-linkbutton"
				id="ruthOperation">授予操作功能</a> -->
		</div>

	<div id="t-tab" class="easyui-tabs">
		<div title="基本信息" style="padding: 40px;">
			<table id="dg" class="easyui-datagrid" toolbar="#toolbar"></table>
		</div>
	</div>
	<div id="dlg1" class="easyui-dialog dlg" title="添加平台" 
		style="width: 400px; height: 400px; padding: 10px;"
		data-options="iconCls:'icon-save',closed:true ,modal:true,buttons:'#bb',">
		<form id="plantformForm" method="post">
			<table cellpadding="0" cellspacing="0" style="width: 100%"
				align="center">

				<tr>
					<td>平台标识 :</td>
					<td><input id="" style="width: 200px;" class="easyui-textbox"
						type="text" name="plantformCode" required /></td>
				</tr>
				<tr>
					<td>平台名称 :</td>
					<td><input id="" style="width: 200px;" class="easyui-textbox"
						type="text" name="plantformName" required /></td>
				</tr>
				<tr>
					<td>状态:</td>
					<td><input style="width: 200px;" class="easyui-combobox"
						id="status" name="status" value="1"
						data-options="
										valueField: 'value',
										textField: 'text',
										data: [{
											value: '1',
											text: '启用'
										},{
											value: '2',
											text: '停用'
										},{
											value: '9',
											text: '注销'
										}]" />
					</td>
				</tr>
				<tr>
					<td>备注:</td>
					<td><input id="" style="width: 200px;" class="easyui-textbox"
						type="text" name="remark" required /></td>
				</tr>
				
				<tr class="hide" id="hideClass">
					<td >创建人:</td>
					<td > <input disabled="disabled" id="" style="width: 200px;" class="easyui-textbox"
						type="text" name="creator" required /></td>
				</tr>
				<tr class="hide" id="hideClass">
					<td >创建时间:</td>
					<td ><input disabled="disabled" id="" style="width: 200px;" class="easyui-textbox"
						type="text" name="createTime" required /></td>
				</tr>
				<tr class="hide" id="hideClass">
					<td >更新时间:</td>
					<td ><input disabled="disabled" id="" style="width: 200px;" class="easyui-textbox"
						type="text" name="updateTime" required /></td>
				</tr>
			</table>
		</form>
	

<div id="bb">
	<a href="javascript:void(0)" class="easyui-linkbutton"onclick="addSubmitForm()">提交</a>
	<a  id="cc" href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	<a id="dd" href="javascript:void(0)" class="easyui-linkbutton hide"onclick="closeForm()">取消</a>
</div>
</div>
	<div id="win" class="easyui-window"
		style="width: 400px; height: 400px; top:10px"
		data-options="iconCls:'icon-save',modal:true,closed:true  "></div>

	<script>
	function closeForm() {
		$('#dlg1').dialog('close');
		$('#dg').datagrid('reload');
		$("#cc").removeClass('hide');
		$("#dd").addClass('hide');
		$('#plantformForm').form('clear');
	}
	$('#add').click(function(){
		 $('#plantformForm').form('clear');
		//添加资源
 		 $('#dlg1').dialog('open');
    			$('#dlg1').dialog('open').dialog('center','no').dialog('setTitle','添加资源');
               // $('#userForm').form('clear');
 		
                url = 'addPlantform';
	 }); 
	$('#edit').click(function(){
		var selRow = $('#dg').datagrid("getSelections");//返回选中多行  
		
		if (selRow.length > 1) {
			$('#dg').datagrid('reload', {
				singleSelect : true,
			});
			$.messager.alert('提示', '请选择一条记录!', 'warning');
			return false;
		}
		var row ;
		 row = $('#dg').datagrid('getSelected');
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
			url='editPlantform?id='+row.id;
			$('#dlg1').dialog('open').dialog('center').dialog('setTitle','编辑平台');
			$('#plantformForm').form('load',row);
		}
	})
	
//删除
$('#delete').click(function(){
	var row = $('#dg').datagrid('getSelections');
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
						url: 'deletePlantform?id='+data.id,
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
							$('#dg').datagrid('reload'); 
						    parent.$('#menu_tree').tree('reload');//刷新父级树菜单
						}
					});
				});
			}
			$('#dg').datagrid('reload'); 
			parent.$('#menu_tree').tree('reload');//刷新父级树菜单

			//$('#dg').datagrid('deleteRow',$('#dg').datagrid('getRowIndex',row));
		});
	}
});
	//资源from表单提交
	function addSubmitForm(){
	     $('#plantformForm').form('submit',{
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
                  $('#dlg1').dialog('close'); // close the dialog
                  $('#dg').datagrid('reload');    // reload the user data
                  $.messager.show({
                      title: 'true',
                      msg: '操作成功！'
                  });
              }
              $('#dg').datagrid('reload'); 
			    parent.$('#menu_tree').tree('reload');//刷新父级树菜单

          }
      });
  } 
	 var treeRow =parent.$('#resource_platform_tree').tree('getSelected');
	 function clearForm(){
         $('#plantformForm').form('clear');
     }
	 function closeForm(){
		 $('#dlg1').dialog('close');     
		 $('#dg').datagrid('reload'); 
		 $("#cc").removeClass('hide');
		 $("#dd").addClass('hide');
     }
	 $('#clear').click(function(){
		 $('#plantformName').textbox('setValue','');
         $('#status').combobox('setValue','');
         $('#createTime').combo('setText','').combo('setValue','');
         $('#updateTime').combo('setText','').combo('setValue','');
		});
		$('#search').click(function(){
			//$('#t-tab').tabs('select', 0);
			$('#dg').datagrid({
					url:'plantformInfo',
					queryParams:{	
							plantformName:$('#plantformName').val(),
							status:$('#status').combobox('getValue'),
							createTime:$('#createTime').datebox('getValue'),
							updateTime:$('#updateTime').datebox('getValue')
							},
			});
			RefreshPageNumber();
		});

		function RefreshPageNumber() {  
		     
		    //获取dataGrid的列表对象属性
		    
		    var $datagrid = $('#dg').datagrid("options");
		    if ($datagrid != undefined) {
		        $datagrid.pageNumber = 1;
		    }
		     
		    //获取dataGrid的分页对象
		    var $getPager = $('#dg').datagrid('getPager');
		    var $pagination = $($getPager).pagination("options");
		    if ($pagination != undefined) {
		        $pagination.pageNumber = 1; 
		    }
		    

		}
		$(function() {
			search();
			
		});
		var url;
	function search(){
		$('#dg').datagrid({  
		    url:'plantformInfo',   
		    fitColumns : true,
			pagination : true,
			remoteFilter : true,
			rownumbers : true,
			pageList : [10],//可以设置每页记录条数的列表  
			pageSize : 10,//每页显示的记录条数，默认为10  
			sortName : 'createTime',
			sortOrder : 'desc',
			remoteSort : false,
			singleSelect : false,
		    columns: [
		  			[{
		  				field: 'ck',
		  				checkbox: true
		  			}, {
		  				field: 'id',
		  				title: "id",
		  				hidden:true
		  			},
		  			{
		  				field: 'remark',
		  				title: "remark",
		  				hidden:true
		  			},{
		  				field: 'plantformName',
		  				title: "平台名称",
		  				align:'center',
		  				width:100,
		  				formatter : function(value, rowData,rowIndex) {
							url='plantformDetails?id='+rowData.id;
							return "<a href='javascript:;'  onclick='openPlatWindow(\""
									+ url
									+ "\")'><font color='blue'>"
									+ value + "</font></a>";
						}
		  			}, {
		  				field: 'plantformCode',
		  				title: "平台标识",
		  				align:'center',
		  				width:100,
		  			},
		  			{
		  				field: 'creator',
		  				title: "创建人",
		  				align:'center',
		  				width:100,
		  			},
		  			{
		  			    field: 'status',
		  				title: "状态",
		  				align:'center',
		  				width:100,
		  				formatter:function(value){
							return convertRoleStatus(value);
							}
		  			}, {
		  				field: 'createTime',
		  				title: "创建时间",
		  				align:'center',
		  				width:100,
		  				formatter:function(value,rowData,rowIndex){
		  					return new Date(value).dateFormat ("yyyy-MM-dd hh:mm:ss");}
		  			}, {
		  				field:'updateTime',
		  				title: "修改时间",
		  				align:'center',
		  				width:100,
		  				formatter:function(value,rowData,rowIndex){
		  					 if(value==null){
								 return null;
							 }else{
								return new Date(value).dateFormat ("yyyy-MM-dd hh:mm:ss");

							 } }
		  			}]
		  		]
		  	
		});  
	}

	 function openPlatWindow(url) {
			$('#win').window({
				href :url,
				title : '平台详情'
			});
			$('#win').window('open');
		}
	</script>
	

</body>
</html>