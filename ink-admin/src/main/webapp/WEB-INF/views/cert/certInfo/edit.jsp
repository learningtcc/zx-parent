<%@ page import="com.ink.cert.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>证书编辑</title>
    <%@ include file="/gen-commons/taglibs.jsp" %>
    <script type="text/javascript" src="${yk}/static/common/maintain.js"></script>
</head>
<body>
<div class="title01">
    <h2>修改证书信息
    </h2>
</div>
<form id="form1" action="${yk}/cert/certInfo/update.do" method="post">

    <table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
        <input type="hidden" id="id" name="id" value="<c:out value="${model.id}"/>"/>

        <input id="certId" name="certId" value="<c:out value="${model.certId}"/>" type="hidden" />
        <input id="status" name="status" value="<c:out value="${model.status}"/>" type="hidden" class="input04"/>
        <input id="createUser" name="createUser" value="<c:out value="${model.createUser}"/>" type="hidden"/>
        <input  name="createTime" value="<c:out value="${model.createTime}"/>" type="hidden"/>
        <tr>
            <td class="txtr" style="width: 188px">
                商户号:
            </td>
            <td>
                <input id="merchatCode" name="merchatCode" value="<c:out value="${model.merchatCode}"/>" type="text"
                       class="input04" readonly/>
            </td>
        </tr>
        <tr>
            <td class="txtr" style="width: 188px">
                证书编号:
            </td>
            <td>
                <input id="certCode" name="certCode"  type="text" value="<c:out value="${model.certCode}"/>" class="input04" readonly/>
            </td>
        </tr>
        <tr>
            <td name="certTr" class="txtr" style="width: 188px">
                证书名称<label style="color: red">*</label>:
            </td>
            <td>
                <input id="certName" name="certName"  type="text" value="<c:out value="${model.certName}"/>" class="input04"/>
            </td>
        </tr>
        <tr name="certTr">
            <td class="txtr" style="width: 188px">
                文件名称<label style="color: red">*</label>:
            </td>
            <td>
                <input id="fileName" name="fileName" value="<c:out value="${model.fileName}"/>" type="text" class="input04" />
            </td>
        </tr>
        <tr>
            <td class="txtr" style="width: 188px">
                加解密方式：
            </td>
            <td>
                <yk:constantConvert htmlTag="text" dataTag="endecryType" paramValue="${model.endecryType}"
                                    name="endecryType" emptyTip="未知" sysTag="cert"/>
                <input id="endecryType" name="endecryType" type="hidden" value="<c:out value="${model.endecryType}"/>"  >
            </td>
        </tr>

        <tr name="certTr">
            <td class="txtr" style="width: 188px">
                证书类型:
            </td>
            <td>
                <yk:constantConvert htmlTag="text" dataTag="certType" paramValue="${model.certType}" name="certType"
                                    emptyTip="未知" sysTag="cert"/>
                <input  name="certType" type="hidden" value="<c:out value="${model.certType}"/>" />
            </td>
        </tr>
        <tr name="secretTr">
            <td class="txtr" style="width: 188px">
                算法类型<label style="color: red">*</label>:
            </td>
            <td>
                <yk:constantConvert htmlTag="select" dataTag="arithmeticType" paramValue="${model.arithmeticType}" name="arithmeticType" sysTag="cert"/>
            </td>
        </tr>
        <tr>
            <td class="txtr" style="width: 188px">
                密钥:
            </td>
            <td>
                <input id="secretKey" name="secretKey" value="<c:out value="${model.secretKey}"/>" type="text"
                       class="input04"/>
            </td>
        </tr>
        <tr>
            <td class="txtc" colspan="2">
                <input type="button" class="btn01" value="提交" onclick="$('#form1').submit();"/>
                <input type="button" class="btn01" value="取消" onclick="window.close();"/>
                <input type="reset" class="btn01" value="重置"/>
            </td>
        </tr>
    </table>
</form>

</body>
<script type="text/javascript">
    $(function () {
        var options = {
            beforeSubmit: validateForm,   // pre-submit callback 表单提交前被调用的回调函数
            success: callBack   // post-submit callback   表单提交成功后被调用的回调函数
        };
        $('#form1').ajaxForm(options);

        var endecryType = $("#endecryType").val();
        if(endecryType =='0'){
            $("tr[name=certTr]").show();
            $("tr[name=secretTr]").hide();
        }else{
            $("tr[name=certTr]").hide();
            $("tr[name=secretTr]").show();
        }
    });

    window.onbeforeunload = function () {
        window.opener.document.queryForm.submit();
    }

    function validateForm() {
        var result = false;
        if(validateLengthFrom("certName",1,"请输入证书名称")){
            var endecryType = $("#endecryType").val();
            if(endecryType == '1'){
                if(validateLengthFrom("arithmeticType",1,"请选择算法类型")){
                    result = true;
                }
            }else{
                result = true;
            }
        }
        return result;
    }

    function callBack(data) {
        if (data == 1) {
            alert("保存成功！");
            window.close();
        } else {
            alert("保存失败！");
        }
    }
</script>
</html>