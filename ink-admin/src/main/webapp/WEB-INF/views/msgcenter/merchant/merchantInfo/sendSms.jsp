<%@ page import="com.ink.msgcenter.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>发送短信</title>
    <%@ include file="/gen-commons/taglibs.jsp" %>
    <script type="text/javascript" src="${yk}/static/common/maintain.js"></script>
</head>
<body>
<div class="title01">
    <h2>发送短信</h2>
</div>
<form id="form1" action="${yk}/msgcenter/merchant/merchantInfo/sendSms.do" method="post">

    <table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
        <tr>
            <td class="txtr" style="width: 188px">
                发送类型:
            </td>
            <td>
                <select id="sendFlag" name="sendFlag">
                    <option value="1">单发短信</option>
                    <option value="2">单发短信(含扩展)</option>
                    <option value="3">群发短信</option>
                </select>
            </td>
        </tr>
        <tr>
            <td class="txtr" style="width: 188px">
                商户名称<label style="color: red">*</label>:
            </td>
            <td>
                <yk:select codeName="name" table="merchant_info" codeValue="sn" id="merctCode" name="merctCode"
                           select="${merctId }" where=" status != 2 " serviceName="msgcenterDubboBaseService"/>
            </td>
        </tr>

        <tr>
            <td class="txtr" style="width: 188px">
                短信模版<label style="color: red">*</label>:
            </td>
            <td>
                <select id="tempId" name="tempId"></select>
            </td>
        </tr>
        <tr>
            <td class="txtr" style="width: 188px">
                通道代码:
            </td>
            <td>
                <input id="chnCode" name="chnCode" type="text" class="input04"/>
            </td>
        </tr>
        <tr>
        <tr>
            <td class="txtr" style="width: 188px">
                业务单号:
            </td>
            <td>
                <input id="bizId" name="bizId" type="text" class="input04"/>
            </td>
        </tr>
        <tr>
            <td class="txtr" style="width: 188px">
                手机号码<label style="color: red">*</label>:
            </td>
            <td>
                <input id="mobile" name="mobile" type="text" class="input04"/>
            </td>
        </tr>

        <tr>
            <td class="txtr" style="width: 188px">
                发送时间:
            </td>
            <td>
                <input onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="" id="sendTime" name="sendTime"/>
            </td>
        </tr>
        <tr id="extSend1" style="display: none">
            <td class="txtr" style="width: 188px">
                扩展号<label style="color: red">*</label>:
            </td>
            <td>
                <input id="extNo" name="extNo" type="text" class="input04"/>
            </td>
        </tr>
        <tr id="extSend2" style="display: none">
            <td class="txtr" style="width: 188px">
                扩展信息:
            </td>
            <td>
                <input id="extInfo" name="extInfo" type="text" class="input04"/>
            </td>
        </tr>
        <tr id="extSend3" style="display: none">
            <td class="txtr" style="width: 188px">
                上行Url:
            </td>
            <td>
                <input id="upUrl" name="upUrl" type="text" class="input04"/>
            </td>
        </tr>
        <tr>
            <td class="txtr" style="width: 188px">
                状态报告URL:
            </td>
            <td>
                <input id="reportUrl" name="reportUrl" type="text" class="input04"/>
            </td>
        </tr>
        <tr>
            <td class="txtr" style="width: 188px">
                参数:
            </td>
            <td>
                <textarea rows="5" cols="40" id="paramJson" name="paramJson"></textarea>
            </td>
        </tr>
        <tr>
            <td class="txtc" colspan="2">
                <input type="button" class="btn01" value="预览消息" onclick="showMessage()"/>
                <input type="button" class="btn01" id="subSave" value="提交"/>
                <input type="button" class="btn01" value="取消" onclick="window.close();"/>
                <input type="reset" class="btn01" value="重置"/>
            </td>
        </tr>
    </table>
</form>
<%--模态弹出窗口--%>
<div id="fade" class="black_overlay"></div>
<div id="ModalDiv" class="white_content" style="overflow: scroll">
    <div id="showMessage"></div>
    <div class="clearfix bbt">
        <a id="restore" class="fl restore" onclick="CloseDiv('ModalDiv','fade')">关　闭</a>
    </div>
