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
//                alert(document.getElementById("newUpload").parentNode.innerHTML);
                $("#newUpload").append('<div id="div_'+j+'"><input  name="file_'+j+'" type="file"  /><input type="button" value="删除"  onclick="del_2('+j+')"/></div>');
//                document.getElementById("newUpload").innerHTML+='<div id="div_'+j+'"><input  name="file_'+j+'" type="file"  /><input type="button" value="删除"  onclick="del_2('+j+')"/></div>';
//                alert(document.getElementById("newUpload").innerHTML);
                j = j + 1;
            });
        });

        function del_2(o){
            document.getElementById("newUpload").removeChild(document.getElementById("div_"+o));
        }

    </script>
</head>
<body>

<h1>springMVC包装类上传文件</h1>
<form name="userForm" action="<c:url value="/tfsManage/upload.do"/>" enctype="multipart/form-data" method="post" >
<div id="newUpload">
    <div id="div_0">
    <input type="file" name="file" >
    </div>
</div>
<input type="button" id="btn_add2" value="增加一行" >
<input type="submit" value="上传" >


</form>
</body>
</html>