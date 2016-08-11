
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>编辑</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
	<h2>修改</h2>
</div>
	<form id="form1" action="${yk}/trade/AsileResCode/update.do" method="post">
	
		<table cellpadding="0" cellspacing="0" class="table00" style="width: 100%">
			<input type="hidden" id="id" name="id" value="<c:out value="${model.id}"/>"/>
		
			
			<tr>	
				<td class="txtr" style="width: 188px">
					通道名称:
				</td>	
				<td>
				<input id="asileName" name="asileName" value="<c:out value="${model.asileName}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					通道id:
				</td>	
				<td>
				<input id="asileCode" name="asileCode" value="<c:out value="${model.asileCode}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
<!-- 			<tr>	 -->
<!-- 				<td class="txtr" style="width: 188px"> -->
<!-- 					通道支付方式: -->
<!-- 				</td>	 -->
<!-- 				<td> -->
<%-- 				<input id="asilePayType" name="asilePayType" value="<c:out value="${model.asilePayType}"/>" type="text" class="input04"   /> --%>
				
<!-- 				</td> -->
<!-- 			</tr> -->
			
<!-- 			<tr>	 -->
<!-- 				<td class="txtr" style="width: 188px"> -->
<!-- 					交易类型: -->
<!-- 				</td>	 -->
<!-- 				<td> -->
<%-- 				<input id="transType" name="transType" value="<c:out value="${model.transType}"/>" type="text" class="input04"   /> --%>
				
<!-- 				</td> -->
<!-- 			</tr> -->
			
			<tr>	
				<td class="txtr" style="width: 188px">
					通道响应码:
				</td>	
				<td>
				<input id="asileResCode" name="asileResCode" value="<c:out value="${model.asileResCode}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					通道响应信息:
				</td>	
				<td>
				<input id="asileResMsg" name="asileResMsg" value="<c:out value="${model.asileResMsg}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					平台响应码:
				</td>	
				<td>
				<input id="resCode" name="resCode" value="<c:out value="${model.resCode}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
			<tr>	
				<td class="txtr" style="width: 188px">
					平台响应信息:
				</td>	
				<td>
				<input id="resMsg" name="resMsg" value="<c:out value="${model.resMsg}"/>" type="text" class="input04"   />
				
				</td>
			</tr>
			
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
					创建人id:
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
			
			<tr>	
				<td class="txtr" style="width: 188px">
					更新时间:
				</td>	
				<td>
				<input onClick="WdatePicker()" id="updateTimeString" value="<c:out value="${model.updateTimeString}"/>" name="updateTimeString" size="10"  readonly="readonly"/>
				
				</td>
			</tr>
			
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