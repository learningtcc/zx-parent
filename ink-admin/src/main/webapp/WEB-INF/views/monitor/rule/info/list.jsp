<%@ page import="com.ink.monitor.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>业务监控维护</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
	<script type="text/javascript" src="${yk}/static/common/maintain.js"></script>
</head>
<body>
<div class="title01">
	<h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/monitor/rule/info/list.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
		<tr>	
			<td class="txtr" style="width: 100px">平台系统：</td>
			<td>
				&nbsp;<select id="sysCode" name="sysCode" class="select02"></select>
			</td>
			<td class="txtr" style="width: 100px">功能模块：</td>
			<td>
				&nbsp;<select id="moduleCode" name="moduleCode" class="select02"></select>
			</td>
			<td class="txtr" style="width: 100px">业务模块：</td>
			<td>
				&nbsp;<select id="infoCode" name="infoCode" class="select02"></select>
			</td>
			<td class="txtr" style="width: 100px">预警间隔（单位分钟）：</td>
			<td>
				&nbsp;<input value="<c:out value="${warnInterval}"/>" id="warnInterval" name="warnInterval" maxlength="10"/>
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
	<h2>业务监控列表</h2>
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
			 	<th>序号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>平台系统<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>功能模块<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>业务模块<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>预警间隔（单位分钟）<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>预警阀值<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>预警频次<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>当前已报警次数<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>创建时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>更新时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
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
					<div>
						<yk:code2name tableName="systerm_source" columnName="name" where="code=?" paramValue="${entry.sysCode}" emptyTip="${entry.sysCode}" serviceName="monitorDubboBaseService"/>
					</div>
				</td>
				<td>
					<div>
						<yk:code2name tableName="systerm_module" columnName="name" where="code=?" paramValue="${entry.moduleCode}" emptyTip="${entry.moduleCode}" serviceName="monitorDubboBaseService"/>
					</div>
				</td>
				<td>
				<div>
					<yk:code2name tableName="systerm_info" columnName="name" where="code=?" paramValue="${entry.infoCode}" emptyTip="${entry.infoCode}" serviceName="monitorDubboBaseService"/>
				</div>
				</td>
				<td>
				<div><c:out value="${entry.warnInterval}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.warnThreshold}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.warnFrequency}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.currentWarnCount}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.createTimeString}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.updateTimeString}"/></div>
				</td>
				<td>
						<a href="#" onclick="show('${entry.id}')">详情</a>&nbsp;
						<a href="#" onclick="edit('${entry.id}')">修改</a>&nbsp;
						<a href="#" onclick="del('${entry.id}')">删除</a>&nbsp;
				</td>
			</tr>
			</c:forEach>
			</table>
     	 <yk:page url="${yk}/monitor/rule/info/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">
function show(id){
	window.open('${yk}/monitor/rule/info/show.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}
function edit(id){
	window.open('${yk}/monitor/rule/info/edit.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}

$(function(){
	
	$('#btnedit').click(function(){
		var $id = $('[name=ids]:checkbox:checked');
		
		if($id.length==0){
			alert("请选择要修改的信息！");
			return false;
		}
		window.open('${yk}/monitor/rule/info/edit.do?'+$('#pkName').val()+'='+$id.val(),'','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btnadd').click(function(){
		window.open('${yk}/monitor/rule/info/create.do','','height=600,width=600,resizable=yes,location=no,scrollbars');
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
			$('#sysCode').val('');
			$('#moduleCode').val('');
			$('#infoCode').val('');
			$('#warnInterval').val('');
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
		$.post( "${yk}/monitor/rule/info/delete.do", {"items":ids.join(',')},
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


