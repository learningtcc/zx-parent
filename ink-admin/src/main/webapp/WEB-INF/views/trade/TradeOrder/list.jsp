<%@page import="com.ink.trade.api.platform.trade.model.base.TradeOrderEntity"%>
<%@ page import="com.ink.trade.api.platform.trade.model.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=TradeOrderEntity.TABLE_ALIAS%> 维护</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01 title01_x">
	<h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/trade/TradeOrder/list.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
		<tr>	
			<td class="txtr" style="width: 100px">商户号：</td>		
			<td>
				&nbsp;<input value="<c:out value="${mchId}"/>" id="mchId" name="mchId" maxlength="20"/>
			</td>
			<td class="txtr" style="width: 100px">订单日期：</td>		
			<td>
				&nbsp;<input onClick="WdatePicker()" value="<c:out value="${orderTimeBegin}"/>" id="orderTimeBegin" name="orderTimeBegin" size="10"/>
				<input onClick="WdatePicker()" value="<c:out value="${orderTimeEnd}"/>" id="orderTimeEnd" name="orderTimeEnd" size="10"/>
			</td>
			<td class="txtr" style="width: 100px">订单状态：</td>		
			<td>
			<yk:constantConvert htmlTag="select" dataTag="tradeOrderStatus" codeTag="value" nameTag="name" data="${tradeOrderStatus}" name="status" isAutoItem="true" emptyTip="0"/>
<%-- 				&nbsp;<input value="<c:out value="${status}"/>" id="status" name="status" maxlength="10"/> --%>
			</td>
			<td class="txtr" style="width: 100px">创建时间：</td>		
			<td>
				&nbsp;<input onClick="WdatePicker()" value="<c:out value="${createTimeBegin}"/>" id="createTimeBegin" name="createTimeBegin" size="10"/>
				<input onClick="WdatePicker()" value="<c:out value="${createTimeEnd}"/>" id="createTimeEnd" name="createTimeEnd" size="10"/>
			</td>
			<td class="txtr" style="width: 100px">渠道名称：</td>		
			<td>
			<yk:constantConvert htmlTag="select" dataTag="asileName" codeTag="asileCode" nameTag="asileName" data="${asileInfos}" name="channelNo" isAutoItem="true" emptyTip="0"/>
<%-- 				&nbsp;<input value="<c:out value="${channelNo}"/>" id="channelNo" name="channelNo" maxlength="10"/> --%>
			</td>
			</tr>
			<tr>
			<td class="txtr" style="width: 100px">用户id：</td>		
			<td>
				&nbsp;<input value="<c:out value="${userId}"/>" id="userId" name="userId" maxlength="64"/>
			</td>
			<td class="txtr" style="width: 100px">商户订单号：</td>		
			<td>
				&nbsp;<input value="<c:out value="${orderId}"/>" id="orderId" name="orderId" maxlength="64"/>
			</td>
			<td class="txtr" style="width: 100px">银行：</td>		
			<td>
			<yk:constantConvert htmlTag="select" dataTag="bankName" codeTag="bankShortName" nameTag="bankName" data="${basicBanks}" name="bankShort" isAutoItem="true" emptyTip="0"/>
<%-- 				&nbsp;<input value="<c:out value="${bankShort}"/>" id="bankShort" name="bankShort" maxlength="10"/> --%>
			</td>
			<td class="txtr" style="width: 100px">支付方式：</td>		
			<td>
			<yk:constantConvert htmlTag="select" dataTag="paytype" codeTag="value" nameTag="name" data="${payType}" name="payType" isAutoItem="true" emptyTip="0"/>
<%-- 				&nbsp;<input value="<c:out value="${payType}"/>" id="payType" name="payType" maxlength="2"/> --%>
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
	<h2><%=TradeOrderEntity.TABLE_ALIAS%>列表</h2>
</div>
<!-- 		<div class="groupbtn"> -->
<!-- 			<a href="javascript:void(0)" title="新建" class="btn001" id="btnadd">新增</a> -->
<!-- 			<a href="javascript:void(0)" title="修改" class="btn002" id="btnedit">修改</a> -->
<!-- 			<a href="javascript:void(0)" title="删除"  class="btn004" id="btndelete">删除</a> -->
<!-- 		</div> -->
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb" class="table02">
			<thead>
			 <tr>
			 	<th><input id="allChoose" type="checkbox"/></th>
			 	<th>序号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>请求流水号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>商户号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
<!-- 				<th>交易名称<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
<!-- 				<th>交易码<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
				<th>订单日期<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
<!-- 				<th>手机号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
				<th>姓名<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>订单金额<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>订单状态<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
<!-- 				<th>版本号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
				<th>创建时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
<!-- 				<th>操作人<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
<!-- 				<th>联动优势快捷订单流水号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
<!-- 				<th>绑卡信息表主键<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
				<th>最后修改时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>渠道名称<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>银行卡号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>用户id<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>商户订单号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>商户交易时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>账户类型<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>银行<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
<!-- 				<th>渠道token<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
				<th>支付方式<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
<!-- 				<th>回调url<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
<!-- 				<th>routeBusinessType<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
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
				<div><c:out value="${entry.reqId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.mchId}"/></div>
				</td>
<!-- 				<td> -->
<%-- 				<div><c:out value="${entry.txnName}"/></div> --%>
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 				<div><c:out value="${entry.txnCode}"/></div> --%>
<!-- 				</td> -->
				<td>
				<div><c:out value="${entry.orderTimeString}"/></div>
				</td>
