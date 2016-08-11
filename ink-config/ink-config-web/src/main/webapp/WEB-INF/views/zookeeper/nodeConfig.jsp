<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>节点配置管理</title>
    <%@ include file="/gen-commons/taglibs.jsp" %>
    <%--<link rel="stylesheet" type="text/css" href="${yk}/static/css/autocomplete.css"/>--%>
    <%--<script type="text/javascript" src="${yk}/static/config/autocomplete.js"></script>--%>
    <link rel="stylesheet" type="text/css" href="${yk}/static/css/jquery.coolautosuggest.css"/>
    <script type="text/javascript" src="${yk}/static/config/jquery.coolautosuggest.js"></script>
    <script type="text/javascript" src="${yk}/static/config/maintain.js"></script>
    <script type="text/javascript" src="${yk}/gen-commons/md5.js"></script>
</head>
<body>
<div class="title01">
    <h2>节点 </h2>
</div>
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">

    <tr>
        <td class="txtr" style="width: 100px">节点路径：</td>
        <td>
            &nbsp;<input type="text" value="" id="query" name="query"/>
            <%--<div id="search-form"></div>--%>
        </td>
        <td class="txtr" style="width: 100px">密码：</td>
        <td>
            &nbsp;<input type="password" value="" id="pwd" name="pwd"/>
        </td>
    </tr>
</table>
<br/>

<div align="center">
    <input type="button" class="btn01" value="查 询" id="queryNode"/>
    <input type="button" class="btn01" value="新 增" id="addNode"/>
</div>
<br/>

<div>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb" class="table02">
        <thead>
        <tr>
            <th>节点路径
                <div style="width: 70%; height: 1px; overflow: hidden;"></div>
            </th>
            <th>操作
                <div style="width: 30%; height: 1px; overflow: hidden;"></div>
            </th>
        </tr>
        </thead>
        <tr id="operateTr" style="display: none">
            <td>
                <input type="hidden"  value="" id="tabPath" name="tabPath"/>
                <input type="hidden"  value="" id="tabPwd" name="tabPwd"/>
                <div id="nodePathDiv"></div>
            </td>
            <td>
                <div>
                    <a href="#" onclick="configProperty()">属性配置</a>&nbsp;
                    <a href="#" onclick="showEditPwd()">修改密码</a>&nbsp;
                    <a href="#" onclick="resetPwd()">重置密码</a>&nbsp;
                    <a href="#" onclick="del()">删除</a>&nbsp;
                </div>
            </td>
        </tr>
    </table>
    <%--模态弹出窗口--%>
    <div id="fade" class="black_overlay" style="width: auto;height: auto" ></div>
    <div id="ModalDiv" class="white_content" style="width: auto;height: auto">
        <%--<iframe src="" width="100%"  height="100%" id="modalIframe"></iframe>--%>
        <div align="center">
            <table cellpadding="0" cellspacing="0" class="table00" >
                <tr>
                    <td class="txtr" >
                        旧密码:
                    </td>
                    <td>
                        <input id="oldPassword" name="oldPassword" value="" type="password" class="input04"   />
                    </td>
                </tr>
                <tr>
                    <td class="txtr" >
                        新密码:
                    </td>
                    <td>
                        <input id="newPassword" name="newPassword" value="" type="password" class="input04"   />
                    </td>
                </tr>
                <tr>
                    <td class="txtr">
                        再次输入新密码:
                    </td>
                    <td>
                        <input id="reNewPassword" name="reNewPassword" value="" type="password" class="input04"   />
                    </td>
                </tr>
            </table>
        </div>
        <div class="clearfix bbt">
            <input type="hidden" id="selectType"/>
            <a id="restore" class="fl restore" onclick="CloseDiv('ModalDiv','fade')">关　闭</a>
            <a id="determine" class="fl mglt20 determine" onclick="validateForm()">保　存</a>
        </div>
    </div>
</div>

