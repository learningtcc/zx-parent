<%@ page import="com.yinker.user.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>商户账户管理</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01 title01_x">
	<h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/AccAcc/mchList.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
		<tr>	
			<td class="txtr" style="width: 100px">子帐户号：</td>		
			<td>
				<input value="<c:out value="${sacId}"/>" id="sacId" name="sacId" maxlength="19" type="number"/>
			</td>
			<td class="txtr" style="width: 100px">主账号：</td>		
			<td>
				<input value="<c:out value="${pacId}"/>" id="pacId" name="pacId" maxlength="19" type="number"/>
			</td>
			</tr>	
			<tr>	
			<td class="txtr" style="width: 100px">子帐户种类：</td>		
			<td>
				<select id="sacType" name="sacType">
					<option value="" <c:if test="${sacType == ''}">selected="selected"</c:if>>不限</option>
					<option value="0011" <c:if test="${sacType == '0011'}">selected="selected"</c:if>>活期借款人账户-0011</option>
					<option value="0012" <c:if test="${sacType == '0012'}">selected="selected"</c:if>>过期手续费回收账户-0012</option>
				</select>
			</td>
			<td class="txtr" style="width: 100px">状态 ：</td>		
			<td>
				<select id="status" name="status" style="width:172px">
					<option value="">不限</option>
					<option value="1" <c:if test="${status == '1'}">selected="selected"</c:if>>启用</option>
					<option value="2" <c:if test="${status == '2'}">selected="selected"</c:if>>停用</option>
					<option value="9" <c:if test="${status == '9'}">selected="selected"</c:if>>注销</option>
				</select>
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
	<h2>商户账户列表</h2>
</div>
		<div class="groupbtn">
			<!--<a href="javascript:void(0)" title="新建" class="btn001" id="btnadd">新增</a>
			 <a href="javascript:void(0)" title="修改" class="btn002" id="btnedit">修改</a>
			<a href="javascript:void(0)" title="删除"  class="btn004" id="btndelete">删除</a> -->
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
				<th>昨日余额 <div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>当前余额<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>临时余额 <div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>冻结金额<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>状态<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>删除标识<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>最后修改时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
			    <th>操作<div style="width: 145px; height: 1px; overflow: hidden;"></div></th>
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
				<div><yk:code2name columnName="sac_name" tableName="acc_sac_type" where=" sac_type=? " paramValue="${entry.sacType}"></yk:code2name></div>
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
				<div>
					<c:choose>
						<c:when test="${entry.status == 1 }">启用</c:when>
						<c:when test="${entry.status == 2 }">停用</c:when>
						<c:when test="${entry.status == 9 }">注销</c:when>
						<c:otherwise>未识别</c:otherwise>
					</c:choose>
				</div>
				</td>
				<td>
				<div>
					<c:choose>
						<c:when test="${entry.delFlag == 0}">正常</c:when>
						<c:when test="${entry.delFlag == 1}">删除</c:when>
						<c:otherwise>未识别</c:otherwise>
					</c:choose>
				</div>
				</td>
				<td>
				<div>${entry.lastUpdateTimeString }</div>
				</td>
				<td>
						<a href="#" onclick="show('${entry.id}')">详情</a>&nbsp;
						<a href="#" onclick="proof('${entry.pacId}')">交易流水</a>&nbsp;
						<!-- <a href="#" onclick="edit('${entry.id}')">修改</a>&nbsp;
						<a href="#" onclick="del('${entry.id}')">删除</a>&nbsp; -->
				</td>
			</tr>
			</c:forEach>
			</table>
     	 <yk:page url="${yk}/AccAcc/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">
function show(id){
	window.open('${yk}/AccAcc/show.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}
function proof(pacId){
	window.open('${yk}/AccProof/list.do?pacId='+pacId,'','height=600,width=960,resizable=yes,location=no,scrollbars');
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
			$('#lastUpdateTimeBegin').val('');
			$('#lastUpdateTimeEnd').val('');
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


