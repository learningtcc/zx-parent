<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>维护</title>
<%@ include file="/gen-commons/taglibs.jsp"%>
</head>
<body>
	<div class="title01 title01_x">
		<h2>
			搜索<span class="zksq">
				<!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> -->
			</span>
		</h2>
	</div>
	<form id="queryForm" name="queryForm"
		action="<c:url value="/OperationLog/list.do"/>" method="post"
		style="display: inline;">
		<input type="hidden" id="pageNumber" name="pageNumber"
			value="<c:out value="${pageNumber}" default="1"/>" /> <input
			type="hidden" id="pageSize" name="pageSize"
			value="<c:out value="${pageSize}" default="10"/>" /> <input
			type="hidden" id="queryType" name="queryType"
			value="<c:out value="${queryType}" default="0"/>" />
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="table00">
			<tr>
				<td class="txtr" style="width: 100px">操作类型（1：新增、2：修改、3：删除）：</td>
				<td>&nbsp;<input value="<c:out value="${type}"/>" id="type"
					name="type" maxlength="10" />
				</td>
				<td class="txtr" style="width: 100px">操作表名称：</td>
				<td>&nbsp;<input value="<c:out value="${tableName}"/>"
					id="tableName" name="tableName" maxlength="16" />
				</td>
				<td class="txtr" style="width: 100px">操作表主键：</td>
				<td>&nbsp;<input value="<c:out value="${tableId}"/>"
					id="tableId" name="tableId" maxlength="19" />
				</td>
				<td class="txtr" style="width: 100px">操作表字段：</td>
				<td>&nbsp;<input value="<c:out value="${tableColumn}"/>"
					id="tableColumn" name="tableColumn" maxlength="32" />
				</td>
			</tr>
			<tr>
				<td class="txtr" style="width: 100px">原始内容：</td>
				<td>&nbsp;<input value="<c:out value="${oldContent}"/>"
					id="oldContent" name="oldContent" maxlength="1024" />
				</td>
				<td class="txtr" style="width: 100px">更新内容：</td>
				<td>&nbsp;<input value="<c:out value="${newContent}"/>"
					id="newContent" name="newContent" maxlength="1024" />
				</td>
				<td class="txtr" style="width: 100px">创建时间：</td>
				<td>&nbsp;<input onClick="WdatePicker()"
					value="<c:out value="${createTimeBegin}"/>" id="createTimeBegin"
					name="createTimeBegin" size="10" /> <input onClick="WdatePicker()"
					value="<c:out value="${createTimeEnd}"/>" id="createTimeEnd"
					name="createTimeEnd" size="10" />
				</td>
				<td class="txtr" style="width: 100px">更新时间：</td>
				<td>&nbsp;<input onClick="WdatePicker()"
					value="<c:out value="${updateTimeBegin}"/>" id="updateTimeBegin"
					name="updateTimeBegin" size="10" /> <input onClick="WdatePicker()"
					value="<c:out value="${updateTimeEnd}"/>" id="updateTimeEnd"
					name="updateTimeEnd" size="10" />
				</td>
			</tr>
			<tr>
				<td class="txtr" style="width: 100px">操作人：</td>
				<td>&nbsp;<input value="<c:out value="${operator}"/>"
					id="operator" name="operator" maxlength="20" />
				</td>
				<td class="txtr" style="width: 100px">删除标志：</td>
				<td>&nbsp;<input value="<c:out value="${delFlag}"/>"
					id="delFlag" name="delFlag" maxlength="10" />
				</td>
				<td class="txtr" style="width: 100px">版本号：</td>
				<td>&nbsp;<input value="<c:out value="${version}"/>"
					id="version" name="version" maxlength="10" />
				</td>
				<td class="txtr" style="width: 100px">备注：</td>
				<td>&nbsp;<input value="<c:out value="${remark}"/>" id="remark"
					name="remark" maxlength="128" />
				</td>
			</tr>
		</table>
		<br />
		<div align="center">
			<input type="submit" class="btn01" value="查询"
				onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('0');" />
			<%--<input type="submit" class="btn01" value="模糊查询" onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('1');"/>--%>
			<input type="button" class="btn01" value="清 空" id="btnclear" />
		</div>
	</form>
	<br></br>
	<div class="title01">
		<h2>列表</h2>
	</div>
	<div class="groupbtn">
		<a href="javascript:void(0)" title="新建" class="btn001" id="btnadd">新增</a>
		<a href="javascript:void(0)" title="修改" class="btn002" id="btnedit">修改</a>
		<a href="javascript:void(0)" title="删除" class="btn004" id="btndelete">删除</a>
	</div>
	<div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb"
			class="table02">
			<thead>
				<tr>
					<th><input id="allChoose" type="checkbox" /></th>
					<th>序号
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>操作类型（1：新增、2：修改、3：删除）
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>操作表名称
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>操作表主键
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>操作表字段
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>原始内容
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>更新内容
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>创建时间
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>更新时间
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>操作人
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>删除标志
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>版本号
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>备注
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>操作
						<div style="width: 75px; height: 1px; overflow: hidden;"></div>
					</th>
				</tr>
			</thead>
			<c:forEach var="entry" items="${page.result}" varStatus="v">
				<tr>
					<td><input name="ids" type="checkbox" value="${entry.id}" /> <input
						name="pkName" type="hidden" id="pkName" value="id" /></td>
					<td>${v.index + 1 }</td>
					<td>
						<div>
							<c:out value="${entry.type}" />
						</div>
					</td>
					<td>
						<div>
							<c:out value="${entry.tableName}" />
						</div>
					</td>
					<td>
						<div>
							<c:out value="${entry.tableId}" />
						</div>
					</td>
					<td>
						<div>
							<c:out value="${entry.tableColumn}" />
						</div>
					</td>
					<td>
						<div>
							<c:out value="${entry.oldContent}" />
						</div>
					</td>
					<td>
						<div>
							<c:out value="${entry.newContent}" />
						</div>
					</td>
					<td>
						<div>
							<c:out value="${entry.createTimeString}" />
						</div>
					</td>
					<td>
						<div>
							<c:out value="${entry.updateTimeString}" />
						</div>
					</td>
					<td>
						<div>
							<c:out value="${entry.operator}" />
						</div>
					</td>
					<td>
						<div>
							<c:out value="${entry.delFlag}" />
						</div>
					</td>
					<td>
						<div>
							<c:out value="${entry.version}" />
						</div>
					</td>
					<td>
						<div>
							<c:out value="${entry.remark}" />
						</div>
					</td>
					<td><a href="#" onclick="show('${entry.id}')">详情</a>&nbsp; <a
						href="#" onclick="edit('${entry.id}')">修改</a>&nbsp; <a href="#"
						onclick="del('${entry.id}')">删除</a>&nbsp;</td>
				</tr>
			</c:forEach>
		</table>
		<yk:page url="${yk}/OperationLog/list.do" id="logForm"
			submitForm="queryForm" joy="true" />
	</div>

	<script type="text/javascript">
