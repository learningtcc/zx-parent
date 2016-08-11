<%@ page import="com.yinker.user.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>交易流水信息 维护</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01 title01_x">
	<h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/AccProof/list.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
		<tr>	
			<td class="txtr" style="width: 100px">子帐户种类：</td>		
			<td>
				<yk:select codeName="sac_name" table="acc_sac_type" codeValue="sac_type" id="sacType" name="sacType" select="${sacType }" where=" status = 1"></yk:select>
			</td>
			<td class="txtr" style="width: 100px">创建时间：</td>		
			<td>
				<input onClick="WdatePicker(WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'}))" value="<c:out value="${createTimeBegin}"/>" id="createTimeBegin" name="createTimeBegin" size="10"/>
				&nbsp;---&nbsp;<input onClick="WdatePicker(WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'}))" value="<c:out value="${createTimeEnd}"/>" id="createTimeEnd" name="createTimeEnd" size="10"/>
			</td>
			<td class="txtr" style="width: 100px">最后修改时间：</td>		
			<td>
				<input onClick="WdatePicker(WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'}))" value="<c:out value="${lastUpdateTimeBegin}"/>" id="lastUpdateTimeBegin" name="lastUpdateTimeBegin" size="10"/>
				&nbsp;---&nbsp;<input onClick="WdatePicker(WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'}))" value="<c:out value="${lastUpdateTimeEnd}"/>" id="lastUpdateTimeEnd" name="lastUpdateTimeEnd" size="10"/>
			</td>
		</tr>	
		<!-- 
		<tr>	
			
		</tr>	
		 -->
	</table>
	<br/>
	<div align="center">
		<input type="submit" class="btn01" value="精确查询" onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('0');"/>
		<!--  <input type="submit" class="btn01" value="模糊查询" onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('1');"/>-->
		<input type="button" class="btn01" value="清 空" id="btnclear"/>
	</div>
</form>
	<br></br>
	<div class="title01">
	<h2>交易流水信息列表</h2>
</div>
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb" class="table02">
			<thead>
			 <tr>
			 	<th><input id="allChoose" type="checkbox"/></th>
			 	<th>序号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>主账号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>子帐户号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>子帐户种类<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>之前金额<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>当前余额<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>交易代码<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>修改金额<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>创建时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>最后修改时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
			  <!--  <th>操作<div style="width: 75px; height: 1px; overflow: hidden;"></div></th> --> 
	        </tr>
	        </thead>
			<c:forEach var="entry" items="${page.result}" varStatus="v">
			<tr>
				<td><input name="ids" type="checkbox" value="${entry.id}"/>
				<input name="pkName" type="hidden" id="pkName" value="id"/>
				</td>
				<td>${v.index + 1 + (page.pageNumber-1) * page.pageSize }</td>
				<td>
				<div><c:out value="${entry.pacId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.sacId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.sacType}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.beforeBal}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.curBal}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.txnCode}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.modifyBal}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.createTimeString}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.lastUpdateTimeString}"/></div>
				</td>
				<!-- <td>
						<a href="#" onclick="show('${entry.id}')">详情</a>&nbsp;
						<a href="#" onclick="edit('${entry.id}')">修改</a>&nbsp;
						<a href="#" onclick="del('${entry.id}')">删除</a>&nbsp; 
				</td>-->
			</tr>
			</c:forEach>
			</table>
     	 <yk:page url="${yk}/AccProof/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">
function show(id){
	window.open('${yk}/AccProof/show.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}
function edit(id){
	window.open('${yk}/AccProof/edit.do?'+$('#pkName').val()+'='+id,'','height=600,width=960,resizable=yes,location=no,scrollbars');
}

$(function(){
	
	$('#btnedit').click(function(){
		var $id = $('[name=ids]:checkbox:checked');
		
		if($id.length==0){
			alert("请选择要修改的信息！");
			return false;
		}
		window.open('${yk}/AccProof/edit.do?'+$('#pkName').val()+'='+$id.val(),'','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btnadd').click(function(){
		window.open('${yk}/AccProof/create.do','','height=600,width=600,resizable=yes,location=no,scrollbars');
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
			$('#uid').val('');
			$('#pacId').val('');
			$('#sacId').val('');
			$('#sacType').val('');
			$('#cur').val('');
			$('#beforeBal').val('');
			$('#curBal').val('');
			$('#txnCode').val('');
			$('#modifyBal').val('');
			$('#filler1').val('');
			$('#filler2').val('');
			$('#filler3').val('');
	});
});

function del(id){
	var ids = [];
	ids.push(id);
	v_deleteItems(ids);
}

function v_deleteItems(ids){
	if(ids.length>0 && confirm("确定要删除吗？")){
		$.post( "${yk}/AccProof/delete.do", {"items":ids.join(',')}, 
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


