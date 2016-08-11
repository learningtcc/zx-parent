
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>维护</title>
<%@ include file="/gen-commons/taglibs.jsp"%>
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
		action="<c:url value="/ChannelParam/list.do"/>" method="post"
		style="display: inline;">
		<input type="hidden" id="pageNumber" name="pageNumber"
			value="<c:out value="${pageNumber}" default="1"/>" /> <input
			type="hidden" id="pageSize" name="pageSize"
			value="<c:out value="${pageSize}" default="10"/>" /> <input
			type="hidden" id="queryType" name="queryType"
			value="<c:out value="${queryType}" default="0"/>" />
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="table00">
			<tr>
				<td class="txtr" style="width: 100px">渠道名称：</td>
				<td>&nbsp;<input value="<c:out value="${name}"/>" id="name"
					name="name" maxlength="64" />
				</td>
				<td class="txtr" style="width: 100px">渠道编号：</td>
				<td>&nbsp;<input value="<c:out value="${no}"/>" id="no"
					name="no" maxlength="32" />
				</td>
				<td class="txtr" style="width: 100px">支付渠道商户号：</td>
				<td>&nbsp;<input value="<c:out value="${channelMerchantNo}"/>"
					id="channelMerchantNo" name="channelMerchantNo" maxlength="64" />
				</td>
				<td class="txtr" style="width: 100px">渠道类型：</td>
				<td>&nbsp;<input value="<c:out value="${type}"/>" id="type"
					name="type" maxlength="10" />
				</td>
			</tr>
		</table>
		<br />
		<div align="center">
			<input type="submit" class="btn01" value="查询"
				onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('0');" />
			<%--<input type="submit" class="btn01" value="模糊查询" onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('1');"/>--%>
			<input type="button" class="btn01" value="清 空" id="btnclear" />
		</div>
	</form>
	<br></br>
	<div class="title01">
		<h2>列表</h2>
	</div>
	<div class="groupbtn">
		<a href="javascript:void(0)" title="新建" class="btn001" id="btnadd">新增</a>
		<a href="javascript:void(0)" title="修改" class="btn002" id="btnedit">修改</a>
	</div>
	<div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb"
			class="table02">
			<thead>
				<tr>
					<th><input id="allChoose" type="checkbox" /></th>
					<th>序号
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>渠道名称
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>渠道编号
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>支付渠道商户号
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>渠道类型
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>文件定位
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>文件获取方式
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>驻留天数
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>创建时间
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>更新时间
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>操作
						<div style="width: 75px; height: 1px; overflow: hidden;"></div>
					</th>
				</tr>
			</thead>
			<c:forEach var="entry" items="${page.result}" varStatus="v">
				<tr>
					<td><input name="ids" type="checkbox" value="${entry.id}" /> <input
						name="pkName" type="hidden" id="pkName" value="id" /></td>
					<td>${v.index + 1 }</td>
					<td>
						<div>
							<c:out value="${entry.name}" />
						</div>
					</td>
					<td>
						<div>
							<c:out value="${entry.no}" />
						</div>
					</td>
					<td>
						<div>
							<c:out value="${entry.channelMerchantNo}" />
						</div>
					</td>
					<td>
						<div>
							<c:out value="${entry.type}" />
						</div>
					</td>
					<td>
						<div>
							<c:out value="${entry.fileAddress}" />
						</div>
					</td>
					<td>
						<div>
							<c:out value="${entry.fileGetModel}" />
						</div>
					</td>
					<td>
						<div>
							<c:out value="${entry.resideDays}" />
						</div>
					</td>
					<td>
						<div>
							<fmt:formatDate value="${entry.createDate}"
								pattern="yyyy-MM-dd HH:mm:ss" />
						</div>
					</td>
					<td>
						<div>
							<fmt:formatDate value="${entry.updateDate}"
								pattern="yyyy-MM-dd HH:mm:ss" />
						</div>
					</td>
					<td><a href="#" onclick="show('${entry.id}')">详情</a>&nbsp; <a
						href="#" onclick="edit('${entry.id}')">修改</a>&nbsp;</td>
				</tr>
			</c:forEach>
		</table>
		<yk:page url="${yk}/ChannelParam/list.do" id="logForm"
			submitForm="queryForm" joy="true" />
	</div>

	<script type="text/javascript">
function show(id){
	window.open('${yk}/ChannelParam/show.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}
function edit(id){
	window.open('${yk}/ChannelParam/edit.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}

$(function(){
	
	$('#btnedit').click(function(){
		var $id = $('[name=ids]:checkbox:checked');
		
		if($id.length==0){
			alert("请选择要修改的信息！");
			return false;
		}
		window.open('${yk}/ChannelParam/edit.do?'+$('#pkName').val()+'='+$id.val(),'','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btnadd').click(function(){
		window.open('${yk}/ChannelParam/create.do','','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	
	$('#btnclear').click(function(){
			$('#name').val('');
			$('#no').val('');
			$('#channelMerchantNo').val('');
			$('#type').val('');
			$('#cutDayStartBegin').val('');
			$('#cutDayStartEnd').val('');
			$('#cutDayEndBegin').val('');
			$('#cutDayEndEnd').val('');
			$('#fileAddress').val('');
			$('#fileGetModel').val('');
			$('#resideDays').val('');
			$('#remark').val('');
			$('#createDateBegin').val('');
			$('#createDateEnd').val('');
			$('#updateDateBegin').val('');
			$('#updateDateEnd').val('');
			$('#operatorId').val('');
			$('#delFlag').val('');
			$('#version').val('');
	});
});
</script>
</body>
</html>


