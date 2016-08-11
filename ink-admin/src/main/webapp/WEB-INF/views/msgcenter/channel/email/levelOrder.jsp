<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <%@ include file="/gen-commons/taglibs.jsp" %>
  <link rel="stylesheet" href="${yk}/static/ztree/style/zTreeStyle.css" type="text/css"></link>
  <link rel="stylesheet" href="${yk}/static/ztree/style/zTreeDemo.css" type="text/css">
  <%--<script type="text/javascript" src="${yk}/static/ztree/jquery.ztree.all-3.1.js"></script>--%>
  <%--<script type="text/javascript" src="${yk}/static/ztree/js/jquery-1.4.4.min.js"></script>--%>
  <script type="text/javascript" src="${yk}/static/ztree/js/jquery.ztree.core.js"></script>
  <script type="text/javascript" src="${yk}/static/ztree/js/jquery.ztree.excheck.js"></script>
  <script type="text/javascript" src="${yk}/static/ztree/js/jquery.ztree.exedit.js"></script>
  <title>邮件通道优先级排序</title>

  <SCRIPT type="text/javascript">
    <!--
    var setting = {
      edit: {
        drag: {
          autoExpandTrigger: true,
          prev: dropPrev,
          inner: dropInner,
          next: dropNext
        },
        enable: true,
        showRemoveBtn: false,
        showRenameBtn: false
      },
      data: {
        simpleData: {
          enable: true,
          idKey: "id",
          pIdKey: "pid"
        }
      },
      callback: {
        beforeDrag: beforeDrag
      }
    };

    function dropPrev(treeId, nodes, targetNode) {
      var pNode = targetNode.getParentNode();
      if (pNode && pNode.dropInner === false) {
        return false;
      } else {
        for (var i=0,l=curDragNodes.length; i<l; i++) {
          var curPNode = curDragNodes[i].getParentNode();
          if (curPNode && curPNode !== targetNode.getParentNode() && curPNode.childOuter === false) {
            return false;
          }
        }
      }
      return true;
    }
    function dropInner(treeId, nodes, targetNode) {
      if (targetNode && targetNode.dropInner === false) {
        return false;
      } else {
        for (var i=0,l=curDragNodes.length; i<l; i++) {
          if (!targetNode && curDragNodes[i].dropRoot === false) {
            return false;
          } else if (curDragNodes[i].parentTId && curDragNodes[i].getParentNode() !== targetNode && curDragNodes[i].getParentNode().childOuter === false) {
            return false;
          }
        }
      }
      return true;
    }
    function dropNext(treeId, nodes, targetNode) {
      var pNode = targetNode.getParentNode();
      if (pNode && pNode.dropInner === false) {
        return false;
      } else {
        for (var i=0,l=curDragNodes.length; i<l; i++) {
          var curPNode = curDragNodes[i].getParentNode();
          if (curPNode && curPNode !== targetNode.getParentNode() && curPNode.childOuter === false) {
            return false;
          }
        }
      }
      return true;
    }

    var  className = "dark", curDragNodes, autoExpandNode;
    function beforeDrag(treeId, treeNodes) {
      className = (className === "dark" ? "":"dark");
      for (var i=0,l=treeNodes.length; i<l; i++) {
        if (treeNodes[i].drag === false) {
          curDragNodes = null;
          return false;
        } else if (treeNodes[i].parentTId && treeNodes[i].getParentNode().childDrag === false) {
          curDragNodes = null;
          return false;
        }
      }
      curDragNodes = treeNodes;
      return true;
    }

    $(document).ready(function(){
      var options = {
        beforeSubmit:   validateForm,
        success:        callBack
      };
      $('#form1').ajaxForm(options);
      $.post("${yk}/msgcenter/channel/email/findMonitorServiceTree.do",function(data){
        if(data != null){
          var zNodes = eval(data);
          $.fn.zTree.init($("#treeDemo"), setting, zNodes);

        }
      });

      $("#subSave").click(function(){
        var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
        var nodes=treeObj.getNodes()[0].children;
        var channelLevel = "";
        for(var i=0;i < nodes.length;i++){
          var id = nodes[i].id;
//          var level = nodes[i].level;
          channelLevel += ";" + id + "," + (i+1);
        }

        $("#channelLevel").val(channelLevel);
        $('#form1').submit();
      });
    });

    window.onbeforeunload=function(){
      window.opener.document.queryForm.submit();
    };

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
    //-->
  </SCRIPT>

</head>

<body style="background-color: #eef9ff; margin-left:100px; margin-top:0px">
<form action="${yk}/msgcenter/channel/email/saveLevelOrder.do" id="form1" name="form1" method="post">
  <input type="hidden" id="channelLevel" name="channelLevel"/>
  <table cellpadding="0" align="center" cellspacing="0" class="table00" style="width: 100%">
    <tr>
      <td>
        <h2>邮件通道优先级调整</h2>
      </td>
    </tr>
    <tr>
      <td   align="center">
        <div class="content_wrap">
          <div class="zTreeDemoBackground left">
            <ul id="treeDemo" class="ztree"></ul>
          </div>
        </div>
      </td>
    </tr>
    <tr>
      <td align="center">
        <input type="button" class="btn01" id="subSave" value="提交" />
        <input type="button" class="btn01"  value="取消" onclick="window.close();"/>
      </td>
    </tr>
    <tr>
      <td>
        注：上下拖动树节点即可对优先级进行排序
      </td>
    </tr>
  </table>

</form>

</body>
</html>

