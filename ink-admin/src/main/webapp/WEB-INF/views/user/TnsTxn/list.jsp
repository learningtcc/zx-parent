
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>交易代码维护</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01 title01_x">
	<h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/user/TnsTxn/list.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
		<tr>	
			<td class="txtr" style="width: 100px">交易代码：</td>		
			<td>
				<input value="<c:out value="${txnCode}"/>" id="txnCode" name="txnCode" maxlength="8"/>
			</td>
			<td class="txtr" style="width: 100px">交易名称：</td>		
			<td>
				<input value="<c:out value="${txnName}"/>" id="txnName" name="txnName" maxlength="64"/>
			</td>
			<!-- 
			<td class="txtr" style="width: 100px">交易性质 ：</td>		
			<td>
				<select id="txnNature" name="txnNature">
					<option value="" <c:if test="${txnNature eq ''}">selected="selected"</c:if>>不限</option>
					<option value="1" <c:if test="${txnNature eq '1'}">selected="selected"</c:if>>充值</option>
					<option value="2" <c:if test="${txnNature eq '2'}">selected="selected"</c:if>>消费</option>
					<option value="3" <c:if test="${txnNature eq '3'}">selected="selected"</c:if>>资金转入</option>
					<option value="4" <c:if test="${txnNature eq '4'}">selected="selected"</c:if>>资金转出</option>
					<option value="5" <c:if test="${txnNature eq '5'}">selected="selected"</c:if>>汇入</option>
					<option value="6" <c:if test="${txnNature eq '6'}">selected="selected"</c:if>>汇出</option>
					<option value="7" <c:if test="${txnNature eq '7'}">selected="selected"</c:if>>手续费</option>
					<option value="8" <c:if test="${txnNature eq '8'}">selected="selected"</c:if>>取现</option>
					<option value="9" <c:if test="${txnNature eq '9'}">selected="selected"</c:if>>其他</option>
				</select>
			</td>
			 -->
		</tr>	
		<tr>	
			<td class="txtr" style="width: 100px">状态 ：</td>		
			<td>
				<select id="status" name="status">
					<option value="" <c:if test="${status eq ''}">selected="selected"</c:if>>不限</option>
					<option value="1" <c:if test="${status eq '1'}">selected="selected"</c:if>>启用</option>
					<option value="2" <c:if test="${status eq '2'}">selected="selected"</c:if>>停用</option>
					<option value="9" <c:if test="${status eq '9'}">selected="selected"</c:if>>注销</option>
				</select>
			</td>
			<td class="txtr" style="width: 100px">最后修改时间：</td>		
			<td colspan="2">
				<input onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<c:out value="${lastUpdateTimeBegin}"/>" id="lastUpdateTimeBegin" name="lastUpdateTimeBegin" size="10" readonly/>
				&nbsp;---&nbsp;
				<input onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<c:out value="${lastUpdateTimeEnd}"/>" id="lastUpdateTimeEnd" name="lastUpdateTimeEnd" size="10" readonly/>
			</td>
		</tr>	
	</table>
	<br/>
	<div align="center">
		<!-- <input type="submit" class="btn01" value="精确查询" onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('0');"/> -->
		<input type="submit" class="btn01" value="查询" onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('1');"/>
		<input type="button" class="btn01" value="清 空" id="btnclear"/>
	</div>
</form>
	<br></br>
	<div class="title01">
	<h2>交易列表</h2>
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
				<th>交易代码<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>交易名称<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>交易性质 <div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>状态 <div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>创建时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>最后修改时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
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
				<div><c:out value="${entry.txnCode}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.txnName}"/></div>
				</td>
				<td>
				<div>
				<c:choose>
					<c:when test="${entry.txnNature eq '1'}">充值</c:when>
					<c:when test="${entry.txnNature eq '2'}">消费</c:when>
					<c:when test="${entry.txnNature eq '3'}">资金转入</c:when>
					<c:when test="${entry.txnNature eq '4'}">资金转出</c:when>
					<c:when test="${entry.txnNature eq '5'}">汇入</c:when>
					<c:when test="${entry.txnNature eq '6'}">汇出</c:when>
					<c:when test="${entry.txnNature eq '7'}">手续费</c:when>
					<c:when test="${entry.txnNature eq '8'}">取现</c:when>
					<c:when test="${entry.txnNature eq '9'}">其他</c:when>
					<c:otherwise>未定义</c:otherwise>
				</c:choose>
				</div>
				</td>
				<td>
				<div>

				<c:choose>
					<c:when test="${entry.status eq '1'}">启用</c:when>
					<c:when test="${entry.status eq '2'}">停用</c:when>
					<c:when test="${entry.status eq '3'}">注销</c:when>
					<c:otherwise>未定义</c:otherwise>
				</c:choose>
				</div>
				</td>
				<td>
				<div><c:out value="${entry.createTimeString}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.lastUpdateTimeString}"/></div>
				</td>
				<td>
						<a href="#" onclick="show('${entry.id}')">详情</a>&nbsp;
						<a href="#" onclick="edit('${entry.id}')">修改</a>&nbsp;
						<!-- <a href="#" onclick="del('${entry.id}')">注销</a>&nbsp; -->
				</td>
			</tr>
			</c:forEach>
			</table>
     	 <yk:page url="${yk}/user/TnsTxn/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">
function show(id){
	window.open('${yk}/user/TnsTxn/show.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}
function edit(id){
	window.open('${yk}/user/TnsTxn/edit.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}

$(function(){
	
	$('#btnedit').click(function(){
		var $id = $('[name=ids]:checkbox:checked');
		
		if($id.length==0){
			alert("请选择要修改的信息！");
			return false;
		}
		window.open('${yk}/user/TnsTxn/edit.do?'+$('#pkName').val()+'='+$id.val(),'','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btnadd').click(function(){
		window.open('${yk}/user/TnsTxn/create.do','','height=600,width=600,resizable=yes,location=no,scrollbars');
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
			$('#createTimeBegin').val('');
			$('#createTimeEnd').val('');
			$('#lastUpdateTimeBegin').val('');
			$('#lastUpdateTimeEnd').val('');
			$('#txnCode').val('');
			$('#txnName').val('');
			$('#txnNature').val('');
			$('#runLv').val('');
			$('#dir').val('');
			$('#revAllowFlg').val('');
			$('#feeFlg').val('');
			$('#minAmt').val('');
			$('#maxAmt').val('');
			$('#channel').val('');
			$('#accOrder').val('');
			$('#accStatus').val('');
			$('#status').val('');
			$('#version').val('');
	});
});

function del(id){
	var ids = [];
	ids.push(id);
	v_deleteItems(ids);
}

function v_deleteItems(ids){
	if(ids.length>0 && confirm("确定要删除吗？")){
		$.post( "${yk}/user/TnsTxn/delete.do", {"items":ids.join(',')},
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


