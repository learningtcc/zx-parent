
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
			<td class="txtr" style="width: 188px">差错类型:</td>
			<td><c:choose>
					<c:when test="${model.differenceType==1}">
            				渠道单边
     					</c:when>
					<c:when test="${model.differenceType==2}">
           			 		平台单边
    					</c:when>
					<c:when test="${model.differenceType==3}">
           			 		差错
    					</c:when>
					<c:otherwise>
            				状态差错
     					</c:otherwise>
				</c:choose></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">差错来源:</td>
			<td><c:choose>
					<c:when test="${model.differenceSource==1}">
            				渠道
     					</c:when>
					<c:when test="${model.differenceSource==2}">
           			 		平台
    					</c:when>
					<c:otherwise>
            				状态差错
     					</c:otherwise>
				</c:choose></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">对账总表主键:</td>
			<td><c:out value="${model.refMainrecordId}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">流水号:</td>
			<td><c:out value="${model.seqNo}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">平台订单号:</td>
			<td><c:out value="${model.platformOrderNo}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">金额:</td>
			<td><c:out value="${model.amount}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">交易状态:</td>
			<td><c:choose>
					<c:when test="${model.status==1}">
            				待支付
     					</c:when>
					<c:when test="${model.status==2}">
           			 		支付成功
    					</c:when>
					<c:when test="${model.status==3}">
           			 		支付失败
    					</c:when>
					<c:otherwise>
            				状态差错
     					</c:otherwise>
				</c:choose></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">日期:</td>
			<td><fmt:formatDate value="${model.date}" pattern="yyyy-MM-dd" />
			</td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">处理状态:</td>
			<td><c:choose>
					<c:when test="${model.handleStatus==0}">
           			 		待处理
    					</c:when>
					<c:when test="${model.handleStatus==1}">
            				处理完成
     					</c:when>
					<c:when test="${model.handleStatus==2}">
           			 		挂起
    					</c:when>
					<c:otherwise>
            				状态差错
     					</c:otherwise>
				</c:choose></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">处理说明:</td>
			<td><c:out value="${model.handleNotes}" /></td>
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
			<td class="txtr" style="width: 188px">操作者ID:</td>
			<td><c:out value="${model.operatorId}" /></td>
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