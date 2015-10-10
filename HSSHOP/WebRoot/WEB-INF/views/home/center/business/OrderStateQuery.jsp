<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="红狮水泥商城" />
<meta name="description" content="红狮水泥商城" />
<%@ include file="../../../../includes/homenew/header.jsp"%>
<script src="${ctx }/scripts/product.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="${ctx }/themes/grey/css/user.css" />

<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/date/need/laydate.css" />
<script type="text/javascript" src="${ctx }/scripts/date/laydate.js"></script>

<title>订单详情-红狮水泥商城</title>
</head>
<body>

	<%@ include file="../../../../includes/homenew/headerBar.jsp"%>
	<%@ include file="../../../../includes/homenew/headerTop.jsp"%>
	<!--bof main-->
	<div class="user-main">
		<div class="wrap clearfix">
			<div class="crumbs">
				<strong>| <a href="seller_data.html">个人中心首页</a>
				</strong>&gt; <a href="${ctx }/ucenter/userinfo.do?&navid=10">个人中心</a> &gt; <span>订单详情</span>
			</div>

			<div class="user-menu left">
				<%@ include file="../centermenu.jsp"%>
			</div>

			<!--bof content-->
			<div class="user-content right">
				<h1>订单详情</h1>
				<div class="user-order">
					<!--01 订单支付信息-->
					<div class="order-list01">
						<ul>

							<!-- 经销商门户-买入的商品 -->
							<c:if test="${param.roleid == 2 && param.storeType==1 }">
								<li>
									<ul class="clearfix">
										<c:if test="${orderinfo.paymode==2}">
											<li>代收货款： <em class="blue">工厂支付</em></li>
											<li>合计金额： ￥ ${factoryMoney.paysummny}</li>
											<li class="right">支付状态： <em class="green"> 已付款 </em></li>
										</c:if>
										<c:if test="${orderinfo.paymode!=2}">
											<li>代收货款： <em class="blue">无</em></li>
										</c:if>
									</ul>
								</li>
							</c:if>

							<!-- 其他 -->
							<c:if test="${ not (param.roleid == 2 && param.storeType==1) }">
								<li>
									<ul class="clearfix">
										<li>支付方式： <em class="blue"> <c:if
													test="${param.roleid == 1}">
													<c:if test="${orderinfo.paymode==1}">到店支付</c:if>
													<c:if test="${orderinfo.paymode==2}">到厂支付</c:if>
													<c:if test="${orderinfo.paymode==3}">货到付款</c:if>
													<c:if test="${orderinfo.paymode==4}">在线支付</c:if>
												</c:if> <c:if test="${ param.roleid == 2}">
									${orderinfo.payfriendname}
									</c:if>
										</em></li>
										<li>合计金额： ￥ ${orderinfo.orderamount}</li>

										<c:if
											test="${(param.storeType=='2' &&  param.roleid == 2) or (param.storeType=='1' &&  param.roleid == 1)}">
											<li class="right">支付状态： <em class="green"><c:if
														test="${orderinfo.paystate!='0'}"> 已付款 </c:if> <c:if
														test="${orderinfo.paystate=='0'}"> 未付款 </c:if> </em> <c:if
													test="${(param.storeType=='2' && orderinfo.paystate=='0') or (param.storeType=='1' &&  param.roleid == 1 && orderinfo.paystate=='0')}">
													<!--支付按钮-->
													<form action="${ctx }/business/payOrderPage.do">
														<input type="hidden" name="oid" value="${orderinfo.oid }" />
														<input type="hidden" name="osn" value="${orderinfo.osn }" />
														<input type="hidden" name="storeType"
															value="${param.storeType}" /> <input type="hidden"
															name="roleid" value="${param.roleid}" />
														<fieldset>
															<input class="order-btn" value="收款" type="submit" />
														</fieldset>
													</form>
												</c:if>

											</li>
										</c:if>

									</ul>
								</li>
							</c:if>
							<li>
								<ul class="clearfix">
									<li>订单号：${orderinfo.osn}</li>
									<li>下单时间： ${formate.format(orderinfo.addtime)}</li>

									<c:if
										test="${(param.storeType=='1' &&  param.roleid == 2) or (param.storeType=='2' &&  param.roleid == 1) }">
										<li>有效期至：<c:if test="${orderinfo.expireTime != null }">${formate.format(orderinfo.expireTime)} </c:if>
										</li>
									</c:if>

									<li class="right clearfix">订单状态： <em class="green">${orderStateName}</em>
										<c:if
											test="${param.storeType=='2' or (param.storeType=='1' &&  param.roleid == 1)}">

											<!-- 订单确认：工厂B2B，工厂B2C，经销商卖出订单 -->
											<c:if test="${orderinfo.orderstate=='0'}">
												<form name="confirm-form" id="confirm-form">
													<input type="hidden" name="orderid"
														value="${orderinfo.oid }" /> <input type="hidden"
														name="storeType" value="${param.storeType }" />
													<fieldset class="clearfix">
														<c:if test="${param.roleid == 1}">
															<!-- <input type="text" name="message" placeholder="请输入手机验证码" /> -->
														</c:if>
														<input id="confirm-btn" class="order-btn" value="确认订单"
															type="button" />
													</fieldset>
												</form>
											</c:if>

											<!--发货按钮：工厂B2B，工厂B2C，经销商卖出订单-->
											<c:if test="${orderinfo.orderstate=='6'}">
												<form action="${ctx }/business/OrderSendPage.do">
													<input type="hidden" name="ordernum"
														value="${orderinfo.osn }" /> <input type="hidden"
														name="storeType" value="${param.storeType}" /> <input
														type="hidden" name="roleid" value="${param.roleid}" />
													<fieldset>
														<input class="order-btn" value="发货" type="submit" />
													</fieldset>
												</form>
											</c:if>
										</c:if> <!-- 订单评价：工厂B2C，经销商卖出订单 --> <c:if
											test="${param.roleid == 1 && orderinfo.orderstate=='10'}">
											<form action="${ctx }/business/OrderEvaluatePage.do">
												<input type="hidden" name="orderid"
													value="${orderinfo.oid }" /> <input type="hidden"
													name="ordernum" value="${orderinfo.osn }" /> <input
													type="hidden" name="storeType" value="${param.storeType}" />
												<input type="hidden" name="roleid" value="${param.roleid}" />
												<fieldset>
													<input class="order-btn" value="评价订单" type="submit" />
												</fieldset>
											</form>
										</c:if>



									</li>
								</ul>
							</li>
						</ul>
					</div>
					<!--01-->

					<!--02 订单跟踪信息-->
					<div class="order-list02">
						<div class="order-tab">订单跟踪</div>
						<ul>
							<c:forEach items="${boalist}" var="boa" varStatus="status">
								<c:if test="${status.index==0 }">
									<li class="active"><i class="icon"></i>
										<p>
											<span>${formate.format(boa.actiontime)} </span>${boa.actiondes}
											操作人：${boa.realname}
										</p></li>
								</c:if>

								<c:if test="${status.index!=0 }">
									<li><i class="icon"></i>
										<p>
											<span>${formate.format(boa.actiontime)} </span>${boa.actiondes}
											操作人：${boa.realname}
										</p></li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
					<!--02-->

					<!--03 订单配送信息-->
					<!--订单配送信息：经销商买入订单-->
					<c:if test="${param.storeType== 1 &&  param.roleid == 2}">
						<div class="order-list03">
							<div class="order-tab order-tab-grey">配送信息</div>
							<ul>
								<c:forEach var="extitem" items="${orderext }">
									<c:if test="${extitem.oexttype==1 }">
										<li><em>车牌号：</em>${extitem.oextvalue }</li>

									</c:if>
									<c:if test="${extitem.oexttype==3 }">
										<li><em>发动机号：</em>${extitem.oextvalue }</li>
									</c:if>
								</c:forEach>
							</ul>
							<div class="order-tab order-tab-grey">备注</div>
							<p>${orderinfo.buyerremark}</p>
						</div>
					</c:if>
					<!--订单配送信息：经销商卖出订单 & 工厂B2B订单 & 工厂B2C订单-->
					<c:if test="${not (param.storeType== 1 &&  param.roleid == 2)}">
						<div class="order-list03">
							<div class="order-tab order-tab-grey">收货人信息</div>
							<ul>
								<li><em>收 货 人：</em>${orderinfo.consignee}</li>
								<li><em>收货地址：</em>${regions.provincename }${regions.cityname }${regions.countyname }${regions.streetname }${regions.name }
									${orderinfo.address}</li>
								<li><em>手机号码：</em>${orderinfo.mobile}</li>
							</ul>
							<div class="order-tab order-tab-grey">配送信息</div>
							<ul>
								<li><em>配送方式：</em>${orderinfo.shipfriendname}</li>
								<li><em>自提时间：</em> <c:if
										test="${orderinfo.shipsystemname == '1' or orderinfo.shipsystemname == '2'}">
										<fmt:formatDate value="${orderinfo.besttime}"
											pattern="yyyy年MM月dd日" />
										<c:if test="${!empty orderinfo.begindate}">从${orderinfo.begindate}到${orderinfo.enddate}之间</c:if>
									</c:if></li>

								<c:forEach var="extitem" items="${orderext }">
									<c:if test="${extitem.oexttype==1 }">
										<li><em>车牌号：</em>${extitem.oextvalue }</li>

									</c:if>
									<c:if test="${extitem.oexttype==2 }">
										<li><em>证件号：</em> <c:if
												test="${extitem.oextlabelvalue==1 }">
												<span>身份证 </span>
											</c:if> <c:if test="${extitem.oextlabelvalue==2 }">
												<span>驾驶证 </span>
											</c:if> <span>${extitem.oextvalue }</span></li>
									</c:if>
									<c:if test="${extitem.oexttype==3 }">
										<li><em>发动机号：</em>${extitem.oextvalue }</li>
									</c:if>

								</c:forEach>
							</ul>

							<!-- b2c订单，经销商卖出订单显示发票 -->
							<c:if test="${ param.roleid == 1 }">
								<div class="order-tab order-tab-grey">发票信息</div>
								<ul>
									<c:if test="${not empty invoice}">
										<c:if test="${invoice.invtype==1}">
											<li><em>发票类型：</em>普通发票</li>
											<li><em>发票抬头：</em>${invoice.invtitle}</li>
										</c:if>
										<c:if test="${invoice.invtype==2}">
											<li><em>发票类型：</em>增值税发票</li>
											<li><em>发票抬头：</em>${invoice.invcomname}</li>
										</c:if>

										<li><em>发票内容:</em>${invoice.invcontent}</li>
										<li><em>发票配送方式:</em> <c:if test="${invoice.shiptype==1}">
											随货同行
										</c:if> <c:if test="${invoice.shiptype==2}">
											配送到收货地址
										</c:if> <c:if test="${invoice.shiptype==3}">
											配送到发票地址
										</c:if></li>

										<c:if test="${invoice.shiptype==3}">
											<li><em>发票接收地址:</em>${invoice.shipaddress}</li>
										</c:if>

									</c:if>


								</ul>
							</c:if>

							<div class="order-tab order-tab-grey">备注</div>
							<p>${orderinfo.buyerremark}</p>
						</div>
					</c:if>

					<!--03-->

					<!--04 商品清单-->
					<div class="order-list04">
						<div class="order-tab order-tab-grey">商品清单</div>

						<div class="order-table">
							<div class="user-table-head">
								<table>
									<tbody>
										<tr>
											<th scope="col" class="product-info">订单信息</th>
											<th scope="col" class="table-wd01">数量</th>
											<c:if
												test="${ not (param.storeType==2 &&  param.roleid == 2)}">
												<th scope="col" class="table-wd01">金额</th>
												<th scope="col" class="table-wd01">合计</th>
											</c:if>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="user-table-li">
								<table>
									<tbody>

										<c:forEach items="${boplist}" var="bop" varStatus="vs">
											<tr class="table-sline">
												<td class="product-info"><div class="imghloder">
														<a href="product.html"><img
															src="${ctx }/upload/product/thumb/60_60/${bop.showimg}"
															alt="" /></a>
													</div>
													<div class="intro">
														<a href="${ctx }/upload/html/${bop.url}" target="_blank">${bop.name}</a>
													</div></td>
												<td class="table-wd01">${bop.buycount}</td>
												<c:if
													test="${ not (param.storeType==2 &&  param.roleid == 2)}">
													<td class="table-wd01">￥${bop.marketprice}</td>
													<td class="table-wd01">￥${bop.marketprice.multiply(bop.buycount)}</td>
												</c:if>
											</tr>
										</c:forEach>

									</tbody>
								</table>
							</div>
						</div>


						<!--发货清单-->
						<c:if
							test="${orderinfo.orderstate=='7' or orderinfo.orderstate=='8' or orderinfo.orderstate=='9' or orderinfo.orderstate=='10' or orderinfo.orderstate=='11'}">
							<div class="order-tab order-tab-grey">发货清单</div>
							<!--发货清单商品列表-->
							<div class="order-table">
								<div class="user-table-head">
									<table>
										<tbody>
											<tr>
												<th scope="col" class="product-info">订单信息</th>
												<th scope="col" class="table-wd01">数量</th>
												<th scope="col" class="table-wd01">金额</th>
												<th scope="col" class="table-wd01">合计</th>
												<th scope="col" class="table-wd02">水泥编号</th>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="user-table-li">
									<table>
										<tbody>

											<c:forEach items="${boplist}" var="bop" varStatus="vs">
												<tr class="table-sline">
													<td class="product-info"><div class="imghloder">
															<a href="product.html"><img
																src="${ctx }/upload/product/thumb/60_60/${bop.showimg}"
																alt="" /></a>
														</div>
														<div class="intro">
															<a href="${ctx }/upload/html/${bop.url}" target="_blank">${bop.name}</a>
														</div></td>
													<td class="table-wd01">${bop.sendcount}</td>
													<td class="table-wd01">￥${bop.marketprice}</td>
													<td class="table-wd01">￥${bop.marketprice.multiply(bop.sendcount)}</td>
													<td class="table-wd02">1234567890</td>
												</tr>
											</c:forEach>


										</tbody>
									</table>
								</div>
							</div>

							<!--发货清单说明信息-->
							<!--发货清单说明信息：买家-->
							<c:if
								test="${(param.storeType=='2' &&  param.roleid == 1) or (param.storeType=='1' &&  param.roleid == 2)}">
								<div class="delivery-note clearfix">
									<p>
										<span>说明：${sender.description}</span>
									</p>
									<c:if test="${not empty sender.file1}">
										<ul class="clearfix">
											<li><img
												src="${ctx }/upload/order/thumb/100_100/${sender.file1}"
												alt="发货上传凭证" /></li>

										</ul>
									</c:if>
								</div>
								<!--合计信息:买家-->
								<div class="order-check">
									合计金额: <strong> ${orderinfo.orderamount}元 </strong>
								</div>
							</c:if>

							<!--发货清单说明信息:卖家-->
							<c:if
								test="${(param.storeType=='2' &&  param.roleid == 2) or (param.storeType=='1' &&  param.roleid == 1) or (param.storeType=='' &&  param.roleid == '')}">
								<div class="delivery-note clearfix">
									<div class="delivery-info">
										<ul>
											<li><em>身份证：</em>${sender.senderidno}</li>
											<li><em>车牌号：</em>${sender.carno}</li>
											<li class="red"><em>说明：</em>${sender.description}</li>
										</ul>
									</div>
									<c:if test="${not empty sender.file1}">
										<ul class="clearfix">
											<li><img
												src="${ctx }/upload/order/thumb/100_100/${sender.file1}"
												alt="" /></li>
										</ul>
									</c:if>
								</div>
								<!--合计信息:卖家-->
								<div class="sell-order-check">
									<ul>
										<li>总商品金额：￥ ${orderinfo.productamount}</li>
										<li>+ 运费：￥ ${orderinfo.shipfee}</li>
										<li>+ 装卸费：￥${orderinfo.handlingcost}</li>
										<li class="line">合计金额: <strong>${orderinfo.orderamount}元
										</strong></li>
										<li>实付金额: <em>${payorder.payamount}元 </em></li>
									</ul>
								</div>
							</c:if>
						</c:if>

						<!--订单取消原因-->
						<c:if test="${orderinfo.orderstate=='14'}">

							<div class="delivery-note clearfix">
								<div class="delivery-info">
									<ul>
										<li><em>身份证：</em>${sender.senderidno}</li>
										<li><em>车牌号：</em>${sender.carno}</li>
										<li class="red"><em>说明：</em>${sender.description}</li>
									</ul>
								</div>
								<c:if test="${not empty sender.file1}">
									<ul class="clearfix">
										<li><img
											src="${ctx }/upload/order/thumb/100_100/${sender.file1}"
											alt="" /></li>
									</ul>
								</c:if>
							</div>

							<div class="order-tab order-tab-grey">取消订单</div>
							<div class="delivery-note clearfix">
								<c:if test="${reason!=null}">
									<div class="delivery-info">
										<ul>
											<li><em>取消方：</em> <c:if test="${CanceledBy==1}">
									用户
									</c:if> <c:if test="${CanceledBy==2}">
									商家
									</c:if> <c:if test="${CanceledBy==3}">
									工厂
									</c:if></li>
											<li><em>取消原因：</em>${reason.reason}</li>
											<li><em>时间：</em>${formate.format(reason.createtime)}</li>
											<li class="red"><em>说明：</em>${reason.description}</li>
										</ul>
									</div>

									<ul class="clearfix">
										<c:forEach items="${cancelpicList}" var="pic" varStatus="vs">
											<c:if test="${ not empty pic.pic}">
												<li><img
													src="${ctx }/upload/order/thumb/100_100/${pic.pic}"
													alt="取消订单上传凭证" /></li>
											</c:if>
										</c:forEach>
									</ul>
								</c:if>
							</div>
						</c:if>




					</div>
					<!--04-->
				</div>
			</div>
			<!--eof content-->
		</div>
	</div>
	<!--eof main-->

	<!--bof Service-->
	<c:import url="${ctx }/tool/newService.do"></c:import>
	<!--eof Service-->
	<%@ include file="../../../../includes/homenew/footer.jsp"%>
	<script type="text/javascript">
		$("#confirm-form input").bind("keydown", function(e) {
			if (e.keyCode == "13") {
				$("#confirm-btn").trigger("click");
				if (e && e.preventDefault) {
					e.preventDefault();
				} else {
					window.event.returnValue = false;
				}
				if (e && e.stopPropagation) {
					e.stopPropagation();
				} else {
					window.event.cancelBubble = true;
				}
				return false;
			}
		});
		//订单确认
		$("#confirm-btn").bind("click", function(e) {
			var tform = document.forms["confirm-form"];
			var sendData = $(tform).serialize();
			//弹窗提示配置
			var hsArtDialog = dialog({
				title : '提示',
				id : "hs-dialog",
				fixed : true,
				width : 300,
				height : 50
			});
			$.ajax({
				url : "${ctx }/business/OrderConfirm.do",
				type : "post",
				data : sendData,
				async : false,
				success : function(data, status) {
					//后台执行成功后的回调函数
					if (data.state == "success") {
						hsArtDialog.content("操作成功！").showModal();
						$(tform).remove();
						window.location.reload();
					} else {
						hsArtDialog.content(data.content).showModal();
					}
				},
				error : function(xhr, errinfor, ex) {
					//后台发生异常后的回调函数
					hsArtDialog.content(errinfor).showModal();
				}
			});

		});
	</script>
</body>
</html>
