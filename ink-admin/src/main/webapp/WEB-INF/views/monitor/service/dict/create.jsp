<%@ page import="com.ink.monitor.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>业务监控新增</title>
  <%@ include file="/gen-commons/taglibs.jsp" %>
  <script type="text/javascript" src="${yk}/static/common/maintain.js"></script>
</head>
<body>
<div class="title01">
  <h2>新增业务监控</h2>
</div>
<form id="form1" action="${yk}/monitor/service/dict/save.do" method="post">

  <table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
    <input type="hidden" id="id" name="id" value="<c:out value="${model.id}"/>"/>


    <tr>
      <td class="txtr" style="width: 188px">
        平台系统<label style="color: red">*</label>：
      </td>
      <td>
        <select id="sysCode" name="sysCode" class="select02">

        </select>
      </td>
    </tr>

    <tr>
      <td class="txtr" style="width: 188px">
        服务类型：
      </td>
      <td>
        <select id="sourceType" name="sourceType">
          <option value="http">Http</option>
        </select>
      </td>
    </tr>
    <tr>
      <td class="txtr" style="width: 188px">
        服务名称<label style="color: red">*</label>：
      </td>
      <td>
        <input id="sourceName" name="sourceName" type="text" class="input04"  required="true" />
      </td>
    </tr>
    <tr>
      <td class="txtr" style="width: 188px">
        服务地址<label style="color: red">*</label>：
      </td>
      <td>
        <input id="sourceUrl" name="sourceUrl" type="text" class="input04"  required="true" />
      </td>
    </tr>

    <tr>
      <td class="txtr" style="width: 188px">
        预警阀值<label style="color: red">*</label>：
      </td>
      <td>
        <input id="warnThreshold" name="warnThreshold" type="text" class="input04"   />

      </td>
    </tr>

    <tr>
      <td class="txtr" style="width: 188px">
        预警频次<label style="color: red">*</label>：
      </td>
      <td>
        <input id="warnFrequency" name="warnFrequency" type="text" class="input04"   />

      </td>
    </tr>
    <tr>
      <td class="txtr" style="width: 188px">
        状态<label style="color: red">*</label>:
      </td>
      <td>
        <yk:constantConvert htmlTag="select" dataTag="status" name="status" paramValue="0" sysTag="monitor"/>
      </td>
    </tr>
    <tr>
      <td class="txtc" colspan="2">
        <input type="button" class="btn01" id="subSave" value="提交" />
        <input type="button" class="btn01"  value="取消" onclick="window.close();"/>
        <input type="reset" class="btn01"  value="重置"/>
      </td>
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
</form>

</body>
<script type="text/javascript">
  $(function(){
    var options = {
      beforeSubmit:   validateForm,   // pre-submit callback 表单提交前被调用的回调函数
      success:        callBack   // post-submit callback   表单提交成功后被调用的回调函数
    };
    $('#form1').ajaxForm(options);

    //ajax获取系统代码
    var systemUrl = '${yk}/monitor/dict/source/listForAjax.do';
    executeAjax(systemUrl,"sysCode");

    $("#subSave").click(function(){
      if(matchFrom("sysCode",/^\w+$/,"平台系统不能为空")
              && validateLengthFrom("sourceName",1,"资源名称不能为空")
              && validateLengthFrom("sourceUrl",1,"资源路径不能为空")
              && matchFrom("warnThreshold",/^\d+$/,"预警阀值不能为空且必须是数值")
              && matchFrom("warnFrequency",/^\d+$/,"预警频次不能为空且必须是数值")
              && validateLengthFrom("status",1,"请选择状态")
      ){
        $('#form1').submit();
      }
    });
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

