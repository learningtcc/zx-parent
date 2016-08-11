
//获取用户的父组织机构
var node = parent.$('#org_user_tree').tree('getSelected');
var parentOrg = parent.$('#org_user_tree').tree('getParent',node.target);
var userId = node.id;
//var parentOrgId =nodes.id
//alert(parentOrg.id);
if(node.subjectType=='2'){
	var dg = $('#dg')
	dg.datagrid({
		url : 'orgUserBasicInfo?userId=' + userId +'&parentOrgId='+parentOrg.id,
		fitColumns : true,
		pagination : true,
		remoteFilter : true,
		rownumbers : true,
		pageList : [ 10 ],//可以设置每页记录条数的列表  
		pageSize : 10,//每页显示的记录条数，默认为10  
		/* 		queryParams : params, */
		sortName : 'createTime',
		sortOrder : 'desc',
		remoteSort : false,
		singleSelect : true,
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
		            	 field : 'delFlag',
		            	 title : "delFlag",
		            	 hidden : true
		             },
		             {
		            	 field : 'orgId',
		            	 title : "orgId",
		            	 hidden : true
		             },
		             {
		            	 field : 'roleId',
		            	 title : "roleId",
		            	 hidden : true
		             },
		             {
		            	 field : 'userOrgId',
		            	 title : "userOrgId",
		            	 hidden : true
		             },
		             {
		            	 field : 'userRoleId',
		            	 title : "userRoleId",
		            	 hidden : true
		             },
		             {
		            	 field: 'remark',
		            	 title: "remark",
		            	 hidden:true
		             },
		             {
		            	 field : 'username',
		            	 title : "名称",
		            	 align : 'center',
		            	 width : 100,
		            	 formatter : function(value, rowData,
		            			 rowIndex) {
		            		 url='userDetails?id='+rowData.id+'&titleCode='+rowData.titleIdentify;
		            		 return "<a href='javascript:;'  onclick='openWindow(\""
		            		 + url
		            		 + "\")'><font color='blue'>"
		            		 + value + "</font></a>";
		            	 }
		             },
		             {
		            	 field : 'subjectType',
		            	 title : "主体类型",
		            	 align : 'center',
		            	 width : 100,
		            	 formatter:function(value){
		            		 return convertSubjectType(value);
		            	 }
		             },

		             {
		            	 field : 'orgName',
		            	 title : "上级机构",
		            	 align : 'center',
		            	 width : 100,
		             },
		             {
		            	 field : 'roleName',
		            	 title : "角色",
		            	 /*width : 100,*/
		             },
		             {
		            	 field : 'title',
		            	 title : "职务",
		            	 align : 'center',
		            	 width : 100,
		             },
		             {
		            	 field : 'titleIdentify',
		            	 title : "职务标识",
		            	 align : 'center',
		            	 width : 100,
		            	 formatter:function(value){
		            		 return convertTitle(value);
		            	 }
		             },
		             {
		            	 field : 'status',
		            	 title : "状态",
		            	 align : 'center',
		            	 width : 100,
		            	 formatter:function(value){
		            		 return convertRoleStatus(value);
		            	 }
		             },
		             {
		            	 field : 'creator',
		            	 title : "创建人",
		            	 align : 'center',
		            	 width : 100,									
		             },
		             {
		            	 field : 'createTime',
		            	 title : "创建时间",
		            	 align : 'center',
		            	 width : 100,
		            	 formatter : function(value, rowData,
		            			 rowIndex) {
		            		 return new Date(value).dateFormat ("yyyy-MM-dd hh:mm:ss");
		            	 }
		             },
		             {
		            	 field : 'updateTime',
		            	 title : "修改时间",
		            	 align : 'center',
		            	 width : 100,
		            	 formatter : function(value, rowData,
		            			 rowIndex) {
		            		 if(value==null){
		            			 return null;
		            		 }else{
		            			 return new Date(value).dateFormat ("yyyy-MM-dd hh:mm:ss");

		            		 } 
		            	 }
		             } ] ]
	});

	function openWindow(url) {
		$('#win').window({
			href : url,
			title : '用户详情'
		});
		$('#win').window('open');
	}
}