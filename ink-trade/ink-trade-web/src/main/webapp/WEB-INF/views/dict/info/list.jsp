
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>业务模块维护</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
	<script type="text/javascript" src="${yk}/static/monitor/monitorMaintain.js"></script>
</head>
<body>
<div class="title01">
	<h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/dict/info/list.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">

		<tr>	
			<td class="txtr" style="width: 100px">系统码：</td>
			<td>
				&nbsp;<select id="systermCode" name="systermCode" class="select02"></select>
			</td>
			<td class="txtr" style="width: 100px">模块码：</td>
			<td>
				&nbsp;<select id="moduleCode" name="moduleCode" class="select02"></select>
			</td>
			<td class="txtr" style="width: 100px">状态：</td>
			<td>
				<select name="status" id="status">
					<option value="">请选择...</option>
					<option value="0">正常</option>
					<option value="1">停用</option>
				</select>
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
	<br></br>
	<div class="title01">
	<h2>业务模块列表</h2>
</div>
		<div class="groupbtn">
			<a href="javascript:void(0)" title="新建" class="btn001" id="btnadd">新增</a>
			<a href="javascript:void(0)" title="修改" class="btn002" id="btnedit">修改</a>
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
						<yk:code2name tableName="systerm_source" columnName="name" where="code=?" paramValue="${entry.systermCode}" emptyTip="${entry.systermCode}"/>
					</div>
				</td>
				<td>
					<div>
						<yk:code2name tableName="systerm_module" columnName="name" where="code=?" paramValue="${entry.moduleCode}" emptyTip="${entry.moduleCode}"/>
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
					<c:choose><c:when test="${entry.status==0}">正常</c:when><c:when test="${entry.status==1}">停用</c:when><c:otherwise>未知</c:otherwise></c:choose>
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
					<yk:code2name tableName="monitor_user" columnName="fullName" where="userid=?" paramValue="${entry.opUser}" emptyTip="${entry.opUser}"/>
				</div>
				</td>
				<td>
				<div><c:out value="${entry.opDesc}"/></div>
				</td>

				<td>
						<a href="#" onclick="show('${entry.id}')">详情</a>&nbsp;
						<a href="#" onclick="edit('${entry.id}')">修改</a>&nbsp;
						<a href="#" onclick="del('${entry.id}')">停用</a>&nbsp;
				</td>
			</tr>
			</c:forEach>
			</table>
     	 <yk:page url="${yk}/dict/info/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">
function show(id){
	window.open('${yk}/dict/info/show.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}
function edit(id){
	window.open('${yk}/dict/info/edit.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}

$(function(){
	
	$('#btnedit').click(function(){
		var $id = $('[name=ids]:checkbox:checked');
		
		if($id.length==0){
			alert("请选择要修改的信息！");
			return false;
		}
		window.open('${yk}/dict/info/edit.do?'+$('#pkName').val()+'='+$id.val(),'','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btnadd').click(function(){
		window.open('${yk}/dict/info/create.do','','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btndelete').click(function(){
		if($('[name=ids]:checkbox:checked').length>0){
			var ids = [];
			$('[name=ids]:checkbox:checked').each(function(){ids.push($(this).val())});
			v_deleteItems(ids);
		}else{
			alert("请选择后进行停用操作！");
		}
		
	});

	$("#status").val('<c:out value="${status}"/>');
	var systemUrl = '${yk}/dict/source/listForAjax.do';
	executeAjax(systemUrl,"systermCode",'<c:out value="${systermCode}"/>',true);

	//系统代码联动模块代码
	$("#systermCode").change(function(){
		var optionValue = this.value;
		if (optionValue == ""){
			$("#moduleCode").empty();
			return ;
		}
		var moduleUrl = '${yk}/dict/module/listAjaxForSystemCode.do?systemCode='+optionValue;
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

function del(id){
	var ids = [];
	ids.push(id);
	v_deleteItems(ids);
}

function v_deleteItems(ids){
	if(ids.length>0 && confirm("确定要停用吗？")){
		$.post( "${yk}/dict/info/delete.do", {"items":ids.join(',')}, 
				function(data){
					if(data=='1'){
						alert('停用成功');
						$('#queryForm').submit();
					}else{
						alert('停用失败');
					}
				}
		);
	}
};
</script>
</body>
</html>


