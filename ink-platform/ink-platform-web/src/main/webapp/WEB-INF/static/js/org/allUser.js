		  	
		  	//显示全部用户
			  $('#allUser').click(function(){
					//$('#enable').is(':checked')
					
				//默认选择第一个标签
					$('#t-tab').tabs('select', 0);
					 var disable=$('#enable').is(':checked');
						var cancel=$('#cancel').is(':checked');
						var status=null;
						
						if(disable){
							status=2;
						}
						if(cancel){
							status=9;
						} 
						if(disable==true &&  cancel==true ){
							status ='';
						}
						if(disable==false && cancel==false ){
							status=1;
						}
					var	queryParams={		
							status:status
							};
	    		    dg.datagrid({
	    		    	title:'全部用户',
						url : 'allUser',
						fitColumns : true,
						pagination : true,
						remoteFilter : true,
						rownumbers : true,
						pageList : [10],//可以设置每页记录条数的列表  
						pageSize : 10,//每页显示的记录条数，默认为10  
				 		queryParams : queryParams,
						remoteSort : true,
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
					            	 field : 'delFlag',
					            	 title : "delFlag",
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
									/*formatter:function(value, rowData,
					            			 rowIndex) {
										console.info(rowData.delFlag);
										if(rowData.delFlag=='1'){
											return null;
										}else{
											return rowData.orgName;
										}
									}*/
									 
								},
								{
									field : 'roleName',
									title : "角色",
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
									},
								},
								{
									field : 'ustatus',
									title : "用户组织机构关系状态",
									align : 'center',
									//width : 100,
									formatter:function(value, rowData,
					            			 rowIndex) {
									//	console.info(rowData.ustatus);
										/*if(rowData.ustatus=='9'){
											value='9'
										}*/
										return convertRoleStatus(value);
									},
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
			  });