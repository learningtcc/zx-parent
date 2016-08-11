<%@page import="com.ink.trade.api.platform.trade.model.base.PayOrderEntity"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	 <title>支付订单详情</title> 
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<br></br>
	<div class="title01">
	 <h2>支付订单列表</h2>
</div>
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb" class="table02">
			<thead>
			 <tr>
			 	<th><input id="allChoose" type="checkbox"/></th>
			 	<th>序号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<!-- <th>交易请求流水号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
				<th>交易订单号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>渠道名称<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<!-- <th>渠道编号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
				<th>订单金额<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>支付状态<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>创建时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
			<!-- 	<th>版本号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
				<th>备注<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>请求时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>响应时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<!-- <th>最后修改时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>商户号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
				<th>订单日期<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>通道响应码<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>通道响应信息<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>响应码<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>响应信息<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>支付订单号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<!-- <th>支付流水号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
				<th>支付方式<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
			   <!--  <th>操作<div style="width: 75px; height: 1px; overflow: hidden;"></div></th> -->
	        </tr>
	        </thead>
			<c:forEach var="entry" items="${page.result}" varStatus="v">
			<tr>
				<td><input name="ids" type="checkbox" value="${entry.id}"/>
				<input name="pkName" type="hidden" id="pkName" value="id"/>
				</td>
				<td>${v.index + 1 }</td>
				<%-- <td>
				<div><c:out value="${entry.reqId}"/></div>
				</td> --%>
				<td>
				<div><c:out value="${entry.ordId}"/></div>
				</td>
				<td>
				<yk:constantConvert htmlTag="text" dataTag="asileName" codeTag="asileCode" paramValue="${entry.chanelNo}" nameTag="asileName" data="${asileInfos}" name="channelNo" isAutoItem="false" emptyTip="0"/>
				</td>
				<%-- <td>
				<div><c:out value="${entry.chanelNo}"/></div>
				</td> --%>
				<td>
				<div><c:out value="${entry.amt}"/></div>
				</td>
				<td>
				<yk:constantConvert htmlTag="text" dataTag="tradeOrderStatus" codeTag="value" paramValue="${entry.status}" nameTag="name" data="${tradeOrderStatus}" name="status" isAutoItem="false" emptyTip="0"/>
				</td>
				<td>
				<div><c:out value="${entry.createTimeString}"/></div>
				</td>
				<%-- <td>
				<div><c:out value="${entry.version}"/></div>
				</td> --%>
				<td>
				<div><c:out value="${entry.remark}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.reqTimeString}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.repTimeString}"/></div>
				</td>
				<%-- <td>
				<div><c:out value="${entry.lastupdateTimeString}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.mchId}"/></div>
				</td> --%>
				<td>
				<div><c:out value="${entry.orderDateString}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.asileRepCode}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.asileRepMsg}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.repCode}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.repMsg}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.payOrderId}"/></div>
				</td>
				<%-- <td>
				<div><c:out value="${entry.payReqId}"/></div>
				</td> --%>
				<td>
				 <yk:constantConvert htmlTag="text" dataTag="payType" codeTag="value" paramValue="${entry.payType}" nameTag="name" data="${payType}" name="payType" isAutoItem="false" emptyTip="0"/> 
				</td>
				<%-- <td>
						<a href="#" onclick="show('${entry.id}')">详情</a>&nbsp;
						<a href="#" onclick="edit('${entry.id}')">修改</a>&nbsp;
						<a href="#" onclick="del('${entry.id}')">删除</a>&nbsp;
				</td> --%>
			</tr>
			</c:forEach>
			</table>
     	 <yk:page url="${yk}/Pay/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>
</body>
</html>


