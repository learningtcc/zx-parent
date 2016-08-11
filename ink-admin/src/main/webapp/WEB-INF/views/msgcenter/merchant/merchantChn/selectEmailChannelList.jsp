<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>选择邮件通道</title>
    <%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<%--<div class="down_al_top">为了我们能提供给您更多更好的服务，请如实填写以下信息：</div>--%>
    <form id="selectForm" action="${yk}/msgcenter/merchant/merchantChn/saveEmailChannelList.do" method="post">
        <input type="hidden" id="id" name="id" value="${id}"/>
        <input type="hidden" id="sn" name="sn" value="${sn}"/>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb" class="table02">
            <thead>
            <tr>
                <th><input id="allChoose" type="checkbox"></th>

                <th>通道代码
                    <div style="width: 65px; height: 1px; overflow: hidden;"></div>
                </th>
                <th>通道名称
                    <div style="width: 65px; height: 1px; overflow: hidden;"></div>
                </th>
                <th>发送邮箱
                    <div style="width: 65px; height: 1px; overflow: hidden;"></div>
                </th>
                <th>发送服务器
                    <div style="width: 65px; height: 1px; overflow: hidden;"></div>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${dataList}" var="item">
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${item.checked == '1'}">
                                <input name="ids" id="ids" type="checkbox" value="${item.id}" checked>
                            </c:when>
                            <c:otherwise>
                                <input name="ids" id="ids" type="checkbox" value="${item.id}">
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <div><c:out value="${item.chn_code}"/></div>
                    </td>
                    <td>
                        <div><c:out value="${item.name}"/></div>
                    </td>
                    <td>
                        <div><c:out value="${item.mail_addr}"/></div>
                    </td>
                    <td>
                        <div><c:out value="${item.smtp_host}"/></div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
</body>
</html>
