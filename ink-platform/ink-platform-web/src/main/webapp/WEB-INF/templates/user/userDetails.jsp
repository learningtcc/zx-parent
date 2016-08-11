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
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
</head>
<body>

		<div  class="t_table" style="text-align: center; padding: 10px">
	<table  cellpadding="50" cellspacing="10" style="width: 100%"
				align="center">
				<tr>
						<td>用户名	:</td>
						<td>
						  <span>${secUser.username }</span>
						</td>
					</tr>
					<tr>
						<td>登录名:</td>
						<td>
						<span>${secUser.loginName }</span>
							
						</td>
					</tr>
				
					<tr>
						<td>职务:</td>
						<td>
						<span>${secUser.title }</span>
							
						</td>
					</tr>
					<tr>
						<td>上级机构:</td>
						<td>
						<span>${parenOrg }</span>
						</td>
					</tr>
					<tr>
						<td>角色:</td>
						<td>
						<span>${roles }</span>
						</td>
					</tr>	
					 <tr>
						<td>状态:</td>
						<td>
						<c:if test="${secUser.status ==1}">
						<span>启用</span>
						</c:if>
						<c:if test="${secUser.status ==2}">
						<span>停用</span>
						</c:if>
						<c:if test="${secUser.status ==9}">
						<span>注销</span>
						</c:if>
						</td>
					</tr> 
					<tr>
						<td>备注:</td>
						<td>
						<span>${secUser.remark }</span>
						
						</td>
					</tr>
					<tr>
						<td>创建人:</td>
						<td>
						<span>${secUser.creator}</span>
						</td>
					</tr>
					<tr>
						<td>创建时间:</td>
						<td>
						<span><fmt:formatDate value="${secUser.createTime }" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></span>
						</td>
					</tr>
					<tr>
						<td>修改时间:</td>
						<td>
					<span><fmt:formatDate value="${secUser.updateTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></span>
						
						</td>
					</tr>
					<!--  <tr>
						<td>角色:</td>
						<input id=""  type="hidden" name="userRoleId" required/>
						<input id="roleId"  type="" name="roleId" />
						<td><select id="roleTree" class="easyui-combotree"  data-options="url:'allRole',required:true" name="roleName" style="width:200px;"/></td>
					</tr>
					<tr>
						<td>上级机构:</td>
						<input id=""  type="hidden" name="userOrgId" required/>
						<input id=""  type="hidden" name="orgId" required/>
						<td><select id="orgTree" class="easyui-combotree" url=" " name="orgName" style="width:200px;"/></td>
					</tr>  -->
				</table>
				</div>
</body>
</html>