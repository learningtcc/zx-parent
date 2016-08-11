
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>导入渠道数据</title>
<%@ include file="/gen-commons/taglibs.jsp"%>
</head>
<body>
	<div class="title01">
		<h2>导入</h2>
	</div>

	<!-- <form action="" method="post" enctype="multipart/form-data"
		name="upload_form">
		<label>选择图片文件</label> <input name="imgfile" type="file"
			accept="image/gif, image/jpeg" /> <input name="upload" type="submit"
			value="上传" />
	</form> -->
	<form id="form1" action="${yk}/ChannelData/exportFile.do" method="post"
		enctype="multipart/form-data">

		<table cellpadding="0" cellspacing="0" class="table00"
			style="width: 100%">

			<tr>
				<td class="txtr" style="width: 188px">上传：</td>
				<td><input name="file" type="file" /></td>
			</tr>
			<tr>
				<td class="txtc" colspan="2"><input type="button" class="btn01"
					value="上传" onclick="$('#form1').submit();" /> <input type="button"
					class="btn01" value="取消" onclick="window.close();" /> <input
					type="reset" class="btn01" value="重置" /></td>
			</tr>
		</table>
	</form>

</body>
<script type="text/javascript">
	$(function() {
		var options = {
			beforeSubmit : validateForm, // pre-submit callback 表单提交前被调用的回调函数
			success : callBack
		// post-submit callback   表单提交成功后被调用的回调函数
		};
		$('#form1').ajaxForm(options);
	});

	window.onbeforeunload = function() {
		window.opener.document.queryForm.submit();
	}

	function validateForm() {
		return true;
	}
	function callBack(data) {
		if (data == "上传成功") {
			alert(data);
			window.close();
		} else {
			alert(data);
		}
	}
</script>
</html>

