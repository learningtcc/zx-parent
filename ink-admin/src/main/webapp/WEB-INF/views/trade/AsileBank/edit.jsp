
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
</div>
	<form id="form1" action="${yk}/trade/AsileBank/update.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<input type="hidden" id="id" name="id" value="<c:out value="${model.id}"/>"/>
		
			
			<tr>	
				<td class="txtr" style="width: 188px">
					通道名称:
				</td>	
				<td>
<%-- 				<input id="asileName" name="asileName" value="<c:out value="${model.asileName}"/>" type="text" class="input04"   /> --%>
				<yk:constantConvert htmlTag="select" paramValue="${model.asileCode}" dataTag="asileName" codeTag="asileCode" nameTag="asileName" data="${asileInfos}" name="asileCode" isAutoItem="false" emptyTip="0"/>
				</td>
			</tr>
			
<!-- 			<tr>	 -->
<!-- 				<td class="txtr" style="width: 188px"> -->
<!-- 					通道id: -->
<!-- 				</td>	 -->
<!-- 				<td> -->
<%-- 				<input id="asileCode" name="asileCode" value="<c:out value="${model.asileCode}"/>" type="text" class="input04"   /> --%>
<%-- 					<yk:constantConvert htmlTag="select" paramValue="${model.asileCode}" dataTag="asileCode" codeTag="asileCode" nameTag="asileCode" data="${asileInfos}" name="asileCode" isAutoItem="false" emptyTip="0"/> --%>
<!-- 				</td> -->
<!-- 			</tr> -->
			
			<tr>	
				<td class="txtr" style="width: 188px">
					银行名称:
				</td>	
				<td>
				<yk:constantConvert htmlTag="select" dataTag="bankName" codeTag="bankShortName" paramValue="${model.bankShort}" nameTag="bankName" data="${basicBanks}" name="bankCode" isAutoItem="false" emptyTip="0"/>
				
				</td>
			</tr>
			
<!-- 			<tr>	 -->
<!-- 				<td class="txtr" style="width: 188px"> -->
<!-- 					银行id: -->
<!-- 				</td>	 -->
<!-- 				<td> -->
<%-- 				<input id="bankCode" name="bankCode" value="<c:out value="${model.bankCode}"/>" type="text" class="input04"   /> --%>
				
<!-- 				</td> -->
<!-- 			</tr> -->
			
<!-- 			<tr>	 -->
<!-- 				<td class="txtr" style="width: 188px"> -->
<!-- 					通道产品编码: -->
<!-- 				</td>	 -->
<!-- 				<td> -->
<%-- 				<input id="asileProductCode" name="asileProductCode" value="<c:out value="${model.asileProductCode}"/>" type="text" class="input04"   /> --%>
				
<!-- 				</td> -->
<!-- 			</tr> -->
			
<!-- 			<tr>	 -->
<!-- 				<td class="txtr" style="width: 188px"> -->
<!-- 					通道产品名称: -->
<!-- 				</td>	 -->
<!-- 				<td> -->
<%-- 				<input id="asileProductName" name="asileProductName" value="<c:out value="${model.asileProductName}"/>" type="text" class="input04"   /> --%>
				
<!-- 				</td> -->
<!-- 			</tr> -->
			
<!-- 			<tr>	 -->
<!-- 				<td class="txtr" style="width: 188px"> -->
<!-- 					是否直连: -->
<!-- 				</td>	 -->
<!-- 				<td> -->
<%-- 				<input id="asileDirectBank" name="asileDirectBank" value="<c:out value="${model.asileDirectBank}"/>" type="text" class="input04"   /> --%>
				
<!-- 				</td> -->
<!-- 			</tr> -->
			
<!-- 			<tr>	 -->
<!-- 				<td class="txtr" style="width: 188px"> -->
<!-- 					对公对私: -->
<!-- 				</td>	 -->
<!-- 				<td> -->
<%-- 				<input id="asilePublicPrivate" name="asilePublicPrivate" value="<c:out value="${model.asilePublicPrivate}"/>" type="text" class="input04"   /> --%>
				
<!-- 				</td> -->
<!-- 			</tr> -->
			
<!-- 			<tr>	 -->
<!-- 				<td class="txtr" style="width: 188px"> -->
<!-- 					提供接口方式: -->
<!-- 				</td>	 -->
<!-- 				<td> -->
<%-- 				<input id="asileIntefaceType" name="asileIntefaceType" value="<c:out value="${model.asileIntefaceType}"/>" type="text" class="input04"   /> --%>
				
