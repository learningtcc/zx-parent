<%@ page import="com.ink.monitor.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>mongo日志查询</title>
    <%@ include file="/gen-commons/taglibs.jsp" %>
    <script type="text/javascript" src="${yk}/static/common/maintain.js"></script>
</head>
<body>
<div class="title01">
    <h2>搜索<span
            class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span>
    </h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/monitor/log/mongo/list.do"/>" method="post"
      style="display: inline;">
    <input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
    <input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
    <input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
        <tr>

            <td class="txtr" style="width: 100px">平台系统：</td>
            <td>
                <select id="source" name="source" class="select02">

                </select>
            </td>
            <td class="txtr" style="width: 100px">功能模块：</td>
            <td>
                &nbsp;<select id="module" name="module" class="select02"></select>
            </td>
            <td class="txtr" style="width: 100px">业务模块：</td>
            <td>
                &nbsp;<select id="infoId" name="infoId" class="select02"></select>
            </td>
            <td class="txtr" style="width: 100px">日志级别：</td>
            <td>
                <select id="logLevel" name="logLevel" class="select02">
                    <option value="">请选择...</option>
                    <option value="DEBUG">DEBUG</option>
                    <option value="INFO">INFO</option>
                    <option value="WARN">WARN</option>
                    <option value="ERROR">ERROR</option>
                </select>
            </td>
        </tr>
        <tr>
            <td class="txtr" style="width: 100px">请求系统：</td>
            <td>

                <input value="<c:out value="${reqContext}"/>" id="reqContext" name="reqContext"/>
            </td>
            <td class="txtr" style="width: 100px">响应系统：</td>
            <td>
                <input value="<c:out value="${resContext}"/>" id="resContext" name="resContext"/>
            </td>
            <td class="txtr" style="width: 100px">交易流水：</td>
            <td>
                <input value="<c:out value="${logSeq}"/>" id="logSeq" name="logSeq"/>
            </td>
            <td class="txtr" style="width: 100px">用户账号：</td>
            <td>
                <input value="<c:out value="${userName}"/>" id="userName" name="userName"/>
            </td>

        </tr>
        <tr>
            <td class="txtr" style="width: 100px">请求IP：</td>
            <td>
                <input value="<c:out value="${reqIp}"/>" id="reqIp" name="reqIp"/>
            </td>
            <td class="txtr" style="width: 100px">响应IP：</td>
            <td>

                <input value="<c:out value="${serverIp}"/>" id="serverIp" name="serverIp"/>
            </td>
            <td class="txtr" style="width: 100px">日志消息：</td>
            <td>
                <input value="<c:out value="${message}"/>" id="message" name="message"/>
            </td>

            <td class="txtr" style="width: 100px">请求流水：</td>
            <td>
                <input value="<c:out value="${requestId}"/>" id="requestId" name="requestId"/>
            </td>

        </tr>
        <tr>

            <td class="txtr" style="width: 100px">请求URL：</td>
            <td>
                <input value="<c:out value="${requestUrl}"/>" id="requestUrl" name="requestUrl"/>
            </td>
            <td class="txtr" style="width: 100px">日志时间：</td>
            <td colspan="3">
                <input onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<c:out value="${startLogTime}"/>"
                       id="startLogTime" name="startLogTime" size="10"/>
                <input onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<c:out value="${endLogTime}"/>"
                       id="endLogTime" name="endLogTime" size="10"/>
            </td>
        </tr>
    </table>
    <br/>

    <div align="center">
        <input type="button" class="btn01" value="查 询" onclick="subQuery()"/>
        <input type="button" class="btn01" value="清 空" id="btnclear"/>
    </div>
</form>
<br/>

<div class="title01">
    <h2><font color="red" id="systemName">
        <yk:code2name tableName="systerm_source" columnName="name" where="code=?"
                      paramValue="${source}" emptyTip="${source}" serviceName="monitorDubboBaseService"/>
    </font>日志记录列表</h2>
