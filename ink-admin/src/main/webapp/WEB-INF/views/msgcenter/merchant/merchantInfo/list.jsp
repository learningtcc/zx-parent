<%@ page import="com.ink.msgcenter.core.po.*" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>商户维护</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01">
	<h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/msgcenter/merchant/merchantInfo/list.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
		<tr>	
			<td class="txtr" style="width: 100px">商户名称：</td>		
			<td>
				&nbsp;<input value="<c:out value="${name}"/>" id="name" name="name" maxlength="20"/>
			</td>
			<td class="txtr" style="width: 100px">商户编号：</td>		
			<td>
				&nbsp;<input value="<c:out value="${sn}"/>" id="sn" name="sn" maxlength="20"/>
			</td>
		</tr>
		<tr>	
			<td class="txtr" style="width: 100px">通道类型：</td>		
			<td>
				&nbsp; <yk:constantConvert htmlTag="select" dataTag="channelType" name="channelType" paramValue="${channelType}"  paramSplit="," sysTag="msgcenter"/>
			</td>
			<td class="txtr" style="width: 100px">商户状态：</td>
			<td>
				&nbsp;<yk:constantConvert htmlTag="select" dataTag="status" name="status" paramValue="${status}" emptyTip="0" sysTag="msgcenter"/>
			</td>
		</tr>
	</table>
	<br/>
	<div align="center">
		<input type="submit" class="btn01" value="精确查询" onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('0');"/>
		<input type="submit" class="btn01" value="模糊查询" onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('1');"/>
		<input type="button" class="btn01" value="清 空" id="btnclear"/>
	</div>
</form>
	<br/>
	<div class="title01">
	<h2>商户列表</h2>
</div>
		<div class="groupbtn">
			<a href="javascript:void(0)" title="新建" class="btn001" id="btnadd">新增</a>
			<a href="javascript:void(0)" title="删除"  class="btn004" id="btndelete">删除</a>
			<input type="button" class="btn01"  name="sendEmail" id="sendEmail" value="发送邮件" onclick="sendEmail()"/>
			<input type="button" class="btn01"  name="sendSms" id="sendSms" value="发送短信" onclick="sendSms()"/>
		</div>
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb" class="table02">
			<thead>
			 <tr>
			 	<th><input id="allChoose" type="checkbox"/></th>
			 	<th>序号<div style="width: 30px; height: 1px; overflow: hidden;"></div></th>
				<th>商户名称<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>商户编号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				 <th>通道类型<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>商户状态<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>创建时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>更新时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
			    <th>操作<div style="width: 75px; height: 1px; overflow: hidden;"></div></th>
	        </tr>
	        </thead>
			<c:forEach var="entry" items="${page.result}" varStatus="v">
			<tr>
				<td><input name="ids" type="checkbox" value="${entry.id}"/>
				<input name="pkName" type="hidden" id="pkName" value="id"/>
				</td>
				<td>${v.index + 1 }</td>
				<td>
				<div><c:out value="${entry.name}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.sn}"/></div>
				</td>
				<td>
					<div>
						<yk:constantConvert htmlTag="text" dataTag="channelType" paramValue="${entry.channelType}" paramSplit="," emptyTip="未知" sysTag="msgcenter"/>
					</div>
				</td>
				<td>
				<div>
					<yk:constantConvert htmlTag="text" dataTag="status" paramValue="${entry.status}" emptyTip="未知" sysTag="msgcenter"/>
				</div>
				</td>
				<td>
				<div><c:out value="${entry.createTimeString}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.updateTimeString}"/></div>
				</td>
				<td>
						<a href="#" onclick="show('${entry.id}')">详情</a>&nbsp;
						<a href="#" onclick="edit('${entry.id}')">修改</a>&nbsp;
					<c:choose>
						<c:when test="${entry.status == 1}">
							<a href="#" onclick="del('${entry.id}','0')">启用</a>&nbsp;
						</c:when>
						<c:otherwise>
							<a href="#" onclick="del('${entry.id}','1')">停用</a>&nbsp;
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${entry.channelType == '1'}">
							<a href="#" onclick="showModalWin('${entry.id}','${entry.sn}','1')">短信通道</a>&nbsp;
						</c:when>
						<c:when test="${entry.channelType == '2'}">
							<a href="#" onclick="showModalWin('${entry.id}','${entry.sn}','2')">邮件通道</a>&nbsp;
						</c:when>
						<c:when test="${entry.channelType == null}">
						</c:when>
						<c:otherwise>
							<a href="#" onclick="showModalWin('${entry.id}','${entry.sn}','1')">短信通道</a>&nbsp;
							<a href="#" onclick="showModalWin('${entry.id}','${entry.sn}','2')">邮件通道</a>&nbsp;
						</c:otherwise>
					</c:choose>
						<a href="#" onclick="btnEmail('${entry.sn}','${entry.id}')">邮件日志</a>&nbsp;
						<a href="#" onclick="btnSms('${entry.sn}','${entry.id}')">短信日志</a>&nbsp;
						<a href="#" onclick="del('${entry.id}','2')">删除</a>&nbsp;
				</td>
			</tr>
			</c:forEach>
			</table>
     	 <yk:page url="${yk}/msgcenter/merchant/merchantInfo/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>