</div>
</body>
<script type="text/javascript">

    $(function () {
        $("#merctCode").change(function () {
            var optionValue = this.value;
            if (optionValue == "") {
                $("#tempId").empty();
                return;
            }

            var moduleUrl = '${yk}/msgcenter/SmsTemplate/listForMerctCode.do?merctCode=' + optionValue;
            executeAjax(moduleUrl, "tempId");
        });

        $("#sendFlag").change(function () {
            if (this.value == '2') {
                $("tr[id^='extSend']").show();
            } else {
                $("tr[id^='extSend']").hide();
            }
        });

        $("#subSave").click(function () {
            if (validateLengthFrom("merctCode", 1, "请选择商户")
                    && validateLengthFrom("tempId", 1, "请选择短信模版")
                    && validateLengthFrom("mobile", 11, "请输入手机号码")
            ) {
                var sendFlag = $("#sendFlag").val();
                if (sendFlag == '2') {
                    if (validateLengthFrom("extNo", 1, "请输入扩展号")) {
                        sendSms();
                    }
                } else {
                        sendSms();
                }
            }
        });

        $("#tempId").change(function () {
            var tempId = $(this).val();
            if (tempId.length < 1) {
                $("#showMessage").empty();
                $("#paramJson").val("");
                return false;
            }
            var url = '${yk}/msgcenter/SmsTemplate/findEmailTemplate.do?id=' + $(this).val();
            $.ajax({
                type: 'GET',
                contentType: 'application/json',
                url: url,
                dataType: 'json',
                success: function (data) {
                    $("#paramJson").val(data.tempParam);
                    $("#showMessage").empty();
                    $("#showMessage").append(data.tempContent);
                },
                error: function () {
                    alert("系统出错，请联系管理员")
                }
            });
        });
    });

    function sendSms(){
        var url = $("#form1").attr("action");
        var sendFlag = $("#sendFlag").val();
        var merctCode = $("#merctCode").val();
        var tempId = $("#tempId").val();
        var bizId = $("#bizId").val();
        var mobile = $("#mobile").val();
        var sendTime = $("#sendTime").val();
        var extNo = $("#extNo").val();
        var extInfo = $("#extInfo").val();
        var paramJson = $("#paramJson").val();
        var chnCode = $("#chnCode").val();
        var upUrl = $("#upUrl").val();
        var reportUrl = $("#reportUrl").val();
        url += "?merctCode="+merctCode +"&tempId="+tempId+"&bizId="+bizId+"&extInfo="+extInfo+"&sendFlag="+sendFlag;
        url += "&mobile="+mobile + "&sendTime="+sendTime +"&extNo="+extNo +"&paramJson="+paramJson;
        url += "&chnCode="+chnCode + "&upUrl="+upUrl +"&reportUrl="+reportUrl;
        $.ajax( {
            type : 'POST',
            contentType : 'application/json',
            url : url,
            dataType : 'json',
            success : function(data) {
                if(data.retCode != "RT0000"){
                    alert("发送失败，msgId:"+data.msgId+"错误信息：" + data.retMsg);
                }else{
                    alert("发送成功");
                }
            },
            error : function() {
                alert("系统出错，请联系管理员")
            }
        });
    }

    function showMessage(){

        var tempId = $("#tempId").val();
        var merctCode = $("#merctCode").val();
        var showMessage = $("#showMessage");
        if(merctCode.length <1 || tempId.length<1){
            alert("请选选择模版");
            return false;
        }
        var paramJson = $("#paramJson").val();

        var url =   '${yk}/msgcenter/SmsTemplate/findTemplateText.do?tempParam='+paramJson+'&id='+tempId;
        $.ajax( {
            type : 'POST',
            contentType : 'application/json',
            url : url,
            dataType : 'json',
            success : function(data) {
                showMessage.html("");
                showMessage.append(data.tempContent);
                ShowDiv('ModalDiv','fade');
            },
            error : function() {
                alert("系统出错，请联系管理员")
            }
        });
    }
</script>
</html>