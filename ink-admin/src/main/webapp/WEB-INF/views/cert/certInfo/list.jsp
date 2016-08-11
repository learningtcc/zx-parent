<%@ page import="com.ink.cert.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>证书维护</title>
    <%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
    <h2>搜索<span
            class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span>
    </h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/cert/certInfo/list.do"/>" method="post"
      style="display: inline;">
    <input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
    <input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
    <input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
        <tr>
            <td class="txtr" style="width: 100px">商户号：</td>
            <td>
                &nbsp;<input value="<c:out value="${merchatCode}"/>" id="merchatCode" name="merchatCode"
                             maxlength="20"/>
            </td>
            <td class="txtr" style="width: 100px">证书编号：</td>
            <td>
                &nbsp;<input value="<c:out value="${certCode}"/>" id="certCode" name="certCode" maxlength="50"/>
            </td>

        </tr>
        <tr>
            <td class="txtr" style="width: 100px">证书名称：</td>
            <td>
                &nbsp;<input value="<c:out value="${certName}"/>" id="certName" name="certName" maxlength="50"/>
            </td>
            <td class="txtr" style="width: 100px">状态：</td>
            <td>
                &nbsp;
                <yk:constantConvert htmlTag="select" dataTag="status" paramValue="${status}" name="status" sysTag="cert"/>
            </td>

        </tr>
    </table>
    <br/>

    <div align="center">
        <input type="submit" class="btn01" value="查 询"
               onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('1');"/>
        <input type="button" class="btn01" value="清 空" id="btnclear"/>
    </div>
</form>
<br/>

<div class="title01">
    <h2>证书维护列表</h2>
</div>
<div class="groupbtn">
    <a href="javascript:void(0)" title="新建" class="btn001" id="btnadd">新增</a>
    <a href="javascript:void(0)" title="修改" class="btn002" id="btnedit">修改</a>
    <a href="javascript:void(0)" title="删除" class="btn004" id="btndelete">删除</a>
    <input type="button" class="btn01" value="部署配置下载" id="btnDownLoad"/>
