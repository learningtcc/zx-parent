
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
	<h2>详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">订单号:</td>	
			<td><c:out value="${model.orderId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">签约申请请求流水号:</td>	
			<td><c:out value="${model.reqId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">交易码:</td>	
			<td><c:out value="${model.txnCode}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">交易名称:</td>	
			<td><c:out value="${model.txnName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">签约状态:</td>	
			<td><c:out value="${model.status}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">渠道编号:</td>	
			<td><c:out value="${model.channelNo}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">版本号:</td>	
			<td><c:out value="${model.version}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">用户id:</td>	
			<td><c:out value="${model.userId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">商户号:</td>	
			<td><c:out value="${model.mchId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">银行名称简写:</td>	
			<td><c:out value="${model.bankNameShort}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">银行卡号:</td>	
			<td><c:out value="${model.cardNo}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">姓名:</td>	
			<td><c:out value="${model.userName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">证件号:</td>	
			<td><c:out value="${model.idNo}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">证件类型01-身份证 02-户口本 03-军人身份证 04-港、澳居民往来内地通行证 05-台湾居民来往大陆通行证 06-护照 07-工商营业执照 08-法人证书 09-组织机构代码证 10-其他:</td>	
			<td><c:out value="${model.idType}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">手机号:</td>	
			<td><c:out value="${model.phone}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">cvv2:</td>	
			<td><c:out value="${model.cvv2}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">有效开始日期:</td>	
			<td><c:out value="${model.expireDate}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">最后更新时间:</td>	
			<td><c:out value="${model.lastupdateTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">创建时间:</td>	
			<td><c:out value="${model.createTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">repCode:</td>	
			<td><c:out value="${model.repCode}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">repMsg:</td>	
			<td><c:out value="${model.repMsg}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">asileRepCode:</td>	
			<td><c:out value="${model.asileRepCode}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">asileRepMsg:</td>	
			<td><c:out value="${model.asileRepMsg}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">银行卡类型0借记卡1贷记卡:</td>	
			<td><c:out value="${model.cardType}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">预签约信息，预签约号:</td>	
			<td><c:out value="${model.signId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">渠道返回token:</td>	
			<td><c:out value="${model.token}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">tokenCreateTime:</td>	
			<td><c:out value="${model.tokenCreateTimeString}"/></td>
		</tr>
	</table>
	</body>
</html>