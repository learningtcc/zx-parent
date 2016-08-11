
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/gen-commons/taglibs.jsp"%>
<script src="http://code.jquery.com/jquery-latest.js"></script>
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
		action="<c:url value="/AsileBank/prioritylist.do"/>" method="post"
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
<!-- 				<td class="txtr" style="width: 100px">渠道名称：</td> -->
<%-- 				<td>&nbsp;<input value="<c:out value="${asileName}"/>" --%>
<!-- 					id="asileName" name="asileName" maxlength="16" /> -->
<!-- 				</td> -->
<!-- 				<td class="txtr" style="width: 100px">渠道编码：</td> -->
<%-- 				<td>&nbsp;<input value="<c:out value="${asileCode}"/>" --%>
<!-- 					id="asileCode" name="asileCode" maxlength="32" /> -->
<!-- 				</td> -->
				<td class="txtr" style="width: 100px">银行名称：</td>
				<td>&nbsp;
				<yk:constantConvert htmlTag="select" dataTag="bankName" codeTag="bankName" nameTag="bankName" data="${basicBanks}" name="bankName" isAutoItem="false" emptyTip="0"/>
<!-- 				<input id="bankName" name="bankName" type="text" class="input04"   /> -->
				
				</td>
<%-- 				<td>&nbsp;<input value="<c:out value="${bankName}"/>" --%>
<!-- 					id="bankName" name="bankName" maxlength="16" /> -->
<!-- 				</td> -->
<!-- 				<td class="txtr" style="width: 100px">银行编码：</td> -->
<%-- 				<td>&nbsp;<input value="<c:out value="${bankCode}"/>" --%>
<!-- 					id="bankCode" name="bankCode" maxlength="32" /> -->
<!-- 				</td> -->

<!-- 				<td class="txtr" style="width: 100px">银行简码：</td> -->
<%-- 				<td>&nbsp;<input value="<c:out value="${bankShort}"/>" --%>
<!-- 					id="bankShort" name="bankShort" maxlength="10" /> -->
<!-- 				</td> -->
				<td class="txtr" style="width: 188px">
					支付方式<label style="color: red">*</label>：
				</td>	
				<td>
				<yk:constantConvert htmlTag="select" dataTag="asilePayType" codeTag="code" nameTag="value" data="${routeBusinessType}" name="asilePayType" isAutoItem="false" emptyTip="0"/>
<!-- 				<input id="asilePayType" name="asilePayType" type="text" class="input04"   /> -->
				
				</td>
			</tr>
		</table>
		<br />
		<div align="center">
			<input type="submit" class="btn01" value="查询"
				onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('0');" />
<!-- 			<input type="submit" class="btn01" value="模糊查询" -->
<!-- 				onclick="$('#pageNumber').val('1');$('#pageSize').val('10');$('#queryType').val('1');" /> -->
<!-- 			<input type="button" class="btn01" value="清 空" id="btnclear" /> -->
		</div>
	</form>
	<br></br>
	<div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tb"
			class="table02">
			<thead>
				<tr>
					<th><input id="allChoose" type="checkbox" /></th>
					<th>序号
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>通道名称
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>通道id
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>银行名称
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>银行id
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<!-- 				<th>通道产品编码<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
					<!-- 				<th>通道产品名称<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
					<!-- 				<th>是否直连<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
					<!-- 				<th>对公对私<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
					<!-- 				<th>提供接口方式<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
					<!-- 				<th>鉴权方式<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
					<!-- 				<th>鉴权要素(转换为二进制位数表示)<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
					<!-- 				<th>授权方式<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
					<!-- 				<th>支持银行卡类型<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
					<th>支付类型
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>单笔限额
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<!-- 				<th>单卡单日限额<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
					<!-- 				<th>单卡单月限额<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
					<!-- 				<th>单卡单日限额<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
					<!-- 				<th>批量限额<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
					<!-- 				<th>批量限次<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
					<th>备注
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>创建时间
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<!-- <th>createrId<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
					<th>创建人
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>修改时间
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<!-- <th>updaterId<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
					<th>修改人
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<!-- <th>是否删除<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
					<!-- 				<th>回盘时间<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
					<th>通道服务开始时间
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>通道服务结束时间
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
<!-- 					<th>优先级 -->
<!-- 						<div style="width: 65px; height: 1px; overflow: hidden;"></div> -->
<!-- 					</th> -->
					<!-- 				<th>0是分离，1是不分<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
					<!-- 				<th>短信发送方<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
					<th>银行简码
						<div style="width: 65px; height: 1px; overflow: hidden;"></div>
					</th>
					<!-- 				<th>渠道银行编码<div style="width: 65px; height: 1px; overflow: hidden;"></div></th> -->
					<th>升级
						<div style="width: 75px; height: 1px; overflow: hidden;"></div>
					</th>
					<th>降级
						<div style="width: 75px; height: 1px; overflow: hidden;"></div>
					</th>
