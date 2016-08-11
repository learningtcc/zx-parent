
//下级机构
	$('#t-tab').tabs({  
	      border:false,  
	      onSelect:function(title){  
	       //   alert(title+' is selected');
	        var pp = $('#t-tab').tabs('getSelected');  
	    	//用户
	    	//获取用户的父组织机构
	    	var node = parent.$('#org_user_tree').tree('getSelected');
	    	var orgId =node.id;
	    	if(node.subjectType=='2'){
	    		orgId =node.parentOrgId;
	    	}
	    	//console.info(title)
	    	if(title=='下级机构'){
	    	$('#dg_tab').datagrid({
	      		url: 'orgInfoList?orgId='+orgId,
	      		fitColumns:true,
	      		 pagination: true,
	      		remoteFilter: true,
	      		rownumbers: true,
	      		pageList: [10],//可以设置每页记录条数的列表  
	      		pageSize: 10,//每页显示的记录条数，默认为10  
	      		sortName:'createTime',
	      		sortOrder:'desc',
	      		remoteSort:false,
	      		singleSelect:false,
	      		columns: [
/*hidden:true*/
	      			[{	field: 'ck',checkbox: true	}, 
	      			 {	field: 'id',    title: "id",   hidden:true  	},
	      			{	field: 'orgName',	title: "名称",	align:'center',width:100,
	      				formatter:function(value,rowData,rowIndex){
					         url='orgDetails?id='+rowData.id;
					         return "<a href='javascript:;'  onclick='openWindow(\""
								+ url
								+ "\")'><font color='blue'>"
								+ value + "</font></a>";
					}  
	      			    
	      			}, 
	      			 {	field: 'subjectType',title: "主体类型",align:'center',width:100,
	      				formatter:function(value){
							return convertSubjectType(value);
						}
	      			}, 
	      			{
	      				field: 'parentName',
	      				title: "上级机构",
	      				align:'center',
	      				width:100,
	      			},{
	      				field: 'status',
	      				title: "状态",
	      				align:'center',
	      				width:100,
	      				formatter:function(value){
	    					return convertRoleStatus(value);
	    					}
	      			}, {
	      			    field: 'userName',
	      				title: "创建人",
	      				align:'center',
	      				width:100,
	      			}, {
	      				field: 'createTime',
	      				title: "创建时间",
	      				align:'center',
	      				width:100,
	      				formatter:function(value,rowData,rowIndex){
							return new Date(value).dateFormat ("yyyy-MM-dd hh:mm:ss");
}
	      			}, {
	      				field: 'updateTime',
	      				title: "修改时间",
	      				align:'center',
	      				width:100,
	      				formatter:function(value,rowData,rowIndex){
	      					 if(value==null){
								 return null;
							 }else{
								return new Date(value).dateFormat ("yyyy-MM-dd hh:mm:ss");

							 }}	
	      			}]
	      		]
	      	});
	    	}
	    	
	    	if(title=='机构直属用户'){
	    	//var parentOrg = parent.$('#org_user_tree').tree('getParent',node.target);
     	$('#dg_user').datagrid({
				url : 'allUserInfo?orgId='+orgId,
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
				singleSelect : false,
				columns : [ [
						{
							field : 'ck',
							checkbox : true
						},
						 {
			            	 field : 'delFlag',
			            	 title : "delFlag",
			            	 hidden : true
			             },
						{
							field : 'id',
							title : "id",
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
	    	}
	    	if(title=='机构全部用户'){
	    		console.info('ss;');
     	$('#dg_alluser').datagrid({
			url : 'allUserUderOrg?orgId='+orgId,
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
			singleSelect : false,
			columns : [ [
					{
						field : 'ck',
						checkbox : true
					},
					 {
		            	 field : 'delFlag',
		            	 title : "delFlag",
		            	 hidden : true
		             },
					{
						field : 'id',
						title : "id",
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
	    	}
	      }  
	  });  
	function openWindow(url){
	    $('#win').window({
	    	href:url,
	    	title:'机构详情',
	    });
	    $('#win').window('open');
	    }