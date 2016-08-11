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
	 <div data-options="region:'center',fit:true,border:false" title="" style="width: 150px;  padding: 5px;float: left;">
        <ul id="resource_platform_tree" data-options="url:'',method:'get',animate:true, onClick: menuClickHandler, cache: false,lines:true"></ul>
    </div>

    <div >
        <div id="tt" class="easyui-tabs" >
          
        </div>
    </div>
    <script type="text/javascript">
    $('#resource_platform_tree').tree({
		url: 'resourceTree',
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
				/* 	state:row.state, */
					away:'_self',
					/* url:'plantformlist?id='+row.id, */
					url:'resourcelist?id='+row.id+'&pid='+row.pid+'&resourceName='+row.resourceName,
					attributes :{
			            treeType:"资源",
			        },
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
							/* 	state:row.state, */
							away:'_self',
							url:'resourcelist?id='+row.id+'&pid='+row.pid+'&resourceName='+row.resourceName,
							attributes :{
					            treeType:"资源",
					        },		
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
    
    $(function () {
        $('#tt').tabs({
            onLoad: function (panel) {
                var plugin = panel.panel('options').title;
            }
        });

        //加载默认页
        //$('#ifr-main').attr('src', 'resource/resourcelist');
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