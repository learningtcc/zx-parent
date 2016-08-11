<%@ page import="com.ink.scheduler.core.po.*" %>
<%@ page import="com.ink.scheduler.core.po.TimerTaskJob" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=TimerTaskJob.TABLE_ALIAS%> 维护</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01 title01_x"  >
	<h2>搜索<span  class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/scheduler/job/list.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
		<tr>	
			<td class="txtr" style="width: 100px">任务名称：</td>		
			<td>
				&nbsp;<input value="<c:out value="${jobName}"/>" id="jobName" name="jobName" maxlength="300"/>
			</td>
			<td class="txtr" style="width: 100px">任务分组：</td>
			<td>
				&nbsp;<input value="<c:out value="${jobGroup}"/>" id="jobGroup" name="jobGroup" maxlength="100"/>
			</td>
			<td class="txtr" style="width: 100px">调度状态：</td>
			<td>
                &nbsp;
                <select id="jobStatus" name="jobStatus">
                    <option value="">全部</option>
                    <option value="0" <c:if test="${jobStatus=='0'}"> selected = "selected"</c:if> >运行</option>
                    <option value="1" <c:if test="${jobStatus=='1'}"> selected = "selected"</c:if> >暂停</option>
                </select>
			</td>
		</tr>
		<tr>
            <td class="txtr" style="width: 100px">时间表达式：</td>
            <td>
                &nbsp;<input value="<c:out value="${cronExpression}"/>" id="cronExpression" name="cronExpression" maxlength="100"/>
            </td>
            <td class="txtr" style="width: 100px">调用类名：</td>
            <td>
                &nbsp;<input value="<c:out value="${jobClass}"/>" id="jobClass" name="jobClass" maxlength="100"/>
            </td>
			<td class="txtr" style="width: 100px">描述：</td>
			<td>
				&nbsp;<input value="<c:out value="${description}"/>" id="description" name="description" maxlength="200"/>
			</td>
		</tr>
        <tr>
            <td class="txtr" style="width: 100px">启动时间：</td>
            <td>
                &nbsp;<input onClick="WdatePicker()" value="<c:out value="${firstTimeBegin}"/>" id="firstTimeBegin" name="firstTimeBegin" size="10"/>
                <input onClick="WdatePicker()" value="<c:out value="${firstTimeEnd}"/>" id="firstTimeEnd" name="firstTimeEnd" size="10"/>
            </td>
            <td class="txtr" style="width: 100px">前一次运行时间：</td>
            <td>
                &nbsp;<input onClick="WdatePicker()" value="<c:out value="${previousTimeBegin}"/>" id="previousTimeBegin" name="previousTimeBegin" size="10"/>
                <input onClick="WdatePicker()" value="<c:out value="${previousTimeEnd}"/>" id="previousTimeEnd" name="previousTimeEnd" size="10"/>
            </td>
            <td class="txtr" style="width: 100px">任务有效时间：</td>
            <td>
                &nbsp;<input onClick="WdatePicker()" value="<c:out value="${validityPeriodBegin}"/>" id="validityPeriodBegin" name="validityPeriodBegin" size="10"/>
                <input onClick="WdatePicker()" value="<c:out value="${validityPeriodEnd}"/>" id="validityPeriodEnd" name="validityPeriodEnd" size="10"/>
            </td>
        </tr>
	</table>
	<br/>
	<div align="center">
		<input type="submit" id="queryBtn" class="btn01" value="查询" onclick="$('#pageNumber').val('1');$('#pageSize').val('10');"/>
		<input type="button" class="btn01" value="清 空" id="btnclear"/>
	</div>
