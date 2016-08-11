<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="static/js/easyui/1.4.2/themes/bootstrap/easyui.css"
	rel="stylesheet" type="text/css" />
<link href="static/js/easyui/1.4.2/themes/icon.css" rel="stylesheet"
	type="text/css" />
<link href="static/css/main.css" rel="stylesheet" type="text/css" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<script src="static/js/jquery/1.8.0/jquery.min.js"
	type="text/javascript"></script>
<script src="static/js/easyui/1.4.2/jquery.easyui.min.js"
	type="text/javascript"></script>
<script src="static/js/global.js" type="text/javascript"></script>
<script src="static/js/easyui/1.4.2/locale/easyui-lang-zh_CN.js"></script>
 <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>   

<script type="text/javascript">
//获取浏览器页面可见高度和宽度
var _PageHeight = document.documentElement.clientHeight,
    _PageWidth = document.documentElement.clientWidth;

//计算loading框距离顶部和左部的距离（loading框的宽度为215px，高度为61px）
var _LoadingTop = _PageHeight > 61 ? (_PageHeight - 61) / 2 : 0,
    _LoadingLeft = _PageWidth > 215 ? (_PageWidth - 215) / 2 : 0;

//加载gif地址
var Loadimagerul="";

//在页面未加载完毕之前显示的loading Html自定义内容
var _LoadingHtml = '<div id="loadingDiv" style="position:absolute;left:0;width:100%;height:' + _PageHeight + 'px;top:0;background:#f3f8ff;opacity:1;filter:alpha(opacity=80);z-index:10000;"><div style="position: absolute; cursor1: wait; left: ' + _LoadingLeft + 'px; top:' + _LoadingTop + 'px; width:100px;; height: 57px; line-height: 57px; padding-left: 50px; padding-right: 5px; background: #fff url('+Loadimagerul+') no-repeat scroll 5px 12px; border: 2px solid #95B8E7; color: #696969; font-family:\'Microsoft YaHei\';">玩命加载中...</div></div>';

//呈现loading效果
document.write(_LoadingHtml);

//监听加载状态改变
document.onreadystatechange = completeLoading;

