<%@ page import="com.ink.msgcenter.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>邮件日志查询</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01 title01_x">
	<h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/msgcenter/EmailLog/list.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
		<tr>	
			<td class="txtr" style="width: 100px">商户名称：</td>		
			<td>
				<yk:select codeName="name" table="merchant_info" codeValue="id" id="merctId" name="merctId" select="${merctId }" where=" status != 2 " serviceName="msgcenterDubboBaseService"></yk:select>
			</td>
			<td class="txtr" style="width: 100px">通道：</td>		
			<td>
				<input type="hidden" id="chnIdvalue" value="${chnId }"></input>
				<select id="chnId" name="chnId">
				</select>
			</td>
			<td class="txtr" style="width: 100px">模板ID：</td>
			<td>
				<input value="<c:out value="${tempId}"/>" id="tempId" name="tempId" maxlength="19"/>
			</td>
			<td class="txtr" style="width: 100px">邮箱：</td>		
			<td>
				<input value="<c:out value="${email}"/>" id="email" name="email" maxlength="50"/>
			</td>
		</tr>	
		<tr>	
			<td class="txtr" style="width: 100px">主题：</td>		
			<td>
				<input value="<c:out value="${subject}"/>" id="subject" name="subject" maxlength="100"/>
			</td>
			<td class="txtr" style="width: 100px">业务单号：</td>		
			<td>
				<input value="<c:out value="${infoCode}"/>" id="infoCode" name="infoCode" maxlength="50"/>
			</td>
			<td class="txtr" style="width: 100px">发送状态：</td>		
			<td>
				<select id="sendStatus" name="sendStatus">
					<option value="" <c:if test="${sendStatus == ''}">selected="selected"</c:if>>不限</option>
					<option value="0" <c:if test="${sendStatus eq '0'}">selected="selected"</c:if>>待发送</option>	
					<option value="1" <c:if test="${sendStatus eq '1'}">selected="selected"</c:if>>已发送</option>
					<option value="2" <c:if test="${sendStatus eq '2'}">selected="selected"</c:if>>发送失败</option>
				</select>
			</td>
		</tr>	
		
	</table>
	<br/>
	<div align="center">
		<input type="submit" class="btn01" value="查 询" onclick="return check()"/>
		<input type="button" class="btn01" value="清 空" id="btnclear"/>
	</div>
</form>
	<br></br>
	<div class="title01">
	<h2>邮件日志列表</h2>
</div>
		<div class="groupbtn">

		</div>
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb" class="table02">
			<thead>
			 <tr>
			 	<th>序号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>商户名称<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>通道<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>模板ID<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>邮箱<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>主题<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>业务单号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>发送状态<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>提交时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>发送时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
			    <th>操作<div style="width: 75px; height: 1px; overflow: hidden;"></div></th>
	        </tr>
	        </thead>
			<c:forEach var="entry" items="${page.result}" varStatus="v">
			<tr>
				<input name="pkName" type="hidden" id="pkName" value="id"/>
				<td>${v.index + 1 }</td>
				<td>
				<div>
					<yk:code2name columnName="name" tableName="merchant_info" where="id = ?" paramValue="${entry.merctId}"  serviceName="msgcenterDubboBaseService"/>
				</div>
				</td>
				<td>
				<div>
					<yk:code2name columnName="name" tableName="email_channel" where="id = ?" paramValue="${entry.chnId}"  serviceName="msgcenterDubboBaseService"/>
				</div>
				</td>
				<td>
				<div>
					<c:out value="${entry.tempId}"/>
					<!--<yk:code2name columnName="temp_name" tableName="email_template" where="id = ?" paramValue="${entry.tempId}" serviceName="msgcenterDubboBaseService"/>-->
				</div>
				</td>
				<td>
				<div><c:out value="${entry.email}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.subject}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.infoCode}"/></div>
				</td>
				<td>
				<div>
					<c:choose>
						<c:when test="${entry.sendStatus == 0}">待发送</c:when>
						<c:when test="${entry.sendStatus == 1}">已发送</c:when>
						<c:when test="${entry.sendStatus == 2}">发送失败</c:when>
						<c:otherwise>未定义</c:otherwise>
					</c:choose>
				</div>
				</td>
				<td>
				<div><c:out value="${entry.submitTimeString}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.sendTimeString}"/></div>
				</td>
				<td>
						<a href="#" onclick="show('${entry.id}','${entry.merctCode}')">详情</a>&nbsp;
				</td>
			</tr>
			</c:forEach>
			</table>
     	 <yk:page url="${yk}/msgcenter/EmailLog/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">
