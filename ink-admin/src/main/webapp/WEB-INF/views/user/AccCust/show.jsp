<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>客户信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<div class="title01">
	<h2>客户详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">商户编号 :</td>	
			<td><c:out value="${model.mchId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">第三方客户号:</td>	
			<td><c:out value="${model.custId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">客户类型:</td>	
			<td><c:out value="${model.custType}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">证件类型 :</td>	
			<td><c:out value="${model.idType}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">证件号码:</td>	
			<td><c:out value="${model.idNo}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">主账号:</td>	
			<td><c:out value="${model.pacId}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">姓名:</td>	
			<td><c:out value="${model.custName}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">性别:</td>	
			<td>
			<c:choose>
			<c:when test="W">女性</c:when>
			<c:when test="M">男性</c:when>
			<c:otherwise>保密</c:otherwise>
			</c:choose>
			
			
			<c:out value="${model.sex}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">出生日期:</td>	
			<td><c:out value="${model.birthdayString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">联系移动电话:</td>	
			<td><c:out value="${model.mblNo}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">联系固定电话:</td>	
			<td><c:out value="${model.telNo}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">联系邮箱:</td>	
			<td><c:out value="${model.email}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">邮编:</td>	
			<td><c:out value="${model.zipcode}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">联系地址:</td>	
			<td><c:out value="${model.address}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">状态 :</td>	
			<td>
				<c:choose>
					<c:when test="${model.status == 1 }">启用</c:when>
					<c:when test="${model.status == 2 }">停用</c:when>
					<c:when test="${model.status == 9 }">注销</c:when>
					<c:otherwise>未识别</c:otherwise>
				</c:choose>			
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">删除标识:</td>	
			<td>
				<c:choose>
					<c:when test="${model.delFlag == 0}">正常</c:when>
					<c:when test="${model.delFlag == 1}">删除</c:when>
					<c:otherwise>未识别</c:otherwise>
				</c:choose>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">版本:</td>	
			<td><c:out value="${model.version}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">客户号:</td>	
			<td><c:out value="${model.uid}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">创建时间:</td>	
			<td><c:out value="${model.createTimeString}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">最后修改时间:</td>	
			<td><c:out value="${model.lastUpdateTimeString}"/></td>
		</tr>
	</table>
	</body>
</html>