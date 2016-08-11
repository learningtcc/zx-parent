
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
</div>
	<form id="form1" action="${yk}/AsileBankTemp/save.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<input type="hidden" id="id" name="id" value="<c:out value="${model.id}"/>"/>
		
			
			<tr>	
				<td class="txtr" style="width: 188px">
					通道名称：
				</td>	
				<td>
				<yk:constantConvert htmlTag="select" dataTag="asileName" codeTag="id" nameTag="asileName" data="${asileInfos}" name="asileId" isAutoItem="false" emptyTip="0"/>
				</td>
			</tr>
			
<!-- 			<tr>	 -->
<!-- 				<td class="txtr" style="width: 188px"> -->
<!-- 					通道简码： -->
<!-- 				</td>	 -->
<!-- 				<td> -->
<!-- 				<input id="asileCode" name="asileCode" type="text" class="input04"   /> -->
				
<!-- 				</td> -->
<!-- 			</tr> -->
			
			<tr>	
				<td class="txtr" style="width: 188px">
					银行名称：
				</td>	
				<td>
				<yk:constantConvert htmlTag="select" dataTag="bankName" codeTag="id" nameTag="bankName" data="${basicBanks}" name="bankId" isAutoItem="false" emptyTip="0"/>
				</td>
			</tr>
			
<!-- 			<tr>	 -->
<!-- 				<td class="txtr" style="width: 188px"> -->
<!-- 					银行简码： -->
<!-- 				</td>	 -->
<!-- 				<td> -->
<!-- 				<input id="bankCode" name="bankCode" type="text" class="input04"   /> -->
				
<!-- 				</td> -->
<!-- 			</tr> -->
			
<!-- 			<tr>	 -->
<!-- 				<td class="txtr" style="width: 188px"> -->
<!-- 					支持银行卡类型： -->
<!-- 				</td>	 -->
<!-- 				<td> -->
<!-- 				<input id="asileBankType" name="asileBankType" type="text" class="input04"   /> -->
				
<!-- 				</td> -->
<!-- 			</tr> -->
			
			<tr>	
				<td class="txtr" style="width: 188px">
					支付类型：
				</td>	
				<td>
				<yk:constantConvert htmlTag="select" dataTag="asilePayType" codeTag="code" nameTag="value" data="${routeBusinessType}" name="asilePayType" isAutoItem="false" emptyTip="0"/>
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					单笔交易金额下限：
				</td>	
				<td>
				<input id="asileAmtStart" name="asileAmtStart" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					单笔交易金额上限：
				</td>	
				<td>
				<input id="asileAmtEnd" name="asileAmtEnd" type="text" class="input04"   />
				
				</td>
			</tr>
			
<!-- 			<tr>	 -->
<!-- 				<td class="txtr" style="width: 188px"> -->
<!-- 					单卡单月限额： -->
<!-- 				</td>	 -->
<!-- 				<td> -->
<!-- 				<input id="cardCrashMonthLimit" name="cardCrashMonthLimit" type="text" class="input04"   /> -->
				
<!-- 				</td> -->
<!-- 			</tr> -->
			
<!-- 			<tr>	 -->
<!-- 				<td class="txtr" style="width: 188px"> -->
<!-- 					单卡单日限额： -->
<!-- 				</td>	 -->
<!-- 				<td> -->
<!-- 				<input id="cardDayLimits" name="cardDayLimits" type="text" class="input04"   /> -->
				
<!-- 				</td> -->
<!-- 			</tr> -->
			
			<tr>	
				<td class="txtr" style="width: 188px">
					备注：
				</td>	
				<td>
				<input id="remark" name="remark" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<!-- <tr>	
				<td class="txtr" style="width: 188px">
					创建时间：
				</td>	
				<td>
				<input onClick="WdatePicker()" id="createTimeString" name="createTimeString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					createrId：
				</td>	
				<td>
				<input id="createrId" name="createrId" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					创建人：
				</td>	
				<td>
				<input id="createrName" name="createrName" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					updateTime：
				</td>	
				<td>
				<input onClick="WdatePicker()" id="updateTimeString" name="updateTimeString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					updaterId：
				</td>	
				<td>
				<input id="updaterId" name="updaterId" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					updaterName：
				</td>	
				<td>
				<input id="updaterName" name="updaterName" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					是否删除：
				</td>	
				<td>
				<input id="isDelete" name="isDelete" type="text" class="input04"   />
				
				</td>
			</tr> -->
			
			<tr>	
				<td class="txtr" style="width: 188px">
					临时路由开始时间：
				</td>	
				<td>
				<input onClick="WdatePicker()" id="asileServiceBeginTimeString" name="asileServiceBeginTimeString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					临时路由结束时间：
				</td>	
				<td>
				<input onClick="WdatePicker()" id="asileServiceEndTimeString" name="asileServiceEndTimeString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			
<!-- 			<tr>	 -->
<!-- 				<td class="txtr" style="width: 188px"> -->
<!-- 					优先级： -->
<!-- 				</td>	 -->
<!-- 				<td> -->
<!-- 				<input id="priority" name="priority" type="text" class="input04"   /> -->
				
<!-- 				</td> -->
<!-- 			</tr> -->
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

