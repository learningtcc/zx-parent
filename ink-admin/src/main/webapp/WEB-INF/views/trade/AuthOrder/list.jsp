
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title> 维护</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01 title01_x">
	<h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/trade/AuthOrder/list.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
		<tr>
			<td class="txtr" style="width: 100px">创建时间：</td>		
			<td>
				&nbsp;<input onClick="WdatePicker()" value="<c:out value="${createTimeBegin}"/>" id="createTimeBegin" name="createTimeBegin" size="10"/>
				<input onClick="WdatePicker()" value="<c:out value="${createTimeEnd}"/>" id="createTimeEnd" name="createTimeEnd" size="10"/>
			</td>
			
			<td class="txtr" style="width: 100px">订单号：</td>		
			<td>
				&nbsp;<input value="<c:out value="${orderId}"/>" id="orderId" name="orderId" maxlength="32"/>
			</td>
			<td class="txtr" style="width: 100px">签约状态：</td>		
			<td>
			&nbsp;
				<yk:constantConvert htmlTag="select" dataTag="" data="${statusData}" paramValue="" emptyTip=" " codeTag="value" nameTag="name" name="status"/>
				<%-- &nbsp;<input value="<c:out value="${status}"/>" id="status" name="status" maxlength="10"/> --%>
			</td>
			<%-- <td class="txtr" style="width: 100px">签约申请请求流水号：</td>		
			<td>
				&nbsp;<input value="<c:out value="${reqId}"/>" id="reqId" name="reqId" maxlength="64"/>
			</td> --%>
			<%-- <td class="txtr" style="width: 100px">交易码：</td>		
			<td>
				&nbsp;<input value="<c:out value="${txnCode}"/>" id="txnCode" name="txnCode" maxlength="10"/>
			</td>
			<td class="txtr" style="width: 100px">交易名称：</td>		
			<td>
				&nbsp;<input value="<c:out value="${txnName}"/>" id="txnName" name="txnName" maxlength="10"/>
			</td> --%>
		</tr>	
		<tr>	
			
			<td class="txtr" style="width: 100px">渠道编号：</td>		
			<td>
			&nbsp;
				<yk:constantConvert htmlTag="select" dataTag="" data="${asileData}" paramValue="" emptyTip=" " codeTag="asileCode" nameTag="asileName" name="channelNo"/>
				<%-- &nbsp;<input value="<c:out value="${channelNo}"/>" id="channelNo" name="channelNo" maxlength="10"/> --%>
			</td>
			<%--
			<td class="txtr" style="width: 100px">版本号：</td>		
			 <td>
				&nbsp;<input value="<c:out value="${version}"/>" id="version" name="version" maxlength="10"/>
			</td> --%>
			<td class="txtr" style="width: 100px">用户编号：</td>		
			<td>
				&nbsp;<input value="<c:out value="${userId}"/>" id="userId" name="userId" maxlength="50"/>
			</td>
			<td class="txtr" style="width: 100px">姓名：</td>		
			<td>
				&nbsp;<input value="<c:out value="${userName}"/>" id="userName" name="userName" maxlength="10"/>
			</td>
		</tr>	
		<tr>	
			<%-- <td class="txtr" style="width: 100px">商户号：</td>		
			<td>
				&nbsp;<input value="<c:out value="${mchId}"/>" id="mchId" name="mchId" maxlength="20"/>
			</td> 
			<td class="txtr" style="width: 100px">银行名称简写：</td>		
			 <td>
				&nbsp;<input value="<c:out value="${bankNameShort}"/>" id="bankNameShort" name="bankNameShort" maxlength="10"/>
			</td> --%>
			<td class="txtr" style="width: 100px">手机号：</td>		
			<td>
				&nbsp;<input value="<c:out value="${phone}"/>" id="phone" name="phone" maxlength="20"/>
			</td>
			<td class="txtr" style="width: 100px">银行卡号：</td>		
			<td>
				&nbsp;<input value="<c:out value="${cardNo}"/>" id="cardNo" name="cardNo" maxlength="64"/>
			</td>
			<td class="txtr" style="width: 100px">证件号：</td>		
			<td>
				&nbsp;<input value="<c:out value="${idNo}"/>" id="idNo" name="idNo" maxlength="20"/>
			</td>
			
		</tr>	
		<%-- <tr>	
			
			<td class="txtr" style="width: 100px">证件类型01-身份证 02-户口本 03-军人身份证 04-港、澳居民往来内地通行证 05-台湾居民来往大陆通行证 06-护照 07-工商营业执照 08-法人证书 09-组织机构代码证 10-其他：</td>		
			<td>
				&nbsp;<input value="<c:out value="${idType}"/>" id="idType" name="idType" maxlength="2"/>
			</td>
			
			<td class="txtr" style="width: 100px">cvv2：</td>		
			<td>
				&nbsp;<input value="<c:out value="${cvv2}"/>" id="cvv2" name="cvv2" maxlength="10"/>
			</td>
		</tr>	
		<tr>	
			<td class="txtr" style="width: 100px">有效开始日期：</td>		
			<td>
				&nbsp;<input value="<c:out value="${expireDate}"/>" id="expireDate" name="expireDate" maxlength="14"/>
			</td>
			<td class="txtr" style="width: 100px">最后更新时间：</td>		
			<td>
				&nbsp;<input onClick="WdatePicker()" value="<c:out value="${lastupdateTimeBegin}"/>" id="lastupdateTimeBegin" name="lastupdateTimeBegin" size="10"/>
				<input onClick="WdatePicker()" value="<c:out value="${lastupdateTimeEnd}"/>" id="lastupdateTimeEnd" name="lastupdateTimeEnd" size="10"/>
			</td>
			
			<td class="txtr" style="width: 100px">repCode：</td>		
			<td>
				&nbsp;<input value="<c:out value="${repCode}"/>" id="repCode" name="repCode" maxlength="10"/>
			</td>
		</tr>	
		<tr>	
			<td class="txtr" style="width: 100px">repMsg：</td>		
			<td>
				&nbsp;<input value="<c:out value="${repMsg}"/>" id="repMsg" name="repMsg" maxlength="255"/>
			</td>
			<td class="txtr" style="width: 100px">asileRepCode：</td>		
			<td>
				&nbsp;<input value="<c:out value="${asileRepCode}"/>" id="asileRepCode" name="asileRepCode" maxlength="10"/>
			</td>
			<td class="txtr" style="width: 100px">asileRepMsg：</td>		
			<td>
				&nbsp;<input value="<c:out value="${asileRepMsg}"/>" id="asileRepMsg" name="asileRepMsg" maxlength="255"/>
			</td>
			<td class="txtr" style="width: 100px">银行卡类型0借记卡1贷记卡：</td>		
			<td>
				&nbsp;<input value="<c:out value="${cardType}"/>" id="cardType" name="cardType" maxlength="2"/>
			</td>
		</tr>	
		<tr>	
			<td class="txtr" style="width: 100px">预签约信息，预签约号：</td>		
			<td>
				&nbsp;<input value="<c:out value="${signId}"/>" id="signId" name="signId" maxlength="64"/>
			</td>
			<td class="txtr" style="width: 100px">渠道返回token：</td>		
			<td>
				&nbsp;<input value="<c:out value="${token}"/>" id="token" name="token" maxlength="64"/>
			</td>
			<td class="txtr" style="width: 100px">tokenCreateTime：</td>		
			<td>
				&nbsp;<input onClick="WdatePicker()" value="<c:out value="${tokenCreateTimeBegin}"/>" id="tokenCreateTimeBegin" name="tokenCreateTimeBegin" size="10"/>
				<input onClick="WdatePicker()" value="<c:out value="${tokenCreateTimeEnd}"/>" id="tokenCreateTimeEnd" name="tokenCreateTimeEnd" size="10"/>
			</td>
		</tr>	 --%>
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
	<h2>列表</h2>
