<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单信息-红狮水泥商城</title>
<meta name="keywords" content="红狮水泥商城" />
<meta name="description" content="红狮水泥商城" />
<%@ include file="../../../includes/home/header.jsp"%>
<script type="text/javascript">
	uid = 1;
	isGuestSC = 1;
	scSubmitType = 0;
</script>
<script type="text/javascript">
	function repeat(){
		//弹窗提示配置
		var hsArtDialog=dialog({
		  	title: '提示',
		  	id:"hs-dialog",
		  	fixed:true,
		  	width:300,
		  	height:50
		});
		$.ajax({
			url:'${ctx }'+'/ucenter/repeatMessage.do',// 跳转到 action    
			data:{    
				orderid:'${orderinfo.oid}',
			},
			type:'post',
			dataType:'json',
			success:function(data) {    
				if(data.state == 'success'){
					hsArtDialog.content("短信重发成功！").showModal();
				}else{		
					hsArtDialog.content("短信重发失败！").showModal();
				}
			},
			error : function() {
				hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
			}  
		});
	}
	$(document).ready(function(e){
		$(".go-pay-btn").bind("click",function(e){
			var orderid=$("#orderid").val();
		});
	});
</script>
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

	<%@ include file="../../../includes/home/headerBar.jsp"%>

	<%@ include file="../../../includes/home/headerTop.jsp"%>

	<c:import url="${ctx }/tool/topMenu.do"></c:import>

	<div class="breadcrumb box">
		<strong>会员中心</strong> <span>&nbsp;&gt;&nbsp;<a
			href="orderlist.do?uid=1">订单中心</a>&nbsp;&gt;&nbsp;<a
			href="orderinfo.do?oid=${orderinfo.oid}">订单：${orderinfo.osn}</a>
		</span>
	</div>

	<div class="box">
		<c:if
			test="${orderinfo.paymode=='4' && orderinfo.orderstate=='0'&& orderinfo.paystate=='0'}">
			<h2 class="DDstatus">
				<input type="hidden" name="orderid" id="orderid"
					value="${orderinfo.oid }"></input> <span>支付状态:</span> <span>未支付！</span>
				<a href="${ctx }/unionpay/submitOrder.do?orderId=${orderinfo.osn }"
					style="color: white;" class="btn btn-danger go-pay-btn">继续支付</a>
			</h2>
		</c:if>
		<c:if test="${orderinfo.paymode=='4' && orderinfo.paystate!='0'}">
			<h2 class="DDstatus">
				<span>支付状态:</span> <span style="color: #12A000;">已支付！</span>
			</h2>
		</c:if>
		<h2 class="DDstatus">
			订单号：${orderinfo.osn} 状态：
			<font color="#12A000">
				<c:if test="${orderinfo.orderstate=='0'}">待确认</c:if> 
				<c:if test="${orderinfo.orderstate=='1'}">已确认</c:if> 
				<c:if test="${orderinfo.orderstate=='2'}">待验证</c:if> 
				<c:if test="${orderinfo.orderstate=='3'}">已验证</c:if> 
				<c:if test="${orderinfo.orderstate=='4'}">待支付</c:if> 
				<c:if test="${orderinfo.orderstate=='5'}">已支付</c:if> 
				<c:if test="${orderinfo.orderstate=='6'}">待发货</c:if>
				<c:if test="${orderinfo.orderstate=='7'}">已发货
					<span id="sended-remove">
						<form name="send-form" style="display: inline-block;">
							<input type="hidden" name="orderid" value="${orderinfo.oid }">
							<input type="hidden" name="state" value="9"> 
							<input type="hidden" name="remarks"> 
							<span class="btn btn-danger" id="send-btn">确认收货</span>
						</form>
					</span>
				</c:if>
				<c:if test="${orderinfo.orderstate=='8'}">待确认收货</c:if>
				<c:if test="${orderinfo.orderstate=='9'}">确认收货</c:if>
				<c:if test="${orderinfo.orderstate=='10'}">待评价</c:if>
				<c:if test="${orderinfo.orderstate=='11'}">已评价</c:if>
				<c:if test="${orderinfo.orderstate=='12'}">锁定</c:if>
				<c:if test="${orderinfo.orderstate=='13'}">已失效</c:if>
				<c:if test="${orderinfo.orderstate=='14'}">已取消</c:if>
				<c:if test="${orderinfo.orderstate=='15'}">待收款</c:if>
				<c:if test="${orderinfo.orderstate=='16'}">待付款</c:if> 
			</font>
			<!--
			<c:if test="${orderinfo.orderstate<3}">
				<span class="btn btn-default" onclick="repeat();">短信重发</span>
			</c:if>
			-->

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
						<c:if test="${orderinfo.shipsystemname == 1}">
						门店地址：<c:if test="${not empty saleraddr }">${saleraddr.regionname } ${saleraddr.address }</c:if>
					</c:if>
						<c:if test="${orderinfo.shipsystemname == 2}">
						工厂地址：<c:if test="${not empty saleraddr }">${saleraddr.regionname } ${saleraddr.address }</c:if>
					</c:if>
					</dd>
					<dd>
						<c:if test="${orderinfo.shipsystemname < 3}">
						自提时间：<fmt:formatDate value="${orderinfo.besttime}"
								pattern="yyyy年MM月dd日" />
							<c:if test="${!empty orderinfo.begindate}">
								   从${orderinfo.begindate}到${orderinfo.enddate}之间
							</c:if>
						</c:if>
						<c:if test="${orderinfo.shipsystemname == 3}">
						配送时间：<fmt:formatDate value="${orderinfo.besttime}"
								pattern="yyyy年MM月dd日" />
							<c:if test="${!empty orderinfo.begindate}">
								   从${orderinfo.begindate}到${orderinfo.enddate}之间
							</c:if>
						</c:if>
					</dd>
				</dl>
				<c:if test="${orderinfo.shipsystemname < 3}">
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
								<div>
									<em>发票配送方式:</em>
									<span>
										<c:if test="${invoice.shiptype==1}">
											随货同行
										</c:if>	
										<c:if test="${invoice.shiptype==2}">
											配送到收货地址
										</c:if>	
										<c:if test="${invoice.shiptype==3}">
											配送到发票地址
										</c:if>									
									</span>
								</div>
								<c:if test="${invoice.shiptype==3}">
									<div>
										<em>发票接收地址:</em><span>${invoice.shipaddress}</span>
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
									<th>数量</th>
									<th>单价</th>
									<th>金额</th>
									<th>运费</th>
									<th>装卸费</th>									
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
														<c:if test="${bop.isExists=='0'}">
															<img src="${ctx }/upload/product/thumb/60_60/${bop.showimg}"
																onerror="nofind();" width="50" height="50" />
																产品已下架
														 </c:if>
														<c:if test="${bop.isExists=='1'}">
															<img src="${ctx }/upload/product/thumb/60_60/${bop.showimg}"
																onerror="nofind();" width="50" height="50" />
															<a href="${ctx }/tool/snapshot.do?pid=${bop.pid}&oid=${bop.oid}" target="_blank">${bop.name}<br />
																<br /> <br />
															</a>
														</c:if>
														<div class="clear"></div>
													</div>
												</td>
												<td>${bop.buycount}包</td>
												<td>￥${bop.marketprice}元</td>
												<td>￥${bop.itemtotalmny-bop.shipmny-bop.handmny}元</td>
												<td>￥${bop.shipmny}元</td>
												<td>￥${bop.handmny}元</td>
												<td>￥${bop.itemtotalmny}元</td>
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
						<table width="100%" border="0" cellspacing="0" class="dingdan">
							<thead>
								<tr>
									<th>商品</th>
									<th>数量</th>
									<th>单价</th>									
									<th>合计</th>
									<th>水泥编号</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${not empty boplist}">
										<c:forEach items="${boplist}" var="bop" varStatus="vs">
											<tr>
												<td>
													<div class="proList">
														<c:if test="${bop.isExists=='0'}">
															<img src="${ctx }/upload/product/thumb/60_60/${bop.showimg}"
																onerror="nofind();" width="50" height="50" />
																产品已下架
														 </c:if>
														<c:if test="${bop.isExists=='1'}">
															<img src="${ctx }/upload/product/thumb/60_60/${bop.showimg}"
																onerror="nofind();" width="50" height="50" />
															<a href="${ctx }/tool/snapshot.do?pid=${bop.pid}&oid=${bop.oid}" target="_blank">${bop.name}<br />
																<br /> <br />
															</a>
														</c:if>
														<div class="clear"></div>
													</div>
												</td>
												<td>${bop.sendcount}包</td>
												<td>￥${bop.marketprice}元</td>												
												<td>￥<fmt:formatNumber type="number" value="${(bop.itemtotalmny-bop.shipmny-bop.handmny)/bop.buycount*bop.sendcount}" 
												maxFractionDigits="2"/> 元</td>
												<td>
													<c:if test="${not empty bop.sendbatch }">
														${bop.sendbatch }
													</c:if>
												</td>
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
					<div class="delivery-note clearfix">
						<c:if test="${sender.description }">
							<p>
							<span>说明：${sender.description}</span>
							</p>
						</c:if>
						<c:if test="${sender.file1 }">
							<ul class="clearfix">
								<li><img
									src="${ctx }/upload/order/thumb/100_100/${sender.file1}"
									alt="发货上传凭证" /></li>
	
							</ul>
						</c:if>						
					</div>
				</dl>
				<dl>
					<!--订单取消原因-->
					<c:if test="${orderinfo.orderstate=='14'}">
						<div class="order-tab order-tab-grey">取消订单</div>
						<div class="delivery-note clearfix">
							<p>
								<span>取消方：${reason.reason}</span>
							</p>
							<p>
								<span>取消原因：${reason.reason}</span>
							</p>
							<p>
								<span>时间：${reason.reason}</span>
							</p>
							<p>
								<span>说明：${reason.description}</span>
							</p>
							<ul class="clearfix">
								<c:forEach items="${cancelpicList}" var="pic" varStatus="vs">
									<li><img
										src="${ctx }/upload/order/thumb/100_100/${pic.pic}"
										alt="取消订单上传凭证" /></li>
								</c:forEach>
							</ul>
						</div>
		
					</c:if>
				</dl>
			</div>

			<div class="right DDsum">
				<p>
					<i>￥ ${orderinfo.productamount}元</i><span>总商品金额：</span>
				</p>
				<p>
					<i>￥ ${orderinfo.shipfee}元</i><span>+ 运费：</span>
				</p>
				<p>
					<i>￥ ${orderinfo.handlingcost}元</i><span>+ 装卸费：</span>
				</p>
				<div class="clear"></div>
				<hr color="#ddd" size="1" />
				<b>应付总额：<em>￥ ${orderinfo.orderamount}元</em>
				</b>
			</div>		
			
			<div class="clear"></div>

		</div>

	</div>
	<%@ include file="../../../includes/home/footer.jsp"%>
	<script type="text/javascript">
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
				url : "${ctx }/ucenter/changeOrderState.do",
				type : "post",
				data : sendData,
				async : false,
				success : function(data, staus) {
					//后台执行成功后的回调函数
					if (data.state = "success") {
						hsArtDialog.content("操作成功！").showModal();
						$("#order-state").html("已发货");
						$("#order-state").html("已发货");
						$("#sended-remove").remove();
						window.location.reload();
					} else {
						hsArtDialog.content(result.errmsg).showModal();
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