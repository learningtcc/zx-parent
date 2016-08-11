<%@ page import="com.yinker.user.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title> 账户错误信息日志</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01 title01_x">
	<h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/AccAccLog/list.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
		<tr>	
			<td class="txtr" style="width: 100px">子帐户号：</td>		
			<td>
				<input value="<c:out value="${filter.sacId}"/>" id="sacId" name="sacId" maxlength="19"/>
			</td>
			<td class="txtr" style="width: 100px">主账号：</td>		
			<td>
				<input value="<c:out value="${filter.pacId}"/>" id="pacId" name="pacId" maxlength="19"/>
			</td>
			<td class="txtr" style="width: 100px">子帐户种类：</td>		
			<td>
				&nbsp;<input value="<c:out value="${filter.sacType}"/>" id="sacType" name="sacType" maxlength="4"/>
			</td>
		</tr>	
		<tr>
			<td class="txtr" style="width: 100px">开户日期：</td>		
			<td>
				<input onClick="WdatePicker(WdatePicker({dateFmt:'yyyy-MM-dd'}))" value="<c:out value="${filter.beginOpenDate}"/>" id="beginOpenDate" name="beginOpenDate" size="10" readonly/>
				&nbsp;---&nbsp;<input onClick="WdatePicker(WdatePicker({dateFmt:'yyyy-MM-dd'}))" value="<c:out value="${filter.endOpenDate}"/>" id="endOpenDate" name="endOpenDate" size="10" readonly/>
			</td>
			<td class="txtr" style="width: 100px">销户日期：</td>		
			<td>
				<input onClick="WdatePicker(WdatePicker({dateFmt:'yyyy-MM-dd'}))" value="<c:out value="${filter.beginCloseDate}"/>" id="beginCloseDate" name="beginCloseDate" size="10" readonly/>
				&nbsp;---&nbsp;<input onClick="WdatePicker(WdatePicker({dateFmt:'yyyy-MM-dd'}))" value="<c:out value="${filter.endCloseDate}"/>" id="endCloseDate" name="endCloseDate" size="10" readonly/>
			</td>
			<td class="txtr" style="width: 100px">状态 ：</td>		
			<td>
				<select id="status" name="status">
					<option value="1" <c:if test="${filter.status eq '1' }">selected="selected"</c:if>>启用</option>
					<option value="2" <c:if test="${filter.status eq '2' }">selected="selected"</c:if>>停用</option>
					<option value="9" <c:if test="${filter.status eq '9' }">selected="selected"</c:if>>注销</option>
				</select>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 100px">创建时间：</td>		
			<td>
				<input onClick="WdatePicker(WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'}))" value="<c:out value="${filter.beginCreateTime}"/>" id="beginCreateTime" name="beginCreateTime" size="10" readonly/>
				&nbsp;---&nbsp;<input onClick="WdatePicker(WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'}))" value="<c:out value="${filter.endCreateTime}"/>" id="endCreateTime" name="endCreateTime" size="10" readonly/>
			</td>
			<td class="txtr" style="width: 100px">最后修改时间：</td>		
			<td>
				<input onClick="WdatePicker(WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'}))" value="<c:out value="${filter.beginLastUpdateTime}"/>" id="beginLastUpdateTime" name="beginLastUpdateTime" size="10" readonly/>
				&nbsp;---&nbsp;<input onClick="WdatePicker(WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'}))" value="<c:out value="${filter.endLastUpdateTime}"/>" id="endLastUpdateTime" name="endLastUpdateTime" size="10" readonly/>
			</td>
		</tr>	
	</table>
	<br/>
	<div align="center">
		<input type="submit" class="btn01" value="精确查询" onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('0');"/>
		<!-- <input type="submit" class="btn01" value="模糊查询" onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('1');"/> -->
		<input type="button" class="btn01" value="清 空" id="btnclear"/>
	</div>
</form>
	<br></br>
	<div class="title01">
	<h2>账户信息日志列表</h2>
</div>
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb" class="table02">
			<thead>
			 <tr>
			 	<th><input id="allChoose" type="checkbox"/></th>
			 	<th>序号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>子帐户号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>主账号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>子帐户种类<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>上次余额<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>当前余额<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>临时余额<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>冻结金额<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
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
				<div><c:out value="${entry.sacId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.pacId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.sacType}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.lstBal}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.curBal}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.tmpBal}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.frozenAmt}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.createTime}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.lastUpdateTime}"/></div>
				</td>
				<td>
						<a href="#" onclick="show('${entry.id}')">详情</a>&nbsp;
				</td>
			</tr>
			</c:forEach>
			
			</table>
     	 <yk:page url="${yk}/AccAccLog/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">
function show(id){
	window.open('${yk}/AccAccLog/show.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}
function edit(id){
	window.open('${yk}/AccAcc/edit.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}

$(function(){
	
	$('#btnedit').click(function(){
		var $id = $('[name=ids]:checkbox:checked');
		
		if($id.length==0){
			alert("请选择要修改的信息！");
			return false;
		}
		window.open('${yk}/AccAcc/edit.do?'+$('#pkName').val()+'='+$id.val(),'','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btnadd').click(function(){
		window.open('${yk}/AccAcc/create.do','','height=600,width=600,resizable=yes,location=no,scrollbars');
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
			$('#owner').val('');
			$('#ownerGroup').val('');
			$('#createTimeBegin').val('');
			$('#createTimeEnd').val('');
			$('#beginLastUpdateTime').val('');
			$('#endLastUpdateTime').val('');
			$('#accSacId').val('');
			$('#sacId').val('');
			$('#accPacId').val('');
			$('#pacId').val('');
			$('#sacType').val('');
			$('#cur').val('');
			$('#upItemId').val('');
			$('#lstBal').val('');
			$('#curBal').val('');
			$('#tmpBal').val('');
			$('#frozenAmt').val('');
			$('#openDateBegin').val('');
			$('#openDateEnd').val('');
			$('#closeDateBegin').val('');
			$('#closeDateEnd').val('');
			$('#status').val('');
			$('#accMac').val('');
			$('#delFlag').val('');
			$('#version').val('');
			$('#uid').val('');
			$('#accDepositType').val('');
	});
});

function del(id){
	var ids = [];
	ids.push(id);
	v_deleteItems(ids);
}

function v_deleteItems(ids){
	if(ids.length>0 && confirm("确定要删除吗？")){
		$.post( "${yk}/AccAcc/delete.do", {"items":ids.join(',')}, 
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