<%--模态弹出窗口--%>
<div id="fade" class="black_overlay"></div>
<div id="ModalDiv" class="white_content">
	<iframe src="" width="100%"  height="100%" id="modalIframe"></iframe>
	<div class="clearfix bbt">
		<input type="hidden" id="selectType"/>
		<a id="restore" class="fl restore" onclick="CloseDiv('ModalDiv','fade')">关　闭</a>
		<a id="determine" class="fl mglt20 determine" onclick="saveSelect()">保　存</a>
	</div>
</div>

<script type="text/javascript">
function show(id){
	window.open('${yk}/msgcenter/merchant/merchantInfo/show.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}
function edit(id){
	window.open('${yk}/msgcenter/merchant/merchantInfo/edit.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}

$(function(){
	
	$('#btnedit').click(function(){
		var $id = $('[name=ids]:checkbox:checked');
		
		if($id.length==0){
			alert("请选择要修改的信息！");
			return false;
		}
		window.open('${yk}/msgcenter/merchant/merchantInfo/edit.do?'+$('#pkName').val()+'='+$id.val(),'','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btnadd').click(function(){
		window.open('${yk}/msgcenter/merchant/merchantInfo/create.do','','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btndelete').click(function(){
		if($('[name=ids]:checkbox:checked').length>0){
			var ids = [];
			$('[name=ids]:checkbox:checked').each(function(){ids.push($(this).val())});
			v_deleteItems(ids,'2');
		}else{
			alert("请选择后进行删除操作！");
		}
		
	});
	
	$('#btnclear').click(function(){
			$('#name').val('');
			$('#sn').val('');
			$('#status').val('');
			$('#channelType').val('');
	});

});

function showModalWin(id,sn,flag){
	var url = "${yk}/msgcenter/merchant/merchantChn/selectSmsChannelList.do?id="+id+"&sn="+sn;
	$("#selectType").val("1");
	if(flag == '2'){
		url = "${yk}/msgcenter/merchant/merchantChn/selectEmailChannelList.do?id="+id+"&sn="+sn;
		$("#selectType").val("2");
	}
	$("#modalIframe").attr("src",url);

	ShowDiv('ModalDiv','fade');
}
function saveSelect(){
	$("#modalIframe").contents().find("#selectForm").ajaxSubmit(function(data){
        if(data==1)	{
            alert("保存成功！");
			CloseDiv('ModalDiv','fade');
        }else{
            alert("保存失败！");
        }
	});
}

function btnEmail(merctCode,id){
	window.open('${yk}/msgcenter/EmailLog/showEmailLog.do?queryFlag=1&merctCode='+merctCode+'&merctId='+id,'','','height=600,width=1000,resizable=yes,location=no,scrollbars');
}
function btnSms(merctCode,id){
	window.open('${yk}/msgcenter/SmsLog/showSmsLog.do?queryFlag=1&merctCode='+merctCode+'&merctId='+id,'','','height=600,width=1000,resizable=yes,location=no,scrollbars');
}

function sendEmail(){
	window.open('${yk}/msgcenter/merchant/merchantInfo/goSendEmail.do','','height=600,width=1000,resizable=yes,location=no,scrollbars');
}

function sendSms(){
	window.open('${yk}/msgcenter/merchant/merchantInfo/goSendSms.do','','height=600,width=1000,resizable=yes,location=no,scrollbars');
}

function del(id,status){
	var ids = [];
	ids.push(id);
	v_deleteItems(ids,status);
}

function v_deleteItems(ids,status){
	var message = "";
	if(status=="0"){
		message = "确认要启用吗？";
	}else if(status == "1"){
		message = "确认要停用吗？";
	}else {
		message = "确认要删除吗？";
	}

	if(ids.length>0 && confirm(message)){
		$.post( "${yk}/msgcenter/merchant/merchantInfo/updateStatus.do", {"items":ids.join(','),"status":status},
				function(data){
					if(data=='1'){
						alert('操作成功');
						$('#queryForm').submit();
					}else{
						alert('操作失败');
					}
				}
		);
	}
}
</script>
</body>
</html>


