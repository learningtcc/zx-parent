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
		<div data-options="region:'center',fit:true,border:false" title=""
			style="width: 150px; padding: 5px; float: left;">
			<ul id="org_user_tree"
				data-options="url:'',method:'get',animate:true, onClick: menuClickHandler, cache: false,lines:true"></ul>
		</div>
		<div>
			<div id="tt" class="easyui-tabs"></div>
		</div>

	<script type="text/javascript">
    $('#org_user_tree').tree({
		url: 'orgInfoMenu',
		loadFilter: function(rows){
			return convert(rows);
		},
	/*  formatter:function(node){
		console.info(node.subjectType);
		var ic =node.subjectType;
		var s = node.text;
		if (ic=='1'){
			$('#this').tree('update', {
				iconCls: "icon-add",
			});
			// s= ' <span style=\'color:blue\'>(' +s + ')</span>';
		}
		return s;
	} */
	});


	function convert(rows){
		function exists(rows, parentOrgId){
			for(var i=0; i<rows.length; i++){
				if (rows[i].id == parentOrgId) return true;
			}
			return false;
		}	
		var nodes = [];
		// get the top level nodes
		for(var i=0; i<rows.length; i++){
			var row = rows[i];
			if (!exists(rows, row.parentOrgId)){
				//1:机构，2：用户
				var type =row.subjectType;
				if (type=='1'){
					nodes.push({
					    id:row.id,
						text:row.orgName,
						//state:'closed',
						away:'_self',
						url:row.url+'?id='+row.id,
				     	iconCls: 'icon-org',
						subjectType:row.subjectType
					});			
				}else{
					nodes.push({
					    id:row.id,
						text:row.orgName,
						state:'closed',
						away:'_self',
						url:row.url+'?id='+row.id,
				     	iconCls: 'icon-user',
						subjectType:row.subjectType
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
				if (row.parentOrgId == node.id){
					var type =row.subjectType;
					if (type=='1'){
						var child = {    
								id:row.id,
								text:row.orgName,
								away:'_self',
								url:row.url+'?id='+row.id,
						     	iconCls: 'icon-org',
								subjectType:row.subjectType
								};
					}else{
						var child = {    
								id:row.id,
								text:row.orgName,
								away:'_self',
								url:row.url+'?id='+row.id,
						     	iconCls: 'icon-user',
								subjectType:row.subjectType
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
    $(function () {
        $('#tt').tabs({
        	//当一个 ajax 标签页面板（tab panel）完成加载远程数据时触发。
            onLoad: function (panel) {
            	//// 相应的标签页（tab）对象 
                var plugin = panel.panel('options').title;
            },
        });

        //加载默认页
        //$('#ifr-main').attr('src', 'aa');
    });

    function closeTT(title) {
        $('#tt').tabs('close', title);
    }

    function open1(title, plugin) {
        if ($('#tt').tabs('exists', title)) {
            $('#tt').tabs('select', title);
            var tab = $('#tt').tabs('getSelected');
            $('#tt').tabs('update', {
                tab: tab,
                options: {
                    content: '<iframe frameborder="0" src="' + plugin + '"></iframe>'
                }
            });
        } else {
            $('#tt').tabs('add', {
                title: title,
                closable: true,
                bodyCls: 'p5',
                border:false,
                fit:true,
                content: '<iframe frameborder="0" src="' + plugin + '"></iframe>'
            });
        }
    }
</script>
</body>
</html>