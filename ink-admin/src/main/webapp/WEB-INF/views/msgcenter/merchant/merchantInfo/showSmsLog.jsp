<%@ page import="com.ink.msgcenter.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>短信发送日志</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01 title01_x">
	<h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/msgcenter/SmsLog/showSmsLog.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
	<input type="hidden" value="<c:out value="${merctCode}"/>" id="merctCode" name="merctCode"  />
	<input type="hidden" value="<c:out value="${queryFlag}"/>" id="queryFlag" name="queryFlag"  />
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
		<tr>	
			<td class="txtr" style="width: 100px">商户：</td>		
			<td>
				<c:choose>
					<c:when test="${queryFlag==1}">
						<input type="hidden" id="merctId" name="merctId" value="<c:out value="${merctId}" />"/>
						<yk:code2name tableName="merchant_info" columnName="name" where="id=?" paramValue="${merctId}" serviceName="msgcenterDubboBaseService"/>
					</c:when>
					<c:otherwise>
						<yk:select codeName="name" table="merchant_info" codeValue="id" id="merctId" name="merctId" select="${merctId }" where=" status != 2 " serviceName="msgcenterDubboBaseService"/>
					</c:otherwise>
				</c:choose>
			</td>
			<td class="txtr" style="width: 100px">通道：</td>
			<td>
				<select id="chnId" name="chnId">
					<option value="">请选择...</option>
				</select>
			</td>
			<td class="txtr" style="width: 100px">模板ID：</td>		
			<td>
				<input value="<c:out value="${tempId}"/>" id="tempId" name="tempId" maxlength="19"/>
			</td>
			<td class="txtr" style="width: 100px">手机号：</td>		
			<td>
				<input value="<c:out value="${mobile}"/>" id="mobile" name="mobile" maxlength="20"/>
			</td>
		</tr>	
		<tr>
			<td class="txtr" style="width: 100px">业务单号：</td>		
			<td>
				<input value="<c:out value="${infoCode}"/>" id="infoCode" name="infoCode" maxlength="50"/>
			</td>	
			<td class="txtr" style="width: 100px">短信代码：</td>		
			<td>
				<input value="<c:out value="${sms_code}"/>" id="sms_code" name="sms_code" maxlength="50"/>
			</td>
			<td class="txtr" style="width: 100px">消息ID：</td>
			<td>
				<input value="<c:out value="${smsId}"/>" id="smsId" name="smsId" maxlength="50"/>
			</td>
			<td class="txtr" style="width: 100px">响应代码：</td>		
			<td>
				&nbsp;<input value="<c:out value="${responseCode}"/>" id="responseCode" name="responseCode" maxlength="5"/>
			</td>
		</tr>	
		<tr>
			<td class="txtr" style="width: 100px">短信类型：</td>		
			<td>
				<yk:constantConvert htmlTag="select" dataTag="smsType" paramValue="${smsType}" name="smsType" sysTag="msgcenter"/>
			</td>
			<td class="txtr" style="width: 100px">发送状态：</td>
			<td>
				<yk:constantConvert htmlTag="select" dataTag="sendStatus" paramValue="${sendStatus}" name="sendStatus" sysTag="msgcenter"/>
			</td>
			<td class="txtr" style="width: 100px">状态报告：</td>		
			<td>
				<yk:constantConvert htmlTag="select" dataTag="reportStatus" paramValue="${reportStatus}" name="sendStatus" sysTag="msgcenter"/>
			</td>

		</tr>
	</table>
	<br/>
	<div align="center">
		<input type="submit" class="btn01" value="查 询" onclick="return check()"/>
		<input type="button" class="btn01" value="清 空" id="btnclear"/>
	</div>
</form>
	<br></br>
	<div class="title01">
	<h2><%=SmsLog.TABLE_ALIAS%>列表</h2>
