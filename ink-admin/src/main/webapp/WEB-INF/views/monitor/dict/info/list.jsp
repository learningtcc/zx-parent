<%@ page import="com.ink.monitor.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>业务模块维护</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
	<script type="text/javascript" src="${yk}/static/common/maintain.js"></script>
</head>
<body>
<div class="title01">
	<h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/monitor/dict/info/list.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">

		<tr>	
			<td class="txtr" style="width: 100px">平台系统：</td>
			<td>
				&nbsp;<select id="systermCode" name="systermCode" class="select02"></select>
			</td>
			<td class="txtr" style="width: 100px">功能模块：</td>
			<td>
				&nbsp;<select id="moduleCode" name="moduleCode" class="select02"></select>
			</td>
			<td class="txtr" style="width: 100px">状态：</td>
			<td>
				<yk:constantConvert htmlTag="select" dataTag="status" name="status" paramValue="${status}" sysTag="monitor"/>
			</td>

		</tr>
		<tr>
			<td class="txtr" style="width: 100px">业务名：</td>
			<td>
				&nbsp;<input value="<c:out value="${name}"/>" id="name" name="name" maxlength="20"/>
			</td>
			<td class="txtr" style="width: 100px">业务码：</td>
			<td>
				&nbsp;<input value="<c:out value="${code}"/>" id="code" name="code" maxlength="20"/>
			</td>

			<td class="txtr" style="width: 100px"></td>
			<td>
			</td>
		</tr>

	</table>
	<br/>
	<div align="center">
		<input type="submit" class="btn01" value="查 询" onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('1');"/>
		<input type="button" class="btn01" value="清 空" id="btnclear"/>
	</div>
</form>
	<br/>
	<div class="title01">
	<h2>业务模块列表</h2>
</div>
		<div class="groupbtn">
			<a href="javascript:void(0)" title="新建" class="btn001" id="btnadd">新增</a>
			<%--<a href="javascript:void(0)" title="修改" class="btn002" id="btnedit">修改</a>--%>
			<a href="javascript:void(0)" title="停用"  class="btn004" id="btndelete">停用</a>
		</div>
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb" class="table02">
			<thead>
			 <tr>
			 	<th><input id="allChoose" type="checkbox"/></th>
			 	<th>序号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				 <th>平台系统<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				 <th>功能模块<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				 <th>业务模块名<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>业务模块码<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>状态<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>创建时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>更新时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>操作人<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>描述<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
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
						<yk:code2name tableName="systerm_source" columnName="name" where="code=?" paramValue="${entry.systermCode}" emptyTip="${entry.systermCode}" serviceName="monitorDubboBaseService"/>
					</div>
				</td>
				<td>
					<div>
						<yk:code2name tableName="systerm_module" columnName="name" where="code=?" paramValue="${entry.moduleCode}" emptyTip="${entry.moduleCode}" serviceName="monitorDubboBaseService"/>
					</div>
				</td>
				<td>
				<div><c:out value="${entry.name}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.code}"/></div>
				</td>
				<td>
				<div>
					<yk:constantConvert htmlTag="text" dataTag="status" paramValue="${entry.status}" emptyTip="未知" sysTag="monitor"/>
				</div>
				</td>
				<td>
				<div><c:out value="${entry.createTimeString}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.updateTimeString}"/></div>
				</td>
				<td>
				<div>
					<yk:code2name tableName="monitor_user" columnName="fullName" where="userid=?" paramValue="${entry.opUser}" emptyTip="${entry.opUser}" serviceName="monitorDubboBaseService"/>
				</div>
				</td>
				<td>
				<div><c:out value="${entry.opDesc}"/></div>
				</td>
				<td>
						<a href="#" onclick="show('${entry.id}')">详情</a>&nbsp;
						<a href="#" onclick="edit('${entry.id}')">修改</a>&nbsp;
						<c:choose>
							<c:when test="${entry.status =='0'}">
								<a href="#" onclick="del('${entry.id}','1')">停用</a>&nbsp;
							</c:when>
							<c:when test="${entry.status =='1'}">
								<a href="#" onclick="del('${entry.id}','0')">启用</a>&nbsp;
							</c:when>
						</c:choose>
				</td>
			</tr>
			</c:forEach>
			</table>
     	 <yk:page url="${yk}/monitor/dict/info/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">
function show(id){
	window.open('${yk}/monitor/dict/info/show.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}
function edit(id){
	window.open('${yk}/monitor/dict/info/edit.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}

$(function(){
	
	$('#btnedit').click(function(){
		var $id = $('[name=ids]:checkbox:checked');
		
		if($id.length==0){
			alert("请选择要修改的信息！");
			return false;
		}
		window.open('${yk}/monitor/dict/info/edit.do?'+$('#pkName').val()+'='+$id.val(),'','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btnadd').click(function(){
		window.open('${yk}/monitor/dict/info/create.do','','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btndelete').click(function(){
		if($('[name=ids]:checkbox:checked').length>0){
			var ids = [];
			$('[name=ids]:checkbox:checked').each(function(){ids.push($(this).val())});
			v_deleteItems(ids,'1');
		}else{
			alert("请选择后进行停用操作！");
		}
		
	});

	var systemUrl = '${yk}/monitor/dict/source/listForAjax.do';
	executeAjax(systemUrl,"systermCode",'<c:out value="${systermCode}"/>',true);

	//系统代码联动模块代码
	$("#systermCode").change(function(){
		var optionValue = this.value;
		if (optionValue == ""){
			$("#moduleCode").empty();
			return ;
		}
		var moduleUrl = '${yk}/monitor/dict/module/listAjaxForSystemCode.do?systemCode='+optionValue;
		executeAjax(moduleUrl,"moduleCode",'<c:out value="${moduleCode}"/>',true);

	});

	$('#btnclear').click(function(){
		$('#systermCode').val('');
		$('#moduleCode').val('');
		$('#status').val('');
		$('#name').val('');
		$('#code').val('');
	});
});

function del(id,status){
	var ids = [];
	ids.push(id);
	v_deleteItems(ids,status);
}

function v_deleteItems(ids,status){
	var showStr = "停用";
	if(status =='0'){
		showStr = "启用";
	}
	if(ids.length>0 && confirm("确定要"+showStr+"吗？")){
		$.post( "${yk}/monitor/dict/info/delete.do", {"items":ids.join(','),"status":status},
				function(data){
					if(data=='1'){
						alert(showStr+'停用');
						$('#queryForm').submit();
					}else{
						alert(showStr+'停用失败');
					}
				}
		);
	}
};
</script>
</body>
</html>


