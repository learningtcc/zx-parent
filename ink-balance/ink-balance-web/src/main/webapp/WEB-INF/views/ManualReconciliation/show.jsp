<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>维护</title>
<%@ include file="/gen-commons/taglibs.jsp"%>
<style>
.loadingdiv {
	position: absolute;
	left: 0;
	top: 0;
	background: #F1F1F1;
	display: none;
	filter: alpha(opacity = 35);
	opacity: 0.5;
	font-weight: bold;
	color: Red;
	width: 100%;
	height: 4000px;
	z-index: 3333;
	font-size: 14px;
}

.loadingdiv .child {
	position: absolute;
	visibility: visible;
	z-index: 3332;
	width: 100%;
	text-align: center;
	margin-top: 250px;
}
</style>
</head>
<body>
	<div class="title01 title01_x">
		<h2>
			搜索<span class="zksq">
				<!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> -->
			</span>
		</h2>
	</div>
	<form id="queryForm" name="queryForm"
		action="<c:url value="/CheckBalance/list.do"/>" method="post"
		style="display: inline;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="table00">
			<tr>



				<td class="txtr" style="width: 100px">渠道：</td>
				<td><select class="easyui-combobox" name="channelNo"
					id="channelNo">
						<option value="">请选择</option>
						<c:forEach var="entry" items="${channelList}" varStatus="v">
							<option value="${entry.no}"
								<c:if test="${entry.no eq channelNo}">selected</c:if>>${entry.name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td class="txtr" style="width: 100px">商户编号：</td>
				<td><select name="channelMerchantNo" id="channelMerchantNo">
						<option value="">请选择</option>
						<option value="CF2000027305"
							<c:if test="${'CF2000027305' eq channelMerchantNo}">selected</c:if>>民生-简理财</option>
						<option value="686062"
							<c:if test="${'686062' eq channelMerchantNo}">selected</c:if>>宝付-简理财</option>
						<option value="YKRE0001">银客-懒猫01</option>
						<option value="YKRE0002">银客-懒猫02</option>
						<option value="YKRE0003">银客-懒猫03</option>
						<option value="YKRE0004">银客-懒猫04</option>
				</select></td>
				<td>
					<div align="center">
						<input type="button" class="btn01" value="对账"
							onclick="reconciliation()" />
					</div>
				</td>
			</tr>
			<tr>
				<td class="txtr" style="width: 100px">交易日期：</td>
				<td><input onClick="WdatePicker()"
					value="<c:out value="${createBeginDate}"/>" id="TradeDate"
					name="TradeDate" size="10" readonly="readonly" /></td>
			</tr>

		</table>
		<br />
		<div id="loadingdiv" class="loadingdiv">
			<center>
				<div class="child">
					<img src="${yk}/static/ztree/style/img/loading2.gif"
						style="width: 160px" />
				</div>
			</center>
		</div>
	</form>
	<br></br>


	<script type="text/javascript">
function validate(){
	var channelNo = $("#channelNo").val();
	var channelMerchantNo = $("#channelMerchantNo").val();
	var TradeDate = $("#TradeDate").val();
	if(channelNo==null||channelNo==''){
		return false;
	}
	if(channelMerchantNo==null||channelMerchantNo==''){
		return false;
	}
	if(TradeDate==null||TradeDate==''){
		return false;
	}
	return true;
}
    function reconciliation() {
    	if(validate()){
	    	if(confirm("确定要手工对账吗？"))
	    	 {
	    			$("#loadingdiv").show();
			        var params = $("#queryForm").serializeObject();
			        $.ajax({
			            url: '${yk}/ManualReconciliation/check.do',    //请求的url地址
			            data: params,
			            dataType: "json",   //返回格式为json
			            async: true, //请求是否异步，默认为异步，这也是ajax重要特性
			            type: "post",   //请求方式
			            success: function (data) {
			            	$("#loadingdiv").hide();
			            	if(data=='SUCCESS'){
			            		alert('对账成功');
			            	}else{
			            		alert('对账失败');
			            	}
			                
			            }
			        });
	    	 }
    	}else{
    		alert("请选择渠道、商户和对账日期");
    	}
    }
    
</script>
</body>
</html>


