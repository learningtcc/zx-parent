<%@ page import="com.ink.scheduler.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=TimerTaskJobLog.TABLE_ALIAS%> 维护</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01 title01_x">
	<h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/jobLog/list.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
		<tr>	
			<td class="txtr" style="width: 100px">任务ID：</td>		
			<td>
				<%--&nbsp;<input value="<c:out value="${jobId}"/>" id="jobId" name="jobId" maxlength="10"/>--%>
				<select id="jobId" name="jobId">
					<option value="">全部</option>
					<c:forEach var="entry" items="${jobList}" varStatus="v">
						<option value="${entry.jobId}"><c:out value="${entry.description}"/></option>
					</c:forEach>
				</select>
			</td>
			<td class="txtr" style="width: 100px">任务开始时间：</td>		
			<td>
				&nbsp;<input onClick="WdatePicker()" value="<c:out value="${startTimeBegin}"/>" id="startTimeBegin" name="startTimeBegin" size="10"/>
				<input onClick="WdatePicker()" value="<c:out value="${startTimeEnd}"/>" id="startTimeEnd" name="startTimeEnd" size="10"/>
			</td>
			<td class="txtr" style="width: 100px">任务结束时间：</td>		
			<td>
				&nbsp;<input onClick="WdatePicker()" value="<c:out value="${endTimeBegin}"/>" id="endTimeBegin" name="endTimeBegin" size="10"/>
				<input onClick="WdatePicker()" value="<c:out value="${endTimeEnd}"/>" id="endTimeEnd" name="endTimeEnd" size="10"/>
			</td>
			<td class="txtr" style="width: 100px">任务执行结果：</td>		
			<td>
				&nbsp;
				<select id="status" name="status">
					<option value="">全部</option>
					<option value="0" <c:if test="${status=='0'}"> selected = "selected"</c:if> >成功</option>
					<option value="1" <c:if test="${status=='1'}"> selected = "selected"</c:if> >失败</option>
				</select>
			</td>
		</tr>	
		<tr>	
			<td class="txtr" style="width: 100px">业务流水ID：</td>		
			<td>
				&nbsp;<input value="<c:out value="${reqId}"/>" id="reqId" name="reqId" maxlength="200"/>
			</td>
			<td class="txtr" style="width: 100px">错误信息：</td>		
			<td>
				&nbsp;<input value="<c:out value="${errorMsg}"/>" id="errorMsg" name="errorMsg" maxlength="500"/>
			</td>
		</tr>	
	</table>
	<br/>
	<div align="center">
		<input type="submit" id="queryBtn" class="btn01" value="查询" onclick="$('#pageNumber').val('1');$('#pageSize').val('10');"/>
		<input type="button" class="btn01" value="清 空" id="btnclear"/>
	</div>
</form>
	<div class="title01">
	<h2><%=TimerTaskJobLog.TABLE_ALIAS%>列表</h2>
</div>
		<div class="groupbtn">
		</div>
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb" class="table02">
			<thead>
			 <tr>
			 	<th><input id="allChoose" type="checkbox"/></th>
			 	<th>序号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>任务名称<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>任务ID<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>任务开始时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>任务结束时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>任务执行结果<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>业务流水ID<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>错误信息<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
	        </tr>
	        </thead>
			<c:forEach var="entry" items="${page.result}" varStatus="v">
			<tr>
				<td><input name="ids" type="checkbox" value="${entry.id}"/>
				<input name="pkName" type="hidden" id="pkName" value="id"/>
				</td>
				<td>${v.index + 1 }</td>
				<td>
					<div><c:out value="${entry.description}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.jobName}"/></div>
				</td>
				<td>
				<div>
					<fmt:formatDate value="${entry.startTime}" type="both" pattern="yyyy/MM/dd HH:mm:ss"/>
				</div>
				</td>
				<td>
				<div><fmt:formatDate value="${entry.endTime}" type="both" pattern="yyyy/MM/dd HH:mm:ss"/></div>
				</td>
				<td>
				<div><c:out value="${entry.status}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.reqId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.errorMsg}"/></div>
				</td>
			</tr>
			</c:forEach>
			</table>
     	 <yk:page url="${yk}/TimerTaskJobLog/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">
function show(id){
	window.open('${yk}/TimerTaskJobLog/show.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}
function edit(id){
	window.open('${yk}/TimerTaskJobLog/edit.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}

$(function(){
	
	$('#btnedit').click(function(){
		var $id = $('[name=ids]:checkbox:checked');
		
		if($id.length==0){
			alert("请选择要修改的信息！");
			return false;
		}
		window.open('${yk}/TimerTaskJobLog/edit.do?'+$('#pkName').val()+'='+$id.val(),'','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btnadd').click(function(){
		window.open('${yk}/TimerTaskJobLog/create.do','','height=600,width=600,resizable=yes,location=no,scrollbars');
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
			$('#jobId').val('');
			$('#startTimeBegin').val('');
			$('#startTimeEnd').val('');
			$('#endTimeBegin').val('');
			$('#endTimeEnd').val('');
			$('#status').val('');
			$('#reqId').val('');
			$('#errorMsg').val('');
	});
});

function del(id){
	var ids = [];
	ids.push(id);
	v_deleteItems(ids);
}

function v_deleteItems(ids){
	if(ids.length>0 && confirm("确定要删除吗？")){
		$.post( "${yk}/TimerTaskJobLog/delete.do", {"items":ids.join(',')}, 
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