</div>
		<!-- <div class="groupbtn">
			<a href="javascript:void(0)" title="新建" class="btn001" id="btnadd">新增</a>
			<a href="javascript:void(0)" title="修改" class="btn002" id="btnedit">修改</a>
			<a href="javascript:void(0)" title="删除"  class="btn004" id="btndelete">删除</a>
		</div> -->
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb" class="table02">
			<thead>
			 <tr>
			 	<th><input id="allChoose" type="checkbox"/></th>
			 	<th>序号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>用户编号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>姓名<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>手机号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>银行名称<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>鉴权单号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>绑卡渠道<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>状态<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>处理描述<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>创建时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				
				<!-- <th>签约申请请求流水号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>交易码<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>交易名称<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>版本号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>商户号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>银行卡号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>证件号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>证件类型01-身份证 02-户口本 03-军人身份证 04-港、澳居民往来内地通行证 05-台湾居民来往大陆通行证 06-护照 07-工商营业执照 08-法人证书 09-组织机构代码证 10-其他<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>cvv2<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>有效开始日期<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>最后更新时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>repCode<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>asileRepCode<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>asileRepMsg<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>银行卡类型0借记卡1贷记卡<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>预签约信息，预签约号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>渠道返回token<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>tokenCreateTime<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
			    <th>操作<div style="width: 75px; height: 1px; overflow: hidden;"></div></th> -->
	        </tr>
	        </thead>
			<c:forEach var="entry" items="${page.result}" varStatus="v">
			<tr>
				<td><input name="ids" type="checkbox" value="${entry.id}"/>
				<input name="pkName" type="hidden" id="pkName" value="id"/>
				</td>
				<td>${v.index + 1 }</td>
				<td>
				<div><c:out value="${entry.userId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.userName}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.phone}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.bankNameShort}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.orderId}"/></div>
				</td>
				<td>
				<div>
				<yk:constantConvert htmlTag="text" dataTag="" data="${asileData}" paramValue="${entry.channelNo}" emptyTip=" " codeTag="asileCode" nameTag="asileName"/>
				<%-- <c:out value="${entry.channelNo}"/> --%>
				</div>
				</td>
				<td>
				<div>
				<yk:constantConvert htmlTag="text" dataTag="" data="${statusData}" paramValue="${entry.status}" emptyTip=" " codeTag="value" nameTag="name"/>
				<%-- <c:out value="${entry.status}"/> --%>
				</div>
				</td>
				<td>
				<div><c:out value="${entry.repMsg}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.createTimeString}"/></div>
				</td>
				<%-- <td>
				<div><c:out value="${entry.reqId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.txnCode}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.txnName}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.version}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.mchId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.cardNo}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.idNo}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.idType}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.cvv2}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.expireDate}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.lastupdateTimeString}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.repCode}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.asileRepCode}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.asileRepMsg}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.cardType}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.signId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.token}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.tokenCreateTimeString}"/></div>
				</td>
				<td>
						<a href="#" onclick="show('${entry.id}')">详情</a>&nbsp;
						<a href="#" onclick="edit('${entry.id}')">修改</a>&nbsp;
						<a href="#" onclick="del('${entry.id}')">删除</a>&nbsp;
				</td> --%>
			</tr>
			</c:forEach>
			</table>
     	 <yk:page url="${yk}/trade/AuthOrder/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">
