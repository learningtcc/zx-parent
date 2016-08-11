<%-- <%@ page contentType="text/html; charset=UTF-8" %>
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
	<div class="easyui-panel" style="padding:5px">
		<ul id="tt" class="easyui-tree" data-options="url:'getResource',method:'get',cascadeCheck:'false'"></ul>
	</div>
	<script type="text/javascript">
	$('#tt').tree({
		url: 'role/getRoleResource?id='+'${id}',
		cascadeCheck:false,
		loadFilter: function(rows){
			return convert(rows);
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
				nodes.push({
				    id:row.id,
					text:row.resourceName,
					state:row.state,
					away:row.away,
					url:row.url
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
				if (row.pid == node.id){
					var child = {    
							id:row.id,
							text:row.resourceName,
							state:row.state,
							away:row.away,
					
							url:row.url
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
		function getChecked(){
			var nodes = $('#tt').tree('getChecked');
			var ids = '';
			for(var i=0; i<nodes.length; i++){
				if (ids != '') ids += ',';
				ids += nodes[i].id;
			}
			alert(ids);
			$.ajax({
				url:'role/doempower?ids='+ids, 
				data:ids,
				success: function() {
					alert('授权成功');
				}
			    });
		}
		</script>
</body>
</html> --%>