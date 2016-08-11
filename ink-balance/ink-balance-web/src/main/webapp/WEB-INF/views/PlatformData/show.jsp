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
			<td class="txtr" style="width: 188px">支付金额:</td>
			<td><c:out value="${model.amt}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">到账金额:</td>
			<td><c:out value="${model.arrivedAmt}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">支付渠道:</td>
			<td><c:choose>
					<c:when test="${'1' eq model.channelNo}">
	           		      快钱
	                 </c:when>
					<c:when test="${'2' eq model.channelNo}">
	          		       快翼支付
	                 </c:when>
					<c:when test="${'10002' eq model.channelNo}">
	         		        民生
	                 </c:when>
					<c:otherwise>
						<c:out value="${model.channelNo}" />
					</c:otherwise>
				</c:choose></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">支付渠道商户号:</td>
			<td><c:choose>
					<c:when test="${'CF2000027305' eq model.channelMerchantNo}">
          		      民生-简理财
                </c:when>
					<c:otherwise>
						<c:out value="${model.channelMerchantNo}" />
					</c:otherwise>
				</c:choose></td>
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
			<td class="txtr" style="width: 188px">支付生成时间:</td>
			<td><fmt:formatDate value="${model.payTime}"
					pattern="yyyy-MM-dd HH:mm:ss" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">到账时间:</td>
			<td><fmt:formatDate value="${model.arrivedTime}"
					pattern="yyyy-MM-dd HH:mm:ss" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">交易状态 :</td>
			<td><c:choose>
					<c:when test="${model.payStatus==1}">
            				待支付
     					</c:when>
					<c:when test="${model.payStatus==2}">
           			 		支付成功
    					</c:when>
					<c:when test="${model.payStatus==3}">
           			 		支付失败
    					</c:when>
					<c:otherwise>
            				状态差错
     					</c:otherwise>
				</c:choose></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">支付方式:</td>
			<td><c:out value="${model.payMethod}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">对账状态:</td>
			<td><c:choose>
					<c:when test="${model.checkStatus==0}">
           			 		初始
    					</c:when>
					<c:when test="${model.checkStatus==1}">
            				未匹配
     					</c:when>
					<c:when test="${model.checkStatus==2}">
           			 		已匹配
    					</c:when>
					<c:when test="${model.checkStatus==3}">
           			 		差错
    					</c:when>
					<c:otherwise>
            				状态差错
     					</c:otherwise>
				</c:choose></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">驻留标识:</td>
			<td><c:choose>
					<c:when test="${model.resideFlag==0}">
           			 		非驻留
    					</c:when>
					<c:when test="${model.resideFlag==1}">
            				驻留
     					</c:when>
					<c:otherwise>
            				状态差错
     					</c:otherwise>
				</c:choose></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">驻留截止日:</td>
			<td><fmt:formatDate value="${model.resideToDate}"
					pattern="yyyy-MM-dd" /></td>
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
		<%--<tr>
			<td class="txtr" style="width: 188px">删除标志:</td>	
			<td><c:out value="${model.delFlag}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">版本号:</td>	
			<td><c:out value="${model.version}"/></td>
		</tr>--%>
		<tr>
			<td class="txtr" style="width: 188px">备注:</td>
			<td><c:out value="${model.remark}" /></td>
		</tr>
	</table>
</body>
</html>