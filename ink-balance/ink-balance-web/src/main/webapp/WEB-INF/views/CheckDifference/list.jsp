<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>维护</title>
<%@ include file="/gen-commons/taglibs.jsp"%>
<link rel="stylesheet" href="../gen-commons/yinker-gen.css"
	type="text/css"></link>
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
		action="<c:url value="/CheckDifference/list.do"/>" method="post"
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
				<td class="txtr" style="width: 100px">支付渠道：</td>
				<td>&nbsp;<select name="channelNo" id="channelNo">
						<option value="">请选择</option>
						<c:forEach var="entry" items="${channelList}" varStatus="v">
							<option value="${entry.no}"
								<c:if test="${entry.no eq channelNo}">selected</c:if>>${entry.name}</option>
						</c:forEach>
				</select>
				</td>
				<td class="txtr" style="width: 80px">批次号：</td>
				<td>&nbsp;<input value="<c:out value="${refMainrecordId}"/>"
					id="refMainrecordId" name="refMainrecordId" style="width: 100px" />
				</td>
				<td class="txtr" style="width: 100px">差错类型：</td>
				<td>&nbsp;<select name="differenceType" id="differenceType">
						<option value="-1">请选择</option>
						<option value="1"
							<c:if test="${'1' eq differenceType}">selected</c:if>>渠道单边</option>
						<option value="2"
							<c:if test="${'2' eq differenceType}">selected</c:if>>平台单边</option>
						<option value="3"
							<c:if test="${'3' eq differenceType}">selected</c:if>>差错</option>
				</select>
				</td>
				<td class="txtr" style="width: 100px">差错来源：</td>
				<td>&nbsp;<select name="differenceSource" id="differenceSource">
						<option value="-1">请选择</option>
						<option value="1"
							<c:if test="${'1' eq differenceSource}">selected</c:if>>渠道</option>
						<option value="2"
							<c:if test="${'2' eq differenceSource}">selected</c:if>>平台</option>
				</select>
				</td>
			</tr>
			<tr>
				<td class="txtr" style="width: 100px">平台订单号：</td>
				<td>&nbsp;<input value="<c:out value="${platformOrderNo}"/>"
					id="platformOrderNo" name="platformOrderNo" maxlength="64" />
				</td>
				<td class="txtr" style="width: 100px">处理状态：</td>
				<td>&nbsp; <select class="easyui-combobox" name="handleStatus"
					id="handleStatus">
						<option value="0" selected>待处理</option>
						<option value="-1"
							<c:if test="${'-1' eq handleStatus}">selected</c:if>>全部数据</option>
						<option value="1"
							<c:if test="${'1' eq handleStatus}">selected</c:if>>处理完成</option>
						<option value="2"
							<c:if test="${'2' eq handleStatus}">selected</c:if>>挂起</option>
				</select>
				</td>
				<td class="txtr" style="width: 100px">生成日期：</td>
				<td>&nbsp;<input
					onClick="WdatePicker({startDate:'%y',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'createEndDate\')}'})"
					value="<c:out value="${createBeginDate}"/>" id="createBeginDate"
					name="createBeginDate" size="10" readonly="readonly" /> <input
					onClick="WdatePicker({startDate:'%y',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'createBeginDate\')}'})"
					value="<c:out value="${createEndDate}"/>" id="createEndDate"
					name="createEndDate" size="10" readonly="readonly" />
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

				<td></td>
			</tr>
		</table>
		<br />
		<div align="center">
			<input type="submit" class="btn01" value="查询"
				onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('0');" />
			<input type="button" class="btn01" value="清 空" id="btnclear" />
		</div>
	</form>
	<br></br>
	<div class="title01">
		<h2>列表</h2>
	</div>
	<div class="groupbtn">
		<a href="javascript:exportExcel()" title="导出明细" class="btn010"
			id="btnexcel">导出</a> <a href="javascript:void(0)" title="单边勾兑"
			class="btn002" id="btn2side">勾兑</a>
		<%--<a href="javascript:void(0)" title="挂起" class="btn001" id="hangup">挂起</a>--%>
	</div>
	<div>
		<div class="table02_xu">
			<div style="zoom: 1;">
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
							<th>支付渠道
								<div style="width: 65px; height: 1px; overflow: hidden;"></div>
							</th>
							<th>支付渠道商户号
								<div style="width: 95px; height: 1px; overflow: hidden;"></div>
							</th>
							<th>差错类型
								<div style="width: 55px; height: 1px; overflow: hidden;"></div>
							</th>
							<th>差错来源
								<div style="width: 55px; height: 1px; overflow: hidden;"></div>
							</th>
							<th>对账总表主键
								<div style="width: 80px; height: 1px; overflow: hidden;"></div>
							</th>
							<th>平台订单号
								<div style="width: 65px; height: 1px; overflow: hidden;"></div>
							</th>
							<th>金额
								<div style="width: 65px; height: 1px; overflow: hidden;"></div>
							</th>
							<th>交易状态
								<div style="width: 65px; height: 1px; overflow: hidden;"></div>
							</th>
							<th>处理状态
								<div style="width: 65px; height: 1px; overflow: hidden;"></div>
							</th>
							<th>创建日期
								<div style="width: 120px; height: 1px; overflow: hidden;"></div>
							</th>
							<th>修改日期
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
									<c:out value="${entry.refMainrecordId}" />
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
									<c:choose>
										<c:when test="${entry.differenceType==1}">
                                        渠道单边
                                    </c:when>
										<c:when test="${entry.differenceType==2}">
                                        平台单边
                                    </c:when>
										<c:when test="${entry.differenceType==3}">
                                        差错
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
										<c:when test="${entry.differenceSource==1}">
                                        渠道
                                    </c:when>
										<c:when test="${entry.differenceSource==2}">
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
									<c:out value="${entry.refMainrecordId}" />
								</div>
							</td>
							<td>
								<div>
									<c:out value="${entry.platformOrderNo}" />
								</div>
							</td>
							<td>
								<div>
									<c:out value="${entry.amount}" />
								</div>
							</td>
							<td>
								<div>
									<c:choose>
										<c:when test="${entry.status==1}">
                                        待支付
                                    </c:when>
										<c:when test="${entry.status==2}">
                                        支付成功
                                    </c:when>
										<c:when test="${entry.status==3}">
                                        支付失败
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
										<c:when test="${entry.handleStatus==0}">
                                        待处理
                                    </c:when>
										<c:when test="${entry.handleStatus==1}">
                                        处理完成
                                    </c:when>
										<c:when test="${entry.handleStatus==2}">
                                        挂起
                                    </c:when>
										<c:otherwise>
                                        状态差错
                                    </c:otherwise>
									</c:choose>
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
										pattern="yyyy-MM-dd  HH:mm:ss" />
								</div>
							</td>
							<td><a href="#" onclick="show('${entry.id}')">详情</a>&nbsp; <c:if
									test="${entry.handleStatus==1}">
									<a href="#" onclick="show_balance('${entry.platformOrderNo}')">调账详情</a>&nbsp;
                            </c:if> <%--
                                <a href="#" onclick="edit('${entry.id}')">修改</a>&nbsp;
                                <a href="#" onclick="del('${entry.id}')">删除</a>&nbsp;
                                --%></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<yk:page url="${yk}/CheckDifference/list.do" id="logForm"
			submitForm="queryForm" joy="true" />
	</div>

	<script type="text/javascript">
    function show(id) {
        window.open('${yk}/CheckDifference/show.do?' + $('#pkName').val() + '=' + id, '', 'height=500,width=500,top=100,left=100,resizable=yes,location=no,scrollbars');
    }
    function show_balance(id) {
        window.open('${yk}/CheckDifference/showBalance.do?'+'platformOrderNo='+ id, '', 'height=500,width=500,top=100,left=100,resizable=yes,location=no,scrollbars');
    }

    //勾兑双单边情况
    $('#btn2side').click(function () {
        if ($('[name=ids]:checkbox:checked').length == 2) {
            var ids = [];
            $('[name=ids]:checkbox:checked').each(function () {
                ids.push($(this).val())
            });
            handle2side(ids);
            //alert("SUCCESS"=="SUCCESS");
        } else {
            alert("请选择两项后进行勾兑操作！");
        }

    });

    function handle2side(ids) {
        var message = "勾兑双单边[" + ids + "]，是否确认？";
        if (ids.length > 0 && confirm(message)) {
            $.post("${yk}/CheckDifference/handle2side.do", {"ids": ids.join(',')},
                    function (data) {
                        if (data == 'SUCCESS') {
                            alert('操作成功');
                            $('#queryForm').submit();
                        } else if (data == 'ERROR_ONE_SIDE') {
                            alert('请选择两方数据进行勾兑！');
                        } else {
                            alert('操作失败,请核验勾兑数据！');
                        }
                    }
            );
        }
    }

    //挂起
    $('#hangup').click(function () {
        var $id = $('[name=ids]:checkbox:checked');
        if ($id.length != 1) {
            alert("请选择一项项后进行勾兑操作！");
            return false;
        }
        var id;
        $('[name=ids]:checkbox:checked').each(function () {
            id=$(this).val();
        });
        hangup(id);

    });

    function hangup(id) {
        var message = "挂起[" + id + "]记录，是否确认？";
        if ( confirm(message)) {
            $.post("${yk}/CheckDifference/hangup.do", {"id": id},
                    function (data) {
                        if (data == 'SUCCESS') {
                            alert('操作成功');
                            $('#queryForm').submit();
                        } else if (data == 'ERROR_TYPE') {
                            alert('记录差异类型不符，无法挂起！');
                        } else if (data == 'ERROR_STATUS') {
                            alert('交易状态非失败 无法挂起！');
                        }else if (data == 'ERROR_HANDLESTATUS') {
                            alert('非待处理的数据，无法挂起！');
                        } else if (data == 'ERROR') {
                            alert('更新异常，无法挂起！');
                        } else {
                            alert('操作失败,核对！');
                        }
                    }
            );
        }
    }

    //导出excel
    function exportExcel() {
        var params = $("#queryForm").serializeObject();
        $.ajax({
            url: '${yk}/CheckDifference/select_one.do',    //请求的url地址
            data: params,
            dataType: "json",   //返回格式为json
            async: true, //请求是否异步，默认为异步，这也是ajax重要特性
            type: "post",   //请求方式
            success: function (data) {
                if (data.total > 0) {
                    var url = "${yk}/CheckDifference/exprot_excel.do";   //请求的url地址
                    var form = $("<form>");   //定义一个form表单
                    form.attr('style', 'display:none');   //在form表单中添加查询参数
                    form.attr('target', '');
                    form.attr('method', 'post');
                    form.attr('action', url);

                    var input1 = $('<input>');
                    input1.attr('type', 'hidden');
                    input1.attr('name', 'channelNo');
                    input1.attr('value', params.channelNo);

                    var input2 = $('<input>');
                    input2.attr('type', 'hidden');
                    input2.attr('name', 'createBeginDate');
                    input2.attr('value', params.createBeginDate);

                    var input3 = $('<input>');
                    input3.attr('type', 'hidden');
                    input3.attr('name', 'createEndDate');
                    input3.attr('value', params.createEndDate);

                    var input4 = $('<input>');
                    input4.attr('type', 'hidden');
                    input4.attr('name', 'differenceSource');
                    input4.attr('value', params.differenceSource);

                    var input5 = $('<input>');
                    input5.attr('type', 'hidden');
                    input5.attr('name', 'differenceType');
                    input5.attr('value', params.differenceType);

                    var input6 = $('<input>');
                    input6.attr('type', 'hidden');
                    input6.attr('name', 'handleStatus');
                    input6.attr('value', params.handleStatus);

                    var input7 = $('<input>');
                    input7.attr('type', 'hidden');
                    input7.attr('name', 'platformOrderNo');
                    input7.attr('value', params.platformOrderNo);


                    $('body').append(form);  //将表单放置在web中
                    form.append(input1);   //将查询参数控件提交到表单上
                    form.append(input2);   //将查询参数控件提交到表单上
                    form.append(input3);   //将查询参数控件提交到表单上
                    form.append(input4);   //将查询参数控件提交到表单上
                    form.append(input5);   //将查询参数控件提交到表单上
                    form.append(input6);   //将查询参数控件提交到表单上
                    form.append(input7);   //将查询参数控件提交到表单上
                    form.submit();

                } else {
                    alert('MyTitle', '该查询条件下无符合结果!', 'warning');
                }
            }
        });

    }

    $(function () {

        $('#btnedit').click(function () {
            var $id = $('[name=ids]:checkbox:checked');

            if ($id.length == 0) {
                alert("请选择要修改的信息！");
                return false;
            }
            window.open('${yk}/CheckDifference/edit.do?' + $('#pkName').val() + '=' + $id.val(), '', 'height=600,width=600,resizable=yes,location=no,scrollbars');
        });

        $('#btnadd').click(function () {
            window.open('${yk}/CheckDifference/create.do', '', 'height=600,width=600,resizable=yes,location=no,scrollbars');
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
            $("#differenceType").get(0).selectedIndex = 0;
            $("#differenceSource").get(0).selectedIndex = 0;
            $("#handleStatus").get(0).selectedIndex = 0;
            $('#channelMerchantNo').val('');
            $('#refMainrecordId').val('');
            $('#seqNo').val('');
            $('#platformOrderNo').val('');
            $('#amount').val('');
            $('#status').val('');
            $('#dateBegin').val('');
            $('#dateEnd').val('');
            $('#handleNotes').val('');
            $('#createBeginDate').val('');
            $('#createEndDate').val('');
            $('#updateDateBegin').val('');
            $('#updateDateEnd').val('');
            $('#operatorId').val('');
            $('#delFlag').val('');
            $('#version').val('');
            $('#remark').val('');
            $('#channelMerchantNo').val('');
            
        });
    });

    function del(id) {
        var ids = [];
        ids.push(id);
        v_deleteItems(ids);
    }

    function v_deleteItems(ids) {
        if (ids.length > 0 && confirm("确定要删除吗？")) {
            $.post("${yk}/CheckDifference/delete.do", {"items": ids.join(',')},
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
</script>
</body>
</html>