function show(id){
	window.open('${yk}/trade/AuthOrder/show.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}
function edit(id){
	window.open('${yk}/trade/AuthOrder/edit.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}

$(function(){
	
	$('#btnedit').click(function(){
		var $id = $('[name=ids]:checkbox:checked');
		
		if($id.length==0){
			alert("请选择要修改的信息！");
			return false;
		}
		window.open('${yk}/trade/AuthOrder/edit.do?'+$('#pkName').val()+'='+$id.val(),'','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btnadd').click(function(){
		window.open('${yk}/trade/AuthOrder/create.do','','height=600,width=600,resizable=yes,location=no,scrollbars');
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
			$('#orderId').val('');
			$('#reqId').val('');
			$('#txnCode').val('');
			$('#txnName').val('');
			$('#status').val('');
			$('#channelNo').val('');
			$('#version').val('');
			$('#userId').val('');
			$('#mchId').val('');
			$('#bankNameShort').val('');
			$('#cardNo').val('');
			$('#userName').val('');
			$('#idNo').val('');
			$('#idType').val('');
			$('#phone').val('');
			$('#cvv2').val('');
			$('#expireDate').val('');
			$('#lastupdateTimeBegin').val('');
			$('#lastupdateTimeEnd').val('');
			$('#createTimeBegin').val('');
			$('#createTimeEnd').val('');
			$('#repCode').val('');
			$('#repMsg').val('');
			$('#asileRepCode').val('');
			$('#asileRepMsg').val('');
			$('#cardType').val('');
			$('#signId').val('');
			$('#token').val('');
			$('#tokenCreateTimeBegin').val('');
			$('#tokenCreateTimeEnd').val('');
	});
});

function del(id){
	var ids = [];
	ids.push(id);
	v_deleteItems(ids);
}

function v_deleteItems(ids){
	if(ids.length>0 && confirm("确定要删除吗？")){
		$.post( "${yk}/trade/AuthOrder/delete.do", {"items":ids.join(',')}, 
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


