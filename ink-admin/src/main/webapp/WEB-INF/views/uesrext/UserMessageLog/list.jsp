
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>用户消息日志 维护</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01 title01_x">
	<h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/userext/UserMessageLog/list.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
		<tr>	
			<td class="txtr" style="width: 100px">文件号：</td>		
			<td>
				<input value="<c:out value="${fileId}"/>" id="fileId" name="fileId" maxlength="19" type="number"/>
			</td>
			<td class="txtr" style="width: 100px">用户id：</td>		
			<td>
				<input value="<c:out value="${userId}"/>" id="userId" name="userId" maxlength="19" type="number"/>
			</td>
			<td class="txtr" style="width: 100px">商户号：</td>		
			<td>
				<input value="<c:out value="${mchId}"/>" id="mchId" name="mchId" maxlength="19" type="number"/>
			</td>
			<td class="txtr" style="width: 100px">姓名：</td>		
			<td>
				<input value="<c:out value="${name}"/>" id="name" name="name" maxlength="20"/>
			</td>
		</tr>	
		<tr>	
			<td class="txtr" style="width: 100px">手机号：</td>		
			<td>
				<input value="<c:out value="${phone}"/>" id="phone" name="phone" maxlength="20" type="number"/>
			</td>
			<td class="txtr" style="width: 100px">发送状态：</td>		
			<td>
				<select id="status" name="status">
					<option>请选择..</option>
					<option value="0" <c:if test="${status eq '0' }">selected="selected"</c:if>>已提交</option>
					<option value="1" <c:if test="${status eq '1' }">selected="selected"</c:if>>发送成功</option>
					<option value="2" <c:if test="${status eq '2' }">selected="selected"</c:if>>发送失败</option>
				</select>
			</td>
			<td class="txtr" style="width: 100px">信息渠道：</td>		
			<td>
				<input value="<c:out value="${msgChannel}"/>" id="msgChannel" name="msgChannel" maxlength="100" />
			</td>
		</tr>	
		<!-- 
		<tr>	
			<td class="txtr" style="width: 100px">信息模板：</td>		
			<td>
				&nbsp;<input value="<c:out value="${msgTemplate}"/>" id="msgTemplate" name="msgTemplate" maxlength="100"/>
			</td>
			<td class="txtr" style="width: 100px">创建时间：</td>		
			<td>
				&nbsp;<input onClick="WdatePicker()" value="<c:out value="${createTimeBegin}"/>" id="createTimeBegin" name="createTimeBegin" size="10"/>
				<input onClick="WdatePicker()" value="<c:out value="${createTimeEnd}"/>" id="createTimeEnd" name="createTimeEnd" size="10"/>
			</td>
			<td class="txtr" style="width: 100px">更新时间：</td>		
			<td>
				&nbsp;<input onClick="WdatePicker()" value="<c:out value="${updateTimeBegin}"/>" id="updateTimeBegin" name="updateTimeBegin" size="10"/>
				<input onClick="WdatePicker()" value="<c:out value="${updateTimeEnd}"/>" id="updateTimeEnd" name="updateTimeEnd" size="10"/>
			</td>
			<td class="txtr" style="width: 100px">删除标识：</td>		
			<td>
				&nbsp;<input value="<c:out value="${delFlag}"/>" id="delFlag" name="delFlag" maxlength="0"/>
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
	<h2>用户消息日志列表</h2>
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
				<th>用户id<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>商户号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>姓名<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>手机号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>信息渠道<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>信息模板<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>状态<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
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
				<div><c:out value="${entry.userId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.mchId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.name}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.phone}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.msgChannel}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.msgTemplate}"/></div>
				</td>
				<td>
					<c:choose>
						<c:when test="${entry.status eq '0' }">已提交</c:when>
						<c:when test="${entry.status eq '1' }">发送成功</c:when>
						<c:when test="${entry.status eq '2' }">发送失败</c:when>
					</c:choose>
				</td>
				<td>
						<a href="#" onclick="show('${entry.id}')">详情</a>&nbsp;
						<!-- <a href="#" onclick="edit('${entry.id}')">修改</a>&nbsp;
						<a href="#" onclick="del('${entry.id}')">删除</a>&nbsp; -->
				</td>
			</tr>
			</c:forEach>
			</table>
     	 <yk:page url="${yk}/userext/UserMessageLog/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">
function show(id){
	window.open('${yk}/userext/UserMessageLog/show.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}
function edit(id){
	window.open('${yk}/userext/UserMessageLog/edit.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}

$(function(){
	
	$('#btnedit').click(function(){
		var $id = $('[name=ids]:checkbox:checked');
		
		if($id.length==0){
			alert("请选择要修改的信息！");
			return false;
		}
		window.open('${yk}/userext/UserMessageLog/edit.do?'+$('#pkName').val()+'='+$id.val(),'','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btnadd').click(function(){
		window.open('${yk}/userext/UserMessageLog/create.do','','height=600,width=600,resizable=yes,location=no,scrollbars');
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
			$('#userId').val('');
			$('#mchId').val('');
			$('#name').val('');
			$('#phone').val('');
			$('#status').val('');
			$('#eventInfo').val('');
			$('#msgChannel').val('');
			$('#msgTemplate').val('');
			$('#createTimeBegin').val('');
			$('#createTimeEnd').val('');
			$('#updateTimeBegin').val('');
			$('#updateTimeEnd').val('');
			$('#delFlag').val('');
	});
});

function del(id){
	var ids = [];
	ids.push(id);
	v_deleteItems(ids);
}

function v_deleteItems(ids){
	if(ids.length>0 && confirm("确定要删除吗？")){
		$.post( "${yk}/userext/UserMessageLog/delete.do", {"items":ids.join(',')}, 
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


