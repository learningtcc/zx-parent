
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title> 体验金发放日志</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01 title01_x">
	<h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/user/GoldGrantHistory/list.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
		<tr>	
			<td class="txtr" style="width: 100px">商户ID：</td>		
			<td>
				<input value="<c:out value="${filter.mchId}"/>" id="mchId" name="mchId" maxlength="19"/>
			</td>
			<td class="txtr" style="width: 100px">客户ID：</td>		
			<td>
				&nbsp;<input value="<c:out value="${filter.custId}"/>" id="custId" name="custId" maxlength="20"/>
			</td>
		</tr>	
		<tr>
			<td class="txtr" style="width: 100px">主账户ID：</td>		
			<td>
				&nbsp;<input value="<c:out value="${filter.pacId}"/>" id="pacId" name="pacId" maxlength="20"/>
			</td>
			<td class="txtr" style="width: 100px">子账户种类：</td>		
			<td>
				&nbsp;<input value="<c:out value="${filter.sacType}"/>" id="sacType" name="sacType" maxlength="20"/>
			</td>
			<td class="txtr" style="width: 100px">状态 ：</td>		
			<td>
				<select id="state" name="state">
					<option value="">不限</option>
					<option value="0" <c:if test="${filter.state eq '0' }">selected="selected"</c:if>>成功</option>
					<option value="1" <c:if test="${filter.state eq '1' }">selected="selected"</c:if>>失败</option>
				</select>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 100px">创建时间：</td>		
			<td>
				<input onClick="WdatePicker(WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'}))" value="<c:out value="${filter.beginCreateTime}"/>" id="beginCreateTime" name="beginCreateTime" size="10" readonly/>
				&nbsp;---&nbsp;<input onClick="WdatePicker(WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'}))" value="<c:out value="${filter.endCreateTime}"/>" id="endCreateTime" name="endCreateTime" size="10" readonly/>
			</td>
		</tr>	
	</table>
	<br/>
	<div align="center">
		<input type="submit" class="btn01" value="精确查询" onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('0');"/>
		<input type="button" class="btn01" value="清 空" id="btnclear"/>
	</div>
</form>
	<br></br>
	<div class="title01">
	<h2>体验金历史日志日志列表</h2>
</div>
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb" class="table02">
			<thead>
			 <tr>
			 	<th><input id="allChoose" type="checkbox"/></th>
			 	<th>序号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>交易代码<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>商户ID<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>客户ID<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>主帐户ID<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>子帐户种类<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>当前余额<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>回收金额<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>昨日余额<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>状态<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>返回码<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>返回描述<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>创建时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
	        </tr>
	        </thead>
			<c:forEach var="entry" items="${page.result}" varStatus="v">
			<tr>
				<td><input name="ids" type="checkbox" value=""/>
				<input name="pkName" type="hidden" id="pkName" value="id"/>
				</td>
				<td>${v.index + 1 + (page.pageNumber-1) * page.pageSize }</td>
				<td>
				<div><c:out value="${entry.txnCode}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.mchId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.custId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.pacId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.sacType}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.curAmt}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.recoveryAmt}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.lasAmt}"/></div>
				</td>
				<td>
				<div>
					<c:choose>
						<c:when test="${entry.state == 0 }">成功</c:when>
						<c:when test="${entry.state == 1 }">失败</c:when>
						<c:otherwise>未识别</c:otherwise>
					</c:choose>
				</div>
				</td>
				<td>
				<div><c:out value="${entry.retCode}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.retMsg}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.createTime}"/></div>
				</td>
			</tr>
			</c:forEach>
			
			</table>
     	 <yk:page url="${yk}/GoldGrantHistory/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">
	$('#btnclear').click(function(){
		$('#mchId').val('');
		$('#custId').val('');
		$('#pacId').val('');
		$('#sacType').val('');
		$('#state').val('');
		$('#beginCreateTime').val('');
		$('#endCreateTime').val('');
	});
</script>
</body>
</html>


