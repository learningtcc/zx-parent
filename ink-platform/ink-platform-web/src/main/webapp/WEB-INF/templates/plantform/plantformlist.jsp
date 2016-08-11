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
					<span>资源名称：</span>
					 <input id="resourceName" class="easyui-textbox" type="text">
				 </td>
				<td>
					<span>资源级别：</span> 
					<input id="resourceLevel" class="easyui-textbox" type="text">
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
			<a href="javascript:void(0)" class="easyui-linkbutton"
				id="ruthOperation">授予操作功能</a>
		</div>

	<div id="t-tab" class="easyui-tabs">
		<div title="基本信息" style="padding: 40px;">
			<table id="dg" class="easyui-datagrid" toolbar="#toolbar"></table>
		</div>
		<div title="下级资源" style="padding: 40px;">
			<table id="lowResource" class="easyui-datagrid" toolbar="#toolbar"></table>
		</div>
	</div>
	<div id="dlg1" class="easyui-dialog dlg" title="添加资源" 
		style="width: 400px; height: 400px; padding: 10px;"
		data-options="iconCls:'icon-save',closed:true ,modal:true,buttons:'#bb',">
		<form id="resourceForm" method="post">
			<table cellpadding="0" cellspacing="0" style="width: 100%"
				align="center">

				<tr>
					<td>平台标识 :</td>
					<td>
<input id="plantformTree" class="easyui-combotree" name="sysCode" 
							data-options="url:'',method:'get'" style="width: 200px;">					
					</td>
				</tr>
				<tr>
					<td>资源标识 :</td>
					<td><input id="" style="width: 200px;" class="easyui-textbox"
						type="text" name="resourceCode" required /></td>
				</tr>
				<tr>
					<td>资源名称 :</td>
					<td><input id="" style="width: 200px;" class="easyui-textbox"
						type="text" name="resourceName" required /></td>
				</tr>
				 <tr>
					<td>权限表达式 :</td>
					<td><input id="" style="width: 200px;" class="easyui-textbox" type="text"
						name="permission" required /></td>
				</tr> 
			<!-- 	<tr>
					<td>资源级别:</td>
					<td><input id="" style="width: 200px;"
						class="easyui-numberbox" type="text" name="resourceLevel" value=""
					missingMessage="请填写数字级别"	required /></td>
				</tr> -->
				<tr>
					<td>上级资源:</td>

					<td><input id="parentResourceTree" class="easyui-combotree"
						name="pid" data-options="url:'',method:'get'"
						style="width: 200px;"></td>
				</tr>

					<!-- 	 <tr>
					<td>操作名称:</td>
					<td>
					<select id="operationTree" class="easyui-combotree" name="operationTree" data-options="url:'',cascadeCheck:false" multiple style="width:200px;"></select>
					</td>
				</tr> -->
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
					<!-- <tr>
					<td>资源位置:</td>
					<td><input id="" class="easyui-numberbox" type="text"
						name="resourcePosition" required /></td>
				<tr>  -->
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
	</div>

<div id="bb">
	<a href="javascript:void(0)" class="easyui-linkbutton"onclick="addSubmitForm()">提交</a>
	<a  id="cc" href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
	<a id="dd" href="javascript:void(0)" class="easyui-linkbutton hide"onclick="closeForm()">取消</a>
</div>
	<div id="win" class="easyui-window"
		style="width: 400px; height: 400px; top:10px"
		data-options="iconCls:'icon-save',modal:true,closed:true  "></div>

	<script>
	 var treeRow =parent.$('#resource_platform_tree').tree('getSelected');
	 function clearForm(){
         $('#plantformForm').form('clear');
         $('#resourceForm').form('clear');       
     }
	 function closeForm(){
		 $('#dlg1').dialog('close');     
		 $('#dg').datagrid('reload'); 
		 $("#cc").removeClass('hide');
		 $("#dd").addClass('hide');
		    $('#resourceForm').form('clear');
     }
	 $('#clear').click(function(){
		 $('#resourceName').textbox('setValue','');
         $('#resourceLevel').textbox('setValue','');
         $('#status').combobox('setValue','');
         $('#createTime').combo('setText','').combo('setValue','');
         $('#updateTime').combo('setText','').combo('setValue','');
		});
		$('#search').click(function(){
			$('#t-tab').tabs('select', 0);
			$('#dg').datagrid({
					url:'selectResource',
					queryParams:{	
							resourceLevel:$('#resourceLevel').val(),
							resourceName:$('#resourceName').val(),
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
		$('#plantformTree').combotree({
			url: 'plantTree',
			required: true,
			loadFilter: function(rows){
				return convertPlatform(rows);
			},
			
		});
		function convertPlatform(rows){
			function exists(rows, id){
				 return true;		
			}	
			var nodes = [];
			for(var i=0; i<rows.length; i++){
				var row = rows[i];
				
				if (exists(rows, row.id)){
					nodes.push({
					    id:row.plantformCode,
						text:row.plantformCode,
					//	checked:row.checked
					});
				}
			}
			return nodes;
		}
		
	</script>
	<script src="static/js/platform/platform.js"></script>
	<script src="static/js/platform/add.js"></script>
	<script src="static/js/platform/delete.js"></script>
	<script src="static/js/platform/edit.js"></script>
	<script src="static/js/platform/lowResource.js"></script>
	<script src="static/js/platform/platform.js"></script>
	<script src="static/js/platform/resourceTree.js"></script>
	<script src="static/js/platform/operationTree.js"></script>
	<script src="static/js/platform/ruthOperation.js"></script>
</body>
</html>