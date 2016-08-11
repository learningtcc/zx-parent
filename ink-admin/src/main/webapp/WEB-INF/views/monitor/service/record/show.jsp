<%@ page import="com.ink.monitor.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title><%=MonitorServiceRecord.TABLE_ALIAS%>信息</title>
  <%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
  <h2><%=MonitorServiceRecord.TABLE_ALIAS%>详情</h2>
</div>
<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
  <tr>
    <td class="txtr" style="width: 188px">serviceId:</td>
    <td><c:out value="${model.serviceId}"/></td>
  </tr>
  <tr>
    <td class="txtr" style="width: 188px">平台系统编号:</td>
    <td><c:out value="${model.sysCode}"/></td>
  </tr>
  <tr>
    <td class="txtr" style="width: 188px">资源路径:</td>
    <td><c:out value="${model.sourceUrl}"/></td>
  </tr>
  <tr>
    <td class="txtr" style="width: 188px">访问状态:</td>
    <td><c:out value="${model.visitStatus}"/></td>
  </tr>
  <tr>
    <td class="txtr" style="width: 188px">错误描述:</td>
    <td><c:out value="${model.errorDesc}"/></td>
  </tr>
  <tr>
    <td class="txtr" style="width: 188px">第一次发现问题时间:</td>
    <td><c:out value="${model.firstErrorTimeString}"/></td>
  </tr>
  <tr>
    <td class="txtr" style="width: 188px">错误次数:</td>
    <td><c:out value="${model.errorCount}"/></td>
  </tr>
  <tr>
    <td class="txtr" style="width: 188px">状态0未解决1已解决:</td>
    <td><c:out value="${model.status}"/></td>
  </tr>
  <tr>
    <td class="txtr" style="width: 188px">问题解决时间:</td>
    <td><c:out value="${model.safeTimeString}"/></td>
  </tr>
  <tr>
    <td class="txtr" style="width: 188px">最后操作时间:</td>
    <td><c:out value="${model.operateTimeString}"/></td>
  </tr>
</table>
</body>
</html>