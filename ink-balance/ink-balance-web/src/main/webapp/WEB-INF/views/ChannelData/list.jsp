<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>渠道</title>
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
		action="<c:url value="/ChannelData/list.do"/>" method="post"
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
				<td class="txtr" style="width: 100px">平台订单号：</td>
				<td>&nbsp;<input value="<c:out value="${platformOrderNo}"/>"
					id="platformOrderNo" name="platformOrderNo" maxlength="64" />
				</td>
				<td class="txtr" style="width: 100px">交易流水号：</td>
				<td>&nbsp;<input value="<c:out value="${transNo}"/>"
					id="transNo" name="transNo" maxlength="64" />
				</td>
				<td class="txtr" style="width: 100px">对账状态 ：</td>
				<td>&nbsp;<select name="checkStatus" id="checkStatus">
						<option value="-1">请选择</option>
						<option value="0"
							<c:if test="${'0' eq checkStatus}">selected</c:if>>初始</option>
						<option value="1"
							<c:if test="${'1' eq checkStatus}">selected</c:if>>未匹配</option>
						<option value="2"
							<c:if test="${'2' eq checkStatus}">selected</c:if>>已匹配</option>
						<option value="3"
							<c:if test="${'3' eq checkStatus}">selected</c:if>>差错</option>
						<option value="4"
							<c:if test="${'4' eq checkStatus}">selected</c:if>>调账后匹配</option>
				</select>
				</td>
			</tr>
			<tr>
				<td class="txtr" style="width: 100px">交易时间：</td>
				<td>&nbsp;<input
					onClick="WdatePicker({startDate:'%y',dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'tradeEndTime\')}'})"
					value="<c:out value="${tradeBeginTime}"/>" id="tradeBeginTime"
					name="tradeBeginTime" size="10" readonly="readonly" /> <input
					onClick="WdatePicker({startDate:'%y',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'tradeBeginTime\')}'})"
					value="<c:out value="${tradeEndTime}"/>" id="tradeEndTime"
					name="tradeEndTime" size="10" readonly="readonly" />
				</td>
				<td class="txtr" style="width: 100px">交易状态：</td>
				<td>&nbsp;<select name="transStatus" id="transStatus">
						<option value="0">请选择</option>
						<option value="1"
							<c:if test="${'1' eq transStatus}">selected</c:if>>待支付</option>
						<option value="2"
							<c:if test="${'2' eq transStatus}">selected</c:if>>支付成功</option>
						<option value="3"
							<c:if test="${'3' eq transStatus}">selected</c:if>>支付失败</option>
				</select>
				</td>
				<td class="txtr" style="width: 100px">商户编号：</td>
				<td>&nbsp;<select name="merchantNo" id="merchantNo">
						<option value="">请选择</option>
						<option value="CF2000027305"
							<c:if test="${'CF2000027305' eq merchantNo}">selected</c:if>>民生-简理财
						</option>
						<option value="686062"
							<c:if test="${'686062' eq merchantNo}">selected</c:if>>宝付-简理财</option>
				</select>
				</td>
				<td class="txtr" style="width: 100px">驻留标识：</td>
				<td>&nbsp;<select name="resideFlag" id="resideFlag">
						<option value="-1">请选择</option>
						<option value="0"
							<c:if test="${'0' eq resideFlag}">selected</c:if>>非驻留</option>
						<option value="1"
							<c:if test="${'1' eq resideFlag}">selected</c:if>>驻留</option>
				</select>
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
		<a href="javascript:void(0)" title="新建" class="btn015" id="btnexport">导入</a>

		<!--
        <a href="javascript:void(0)" title="修改" class="btn002" id="btnedit">修改</a>
        <a href="javascript:void(0)" title="删除"  class="btn004" id="btndelete">删除</a>
    -->
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
						<th>商户编号
							<div style="width: 75px; height: 1px; overflow: hidden;"></div>
						</th>
						<th>支付渠道
							<div style="width: 55px; height: 1px; overflow: hidden;"></div>
						</th>
						<th>平台订单号
							<div style="width: 65px; height: 1px; overflow: hidden;"></div>
						</th>
						<%--<th>交易流水号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>--%>
						<th>订单金额
							<div style="width: 55px; height: 1px; overflow: hidden;"></div>
						</th>
						<th>实收金额
							<div style="width: 55px; height: 1px; overflow: hidden;"></div>
						</th>
						<th>交易时间
							<div style="width: 120px; height: 1px; overflow: hidden;"></div>
						</th>
						<th>交易状态
							<div style="width: 55px; height: 1px; overflow: hidden;"></div>
						</th>
						<%--<th>支付方式<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>--%>
						<th>对账状态
							<div style="width: 60px; height: 1px; overflow: hidden;"></div>
						</th>
						<th>驻留标识
							<div style="width: 55px; height: 1px; overflow: hidden;"></div>
						</th>
						<th>驻留截止日
							<div style="width: 65px; height: 1px; overflow: hidden;"></div>
						</th>
						<th>创建时间
							<div style="width: 120px; height: 1px; overflow: hidden;"></div>
						</th>
						<th>更新时间
							<div style="width: 120px; height: 1px; overflow: hidden;"></div>
						</th>
						<%--				<th>删除标志<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
                                <th>版本号<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>
                                <th>备注<div style="width: 65px; height: 1px; overflow: hidden;"></div></th>--%>
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
									<c:when test="${'CF2000027305' eq entry.merchantNo}">
                                    民生-简理财
                                </c:when>
									<c:when test="${'686062' eq entry.merchantNo}">
                                    宝付-简理财
                                </c:when>
									<c:otherwise>
										<div>
											<c:out value="${entry.merchantNo}" />
										</div>
									</c:otherwise>
								</c:choose>
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
								<c:out value="${entry.amt}" />
							</div>
						</td>
						<td>
							<div>
								<c:out value="${entry.receivedAmt}" />
							</div>
						</td>
						<td>
							<div>
								<fmt:formatDate value="${entry.transTime}"
									pattern="yyyy-MM-dd HH:mm:ss" />
							</div>
						</td>
						<td>
							<div>
								<c:choose>
									<c:when test="${entry.transStatus==1}">
                                    待支付
                                </c:when>
									<c:when test="${entry.transStatus==2}">
                                    支付成功
                                </c:when>
									<c:when test="${entry.transStatus==3}">
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
									<c:when test="${entry.checkStatus==0}">
                                    初始
                                </c:when>
									<c:when test="${entry.checkStatus==1}">
                                    未匹配
                                </c:when>
									<c:when test="${entry.checkStatus==2}">
                                    已匹配
                                </c:when>
									<c:when test="${entry.checkStatus==3}">
                                    差错
                                </c:when>
									<c:when test="${entry.checkStatus==4}">
                                    调账后匹配
                                </c:when>
									<c:otherwise>
                                    未知状态
                                </c:otherwise>
								</c:choose>
							</div>
						</td>
						<td>
							<div>
								<c:choose>
									<c:when test="${entry.resideFlag==0}">
                                    非驻留
                                </c:when>
									<c:when test="${entry.resideFlag==1}">
                                    驻留
                                </c:when>
									<c:otherwise>
                                    差错
                                </c:otherwise>
								</c:choose>
							</div>
						</td>
						<td>
							<div>
								<fmt:formatDate value="${entry.resideToDate}"
									pattern="yyyy-MM-dd" />
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
						<td><a href="#" onclick="show('${entry.id}')">详情</a>&nbsp; <%--      <a href="#" onclick="del('${entry.id}')">删除</a>&nbsp;
                           --%></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<yk:page url="${yk}/ChannelData/list.do" id="logForm"
			submitForm="queryForm" joy="true" />
	</div>

	<script type="text/javascript">
    function show(id) {
        window.open('${yk}/ChannelData/show.do?' + $('#pkName').val() + '=' + id, '', 'height=500,width=500,top=100,left=100,resizable=yes,location=no,scrollbars');
    }
    function edit(id) {
        window.open('${yk}/ChannelData/edit.do?' + $('#pkName').val() + '=' + id, '', 'height=600,width=600,resizable=yes,location=no,scrollbars');
    }

    $(function () {

        $('#btnedit').click(function () {
            var $id = $('[name=ids]:checkbox:checked');

            if ($id.length == 0) {
                alert("请选择要修改的信息！");
                return false;
            }
            window.open('${yk}/ChannelData/edit.do?' + $('#pkName').val() + '=' + $id.val(), '', 'height=600,width=600,resizable=yes,location=no,scrollbars');
        });

        $('#btnadd').click(function () {
            window.open('${yk}/ChannelData/create.do', '', 'height=600,width=600,resizable=yes,location=no,scrollbars');
        });
        $('#btnexport').click(function () {
            window.open('${yk}/ChannelData/export.do', '', 'height=600,width=600,resizable=yes,location=no,scrollbars');
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
            $("#transStatus").get(0).selectedIndex = 0;
            $("#checkStatus").get(0).selectedIndex = 0;
            $("#resideFlag").get(0).selectedIndex = 0;
            $('#platformOrderNo').val('');
            $('#transNo').val('');
            $('#amt').val('');
            $('#receivedAmt').val('');
            $('#tradeBeginTime').val('');
            $('#tradeEndTime').val('');
            $('#payMethod').val('');
            $('#resideToDateBegin').val('');
            $('#resideToDateEnd').val('');
            $('#createTimeBegin').val('');
            $('#createTimeEnd').val('');
            $('#updateTimeBegin').val('');
            $('#updateTimeEnd').val('');
            $('#delFlag').val('');
            $('#version').val('');
            $('#remark').val('');
            $('#merchantNo').val('');

        });
    });

    function del(id) {
        var ids = [];
        ids.push(id);
        v_deleteItems(ids);
    }

    function v_deleteItems(ids) {
        if (ids.length > 0 && confirm("确定要删除吗？")) {
            $.post("${yk}/ChannelData/delete.do", {"items": ids.join(',')},
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

    function insertmq() {
        $.post("${yk}/ChannelData/insertMq.do",
                function (data) {
                    alert('生产MQ成功');
                }
        );
    }
    ;

</script>
</body>
</html>


