
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>  维护</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01 title01_x">
	<h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/trade/AsileBusinessSupport/list.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
		<tr>	
			<td class="txtr" style="width: 100px">商户号：</td>		
			<td>
				&nbsp;<input value="<c:out value="${mchId}"/>" id="mchId" name="mchId" maxlength="20"/>
			</td>
			<td class="txtr" style="width: 100px">渠道号：</td>		
			<td>
				&nbsp;
				<yk:constantConvert htmlTag="select" dataTag="" data="${asileData}" paramValue="${asileCode}" emptyTip=" " codeTag="asileCode" nameTag="asileName" name="channelNo"/>
				<%-- <input value="<c:out value="${channelNo}"/>" id="channelNo" name="channelNo" maxlength="10"/> --%>
			</td>
			<%-- <td class="txtr" style="width: 100px">支持的支付方式：</td>		
			<td>
				&nbsp;<input value="<c:out value="${payType}"/>" id="payType" name="payType" maxlength="10"/>
			</td> --%>
			<td class="txtr" style="width: 100px">商户在渠道开通的商户号：</td>		
			<td>
				&nbsp;<input value="<c:out value="${channelMchId}"/>" id="channelMchId" name="channelMchId" maxlength="50"/>
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
	<h2> 列表</h2>
</div>
		<div class="groupbtn">
			<a href="javascript:void(0)" title="新建" class="btn001" id="btnadd">新增</a>
			<a href="javascript:void(0)" title="修改" class="btn002" id="btnedit">修改</a>
			<a href="javascript:void(0)" title="删除"  class="btn004" id="btndelete">删除</a>
		</div>
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb" class="table02">
			<thead>
			 <tr>
			 	<th><input id="allChoose" type="checkbox"/></th>
			 	<th>序号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>商户号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>渠道号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>支持的支付方式<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>商户在渠道开通的商户号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>渠道提供的终端号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>证书编号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>备注<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
<!-- 				<th>扩展字段<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
<!-- 				<th>1 启用 0 停用<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
<!-- 				<th>扩展字段<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
				<th>创建时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>最后更新时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>操作人<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
			    <th>操作<div style="width: 75px; height: 1px; overflow: hidden;"></div></th>
	        </tr>
	        </thead>
			<c:forEach var="entry" items="${page.result}" varStatus="v">
			<tr>
				<td><input name="ids" type="checkbox" value="${entry.id}"/>
				<input name="pkName" type="hidden" id="pkName" value="id"/>
				</td>
				<td>${(page.pageSize*(page.pageNumber-1))+(v.index + 1) }</td>
				<td>
				<div><c:out value="${entry.mchId}"/></div>
				</td>
				<td>
				<div>
				<yk:constantConvert htmlTag="text" dataTag="" data="${asileData}" paramValue="${entry.channelNo}" emptyTip=" " codeTag="asileCode" nameTag="asileName"/>
				<%-- <c:out value="${entry.channelNo}"/> --%>
				</div>
				</td>
				<td>
				<div>
				<yk:constantConvert htmlTag="text" dataTag="" data="${payTypeDate}" paramValue="${entry.payType}" emptyTip=" " codeTag="code" nameTag="value"/>
				<%-- <c:out value="${entry.payType}"/> --%>
				
				</div>
				</td>
				<td>
				<div><c:out value="${entry.channelMchId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.terminalId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.certId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.remark}"/></div>
				</td>
<!-- 				<td> -->
<%-- 				<div><c:out value="${entry.ext1}"/></div> --%>
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 				<div><c:out value="${entry.status}"/></div> --%>
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 				<div><c:out value="${entry.ext2}"/></div> --%>
<!-- 				</td> -->
				<td>
				<div><c:out value="${entry.createTimeString}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.lastupdateTimeString}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.operator}"/></div>
				</td>
				<td>
						<a href="#" onclick="show('${entry.id}')">详情</a>&nbsp;
						<a href="#" onclick="edit('${entry.id}')">修改</a>&nbsp;
						<a href="#" onclick="del('${entry.id}')">删除</a>&nbsp;
				</td>
			</tr>
			</c:forEach>
			</table>
     	 <yk:page url="${yk}/trade/AsileBusinessSupport/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">
function show(id){
	window.open('${yk}/trade/AsileBusinessSupport/show.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}
function edit(id){
	window.open('${yk}/trade/AsileBusinessSupport/edit.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}

$(function(){
	
	$('#btnedit').click(function(){
		var $id = $('[name=ids]:checkbox:checked');
		
		if($id.length==0){
			alert("请选择要修改的信息！");
			return false;
		}
		window.open('${yk}/trade/AsileBusinessSupport/edit.do?'+$('#pkName').val()+'='+$id.val(),'','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btnadd').click(function(){
		window.open('${yk}/trade/AsileBusinessSupport/create.do','','height=600,width=600,resizable=yes,location=no,scrollbars');
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
			$('#mchId').val('');
			$('#channelNo').val('');
			$('#payType').val('');
			$('#channelMchId').val('');
			$('#terminalId').val('');
			$('#certId').val('');
			$('#remark').val('');
			$('#ext1').val('');
			$('#status').val('');
			$('#ext2').val('');
			$('#createTimeBegin').val('');
			$('#createTimeEnd').val('');
			$('#lastupdateTimeBegin').val('');
			$('#lastupdateTimeEnd').val('');
			$('#operator').val('');
	});
});

function del(id){
	var ids = [];
	ids.push(id);
	v_deleteItems(ids);
}

function v_deleteItems(ids){
	if(ids.length>0 && confirm("确定要删除吗？")){
		$.post( "${yk}/trade/AsileBusinessSupport/delete.do", {"items":ids.join(',')}, 
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


