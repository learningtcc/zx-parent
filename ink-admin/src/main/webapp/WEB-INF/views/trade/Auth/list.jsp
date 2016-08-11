
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>维护</title>
	<%@ include file="/gen-commons/taglibs.jsp" %>
</head>
<body>
<div class="title01 title01_x">
	<h2>搜索<span class="zksq"><!-- <a href="" title="收起展开"><img alt="展开收起按钮" height="7" src="${yk}/images/zksq.png" width="13" /></a> --></span></h2>
</div>
<form id="queryForm" name="queryForm" action="<c:url value="/trade/Auth/list.do"/>" method="post" style="display: inline;">
	<input type="hidden" id="pageNumber" name="pageNumber" value="<c:out value="${pageNumber}" default="1"/>"/>
	<input type="hidden" id="pageSize" name="pageSize" value="<c:out value="${pageSize}" default="10"/>"/>
	<input type="hidden" id="queryType" name="queryType" value="<c:out value="${queryType}" default="0"/>"/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="table00">
		<tr>	
			<td class="txtr" style="width: 100px">银行卡号：</td>		
			<td>
				&nbsp;<input value="<c:out value="${cardNo}"/>" id="cardNo" name="cardNo" maxlength="64"/>
			</td>
			<%-- <td class="txtr" style="width: 100px">银行卡类型：</td>		
			<td>
				&nbsp;<input value="<c:out value="${cardType}"/>" id="cardType" name="cardType" maxlength="2"/>
			</td> --%>
			<td class="txtr" style="width: 100px">手机号：</td>		
			<td>
				&nbsp;<input value="<c:out value="${phoneNo}"/>" id="phoneNo" name="phoneNo" maxlength="20"/>
			</td>
			<%-- <td class="txtr" style="width: 100px">证件类型：</td>		
			<td>
				&nbsp;<input value="<c:out value="${idType}"/>" id="idType" name="idType" maxlength="2"/>
			</td> --%>
			<td class="txtr" style="width: 100px">身份证号：</td>		
			<td>
				&nbsp;<input value="<c:out value="${idNo}"/>" id="idNo" name="idNo" maxlength="20"/>
			</td>
			<td class="txtr" style="width: 100px">姓名：</td>		
			<td>
				&nbsp;<input value="<c:out value="${userName}"/>" id="userName" name="userName" maxlength="20"/>
			</td>
		</tr>	
		<tr>	
			
			<%-- <td class="txtr" style="width: 100px">所属银行简码：</td>		
			<td>
				&nbsp;<input value="<c:out value="${bankShort}"/>" id="bankShort" name="bankShort" maxlength="15"/>
			</td> --%>
			<%-- <td class="txtr" style="width: 100px">所属银行：</td>		
			<td>
				&nbsp;<input value="<c:out value="${bankName}"/>" id="bankName" name="bankName" maxlength="20"/>
			</td> --%>
			<td class="txtr" style="width: 100px">创建时间：</td>		
			<td>
				&nbsp;<input onClick="WdatePicker()" value="<c:out value="${createTimeBegin}"/>" id="createTimeBegin" name="createTimeBegin" size="10"/>
				<input onClick="WdatePicker()" value="<c:out value="${createTimeEnd}"/>" id="createTimeEnd" name="createTimeEnd" size="10"/>
			</td>
			<td class="txtr" style="width: 100px">状态：</td>		
			<td>
				&nbsp;
				<yk:constantConvert htmlTag="select" dataTag="" data="${statusData}" paramValue="${isDelete}" emptyTip=" " codeTag="code" nameTag="value" name="isDelete"/>
				<%-- &nbsp;<input value="<c:out value="${isDelete}"/>" id="isDelete" name="isDelete" maxlength="10"/> --%>
			</td>
			<td class="txtr" style="width: 100px">请求流水号：</td>		
			<td>
				&nbsp;<input value="<c:out value="${reqId}"/>" id="reqId" name="reqId" maxlength="20"/>
			</td>
			<td class="txtr" style="width: 100px">客户号：</td>		
			<td>
				&nbsp;<input value="<c:out value="${userId}"/>" id="userId" name="userId" maxlength="64"/>
			</td>
			
		</tr>	
		<%-- <tr>	
			
			<td class="txtr" style="width: 100px">版本号：</td>		
			<td>
				&nbsp;<input value="<c:out value="${version}"/>" id="version" name="version" maxlength="10"/>
			</td>
			<td class="txtr" style="width: 100px">删除标识：</td>		
			
			<td class="txtr" style="width: 100px">备注：</td>		
			<td>
				&nbsp;<input value="<c:out value="${remark}"/>" id="remark" name="remark" maxlength="255"/>
			</td>
		</tr>	
		<tr>	
			<td class="txtr" style="width: 100px">商户号：</td>		
			<td>
				&nbsp;<input value="<c:out value="${mchId}"/>" id="mchId" name="mchId" maxlength="20"/>
			</td>
			<td class="txtr" style="width: 100px">最后修改时间：</td>		
			<td>
				&nbsp;<input onClick="WdatePicker()" value="<c:out value="${lastupdateTimeBegin}"/>" id="lastupdateTimeBegin" name="lastupdateTimeBegin" size="10"/>
				<input onClick="WdatePicker()" value="<c:out value="${lastupdateTimeEnd}"/>" id="lastupdateTimeEnd" name="lastupdateTimeEnd" size="10"/>
			</td>
			
		</tr>	
		<tr>	
			
			<td class="txtr" style="width: 100px">支付类型：</td>		
			<td>
				&nbsp;<input value="<c:out value="${payType}"/>" id="payType" name="payType" maxlength="2"/>
			</td>
		</tr>	 --%>
	</table>
	<br/>
	<div align="center">
		<input type="submit" class="btn01" value="精确查询" onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('0');"/>
		<input type="submit" class="btn01" value="模糊查询" onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('1');"/>
		<input type="button" class="btn01" value="清 空" id="btnclear"/>
	</div>