</div>
<div>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb" class="table02">
        <thead>
        <tr>
            <%--<th><input id="allChoose" type="checkbox"/></th>--%>
            <th>序号
                <div style="width: 30px; height: 1px; overflow: hidden;"></div>
            </th>
            <th>功能模块
                <div style="width: 65px; height: 1px; overflow: hidden;"></div>
            </th>
            <th>业务模块
                <div style="width: 65px; height: 1px; overflow: hidden;"></div>
            </th>
            <th>日志级别
                <div style="width: 65px; height: 1px; overflow: hidden;"></div>
            </th>
            <th>日志时间
                <div style="width: 65px; height: 1px; overflow: hidden;"></div>
            </th>
            <th>请求应用
                <div style="width: 65px; height: 1px; overflow: hidden;"></div>
            </th>
            <th>响应应用
                <div style="width: 65px; height: 1px; overflow: hidden;"></div>
            </th>
            <th>交易流水
                <div style="width: 65px; height: 1px; overflow: hidden;"></div>
            </th>
            <th>用户账号
                <div style="width: 65px; height: 1px; overflow: hidden;"></div>
            </th>
            <th>日志消息
                <div style="width: 90px; height: 1px; overflow: hidden;"></div>
            </th>
            <th>请求IP
                <div style="width: 65px; height: 1px; overflow: hidden;"></div>
            </th>
            <th>响应IP
                <div style="width: 65px; height: 1px; overflow: hidden;"></div>
            </th>
            <th>请求URL
                <div style="width: 65px; height: 1px; overflow: hidden;"></div>
            </th>
            <th>请求流水
                <div style="width: 65px; height: 1px; overflow: hidden;"></div>
            </th>
            <th>操作
                <div style="width: 50px; height: 1px; overflow: hidden;"></div>
            </th>
        </tr>
        </thead>
        <c:forEach var="entry" items="${page.result}" varStatus="v">
            <tr>
                <td>${v.index + 1 }</td>
                <td>
                    <div>
                        <yk:code2name tableName="systerm_module" columnName="name" where="code=?"
                                      paramValue="${entry.module}" emptyTip="${entry.module}" serviceName="monitorDubboBaseService"/>
                    </div>
                </td>
                <td>
                    <div>
                        <yk:code2name tableName="systerm_info" columnName="name" where="code=?"
                                      paramValue="${entry.infoId}" emptyTip="${entry.infoId}" serviceName="monitorDubboBaseService"/>
                    </div>
                </td>
                <td>
                    <div><c:out value="${entry.logLevel}"/></div>
                </td>
                <td>
                    <div><c:out value="${entry.logTime}"/></div>
                </td>
                <td>
                    <div><c:out value="${entry.reqContext}"/></div>
                </td>
                <td>
                    <div><c:out value="${entry.resContext}"/></div>
                </td>
                <td>
                    <div><c:out value="${entry.tradeId}"/></div>
                </td>
                <td>
                    <div>
                        <yk:code2name tableName="monitor_user" columnName="fullName" where="userName=?"
                                      paramValue="${entry.userName}" emptyTip="${entry.userName}" serviceName="monitorDubboBaseService"/>
                    </div>
                </td>
                <td >
                    <div class="showMsg" style="width:150px;white-space:nowrap; text-overflow:ellipsis; -o-text-overflow:ellipsis; overflow: hidden;" onclick="showLogMsg(this)"   ><c:out value="${entry.message}"/></div>
                    <div class="showFullMsg" style="display: none" onmouseout="hideLogMsg(this)"><c:out value="${entry.message}"/></div>
                </td>
                <td>
                    <div><c:out value="${entry.reqIp}"/></div>
                </td>
                <td>
                    <div><c:out value="${entry.serverIp}"/></div>
                </td>
                <td>
                    <div><c:out value="${entry.requestUrl}"/></div>
                </td>
                <td>
                    <div><c:out value="${entry.requestId}"/></div>
                </td>

                <td>
                    <a href="#" onclick="show('${entry._id}','${entry.source}')">详情</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <yk:page url="${yk}/monitor/log/mongo/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">
    function show(id, source) {
        window.open('${yk}/monitor/log/mongo/show.do?id=' + id + '&source=' + source, '', 'height=600,width=600,resizable=yes,location=no,scrollbars');
    }

    $(function () {

        $("#logLevel").val('<c:out value="${logLevel}"/>');
        //ajax获取平台系统
        var systemUrl = '${yk}/monitor/dict/source/listForAjax.do';
        findSource(systemUrl, "source", '<c:out value="${source}"/>', true);

        //系统代码联动模块代码
        $("#source").change(function () {
            var optionValue = this.value;
            if (optionValue == "") {
                $("#module").empty();
                return;
            }
            var moduleUrl = '${yk}/monitor/dict/module/listAjaxForSystemCode.do?systemCode=' + optionValue;
            executeAjax(moduleUrl, "module", '<c:out value="${module}"/>', true);

        });

        $("#module").change(function () {
            var optionValue = this.value;
            if (optionValue == "") {
                $("#infoId").empty();
                return;
            }
            var infoUrl = '${yk}/monitor/dict/info/listAjaxForModuleCode.do?moduleCode=' + optionValue;
            executeAjax(infoUrl, "infoId", '<c:out value="${infoId}"/>');

        });

        $('#btnclear').click(function () {
            $('#source').val('');
            $('#module').val('');
            $('#infoId').val('');
            $('#logLevel').val('');
            $('#reqContext').val('');
            $('#resContext').val('');
            $('#logSeq').val('');
            $('#reqIp').val('');
            $('#serverIp').val('');
            $('#userName').val('');
            $('#message').val('');
            $('#requestId').val('');
            $('#requestUrl').val('');
            $('#startLogTime').val('');
            $('#endLogTime').val('');
        });
    });

    function subQuery() {
        if(validateLengthFrom("startLogTime","2","请输入日志开始时间")&&
                validateLengthFrom("endLogTime","2","请输入日志结束时间")){
            $('#pageNumber').val('1');
            $('#pageSize').val('10');
            $('#queryType').val('0');
            $("#queryForm").submit();
        }


    }

    function showLogMsg(obj){
        $(obj).hide();
        $(obj).next(".showFullMsg").show();
    }
    function hideLogMsg(obj){
        $(obj).hide(200);
        $(obj).prev(".showMsg").show();
    }

</script>
</body>
</html>