</div>
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb" class="table02">
			<thead>
			 <tr>
			 	<th>序号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>商户<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>商户代码<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>模板ID<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>手机号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>短信内容<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>业务单号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>短信代码<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				 <th>消息ID<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>发送状态<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>响应代码<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>短信类型<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>状态报告<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>提交时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>发送时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
			    <th>操作<div style="width: 75px; height: 1px; overflow: hidden;"></div></th>
	        </tr>
	        </thead>
			<c:forEach var="entry" items="${page.result}" varStatus="v">
			<tr>
				<td>${v.index + 1 }</td>
				<td>
				<div>
					<yk:code2name columnName="name" tableName="merchant_info" where="id = ?" paramValue="${entry.merctId}" serviceName="msgcenterDubboBaseService"/>
				</div>
				</td>
				<td>
				<div><c:out value="${entry.merctCode}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.tempId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.mobile}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.smsMsg}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.infoCode}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.smsCode}"/></div>
				</td>
				<td>
					<div><c:out value="${entry.smsId}"/></div>
				</td>
				<td>
				<div>
					<yk:constantConvert htmlTag="text" dataTag="sendStatus" paramValue="${entry.sendStatus}" emptyTip="未知" sysTag="msgcenter"/>
				</div>
				</td>
				<td>
				<div><c:out value="${entry.responseCode}"/></div>
				</td>
				<td>
				<div>
					<yk:constantConvert htmlTag="text" dataTag="smsType" paramValue="${entry.smsType}" emptyTip="未知" sysTag="msgcenter"/>
				</div>
				</td>
				<td>
				<div>
					<yk:constantConvert htmlTag="text" dataTag="reportStatus" paramValue="${entry.reportStatus}" emptyTip="未知"  sysTag="msgcenter"/>
				</div>
				</td>
				<td>
				<div><c:out value="${entry.submitTimeString}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.sendTimeString}"/></div>
				</td>
				<td>
						<a href="#" onclick="show('${entry.id}','${entry.merctCode}')">详情</a>&nbsp;
				</td>
			</tr>
			</c:forEach>
			</table>
     	 <yk:page url="${yk}/msgcenter/SmsLog/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">
function show(id,merctCode){
	window.open('${yk}/msgcenter/SmsLog/show.do?id='+id + "&merctCode="+merctCode);
}

function check(){
	$('#pageNumber').val('1');
	$('#pageSize').val('10');
	$('#queryType').val('1');
	if($.trim($('#merctCode').val()) == ""){
		alert('请选择商户!');
		return false;
	}
}

$(function(){

	$('#btnclear').click(function(){
			$('#merctId').val('');
			$('#merctCode').val('');
			$('#chnId').val('');
			$('#chnCode').val('');
			$('#tempId').val('');
			$('#mobile').val('');
			$('#infoCode').val('');
			$('#smsCode').val('');
			$('#smsId').val('');
			$('#taskId').val('');
			$('#sendStatus').val('');
			$('#responseCode').val('');
			$('#smsType').val('');
			$('#reportStatus').val('');
	});

	var queryFlag = $("#queryFlag").val();
	var merctId = $("#merctId").val();
	if(queryFlag ==1 || merctId.length>0){
		selectChnInfo('${merctId}');
	}

	$('#merctId').change(function(){
		selectChnInfo($("#merctId").val());
		$.post("${yk}/msgcenter/merchant/merchantInfo/get.do",{id:$("#merctId").val()},function(data){
			$("#merctCode").val(data.sn);
		},'json');
	});

});

function selectChnInfo(merchId){
	$('#chnId').empty();
	$('#chnId').append('<option value="">请选择...</option>');
	$.post("${yk}/msgcenter/merchant/merchantChn/findSmsByMerctId.do",{merctId:merchId},function(data){
		$.each(data,function(i,value){
			console.info(value);
			var chnv = '${chnId}';
			if(value.id == chnv){
				$("#chnId").append('<option value="'+value.id+'" selected="selected">'+value.name+'</option>');
			}else{
				$("#chnId").append('<option value="'+value.id+'">'+value.name+'</option>');
			}
		});
	},'json');
}
</script>
</body>
</html>