</div>
<div>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb" class="table02">
        <thead>
        <tr>
            <th><input id="allChoose" type="checkbox"/></th>
            <th>序号
                <div style="width: 35px; height: 1px; overflow: hidden;"></div>
            </th>
            <th>商户号
                <div style="width: 65px; height: 1px; overflow: hidden;"></div>
            </th>
            <th>证书编号
                <div style="width: 65px; height: 1px; overflow: hidden;"></div>
            </th>
            <th>证书名称
                <div style="width: 65px; height: 1px; overflow: hidden;"></div>
            </th>
            <th>加解密方式
                <div style="width: 65px; height: 1px; overflow: hidden;"></div>
            </th>
            <th>证书类型
                <div style="width: 65px; height: 1px; overflow: hidden;"></div>
            </th>
            <th>密钥
                <div style="width: 65px; height: 1px; overflow: hidden;"></div>
            </th>
            <th>算法类型
                <div style="width: 65px; height: 1px; overflow: hidden;"></div>
            </th>
            <th>证书文件名
                <div style="width: 65px; height: 1px; overflow: hidden;"></div>
            </th>
            <th>状态
                <div style="width: 65px; height: 1px; overflow: hidden;"></div>
            </th>
            <th>操作
                <div style="width: 75px; height: 1px; overflow: hidden;"></div>
            </th>
        </tr>
        </thead>
        <c:forEach var="entry" items="${page.result}" varStatus="v">
            <tr>
                <td><input name="ids" type="checkbox" value="${entry.id}"/>
                    <input name="pkName" type="hidden" id="pkName" value="id"/>
                </td>
                <td>${v.index + 1 }</td>
                <td>
                    <div><c:out value="${entry.merchatCode}"/></div>
                </td>
                <td>
                    <div><c:out value="${entry.certCode}"/></div>
                </td>
                <td>
                    <div><c:out value="${entry.certName}"/></div>
                </td>
                <td>
                    <div>
                        <yk:constantConvert htmlTag="text" dataTag="endecryType" paramValue="${entry.endecryType}" emptyTip="未知" sysTag="cert"/>
                    </div>
                </td>
                <td>
                    <div>
                        <yk:constantConvert htmlTag="text" dataTag="certType" paramValue="${entry.certType}"
                                            emptyTip=" " sysTag="cert"/>
                    </div>
                </td>
                <td>
                    <div><c:out value="${entry.secretKey}"/></div>
                </td>
                <td>
                    <div>
                        <yk:constantConvert htmlTag="text" dataTag="arithmeticType" paramValue="${entry.arithmeticType}"
                                            emptyTip=" " sysTag="cert"/>
                    </div>
                </td>
                <td>
                    <div><c:out value="${entry.fileName}"/></div>
                </td>
                <td>
                    <div>
                        <yk:constantConvert htmlTag="text" dataTag="del_status" paramValue="${entry.status}"
                                            emptyTip="未知" sysTag="cert"/>
                    </div>
                </td>
                <td>
                    <a href="#" onclick="show('${entry.id}')">详情</a>&nbsp;
                    <a href="#" onclick="edit('${entry.id}')">修改</a>&nbsp;
                    <c:choose>
                        <c:when test="${entry.status == '0'}">
                            <a href="#" onclick="updateStatus('${entry.id}','1')">停用</a>&nbsp;
                        </c:when>
                        <c:when test="${entry.status == '1'}">
                            <a href="#" onclick="updateStatus('${entry.id}','0')">启用</a>&nbsp;
                        </c:when>
                    </c:choose>
                    <c:if test="${entry.endecryType =='0'}">
                        <a href="#" onclick="certFileDownLoad('${entry.id}')">证书下载</a>&nbsp;
                    </c:if>

                    <a href="#" onclick="updateStatus('${entry.id}','2')">删除</a>&nbsp;
                </td>
            </tr>
        </c:forEach>
    </table>
    <yk:page url="${yk}/cert/certInfo/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">
    function show(id) {
        window.open('${yk}/cert/certInfo/show.do?' + $('#pkName').val() + '=' + id, '', 'height=600,width=600,resizable=yes,location=no,scrollbars');
    }
    function edit(id) {
        window.open('${yk}/cert/certInfo/edit.do?' + $('#pkName').val() + '=' + id, '', 'height=600,width=600,resizable=yes,location=no,scrollbars');
    }

    $(function () {
        $('#btnedit').click(function () {
            var $id = $('[name=ids]:checkbox:checked');

            if ($id.length == 0) {
                alert("请选择要修改的信息！");
                return false;
            }
            window.open('${yk}/cert/certInfo/edit.do?' + $('#pkName').val() + '=' + $id.val(), '', 'height=600,width=600,resizable=yes,location=no,scrollbars');
        });

        $('#btnadd').click(function () {
            window.open('${yk}/cert/certInfo/create.do', '', 'height=600,width=600,resizable=yes,location=no,scrollbars');
        });

        $('#btndelete').click(function () {
            if ($('[name=ids]:checkbox:checked').length > 0) {
                var ids = [];
                $('[name=ids]:checkbox:checked').each(function () {
                    ids.push($(this).val())
                });
                v_updateStatus(ids,'2');
            } else {
                alert("请选择后进行删除操作！");
            }

        });

        $('#btnDownLoad').click(function () {
            if ($('[name=ids]:checkbox:checked').length > 0) {
                var ids = [];
                $('[name=ids]:checkbox:checked').each(function () {
                    ids.push($(this).val())
                });
                var url = "${yk}/cert/certInfo/propertiesFileDownLoad.do?ids="+ids.join(',');
                window.open(url);
            } else {
                alert("请选择后进行删除操作！");
            }

        });

        $('#btnclear').click(function () {
            $('#merchatCode').val('');
            $('#certCode').val('');
            $('#status').val('');
        });
    });

    function updateStatus(id,status){
        var ids = [];
        ids.push(id);
        v_updateStatus(ids,status);
    }

    function v_updateStatus(ids,status) {
        if(status == '2'){
            if(!confirm("确定要删除吗？")){
                return ;
            }
        }
        if (ids.length > 0) {
            $.post("${yk}/cert/certInfo/updateStatus.do",
                    {"items": ids.join(','),"status":status},
                    function (data) {
                        if (data == '1') {
                            alert('操作成功');
                            $('#queryForm').submit();
                        } else {
                            alert('操作失败');
                        }
                    }
            );
        }
    }
    function certFileDownLoad(id){

        var url = "${yk}/cert/certInfo/certFileDownLoad.do?id="+id;
        window.open(url);
    }
</script>
</body>
</html>


