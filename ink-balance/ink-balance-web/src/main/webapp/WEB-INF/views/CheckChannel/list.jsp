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
		action="<c:url value="/CheckChannel/list.do"/>" method="post"
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
				<td class="txtr" style="width: 100px">对账日期：</td>
				<td>&nbsp;<input
					onClick="WdatePicker({startDate:'%y',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'checkEndTime\')}'})"
					value="<c:out value="${checkBeginTime}"/>" id="checkBeginTime"
					name="checkBeginTime" size="10" readonly="readonly" /> <input
					onClick="WdatePicker({startDate:'%y',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'checkBeginTime\')}'})"
					value="<c:out value="${checkEndTime}"/>" id="checkEndTime"
					name="checkEndTime" size="10" readonly="readonly" />
				</td>
				<td class="txtr" style="width: 100px">交易日期：</td>
				<td>&nbsp;<input
					onClick="WdatePicker({startDate:'%y',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'tradeEndTime\')}'})"
					value="<c:out value="${tradeBeginTime}"/>" id="tradeBeginTime"
					name="tradeBeginTime" size="10" readonly="readonly" /> <input
					onClick="WdatePicker({startDate:'%y',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'tradeBeginTime\')}'})"
					value="<c:out value="${tradeEndTime}"/>" id="tradeEndTime"
					name="tradeEndTime" size="10" readonly="readonly" />
				</td>
				<td class="txtr" style="width: 100px">渠道编号：</td>
				<td>&nbsp;<select name="channelNo" id="channelNo">
						<option value="">请选择</option>
						<c:forEach var="entry" items="${channelList}" varStatus="v">
							<option value="${entry.no}"
								<c:if test="${entry.no eq channelNo}">selected</c:if>>${entry.name}</option>
						</c:forEach>
				</select>
				</td>
				<td class="txtr" style="width: 100px">商户编号：</td>
				<td>&nbsp;<select name="channelMerchantNo"
					id="channelMerchantNo">
						<option value="">请选择</option>
						<option value="CF2000027305"
							<c:if test="${'CF2000027305' eq channelMerchantNo}">selected</c:if>>民生-简理财</option>
						<option value="686062"
							<c:if test="${'686062' eq channelMerchantNo}">selected</c:if>>宝付-简理财</option>
				</select>
				</td>
			</tr>
			<tr>
				<td class="txtr" style="width: 100px">对账结果：</td>
				<td>&nbsp;<select name="checkStatus" id="checkStatus">
						<option value="-1">请选择</option>
						<option value="1"
							<c:if test="${'1' eq checkStatus}">selected</c:if>>匹配</option>
						<option value="2"
							<c:if test="${'2' eq checkStatus}">selected</c:if>>未匹配</option>
				</select>
				</td>
				<td class="txtr" style="width: 100px">处理状态：</td>
				<td>&nbsp;<select name="adjustStatus" id="adjustStatus">
						<option value="-1">请选择</option>
						<option value="0"
							<c:if test="${'0' eq adjustStatus}">selected</c:if>>待处理</option>
						<option value="1"
							<c:if test="${'1' eq adjustStatus}">selected</c:if>>处理完成</option>
				</select>
				</td>
				<td class="txtr" style="width: 80px">批次号：</td>
				<td>&nbsp;<input value="<c:out value="${id}"/>" id="id"
					name="id" style="width: 100px" />
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
	<br><br>
			<div class="title01">
				<h2>列表</h2>
			</div>
			<div class="groupbtn">
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
								<th>批次号
									<div style="width: 60px; height: 1px; overflow: hidden;"></div>
								</th>
								<th>对账日期
									<div style="width: 65px; height: 1px; overflow: hidden;"></div>
								</th>
								<th>交易日期
									<div style="width: 65px; height: 1px; overflow: hidden;"></div>
								</th>
								<th>渠道编号
									<div style="width: 65px; height: 1px; overflow: hidden;"></div>
								</th>
								<th>支付渠道商户号
									<div style="width: 95px; height: 1px; overflow: hidden;"></div>
								</th>
								<th>渠道总金额
									<div style="width: 65px; height: 1px; overflow: hidden;"></div>
								</th>
								<th>渠道总笔数
									<div style="width: 65px; height: 1px; overflow: hidden;"></div>
								</th>
								<th>平台总金额
									<div style="width: 65px; height: 1px; overflow: hidden;"></div>
								</th>
								<th>平台总笔数
									<div style="width: 65px; height: 1px; overflow: hidden;"></div>
								</th>
								<th>匹配总笔数
									<div style="width: 65px; height: 1px; overflow: hidden;"></div>
								</th>
								<th>平台单边笔数
									<div style="width: 95px; height: 1px; overflow: hidden;"></div>
								</th>
								<th>渠道单边笔数
									<div style="width: 95px; height: 1px; overflow: hidden;"></div>
								</th>
								<th>对账结果
									<div style="width: 60px; height: 1px; overflow: hidden;"></div>
								</th>
								<th>差异总笔数
									<div style="width: 65px; height: 1px; overflow: hidden;"></div>
								</th>
								<th>处理状态
									<div style="width: 65px; height: 1px; overflow: hidden;"></div>
								</th>
								<th>未处理差异笔数
									<div style="width: 95px; height: 1px; overflow: hidden;"></div>
								</th>
								<th>创建时间
									<div style="width: 120px; height: 1px; overflow: hidden;"></div>
								</th>
								<th>更新时间
									<div style="width: 120px; height: 1px; overflow: hidden;"></div>
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
										<c:out value="${entry.id}" />
									</div>
								</td>
								<td>
									<div>
										<fmt:formatDate value="${entry.checkDate}"
											pattern="yyyy-MM-dd" />
									</div>
								</td>
								<td>
									<div>
										<fmt:formatDate value="${entry.tradeDate}"
											pattern="yyyy-MM-dd" />
									</div>
								</td>
								<td>
									<div>
										<c:forEach var="entryParam" items="${channelList}"
											varStatus="v">
											<c:if test="${entryParam.no eq entry.channelNo}">${entryParam.name}</c:if>
										</c:forEach>
									</div>
								</td>
								<td>
									<div>
										<c:choose>
											<c:when test="${'CF2000027305' eq entry.channelMerchantNo}">
                          		      民生-简理财
                                </c:when>
											<c:when test="${'686062' eq entry.channelMerchantNo}">
                                      宝付-简理财
                                </c:when>
											<c:otherwise>
												<div>
													<c:out value="${entry.channelMerchantNo}" />
												</div>
											</c:otherwise>
										</c:choose>
									</div>
								</td>
								<td>
									<div>
										<c:out value="${entry.channelAmount}" />
									</div>
								</td>
								<td>
									<div>
										<c:out value="${entry.channelCount}" />
									</div>
								</td>
								<td>
									<div>
										<c:out value="${entry.platformAmount}" />
									</div>
								</td>
								<td>
									<div>
										<c:out value="${entry.platformCount}" />
									</div>
								</td>
								<td>
									<div>
										<c:out value="${entry.matchCount}" />
									</div>
								</td>
								<td>
									<div>
										<c:out value="${entry.platformSideCount}" />
									</div>
								</td>
								<td>
									<div>
										<c:out value="${entry.channelSideCount}" />
									</div>
								</td>
								<td>
									<div>
										<c:choose>
											<c:when test="${entry.checkStatus==1}">
                                    匹配
                                </c:when>
											<c:when test="${entry.checkStatus==2}">
                                    未匹配
                                </c:when>
											<c:otherwise>
                                    数据异常
                                </c:otherwise>
										</c:choose>
									</div>
								</td>
								<td>
									<div>
										<c:out value="${entry.differenceCount}" />
									</div>
								</td>
								<td>
									<div>
										<c:choose>
											<c:when test="${entry.adjustStatus==0}">
                                    待处理
                                </c:when>
											<c:when test="${entry.adjustStatus==1}">
                                    处理完成
                                </c:when>
											<c:otherwise>
                                    状态差错
                                </c:otherwise>
										</c:choose>
									</div>
								</td>
								<td>
									<div>
										<c:out value="${entry.unhandleCount}" />
									</div>
								</td>
								<td><fmt:formatDate value="${entry.createDate}"
										pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td>
									<div>
										<fmt:formatDate value="${entry.updateDate}"
											pattern="yyyy-MM-dd HH:mm:ss" />
									</div>
								</td>
								<td><a href="#" onclick="show('${entry.id}')">详情</a>&nbsp;
									<%-- <a href="#" onclick="edit('${entry.id}')">修改</a>&nbsp;
                            <a href="#" onclick="del('${entry.id}')">删除</a>&nbsp; --%>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<yk:page url="${yk}/CheckChannel/list.do" id="logForm"
					submitForm="queryForm" joy="true" />
			</div> <script type="text/javascript">
    function show(id) {
        window.open('${yk}/CheckChannel/show.do?' + $('#pkName').val() + '=' + id, '', 'height=600,width=600,resizable=yes,location=no,scrollbars');
    }
    function edit(id) {
        window.open('${yk}/CheckChannel/edit.do?' + $('#pkName').val() + '=' + id, '', 'height=600,width=600,resizable=yes,location=no,scrollbars');
    }

    $(function () {

        $('#btnedit').click(function () {
            var $id = $('[name=ids]:checkbox:checked');

            if ($id.length == 0) {
                alert("请选择要修改的信息！");
                return false;
            }
            window.open('${yk}/CheckChannel/edit.do?' + $('#pkName').val() + '=' + $id.val(), '', 'height=600,width=600,resizable=yes,location=no,scrollbars');
        });

        $('#btnadd').click(function () {
            window.open('${yk}/CheckChannel/create.do', '', 'height=600,width=600,resizable=yes,location=no,scrollbars');
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
            $("#checkStatus").get(0).selectedIndex = 0;
            $("#adjustStatus").get(0).selectedIndex = 0;
            $('#checkBeginTime').val('');
            $('#checkEndTime').val('');
            $('#tradeBeginTime').val('');
            $('#tradeEndTime').val('');
            $('#channelMerchantNo').val('');
            $('#channelAmount').val('');
            $('#channelCount').val('');
            $('#platformAmount').val('');
            $('#platformCount').val('');
            $('#platformSideCount').val('');
            $('#channelSideCount').val('');
            $('#differenceCount').val('');
            $('#channelCheckFile').val('');
            $('#fileSource').val('');
            $('#unhandleCount').val('');
            $('#handleNotes').val('');
            $('#remark').val('');
            $('#createDateBegin').val('');
            $('#createDateEnd').val('');
            $('#updateDateBegin').val('');
            $('#updateDateEnd').val('');
            $('#operatorId').val('');
            $('#version').val('');
            $('#channelMerchantNo').val('');
            $('#id').val('');
            
        });
    });

    function del(id) {
        var ids = [];
        ids.push(id);
        v_deleteItems(ids);
    }

    function v_deleteItems(ids) {
        if (ids.length > 0 && confirm("确定要删除吗？")) {
            $.post("${yk}/CheckChannel/delete.do", {"items": ids.join(',')},
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
    	document.queryForm.action="${yk}/CheckChannel/exprot_excel.do";
		document.queryForm.submit();
		document.queryForm.action="${yk}/CheckChannel/list.do";
    }
</script>
</body>
</html>