function show(id){
	window.open('${yk}/OperationLog/show.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}
function edit(id){
	window.open('${yk}/OperationLog/edit.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}

$(function(){
	
	$('#btnedit').click(function(){
		var $id = $('[name=ids]:checkbox:checked');
		
		if($id.length==0){
			alert("请选择要修改的信息！");
			return false;
		}
		window.open('${yk}/OperationLog/edit.do?'+$('#pkName').val()+'='+$id.val(),'','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btnadd').click(function(){
		window.open('${yk}/OperationLog/create.do','','height=600,width=600,resizable=yes,location=no,scrollbars');
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
			$('#type').val('');
			$('#tableName').val('');
			$('#tableId').val('');
			$('#tableColumn').val('');
			$('#oldContent').val('');
			$('#newContent').val('');
			$('#createTimeBegin').val('');
			$('#createTimeEnd').val('');
			$('#updateTimeBegin').val('');
			$('#updateTimeEnd').val('');
			$('#operator').val('');
			$('#delFlag').val('');
			$('#version').val('');
			$('#remark').val('');
	});
});

function del(id){
	var ids = [];
	ids.push(id);
	v_deleteItems(ids);
}

function v_deleteItems(ids){
	if(ids.length>0 && confirm("确定要删除吗？")){
		$.post( "${yk}/OperationLog/delete.do", {"items":ids.join(',')}, 
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