<!-- 				</td> -->
<!-- 			</tr> -->
			
<!-- 			<tr>	 -->
<!-- 				<td class="txtr" style="width: 188px"> -->
<!-- 					鉴权方式: -->
<!-- 				</td>	 -->
<!-- 				<td> -->
<%-- 				<input id="asileAuthType" name="asileAuthType" value="<c:out value="${model.asileAuthType}"/>" type="text" class="input04"   /> --%>
				
<!-- 				</td> -->
<!-- 			</tr> -->
			
<!-- 			<tr>	 -->
<!-- 				<td class="txtr" style="width: 188px"> -->
<!-- 					鉴权要素(转换为二进制位数表示): -->
<!-- 				</td>	 -->
<!-- 				<td> -->
<%-- 				<input id="asileAuthElements" name="asileAuthElements" value="<c:out value="${model.asileAuthElements}"/>" type="text" class="input04"   /> --%>
				
<!-- 				</td> -->
<!-- 			</tr> -->
			
<!-- 			<tr>	 -->
<!-- 				<td class="txtr" style="width: 188px"> -->
<!-- 					授权方式: -->
<!-- 				</td>	 -->
<!-- 				<td> -->
<%-- 				<input id="asileAuthMode" name="asileAuthMode" value="<c:out value="${model.asileAuthMode}"/>" type="text" class="input04"   /> --%>
				
<!-- 				</td> -->
<!-- 			</tr> -->
			
<!-- 			<tr>	 -->
<!-- 				<td class="txtr" style="width: 188px"> -->
<!-- 					支持银行卡类型: -->
<!-- 				</td>	 -->
<!-- 				<td> -->
<%-- 				<input id="asileBankType" name="asileBankType" value="<c:out value="${model.asileBankType}"/>" type="text" class="input04"   /> --%>
				
<!-- 				</td> -->
<!-- 			</tr> -->
			
			<tr>	
				<td class="txtr" style="width: 188px">
					支付类型:
				</td>	
				<td>
				<yk:constantConvert htmlTag="select" paramValue="${model.asilePayType}" dataTag="asilePayType" codeTag="code" nameTag="value" data="${routeBusinessType}" name="asilePayType" isAutoItem="false" emptyTip="0"/>
<%-- 				<input id="asilePayType" name="asilePayType" value="<c:out value="${model.asilePayType}"/>" type="text" class="input04"   /> --%>
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					单笔限额:
				</td>	
				<td>
				<input id="asileCrashLimit" name="asileCrashLimit" value="<c:out value="${model.asileCrashLimit}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
<!-- 			<tr>	 -->
<!-- 				<td class="txtr" style="width: 188px"> -->
<!-- 					单卡单日限额: -->
<!-- 				</td>	 -->
<!-- 				<td> -->
<%-- 				<input id="cardCrashDayLimit" name="cardCrashDayLimit" value="<c:out value="${model.cardCrashDayLimit}"/>" type="text" class="input04"   /> --%>
				
<!-- 				</td> -->
<!-- 			</tr> -->
			
<!-- 			<tr>	 -->
<!-- 				<td class="txtr" style="width: 188px"> -->
<!-- 					单卡单月限额: -->
<!-- 				</td>	 -->
<!-- 				<td> -->
<%-- 				<input id="cardCrashMonthLimit" name="cardCrashMonthLimit" value="<c:out value="${model.cardCrashMonthLimit}"/>" type="text" class="input04"   /> --%>
				
<!-- 				</td> -->
<!-- 			</tr> -->
			
<!-- 			<tr>	 -->
<!-- 				<td class="txtr" style="width: 188px"> -->
<!-- 					单卡单日限额: -->
<!-- 				</td>	 -->
<!-- 				<td> -->
<%-- 				<input id="cardDayLimits" name="cardDayLimits" value="<c:out value="${model.cardDayLimits}"/>" type="text" class="input04"   /> --%>
				
<!-- 				</td> -->
<!-- 			</tr> -->
			
<!-- 			<tr>	 -->
<!-- 				<td class="txtr" style="width: 188px"> -->
<!-- 					批量限额: -->
<!-- 				</td>	 -->
<!-- 				<td> -->
<%-- 				<input id="batchCrashLimit" name="batchCrashLimit" value="<c:out value="${model.batchCrashLimit}"/>" type="text" class="input04"   /> --%>
				
<!-- 				</td> -->
<!-- 			</tr> -->
			
