<%@ page import="com.ink.msgcenter.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><%=EmailLog.TABLE_ALIAS%>信息</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
<script type="text/javascript" charset="utf-8" src="${yk}/gen-commons/ueditor1_4_3_2/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${yk}/gen-commons/ueditor1_4_3_2/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="${yk}/gen-commons/ueditor1_4_3_2/lang/zh-cn/zh-cn.js"></script>
<style type="text/css">
    div{
        width:100%;
    }
</style>
</head>
<body>
	<div class="title01">
	<h2><%=EmailLog.TABLE_ALIAS%>详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		<tr>	
			<td class="txtr" style="width: 188px">商户名称:</td>	
			<td><yk:code2name columnName="name" tableName="merchant_info" where="id = ?" paramValue="${model.merctId}" serviceName="msgcenterDubboBaseService"/></td>
			<td class="txtr" style="width: 188px">商户代码:</td>	
			<td><c:out value="${model.merctCode}"/></td>
			<td class="txtr" style="width: 188px">通道名称:</td>	
			<td><yk:code2name columnName="name" tableName="email_channel" where="id = ?" paramValue="${model.chnId}" serviceName="msgcenterDubboBaseService"/></td>
			<td class="txtr" style="width: 188px">通道代码:</td>	
			<td><c:out value="${model.chnCode}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">模板ID:</td>	
			<td><c:out value="${model.tempId}"/></td>
			<td class="txtr" style="width: 188px">模板名称:</td>	
			<td><yk:code2name columnName="temp_name" tableName="email_template" where="id = ?" paramValue="${model.tempId}" serviceName="msgcenterDubboBaseService"/></td>
			<td class="txtr" style="width: 188px">邮箱:</td>	
			<td><c:out value="${model.email}"/></td>
			<td class="txtr" style="width: 188px">主题:</td>	
			<td><c:out value="${model.subject}"/></td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">邮件内容:</td>	
			<td colspan="7">
				<script id="editor" type="text/plain" style="width:350px;height:300px;">${model.mailMsg}</script>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 188px">业务单号:</td>	
			<td><c:out value="${model.infoCode}"/></td>
			<td class="txtr" style="width: 188px">发送状态:</td>	
			<td>
				<yk:constantConvert htmlTag="text" dataTag="sendStatus" paramValue="${model.sendStatus}" emptyTip="未知" sysTag="msgcenter"/>
			</td>
			<td class="txtr" style="width: 188px">消息ID:</td>
			<td><c:out value="${model.emailId}"/></td>
			<td class="txtr" style="width: 188px">提交时间:</td>	
			<td><c:out value="${model.submitTimeString}"/></td>
		</tr>
		<tr>
			<td class="txtr" style="width: 188px">发送时间:</td>
			<td><c:out value="${model.sendTimeString}"/></td>
			<td class="txtr" style="width: 188px">异常信息:</td>	
			<td colspan="7"><c:out value="${model.sendException}"/></td>
		</tr>
	</table>
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

</script>
</html>