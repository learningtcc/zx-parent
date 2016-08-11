
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
			<td class="txtr" style="width: 188px">通道名称:</td>	
			<td><c:out value="${model.asileName}"/></td>
		</tr>
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">通道简码:</td>	 -->
<%-- 			<td><c:out value="${model.asileCode}"/></td> --%>
<!-- 		</tr> -->
		<tr>	
			<td class="txtr" style="width: 188px">银行名称:</td>	
			<td><c:out value="${model.bankName}"/></td>
		</tr>
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">银行简码:</td>	 -->
<%-- 			<td><c:out value="${model.bankCode}"/></td> --%>
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
			<td><c:out value="${model.asilePayType}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">单笔交易金额下限:</td>	
			<td><c:out value="${model.asileAmtStart}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">单笔交易金额上限:</td>	
			<td><c:out value="${model.asileAmtEnd}"/></td>
		</tr>
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">单卡单月限额:</td>	 -->
<%-- 			<td><c:out value="${model.cardCrashMonthLimit}"/></td> --%>
<!-- 		</tr> -->
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">单卡单日限额:</td>	 -->
<%-- 			<td><c:out value="${model.cardDayLimits}"/></td> --%>
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
			<td class="txtr" style="width: 188px">修改时间:</td>	
			<td><c:out value="${model.updateTimeString}"/></td>
		</tr>
<!-- 		 <tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">updaterId:</td>	 -->
<%-- 			<td><c:out value="${model.updaterId}"/></td> --%>
<!-- 		</tr>  -->
		<tr>	
			<td class="txtr" style="width: 188px">修改人:</td>	
			<td><c:out value="${model.updaterName}"/></td>
		</tr>
		<%-- <tr>	
			<td class="txtr" style="width: 188px">是否删除:</td>	
			<td><c:out value="${model.isDelete}"/></td>
		</tr> --%>
		<tr>	
			<td class="txtr" style="width: 188px">通道服务开始时间:</td>	
			<td><c:out value="${model.asileServiceBeginTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">通道服务结束时间:</td>	
			<td><c:out value="${model.asileServiceEndTimeString}"/></td>
		</tr>
<!-- 		<tr>	 -->
<!-- 			<td class="txtr" style="width: 188px">优先级:</td>	 -->
<%-- 			<td><c:out value="${model.priority}"/></td> --%>
<!-- 		</tr> -->
	</table>
	</body>
</html>