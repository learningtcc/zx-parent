<%@ page import="com.ink.cert.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>证书信息</title>
    <%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
    <h2>证书详情</h2>
</div>
<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
    <tr>
        <td class="txtr" style="width: 188px">商户号:</td>
        <td><c:out value="${model.merchatCode}"/></td>
    </tr>
    <tr>
        <td class="txtr" style="width: 188px">证书编号:</td>
        <td><c:out value="${model.certCode}"/></td>
    </tr>
    <tr>
        <td class="txtr" style="width: 188px">加解密方式:</td>
        <td>
            <yk:constantConvert htmlTag="text" dataTag="endecryType" paramValue="${model.endecryType}"
                                emptyTip="未知" sysTag="cert"/>
        </td>
    </tr>
    <tr name="certTr">
        <td class="txtr" style="width: 188px">证书类型:</td>
        <td>
            <yk:constantConvert htmlTag="text" dataTag="certType" paramValue="${model.certType}"
                                emptyTip="未知" sysTag="cert"/>
        </td>
    </tr>
    <tr name="secretTr">
        <td class="txtr" style="width: 188px">算法类型:</td>
        <td>
            <yk:constantConvert htmlTag="text" dataTag="arithmeticType" paramValue="${model.arithmeticType}"
                                emptyTip="未知" sysTag="cert" />
        </td>
    </tr>
    <tr name="certTr">
        <td class="txtr" style="width: 188px">证书ID:</td>
        <td><c:out value="${model.certId}"/></td>
    </tr>
    <tr name="certTr">
        <td class="txtr" style="width: 188px">证书文件名:</td>
        <td><c:out value="${model.certName}"/></td>
    </tr>
    <tr>
        <td class="txtr" style="width: 188px">密钥:</td>
        <td><c:out value="${model.secretKey}"/></td>
    </tr>
    <tr>
        <td class="txtr" style="width: 188px">状态:</td>
        <td>
            <yk:constantConvert htmlTag="text" dataTag="status" paramValue="${model.status}"
                                emptyTip="未知" sysTag="cert"/>
        </td>
    </tr>
    <tr>
        <td class="txtr" style="width: 188px">创建人:</td>
        <td><c:out value="${model.createUser}"/></td>
    </tr>
    <tr>
        <td class="txtr" style="width: 188px">创建时间:</td>
        <td><c:out value="${model.createTimeString}"/></td>
    </tr>
    <tr>
        <td class="txtr" style="width: 188px">更新人:</td>
        <td><c:out value="${model.updateUser}"/></td>
    </tr>
    <tr>
        <td class="txtr" style="width: 188px">更新时间:</td>
        <td><c:out value="${model.updateTimeString}"/></td>
    </tr>
</table>
</body>
<script type="text/javascript">
    $(function () {
        var endecryType = '${model.endecryType}';
        if (endecryType == '0') {
            $("tr[name=certTr]").show();
            $("tr[name=secretTr]").hide();
        } else {
            $("tr[name=certTr]").hide();
            $("tr[name=secretTr]").show();
        }
    });
</script>
</html>