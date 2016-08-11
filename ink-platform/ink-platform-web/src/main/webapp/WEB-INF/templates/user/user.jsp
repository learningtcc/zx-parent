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
	 <div region="west" border="true" split="true" title="" style="width: 150px;  padding: 5px;float: left;">
        <ul id="menu_tree" data-options="url:'userInfo',method:'get',animate:true, onClick: menuClickHandler, cache: false,lines:true"></ul>
    </div>
   
    <div region="center" border="true">
        <div id="tt" class="easyui-tabs" fit="true" border="false" plain="true" style="height:400px;">
          
        </div>
    </div>
    <script type="text/javascript">
    $(function () {
        $('#tt').tabs({
            onLoad: function (panel) {
                var plugin = panel.panel('options').title;
            }
        });

        //加载默认页
        $('#ifr-main').attr('src', 'aa');
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
                content: '<iframe frameborder="0" src="' + plugin + '"></iframe>'
            });
        }
    }
</script>
</body>
</html>