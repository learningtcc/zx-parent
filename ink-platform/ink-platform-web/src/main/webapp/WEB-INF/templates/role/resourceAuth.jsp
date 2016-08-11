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
  <%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
   + request.getServerName() + ":" + request.getServerPort()
   + path + "/";
%>
    
</head>
<body>
	<div class="easyui-panel" style="padding:5px">
		<ul id="tt" class="easyui-tree" data-options="url:'',method:'get',animate:true,checkbox:true,cascadeCheck:false,"></ul>
	</div>
	<div style="margin:20px 0;" align="center">
		<a href="#" class="easyui-linkbutton" onclick="getChecked()" text-align:'center'>授权</a> 
	</div>
	<script type="text/javascript">
	var row = $('#dg').datagrid('getSelected'); 
	var roleId =row.id;
	console.info(roleId);
	$('#tt').tree({
		url: 'getUserResource?roleId='+roleId,
		loadFilter: function(rows){
			return convert(rows);
		},
		formatter:function(node){
			var ic =node.code;
			var s = node.text;
			if (ic=='1'){
				s= ' <span style=\'color:blue\'>' +s + '</span>';
			}
			return s;
		}
	});

	function convert(rows){
		function exists(rows, pid){
			for(var i=0; i<rows.length; i++){
				if (rows[i].id == pid) return true;
			}
			return false;
		}
		
		var nodes = [];
		// get the top level nodes
		for(var i=0; i<rows.length; i++){
			var row = rows[i];
			if (!exists(rows, row.pid)){
				if(row.resourceCode=='1'){
					nodes.push({
					    id:row.id,
					    pid:row.pid,
						text:row.resourceName,
						state:row.state,
						away:row.away,
						url:row.url,
						checked:row.checked,
						code: row.resourceCode,
						iconCls: 'icon-operation',
					});
				}else{
					nodes.push({
					    id:row.id,
					    pid:row.pid,
						text:row.resourceName,
						state:row.state,
						away:row.away,
						url:row.url,
						checked:row.checked,
						code: row.resourceCode,
						iconCls: 'icon-resource',
					});
				}
				
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
				if (row.pid == node.id){
					if(row.resourceCode=='1'){
						var child = {    
								id:row.id,
								pid:row.pid,
								text:row.resourceName,
								state:row.state,
								away:row.away,
								checked:row.checked,
								url:row.url,
								code: row.resourceCode,
								iconCls: 'icon-operation',
								};
					}else{
						var child = {    
								id:row.id,
								pid:row.pid,
								text:row.resourceName,
								state:row.state,
								away:row.away,
								checked:row.checked,
								url:row.url,
								code: row.resourceCode,
								iconCls: 'icon-resource',
								};
					}
					
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
		function getChecked(){
			var nodes = $('#tt').tree('getChecked');
			$.post('<%=path %>/role/deleteResource',{id:roleId},function(str){				});
			var ids = '';
			for(var i=0; i<nodes.length; i++){
				if (ids != '') ids += ',';
				ids += nodes[i].id;
				if(nodes[i].code=='1'){
					//1代表操作
						//console.info(nodes[i].pid)
						$.post("<%=path %>/role/roleAuth",{oprationId:nodes[i].id,resourceId:nodes[i].pid,id:roleId},function(str){	
							$.messager.show({
			                    title: 'true',
			                    msg: '授权成功！'
			                });
						});
				}else if (nodes[i].code=='2'){
					//2代表资源
					$.post('<%=path %>/role/roleAuth',{resourceId:nodes[i].id,id:roleId},function(str){	
						$.messager.show({
		                    title: 'true',
		                    msg: '授权成功！'
		                });
					});
				}
			}
			$('#win').window('close'); 
			$('#dg').datagrid('reload');   
		}
		</script>
</body>
</html>