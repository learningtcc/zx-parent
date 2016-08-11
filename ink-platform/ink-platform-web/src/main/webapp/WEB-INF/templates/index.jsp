<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.jasig.cas.client.validation.Assertion" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
    String name ="";
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    HttpSession ss = request.getSession();
    final Assertion assertion = session != null ? (Assertion) ss.getAttribute("yinker_sso_user") : null;
    if(assertion!= null){
        name = assertion.getPrincipal().getName();
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title> 银客权限管理平台</title>
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
<body class="easyui-layout" data-options="fit:true"
	style="text-align: left">
	<div data-options="region:'north' ,border:false"
		style="background: #666; text-align: center">
		<div id="header-inner">
			<table style="width: 100%;">
				<tr>
					<td rowspan="2" style="width: 20px;"></td>
					<td style="height: 52px;">
						<div style="color: #fff; font-size: 22px; font-weight: bold;">
							<a href="#"
								style="color: #fff; font-size: 22px; font-weight: bold; text-decoration: none">银客权限管理后台</a>
						</div>
						<div style="color: #fff">
							<a href="#" style="color: #fff; text-decoration: none">权限平台</a>
						</div>
					</td>
					<td style="padding-right: 5px; text-align: right; vertical-align: bottom;">
					
					<div id="topmenu">
					 <span>欢迎 <%=name%> 登录权限管理平台</span>
					</div>
							
						<div id="topmenu">
							<a href="login">显示首页</a> |

							<a href="javascript:void(0)" onclick="resetPwd()">修改密码</a> |
						
							<a href="javascript:void(0)" onclick="logOut()">退出</a>
							 <form method="post" id="form1" name="form1" action="https://sso.yinker.com:9443/sso/logout">
			  			  		<input type="hidden" name="service" id="service" value="${loginOutService}">
							</form>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	
	<input id="userid" type="hidden" value="${userId }">
	<script type="text/javascript">
	var service = $("#service").val();
	console.info(service);
	function logOut(){
		//$.ajax('https://sso.yinker.com:9443/sso/logout',{service:service},function(str){				});
		 $('#form1').submit();
	}
    function resetPwd(){
         $('#win').dialog('open').dialog('center').dialog('setTitle','修改密码');		 
    }
    //from表单提交
	function submitForm(){
		$.extend($.fn.validatebox.defaults.rules, {
		    equals: {
				validator: function(value,param){
					return value == $(param[0]).val();
				},
				message: '密码不匹配.'
		    }
		});
	     $('#reSetPwd').form('submit',{
            url: 'editPwd',
            onSubmit: function(){
                return $(this).form('validate');
            },
            success: function(result){
               console.info(result);
                if (result){
                        $.messager.show({
                            title: 'true',
                            msg: '操作成功！'
                        });
                        $('#win').window('close'); 
                }else {
                	 $.messager.show({
                            title: 'Error',
                            msg: '操作失败！'
                        });
                }
            }
        });
    }
    </script>
	<div id="workbench" data-options="region:'west' " title="我的工作台"
		style="width: 180px; padding: 5px;">
		<ul id="menu_tree"
			data-options="url:'',method:'get',animate:true, onClick: menuClickHandler, cache: false"></ul>

	</div>
	<div data-options="region:'center'">
		<div id="tt" class="easyui-tabs"
			data-options="region:'center',fit:true,border:false" plain="true">
			<div title="管理中心" iconcls="icon-home" bodyCls="p5">
				<iframe id="ifr-main" frameborder="0" src=""></iframe>
			</div>
		</div>
	</div>
	<div data-options="region:'south' "
		style="background-color: #eeeeee; height: 22px; line-height: 22px; padding-left: 10px; font-size: 12px; color: #999999; text-align: left;">
		<span>© 2015 版权所有</span>
	</div>
	<div id="win" class="easyui-dialog"
		style="width: 400px; height: 200px; top: 30px"
		data-options="iconCls:'icon-save',closed:true ,
				buttons: [{
					text:'提交',
					iconCls:'icon-ok',
					handler:function(){
						submitForm();
					}
				},{
					text:'重置',
					handler:function(){
						clearForm();
					}
				}]
			">
		<div style="text-align: center; padding: 10px">
			<form id="reSetPwd" method="post">
				<table cellpadding="5" cellspacing="0" style="width: 100%">
					<tr>
						<td>新密码 :</td>
						<td><input id="pwd" name="pwd" type="password"
							class="easyui-validatebox" data-options="required:true" data-options="validType:'minLength[5]'">
						</td>
					</tr>
					<tr>
						<td>确认新密码:</td>
						<td><input id="rpwd" name="rpwd" type="password"
							class="easyui-validatebox" required="required"
							validType="equals['#pwd']"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<script type="text/javascript">
    $('#menu_tree').tree({
		url: 'getResource',
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
			if (!exists(rows, row.pid) && row.rcode=="2"){
			
				nodes.push({
				    id:row.id,
					text:row.resourceName,
					url:row.permission
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
				if (row.pid == node.id && row.rcode=="2"){
				
					var child = {    
							id:row.id,
							text:row.resourceName,
							url:row.permission
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
        $('#ifr-main').attr('src', 'main');
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
</html>
