
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">通道名称:</td>	
			<td><c:out value="${model.asileName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">通道id:</td>	
			<td><c:out value="${model.asileCode}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">银行名称:</td>	
			<td><c:out value="${model.bankName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">银行id:</td>	
			<td><c:out value="${model.bankCode}"/></td>
		</tr>
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">通道产品编码:</td>	 -->
<%-- 			<td><c:out value="${model.asileProductCode}"/></td> --%>
<!-- 		</tr> -->
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">通道产品名称:</td>	 -->
<%-- 			<td><c:out value="${model.asileProductName}"/></td> --%>
<!-- 		</tr> -->
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">是否直连:</td>	 -->
<%-- 			<td><c:out value="${model.asileDirectBank}"/></td> --%>
<!-- 		</tr> -->
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">对公对私:</td>	 -->
<%-- 			<td><c:out value="${model.asilePublicPrivate}"/></td> --%>
<!-- 		</tr> -->
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">提供接口方式:</td>	 -->
<%-- 			<td><c:out value="${model.asileIntefaceType}"/></td> --%>
<!-- 		</tr> -->
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">鉴权方式:</td>	 -->
<%-- 			<td><c:out value="${model.asileAuthType}"/></td> --%>
<!-- 		</tr> -->
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">鉴权要素(转换为二进制位数表示):</td>	 -->
<%-- 			<td><c:out value="${model.asileAuthElements}"/></td> --%>
<!-- 		</tr> -->
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">授权方式:</td>	 -->
<%-- 			<td><c:out value="${model.asileAuthMode}"/></td> --%>
<!-- 		</tr> -->
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">支持银行卡类型:</td>	 -->
<%-- 			<td><c:out value="${model.asileBankType}"/></td> --%>
<!-- 		</tr> -->
		<tr>	
			<td class="txtr" style="width: 188px">支付类型:</td>	
			<td>
			<yk:constantConvert htmlTag="text" dataTag="asilePayType" codeTag="code" paramValue="${model.asilePayType}" nameTag="value" data="${routeBusinessType}" name="asilePayType" isAutoItem="false" emptyTip="0"/>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">单笔限额:</td>	
			<td><c:out value="${model.asileCrashLimit}"/></td>
		</tr>
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">单卡单日限额:</td>	 -->
<%-- 			<td><c:out value="${model.cardCrashDayLimit}"/></td> --%>
<!-- 		</tr> -->
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">单卡单月限额:</td>	 -->
<%-- 			<td><c:out value="${model.cardCrashMonthLimit}"/></td> --%>
<!-- 		</tr> -->
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">单卡单日限额:</td>	 -->
<%-- 			<td><c:out value="${model.cardDayLimits}"/></td> --%>
<!-- 		</tr> -->
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">批量限额:</td>	 -->
<%-- 			<td><c:out value="${model.batchCrashLimit}"/></td> --%>
<!-- 		</tr> -->
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">批量限次:</td>	 -->
<%-- 			<td><c:out value="${model.batchLimit}"/></td> --%>
<!-- 		</tr> -->
		<tr>	
			<td class="txtr" style="width: 188px">备注:</td>	
			<td><c:out value="${model.remark}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">创建时间:</td>	
			<td><c:out value="${model.createTimeString}"/></td>
		</tr>
		<%-- <tr>	
			<td class="txtr" style="width: 188px">createrId:</td>	
			<td><c:out value="${model.createrId}"/></td>
		</tr> --%>
		<tr>	
			<td class="txtr" style="width: 188px">创建人:</td>	
			<td><c:out value="${model.createrName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">updateTime:</td>	
			<td><c:out value="${model.updateTimeString}"/></td>
		</tr>
		<%-- <tr>	
			<td class="txtr" style="width: 188px">updaterId:</td>	
			<td><c:out value="${model.updaterId}"/></td>
		</tr> --%>
		<tr>	
			<td class="txtr" style="width: 188px">updaterName:</td>	
			<td><c:out value="${model.updaterName}"/></td>
		</tr>
		<%-- <tr>	
			<td class="txtr" style="width: 188px">是否删除:</td>	
			<td><c:out value="${model.isDelete}"/></td>
		</tr> --%>
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">回盘时间:</td>	 -->
<%-- 			<td><c:out value="${model.asileOnlyTimeString}"/></td> --%>
<!-- 		</tr> -->
		<tr>	
			<td class="txtr" style="width: 188px">通道服务开始时间:</td>	
			<td><c:out value="${model.asileServiceBeginTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">通道服务结束时间:</td>	
			<td><c:out value="${model.asileServiceEndTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">优先级:</td>	
			<td><c:out value="${model.priority}"/></td>
		</tr>
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">0是分离，1是不分:</td>	 -->
<%-- 			<td><c:out value="${model.borrowSplit}"/></td> --%>
<!-- 		</tr> -->
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">短信发送方:</td>	 -->
<%-- 			<td><c:out value="${model.smsSender}"/></td> --%>
<!-- 		</tr> -->
		<tr>	
			<td class="txtr" style="width: 188px">银行简码:</td>	
			<td><c:out value="${model.bankShort}"/></td>
		</tr>
	</table>
	</body>
</html>