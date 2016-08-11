<%@ page import="com.ink.monitor.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title><%=MonitorServiceRecord.TABLE_ALIAS%>编辑</title>
  <%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
  <h2>修改<%=MonitorServiceRecord.TABLE_ALIAS%></h2>
</div>
<form id="form1" action="${yk}/MonitorServiceRecord/update.do" method="post">

  <table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
    <input type="hidden" id="id" name="id" value="<c:out value="${model.id}"/>"/>


    <tr>
      <td class="txtr" style="width: 188px">
        serviceId:
      </td>
      <td>
        <input id="serviceId" name="serviceId" value="<c:out value="${model.serviceId}"/>" type="text" class="input04"  required="true" />
        <span class="required">*</span>
      </td>
    </tr>

    <tr>
      <td class="txtr" style="width: 188px">
        平台系统编号:
      </td>
      <td>
        <input id="sysCode" name="sysCode" value="<c:out value="${model.sysCode}"/>" type="text" class="input04"   />

      </td>
    </tr>

    <tr>
      <td class="txtr" style="width: 188px">
        资源路径:
      </td>
      <td>
        <input id="sourceUrl" name="sourceUrl" value="<c:out value="${model.sourceUrl}"/>" type="text" class="input04"   />

      </td>
    </tr>

    <tr>
      <td class="txtr" style="width: 188px">
        访问状态:
      </td>
      <td>
        <input id="visitStatus" name="visitStatus" value="<c:out value="${model.visitStatus}"/>" type="text" class="input04"   />

      </td>
    </tr>

    <tr>
      <td class="txtr" style="width: 188px">
        错误描述:
      </td>
      <td>
        <input id="errorDesc" name="errorDesc" value="<c:out value="${model.errorDesc}"/>" type="text" class="input04"   />

      </td>
    </tr>

    <tr>
      <td class="txtr" style="width: 188px">
        第一次发现问题时间:
      </td>
      <td>
        <input onClick="WdatePicker()" id="firstErrorTimeString" value="<c:out value="${model.firstErrorTimeString}"/>" name="firstErrorTimeString" size="10"  readonly="readonly"/>

      </td>
    </tr>

    <tr>
      <td class="txtr" style="width: 188px">
        错误次数:
      </td>
      <td>
        <input id="errorCount" name="errorCount" value="<c:out value="${model.errorCount}"/>" type="text" class="input04"   />

      </td>
    </tr>

    <tr>
      <td class="txtr" style="width: 188px">
        状态0未解决1已解决:
      </td>
      <td>
        <input id="status" name="status" value="<c:out value="${model.status}"/>" type="text" class="input04"   />

      </td>
    </tr>

    <tr>
      <td class="txtr" style="width: 188px">
        问题解决时间:
      </td>
      <td>
        <input onClick="WdatePicker()" id="safeTimeString" value="<c:out value="${model.safeTimeString}"/>" name="safeTimeString" size="10"  readonly="readonly"/>

      </td>
    </tr>

    <tr>
      <td class="txtr" style="width: 188px">
        最后操作时间:
      </td>
      <td>
        <input onClick="WdatePicker()" id="operateTimeString" value="<c:out value="${model.operateTimeString}"/>" name="operateTimeString" size="10"  readonly="readonly"/>

      </td>
    </tr>
    <tr>
      <td class="txtc" colspan="2">
        <input type="button" class="btn01"  value="提交" onclick="$('#form1').submit();"/>
        <input type="button" class="btn01"  value="取消" onclick="window.close();"/>
        <input type="reset" class="btn01"  value="重置"/>
      </td>
    </tr>
  </table>
</form>

</body>
<script type="text/javascript">
  $(function(){
    var options = {
      beforeSubmit:   validateForm,   // pre-submit callback 表单提交前被调用的回调函数
      success:        callBack   // post-submit callback   表单提交成功后被调用的回调函数
    };
    $('#form1').ajaxForm(options);
  });

  window.onbeforeunload=function(){
    window.opener.document.queryForm.submit();
  }

  function validateForm(){
    return true;
  }
  function callBack(data){
    if(data==1)	{
      alert("保存成功！");
      window.close();
    }else{
      alert("保存失败！");
    }
  }
</script>
</html>