function show(id,merctCode){
	window.open('${yk}/msgcenter/EmailLog/show.do?'+$('#pkName').val()+'='+id+'&merctCode='+merctCode,'height=600,width=600,resizable=yes,location=no,scrollbars');
}
function edit(id){
	window.open('${yk}/msgcenter/EmailLog/edit.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}

function check(){
	$('#pageNumber').val('1');
	$('#pageSize').val('10');
	$('#queryType').val('1');
	if($.trim($('#merctId').val()) == ""){
		alert('请选择商户!');
		return false;
	}
}

$(function(){
	
	$('#btnedit').click(function(){
		var $id = $('[name=ids]:checkbox:checked');
		
		if($id.length==0){
			alert("请选择要修改的信息！");
			return false;
		}
		window.open('${yk}/msgcenter/EmailLog/edit.do?'+$('#pkName').val()+'='+$id.val(),'','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btnadd').click(function(){
		window.open('${yk}/msgcenter/EmailLog/create.do','','height=600,width=600,resizable=yes,location=no,scrollbars');
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
			$('#merctId').val('');
			$('#merctCode').val('');
			$('#chnId').val('');
			$('#chnCode').val('');
			$('#tempId').val('');
			$('#email').val('');
			$('#subject').val('');
			$('#mailMsg').val('');
			$('#infoCode').val('');
			$('#sendStatus').val('');
			$('#submitTimeBegin').val('');
			$('#submitTimeEnd').val('');
			$('#sendTimeBegin').val('');
			$('#sendTimeEnd').val('');
			$('#sendException').val('');
	});
	
	$('#merctId').change(function(){ 
		//$.post("${yk}/merchant/merchantInfo/get.do",{id:$("#merctId").val()},function(data){
		//	$("#merctCode").val(data.sn);
		//},'json');
		$('#chnId').empty();
		$('#chnId').append('<option value="">请选择...</option>');
		$.post("${yk}/msgcenter/merchant/merchantChn/findEmailsByMerctId.do",{merctId:$("#merctId").val()},function(data){
			$.each(data,function(i,value){
				console.info(value);
				var chnv = $("#chnIdvalue").val();
				if(value.id == chnv){
					$("#chnId").append('<option value="'+value.id+'" selected="selected">'+value.name+'</option>');
				}
				$("#chnId").append('<option value="'+value.id+'">'+value.name+'</option>');
			});
		},'json');
		
	}); 
	
	if($("#merctId").val() != ''){
		$('#chnId').append('<option value="">未选择</option>');
		$.post("${yk}/msgcenter/merchant/merchantChn/findEmailsByMerctId.do",{merctId:$("#merctId").val()},function(data){
			$.each(data,function(i,value){
				console.info(value);
				var chnv = $("#chnIdvalue").val();
				if(value.id == chnv){
					$("#chnId").append('<option value="'+value.id+'" selected="selected">'+value.name+'</option>');
				}else{
					$("#chnId").append('<option value="'+value.id+'">'+value.name+'</option>');					
				}
			});
		},'json');
	}
	
});

function del(id){
	var ids = [];
	ids.push(id);
	v_deleteItems(ids);
}

function v_deleteItems(ids){
	if(ids.length>0 && confirm("确定要删除吗？")){
		$.post( "${yk}/msgcenter/EmailLog/delete.do", {"items":ids.join(',')},
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


