<%@ page import="com.ink.msgcenter.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=SmsCode.TABLE_ALIAS%> 维护</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01 title01_x">
	<h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/msgcenter/SmsCode/list.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
		<tr>	
			<td class="txtr" style="width: 100px">短信ID：</td>		
			<td>
				<input value="<c:out value="${smsId}"/>" id="smsId" name="smsId" maxlength="32"/>
			</td>
			<td class="txtr" style="width: 100px">短信代码：</td>		
			<td>
				<input value="<c:out value="${smsCode}"/>" id="smsCode" name="smsCode" maxlength="10"/>
			</td>
			<td class="txtr" style="width: 100px">商户名称：</td>		
			<td>
				<yk:select codeName="name" table="merchant_info" codeValue="id" id="merctId" name="merctId" select="${merctId }" where=" status != 2" serviceName="msgcenterDubboBaseService"/>
			</td>
			<td class="txtr" style="width: 100px">商户代码：</td>		
			<td>
				<input value="<c:out value="${merctCode}"/>" id="merctCode" name="merctCode" maxlength="20"/>
			</td>
		</tr>	
		<tr>	
			<td class="txtr" style="width: 100px">发送序列ID：</td>		
			<td>
				<input value="<c:out value="${taskId}"/>" id="taskId" name="taskId" maxlength="32"/>
			</td>
			<td class="txtr" style="width: 100px">手机号：</td>		
			<td>
				<input value="<c:out value="${mobile}"/>" id="mobile" name="mobile" maxlength="20"/>
			</td>
			<td class="txtr" style="width: 100px">扩展信息：</td>		
			<td>
				<input value="<c:out value="${extInfo}"/>" id="extInfo" name="extInfo" maxlength="200"/>
			</td>
			<td class="txtr" style="width: 100px">状态：</td>		
			<td>
				<select id="" name="">
					<option value="" <c:if test="${status eq '' }">selected="selected"</c:if>>不限</option>
					<option value="0" <c:if test="${status eq '0' }">selected="selected"</c:if>>有效</option>
					<option value="1" <c:if test="${status eq '1' }">selected="selected"</c:if>>失效</option>
				</select>
			</td>
		</tr>	
		<!-- 		<tr>	
			<td class="txtr" style="width: 100px">创建时间：</td>		
			<td colspan="2">
				<input onClick="WdatePicker()" value="<c:out value="${createTimeBegin}"/>" id="createTimeBegin" name="createTimeBegin" size="10"/>
				&nbsp;---&nbsp;
				<input onClick="WdatePicker()" value="<c:out value="${createTimeEnd}"/>" id="createTimeEnd" name="createTimeEnd" size="10"/>
			</td>
		</tr>	 -->
		
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
	<h2><%=SmsCode.TABLE_ALIAS%>列表</h2>
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
				<th>短信ID<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>短信代码<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>商户ID<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>商户代码<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>发送序列ID<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>手机号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>扩展信息<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>状态<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>创建时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
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
				<div><c:out value="${entry.smsId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.smsCode}"/></div>
				</td>
				<td>
				<div><yk:code2name columnName="name" tableName="merchant_info" where=" id=? " paramValue="${entry.merctId}" serviceName="msgcenterDubboBaseService"/></div>
				</td>
				<td>
				<div><c:out value="${entry.merctCode}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.taskId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.mobile}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.extInfo}"/></div>
				</td>
				<td>
				<div>
					<c:choose> 
			            <c:when test="${entry.status  eq 0}">正常</c:when>
			            <c:when test="${entry.status  eq 2}">删除</c:when>  
		            	<c:otherwise>未定义</c:otherwise> 
					</c:choose>
				</div>
				</td>
				<td>
				<div><c:out value="${entry.createTimeString}"/></div>
				</td>
				<td>
						<a href="#" onclick="show('${entry.id}')">详情</a>&nbsp;
						<!-- <a href="#" onclick="edit('${entry.id}')">修改</a>&nbsp;
						<a href="#" onclick="del('${entry.id}')">删除</a>&nbsp; -->
				</td>
			</tr>
			</c:forEach>
			</table>
     	 <yk:page url="${yk}/msgcenter/SmsCode/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">
function show(id){
	window.open('${yk}/msgcenter/SmsCode/show.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}
function edit(id){
	window.open('${yk}/msgcenter/SmsCode/edit.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}

$(function(){
	
	$('#btnedit').click(function(){
		var $id = $('[name=ids]:checkbox:checked');
		
		if($id.length==0){
			alert("请选择要修改的信息！");
			return false;
		}
		window.open('${yk}/msgcenter/SmsCode/edit.do?'+$('#pkName').val()+'='+$id.val(),'','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btnadd').click(function(){
		window.open('${yk}/msgcenter/SmsCode/create.do','','height=600,width=600,resizable=yes,location=no,scrollbars');
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
			$('#smsId').val('');
			$('#smsCode').val('');
			$('#merctId').val('');
			$('#merctCode').val('');
			$('#taskId').val('');
			$('#mobile').val('');
			$('#extInfo').val('');
			$('#status').val('');
			$('#createTimeBegin').val('');
			$('#createTimeEnd').val('');
	});
});

function del(id){
	var ids = [];
	ids.push(id);
	v_deleteItems(ids);
}

function v_deleteItems(ids){
	if(ids.length>0 && confirm("确定要删除吗？")){
		$.post( "${yk}/msgcenter/SmsCode/delete.do", {"items":ids.join(',')},
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


