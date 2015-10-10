<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>买家向经销商的购物单列表-红狮水泥商城</title>
<meta name="keywords" content="红狮水泥商城" />
<meta name="description" content="红狮水泥商城" />
<%@ include file="../../../includes/home/header.jsp"%>
<script type="text/javascript">
	uid = 1;
	isGuestSC = 1;
	scSubmitType = 0;
</script>
<link href="${ctx }/themes/default/css/ucenter.css" rel="stylesheet"
	type="text/css" />
<script src="${ctx }/scripts/order.js" type="text/javascript"></script>
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

	<div class="bigBox" id="member">
		<div class="box">
			<%@ include file="oldcentermenu.jsp"%>
			<div id="memberR" style="padding-bottom: 0px;">
				<h2 id="memberRT">买家的订单</h2>
				<table width="100%" border="0" cellspacing="0" class="dingdan">
					<thead>
						<tr>
							<th>订单信息</th>
							<th>收货人</th>
							<th>订单金额</th>
							<th>时间</th>
							<th>订单状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty orderlist}">
								<c:forEach items="${orderlist}" var="order" varStatus="vs">
									<tr class="dingdanTH">
										<td colspan="6">订单编号:<a target="_blank"
											href="orderinfo.do?oid=${order.oid}">${order.osn} </a>&nbsp;&nbsp;&nbsp;&nbsp;${order.shipfriendname}
											&nbsp;&nbsp;&nbsp;&nbsp;${order.payfriendname}
										</td>
									</tr>
									<tr>
										<td id="orderProductList">
											<div class="proList">
												<c:forEach items="${order.boplist}" var="bop"
													varStatus="status">

													<c:if test="${bop.isExists=='0'}">
														<div class="clearfix proList-img">
															<img src="${ctx }/upload/product/thumb/60_60/${bop.showimg}"
																onerror="nofind();" width="50" height="50"
																title="${bop.name}&#10;状态：已下架" alt="${bop.name}" />
															<div class="clearfix">已下架</div>
														</div>
													</c:if>

													<c:if test="${bop.isExists=='1'}">
														<div class="clearfix proList-img">
															<a target="_blank"
																href="${ctx }/tool/snapshot.do?pid=${bop.pid}&oid=${bop.oid}"
																title="${bop.name}&#10;状态：正常"> <img
																src="${ctx }/upload/product/thumb/60_60/${bop.showimg}"
																onerror="nofind();" width="50" height="50"></img>
															</a>
														</div>
													</c:if>

												</c:forEach>
												<div class="clear"></div>
											</div>
										</td>
										<td>${order.consignee}</td>
										<td>￥${order.productamount.add(order.shipfee)}</td>
										<td>${forday.format(order.addtime)}<br />${fortime.format(order.addtime)}</td>
										<td id="orderState${order.oid}"><c:if
												test="${order.orderstate=='0'}">待确认</c:if> <c:if
												test="${order.orderstate=='1'}">已确认</c:if> <c:if
												test="${order.orderstate=='2'}">待发货</c:if> <c:if
												test="${order.orderstate=='3'}">待确认收货</c:if> <c:if
												test="${order.orderstate=='4'}">确认收货</c:if> <c:if
												test="${order.orderstate=='5'}">锁定</c:if> <c:if
												test="${order.orderstate=='6'}">已取消</c:if></td>
										<td><a href="../saler/toOrderNumTelVerf.do?oid=${order.oid}"
											id="orderInfo${order.oid}">查看</a> <c:if
												test="${order.orderstate=='0'}">
											</c:if> <c:if test="${order.orderstate=='4'}">
												<a href="commenorderInfo.do?oid=${order.oid}"
													id="commenorderInfo${order.oid}">评价</a>
											</c:if></td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr class="main_info">
									<td colspan="100" class="center">没有相关数据</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				${pageDiv}
			</div>
			<div class="clear"></div>
		</div>
		<div class="clear"></div>
	</div>
	<%@ include file="../../../includes/home/footer.jsp"%>
</body>
</html>