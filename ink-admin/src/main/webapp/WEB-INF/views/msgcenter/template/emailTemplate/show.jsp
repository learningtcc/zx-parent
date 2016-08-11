<%@ page import="com.ink.msgcenter.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>邮件模板信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
<script type="text/javascript" charset="utf-8" src="${yk}/gen-commons/ueditor1_4_3_2/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${yk}/gen-commons/ueditor1_4_3_2/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="${yk}/gen-commons/ueditor1_4_3_2/lang/zh-cn/zh-cn.js"></script>
 <style>
table.box {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}
table.box th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
	width: 58px;
}
table.box td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
</style>
</head>
<body>
	<div class="title01">
	<h2>邮件模版详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="box" style="width: 100%">
		<tr>	
			<th class="txtr">商户ID:</th>	
			<td>
				<yk:code2name columnName="name" tableName="merchant_info" where=" id=? " paramValue="${model.merctId}" serviceName="msgcenterDubboBaseService"/>
			</td>
			<th class="txtr">商户代码:</th>	
			<td><c:out value="${model.merctCode}"/></td>
			<th class="txtr">模板名称:</th>	
			<td><c:out value="${model.tempName}"/></td>
			<th class="txtr">邮件主题:</th>	
			<td><c:out value="${model.mailSubject}"/></td>
		</tr>
		<tr>	
			<th class="txtr" width="12%">模板内容:</th>
			<td colspan="7" width="85%">
				<script id="editor" type="text/plain" >${model.tempContent}</script>
			</td>
		</tr>
		
		<tr>
			<th class="txtr">模板类型:</th>
			<td>
				<yk:constantConvert htmlTag="text" dataTag="tempType" name="tempType" paramValue="${model.tempType}" emptyTip="未定义" sysTag="msgcenter"/>
			</td>
			<th class="txtr">解析方式:</th>
			<td>
				<yk:constantConvert htmlTag="text" dataTag="parseMethod" name="parseMethod" paramValue="${model.parseMethod}" emptyTip="未定义" sysTag="msgcenter"/>
			</td>
			<th class="txtr">模板备注:</th>	
			<td><c:out value="${model.tempRemark}"/></td>
			<th class="txtr">模板参数:</th>	
			<td><c:out value="${model.tempParam}"/></td>
		</tr>
		
		<tr>	
			<th class="txtr">状态:</th>	
			<td>
				<yk:constantConvert htmlTag="text" dataTag="templateStatus" name="status" paramValue="${model.status}" emptyTip="未定义" sysTag="msgcenter"/>
			</td>
			<th class="txtr">创建人:</th>	
			<td><c:out value="${model.creatorName}"/></td>
			<th class="txtr">创建时间:</th>	
			<td><fmt:formatDate value="${model.createTime}" pattern="yyyy-mm-dd HH:mm:ss"/></td>
			<td></td>
			<td></td>
		</tr>
		
		<tr><td colspan="8">&nbsp;</td></tr>
		<tr><th colspan="8">操作日志</th></tr>
		<tr>
			<th colspan="2">操作时间:</th>
			
			<th colspan="2">操作人</th>
			
			<th colspan="2">操作内容</th>
			
			<th colspan="2">明细</th>
		</tr>
		<tr>
			<td colspan="2">${log.operateTimeString }</td>
			<td colspan="2">${log.operatorName }</td>
			<td colspan="2">修改了${log.logRemark }</td>
			<td colspan="2"><font color="red"><a href="javascript:;" onclick="showLog('${model.id}')">详情..</a></font></td>
		</tr>
		<tr><th colspan="8">更多历史</th></tr>
		<c:forEach var="entry" items="${page.result}" varStatus="v">
		<tr>
			<td colspan="2">${entry.operateTimeString }</td>
			<td colspan="2">${entry.operatorName }</td>
			<td colspan="2">修改了${entry.logRemark }</td>
			<td colspan="2"><font color="red"><a href="javascript:;" onclick="showHistoryLog('${entry.id}')">详情..</a></font></td>
		</tr>
		</c:forEach>
		<tr><td colspan="8"><yk:page url="${yk}/msgcenter/EmailTemplate/show.do?id=${model.id }" id="logForm" submitForm="queryForm" joy="true"/></td></tr>
	</table>
	<form id="queryForm" name="queryForm" action="<c:url value="/EmailTemplate/show.do?id=${model.id }"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	</form>
	</body>
<script type="text/javascript">
var editor = UE.getEditor('editor',{  
	//这里可以选择自己需要的工具按钮名称,此处仅选择如下五个  
	toolbars:[[]],  
	//关闭字数统计  
	wordCount:false,  
	//关闭elementPath  
	elementPathEnabled:false
}); 


	function showLog(id) {
		window.open('${yk}/msgcenter/EmailTemplate/showLog.do?id='
				+ id, 'resizable=yes,location=no,scrollbars');
	}
	function showHistoryLog(id) {
		window.open('${yk}/msgcenter/EmailTemplate/showHistoryLog.do?id='
				+ id, 'resizable=yes,location=no,scrollbars');
	}
</script>
</html>