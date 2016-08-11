<%@ page import="com.ink.monitor.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>监控人员配置维护</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
	<h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/monitor/rule/userule/list.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
		<tr>	
			<td class="txtr" style="width: 100px">用户ID：</td>		
			<td>
				&nbsp;<input value="<c:out value="${userId}"/>" id="userId" name="userId" maxlength="10"/>
			</td>
			<td class="txtr" style="width: 100px">系统代码：</td>		
			<td>
				&nbsp;<input value="<c:out value="${sysCode}"/>" id="sysCode" name="sysCode" maxlength="10"/>
			</td>
			<td class="txtr" style="width: 100px">模块代码：</td>		
			<td>
				&nbsp;<input value="<c:out value="${moduleCode}"/>" id="moduleCode" name="moduleCode" maxlength="10"/>
			</td>
			<td class="txtr" style="width: 100px">业务代码：</td>		
			<td>
				&nbsp;<input value="<c:out value="${infoCode}"/>" id="infoCode" name="infoCode" maxlength="10"/>
			</td>
		</tr>	

	</table>
	<br/>
	<div align="center">
		<input type="submit" class="btn01" value="查 询" onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('0');"/>
		<input type="button" class="btn01" value="清 空" id="btnclear"/>
	</div>
</form>
	<br></br>
	<div class="title01">
	<h2><%=MonitorUserRule.TABLE_ALIAS%>列表</h2>
</div>
		<div class="groupbtn">
			<a href="javascript:void(0)" title="新建" class="btn001" id="btnadd">新增</a>
			<a href="javascript:void(0)" title="修改" class="btn002" id="btnedit">修改</a>
			<a href="javascript:void(0)" title="删除"  class="btn004" id="btndelete">删除</a>
		</div>
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb" class="table02">
			<thead>
			 <tr>
			 	<th><input id="allChoose" type="checkbox"/></th>
			 	<th>序号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>用户ID<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>系统代码<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>模块代码<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>业务代码<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>创建时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
			    <th>操作<div style="width: 75px; height: 1px; overflow: hidden;"></div></th>
	        </tr>
	        </thead>
			<c:forEach var="entry" items="${page.result}" varStatus="v">
			<tr>
				<td><input name="ids" type="checkbox" value="${entry.id}"/>
				<input name="pkName" type="hidden" id="pkName" value="id"/>
				</td>
				<td>${v.index + 1 }</td>
				<td>
				<div><c:out value="${entry.userId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.sysCode}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.moduleCode}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.infoCode}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.createTimeString}"/></div>
				</td>
				<td>
						<a href="#" onclick="show('${entry.id}')">详情</a>&nbsp;
						<a href="#" onclick="edit('${entry.id}')">修改</a>&nbsp;
						<a href="#" onclick="del('${entry.id}')">删除</a>&nbsp;
				</td>
			</tr>
			</c:forEach>
			</table>
     	 <yk:page url="${yk}/monitor/rule/userule/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">
function show(id){
	window.open('${yk}/monitor/rule/userule/show.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}
function edit(id){
	window.open('${yk}/monitor/rule/userule/edit.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}

$(function(){
	
	$('#btnedit').click(function(){
		var $id = $('[name=ids]:checkbox:checked');
		
		if($id.length==0){
			alert("请选择要修改的信息！");
			return false;
		}
		window.open('${yk}/monitor/rule/userule/edit.do?'+$('#pkName').val()+'='+$id.val(),'','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btnadd').click(function(){
		window.open('${yk}/monitor/rule/userule/create.do','','height=600,width=600,resizable=yes,location=no,scrollbars');
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
			$('#userId').val('');
			$('#sysCode').val('');
			$('#moduleCode').val('');
			$('#infoCode').val('');
			$('#createTimeBegin').val('');
			$('#createTimeEnd').val('');
	});

	//ajax获取系统代码
	var systemUrl = '${yk}/monitor/dict/source/listForAjax.do';
	executeAjax(systemUrl,"sysCode",'<c:out value="${sysCode}"/>',true);

	//系统代码联动模块代码
	$("#sysCode").change(function(){
		var optionValue = this.value;
		if (optionValue == ""){
			$("#moduleCode").empty();
			return ;
		}
		var moduleUrl = '${yk}/monitor/dict/module/listAjaxForSystemCode.do?systemCode='+optionValue;
		executeAjax(moduleUrl,"moduleCode",'<c:out value="${moduleCode}"/>',true);

	});

	$("#moduleCode").change(function(){
		var optionValue = this.value;
		if (optionValue == ""){
			$("#infoCode").empty();
			return ;
		}
		var infoUrl = '${yk}/monitor/dict/info/listAjaxForModuleCode.do?moduleCode='+optionValue;
		executeAjax(infoUrl,"infoCode",'<c:out value="${infoCode}"/>');

	});
});

function del(id){
	var ids = [];
	ids.push(id);
	v_deleteItems(ids);
}

function v_deleteItems(ids){
	if(ids.length>0 && confirm("确定要删除吗？")){
		$.post( "${yk}/monitor/rule/userule/delete.do", {"items":ids.join(',')},
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