<!-- 					<th>置顶 -->
<!-- 						<div style="width: 75px; height: 1px; overflow: hidden;"></div> -->
<!-- 					</th> -->
				</tr>
			</thead>
			<c:forEach var="entry" items="${page.result}" varStatus="v">
				<tr>
					<td style="display: none;">
							<c:out value="${entry.id}" />
						</td>
					<td><input name="ids" type="checkbox" value="${entry.id}" /> <input
						name="pkName" type="hidden" id="pkName" value="id" /> <input
						name="priority" type="hidden" id="priority"
						value="${entry.priority}"></input></td>
					<td>${(page.pageSize*(page.pageNumber-1))+(v.index + 1) }</td>
					<td>
						<div>
							<c:out value="${entry.asileName}" />
						</div>
					</td>
					<td>
						<div>
							<c:out value="${entry.asileCode}" />
						</div>
					</td>
					<td>
						<div>
							<c:out value="${entry.bankName}" />
						</div>
					</td>
					<td>
						<div>
							<c:out value="${entry.bankCode}" />
						</div>
					</td>
					<!-- 				<td> -->
					<%-- 				<div><c:out value="${entry.asileProductCode}"/></div> --%>
					<!-- 				</td> -->
					<!-- 				<td> -->
					<%-- 				<div><c:out value="${entry.asileProductName}"/></div> --%>
					<!-- 				</td> -->
					<!-- 				<td> -->
					<%-- 				<div><c:out value="${entry.asileDirectBank}"/></div> --%>
					<!-- 				</td> -->
					<!-- 				<td> -->
					<%-- 				<div><c:out value="${entry.asilePublicPrivate}"/></div> --%>
					<!-- 				</td> -->
					<!-- 				<td> -->
					<%-- 				<div><c:out value="${entry.asileIntefaceType}"/></div> --%>
					<!-- 				</td> -->
					<!-- 				<td> -->
					<%-- 				<div><c:out value="${entry.asileAuthType}"/></div> --%>
					<!-- 				</td> -->
					<!-- 				<td> -->
					<%-- 				<div><c:out value="${entry.asileAuthElements}"/></div> --%>
					<!-- 				</td> -->
					<!-- 				<td> -->
					<%-- 				<div><c:out value="${entry.asileAuthMode}"/></div> --%>
					<!-- 				</td> -->
					<!-- 				<td> -->
					<%-- 				<div><c:out value="${entry.asileBankType}"/></div> --%>
					<!-- 				</td> -->
					<td><yk:constantConvert htmlTag="text" dataTag="asilePayType"
							codeTag="code" paramValue="${entry.asilePayType}" nameTag="value"
							data="${routeBusinessType}" name="asilePayType"
							isAutoItem="false" emptyTip="0" /></td>
					<td>
						<div>
							<c:out value="${entry.asileCrashLimit}" />
						</div>
					</td>
					<!-- 				<td> -->
					<%-- 				<div><c:out value="${entry.cardCrashDayLimit}"/></div> --%>
					<!-- 				</td> -->
					<!-- 				<td> -->
					<%-- 				<div><c:out value="${entry.cardCrashMonthLimit}"/></div> --%>
					<!-- 				</td> -->
					<!-- 				<td> -->
					<%-- 				<div><c:out value="${entry.cardDayLimits}"/></div> --%>
					<!-- 				</td> -->
					<!-- 				<td> -->
					<%-- 				<div><c:out value="${entry.batchCrashLimit}"/></div> --%>
					<!-- 				</td> -->
					<!-- 				<td> -->
					<%-- 				<div><c:out value="${entry.batchLimit}"/></div> --%>
					<!-- 				</td> -->
					<td>
						<div>
							<c:out value="${entry.remark}" />
						</div>
					</td>
					<td>
						<div>
							<c:out value="${entry.createTimeString}" />
						</div>
					</td>
					<%-- <td>
				<div><c:out value="${entry.createrId}"/></div>
				</td> --%>
					<td>
						<div>
							<c:out value="${entry.createrName}" />
						</div>
					</td>
					<td>
						<div>
							<c:out value="${entry.updateTimeString}" />
						</div>
					</td>
					<%-- <td>
				<div><c:out value="${entry.updaterId}"/></div>
				</td> --%>
					<td>
						<div>
							<c:out value="${entry.updaterName}" />
						</div>
					</td>
					<%-- <td>
				<div><c:out value="${entry.isDelete}"/></div>
				</td> --%>
					<!-- 				<td> -->
					<%-- 				<div><c:out value="${entry.asileOnlyTimeString}"/></div> --%>
					<!-- 				</td> -->
					<td>
						<div>
							<c:out value="${entry.asileServiceBeginTimeString}" />
						</div>
					</td>
					<td>
						<div>
							<c:out value="${entry.asileServiceEndTimeString}" />
						</div>
					</td>
