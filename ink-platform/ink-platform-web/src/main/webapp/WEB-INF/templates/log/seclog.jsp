<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	 <link href="static/js/easyui/1.4.2/themes/bootstrap/easyui.css" rel="stylesheet" type="text/css"/>
    <link href="static/js/easyui/1.4.2/themes/icon.css" rel="stylesheet" type="text/css"/>
    <link href="static/css/main.css" rel="stylesheet" type="text/css"/>
    <script src="static/js/jquery/1.8.0/jquery.min.js" type="text/javascript"></script>
    <script src="static/js/easyui/1.4.2/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="static/js/global.js" type="text/javascript"></script>	
    <script src="static/js/easyui/1.4.2/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
   <div id="toolbar" class="">
		<table class="">
			<tr>
				<td>
					<span>操作人Id：</span>
					<input id="userId" class="easyui-textbox" type="text" >
				</td>
				<td>
					<span>操作类型：</span>
					<input class="easyui-combobox"  id="actionType" name="" data-options="
										valueField: 'value',
										textField: 'text',
										data: [{
											value: '0',
											text: '用户'
										},{
											value: '1',
											text: '角色'
										},{
											value: '2',
											text: '资源'
										},{
										    value: '3',
											text: '平台'
										},{
											value: '4',
											text: '操作'
										},{
											value: '5',
											text: '用户&角色关联'
										},{
											value: '6',
											text: '角色&资源关联'
										}
										]" />
				</td>	
				<td>
					<span>操作结果：</span>
					<input class="easyui-combobox"  id="result" name="" data-options="
										valueField: 'value',
										textField: 'text',
										data: [{
											value: '1',
											text: '成功'
										},{
											value: '0',
											text: '失败'
										}]" />
				</td>
				<td>
					<span>创建日期：</span>
					<input id="createTime" name="" type="text" class="easyui-datebox" style="width:100px">
				</td>
				<td>
					<span>至：</span>
					<input id="endTime" name="" type="text" class="easyui-datebox" style="width:100px">
				</td>
			</tr>
			<tr ><td></td><td></td><td></td>
			<td><a href="javascript:void(0)" class="easyui-linkbutton" id="search">查询</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" id="clear">清空</a>
			</td>
			</tr>
		</table>
	</div>
	
	<table id="dg" class="easyui-datagrid" toolbar="#toolbar"></table>
	
	<div id="win" class="easyui-window" title="My Window" style="width:600px;height:300px"
        data-options="iconCls:'icon-save',modal:true,closed:true">
   </div>
	<script>
	$(function() {
	search();
	$('#search').click(function(){
		search();
	});

});


function search(){
	var createTime =$('#createTime').datebox('getValue');
	var endTime=$('#endTime').datebox('getValue');
	var params={
	//new Date($('#createTime').datebox('getValue')).dateFormat ("yyyy-MM-dd hh:mm:ss"),
			 
		            userId:$('#userId').textbox('getText'),
		            actionType:$('#actionType').combobox('getText'),
		            result:$('#result').combobox('getValue'),
	    			createTime:formatDate(createTime,"yyyy-MM-dd HH:mm:ss"),
	    			endTime:formatDate(endTime,"yyyy-MM-dd HH:mm:ss")
	};
	var dg = $('#dg');
	dg.datagrid({
		url: 'log/list',
		fitColumns:true,
		pagination: true,
		remoteFilter: true,
		rownumbers: true,
		//pageList:[15],
		queryParams:params,
	 	/* sortName:'createTime',
		sortOrder:'desc',  */
		remoteSort:true,//定义是否通过远程服务器对数据排序。
		singleSelect:true,
		columns: [
			[{
				field: 'id',
				title: "日志id",
				/* hidden:true */
			},{
				field: 'userId',
				title: "操作人Id",
				align:'center',
				width:100
			}, {
				field: 'userName',
				title: "操作人姓名",
				align:'center',
				width:100
			}, {
				field: 'actionType',
				title: "操作类型",
				align:'center',
				width:100,
				/* formatter:function(value){
					return convertAction(value);
					} */
			}, {
				field: 'result',
				title: "操作结果",
				align:'center',
				width:100,
				formatter:function(value){
					return convertResult(value);
					}
			}, {
				field: 'action',
				title: "行为",
				align:'center',
				width:100
			}, {
				field: 'createTime',
				title: "创建时间",
				align:'center',
				width:100,
				formatter:function(value,rowData,rowIndex){
					
					//new Date(value).dateFormat("yyyy-MM-dd HH:mm:ss");  
					return new Date(value).dateFormat ("yyyy-MM-dd hh:mm:ss");
					
					
				} 
			}]
		]
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
    $('#clear').click(function(){
           $('#userId').textbox('setValue','');
           $('#actionType').combobox('setValue','');
           $('#result').combobox('setValue','');
           $('#createTime').combo('setText','').combo('setValue','');
           $('#endTime').combo('setText','').combo('setValue','');
		});
		
    //function openWindow(url){
		//    $('#win').window({href:url,title:'角色详情'});
		//    $('#win').window('open');
		//    }
	</script>
</body>
</html>