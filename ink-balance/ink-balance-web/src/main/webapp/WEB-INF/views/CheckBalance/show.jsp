
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>信息</title>
<%@ include file="/gen-commons/taglibs.jsp"%>
</head>
<body>
	<div class="title01">
		<h2>详情</h2>
	</div>
	<table cellpadding="0" cellspacing="0" class="table00"
		style="width: 100%">
		<tr>
			<td class="txtr" style="width: 188px">调账对象:</td>
			<td><c:choose>
					<c:when test="${model.balanceSource==1}">
					渠道
				</c:when>
					<c:when test="${model.balanceSource==2}">
					平台
				</c:when>
					<c:otherwise>
					状态错误
				</c:otherwise>
				</c:choose></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">调账方向:</td>
			<td><c:choose>
					<c:when test="${model.balanceDirection==1}">
					正
				</c:when>
					<c:when test="${model.balanceDirection==2}">
					负
				</c:when>
					<c:otherwise>
					状态错误
				</c:otherwise>
				</c:choose></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">调账金额:</td>
			<td><c:out value="${model.balanceAmt}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">渠道编号:</td>
			<td><c:forEach var="entry" items="${channelList}" varStatus="v">
					<c:if test="${model.channelNo eq entry.no}">
						<c:out value="${entry.name}" />
					</c:if>
				</c:forEach></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">平台订单号:</td>
			<td><c:out value="${model.platformOrderNo}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">交易流水号:</td>
			<td><c:out value="${model.transNo}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">调账状态:</td>
			<td><c:choose>
					<c:when test="${model.balanceStatus==1}">
					调账成功
				</c:when>
					<c:when test="${model.balanceStatus==2}">
					调账失败
				</c:when>
					<c:otherwise>
					状态错误
				</c:otherwise>
				</c:choose></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">创建时间:</td>
			<td><fmt:formatDate value="${model.createTime}"
					pattern="yyyy-MM-dd HH:mm:ss" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">更新时间:</td>
			<td><fmt:formatDate value="${model.updateTime}"
					pattern="yyyy-MM-dd HH:mm:ss" /></td>
		</tr>
		<%--<tr>
			<td class="txtr" style="width: 188px">删除标志:</td>	
			<td><c:out value="${model.delFlag}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">版本号:</td>	
			<td><c:out value="${model.version}"/></td>
		</tr>--%>
		<tr>
			<td class="txtr" style="width: 188px">调账说明:</td>
			<td><c:out value="${model.balanceNote}" /></td>
		</tr>
	</table>
</body>
</html>