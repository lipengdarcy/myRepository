<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单信息-红狮水泥商城</title>
<meta name="keywords" content="红狮水泥商城" />
<meta name="description" content="红狮水泥商城" />
<%@ include file="/WEB-INF/includes/home/header.jsp"%>

<link href="${ctx }/themes/default/css/ucenter.css" rel="stylesheet"
	type="text/css" />
<script src="${ctx }/scripts/region.js" type="text/javascript"></script>
<script src="${ctx }/scripts/ucenter.order.js" type="text/javascript"></script>
<link href="${ctx }/themes/default/css/home.css" rel="stylesheet"
	type="text/css" />
<script src="${ctx }/scripts/MSClass.js" type="text/javascript"></script>
<script src="${ctx }/scripts/product.js" type="text/javascript"></script>
</head>

<body>
	<%@ include file="/WEB-INF/includes/home/headerBar.jsp"%>
	<%@ include file="/WEB-INF/includes/home/headerTop.jsp"%>

	<div class="box">
		<div class="step">
			<ul>
				<c:if
					test="${orderinfo.orderstate=='0' or orderinfo.orderstate =='9'}">
					<li><s>1</s>订单确认</li>
					<li><s>2</s>订单验证</li>
					<li><s>3</s>发货</li>
					<li><s>4</s>完成</li>
				</c:if>

				<c:if
					test="${orderinfo.orderstate=='1' or orderinfo.orderstate =='2'}">
					<li class="hot"><s>1</s>订单确认</li>
					<li><s>2</s>订单验证</li>
					<li><s>3</s>发货</li>
					<li><s>4</s>完成</li>
				</c:if>

				<c:if
					test="${orderinfo.orderstate =='3' or orderinfo.orderstate =='4'}">
					<li class="hot"><s>1</s>订单确认</li>
					<li class="hot"><s>2</s>订单验证</li>
					<li><s>3</s>发货</li>
					<li><s>4</s>完成</li>
				</c:if>

				<c:if
					test="${orderinfo.orderstate=='5' or orderinfo.orderstate =='6'}">
					<li class="hot"><s>1</s>订单确认</li>
					<li class="hot"><s>2</s>订单验证</li>
					<li class="hot"><s>3</s>发货</li>
					<li><s>4</s>完成</li>
				</c:if>

				<c:if test="${orderinfo.orderstate=='7'}">
					<li class="hot"><s>1</s>订单确认</li>
					<li class="hot"><s>2</s>订单验证</li>
					<li class="hot"><s>3</s>发货</li>
					<li class="hot"><s>4</s>完成</li>
				</c:if>

				<div class="clear"></div>
			</ul>
		</div>





		<c:if test="${orderinfo.paymode=='4' && orderinfo.paystate=='0'}">
			<h2 class="DDstatus">
				<input type="hidden" name="orderid" id="orderid"
					value="${orderinfo.oid }"></input> <span>支付状态:</span> <span>未支付！</span>
			</h2>
		</c:if>
		<c:if test="${orderinfo.paymode=='4' && orderinfo.paystate!='0'}">
			<h2 class="DDstatus">
				<span>支付状态:</span> <span style="color: #12A000;">已支付！</span>
			</h2>
		</c:if>
		<h2 class="DDstatus">
			订单号：${orderinfo.osn} 状态： <font color="#12A000"> <span
				id="order-state">${orderStateName} </span> <c:choose>

					<c:when test="${orderinfo.orderstate=='0'}">
						<!-- step 1：确认 -->
						<form name="confirm-form" style="display: inline-block;">
							<input type="hidden" name="orderid" value="${orderinfo.oid }">
								<span class="btn btn-danger" id="confirm-btn">确认订单</span>
						</form>
					</c:when>

					<c:when test="${orderinfo.orderstate=='1'}">
						<!-- step 2 ：验证-->
						<form name="verify-form" style="display: inline-block;">
							<input type="hidden" name="orderid" value="${orderinfo.oid }"><span>验证码：<input
									type="text" name="vercode"></span> <span
								class="btn btn-danger" id="btn-verf-order">验证</span>
						</form>
					</c:when>

					<c:otherwise>
						<tr class="main_info">
							<td colspan="100" class="center"></td>
						</tr>
					</c:otherwise>
				</c:choose>
			</font>

		</h2>

		<div id="DDcon">
			<ul>
				<li><a href="#" class="hot">订单跟踪</a></li>
			</ul>
			<div style="padding: 15px;">
				<table width="100%" cellpadding="0" cellspacing="0"
					class="ddgzTable">
					<thead>
						<tr>
							<th width="15%" align="left"><strong>处理时间</strong></th>
							<th width="50%" align="left"><strong>处理信息</strong></th>
							<th width="35%" align="left"><strong>操作人</strong></th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty boalist}">
								<c:forEach items="${boalist}" var="boa" varStatus="vs">
									<tr>
										<td>${formate.format(boa.actiontime)}</td>
										<td>${boa.actiondes}</td>
										<td>${boa.realname}</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr class="main_info">
									<td colspan="100" class="center">没有跟踪信息</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
		</div>
		<div id="DDinfo">
			<h2>订单信息</h2>
			<div id="DDinfoCon">
				<dl>
					<dt>收货人信息</dt>
					<dd>收 货 人：${orderinfo.consignee}</dd>
					<dd>收货地址：${regions.provincename }${regions.cityname }${regions.countyname }${regions.streetname }${regions.name }
						${orderinfo.address}</dd>
					<dd>手机号码：${orderinfo.mobile}</dd>
				</dl>

				<dl>
					<dt>支付及配送方式</dt>
					<dd>
						支付方式：
						<c:if test="${orderinfo.paymode==1}">到店支付</c:if>
						<c:if test="${orderinfo.paymode==2}">到厂支付</c:if>
						<c:if test="${orderinfo.paymode==3}">货到付款</c:if>
						<c:if test="${orderinfo.paymode==4}">在线支付</c:if>
					</dd>
					<dd>配送方式：${orderinfo.shipfriendname}</dd>
					<dd>
						<c:if test="${orderinfo.shipsystemname == '1'}">
						门店地址：<c:if test="${not empty saleraddr }">${saleraddr.address }</c:if>
						</c:if>
						<c:if test="${orderinfo.shipsystemname == '2'}">
						工厂地址：<c:if test="${not empty saleraddr }">${saleraddr.address }</c:if>
						</c:if>
					</dd>
					<dd>
						<c:if
							test="${orderinfo.shipsystemname == '1' or orderinfo.shipsystemname == '2'}">
						自提时间：<fmt:formatDate value="${orderinfo.besttime}"
								pattern="yyyy年MM月dd日" />
							<c:if test="${!empty orderinfo.beginDate}">
								   从${orderinfo.beginDate}到${orderinfo.endDate}之间
							</c:if>
						</c:if>
						<c:if test="${orderinfo.shipsystemname == '3'}">
						配送时间：<fmt:formatDate value="${orderinfo.besttime}"
								pattern="yyyy年MM月dd日" />
							<c:if test="${!empty orderinfo.beginDate}">
								   从${orderinfo.beginDate}到${orderinfo.endDate}之间
							</c:if>
						</c:if>
					</dd>
				</dl>
				<c:if
					test="${orderinfo.shipsystemname == '1' or orderinfo.shipsystemname == '2'}">
					<dl>
						<dt>自提证件信息</dt>
						<dd>
							<c:forEach var="extitem" items="${orderext }">
								<c:if test="${extitem.oexttype==1 }">
									<div>
										<em>车牌号:</em> <span>${extitem.oextvalue }</span>
									</div>
								</c:if>
								<c:if test="${extitem.oexttype==2 }">
									<div>
										<em>证件号:</em>
										<c:if test="${extitem.oextlabelvalue==1 }">
											<span>身份证 </span>
										</c:if>
										<c:if test="${extitem.oextlabelvalue==2 }">
											<span>驾驶证 </span>
										</c:if>
										<span>${extitem.oextvalue }</span>
									</div>
								</c:if>
							</c:forEach>
						</dd>
					</dl>
				</c:if>
				<c:if test="${orderinfo.needinv ==1}">
					<dl>
						<dt>发票信息:</dt>
						<dd>
							<c:if test="${not empty invoice}">
								<c:if test="${invoice.invtype==1}">
									<div>
										<em>发票类型:</em><span>普通发票</span>
									</div>
									<div>
										<em>发票抬头:</em><span>${invoice.invtitle}</span>
									</div>
									<div>
										<em>发票内容:</em><span>${invoice.invcontent}</span>
									</div>
								</c:if>
								<c:if test="${invoice.invtype==2}">
									<div>
										<em>发票类型:</em><span>增值税发票</span>
									</div>
									<div>
										<em>单位名称:</em><span>${invoice.invcomname}</span>
									</div>
									<div>
										<em>纳税人识别码:</em><span>${invoice.invtaxnum}</span>
									</div>
									<div>
										<em>注册地址:</em><span>${invoice.invaddress}</span>
									</div>
									<div>
										<em>注册电话:</em><span>${invoice.invtel}</span>
									</div>
									<div>
										<em>开户银行:</em><span>${invoice.invbankname}</span>
									</div>
									<div>
										<em>银行账户:</em><span>${invoice.invbankno}</span>
									</div>
									<div></div>
									<div>
										<em>发票内容:</em><span>${invoice.invcontent}</span>
									</div>
									<div>
										<em>收票人姓名:</em><span>${invoice.invrecname}</span>
									</div>
									<div>
										<em>收票人手机:</em><span>${invoice.invrectel}</span>
									</div>
									<div>
										<em>收票人省份:</em><span>${invoice.regionname}</span>
									</div>
									<div>
										<em>详细地址:</em><span>${invoice.invdetailaddr}</span>
									</div>
								</c:if>
							</c:if>
						</dd>
					</dl>
				</c:if>
				<dl style="border-bottom: none;">
					<dt>商品清单</dt>
					<dd style="padding-top: 10px;">
						<table width="100%" border="0" cellspacing="0" class="dingdan">
							<thead>
								<tr>
									<th>商品</th>
									<th>价格</th>
									<th>数量</th>
									<th>合计</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${not empty boplist}">
										<c:forEach items="${boplist}" var="bop" varStatus="vs">
											<tr>
												<td>
													<div class="proList">
														<img
															src="${ctx }/upload/product/thumb/60_60/${bop.showimg}"
															onerror="nofind();" width="50" height="50" /> <a
															href="${ctx }/upload/html/${bop.url}" target="_blank">${bop.name}<br />
															<br /> <br />
														</a>
														<div class="clear"></div>
													</div>
												</td>
												<td>￥${bop.marketprice}</td>
												<td>${bop.buycount}</td>
												<td>￥${bop.marketprice.multiply(bop.buycount)}</td>
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr class="main_info">
											<td colspan="100" class="center">没有商品信息</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</dd>
				</dl>

				<dl style="border-bottom: none;">
					<dt>发货清单</dt>
					<dd style="padding-top: 10px;">
						<c:if test="${orderinfo.orderstate!='4'}">

							<table width="100%" border="0" cellspacing="0" class="dingdan">
								<thead>
									<tr>
										<th>商品</th>
										<th>价格</th>
										<th>数量</th>
										<th>合计</th>
									</tr>
								</thead>
								<tbody>
									<c:choose>
										<c:when test="${not empty boplist}">
											<c:forEach items="${boplist}" var="bop" varStatus="vs">
												<tr>
													<td>
														<div class="proList">
															<img
																src="${ctx }/upload/product/thumb/60_60/${bop.showimg}"
																onerror="nofind();" width="50" height="50" /> <a
																href="${ctx }/upload/html/${bop.url}" target="_blank">${bop.name}<br />
																<br /> <br />
															</a>
															<div class="clear"></div>
														</div>
													</td>
													<td>￥${bop.marketprice}</td>
													<td>${bop.sendcount}</td>
													<td>￥${bop.marketprice.multiply(bop.sendcount)}</td>
												</tr>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<tr class="main_info">
												<td colspan="100" class="center">没有商品信息</td>
											</tr>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>

						</c:if>


						<c:if test="${orderinfo.orderstate=='4'}">
						<form action="${ctx }/business/OrderSendPage.do">	
						<input type="hidden" name="ordernum" value="${orderinfo.osn }"/>			
								 <input class="btn btn-danger" type="submit" value="发货" />		
								 
								 </form>				
						</c:if>

					</dd>
				</dl>
			</div>

			<div class="right DDsum">
				<p>
					<i>￥ ${orderinfo.productamount}</i><span>总商品金额：</span>
				</p>
				<p>
					<i>￥ ${orderinfo.shipfee}</i><span>+ 运费：</span>
				</p>
				<p>
					<i>￥ ${orderinfo.handlingCost}</i><span>+ 装卸费：</span>
				</p>
				<div class="clear"></div>
				<hr color="#ddd" size="1" />
				<b>应付总额：<em>￥ ${orderinfo.orderamount}</em>
				</b>
			</div>
			<div class="clear"></div>

		</div>

	</div>
	<%@ include file="/WEB-INF/includes/home/footer.jsp"%>

	<script type="text/javascript">
		//订单确认
		$("#confirm-btn").bind("click", function(e) {
			var tform = document.forms["confirm-form"];
			var sendData = $(tform).serialize();
			//弹窗提示配置
			var hsArtDialog=dialog({
			  	title: '提示',
			  	id:"hs-dialog",
			  	fixed:true,
			  	width:300,
			  	height:50
			});
			$.ajax({
				url : "${ctx }/business/OrderConfirm.do",
				type : "post",
				data : sendData,
				async : false,
				success : function(data, status) {
					//后台执行成功后的回调函数
					if (data.state = "success") {
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

		//输入验证码验证
		$("#btn-verf-order").bind("click", function(e) {
			var tform = document.forms["verify-form"];
			var sendData = $(tform).serialize();
			//弹窗提示配置
			var hsArtDialog=dialog({
			  	title: '提示',
			  	id:"hs-dialog",
			  	fixed:true,
			  	width:300,
			  	height:50
			});
			$.ajax({
				url : "${ctx }/business/OrderVerify.do",
				type : "post",
				data : sendData,
				async : false,
				success : function(data, staus) {
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

		//发送短信
		function repeat() {
			//弹窗提示配置
			var hsArtDialog=dialog({
			  	title: '提示',
			  	id:"hs-dialog",
			  	fixed:true,
			  	width:300,
			  	height:50
			});
			$.ajax({
				url : '${ctx }' + '/ucenter/repeatMessage.do',// 跳转到 action    
				data : {
					orderid : '${orderinfo.oid}',
				},
				type : 'post',
				dataType : 'json',
				success : function(data) {
					if (data.state == 'success') {
						hsArtDialog.content("短信重发成功！").showModal();
					} else {
						hsArtDialog.content("短信重发失败！").showModal();
					}
				},
				error : function() {
					hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
				}
			});
		}

		//发货
		$("#send-btn").bind("click", function(e) {
			var tform = document.forms["send-form"];
			var sendData = $(tform).serialize();
			//弹窗提示配置
			var hsArtDialog=dialog({
			  	title: '提示',
			  	id:"hs-dialog",
			  	fixed:true,
			  	width:300,
			  	height:50
			});
			$.ajax({
				url : "${ctx }/business/OrderSend.do",
				type : "post",
				data : sendData,
				async : false,
				success : function(data, staus) {
					//后台执行成功后的回调函数
					if (data.state == "success") {
						hsArtDialog.content(data.content).showModal();
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