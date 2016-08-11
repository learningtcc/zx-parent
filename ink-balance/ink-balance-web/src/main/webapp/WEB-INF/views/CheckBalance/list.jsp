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
		action="<c:url value="/CheckBalance/list.do"/>" method="post"
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
				<td class="txtr" style="width: 100px">关联主键：</td>
				<td>&nbsp;<input value="<c:out value="${refId}"/>" id="refId"
					name="refId" maxlength="19" />
				</td>
				<td class="txtr" style="width: 100px">调账对象 ：</td>
				<td>&nbsp;<select name="balanceSource" id="balanceSource">
						<option value="0">请选择</option>
						<option value="1"
							<c:if test="${'1' eq balanceSource}">selected</c:if>>渠道</option>
						<option value="2"
							<c:if test="${'2' eq balanceSource}">selected</c:if>>平台</option>
				</select>
				</td>
				<td class="txtr" style="width: 100px">调账方向：</td>
				<td>&nbsp; <select class="easyui-combobox"
					name="balanceDirection" id="balanceDirection">
						<option value="0">请选择</option>
						<option value="1"
							<c:if test="${'1' eq balanceDirection}">selected</c:if>>正</option>
						<option value="2"
							<c:if test="${'2' eq balanceDirection}">selected</c:if>>负</option>
				</select>
				</td>
				<td class="txtr" style="width: 100px">渠道编号：</td>
				<td>&nbsp; <select class="easyui-combobox" name="channelNo"
					id="channelNo">
						<option value="">请选择</option>
						<c:forEach var="entry" items="${channelList}" varStatus="v">
							<option value="${entry.no}"
								<c:if test="${entry.no eq channelNo}">selected</c:if>>${entry.name}</option>
						</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<td class="txtr" style="width: 100px">平台订单号：</td>
				<td>&nbsp;<input value="<c:out value="${platformOrderNo}"/>"
					id="platformOrderNo" name="platformOrderNo" maxlength="64" />
				</td>
				<td class="txtr" style="width: 100px">调账状态：</td>
				<td>&nbsp;<select name="balanceStatus" id="balanceStatus">
						<option value="0">请选择</option>
						<option value="1"
							<c:if test="${'1' eq balanceStatus}">selected</c:if>>调账成功</option>
						<option value="2"
							<c:if test="${'2' eq balanceStatus}">selected</c:if>>调账失败</option>
				</select>
				</td>
				<td class="txtr" style="width: 100px">创建时间：</td>
				<td>&nbsp;<input
					onClick="WdatePicker({startDate:'%y',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'createEndDate\')}'})"
					value="<c:out value="${createBeginDate}"/>" id="createBeginDate"
					name="createBeginDate" size="10" readonly="readonly" /> <input
					onClick="WdatePicker({startDate:'%y',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'createBeginDate\')}'})"
					value="<c:out value="${createEndDate}"/>" id="createEndDate"
					name="createEndDate" size="10" readonly="readonly" />
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
		<a href="javascript:void(0)" title="新建" class="btn001" id="btnadd">调账</a>
		<!-- 	<a href="javascript:void(0)" title="修改" class="btn002" id="btnedit">修改</a>
        <a href="javascript:void(0)" title="删除"  class="btn004" id="btndelete">删除</a>-->
		<a href="javascript:exportExcel()" title="导出明细" class="btn010"
			id="btnexcel">导出</a>
	</div>
	<div>
		<div class="table02_xu">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				id="tb" class="table02">
				<thead>
					<tr>
						<th><input id="allChoose" type="checkbox" /></th>
						<th>序号
							<div style="width: 30px; height: 1px; overflow: hidden;"></div>
						</th>
						<%--<th>关联主键<div style="width: 55px; height: 1px; overflow: hidden;"></div></th>--%>
						<th>调账对象
							<div style="width: 55px; height: 1px; overflow: hidden;"></div>
						</th>
						<th>调账方向
							<div style="width: 55px; height: 1px; overflow: hidden;"></div>
						</th>
						<th>调账金额
							<div style="width: 65px; height: 1px; overflow: hidden;"></div>
						</th>
						<th>渠道编号
							<div style="width: 55px; height: 1px; overflow: hidden;"></div>
						</th>
						<th>平台订单号
							<div style="width: 65px; height: 1px; overflow: hidden;"></div>
						</th>
						<%--<th>交易流水号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>--%>
						<th>调账状态
							<div style="width: 55px; height: 1px; overflow: hidden;"></div>
						</th>
						<th>创建时间
							<div style="width: 120px; height: 1px; overflow: hidden;"></div>
						</th>
						<th>更新时间
							<div style="width: 120px; height: 1px; overflow: hidden;"></div>
						</th>
						<%--<th>删除标志<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
                <th>版本号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>--%>
						<th>调账说明
							<div style="width: 65px; height: 1px; overflow: hidden;"></div>
						</th>
						<th>操作
							<div style="width: 75px; height: 1px; overflow: hidden;"></div>
						</th>
					</tr>
				</thead>
				<c:forEach var="entry" items="${page.result}" varStatus="v">
					<tr>
						<td><input name="ids" type="checkbox" value="${entry.id}" />
							<input name="pkName" type="hidden" id="pkName" value="id" /></td>
						<td>${v.index + 1 }</td>
						<td>
							<div>
								<c:choose>
									<c:when test="${entry.balanceSource==1}">
                                    渠道
                                </c:when>
									<c:when test="${entry.balanceSource==2}">
                                    平台
                                </c:when>
									<c:otherwise>
                                    状态差错
                                </c:otherwise>
								</c:choose>
							</div>
						</td>
						<td>
							<div>
								<c:choose>
									<c:when test="${entry.balanceDirection==1}">
                                    正
                                </c:when>
									<c:when test="${entry.balanceDirection==2}">
                                    负
                                </c:when>
									<c:otherwise>
                                    状态差错
                                </c:otherwise>
								</c:choose>
							</div>
						</td>
						<td>
							<div>
								<c:out value="${entry.balanceAmt}" />
							</div>
						</td>
						<td>
							<div>
								<c:forEach var="entryParam" items="${channelList}" varStatus="v">
									<c:if test="${entryParam.no eq entry.channelNo}">${entryParam.name}</c:if>
								</c:forEach>
							</div>
						</td>
						<td>
							<div>
								<c:out value="${entry.platformOrderNo}" />
							</div>
						</td>
						<td>
							<div>
								<c:choose>
									<c:when test="${entry.balanceStatus==1}">
                                    调账成功
                                </c:when>
									<c:when test="${entry.balanceStatus==2}">
                                    调账失败
                                </c:when>
									<c:otherwise>
                                    状态差错
                                </c:otherwise>
								</c:choose>
							</div>
						</td>
						<td>
							<div>
								<fmt:formatDate value="${entry.createTime}"
									pattern="yyyy-MM-dd HH:mm:ss" />
							</div>
						</td>
						<td>
							<div>
								<fmt:formatDate value="${entry.updateTime}"
									pattern="yyyy-MM-dd HH:mm:ss" />
							</div>
						</td>
						<td>
							<div>
								<c:out value="${entry.balanceNote}" />
							</div>
						</td>
						<td><a href="#" onclick="show('${entry.id}')">详情</a>&nbsp; <%-- <a href="#" onclick="edit('${entry.id}')">修改</a>&nbsp;
                            <a href="#" onclick="del('${entry.id}')">删除</a>&nbsp; --%>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<yk:page url="${yk}/CheckBalance/list.do" id="logForm"
			submitForm="queryForm" joy="true" />
	</div>

	<script type="text/javascript">
    function show(id) {
        window.open('${yk}/CheckBalance/show.do?' + $('#pkName').val() + '=' + id, '', 'height=500,width=500,top=100,left=100,resizable=yes,location=no,scrollbars');
    }
    function edit(id) {
        window.open('${yk}/CheckBalance/edit.do?' + $('#pkName').val() + '=' + id, '', 'height=600,width=600,resizable=yes,location=no,scrollbars');
    }

    $(function () {

        $('#btnedit').click(function () {
            var $id = $('[name=ids]:checkbox:checked');

            if ($id.length == 0) {
                alert("请选择要修改的信息！");
                return false;
            }
            window.open('${yk}/CheckBalance/edit.do?' + $('#pkName').val() + '=' + $id.val(), '', 'height=600,width=600,resizable=yes,location=no,scrollbars');
        });

        $('#btnadd').click(function () {
            window.open('${yk}/CheckBalance/create.do', '', 'height=300,width=500,top=200,left=500,resizable=yes,location=no,scrollbars');
        });

        $('#btndelete').click(function () {
            if ($('[name=ids]:checkbox:checked').length > 0) {
                var ids = [];
                $('[name=ids]:checkbox:checked').each(function () {
                    ids.push($(this).val())
                });
                v_deleteItems(ids);
            } else {
                alert("请选择后进行删除操作！");
            }

        });

        $('#btnclear').click(function () {
            $("#channelNo").get(0).selectedIndex = 0;
            $("#balanceSource").get(0).selectedIndex = 0;
            $("#balanceDirection").get(0).selectedIndex = 0;
            $("#balanceStatus").get(0).selectedIndex = 0;
            $('#refId').val('');
            $('#balanceAmt').val('');
            $('#platformOrderNo').val('');
            $('#transNo').val('');
            $('#createBeginDate').val('');
            $('#createEndDate').val('');
            $('#updateTimeBegin').val('');
            $('#updateTimeEnd').val('');
            $('#delFlag').val('');
            $('#version').val('');
            $('#balanceNote').val('');
        });
    });

    function del(id) {
        var ids = [];
        ids.push(id);
        v_deleteItems(ids);
    }

    function v_deleteItems(ids) {
        if (ids.length > 0 && confirm("确定要删除吗？")) {
            $.post("${yk}/CheckBalance/delete.do", {"items": ids.join(',')},
                    function (data) {
                        if (data == '1') {
                            alert('删除成功');
                            $('#queryForm').submit();
                        } else {
                            alert('删除失败');
                        }
                    }
            );
        }
    }
    ;
  //导出excel
    function exportExcel() {
    	document.queryForm.action="${yk}/CheckBalance/exprot_excel.do";
		document.queryForm.submit();
		document.queryForm.action="${yk}/CheckBalance/list.do";
    }
</script>
</body>
</html>