<script type="text/javascript">
    var words = new Array();
    $(function () {
        $("#queryNode").click(function () {//查询节点
            var url = "${yk}/zookeeper/node/queryNode.do";
            requestNode(url);
        });
        $("#addNode").click(function () {//添加节点
            var url = "${yk}/zookeeper/node/addNode.do";
            requestNode(url);
        });

        var url = "${yk}/zookeeper/node/findNodes.do?nodePath=";
        $("#query").coolautosuggest({
            url:url
        });
    })

    function requestNode(url){
        var nodePath = $("#query").val();
        if(nodePath.indexOf("/") !=0
            ||(nodePath.length-1) == nodePath.lastIndexOf("/")){
            return false;
        }
        var pwd = $("#pwd").val();
        if(!(validateLengthFrom("query",1,"请输入节点路径")
                && validateLengthFrom("pwd",1,"请输入密码"))){
            return false;
        }
        url = url + "?nodePath=" + nodePath + "&pwd="+pwd;
        $.ajax( {
            type : 'POST',
            contentType : 'application/json',
            url : url,
            dataType : 'json',
            success : function(data) {
                var code = data.code;
                if("200" == code){//查询成功or添加成功
                    setTabInfo(nodePath,pwd,nodePath,true);
                }else if("300" == code){//查询失败
                    setTabInfo("","","",false);
                    alert(data.message);
                }else if("400" == code){//添加失败
                    alert(data.message);
                    $("#operateTr").show();
                }
            },
            error : function() {
                alert("系统出错，请联系管理员")
            }
        });
    }

    function configProperty(){//节点属性配置
        var tabPath = $("#tabPath").val();
        var id = faultylabs.MD5(tabPath);
        window.parent.window.iframehandadd(tabPath,"${yk}/zookeeper/node/propertyConfig.do?nodePath="+tabPath,id);
    }

    function showEditPwd(){
        ShowDiv('ModalDiv','fade');
    }

    function updatePwd(nodePath,pwd){//更新密码

        if(nodePath.length>0 && confirm("确定要修改密码吗？")){
            $.post( "${yk}/zookeeper/node/updatePwd.do", {"nodePath":nodePath,"pwd":pwd},
                    function(data){
                        if(data=="1"){
                            alert('更新成功');
                            $('#queryForm').submit();
                        }else{
                            alert('更新失败');
                        }
                    }
            );
        }
    }

    function resetPwd(){//重置密码
        $("#tabPwd").val("");
        var tabPath = $("#tabPath").val();
        var tabPwd = $("#tabPwd").val();
        updatePwd(tabPath,tabPwd);
    }

    function del(){//删除节点
        var tabPath = $("#tabPath").val();
        var tabPwd = $("#tabPwd").val();
        if(tabPath.length>0 && confirm("确定要删除吗？")){
            $.post( "${yk}/zookeeper/node/deleteNode.do", {"nodePath":tabPath,"pwd":tabPwd},
                    function(data){
                        if(data == "1"){
                            setTabInfo("","","",false);
                            alert('删除成功');
                        }else{
                            alert('删除失败');
                        }
                    }
            );
        }
    }

    function setTabInfo(nodePath,nodePwd,nodePathDiv,showFlag){
        $("#tabPath").val(nodePath);
        $("#tabPwd").val(nodePwd);
        $("#nodePathDiv").text(nodePathDiv);
        if(showFlag){
            $("#operateTr").show();
        }else{
            $("#operateTr").hide();
        }
    }


    function validateForm(){
        var oldPassword = $("#oldPassword").val();
        var newPassword = $("#newPassword").val();
        var reNewPassword = $("#reNewPassword").val();
        if(oldPassword ==''){
            alert("旧密码不能为空");
            return false;
        }
        if(newPassword ==''){
            alert("新密码不能为空");
            return false;
        }
        if(reNewPassword ==''){
            alert("请再次输入新密码");
            return false;
        }

        var tabPwd = $("#tabPwd").val();
        if(tabPwd != oldPassword){
            alert("旧密码错误，请检查");
            return false;
        }

        if(reNewPassword != newPassword){
            alert("两次输入新密码不同，请检查");
            return false;
        }
        if(oldPassword == newPassword){
            alert("新密码与旧密码不能相同，请检查");
            return false;
        }

        var tabPath = $("#tabPath").val();
        updatePwd(tabPath,newPassword);
        CloseDiv('ModalDiv','fade');
    }
</script>
</body>
</html>


