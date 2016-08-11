<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
	<table width="100%">

		<tr>
			<td width="98%"><button onclick="initAccMac()">初始化导入数据AccMac</button></td>
		</tr>

	</table>
<script type="application/javascript">
	function initAccMac(){

		if(confirm("确定要初始化吗？")){
			$.post( "${yk}/user/AccAcc/initAccMac.do",
					function(data){
						if(data=='1'){
							alert('初始化成功');
						}else{
							alert('初始化失败');
						}
					}
			);
		}
	}

</script>
</body>
</html>


