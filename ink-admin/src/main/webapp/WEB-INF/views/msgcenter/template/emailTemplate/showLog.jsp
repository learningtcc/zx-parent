<%@ page import="com.ink.msgcenter.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>修改记录对比</title>
    <%@ include file="/gen-commons/taglibs.jsp" %>
    <script type="text/javascript" charset="utf-8" src="${yk}/gen-commons/ueditor1_4_3_2/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${yk}/gen-commons/ueditor1_4_3_2/ueditor.all.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="${yk}/gen-commons/ueditor1_4_3_2/lang/zh-cn/zh-cn.js"></script>
    <style>
        table.box {
            font-family: verdana, arial, sans-serif;
            font-size: 11px;
            color: #333333;
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
    <h2>修改记录对比
    </h2>
</div>
<table class="box" style="width: 100%">
    <tr>
        <th>操作人:</th>
        <td>
            <c:choose>
                <c:when test="${showFlag=='0'}">
                    <c:out value="${model.creatorName}"/>
                </c:when>
                <c:otherwise>
                    <c:out value="${model.operatorName}"/>
                </c:otherwise>
            </c:choose>
        </td>
        <th>操作人:</th>
        <td><c:out value="${log.operatorName}"/></td>
    </tr>
    <tr>
        <th>操作时间:</th>
        <td>
            <c:choose>
                <c:when test="${showFlag=='0'}">
                    <c:out value="${log.operateTimeString}"/>
                </c:when>
                <c:otherwise>
                    <c:out value="${model.operateTimeString}"/>
                </c:otherwise>
            </c:choose>
        </td>
        <th>操作时间:</th>
        <td><c:out value="${log.operateTimeString}"/></td>
    </tr>
    <tr>
        <th width="10%"><c:if test="${fn:contains(log.logRemark, '商户ID')}"><font color="red"></c:if>商户名称:</font></th>
        <td width="40%">
            <yk:code2name columnName="name" tableName="merchant_info"
                                      where=" id=? " paramValue="${model.merctId}" serviceName="msgcenterDubboBaseService"/>
        </td>
        <th width="10%"><c:if test="${fn:contains(log.logRemark, '商户ID')}"><font color="red"></c:if>商户名称:</font></th>
        <td width="40%">
            <yk:code2name columnName="name" tableName="merchant_info"
                                      where=" id=? " paramValue="${log.merctId}" serviceName="msgcenterDubboBaseService"/>
        </td>
    </tr>
    <tr>
        <th><c:if test="${fn:contains(log.logRemark, '商户代码')}"><font color="red"></c:if>商户代码:</font></th>
        <td><c:out value="${model.merctCode}"/></td>
        <th><c:if test="${fn:contains(log.logRemark, '商户代码')}"><font color="red"></c:if>商户代码:</font></th>
        <td><c:out value="${log.merctCode}"/></td>
    </tr>
    <tr>
        <th><c:if test="${fn:contains(log.logRemark, '模板名称')}"><font color="red"></c:if>模板名称:</font></th>
        <td><c:out value="${model.tempName}"/></td>
        <th><c:if test="${fn:contains(log.logRemark, '模板名称')}"><font color="red"></c:if>模板名称:</font></th>
        <td><c:out value="${log.tempName}"/></td>
    </tr>
    <tr>
        <th><c:if test="${fn:contains(log.logRemark, '模板内容')}"><font color="red"></c:if>模板内容:</font></th>
        <td width="40%">
            <script id="editor" type="text/plain">${model.tempContent}</script>
        </td>
        <th><c:if test="${fn:contains(log.logRemark, '模板内容')}"><font color="red"></c:if>模板内容:</font></th>
        <td width="40%">
            <script id="editor1" type="text/plain">${log.tempContent}</script>
        </td>
    </tr>
    <tr>
        <th><c:if test="${fn:contains(log.logRemark, '解析方式')}"><font color="red"></c:if>解析方式:</font></th>
        <td>
            <yk:constantConvert htmlTag="text" dataTag="parseMethod" name="parseMethod"
                                paramValue="${model.parseMethod}" emptyTip="未定义" sysTag="msgcenter"/>
        </td>
        <th><c:if test="${fn:contains(log.logRemark, '解析方式')}"><font color="red"></c:if>解析方式:</font></th>
        <td>
            <yk:constantConvert htmlTag="text" dataTag="parseMethod" name="parseMethod" paramValue="${log.parseMethod}"
                                emptyTip="未定义" sysTag="msgcenter"/>
        </td>

    </tr>
    <tr>
        <th><c:if test="${fn:contains(log.logRemark, '模板备注')}"><font color="red"></c:if>模板备注:</font></th>
        <td><c:out value="${model.tempRemark}"/></td>
        <th><c:if test="${fn:contains(log.logRemark, '模板备注')}"><font color="red"></c:if>模板备注:</font></th>
        <td><c:out value="${log.tempRemark}"/></td>
    </tr>
    <tr>
        <th><c:if test="${fn:contains(log.logRemark, '模板参数')}"><font color="red"></c:if>模板参数:</font></th>
        <td><c:out value="${model.tempParam}"/></td>
        <th><c:if test="${fn:contains(log.logRemark, '模板参数')}"><font color="red"></c:if>模板参数:</font></th>
        <td><c:out value="${log.tempParam}"/></td>
    </tr>

    <tr>
        <th><c:if test="${fn:contains(log.logRemark, '模板状态')}"><font color="red"></c:if>模板状态:</font></th>
        <td>
            <yk:constantConvert htmlTag="text" dataTag="templateStatus" name="status" paramValue="${model.status}"
                                emptyTip="未定义" sysTag="msgcenter"/>
        </td>
        <th><c:if test="${fn:contains(log.logRemark, '模板状态')}"><font color="red"></c:if>模板状态:</font></th>
        <td><yk:constantConvert htmlTag="text" dataTag="templateStatus" name="status" paramValue="${log.status}"
                                emptyTip="未定义" sysTag="msgcenter"/></td>
    </tr>
</table>
</body>
<script type="text/javascript">
    var editor = UE.getEditor('editor', {
        //这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
        toolbars: [[]],
        //关闭字数统计
        wordCount: false,
        //关闭elementPath
        elementPathEnabled: false
    });
    var editor1 = UE.getEditor('editor1', {
        //这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
        toolbars: [[]],
        //关闭字数统计
        wordCount: false,
        //关闭elementPath
        elementPathEnabled: false
    });

</script>
</html>