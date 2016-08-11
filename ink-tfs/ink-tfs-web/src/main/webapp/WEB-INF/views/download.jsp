<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/gen-commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript">
        i = 1;
        j = 1;
        $(document).ready(function(){

            $("#btn_add2").click(function(){
                document.getElementById("newUpload2").innerHTML+='<div id="div_'+j+'"><input  name="file_'+j+'" type="file"  /><input type="button" value="删除"  onclick="del_2('+j+')"/></div>';
                j = j + 1;
            });
        });

        function del_2(o){
            document.getElementById("newUpload2").removeChild(document.getElementById("div_"+o));
        }

    </script>
</head>
<body>

<h1>springMVC包装类下载文件</h1>
<form name="userForm" action="<c:url value="/file/download.do"/>"  method="post" >
    tfs文件名：<input type="text" id="tfsFileName" name="tfsFileName" value="">
    文件名：<input type="text" id="fileName" name="fileName" value="">
    文件后缀：<input type="text" id="suffix" name="suffix" value="">
    <input type="submit" value="xiazai">
</form>
</body>
</html>