//加载状态为complete时移除loading效果
function completeLoading() {
    if (document.readyState == "complete") {
        var loadingMask = document.getElementById('loadingDiv');
        loadingMask.parentNode.removeChild(loadingMask);
    }
}
</script>
</head>
<body>

	
 	<shiro:hasAnyRoles name="超级管理员,管理员,test">   

	<div style="text-align: left;">
		<div class="easyui-panel" style="padding: 5px;">
		 <shiro:hasAnyRoles name="超级管理员,管理员">  
			<a href="#" class="easyui-menubutton" id="mb"
				data-options="menu:'#adds',iconCls:'icon-add'">添加</a> <a href="#"
				class="easyui-menubutton"
				data-options="menu:'#edits',iconCls:'icon-edit'">编辑</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-remove'" id="delete">删除</a> 
				
			<div id="adds" style="width: 100px;">
				<!-- <div onclick="javascript:alert('sub21')">添加用户</div> -->
				<div onclick="addUser()">添加用户</div>
				<div onclick="addOrg()">添加机构</div>
			</div>
			<div id="edits" style="width: 100px;">
				<div onclick="editUser()">编辑用户</div>
				<div onclick="editOrg()">编辑机构</div>
			</div>
				</shiro:hasAnyRoles>   
				 <shiro:hasPermission  name="moveOrg">   
					<a href="javascript:void(0)" class="easyui-linkbutton"data-options="iconCls:'icon-back'" id="moveOrg">移动</a> 
				 </shiro:hasPermission>  
				 <shiro:hasPermission name="setVicOrg"> 
					<a	href="javascript:void(0)" class="easyui-linkbutton" id="setViceJob"data-options="iconCls:'icon-undo'">设置副岗</a> 
				</shiro:hasPermission>  
				 <shiro:hasPermission name="resetPwd"> 
						<a href="javascript:void(0)" class="easyui-linkbutton" id="resetPwd" data-options="iconCls:'icon-reload'">密码重置</a> 
				</shiro:hasPermission>  
				 <shiro:hasPermission name="roleAuth">   
					<a href="javascript:void(0)" class="easyui-linkbutton" id="roleAuth"data-options="iconCls:'icon-redo'">角色授权</a>
				 </shiro:hasPermission>  
				<shiro:hasPermission name="addUserefef"> 
				 	<a	href="javascript:void(0)" class="easyui-linkbutton" id="allUser"data-options="iconCls:'icon-search'">显示全部用户</a>
				</shiro:hasPermission>  



		<input id="enable" name="enable" type="checkbox">显示停用 
		<input id="cancel" name="cancel" type="checkbox">显示注销 
		<input id="search" class="easyui-searchbox" style="width:210px" data-options="searcher:qq,menu:'#mm'"></input>
		<div id="mm" style="width: 120px">
			<div data-options="name:'',iconCls:'icon-search'">--查询条件--</div>
			<div data-options="name:'orgName'">组织机构名称</div>
			<div data-options="name:'userName'">用户名称</div>
			<div data-options="name:'title'">用户职务</div>
		</div>

	</div>	
	</div>
	<div id="t-tab" class="easyui-tabs">
		<div title="基本信息" style="padding: 40px;">
			<table id="dg" ></table>
		</div>
		<div title="下级机构" closable="true" style="padding: 40px;">
			<table id="dg_tab" class="easyui-datagrid" toolbar="#toolbar" ></table>
		</div>
		<div title="机构直属用户" style="padding: 40px;">
			<table id="dg_user" class="easyui-datagrid" toolbar="#toolbar"></table>
		</div>
		<div title="机构全部用户" style="padding: 40px;">
			<table id="dg_alluser" class="easyui-datagrid" toolbar="#toolbar"></table>
		</div>
	</div>
 </shiro:hasAnyRoles>  
	<div id="dlg" class="easyui-dialog" title="添加机构"
		style="width: 400px; height: 400px; padding: 10px"
		data-options="iconCls:'icon-save',closed:true ,modal:true,
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
			<form id="addOrgForm" method="post">
				<table  style="width: 100%">
					<tr>
						<td>机构标识 :</td>
						<td><input id="" class="easyui-textbox" type="text"
							style="width: 200px;" name="orgCode" required /></td>
					</tr>
					<tr>
						<td>机构名称:</td>
						<td><input id="" class="easyui-textbox" type="text"
							style="width: 200px;" name="orgName" required /></td>
					</tr>
					<tr>
						<td>上级机构:</td>
						<td><input id="orgTree" class="easyui-combotree"
							name="parentOrgId" value="${id }"
							data-options="url:'',method:'get'" style="width: 200px;">
						</td>
					<tr>
						<td>状态:</td>
						<td><input required class="easyui-combobox" id="status"
							name="status" value="1" style="width: 200px;"
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
						<td><input id="" class="easyui-textbox" type="text"
							style="width: 200px;" name="remark" required /></td>
					<tr>
				</table>
			</form>
		</div>
	</div>
	<div id="user—dlg" class="easyui-dialog dlg" title="添加用户"
		style="width: 400px; height: 400px; padding: 10px"
		data-options="iconCls:'icon-save',closed:true ,modal:true,
				buttons: [{
					text:'提交',
					iconCls:'icon-ok',
					handler:function(){
						submitUserForm();
					}
				},{
					text:'重置',
					handler:function(){
						clearForm();
					}
				}]
			">
		<div style="text-align: center; padding: 10px">
			<form id="userForm" method="post">
				<table cellpadding="5" cellspacing="0" style="width: 100%">
					<tr>
						<td>用户名 :</td>
						<td><input id="" class="easyui-textbox" type="text"
							name="username" required style="width: 200px;" /></td>
					</tr>
					<tr>
						<td>登录名:</td>
						<td><input id="" class="easyui-textbox" type="text"
							name="loginName" required style="width: 200px;" /></td>
					</tr>
					<tr>
						<td>上级机构:</td>
						<td><input id="userOrgTree" class="easyui-combotree"
							name="orgId" value="${id }"
							data-options="url:'',method:'get',required:true"
							style="width: 200px;"></td>
					</tr>
					<tr>
						<td>职务:</td>
						<td><input id="title" class="easyui-textbox" type="text"
							name="title" required style="width: 200px;" /></td>
					</tr>
					<tr>
						<td>状态:</td>
						<td><input style="width: 200px;" required
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
					<tr>
						<!-- <td>角色:</td>
				<td><select class="easyui-combotree" url="allRole" name="roleId" style="width:100px;"/></td></tr>
				<tr> -->
						<td>备注:</td>
						<td><input style="width: 200px;" id="" class="easyui-textbox"
							type="text" name="remark" required /></td>
					<tr>
				</table>
			</form>
		</div>
	</div>
	<div id="edit_org" class="easyui-dialog dlg" title="编辑机构"
		style="width: 400px; height: 400px; padding: 20px"
		data-options="iconCls:'icon-save',closed:true ,modal:true,
				buttons: [{
					text:'提交',
					iconCls:'icon-ok',
					handler:function(){
						submitEditOrgForm();
					}
				},{
					text:'取消',
					handler:function(){
						clearForm();
					}
				}]
			">
		<div style="text-align: center; padding: 10px">
			<form id="editOrgForm" method="post">
				<table cellpadding="5" cellspacing="10" style="width: 100%"
					align="center">
					<tr>
						<td>主体类型 :</td>
						<td><c:if test="${secOrg.subjectType ==1}">机构</c:if></td>
					</tr>
					<tr>
						<td>机构标识 :</td>
						<td><input id="" class="easyui-textbox" type="text"
							name="orgCode" required style="width: 200px;" /></td>
					</tr>
					<tr>
						<td>机构名称:</td>
						<td><input id="" class="easyui-textbox" type="text"
							name="orgName" required style="width: 200px;" /></td>
					</tr>
					<tr>
						<td>上级机构:</td>
						<td><select id="editOrgTree" class="easyui-combotree" url=" "
							name="parentOrgId" style="width: 200px;" /></td>
					</tr>
					<tr>
						<td>状态:</td>
						<td><input style="width: 200px;" class="easyui-combobox"
							id="status" name="status"
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
						<td><input style="width: 200px;" id="" class="easyui-textbox"
							type="text" name="remark" required /></td>
					</tr>
					<tr>
						<td>创建人:</td>
						<td>${secOrg.userName }</td>
					</tr>
					<tr>
						<td>创建时间:</td>
						<td><span><fmt:formatDate value="${secOrg.createTime}"
									type="both" pattern="yyyy-MM-dd HH:mm:ss" /></span></td>
					</tr>
					<tr>
						<td>修改时间:</td>
						<td><span><fmt:formatDate value="${secOrg.updateTime}"
									type="both" pattern="yyyy-MM-dd HH:mm:ss" /></span></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div id="edit_user" class="easyui-dialog dlg" title="编辑用户"
		style="width: 400px; height: 420px; padding: 10px"
		data-options="iconCls:'icon-save',closed:true ,modal:true,
				buttons: [{
					text:'提交',
					iconCls:'icon-ok',
					handler:function(){
						submitEditUserForm();
					}
				},{
					text:'取消',
					handler:function(){
						clearForm();
					}
				}]
			">
			<div style="text-align: center; padding: 10px">	
		<form id="editUserForm" method="post">
			<table cellpadding="5" cellspacing="10" style="width: 100%"
				align="center">
				<tr>
					<td>主体类型 :</td>
					<td>用户</td>
				</tr>
				<tr>
					<td>用户名 :</td>
					<td><input style="width: 200px;" id="" class="easyui-textbox"
						type="text" name="username" required /></td>
				</tr>
				<tr>
					<td>登录名:</td>
					<td><input style="width: 200px;" id="" class="easyui-textbox"
						type="text" name="loginName" required /></td>
				</tr>
				 <tr>
						<td>上级机构:</td>
						
						<td><input id="" style="width: 200px;" disabled="disabled" class="easyui-textbox"  type="text" name="orgName" /></td>
					</tr> 
				<tr>
					<td>职务:</td>
					<td><input id="" style="width: 200px;" class="easyui-textbox"
						type="text" name="title" required /></td>
				</tr>			
				<tr>
					<td>状态:</td>
					<td><input style="width: 200px;" class="easyui-combobox"
						id="status" name="status"
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
											}]
											 ,
											required:true" />
					</td>
				</tr>
				<tr>
					<td>备注:</td>
					<td><input style="width: 200px;" id="" class="easyui-textbox"
						type="text" name="remark" required /></td>
				</tr>
				<tr>
					<td>创建人:</td>
					<td><input style="width: 200px;" id="" disabled="disabled"
						class="easyui-textbox" type="text" name="creator" required /></td>
				</tr>
				<tr>
					<td>创建时间:</td>
					<td><input style="width: 200px;" id="userCreattime"
						disabled="disabled" class="easyui-textbox" type="text"
						name="createTime" required /></td>
				</tr>
				<tr>
					<td>修改时间:</td>
					<td><input style="width: 200px;" id="userUpdateTime"
						disabled="disabled" class="easyui-textbox" type="text"
						name="updateTime" required /></td>
				</tr>

				<!-- <tr
					>
						<td>角色:</td>
						<input id=""  type="hidden" name="userRoleId" required/>
						<input id="roleId"  type="" name="roleId" />
						<td><select id="roleTree" class="easyui-combotree"  data-options="url:'allRole',required:true" name="roleName" style="width:200px;"/></td>
					</tr> -->
				
			</table>
		</form>
		</div>
	</div>
	<div id="win" class="easyui-window"
		style="width: 400px; height: 400px; top: 30px"
		data-options="iconCls:'icon-save',modal:true,closed:true  "></div>
	<div id="role_user_auth" class="easyui-dialog" title="角色授权"
		data-options="iconCls:'icon-save',modal:true,closed:true"
		style="width: 400px; height: 400px; padding: 10px">

		<form id="addUserOrgRole" method="post">
			<div class="easyui-panel" style="padding: 5px">
				<ul id="roleTree" class="easyui-tree"
					data-options="url:'',method:'get',animate:true,checkbox:true"></ul>
			</div>
		</form>

		<div style="margin: 20px 0;" align="center">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="submitRoleAuthForm()">确定</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" onclick="clearForm()">取消</a>
		</div>
	</div>
	<div id="allUserMes"></div>
	<input id="orgId" type="hidden" value="${id }">
	</div>
	<script type="text/javascript">
		var treeRow = parent.$('#org_user_tree').tree('getSelected');
		var url;
		function clearForm() {
			$('#addOrgForm').form('clear');
			$('#edit_user').dialog('close');
			$('#userForm').form('clear');
			$('#userForm').form('clear');
			$('#editOrgForm').form('clear');
			$('#editUserForm').form('clear');
			$('#edit_org').dialog('close'); // close the dialog
			$('#dg').datagrid('reload');
			$('#role_user_auth ').dialog('close');
		}
		/* var orgId = $('#orgId').val();
		var dg = $('#dg')
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
		} */
	</script>
	


	<script src="static/js/fuzzySearch.js"></script>
	<script src="static/js/org/addOrgOrUser.js"></script>
	<script src="static/js/org/allUser.js"></script>
	<script src="static/js/org/orgTree.js"></script> 
	<script src="static/js/org/orgInfo.js"></script>
	<script src="static/js/org/lowOrgInfo.js"></script>
	<script src="static/js/org/editOrgUser.js"></script>
	<script src="static/js/org/deleteOrgOrUser.js"></script>


	<script src="static/js/user/userInfo.js"></script>
	<script src="static/js/user/editUser.js"></script>
	<script src="static/js/user/moveOrg.js"></script>
	<script src="static/js/user/setViceJob.js"></script>
	<script src="static/js/user/reSetPwd.js"></script>
	<script src="static/js/user/roleAuth.js"></script>
</body>
</html>