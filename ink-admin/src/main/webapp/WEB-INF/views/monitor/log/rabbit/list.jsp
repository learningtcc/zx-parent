<%@ page import="com.ink.monitor.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>rabbit执行异常日志</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
	<script type="text/javascript" src="${yk}/static/common/maintain.js"></script>
</head>
<body>
<div class="title01 title01_x">
	<h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/monitor/log/rabbit/list.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
		<tr>	
			<td class="txtr" style="width: 100px">系统代码：</td>		
			<td>
				&nbsp;<select id="sysCode" name="sysCode" class="select02"></select>
			</td>
			<td class="txtr" style="width: 100px">MQ交换器：</td>		
			<td>
				&nbsp;<input value="<c:out value="${exchange}"/>" id="exchange" name="exchange" maxlength="50"/>
			</td>
			<td class="txtr" style="width: 100px">MQ路由：</td>		
			<td>
				&nbsp;<input value="<c:out value="${routingKey}"/>" id="routingKey" name="routingKey" maxlength="50"/>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 100px">请求流水：</td>
			<td>
				&nbsp;<input value="<c:out value="${requestId}"/>" id="requestId" name="requestId" maxlength="32"/>
			</td>
			<td class="txtr" style="width: 100px">状态：</td>
			<td>
				<yk:constantConvert htmlTag="select" dataTag="mq_status" name="logStatus" paramValue="${logStatus}" sysTag="monitor"/>
			</td>
			<td class="txtr" style="width: 100px">创建时间：</td>
			<td>
				&nbsp;<input onClick="WdatePicker()" value="<c:out value="${createTimeBegin}"/>" id="createTimeBegin" name="createTimeBegin" size="10"/>
				<input onClick="WdatePicker()" value="<c:out value="${createTimeEnd}"/>" id="createTimeEnd" name="createTimeEnd" size="10"/>
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
	<h2>rabbit执行异常日志</h2>
</div>
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb" class="table02">
			<thead>
			 <tr>
			 	<th>序号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>系统代码<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				 <th>应用名称<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>MQ交换器<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>MQ路由<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>消息ID<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>MQ IP<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>MQ端口<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>本地IP<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>请求流水<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>消息类型<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>状态<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>创建时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
			    <th>操作<div style="width: 75px; height: 1px; overflow: hidden;"></div></th>
	        </tr>
	        </thead>
			<c:forEach var="entry" items="${page.result}" varStatus="v">
			<tr>
				<input name="pkName" type="hidden" id="pkName" value="id"/>
				</td>
				<td>${v.index + 1 }</td>
				<td>
				<div><c:out value="${entry.sysCode}"/></div>
				</td>
				<td>
					<div><c:out value="${entry.appName}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.exchange}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.routingKey}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.msgId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.rabbitAddress}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.rabbitPort}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.localAddress}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.requestId}"/></div>
				</td>
				<td>
				<div>
					<c:out value="${entry.msgType}"/>
				</div>
				</td>
				<td>
				<div>
					<yk:constantConvert htmlTag="text" dataTag="mq_status" paramValue="${entry.logStatus}" emptyTip="未知" sysTag="monitor"/>
				</div>
				</td>
				<td>
				<div><c:out value="${entry.createTimeString}"/></div>
				</td>
				<td>
						<a href="#" onclick="show('${entry.id}')">详情</a>&nbsp;
					<c:if test="${entry.logStatus==0}" >
						<a href="#" onclick="sendMq('${entry.id}')">再次发送</a>&nbsp;
					</c:if>
				</td>
			</tr>
			</c:forEach>
			</table>
     	 <yk:page url="${yk}/monitor/log/rabbit/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">
function show(id){
	window.open('${yk}/monitor/log/rabbit/show.do?'+$('#pkName').val()+'='+id,'','height=800,width=1200,resizable=yes,location=no,scrollbars');
}
function edit(id){
	window.open('${yk}/monitor/log/rabbit/edit.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}

$(function(){

	var systemUrl = '${yk}/monitor/dict/source/listForAjax.do';
	executeAjax(systemUrl,"sysCode",'<c:out value="${sysCode}"/>',true);

	$('#btnclear').click(function(){
			$('#sysCode').val('');
			$('#exchange').val('');
			$('#routingKey').val('');
			$('#rabbitAddress').val('');
			$('#localAddress').val('');
			$('#requestId').val('');
			$('#logStatus').val('');
			$('#createTimeBegin').val('');
			$('#createTimeEnd').val('');
	});
});

	function  sendMq (id) {
		$.post( "${yk}/monitor/log/rabbit/sendMq.do", {"id":id},
				function(data){
					if(data=='1'){
						alert('发送成功');
						$('#queryForm').submit();
					}else{
						alert('发送失败');
					}
				}
		);
	}

</script>
</body>
</html>


