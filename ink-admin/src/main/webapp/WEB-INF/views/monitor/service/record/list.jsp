<%@ page import="com.ink.monitor.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>服务监控日志记录</title>
  <%@ include file="/gen-commons/taglibs.jsp" %>
  <script type="text/javascript" src="${yk}/static/common/maintain.js"></script>
</head>
<body>
<div class="title01">
  <h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/monitor/service/record/list.do"/>" method="post" style="display: inline;">
  <input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
  <input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
  <input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
    <tr>

      <td class="txtr" style="width: 100px">平台系统：</td>
      <td>
        &nbsp;
        <select id="sysCode" name="sysCode" class="select02">

        </select>
      </td>
      <td class="txtr" style="width: 100px">状态：</td>
      <td>
        <select id="status" name="status">
          <option value="">请选择...</option>
          <option value="0">未解决</option>
          <option value="1">已解决</option>
          <option value="2">过期未处理</option>
        </select>
      </td>
      <td class="txtr" style="width: 100px">第一次发现问题时间：</td>
      <td>
        &nbsp;<input onClick="WdatePicker()" value="<c:out value="${firstErrorTimeBegin}"/>" id="firstErrorTimeBegin" name="firstErrorTimeBegin" size="10"/>
        <input onClick="WdatePicker()" value="<c:out value="${firstErrorTimeEnd}"/>" id="firstErrorTimeEnd" name="firstErrorTimeEnd" size="10"/>
      </td>
    </tr>
  </table>
  <br/>
  <div align="center">
    <input type="submit" class="btn01" value="查 询" onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('0');"/>
    <input type="button" class="btn01" value="清 空" id="btnclear"/>
  </div>
</form>
<br></br>
<div class="title01">
  <h2>服务监控日志记录列表</h2>
</div>
<%--<div class="groupbtn">--%>
<%--<a href="javascript:void(0)" title="新建" class="btn001" id="btnadd">新增</a>--%>
<%--<a href="javascript:void(0)" title="修改" class="btn002" id="btnedit">修改</a>--%>
<%--<a href="javascript:void(0)" title="删除"  class="btn004" id="btndelete">删除</a>--%>
<%--</div>--%>
<div>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb" class="table02">
    <thead>
    <tr>
      <%--<th><input id="allChoose" type="checkbox"/></th>--%>
      <th>序号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
      <th>平台系统<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
      <th>监控服务<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
      <th>资源路径<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
      <th>访问状态<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
      <th>错误描述<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
      <th>第一次发现问题时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
      <th>错误次数<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
      <th>状态<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
      <th>问题解决时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
      <th>最后操作时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
      <%--<th>操作<div style="width: 75px; height: 1px; overflow: hidden;"></div></th>--%>
    </tr>
    </thead>
    <c:forEach var="entry" items="${page.result}" varStatus="v">
      <tr>
          <%--<td><input name="ids" type="checkbox" value="${entry.id}"/>--%>
          <%--<input name="pkName" type="hidden" id="pkName" value="id"/>--%>
          <%--</td>--%>
        <td>${v.index + 1 }</td>
        <td>
          <div>
            <yk:code2name tableName="systerm_source" columnName="name" where="code=?" paramValue="${entry.sysCode}" emptyTip="${entry.sysCode}" serviceName="monitorDubboBaseService"/>
          </div>
        </td>
        <td>
          <div>
            <yk:code2name tableName="monitor_service" columnName="sourceName" where="id=?" paramValue="${entry.serviceId}" emptyTip="${entry.serviceId}" serviceName="monitorDubboBaseService"/>
          </div>
        </td>
        <td>
          <div><c:out value="${entry.sourceUrl}"/></div>
        </td>
        <td>
          <div><c:out value="${entry.visitStatus}"/></div>
        </td>
        <td>
          <div><c:out value="${entry.errorDesc}"/></div>
        </td>
        <td>
          <div><c:out value="${entry.firstErrorTimeString}"/></div>
        </td>
        <td>
          <div><c:out value="${entry.errorCount}"/></div>
        </td>
        <td>
          <div>
            <c:choose><c:when test="${entry.status==0}">未解决</c:when>
              <c:when test="${entry.status==1}">已解决</c:when>
              <c:when test="${entry.status==2}">过期未处理</c:when>
              <c:otherwise>未知</c:otherwise></c:choose>
          </div>
        </td>
        <td>
          <div><c:out value="${entry.safeTimeString}"/></div>
        </td>
        <td>
          <div><c:out value="${entry.operateTimeString}"/></div>
        </td>
          <%--<td>--%>
          <%--<a href="#" onclick="show('${entry.id}')">详情</a>&nbsp;--%>
          <%--<a href="#" onclick="edit('${entry.id}')">修改</a>&nbsp;--%>
          <%--<a href="#" onclick="del('${entry.id}')">删除</a>&nbsp;--%>
          <%--</td>--%>
      </tr>
    </c:forEach>
  </table>
  <yk:page url="${yk}/monitor/service/record/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">


  $(function(){

    $("#status").val(<c:out value="${status}"/>);
    //ajax获取平台系统
    var systemUrl = '${yk}/monitor/dict/source/listForAjax.do';
    executeAjax(systemUrl,"sysCode",'<c:out value="${sysCode}"/>',true);
    $('#btnclear').click(function(){
      $('#sysCode').val('');
      $('#status').val('');
      $('#firstErrorTimeBegin').val('');
      $('#firstErrorTimeEnd').val('');
    });

  });


</script>
</body>
</html>


