<%@ page import="com.ink.cert.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>加解密日志</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
	<h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/cert/certLog/list.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
		<tr>	
			<td class="txtr" style="width: 100px">商户号：</td>		
			<td><input value="<c:out value="${merchantCode}"/>" id="merchantCode" name="merchantCode" maxlength="20"/>
			</td>
			<td class="txtr" style="width: 100px">证书号：</td>		
			<td><input value="<c:out value="${certCode}"/>" id="certCode" name="certCode" maxlength="50"/>
			</td>
			<td class="txtr" style="width: 100px">证书名称：</td>
			<td><input value="<c:out value="${certName}"/>" id="certName" name="certName" maxlength="50"/>
			</td>
			<td class="txtr" style="width: 100px">证书类型：</td>
			<td><yk:constantConvert htmlTag="select" dataTag="certType" paramValue="${certType}" name="certType" sysTag="cert"/>
			</td>
		</tr>	
		<tr>	
			<td class="txtr" style="width: 100px">算法类型：</td>
			<td> <yk:constantConvert htmlTag="select" dataTag="arithmeticType" paramValue="${arithmeticType}" name="arithmeticType" sysTag="cert"/>
			</td>
			<td class="txtr" style="width: 100px">操作类型：</td>		
			<td> <yk:constantConvert htmlTag="select" dataTag="operateType" paramValue="${operateType}" name="operateType" sysTag="cert"/>
			</td>
			<td class="txtr" style="width: 100px">执行结果：</td>
			<td> <yk:constantConvert htmlTag="select" dataTag="resultCode" paramValue="${resultCode}" name="resultCode" sysTag="cert"/>
			</td>
			<td class="txtr" style="width: 100px">请求流水：</td>
			<td><input value="<c:out value="${requestId}"/>" id="requestId" name="requestId" maxlength="100" sysTag="monitor"/>
			</td>
			<td class="txtr" style="width: 100px"></td>
			<td>
			</td>
		</tr>	
	</table>
	<br/>
	<div align="center">
		<input type="submit" class="btn01" value="查 询" onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('1');"/>
		<input type="button" class="btn01" value="清 空" id="btnclear"/>
	</div>
</form>
	<br/>
	<div class="title01">
	<h2>加解密日志列表</h2>
</div>
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb" class="table02">
			<thead>
			 <tr>
			 	<th><input id="allChoose" type="checkbox"/></th>
			 	<th>序号<div style="width: 35px; height: 1px; overflow: hidden;"></div></th>
				<th>商户号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>证书号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>证书类型<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>证书名称<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>算法类型<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>操作类型<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				 <th>执行结果<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>原始消息<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>响应消息<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>请求流水<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>操作时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
			    <th>操作<div style="width: 75px; height: 1px; overflow: hidden;"></div></th>
	        </tr>
	        </thead>
			<c:forEach var="entry" items="${page.result}" varStatus="v">
			<tr>
				<td><input name="ids" type="checkbox" value="${entry.id}"/>
				<input name="pkName" type="hidden" id="pkName" value="id"/>
				</td>
				<td>${v.index + 1 }</td>
				<td>
				<div><c:out value="${entry.merchantCode}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.certCode}"/></div>
				</td>
				<td>
				<div>
					<yk:constantConvert htmlTag="text" dataTag="certType" paramValue="${entry.certType}" emptyTip="" sysTag="cert"/>
				</div>
				</td>
				<td>
				<div><c:out value="${entry.certName}"/></div>
				</td>
				<td>
				<div>
					<yk:constantConvert htmlTag="text" dataTag="arithmeticType" paramValue="${entry.arithmeticType}" emptyTip="" sysTag="cert"/>
				</div>
				</td>
				<td>
				<div>
					<yk:constantConvert htmlTag="text" dataTag="operateType" paramValue="${entry.operateType}" emptyTip="" sysTag="cert"/>
				</div>
				</td>
				<td>
					<div>
						<yk:constantConvert htmlTag="text" dataTag="resultCode" paramValue="${entry.resultCode}" emptyTip="" sysTag="cert"/>
					</div>
				</td>
				<td>
					<div class="showMsg1" data = "1" style="width:150px;white-space:nowrap; text-overflow:ellipsis; -o-text-overflow:ellipsis; overflow: hidden;" onclick="showLogMsg(this)"   ><c:out value="${entry.oldMsg}"/></div>
					<div class="showFullMsg1" data="1" style="display: none" onmouseout="hideLogMsg(this)"><c:out value="${entry.oldMsg}"/></div>
				<%--<div><c:out value="${entry.oldMsg}"/></div>--%>
				</td>
				<td>
					<div class="showMsg2" data = "2" style="width:150px;white-space:nowrap; text-overflow:ellipsis; -o-text-overflow:ellipsis; overflow: hidden;" onclick="showLogMsg(this)"   ><c:out value="${entry.newMsg}"/></div>
					<div class="showFullMsg2" data="2" style="display: none" onmouseout="hideLogMsg(this)"><c:out value="${entry.newMsg}"/></div>

				<%--<div><c:out value="${entry.newMsg}"/></div>--%>
				</td>
				<td>
				<div><c:out value="${entry.requestId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.operateTimeString}"/></div>
				</td>
				<td>
						<a href="#" onclick="show('${entry.id}')">详情</a>&nbsp;
				</td>
			</tr>
			</c:forEach>
			</table>
     	 <yk:page url="${yk}/cert/certLog/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">
function show(id){
	window.open('${yk}/cert/certLog/show.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}

$(function(){

	$('#btnclear').click(function(){
			$('#merchantCode').val('');
			$('#certCode').val('');
			$('#certType').val('');
			$('#certName').val('');
			$('#arithmeticType').val('');
			$('#operateType').val('');
			$('#requestId').val('');
	});
});

function showLogMsg(obj){
	var dataFlag = $(obj).attr("data");
	$(obj).hide();
	$(obj).next(".showFullMsg"+dataFlag).show();
}
function hideLogMsg(obj){
	var dataFlag = $(obj).attr("data");
	$(obj).prev(".showMsg"+dataFlag).show();
	$(obj).hide();
}

</script>
</body>
</html>


