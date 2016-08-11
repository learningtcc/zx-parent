<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>用户列表</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
	<script type="text/javascript" src="${yk}/gen-commons/md5.js"></script>
</head>
<body>
<div class="title01">
	<h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/monitor/user/list.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
	<table width="80%" border="0" cellspacing="0" cellpadding="0" class="table00">
		<tr>	
			<td class="txtr" style="width: 100px">账号：</td>		
			<td><input value="<c:out value="${userName}"/>" id="userName" name="userName" maxlength="30"/>
			</td>
			<td class="txtr" style="width: 100px">姓名：</td>		
			<td><input value="<c:out value="${fullName}"/>" id="fullName" name="fullName" maxlength="30"/>
			</td>
		</tr>	
		<tr>	
			<td class="txtr" style="width: 100px">手机号：</td>		
			<td><input value="<c:out value="${mobile}"/>" id="mobile" name="mobile" maxlength="20"/>
			</td>
			<td class="txtr" style="width: 100px">邮箱：</td>		
			<td><input value="<c:out value="${email}"/>" id="email" name="email" maxlength="50"/>
			</td>
		</tr>	
		<tr align="center">	
			<td colspan="4" align="center" >
				<input type="submit" class="btn01" value="精确查询" onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('0');"/>
				<input type="submit" class="btn01" value="模糊查询" onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('1');"/>
				<input type="button" class="btn01" value="清 空" id="btnclear"/>
			</td>		
		</tr>	
	</table>
	<br/>
</form>
	<br></br>
	<div class="title01">
	<h2>用户列表</h2>
</div>
		<div class="groupbtn">
			<a href="javascript:void(0)" title="新建" class="btn001" id="btnadd">新增</a>
			<%--<a href="javascript:void(0)" title="修改" class="btn002" id="btnedit">修改</a>--%>
			<a href="javascript:void(0)" title="删除"  class="btn004" id="btndelete">删除</a>
		</div>
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb" class="table02">
			<thead>
			 <tr>
			 	<th><input id="allChoose" type="checkbox"/></th>
			 	<th>序号<div style="width: 5px; height: 1px; overflow: hidden;"></div></th>
				<th>账号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>姓名<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>手机号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>邮箱<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>告警类型<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>状态<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>更新时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
			    <th>操作<div style="width: 75px; height: 1px; overflow: hidden;"></div></th>
	        </tr>
	        </thead>
			<c:forEach var="entry" items="${page.result}" varStatus="v">
			<tr>
				<td><input name="ids" type="checkbox" value="${entry.userId}"/>
				<input name="pkName" type="hidden" id="pkName" value="userId"/>
				</td>
				<td>${v.index + 1 }</td>
				<td>
				<div><c:out value="${entry.userName}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.fullName}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.mobile}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.email}"/></div>
				</td>
				<td>
				<div>
					<yk:constantConvert htmlTag="text" dataTag="warnType" paramValue="${entry.warnType}" paramSplit="," emptyTip="未知" sysTag="monitor"/>
				</div>
				</td>
				<td>
				<div>
					<yk:constantConvert htmlTag="text" dataTag="status" paramValue="${entry.status}" emptyTip="未知" sysTag="monitor"/>
				</div>
				</td>
				<td>
				<div><c:out value="${entry.updateTimeString}"/></div>
				</td>
				<td>
					<a href="#" onclick="show('${entry.userId}')">详情</a>
					<a href="#" onclick="edit('${entry.userId}')">修改</a>
					<a href="#" onclick="choiceMonitorRuleInfo('${entry.userId}','${entry.fullName}')">功能监控</a>
					<a href="#" onclick="choiceMonitorServiceInfo('${entry.userId}','${entry.fullName}')">服务监控</a>
					<%--<a href="#" onclick="resetPassword('${entry.userName}')">重置密码</a>--%>
					<a href="#" onclick="del('${entry.userId}')">删除</a>

				</td>
			</tr>
			</c:forEach>
			</table>
     	 <yk:page url="${yk}/monitor/user/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">
function show(id){
	window.open('${yk}/monitor/user/show.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}
function edit(id){
	window.open('${yk}/monitor/user/edit.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}

$(function(){
	
	$('#btnedit').click(function(){
		var $id = $('[name=ids]:checkbox:checked');
		
		if($id.length==0){
			alert("请选择要修改的信息！");
			return false;
		}
		window.open('${yk}/monitor/user/edit.do?'+$('#pkName').val()+'='+$id.val(),'','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btnadd').click(function(){
		window.open('${yk}/monitor/user/create.do','','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btndelete').click(function(){
		if($('[name=ids]:checkbox:checked').length>0){
			var ids = [];
			$('[name=ids]:checkbox:checked').each(function(){ids.push($(this).val())});
			v_deleteItems(ids);
		}else{
			alert("请选择后进行删除操作！");
		}
		
	});
	
	$('#btnclear').click(function(){
			$('#userName').val('');
			$('#fullName').val('');
			$('#mobile').val('');
			$('#email').val('');
	});
});

function del(id){
	var ids = [];
	ids.push(id);
	v_deleteItems(ids);
}

//选择检测业务功能
function choiceMonitorRuleInfo(id,fullName){

	window.open('${yk}/monitor/rule/userule/choiceMonitorRuleInfo.do?userId='+id+'&fullName='+fullName,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}
//选择检测业务功能
function choiceMonitorServiceInfo(id,fullName){

	window.open('${yk}/monitor/service/userService/choiceMonitorServiceInfo.do?userId='+id+'&fullName='+fullName,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}

function resetPassword(userName){
	if(confirm("确定要重置密码吗？")){
		var password = faultylabs.MD5(faultylabs.MD5("yk123456"));
		$.post( "${yk}/monitor/user/resetPassword.do?userName="+userName +"&password="+password,
			function(data){
				if(data=='1'){
					alert('操作成功');
				}else{
					alert('操作失败');
				}
			}
		);
	}
}

function v_deleteItems(ids){
	if(ids.length>0 && confirm("确定要删除吗？")){
		$.post( "${yk}/monitor/user/delete.do", {"items":ids.join(',')},
				function(data){
					if(data=='1'){
						alert('删除成功');
						$('#queryForm').submit();
					}else{
						alert('删除失败');
					}
				}
		);
	}
};
</script>
</body>
</html>


