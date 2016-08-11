<%@ page import="com.ink.monitor.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>服务监控信息</title>
  <%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
  <h2>服务监控详情</h2>
</div>
<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
  <tr>
    <td class="txtr" style="width: 188px">平台系统:</td>
    <td>
      <yk:code2name tableName="systerm_source" columnName="name" where="code=?" paramValue="${model.sysCode}" emptyTip="${model.sysCode}" serviceName="monitorDubboBaseService"/>
    </td>
  </tr>
  <tr>
    <td class="txtr" style="width: 188px">服务类型:</td>
    <td><c:out value="${model.sourceType}"/></td>
  </tr>
  <tr>
    <td class="txtr" style="width: 188px">服务名称:</td>
    <td><c:out value="${model.sourceName}"/></td>
  </tr>
  <tr>
    <td class="txtr" style="width: 188px">服务地址:</td>
    <td><c:out value="${model.sourceUrl}"/></td>
  </tr>
  <tr>
    <td class="txtr" style="width: 188px">预警阀值:</td>
    <td><c:out value="${model.warnThreshold}"/></td>
  </tr>
  <tr>
    <td class="txtr" style="width: 188px">预警频次:</td>
    <td><c:out value="${model.warnFrequency}"/></td>
  </tr>
  <tr>
    <td class="txtr" style="width: 188px">状态:</td>
    <td>
      <yk:constantConvert htmlTag="text" dataTag="status" paramValue="${model.status}" emptyTip="未知" sysTag="monitor"/>
    </td>
  </tr>
  <tr>
    <td class="txtr" style="width: 188px">最后操作时间:</td>
    <td><c:out value="${model.operateTimeString}"/></td>
  </tr>
  <tr>
    <td align="left" style="left: 20px" colspan="2">
      <ul>
        <p></p>
        <li>注：</li>
        <li>1、预警阀值，为0时无阀值限制，数值需大于0</li>
        <li>2、预警频次，频次为-1时表示不限制次数，为0时表示不报警</li>
        <li>3、当监控错误时。当阀值< 错误次数 <= (阀值 + 频次)则进行邮件/短信通知监控人员，当之后监控成功时则更改错误标志。</li>
      </ul>
    </td>
  </tr>
</table>
</body>
</html>