//var treeRow =parent.$('#org_user_tree').tree('getSelected');
// alert(treeRow.subjectType)

if(treeRow.subjectType=='1'){
	var orgId =$('#orgId').val();
	var dg = $('#dg')
	dg.datagrid({
		url: 'orgBasicInfo?orgId='+orgId,
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
		           {
		            	 field : 'delFlag',
		            	 title : "delFlag",
		            	 hidden : true
		             },
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
			title:'机构详情',
		});
		$('#win').window('open');
	}
}