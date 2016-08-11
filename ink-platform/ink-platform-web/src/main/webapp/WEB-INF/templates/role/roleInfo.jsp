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

</head>
<body>
	<div id="toolbar" class="">
		<table class="">
			<tr>
				<td><span>角色名称：</span> <input id="roleName"
					class="easyui-textbox" type="text">
				<td><span>状态：</span> <input class="easyui-combobox"
					id="statuType" name=""
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

		<div style="text-align: left;">
			<a href="javascript:void(0)" class="easyui-linkbutton" id="add">添加</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" id="edit">编辑</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" id="delete">删除</a>
			<a href="javascript:void(0)" class="easyui-linkbutton" id="resourceAuth">授权</a>
		</div>



	</div>
	<table id="dg" class="easyui-datagrid"></table>
	<div id="dlg" class="easyui-dialog" title="添加角色"
		style="width: 400px; height: 300px; padding: 10px"
		data-options="iconCls:'icon-save',closed:true ,modal:true,buttons:'#bb'">

		<form id="ff" method="post">
			<table cellpadding="0" cellspacing="0" style="width: 100%"
				align="center">
				<tr>
					<td>角色名称:</td>
					<td><input id="" style="width: 160px;" validtype="length[1,9]"
						class="easyui-textbox" type="text" name="roleName" required /></td>
				</tr>
				<tr>
					<td>角色标识:</td>
					<td><input validtype="length[1,9]" id="" style="width: 160px;"
						class="easyui-textbox" type="text" name="roleCode" required /></td>
				</tr>
				<tr>
					<td>角色描述:</td>
					<td><input id="" style="width: 160px;" class="easyui-textbox"
						type="text" name="remark" required /></td>
				</tr>
				<tr>
					<td>状态:</td>
					<td><input style="width: 160px;" required
						class="easyui-combobox" id="status" name="status" value="1"
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
				<!-- <tr>
					<td>备注:</td>
					<td><input id="" class="easyui-textbox" type="text" style="width:160px;" 
						name="remark" required /></td>
				<tr> -->
			</table>
		</form>
	</div>
	<div id="bb">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			onclick="submitForm()">提交</a> <a id="cc" href="javascript:void(0)"
			class="easyui-linkbutton" onclick="clearForm()">重置</a> <a id="dd"
			href="javascript:void(0)" class="easyui-linkbutton hide"
			onclick="closeForm()">取消</a>
	</div>
	<div id="win" class="easyui-window" title="My Window"
		style="width: 400px; height: 400px"
		data-options="iconCls:'icon-save',modal:true,closed:true"></div>



	<script>
		function closeForm() {
			$('#dlg').dialog('close');
			$('#dg').datagrid('reload');
			$("#cc").removeClass('hide');
			$("#dd").addClass('hide');
			$('#ff').form('clear');
		}
		var url;
		$('#add').click(function() {
			$('#ff').form('clear');
			$('#dlg').dialog('open');
			$('#dlg').dialog('open').dialog('center').dialog('setTitle', '添加');
			//  $('#ff').form('clear');
			url = 'role/save';
		});
		$('#edit').click(
				function() {
					var selRow = $('#dg').datagrid("getSelections");//返回选中多行  
					if (selRow.length > 1) {
						$('#dg').datagrid('reload', {
							singleSelect : true,
						});
						$.messager.alert('提示', '请选择一条记录!', 'warning');
						return false;
					}
					var row = $('#dg').datagrid('getSelected');
					if (row == null) {
						$.messager.alert({
							title : '提示',
							msg : '请选择一条记录',
							icon : 'warning'
						});
					}
					if (row) {
						$("tr").removeClass("hide");
						$("#dd").removeClass('hide');
						$("#cc").addClass('hide');
						url = 'role/edit?id=' + row.id;
						$('#dlg').dialog('open').dialog('center').dialog(
								'setTitle', '编辑');
						$('#ff').form('load', row);
					}
				});
		$('#delete').click(function() {
			// var row = $('#dg').datagrid('getSelected');
			//console.info(row);
			var row = $('#dg').datagrid("getSelections");//返回选中多行  
			if (row == null) {
				$.messager.alert({
					title : '提示',
					msg : '请选择一条记录',
					icon : 'warning'
				});

			}
			if (row) {
				$.messager.confirm('删除', '确认删除吗', function(r) {
					if (r) {
						$.each(row, function(i, data) {
							$.ajax({
								url : 'role/delete?id=' + data.id,
								success : function(b) {
									if (b) {
										$.messager.show({
											title : 'true',
											msg : '操作成功！'
										});
									} else {
										$.messager.show({
											title : 'fasle',
											msg : '操作失败！'
										});
									}
								}
							});
							$('#dg').datagrid('reload');
						});
					}
				});
			}

		});
		function submitForm() {
			$('#ff').form('submit', {
				url : url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(result) {
					// var result = eval('('+result+')');
					if (result == 'false') {
						$.messager.show({
							title : 'Error',
							msg : '角色名称或标识已存在！'
						});
					} else {
						$('#dlg').dialog('close'); // close the dialog
						$('#dg').datagrid('reload'); // reload the user data
						$.messager.show({
							title : 'true',
							msg : '操作成功！'
						});
					}

				}
			});
		}
		$('#resourceAuth').click(function() {
			var selRow = $('#dg').datagrid("getSelections");//返回选中多行  
			if (selRow.length > 1) {
				$('#dg').datagrid('reload', {
					singleSelect : true,
				});
				$.messager.alert('提示', '请选择一条记录!', 'warning');
				return false;
			}
			var row = $('#dg').datagrid('getSelected');
			if (row == null) {
				$.messager.alert({
					title : '提示',
					msg : '请选择一条记录',
					icon : 'warning'
				});
			}
			if (row) {
				$('#win').window({
					href : 'role/resourceAuth?id=' + row.id,
					title : '权限选择'
				});
				$('#win').window('open');
			}
		});

		function clearForm() {
			$('#ff').form('clear');
		}
		$(function() {
			search();
			$('#search').click(function() {
				search();
			});

		});
		$(function() {
			search();
			$('#search').click(function() {
				search();
			});

		});

		function search() {
			var createTime = $('#createTime').datebox('getValue');
			var updateTime = $('#updateTime').datebox('getValue');
			var params = {
				roleName : $('#roleName').textbox('getText'),
				status : $('#statuType').combobox('getValue'),
				createTime : formatDate(createTime, "yyyy-MM-dd HH:mm:ss"),
				updateTime : formatDate(updateTime, "yyyy-MM-dd HH:mm:ss")
			};
			var dg = $('#dg');
			dg
					.datagrid({
						url : 'role/list',
						fitColumns : true,
						pagination : true,
						remoteFilter : true,
						rownumbers : true,
						pageList : [ 10 ],
						queryParams : params,
						sortName : 'createTime',
						sortOrder : 'desc',
						remoteSort : true,
						singleSelect : false,
						columns : [ [
								{
									field : 'ck',
									checkbox : true
								},
								{
									field : 'id',
									title : "id",
									hidden : true
								},
								{
									field : 'roleName',
									title : "角色名称",
									align : 'center',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										var url = 'roleDetail?roleId='
												+ rowData.id;
										return "<a href='javascript:;'  onclick='openWindow(\""
												+ url
												+ "\")'><font color='blue'>"
												+ value + "</font></a>";
									}
								},
								{
									field : 'roleCode',
									title : "角色标识",
									align : 'center',
									width : 100
								},
								{
									field : 'remark',
									title : "角色描述",
									align : 'center',
									width : 100
								},
								{
									field : 'creator',
									title : "创建人",
									align : 'center',
									width : 100
								},
								{
									field : 'status',
									title : "状态",
									align : 'center',
									width : 100,
									formatter : function(value) {
										return convertRoleStatus(value);
									}
								},
								{
									field : 'createTime',
									title : "创建时间",
									align : 'center',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										return new Date(value)
												.dateFormat("yyyy-MM-dd hh:mm:ss");
									}
								},
								{
									field : 'updateTime',
									title : "修改时间",
									align : 'center',
									width : 100,
									formatter : function(value, rowData,
											rowIndex) {
										if (value == null) {
											return null;
										} else {
											return new Date(value)
													.dateFormat("yyyy-MM-dd hh:mm:ss");

										}
									}
								} ] ]
					});
			RefreshPageNumber();
		}
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
		$('#clear').click(function() {
			$('#roleName').textbox('setValue', '');
			$('#status').combobox('setValue', '');
			$('#createTime').combo('setText', '').combo('setValue', '');
			$('#updateTime').combo('setText', '').combo('setValue', '');
		});

		function openWindow(url) {
			$('#win').window({
				href : url,
				title : '角色详情'
			});
			$('#win').window('open');
		}
	</script>
</body>
</html>