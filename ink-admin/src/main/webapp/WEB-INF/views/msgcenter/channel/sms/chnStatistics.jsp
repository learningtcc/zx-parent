<%@ page import="com.ink.msgcenter.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>短信通道统计</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
<script type="text/javascript" charset="utf-8" src="${yk}/gen-commons/circliful/js/jquery.circliful.min.js"></script>
<link href="${yk}/gen-commons/circliful/css/jquery.circliful.css" rel="stylesheet" type="text/css" />
<link href="${yk}/gen-commons/circliful/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
<table width="98%" class="table00" style="border: 1px solid;">
		<tr>
			<th colspan="4"><span style="font-size: 20px;">${smsChannel.name }(${smsChannel.chnCode })</span></th>
		</tr>
		<tr style="border: 1px solid;">
		<td width="30%" align="center" style="border: 1px solid;">
			<table style="padding-left: 30px;">
				<tr><td style="border-bottom: 1px solid;"><span style="font-size: 23px;"><fmt:formatDate value="${date}"></fmt:formatDate></span></td></tr>
				<tr>
					<td style="border-bottom: 1px solid;"><span style="font-size: 20px;">今日成功:${dayEma.successCount }</span></td>
				</tr>
				<tr>
					<td style="border-bottom: 1px solid;"><span style="font-size: 20px;">今日失败:${dayEma.failCount }</span></td>
				</tr>
				<tr>
					<td style="border-bottom: 1px solid;"><span style="font-size: 20px;">今日送达:${dayEma.deliveCount }</span></td>
				</tr>
				<tr>
					<td style="border-bottom: 1px solid;"><span style="font-size: 20px;">当日总数:${dayEma.sendCount }</span></td>
				</tr>
				<tr style="height: 35px;"><td>&nbsp;</td></tr>
			</table>
		</td>
		<td align="center">
			<div style="padding-top: 5px;">
			<div id="myStat1" data-dimension="100" data-text="${daySuccess }%" data-info="成功" data-width="7" data-fontsize="15" data-percent="${daySuccess }" data-fgcolor="#00DB00" data-bgcolor="#eee"></div>
			</div>
		</td>
		<td align="center">
			<div id="myStat2" data-dimension="100" data-text="${dayFail }%" data-info="失败" data-width="7" data-fontsize="15" data-percent="${dayFail }" data-fgcolor="#FF0000" data-bgcolor="#eee"></div>
		</td>
		<td align="center">
			<div id="myStat3" data-dimension="100" data-text="${dayDelive }%" data-info="送达" data-width="7" data-fontsize="15" data-percent="${dayDelive }" data-fgcolor="#F80000" data-bgcolor="#eee"></div>
		</td>
		</tr>
	</table>
	
	<table width="98%" style="border: 1px solid;padding-top: 10px;" cellspacing="0" cellpadding="0" class="table00">
		<tr  style="border: 1px solid;">
		<td width="30%" align="center"  style="border: 1px solid;">
			<table style="padding-left: 30px;">
				<tr><td style="border-bottom: 1px solid;"><span style="font-size: 23px;">历史</span></td></tr>
				<tr>
					<td style="border-bottom: 1px solid;"><span style="font-size: 20px;">历史成功:${liema.success }</span></td>
				</tr>
				<tr>
					<td style="border-bottom: 1px solid;"><span style="font-size: 20px;">历史失败:${liema.fail }</span></td>
				</tr>
				<tr>
					<td style="border-bottom: 1px solid;"><span style="font-size: 20px;">历史送达:${liema.delive }</span></td>
				</tr>
				<tr>
					<td style="border-bottom: 1px solid;"><span style="font-size: 20px;">历史总数:${liema.total }</span></td>
				</tr>
				<tr style="height: 35px;"><td>&nbsp;</td></tr>
			</table>
		</td>
		<td align="center">
			<div id="myStat4" data-dimension="100" data-text="${success }%" data-info="成功" data-width="7" data-fontsize="15" data-percent="${success }" data-fgcolor="#00DB00" data-bgcolor="#eee"></div>
		</td>
		<td align="center">
			<div id="myStat5" data-dimension="100" data-text="${fail }%" data-info="失败" data-width="7" data-fontsize="15" data-percent="${fail }" data-fgcolor="#FF0000" data-bgcolor="#eee"></div>
		</td>
		<td align="center">
			<div id="myStat6" data-dimension="100" data-text="${delive }%" data-info="送达" data-width="7" data-fontsize="15" data-percent="${delive }" data-fgcolor="#F80000" data-bgcolor="#eee"></div>
		</td>
		</tr>
	</table>
</body>
<script type="text/javascript">
//圆形统计图
$( document ).ready(function() {
	$('#myStat1').circliful();
	$('#myStat2').circliful();
	$('#myStat3').circliful();
	$('#myStat4').circliful();
	$('#myStat5').circliful();
	$('#myStat6').circliful();
});
</script>
</html>


