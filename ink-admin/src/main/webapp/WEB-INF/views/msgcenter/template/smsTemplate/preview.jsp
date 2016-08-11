<%@ page import="com.ink.msgcenter.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>短信预览信息</title>
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
	<h2><%=SmsTemplate.TABLE_ALIAS%>详情</h2>
</div>
	<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
		
		<tr>	
			<td class="txtr" width="15%">模板内容:</td>
			<td width="80%">
				<script id="editor" type="text/plain" >${model.tempContent}</script>
			</td>
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