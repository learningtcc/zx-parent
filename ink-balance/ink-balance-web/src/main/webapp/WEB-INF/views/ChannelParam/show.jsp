
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
			<td class="txtr" style="width: 188px">渠道名称:</td>
			<td><c:out value="${model.name}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">渠道编号:</td>
			<td><c:out value="${model.no}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">支付渠道商户号:</td>
			<td><c:out value="${model.channelMerchantNo}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">渠道类型:</td>
			<td><c:out value="${model.type}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">日切开始时间:</td>
			<td><fmt:formatDate value="${model.cutDayStart}"
					pattern="yyyy-MM-dd HH:mm:ss" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">日切结束时间:</td>
			<td><fmt:formatDate value="${model.cutDayEnd}"
					pattern="yyyy-MM-dd HH:mm:ss" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">文件定位:</td>
			<td><c:out value="${model.fileAddress}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">文件获取方式:</td>
			<td><c:out value="${model.fileGetModel}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">驻留天数(对于未匹配的记录，继续参与对账的天数):</td>
			<td><c:out value="${model.resideDays}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">备注:</td>
			<td><c:out value="${model.remark}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">创建时间:</td>
			<td><fmt:formatDate value="${model.createDate}"
					pattern="yyyy-MM-dd HH:mm:ss" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">更新时间:</td>
			<td><fmt:formatDate value="${model.updateDate}"
					pattern="yyyy-MM-dd HH:mm:ss" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">操作员:</td>
			<td><c:out value="${model.operatorId}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">删除标志:</td>
			<td><c:out value="${model.delFlag}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">版本号:</td>
			<td><c:out value="${model.version}" /></td>
		</tr>
	</table>
</body>
</html>