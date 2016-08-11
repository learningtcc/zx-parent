
    function  qq(value,name){
      // alert(name);
        var disable=$('input[name="enable"]:checked').val();
		var cancel=$('input[name="cancel"]:checked').val();
		//默认选择第一个标签
		if(name!=null){
			$('#t-tab').tabs('select', 0);
		}
		if(name =="orgName"){
		
			//查询组织机构名称
			url ='fuzzyQueryOrg?disable='+disable+'&cancel='+cancel+'&orgName='+value;
			var orgId =$('#orgId').val();
			var dg = $('#dg')
		 	dg.datagrid({
				url: url,
				fitColumns:true,
				pagination: true,
				remoteFilter: true,
				rownumbers: true,
				pageList: [10],//可以设置每页记录条数的列表  
				pageSize: 10,//每页显示的记录条数，默认为10  
				//queryParams:params,
				sortName:'createTime',
				sortOrder:'desc',
				remoteSort:false,
				singleSelect:false,
				columns: [
					[{	field: 'ck',checkbox: true	}, 
					 {	field: 'id',    title: "id",     hidden:true	},
					 {	field: 'subjectType',title: "主题类型",align:'center',width:100,
						 formatter:function(value){
								return convertSubjectType(value);
							}
					}, 
					{	field: 'orgName',	title: "名称",	align:'center',width:100,
						formatter:function(value,rowData,rowIndex){
					         url='orgDetails?id='+rowData.id;
					         return "<a href='javascript:;'  onclick='openWindow(\""
								+ url
								+ "\")'><font color='blue'>"
								+ value + "</font></a>";
					}  
					},  {
						field: 'parentName',
						title: "上级机构",
						align:'center',
						width:100,
					}, {
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
							 return new Date(value).dateFormat ("yyyy-MM-dd hh:mm:ss");}
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

		            		 } }	
					}]
				]
			});
			function openWindow(url){
			    $('#win').window({
			    	href:url,
			    	title:'组织详情',
			    });
			    $('#win').window('open');
			    }
		}else if(name =="userName" || name =="title"){
		//	url='fuzzyQueryUserView?disable='+disable+'&cancel='+cancel+'&queryName='+value;
			var dg = $('#dg')
			var objUrl;
			if(name=="userName"){
				objUrl	='fuzzyQueryUser?disable='+disable+'&cancel='+cancel+'&queryName='+value;
			}else if(name =="title"){
				objUrl	='fuzzyQueryUser?disable='+disable+'&cancel='+cancel+'&title='+value;
	
			}
			dg.datagrid({
						url : objUrl,
						fitColumns : true,
						pagination : true,
						remoteFilter : true,
						rownumbers : true,
						pageList : [10],//可以设置每页记录条数的列表  
						pageSize : 10,//每页显示的记录条数，默认为10  
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
									title : "主题类型",
									align : 'center',
									width : 100,
									formatter:function(value){
										return convertSubjectType(value);
									}
								},
								{
									field : 'roleName',
									title : "角色",
								},
								{
									field : 'orgName',
									title : "上级机构",
									align : 'center',
									width : 100,
								},
								{
									field : 'title',
									title : "职务",
									align : 'center',
								//	width : 100,
								},
								{
									field : 'titleIdentify',
									title : "职务标识",
									align : 'center',
									//width : 100,
									formatter:function(value){
										return convertTitle(value);
									}
								},
								{
									field : 'status',
									title : "用户状态",
									align : 'center',
									width : 100,
								formatter:function(value, rowData,
				            			 rowIndex) {
									/*console.info(rowData.delFlag);
									if(rowData.delFlag=='1'){
										value='9'
									}*/
									return convertRoleStatus(value);
								}
								},
								{
									field : 'ustatus',
									title : "用户组织机构关系状态",
									align : 'center',
								//	width : 100,
								formatter:function(value, rowData,
				            			 rowIndex) {
									console.info(rowData.delFlag);
								/*	if(rowData.delFlag=='1'){
										value='9'
									}*/
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
      
	function openWindow(url) {
		$('#win').window({
			href : url,
			title : '用户详情'
		});
		$('#win').window('open');
	};