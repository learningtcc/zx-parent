
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>发送信息 维护</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01 title01_x">
	<h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/userext/SendInfo/list.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
		<tr>	
			<td class="txtr" style="width: 100px">文件ID：</td>		
			<td>
				<input value="<c:out value="${fileId}"/>" id="fileId" name="fileId" maxlength="19" type="number"/>
			</td>
			<td class="txtr" style="width: 100px">发送类型：</td>		
			<td>
				<select id="sendType" name="sendType">
					<option value="0" <c:if test="${sendType eq '0' }">selected="selected"</c:if>>及时发送</option>
					<option value="1" <c:if test="${sendType eq '1' }">selected="selected"</c:if>>延时发送</option>
				</select>
			</td>
		</tr>	
		<tr>	
			<td class="txtr" style="width: 100px">操作员名称：</td>		
			<td>
				<input value="<c:out value="${operatorName}"/>" id="operatorName" name="operatorName" maxlength="64"/>
			</td>
			<td class="txtr" style="width: 100px">商户号：</td>		
			<td>
				<input value="<c:out value="${mchId}"/>" id="mchId" name="mchId" maxlength="19"/>
			</td>
			<!-- 
			<td class="txtr" style="width: 100px">状态：</td>		
			<td>
				<select id="status" name="status">
					<option value="0" <c:if test="${status eq '0' }">selected="selected"</c:if>>已保存</option>
					<option value="1" <c:if test="${status eq '1' }">selected="selected"</c:if>>已发送</option>
				</select>
			</td>
			 -->
		</tr>	
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
	<h2>发送信息列表</h2>
</div>
		<div class="groupbtn">
			<a href="javascript:void(0)" title="新建" class="btn001" id="btnadd">新增</a>
			<!-- <a href="javascript:void(0)" title="修改" class="btn002" id="btnedit">修改</a>
			<a href="javascript:void(0)" title="删除"  class="btn004" id="btndelete">删除</a> -->
		</div>
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb" class="table02">
			<thead>
			 <tr>
			 	<th><input id="allChoose" type="checkbox"/></th>
			 	<th>序号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
			 	<th>文件ID<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>文件原名称<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>发送类型<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>发送时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>操作员名称<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>商户号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
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
				<div><c:out value="${entry.originalName}"/></div>
				</td>
				<td>
				<c:choose>
					<c:when test="${entry.sendType eq 0 }">及时发送</c:when>
					<c:when test="${entry.sendType eq 1 }">延时发送</c:when>
					<c:otherwise>未定义</c:otherwise>
				</c:choose>
				</td>
				<td>
				<div><c:out value="${entry.sendTimeString}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.operatorName}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.mchId}"/></div>
				</td>
				<td>
				<div>
				<c:choose>
					<c:when test="${entry.status == 0 }">已保存</c:when>
					<c:when test="${entry.status == 1 }">已发送</c:when>
					<c:otherwise>未定义</c:otherwise>
				</c:choose>
				</div>
				</td>
				<td>
					<a href="#" onclick="show('${entry.id}')">详情</a>&nbsp;
					<c:if test="${entry.status == 0 }">
						<a href="#" onclick="send('${entry.id}')">发送</a>&nbsp;
						<!--<a href="#" onclick="edit('${entry.id}')">修改</a>&nbsp;
						<a href="#" onclick="del('${entry.id}')">删除</a>&nbsp;  -->
					</c:if>
				</td>
			</tr>
			</c:forEach>
			</table>
     	 <yk:page url="${yk}/userext/SendInfo/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">

function show(id){
	window.open('${yk}/userext/SendInfo/show.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}
function edit(id){
	window.open('${yk}/userext/SendInfo/edit.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}

$(function(){
	
	$('#btnedit').click(function(){
		var $id = $('[name=ids]:checkbox:checked');
		
		if($id.length==0){
			alert("请选择要修改的信息！");
			return false;
		}
		window.open('${yk}/userext/SendInfo/edit.do?'+$('#pkName').val()+'='+$id.val(),'','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btnadd').click(function(){
		window.open('${yk}/userext/SendInfo/create.do','','height=600,width=600,resizable=yes,location=no,scrollbars');
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
			$('#sendTimeBegin').val('');
			$('#sendTimeEnd').val('');
			$('#sendType').val('');
			$('#operatorName').val('');
			$('#filePath').val('');
			$('#eventInfo').val('');
			$('#mchId').val('');
			$('#status').val('');
	});
});

function del(id){
	var ids = [];
	ids.push(id);
	v_deleteItems(ids);
}

function v_deleteItems(ids){
	if(ids.length>0 && confirm("确定要删除吗？")){
		$.post( "${yk}/userext/SendInfo/delete.do", {"items":ids.join(',')}, 
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

function send(id){
	if(ids.length>0 && confirm("确定要发送吗？")){
		$.post( "${yk}/userext/SendInfo/send.do", {"id":id}, 
				function(data){
					if(data=='1'){
						alert('发送成功');
						$('#queryForm').submit();
					}else{
						alert('发送失败');
					}
				}
		);
	}
};
</script>
</body>
</html>