<!-- 					<td> -->
<!-- 						<div> -->
<%-- 							<c:out value="${entry.priority}" /> --%>
<!-- 						</div> -->
<!-- 					</td> -->
					<!-- 				<td> -->
					<%-- 				<div><c:out value="${entry.borrowSplit}"/></div> --%>
					<!-- 				</td> -->
					<!-- 				<td> -->
					<%-- 				<div><c:out value="${entry.smsSender}"/></div> --%>
					<!-- 				</td> -->
					<td>
						<div>
							<c:out value="${entry.bankShort}" />
						</div>
					</td>
					<td align="center" bgcolor="#FFFFCC"><a
						href="#" class="up">上移</a></td>
					<td align="center" bgcolor="#FFFFCC"><a
						href="#" class="down">下移</a></td>
<!--                     <td align="center" bgcolor="#FFFFCC"><a -->
<!-- 						href="#" class="top">置顶</a></td> -->
				</tr>
			</c:forEach>
		</table>
		<yk:page url="${yk}/AsileBank/prioritylist.do" id="logForm"
			submitForm="queryForm" joy="true" />
	</div>

	<script type="text/javascript">
		function show(id) {
			window
					.open('${yk}/AsileBank/show.do?' + $('#pkName').val() + '='
							+ id, '',
							'height=600,width=600,resizable=yes,location=no,scrollbars');
		}
		function edit(id) {
			window
					.open('${yk}/AsileBank/edit.do?' + $('#pkName').val() + '='
							+ id, '',
							'height=600,width=600,resizable=yes,location=no,scrollbars');
		}

		$(function() {

			$('#btnedit')
					.click(
							function() {
								var $id = $('[name=ids]:checkbox:checked');

								if ($id.length == 0) {
									alert("请选择要修改的信息！");
									return false;
								}
								window
										.open('${yk}/AsileBank/edit.do?'
												+ $('#pkName').val() + '='
												+ $id.val(), '',
												'height=600,width=600,resizable=yes,location=no,scrollbars');
							});

			$('#btnadd')
					.click(
							function() {
								window
										.open('${yk}/AsileBank/create.do', '',
												'height=600,width=600,resizable=yes,location=no,scrollbars');
							});

			$('#btndelete').click(function() {
				if ($('[name=ids]:checkbox:checked').length > 0) {
					var ids = [];
					$('[name=ids]:checkbox:checked').each(function() {
						ids.push($(this).val())
					});
					v_deleteItems(ids);
				} else {
					alert("请选择后进行删除操作！");
				}

			});

			$('#btnclear').click(function() {
				$('#asileName').val('');
				$('#asileCode').val('');
				$('#bankName').val('');
				$('#bankCode').val('');
				$('#asileProductCode').val('');
				$('#asileProductName').val('');
				$('#asileDirectBank').val('');
				$('#asilePublicPrivate').val('');
				$('#asileIntefaceType').val('');
				$('#asileAuthType').val('');
				$('#asileAuthElements').val('');
				$('#asileAuthMode').val('');
				$('#asileBankType').val('');
				$('#asilePayType').val('');
				$('#asileCrashLimit').val('');
				$('#cardCrashDayLimit').val('');
				$('#cardCrashMonthLimit').val('');
				$('#cardDayLimits').val('');
				$('#batchCrashLimit').val('');
				$('#batchLimit').val('');
				$('#remark').val('');
				$('#createTimeBegin').val('');
				$('#createTimeEnd').val('');
				$('#createrId').val('');
				$('#createrName').val('');
				$('#updateTimeBegin').val('');
				$('#updateTimeEnd').val('');
				$('#updaterId').val('');
				$('#updaterName').val('');
				$('#isDelete').val('');
				$('#asileOnlyTimeBegin').val('');
				$('#asileOnlyTimeEnd').val('');
				$('#asileServiceBeginTimeBegin').val('');
				$('#asileServiceBeginTimeEnd').val('');
				$('#asileServiceEndTimeBegin').val('');
				$('#asileServiceEndTimeEnd').val('');
				$('#priority').val('');
				$('#borrowSplit').val('');
				$('#smsSender').val('');
				$('#bankShort').val('');
				$('#asileBankCode').val('');
			});
		});

		function del(id) {
			var ids = [];
			ids.push(id);
			v_deleteItems(ids);
		}

		function v_deleteItems(ids) {
			if (ids.length > 0 && confirm("确定要删除吗？")) {
				$.post("${yk}/AsileBank/delete.do", {
					"items" : ids.join(',')
				}, function(data) {
					if (data == '1') {
						alert('删除成功');
						$('#queryForm').submit();
					} else {
						alert('删除失败');
					}
				});
			}
		};

