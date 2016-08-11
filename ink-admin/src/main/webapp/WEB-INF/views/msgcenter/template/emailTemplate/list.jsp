<%@ page import="com.ink.msgcenter.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=EmailTemplate.TABLE_ALIAS%> 维护</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01 title01_x">
	<h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/msgcenter/EmailTemplate/list.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
		<tr>	
			<td class="txtr" style="width: 100px">商户：</td>		
			<td>
				<yk:select codeName="name" table="merchant_info" codeValue="id" id="merctId" name="merctId" select="${merctId }" where=" status != 2" serviceName="msgcenterDubboBaseService"/>
			</td>
			<td class="txtr" style="width: 100px">模板名称：</td>		
			<td>
				<input value="<c:out value="${tempName}"/>" id="tempName" name="tempName" maxlength="30"/>
			</td>
			<td class="txtr" style="width: 100px">邮件主题：</td>		
			<td>
				<input value="<c:out value="${mailSubject}"/>" id="mailSubject" name="mailSubject" maxlength="30"/>
			</td>

		</tr>
		<tr>
			<td class="txtr" style="width: 100px">解析方式：</td>
			<td>
				<yk:constantConvert htmlTag="select" dataTag="parseMethod" name="parseMethod" paramValue="${parseMethod}" sysTag="msgcenter"/>
			</td>
			<td class="txtr" style="width: 100px">状态：</td>
			<td>
				<yk:constantConvert htmlTag="select" dataTag="templateStatus" name="status" paramValue="${status}" sysTag="msgcenter"/>
			</td>
			<td class="txtr" style="width: 100px"></td>
			<td>
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
	<br></br>
	<div class="title01">
	<h2><%=EmailTemplate.TABLE_ALIAS%>列表</h2>
</div>
		<div class="groupbtn">
			<a href="javascript:void(0)" title="新建" class="btn001" id="btnadd">新增</a>
			<a href="javascript:void(0)" title="删除"  class="btn004" id="btndelete">删除</a>
		</div>
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb" class="table02">
			<thead>
			 <tr>
			 	<th>序号<div style="width: 30px; height: 1px; overflow: hidden;"></div></th>
			 	<th>模板ID<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>商户名称<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>商户代码<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>模板名称<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>邮件主题<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				 <th>解析方式<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>模板备注<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>模板参数<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>模板状态<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>创建时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
			    <th>操作<div style="width: 75px; height: 1px; overflow: hidden;"></div></th>
	        </tr>
	        </thead>
			<c:forEach var="entry" items="${page.result}" varStatus="v">
			<tr>
				<input name="pkName" type="hidden" id="pkName" value="id"/>
				<td>${v.index + 1 }</td>
				<td>
				<div><c:out value="${entry.id}"/></div>
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
				<div><c:out value="${entry.mailSubject}"/></div>
				</td>
				<td>
					<div>
						<yk:constantConvert htmlTag="text" dataTag="parseMethod" name="parseMethod" paramValue="${entry.parseMethod}" emptyTip="未定义" sysTag="msgcenter"/>
					</div>
				</td>
				<td>
				<div><c:out value="${entry.tempRemark}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.tempParam}"/></div>
				</td>
				<td>
				<div>
					<yk:constantConvert htmlTag="text" dataTag="templateStatus" name="status" paramValue="${entry.status}" emptyTip="未定义" sysTag="msgcenter"/>
				</div>
				</td>
				<td>
				<div><c:out value="${entry.createTimeString}" /></div>
				</td>
				<td>
						<a href="#" onclick="show('${entry.id}')">详情</a>&nbsp;
						<a href="#" onclick="preview('${entry.id}')">预览</a>&nbsp;
						<a href="#" onclick="edit('${entry.id}')">修改</a>&nbsp;
						<a href="#" onclick="del('${entry.id}')">删除</a>&nbsp;
				</td>
			</tr>
			</c:forEach>
			</table>
     	 <yk:page url="${yk}/msgcenter/EmailTemplate/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">
function show(id){
	window.open('${yk}/msgcenter/EmailTemplate/show.do?'+$('#pkName').val()+'='+id);
}
function preview(id){
	window.open('${yk}/msgcenter/EmailTemplate/preview.do?'+$('#pkName').val()+'='+id,'resizable=yes,location=no,scrollbars');
}
function log(id){
	window.open('${yk}/msgcenter/EmailTemplateLog/updateLog.do?templateId='+id,'resizable=yes,location=no,scrollbars');
}
function edit(id){
	window.open('${yk}/msgcenter/EmailTemplate/edit.do?'+$('#pkName').val()+'='+id,'resizable=yes,location=no,scrollbars');
}

$(function(){
	
	$('#btnedit').click(function(){
		var $id = $('[name=ids]:checkbox:checked');
		
		if($id.length==0){
			alert("请选择要修改的信息！");
			return false;
		}
		window.open('${yk}/msgcenter/EmailTemplate/edit.do?'+$('#pkName').val()+'='+$id.val(),'resizable=yes,location=no,scrollbars');
	});
	
	$('#btnadd').click(function(){
		window.open('${yk}/msgcenter/EmailTemplate/create.do','resizable=yes,location=no,scrollbars');
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
			$('#tempName').val('');
			$('#mailSubject').val('');
			$('#tempContent').val('');
			$('#tempRemark').val('');
			$('#tempParam').val('');
			$('#status').val('');
			$('#creatorId').val('');
			$('#creatorName').val('');
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
		$.post( "${yk}/msgcenter/EmailTemplate/delete.do", {"items":ids.join(',')},
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