</form>
	<br></br>
	<div class="title01">
	<h2>列表</h2>
</div>
		<!-- <div class="groupbtn">
			<a href="javascript:void(0)" title="新建" class="btn001" id="btnadd">新增</a>
			<a href="javascript:void(0)" title="修改" class="btn002" id="btnedit">修改</a>
			<a href="javascript:void(0)" title="删除"  class="btn004" id="btndelete">删除</a>
		</div> -->
		<div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb" class="table02">
			<thead>
			 <tr>
			 	<th><input id="allChoose" type="checkbox"/></th>
			 	<th>序号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>用户编号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>姓名<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>手机号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>身份证号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>银行卡号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>所属银行<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>鉴权单号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>状态<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>创建时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>最后修改时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				
				<!-- 
				<th>银行卡类型<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>证件类型<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>所属银行简码<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>版本号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>删除标识<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				<th>商户号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
				
				<th>支付类型<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
				<th>备注<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
			   <!--  <th>操作<div style="width: 75px; height: 1px; overflow: hidden;"></div></th> -->
	        </tr>
	        </thead>
			<c:forEach var="entry" items="${page.result}" varStatus="v">
			<tr>
				<td><input name="ids" type="checkbox" value="${entry.id}"/>
				<input name="pkName" type="hidden" id="pkName" value="id"/>
				</td>
				<td>${v.index + 1 }</td>
				<td>
				<div><c:out value="${entry.userId}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.userName}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.phoneNo}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.idNo}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.cardNo}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.bankName}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.reqId}"/></div>
				</td>
				<td>
				<div>
				<yk:constantConvert htmlTag="text" dataTag="" data="${statusData}" paramValue="${entry.isDelete}" emptyTip=" " codeTag="code" nameTag="value"/>
				<%-- <c:out value="${entry.isDelete}"/> --%>
				</div>
				</td>
				<td>
				<div><c:out value="${entry.createTimeString}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.lastupdateTimeString}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.remark}"/></div>
				</td>
				
				
			<%-- 	<td>
				<div><c:out value="${entry.cardType}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.idType}"/></div>
				</td>
				
				<td>
				<div><c:out value="${entry.bankShort}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.version}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.isDelete}"/></div>
				</td>
				<td>
				<div><c:out value="${entry.mchId}"/></div>
				</td>
				
				<td>
				<div><c:out value="${entry.payType}"/></div>
				</td> --%>
				<%-- <td>
						<a href="#" onclick="show('${entry.id}')">详情</a>&nbsp;
						<a href="#" onclick="edit('${entry.id}')">修改</a>&nbsp;
						<a href="#" onclick="del('${entry.id}')">删除</a>&nbsp;
				</td> --%>
			</tr>
			</c:forEach>
			</table>
     	 <yk:page url="${yk}/trade/Auth/list.do" id="logForm" submitForm="queryForm" joy="true"/>
</div>

<script type="text/javascript">
function show(id){
	window.open('${yk}/trade/Auth/show.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}
function edit(id){
	window.open('${yk}/trade/Auth/edit.do?'+$('#pkName').val()+'='+id,'','height=600,width=600,resizable=yes,location=no,scrollbars');
}

$(function(){
	
	$('#btnedit').click(function(){
		var $id = $('[name=ids]:checkbox:checked');
		
		if($id.length==0){
			alert("请选择要修改的信息！");
			return false;
		}
		window.open('${yk}/trade/Auth/edit.do?'+$('#pkName').val()+'='+$id.val(),'','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btnadd').click(function(){
		window.open('${yk}/trade/Auth/create.do','','height=600,width=600,resizable=yes,location=no,scrollbars');
	});
	
	$('#btndelete').click(function(){
		if($('[name=ids]:checkbox:checked').length>0){
			var ids = [];
			$('[name=ids]:checkbox:checked').each(function(){ids.push($(this).val())});
			v_deleteItems(ids);
		}else{
			alert("请选择后进行删除操作！");
		}
		
	});
	
	$('#btnclear').click(function(){
			$('#cardNo').val('');
			$('#cardType').val('');
			$('#phoneNo').val('');
			$('#idType').val('');
			$('#idNo').val('');
			$('#userName').val('');
			$('#bankShort').val('');
			$('#bankName').val('');
			$('#status').val('');
			$('#version').val('');
			$('#isDelete').val('');
			$('#remark').val('');
			$('#mchId').val('');
			$('#lastupdateTimeBegin').val('');
			$('#lastupdateTimeEnd').val('');
			$('#reqId').val('');
			$('#userId').val('');
			$('#createTimeBegin').val('');
			$('#createTimeEnd').val('');
			$('#payType').val('');
	});
});

function del(id){
	var ids = [];
	ids.push(id);
	v_deleteItems(ids);
}

function v_deleteItems(ids){
	if(ids.length>0 && confirm("确定要删除吗？")){
		$.post( "${yk}/trade/Auth/delete.do", {"items":ids.join(',')}, 
				function(data){
					if(data=='1'){
						alert('删除成功');
						$('#queryForm').submit();
					}else{
						alert('删除失败');
					}
				}
		);
	}
};
</script>
</body>
</html>