// 		function cleanWhitespace(element) {
// 			//遍历element的子节点 
// 			for (var i = 0; i < element.childNodes.length; i++) {
// 				var node = element.childNodes[i]
// 				if (node.nodeType == 3 && !/\s/.test(node.nodeValue))
// 					node.parentNode.removeChild(node);
// 			}
// 		}
// 		//获得表格对象 
// 		var _table = document.getElementById("table1");
// 		cleanWhitespace(_table);
// 		//使表格行上移，接收参数为链接对象 
// 		function moveUp(_a) {
// 			//通过链接对象获取表格行的引用 
// 			var _row = _a.parentNode.parentNode;
// 			//如果不是第一行 交换顺序 
// 			if (_row.previousSibling)
// 				swapNode(_row, _row.previousSibling);
// 		}
// 		//使表格行下移 接收参数为链接对象 
// 		function moveDown(_a) {
// 			debugger;
// 			//通过链接对象获取表格行的引用 
// 			var _row = _a.parentNode.parentNode;
// 			//如果不是最后一行 则与下一行交换顺序 
// 			if (_row.nextSibling)
// 				swapNode(_row, _row.nextSibling);
// 		}
// 		//定义通用的函数交换两个节点的位置 
// 		function swapNode(node1, node2) {
// 			//获取父节点 
// 			var _parent = node1.parentNode;
// 			//获取两个节点的相应位置 
// 			var _t1 = node1.nextSibling;
// 			var _t2 = node2.nextSibling;
// 			//将node2插入到原来node1的位置 
// 			debugger;
// 			priority();
// 			if (_t1) {
// 				// 	node1.cells[15].innerHTML='<div>'+parseInt(parseInt(node1.rowIndex)+1)+'</div>';
// 				_parent.insertBefore(node2, _t1);
// 			} else
// 				_parent.appendChild(node2);
// 			//将node1插入到原来ndoe2的位置 
// 			if (_t2) {
// 				_parent.insertBefore(node1, _t2);
// 			} else
// 				_parent.appendChild(node1);
// 		}

// 		function priority() {
// 			var asileBanks = new Array();
// 			$("#tb").find("tr").each(function(i) {
// 				var tdArr = $(this).children();
// 				if (i > 0) {
// 					var asileBank=new Object();
// 					asileBank.id = tdArr.eq(0).text().trim();
// 					asileBank.priority = i;
// 					asileBanks[i-1] = asileBank;
// 				}

// 			})
// 			var asileBankJson=JSON.stringify(asileBanks);
// 			alert(asileBankJson)
// 			$.ajax({
// 				url : '${yk}/AsileBank/degree.do',
// 				data :{asileBankJson:asileBankJson},
// 				dataType : "json",
// 				type : "POST",
// 				success : function(responseJSON) {
// 					alert('Ok');
// 				}
// 			});
// 		}
	</script>
	
	<script type="text/javascript">
		$(function(){ 
		  //上移 
		  var $up = $(".up") 
		  $up.click(function() { 
		    var $tr = $(this).parents("tr"); 
		    if ($tr.index() != 0) { 
		      $tr.fadeOut().fadeIn(); 
		      $tr.prev().before($tr);   
		    } 
		    priority();
		  }); 
		  //下移 
		  var $down = $(".down"); 
		  var len = $down.length; 
		  $down.click(function() { 
		    var $tr = $(this).parents("tr"); 
		    if ($tr.index() != len - 1) { 
		      $tr.fadeOut().fadeIn(); 
		      $tr.next().after($tr); 
		    } 
		    priority();
		  }); 
		  //置顶 
		  var $top = $(".top"); 
		  $top.click(function(){ 
		    var $tr = $(this).parents("tr"); 
		    $tr.fadeOut().fadeIn(); 
		    $(".table").prepend($tr); 
		    $tr.css("color","#f60"); 
		    priority();
		  }); 
		}); 
		
		
		function priority() {
			var asileBanks = new Array();
			$("#tb").find("tr").each(function(i) {
				var tdArr = $(this).children();
				if (i > 0) {
					var asileBank=new Object();
					asileBank.id = tdArr.eq(0).text().trim();
					asileBank.priority = i;
					asileBanks[i-1] = asileBank;
				}

			})
			var asileBankJson=JSON.stringify(asileBanks);
			$.ajax({
				url : '${yk}/AsileBank/degree.do',
				data :{asileBankJson:asileBankJson},
				dataType : "json",
				type : "POST",
				success : function(responseJSON) {
					alert('Ok');
				}
			});
		}
	</script>
</body>
</html>


