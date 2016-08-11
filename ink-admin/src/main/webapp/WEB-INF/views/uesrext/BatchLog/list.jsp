
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>批次信息 维护</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01 title01_x">
	<h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/userext/BatchLog/list.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
		<tr>	
			<td class="txtr" style="width: 100px">文件号：</td>		
			<td>
				<input value="<c:out value="${fileId}"/>" id="fileId" name="fileId" maxlength="19" type="number"/>
			</td>
			<td class="txtr" style="width: 100px">批次号：</td>		
			<td>
				<input value="<c:out value="${batchId}"/>" id="batchId" name="batchId" maxlength="20"/>
			</td>
			<!-- 
			<td class="txtr" style="width: 100px">消息类型：</td>		
			<td>
				<select id="msgType" name="msgType">
					<option value="0">短信</option>
				</select>
			</td> -->
		</tr>	
		<!--
		<tr>	
			
			<td class="txtr" style="width: 100px">信息渠道：</td>		
			<td>
				<input value="<c:out value="${msgChannel}"/>" id="msgChannel" name="msgChannel" maxlength="100"/>
			</td>
			<td class="txtr" style="width: 100px">信息模板：</td>		
			<td>
				<input value="<c:out value="${msgTemplate}"/>" id="msgTemplate" name="msgTemplate" maxlength="100"/>
			</td>
			<td class="txtr" style="width: 100px">更新时间：</td>		
			<td colspan="3">
				<input onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<c:out value="${updateTimeBegin}"/>" id="updateTimeBegin" name="updateTimeBegin" size="10"/>
				&nbsp;---&nbsp;
				<input onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<c:out value="${updateTimeEnd}"/>" id="updateTimeEnd" name="updateTimeEnd" size="10"/>
			</td>
		</tr>	
		  -->
	</table>
	<br/>
	<div align="center">
		<input type="submit" class="btn01" value="精确查询" onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('0');"/>
		<input type="submit" class="btn01" value="模糊查询" onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('1');"/>
		<input type="button" class="btn01" value="清 空" id="btnclear"/>
	</div>
</form>
	<br></br>
	<div class="title01">
	<h2>批次信息列表</h2>
</div>
		<div class="groupbtn">
			<!-- <a href="javascript:void(0)" title="新建" class="btn001" id="btnadd">新增</a>
			<a href="javascript:void(0)" title="修改" class="btn002" id="btnedit">修改</a>
			<a href="javascript:void(0)" title="删除"  class="btn004" id="btndelete">删除</a> -->
		</div>
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb" class="table02">
			<thead>
			 <tr>
			 	<th><input id="allChoose" type="checkbox"/></th>
			 	<th>序号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>文件号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>批次号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>条目数<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>发送时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>发送类型<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>消息类型<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>信息渠道<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>信息模板<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
			    <th>操作<div style="width: 75px; height: 1px; overflow: hidden;"></div></th>
	        </tr>
	        </thead>
			<c:forEach var="entry" items="${page.result}" varStatus="v">
			<tr>
				<td><input name="ids" type="checkbox" value="${entry.id}"/>
				<input name="pkName" type="hidden" id="pkName" value="id"/>
				</td>
				<td>${v.index + 1 + (page.pageNumber-1) * page.pageSize }</td>
				<td>
				<div><c:out value="${entry.fileId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.batchId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.itemCount}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.sendTimeString}"/></div>
				</td>
				<td>
					<c:choose>
						<c:when test="${entry.sendType eq 0 }">及时发送</c:when>
						<c:when test="${entry.sendType eq 1 }">延时发送</c:when>
						<c:otherwise>未定义</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${entry.msgType eq '0'}">短信</c:when>
						<c:otherwise>短信</c:otherwise>
					</c:choose>
				</td>
				<td>
				<div><c:out value="${entry.msgChannel}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.msgTemplate}"/></div>
				</td>
				<td>
						<a href="#" onclick="show('${entry.id}')">详情</a>&nbsp;
						<!-- <a href="#" onclick="edit('${entry.id}')">修改</a>&nbsp;
						<a href="#" onclick="del('${entry.id}')">删除</a>&nbsp; -->
				</td>
			</tr>
			</c:forEach>
			</table>
     	 <yk:page url="${yk}/BatchLog/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">
function show(id){
	window.open('${yk}/userext/BatchLog/show.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}
function edit(id){
	window.open('${yk}/userext/BatchLog/edit.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}

$(function(){
	
	$('#btnedit').click(function(){
		var $id = $('[name=ids]:checkbox:checked');
		
		if($id.length==0){
			alert("请选择要修改的信息！");
			return false;
		}
		window.open('${yk}/userext/BatchLog/edit.do?'+$('#pkName').val()+'='+$id.val(),'','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btnadd').click(function(){
		window.open('${yk}/userext/BatchLog/create.do','','height=600,width=600,resizable=yes,location=no,scrollbars');
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
			$('#fileId').val('');
			$('#batchId').val('');
			$('#itemCount').val('');
			$('#sendTimeBegin').val('');
			$('#sendTimeEnd').val('');
			$('#sendType').val('');
			$('#msgType').val('');
			$('#msgChannel').val('');
			$('#msgTemplate').val('');
			$('#createTimeBegin').val('');
			$('#createTimeEnd').val('');
			$('#updateTimeBegin').val('');
			$('#updateTimeEnd').val('');
	});
});

function del(id){
	var ids = [];
	ids.push(id);
	v_deleteItems(ids);
}

function v_deleteItems(ids){
	if(ids.length>0 && confirm("确定要删除吗？")){
		$.post( "${yk}/BatchLog/delete.do", {"items":ids.join(',')}, 
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


