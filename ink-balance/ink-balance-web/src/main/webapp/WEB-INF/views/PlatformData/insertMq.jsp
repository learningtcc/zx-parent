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
	<form id="form2" action="${yk}/PlatformData/insertMq.do" method="post">

		<table cellpadding="0" cellspacing="0" class="table00"
			style="width: 100%">

			<tr>
				<td class="txtr" style="width: 188px">支付金额：</td>
				<td><input id="amt" name="amt" type="text" class="input04" />

				</td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">平台订单号：</td>
				<td><input id="platformOrderNo" name="platformOrderNo"
					type="text" class="input04" /></td>
			</tr>

			<tr>
				<td class="txtr" style="width: 188px">支付生成时间：</td>
				<td><input type="text" id="payTime" name="payTime" size="10" />

				</td>
			</tr>


			<tr>
				<td class="txtr" style="width: 188px">交易状态 1、待支付 2、支付成功 3、支付失败：
				</td>
				<td><input id="payStatus" name="payStatus" type="text"
					class="input04" /></td>
			</tr>

			<tr>
				<td class="txtc" colspan="2"><input type="button" class="btn01"
					value="提交" onclick="$('#form2').submit();" /> <input type="button"
					class="btn01" value="取消" onclick="window.close();" /> <input
					type="reset" class="btn01" value="重置" /></td>
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
		$('#form2').ajaxForm(options);
	});

	/*window.onbeforeunload=function(){
		window.opener.document.queryForm.submit();
	}*/

	function validateForm(){
		return true;
	}
	function callBack(data){
		if(data=="SUCCESS")	{
			alert("保存成功！");
			window.close();
		}else{
			alert("保存失败！");
		}
	}
</script>
</html>

