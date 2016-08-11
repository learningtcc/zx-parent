<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新增</title>
<%@ include file="/gen-commons/taglibs.jsp"%>
</head>
<body>
	<div class="title01">
		<h2>新增</h2>
	</div>
	<form id="form1" action="${yk}/CheckBalance/save.do" method="post">
		<table cellpadding="0" cellspacing="0" class="table00"
			style="width: 100%">
			<input type="hidden" id="id" name="id"
				value="<c:out value="${model.id}"/>" />
			<tr>
				<td class="txtr" style="width: 188px">渠道编号：</td>
				<td><select class="easyui-combobox" id="channelNo"
					style="width: 100px" name="channelNo">
						<option value="">请选择</option>
						<c:forEach var="entry" items="${channelList}" varStatus="v">
							<option value="${entry.no}">${entry.name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td class="txtr" style="width: 188px">平台订单号：</td>
				<td><input id="platformOrderNo" name="platformOrderNo"
					type="text" style="width: 200px" class="input04" /></td>
			</tr>
			<tr>
				<td class="txtr" style="width: 188px">调账对象：</td>
				<td><select id="balanceSource" style="width: 100px"
					name="balanceSource">
						<option value="0">请选择</option>
						<option value="1">渠道</option>
						<option value="2">平台</option>
				</select></td>
			</tr>
			<tr>
				<td class="txtr" style="width: 188px">调账方向：</td>
				<td><select class="easyui-combobox" style="width: 100px"
					id="balanceDirection" name="balanceDirection">
						<option value="0">请选择</option>
						<option value="1">正</option>
						<option value="2">负</option>
				</select></td>
			</tr>
			<tr>
				<td class="txtr" style="width: 188px">调账金额：</td>
				<td><input id="balanceAmt" name="balanceAmt"
					style="width: 200px" type="text" class="input04" /></td>
			</tr>
			<tr>
				<td class="txtr" style="width: 188px">调账说明：</td>
				<td><input id="balanceNote" name="balanceNote"
					style="width: 200px" type="text" class="input04" /></td>
			</tr>
			<tr>
				<td class="txtc" colspan="2"><input type="button" class="btn01"
					value="提交" onclick="$('#form1').submit();" /> <input type="button"
					class="btn01" value="取消" onclick="window.close();" /> <input
					type="reset" class="btn01" value="重置" /></td>
			</tr>
		</table>
	</form>

</body>
<script src="${yk}/gen-commons/jquery.validate.js"></script>
<script type="text/javascript">
$(function(){
    $("#form1").validate({
        rules:{
        	channelNo:{
        		required:true
            },
            platformOrderNo:{
                required:true
            },
            balanceSource:{
        		min:1
            },
            balanceDirection:{
        		min:1
            },     
            balanceAmt:{
                required:true,
                number:true    
            },
            balanceNote:{
                required:true
            }
        },
        messages:{
        	channelNo:{
        		required:'<span style="color: red;">请选择渠道编号</span>'
            },
            platformOrderNo:{
                required:'<span style="color: red;">请输入平台订单号</span>'
            },
            balanceSource:{
            	min:'<span style="color: red;">请选择调账对象</span>'
            },
            balanceDirection:{
            	min:'<span style="color: red;">请选择调账方向</span>'
            },
            balanceAmt:{
                required:'<span style="color: red;">请输入调账金额</span>',
                number:'<span style="color: red;">请输入正确的金额</span>' 
            },
            balanceNote:{
                required:'<span style="color: red;">请输入调账说明</span>'
            }
        }
                  
    });    

});
    $(function () {
        var options = {
            beforeSubmit: validateForm,   // pre-submit callback 表单提交前被调用的回调函数
            success: callBack   // post-submit callback   表单提交成功后被调用的回调函数
        };
        $('#form1').ajaxForm(options);
    });

    window.onbeforeunload = function () {
        window.opener.document.queryForm.submit();
    }
    function validateForm() {
    	if($("#form1").valid()){
    		return true;
    	 }
    	 return false;
    }
    function callBack(data) {
        if (data == 'BALANCE_SUCCESS') {
            alert("调账成功！");
            parent.location.reload();
            window.close();
        }else if(data == 'DATA_IS_EMPTY') {
            alert("未检索可调整的差异数据记录!");
        }else if(data == 'PAY_STATUS_ERROR') {
            alert("支付状态为失败，请直接在差异表中挂账！");
        }else if(data == 'DATA_BALANCE_ERROR') {
            alert("调账数据填写错误，请核对！");
        }else if(data == 'DATA_IS_EXCEPTION') {
            alert("数据异常，请联系技术人员！");
        } else {
            alert("保存失败！");
        }
    }
</script>
</html>