<!-- 				<td> -->
<%-- 				<div><c:out value="${entry.phoneNo}"/></div> --%>
<!-- 				</td> -->
				<td>
				<div><c:out value="${entry.userName}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.amt}"/></div>
				</td>
				<td>
				<div>
				<yk:constantConvert htmlTag="text" dataTag="tradeOrderStatus" codeTag="value" paramValue="${entry.status}" nameTag="name" data="${tradeOrderStatus}" name="status" isAutoItem="false" emptyTip="0"/>
<%-- 				<c:out value="${entry.status}"/> --%>
				</div>
				</td>
<!-- 				<td> -->
<%-- 				<div><c:out value="${entry.version}"/></div> --%>
<!-- 				</td> -->
				<td>
				<div><c:out value="${entry.createTimeString}"/></div>
				</td>
<!-- 				<td> -->
<%-- 				<div><c:out value="${entry.operator}"/></div> --%>
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 				<div><c:out value="${entry.remark}"/></div> --%>
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 				<div><c:out value="${entry.cid}"/></div> --%>
<!-- 				</td> -->
				<td>
				<div><c:out value="${entry.lastupdateTimeString}"/></div>
				</td>
				<td>
				<div>
<%-- 				<c:out value="${entry.channelNo}"/> --%>
				<yk:constantConvert htmlTag="text" dataTag="asileName" codeTag="asileCode" paramValue="${entry.channelNo}" nameTag="asileName" data="${asileInfos}" name="channelNo" isAutoItem="false" emptyTip="0"/>
				</div>
				</td>
				<td>
				<div><c:out value="${entry.cardNo}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.userId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.orderId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.tradeDateString}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.accountType}"/></div>
				</td>
				<td>
				<div>
				<yk:constantConvert htmlTag="text" dataTag="bankName" codeTag="bankShortName" paramValue="${entry.bankShort}" nameTag="bankName" data="${basicBanks}" name="bankShort" isAutoItem="false" emptyTip="0"/>
<%-- 				<c:out value="${entry.bankShort}"/> --%>
				</div>
				</td>
<!-- 				<td> -->
<%-- 				<div><c:out value="${entry.token}"/></div> --%>
<!-- 				</td> -->
				<td>
				<div>
<%-- 				<c:out value="${entry.payType}"/> --%>
				<yk:constantConvert htmlTag="text" dataTag="payType" codeTag="value" paramValue="${entry.payType}" nameTag="name" data="${payType}" name="payType" isAutoItem="false" emptyTip="0"/>
				
				</div>
				</td>
<!-- 				<td> -->
<%-- 				<div><c:out value="${entry.noticeUrl}"/></div> --%>
<!-- 				</td> -->
<!-- 				<td> -->
<%-- 				<div><c:out value="${entry.routeBusinessType}"/></div> --%>
<!-- 				</td> -->
				<td>
						<a href="#" onclick="show('${entry.reqId}')">详情</a>&nbsp;
<%-- 						<a href="#" onclick="edit('${entry.id}')">修改</a>&nbsp; --%>
<%-- 						<a href="#" onclick="del('${entry.id}')">删除</a>&nbsp; --%>
				</td>
			</tr>
			</c:forEach>
			</table>
     	 <yk:page url="${yk}/trade/TradeOrder/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">
function show(id){
	window.open('${yk}/trade/TradeOrder/show.do?reqId='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}
function edit(id){
	window.open('${yk}/trade/TradeOrder/edit.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}

$(function(){
	
	$('#btnedit').click(function(){
		var $id = $('[name=ids]:checkbox:checked');
		
		if($id.length==0){
			alert("请选择要修改的信息！");
			return false;
		}
		window.open('${yk}/trade/TradeOrder/edit.do?'+$('#pkName').val()+'='+$id.val(),'','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btnadd').click(function(){
		window.open('${yk}/trade/TradeOrder/create.do','','height=600,width=600,resizable=yes,location=no,scrollbars');
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
			$('#reqId').val('');
			$('#mchId').val('');
			$('#txnName').val('');
			$('#txnCode').val('');
			$('#orderTimeBegin').val('');
			$('#orderTimeEnd').val('');
			$('#phoneNo').val('');
			$('#userName').val('');
			$('#amt').val('');
			$('#status').val('');
			$('#version').val('');
			$('#createTimeBegin').val('');
			$('#createTimeEnd').val('');
			$('#operator').val('');
			$('#remark').val('');
			$('#cid').val('');
			$('#lastupdateTimeBegin').val('');
			$('#lastupdateTimeEnd').val('');
			$('#channelNo').val('');
			$('#cardNo').val('');
			$('#userId').val('');
			$('#orderId').val('');
			$('#tradeDateBegin').val('');
			$('#tradeDateEnd').val('');
			$('#accountType').val('');
			$('#bankShort').val('');
			$('#token').val('');
			$('#payType').val('');
			$('#noticeUrl').val('');
			$('#routeBusinessType').val('');
	});
});

function del(id){
	var ids = [];
	ids.push(id);
	v_deleteItems(ids);
}

function v_deleteItems(ids){
	if(ids.length>0 && confirm("确定要删除吗？")){
		$.post( "${yk}/trade/TradeOrder/delete.do", {"items":ids.join(',')}, 
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