<!-- 			<tr>	 -->
<!-- 				<td class="txtr" style="width: 188px"> -->
<!-- 					批量限次: -->
<!-- 				</td>	 -->
<!-- 				<td> -->
<%-- 				<input id="batchLimit" name="batchLimit" value="<c:out value="${model.batchLimit}"/>" type="text" class="input04"   /> --%>
				
<!-- 				</td> -->
<!-- 			</tr> -->
			
			<tr>	
				<td class="txtr" style="width: 188px">
					备注:
				</td>	
				<td>
				<input id="remark" name="remark" value="<c:out value="${model.remark}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<%-- <tr>	
				<td class="txtr" style="width: 188px">
					创建时间:
				</td>	
				<td>
				<input onClick="WdatePicker()" id="createTimeString" value="<c:out value="${model.createTimeString}"/>" name="createTimeString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					createrId:
				</td>	
				<td>
				<input id="createrId" name="createrId" value="<c:out value="${model.createrId}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					创建人:
				</td>	
				<td>
				<input id="createrName" name="createrName" value="<c:out value="${model.createrName}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			--%>
			<tr>	
				<td class="txtr" style="width: 188px">
					updateTime:
				</td>	
				<td>
				<input onClick="WdatePicker()" id="updateTimeString" value="<c:out value="${model.updateTimeString}"/>" name="updateTimeString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			<%--
			<tr>	
				<td class="txtr" style="width: 188px">
					updaterId:
				</td>	
				<td>
				<input id="updaterId" name="updaterId" value="<c:out value="${model.updaterId}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					updaterName:
				</td>	
				<td>
				<input id="updaterName" name="updaterName" value="<c:out value="${model.updaterName}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					是否删除:
				</td>	
				<td>
				<input id="isDelete" name="isDelete" value="<c:out value="${model.isDelete}"/>" type="text" class="input04"   />
				
				</td>
			</tr> --%>
			
<!-- 			<tr>	 -->
<!-- 				<td class="txtr" style="width: 188px"> -->
<!-- 					回盘时间: -->
<!-- 				</td>	 -->
<!-- 				<td> -->
<%-- 				<input onClick="WdatePicker()" id="asileOnlyTimeString" value="<c:out value="${model.asileOnlyTimeString}"/>" name="asileOnlyTimeString" size="10"  readonly="readonly"/> --%>
				
<!-- 				</td> -->
<!-- 			</tr> -->
			
			<tr>	
				<td class="txtr" style="width: 188px">
					通道服务开始时间:
				</td>	
				<td>
				<input onClick="WdatePicker()" id="asileServiceBeginTimeString" value="<c:out value="${model.asileServiceBeginTimeString}"/>" name="asileServiceBeginTimeString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					通道服务结束时间:
				</td>	
				<td>
				<input onClick="WdatePicker()" id="asileServiceEndTimeString" value="<c:out value="${model.asileServiceEndTimeString}"/>" name="asileServiceEndTimeString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			
<!-- 			<tr>	 -->
<!-- 				<td class="txtr" style="width: 188px"> -->
<!-- 					优先级: -->
<!-- 				</td>	 -->
<!-- 				<td> -->
<%-- 				<input id="priority" name="priority" value="<c:out value="${model.priority}"/>" type="text" class="input04"   /> --%>
				
<!-- 				</td> -->
<!-- 			</tr> -->
			
<!-- 			<tr>	 -->
<!-- 				<td class="txtr" style="width: 188px"> -->
<!-- 					0是分离，1是不分: -->
<!-- 				</td>	 -->
<!-- 				<td> -->
<%-- 				<input id="borrowSplit" name="borrowSplit" value="<c:out value="${model.borrowSplit}"/>" type="text" class="input04"   /> --%>
				
<!-- 				</td> -->
<!-- 			</tr> -->
			
<!-- 			<tr>	 -->
<!-- 				<td class="txtr" style="width: 188px"> -->
<!-- 					短信发送方: -->
<!-- 				</td>	 -->
<!-- 				<td> -->
<%-- 				<input id="smsSender" name="smsSender" value="<c:out value="${model.smsSender}"/>" type="text" class="input04"   /> --%>
				
<!-- 				</td> -->
<!-- 			</tr> -->
			
			<tr>	
				<td class="txtr" style="width: 188px">
					银行简码:
				</td>	
				<td>
				<input id="bankShort" name="bankShort" value="<c:out value="${model.bankShort}"/>" type="text" class="input04"   />
				
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