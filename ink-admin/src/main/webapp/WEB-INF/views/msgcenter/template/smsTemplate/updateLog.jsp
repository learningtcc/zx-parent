<%@ page import="com.ink.msgcenter.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>短信修改日志</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01 title01_x">
	<h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/msgcenter/SmsTemplateLog/updateLog.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
		<!-- 
		<tr>	
			<td class="txtr" style="width: 100px">商户：</td>		
			<td>
				<yk:select codeName="name" table="merchant_info" codeValue="id" id="merctId" name="merctId" select="${merctId }" serviceName="msgcenterDubboBaseService"/>
			</td>
			<td class="txtr" style="width: 100px">模板名称：</td>		
			<td>
				<input value="<c:out value="${tempName}"/>" id="tempName" name="tempName" maxlength="30"/>
			</td>
			<td class="txtr" style="width: 100px">解析方式：</td>		
			<td>
				<select id="parseMethod" name="parseMethod">
					<option value="">不限</option>
					<option value="1" <c:if test="${tempParam == '1'}">selected="selected"</c:if>>JSON解析</option>
					<option value="2" <c:if test="${tempParam == '2'}">selected="selected"</c:if>>顺序解析</option>
				</select>
			</td>
			<input id="" name="templateId" type="hidden" value="${templateId }"></input>
		</tr> -->
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
	<h2><%=SmsTemplateLog.TABLE_ALIAS%>列表</h2>
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
				<th>模板<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>商户<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>商户代码<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>模板名称<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>解析方式<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>模板参数<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>模板备注<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>修改内容<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>操作人姓名<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>操作时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
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
					<yk:code2name columnName="temp_name" tableName="sms_template" where="id = ?" paramValue="${entry.templateId}" serviceName="msgcenterDubboBaseService"/>
				</div>
				</td>
				<td>
				<div>
					<yk:code2name columnName="name" tableName="merchant_info" where=" id=? " paramValue="${entry.merctId}" serviceName="msgcenterDubboBaseService"/>
				</div>
				</td>
				<td>
				<div><c:out value="${entry.merctCode}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.tempName}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.parseMethod}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.tempParam}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.tempRemark}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.logRemark}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.operatorName}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.operateTimeString}"/></div>
				</td>
				<td>
						<a href="#" onclick="show('${entry.id}')">详情</a>&nbsp;
						<!-- <a href="#" onclick="edit('${entry.id}')">修改</a>&nbsp;
						<a href="#" onclick="del('${entry.id}')">删除</a>&nbsp; -->
				</td>
			</tr>
			</c:forEach>
			</table>
     	 <yk:page url="${yk}/msgcenter/SmsTemplateLog/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">
function show(id){
	window.open('${yk}/msgcenter/SmsTemplateLog/show.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}
function edit(id){
	window.open('${yk}/msgcenter/SmsTemplateLog/edit.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}

$(function(){
	
	$('#btnedit').click(function(){
		var $id = $('[name=ids]:checkbox:checked');
		
		if($id.length==0){
			alert("请选择要修改的信息！");
			return false;
		}
		window.open('${yk}/msgcenter/SmsTemplateLog/edit.do?'+$('#pkName').val()+'='+$id.val(),'','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btnadd').click(function(){
		window.open('${yk}/msgcenter/SmsTemplateLog/create.do','','height=600,width=600,resizable=yes,location=no,scrollbars');
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
			$('#templateId').val('');
			$('#merctId').val('');
			$('#merctCode').val('');
			$('#tempName').val('');
			$('#tempContent').val('');
			$('#parseMethod').val('');
			$('#tempParam').val('');
			$('#tempRemark').val('');
			$('#logRemark').val('');
			$('#operatorId').val('');
			$('#operatorName').val('');
			$('#operateTimeBegin').val('');
			$('#operateTimeEnd').val('');
	});
});

function del(id){
	var ids = [];
	ids.push(id);
	v_deleteItems(ids);
}

function v_deleteItems(ids){
	if(ids.length>0 && confirm("确定要删除吗？")){
		$.post( "${yk}/msgcenter/SmsTemplateLog/delete.do", {"items":ids.join(',')},
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