</form>
	<br></br>
	<div class="title01">
	<h2><%=TimerTaskJob.TABLE_ALIAS%>列表</h2>
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
				<th>任务名称<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>任务分组<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>任务调度状态<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>描述<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>执行次数<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>时间表达式<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
                <th>上次执行时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>任务预计执行时间(单位秒)<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
			    <th>操作<div style="width: 75px; height: 1px; overflow: hidden;"></div></th>
	        </tr>
	        </thead>
			<c:forEach var="entry" items="${page.result}" varStatus="v">
			<tr>
				<td><input name="ids" type="checkbox" value="${entry.jobId}"/>
				<input name="pkName" type="hidden" id="pkName" value="jobId"/>
				</td>
				<td>${v.index + 1 }</td>
				<td>
				<div><c:out value="${entry.jobName}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.jobGroup}"/></div>
				</td>
				<td>
				<div>
                    <c:if test="${entry.jobStatus=='0'}">运行</c:if>
                    <c:if test="${entry.jobStatus=='1'}">暂停</c:if>
                </div>
				</td>
				<td>
				<div><c:out value="${entry.description}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.runCount}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.cronExpression}"/></div>
				</td>
                <td>
                    <fmt:formatDate value="${entry.previousTime}" type="both" pattern="yyyy/MM/dd HH:mm:ss"/>
                </td>
				<td>
					<div><c:out value="${entry.jobExecuteTime}"/></div>
				</td>
				<td>
						<a href="#" onclick="show('${entry.jobId}')">详情</a>&nbsp;
						<a href="#" onclick="edit('${entry.jobId}')">修改</a>&nbsp;
						<a href="#" onclick="del('${entry.jobId}')">删除</a>&nbsp;
                        <c:if test="${entry.jobStatus=='0'}">
                            <a  href="#" onclick="pause('${entry.jobId}')">暂停</a>&nbsp;
                        </c:if>
                        <c:if test="${entry.jobStatus=='1'}">
                            <a  href="#" onclick="resume('${entry.jobId}')">恢复</a>&nbsp;
                        </c:if>
				</td>
			</tr>
			</c:forEach>
			</table>
     	 <yk:page url="${yk}/scheduler/job/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">
function show(id){
	window.open('${yk}/scheduler/job/show.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}
function edit(id){
	window.open('${yk}/scheduler/job/edit.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}
function pause(id){
	if(id != null && confirm("确定要暂停吗？")){
		$.post("${yk}/scheduler/job/pause.do",{jobId:id}, function(data) {
			if(eval('(' + data + ')').result == "true"){
				//暂停后页面处理
				alert('暂停成功');
				$('#queryForm').submit();
			}else{
				alert(data.errorMsg);
			}
		});
	}
}
function resume(id){
	if(id != null && confirm("确定要恢复吗？")){
		$.post("${yk}/scheduler/job/resume.do",{jobId:id}, function(data) {
            if(eval('(' + data + ')').result == "true"){
				//恢复后页面处理
				alert('恢复成功');
				$("#queryBtn").click();
			}else{
                alert(data.errorMsg);
			}
		});
	}
}

$(function(){
	
	$('#btnedit').click(function(){
		var $id = $('[name=ids]:checkbox:checked');
		
		if($id.length==0){
			alert("请选择要修改的信息！");
			return false;
		}
		window.open('${yk}/scheduler/job/edit.do?'+$('#pkName').val()+'='+$id.val(),'','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btnadd').click(function(){
		window.open('${yk}/scheduler/job/create.do','','height=600,width=600,resizable=yes,location=no,scrollbars');
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
			$('#jobName').val('');
			$('#jobGroup').val('');
			$('#status').val('');
			$('#jobStatus').val('');
			$('#isConcurrent').val('');
			$('#description').val('');
			$('#jobClass').val('');
			$('#methodName').val('');
			$('#firstTimeBegin').val('');
			$('#firstTimeEnd').val('');
			$('#previousTimeBegin').val('');
			$('#previousTimeEnd').val('');
			$('#validityPeriodBegin').val('');
			$('#validityPeriodEnd').val('');
			$('#jobUrl').val('');
			$('#runCount').val('');
			$('#cronExpression').val('');
	});
});

function del(id){
	var ids = [];
	ids.push(id);
	v_deleteItems(ids);
}

function v_deleteItems(ids){
	if(ids.length>0 && confirm("确定要删除吗？")){
		$.post( "${yk}/scheduler/job/delete.do", {"items":ids.join(',')},
				function(data){
                    if(eval('(' + data + ')').result == "true"){
						alert('删除成功');
						$('#queryForm').submit();
					}else{
						alert(data.errorMsg);
					}
				}
		);
	}
};
</script>
</body>
</html>


