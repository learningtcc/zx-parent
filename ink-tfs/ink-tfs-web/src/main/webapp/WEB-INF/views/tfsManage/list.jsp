<%@ page import="com.yinker.tfs.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=TfsFileName.TABLE_ALIAS%> 维护</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01 title01_x">
	<h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/tfsManage/list.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<%--<input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>--%>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
		<tr>	
			<td class="txtr" style="width: 100px">系统代码：</td>		
			<td>
				&nbsp;
				<select id="sourceCode" name="sourceCode">
					<option value="">全部</option>
					<c:forEach var="entry" items="${sourcesMap}" varStatus="v">
						<option value="<c:out value="${entry.key}"/>" <c:if test="${entry.key == sourceCode }"> selected = "selected"</c:if> ><c:out value="${entry.value}"/></option>
					</c:forEach>
				</select>
			</td>
			<td class="txtr" style="width: 100px">模块代码：</td>		
			<td>
				&nbsp;<input value="<c:out value="${moduleCode}"/>" id="moduleCode" name="moduleCode" maxlength="19"/>
			</td>
			<td class="txtr" style="width: 100px">tfs文件名：</td>		
			<td>
				&nbsp;<input value="<c:out value="${tfsName}"/>" id="tfsName" name="tfsName" maxlength="100"/>
			</td>
			<td class="txtr" style="width: 100px">文件原始名：</td>		
			<td>
				&nbsp;<input value="<c:out value="${fileName}"/>" id="fileName" name="fileName" maxlength="100"/>
			</td>
		</tr>	
		<tr>	
			<td class="txtr" style="width: 100px">文件后缀：</td>		
			<td>
				&nbsp;<input value="<c:out value="${suffix}"/>" id="suffix" name="suffix" maxlength="50"/>
			</td>
			<td class="txtr" style="width: 100px">文件隐藏状态：</td>
            <td>
                &nbsp;
                <select id="status" name="status">
                    <option value="">全部</option>
                    <option value="0" <c:if test="${status=='0'}"> selected = "selected"</c:if> >正常</option>
                    <option value="1" <c:if test="${status=='1'}"> selected = "selected"</c:if> >隐藏</option>
                </select>
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
		</tr>	
	</table>
	<br/>
	<div align="center">
		<input type="submit" class="btn01" value="查询" onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('0');"/>
		<input type="button" class="btn01" value="清 空" id="btnclear"/>
	</div>
</form>
	<br></br>
	<div class="title01">
	<h2><%=TfsFileName.TABLE_ALIAS%>列表</h2>
</div>
		<div class="groupbtn">
			<a href="javascript:void(0)" title="删除"  class="btn004" id="btndelete">删除</a>
		</div>
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb" class="table02">
			<thead>
			 <tr>
			 	<th><input id="allChoose" type="checkbox"/></th>
			 	<th>序号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>系统代码<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>模块代码<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>tfs文件名<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>文件原始名<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>文件后缀<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>文件状态 0：正常；1：隐藏<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
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
				<div><c:out value="${entry.sourceCode}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.moduleCode}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.tfsName}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.fileName}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.suffix}"/></div>
				</td>
                <td>
                    <div>
                        <c:if test="${entry.status=='0'}">正常</c:if>
                        <c:if test="${entry.status=='1'}">隐藏</c:if>
                    </div>
                </td>
				<td>
					<div><c:out value="${entry.createTimeString}"/></div>
				</td>
				<td>
					<div><c:out value="${entry.updateTimeString}"/></div>
				</td>
				<td>
						<a href="#" onclick="del('${entry.id}')">删除</a>&nbsp;
                        <c:if test="${entry.status=='0'}">
                            <a href="#" onclick="downloadFile('${entry.fileName}'+'.'+'${entry.suffix}','${entry.sourceCode}','${entry.moduleCode}')">下载</a>&nbsp;
                        </c:if>
				</td>
			</tr>
			</c:forEach>
			</table>
     	 <yk:page url="${yk}/tfsManage/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>
<form id="downloadForm" name="downloadForm" action="<c:url value="/tfs/download.do"/>"  method="post" >
	<input type="hidden" id="down_fileName" name="fileName" value="">
	<input type="hidden" id="down_sourceCode" name="sourceCode" value="">
	<input type="hidden" id="down_moduleCode" name="moduleCode" value="">
</form>
<script type="text/javascript">
function show(id){
	window.open('${yk}/tfsManage/show.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}
function edit(id){
	window.open('${yk}/tfsManage/edit.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}

$(function(){
	
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
			$('#sourceCode').val('');
			$('#moduleCode').val('');
			$('#tfsName').val('');
			$('#fileName').val('');
			$('#suffix').val('');
			$('#status').val('');
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
		$.post( "${yk}/tfsManage/delete.do", {"items":ids.join(',')},
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
function downloadFile(fileName,sourceCode,moduleCode){
	$("#down_fileName").val(fileName);
	$("#down_sourceCode").val(sourceCode);
	$("#down_moduleCode").val(moduleCode);
	$("#downloadForm").submit();
}



</script>
</body>
</html>


