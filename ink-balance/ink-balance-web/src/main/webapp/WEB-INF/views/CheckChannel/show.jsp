
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
			<td class="txtr" style="width: 188px">对账日期:</td>
			<td><fmt:formatDate value="${model.checkDate}"
					pattern="yyyy-MM-dd" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">交易日期:</td>
			<td><fmt:formatDate value="${model.tradeDate}"
					pattern="yyyy-MM-dd" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">渠道编号:</td>
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
			<td class="txtr" style="width: 188px">渠道总金额:</td>
			<td><c:out value="${model.channelAmount}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">渠道总笔数:</td>
			<td><c:out value="${model.channelCount}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">平台总金额:</td>
			<td><c:out value="${model.platformAmount}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">平台总笔数:</td>
			<td><c:out value="${model.platformCount}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">平台单边笔数:</td>
			<td><c:out value="${model.platformSideCount}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">渠道单边笔数:</td>
			<td><c:out value="${model.channelSideCount}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">对账结果状态:</td>
			<td><c:choose>
					<c:when test="${model.checkStatus==1}">
              				匹配
       					</c:when>
					<c:when test="${model.checkStatus==2}">
             			 	未匹配
      					</c:when>
					<c:otherwise>
              				数据异常
       					</c:otherwise>
				</c:choose></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">差异总笔数:</td>
			<td><c:out value="${model.differenceCount}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">渠道对账文件名称:</td>
			<td><c:out value="${model.channelCheckFile}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">文件来源:</td>
			<td><c:out value="${model.fileSource}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">处理状态:</td>
			<td><c:choose>
					<c:when test="${model.adjustStatus==0}">
            				待处理
     					</c:when>
					<c:when test="${model.adjustStatus==1}">
           			 	处理完成
    					</c:when>
					<c:otherwise>
            				状态差错
     					</c:otherwise>
				</c:choose></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">未处理差异笔数:</td>
			<td><c:out value="${model.unhandleCount}" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">处理说明:</td>
			<td><c:out value="${model.handleNotes}" /></td>
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
			<td><fmt:formatDate value="${model.createDate}"
					pattern="yyyy-MM-dd HH:mm:ss" /></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">操作员:</td>
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
	</table>
</body>
</html>