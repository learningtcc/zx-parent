<%@ page import="com.ink.msgcenter.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>短信通道维护</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
	<h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/msgcenter/channel/sms/list.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
		<tr>	
			<td class="txtr" style="width: 100px">通道名称：</td>		
			<td>
				&nbsp;<input value="<c:out value="${name}"/>" id="name" name="name" maxlength="20"/>
			</td>
			<td class="txtr" style="width: 100px">通道代码：</td>
			<td>
				&nbsp;<input value="<c:out value="${chnCode}"/>" id="chnCode" name="chnCode" maxlength="3"/>
			</td>

		</tr>
		<tr>
			<td class="txtr" style="width: 100px">通道类型：</td>
			<td>
				&nbsp;	<yk:constantConvert htmlTag="select" name="chnType" dataTag="chnType" paramValue="${chnType}" emptyTip="${chnType}" sysTag="msgcenter"/>
			</td>
			<td class="txtr" style="width: 100px">通道状态：</td>
			<td>
				&nbsp;<yk:constantConvert htmlTag="select" dataTag="status" name="status" paramValue="${status}" emptyTip="0" sysTag="msgcenter"/>
			</td>
		</tr>
	</table>
	<br/>
	<div align="center">
		<input type="submit" class="btn01" value="精确查询" onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('0');"/>
		<input type="submit" class="btn01" value="模糊查询" onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('1');"/>
		<input type="button" class="btn01" value="清 空" id="btnclear"/>
	</div>
</form>
	<br/>
	<div class="title01">
	<h2>邮件通道列表</h2>
</div>
		<div class="groupbtn">
			<a href="javascript:void(0)" title="新建" class="btn001" id="btnadd">新增</a>
			<a href="javascript:void(0)" title="删除"  class="btn004" id="btndelete">删除</a>
			<input type="button" id="levelOrder" class="btn01" name="levelOrder" value="优先级调整"/>
		</div>
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb" class="table02">
			<thead>
			 <tr>
			 	<th><input id="allChoose" type="checkbox"/></th>
			 	<th>序号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>通道名称<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>通道代码<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>通道类型<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>通道参数<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>通道备注<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>通道优先级<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>通道状态<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
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
				<div><c:out value="${entry.name}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.chnCode}"/></div>
				</td>
				<td>
				<div>
					<yk:constantConvert htmlTag="text" name="chnType" dataTag="chnType" paramValue="${entry.chnType}" emptyTip="${entry.chnType}" sysTag="msgcenter"/>
				</div>
				</td>
				<td>
				<div><c:out value="${entry.chnParam}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.remark}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.priorityLevel}"/></div>
				</td>
				<td>
				<div>
					<yk:constantConvert htmlTag="text" dataTag="del_status" paramValue="${entry.status}" emptyTip="未知" sysTag="msgcenter"/>
				</div>
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
					<c:choose>
						<c:when test="${entry.status == 1}">
							<a href="#" onclick="del('${entry.id}','0')">启用</a>&nbsp;
						</c:when>
						<c:otherwise>
							<a href="#" onclick="del('${entry.id}','1')">停用</a>&nbsp;
						</c:otherwise>
					</c:choose>
					<a href="#" onclick="statistics('${entry.id}')">统计</a>&nbsp;
					<a href="#" onclick="del('${entry.id}','2')">删除</a>&nbsp;
				</td>
			</tr>
			</c:forEach>
			</table>
     	 <yk:page url="${yk}/msgcenter/channel/sms/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">
function show(id){
	window.open('${yk}/msgcenter/channel/sms/show.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}
function edit(id){
	window.open('${yk}/msgcenter/channel/sms/edit.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}
function statistics(id){
	window.open('${yk}/msgcenter/SmsAnalyze/statistics.do?chnId='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}

$(function(){
	
	$('#btnedit').click(function(){
		var $id = $('[name=ids]:checkbox:checked');
		
		if($id.length==0){
			alert("请选择要修改的信息！");
			return false;
		}
		window.open('${yk}/msgcenter/channel/sms/edit.do?'+$('#pkName').val()+'='+$id.val(),'','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btnadd').click(function(){
		window.open('${yk}/msgcenter/channel/sms/create.do','','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btndelete').click(function(){
		if($('[name=ids]:checkbox:checked').length>0){
			var ids = [];
			$('[name=ids]:checkbox:checked').each(function(){ids.push($(this).val())});
			v_deleteItems(ids,"2");
		}else{
			alert("请选择后进行删除操作！");
		}
		
	});

	$("#levelOrder").click(function(){
		window.open('${yk}/msgcenter/channel/sms/levelOrder.do','','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btnclear').click(function(){
			$('#name').val('');
			$('#chnCode').val('');
			$('#chnType').val('');
			$('#status').val('');
	});
});

function del(id,status){
	var ids = [];
	ids.push(id);
	v_deleteItems(ids,status);
}

function v_deleteItems(ids,status){
	var message = "";
	if(status=="0"){
		message = "所有配置该通道上商户均启用，是否确认？";
	}else if(status == "1"){
		message = "所有配置该通道上商户均停用，是否确认？";
	}else {
		message = "所有配置该通道上商户通道均删除，是否确认？";
	}
	if(ids.length>0 && confirm(message)){
		$.post( "${yk}/msgcenter/channel/sms/updateStatus.do", {"items":ids.join(','),"status":status},
				function(data){
					if(data=='1'){
						alert('操作成功');
						$('#queryForm').submit();
					}else{
						alert('操作失败');
					}
				}
		);
	}
};
</script>
</body>
</html>


