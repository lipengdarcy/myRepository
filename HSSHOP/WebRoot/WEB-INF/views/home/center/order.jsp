<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的订单-红狮水泥商城</title>
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
			<%@ include file="centermenu.jsp"%>
			<div id="memberR" style=" padding-bottom:0px;">
				<h2 id="memberRT">我的订单</h2>
				<script type="text/javascript">
					//处理取消订单的反馈信息
					function cancelOrderResponse(data) {
						var result = eval("(" + data + ")");
						if (result.state == "success") {
							document.getElementById("orderState"
									+ result.content).innerHTML = "取消";
							document.getElementById("cancelOrderBut"
									+ result.content).parentNode.innerHTML = "<a href='"
									+ document.getElementById("orderInfo"
											+ result.content).href + "'>查看</a>";
							alert("取消成功");
						} else {
							alert(result.content);
						}
					}
				</script>
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
											&nbsp;&nbsp;&nbsp;&nbsp;${order.payfriendname}</td>
									</tr>
									<tr>
										<td id="orderProductList">
											<div class="proList">
												<c:forEach items="${order.boplist}" var="bop">
													<a href="${ctx }/product/detail.do?id=${bop.pid}"> <img
														src="${ctx }/upload/product/show/thumb60_60/${bop.showimg}"
														width="50" height="50"></img> </a>
												</c:forEach>
												<div class="clear"></div>
											</div>
										</td>
										<td>${order.consignee}</td>
										<td>￥${order.productamount.add(order.shipfee)}</td>
										<td>${forday.format(order.addtime)}<br />${fortime.format(order.addtime)}</td>
										<td id="orderState${order.oid}">
												<c:if test="${order.orderstate=='0'}">待确认</c:if>
												<c:if test="${order.orderstate=='1'}">已确认</c:if>
												<c:if test="${order.orderstate=='2'}">备货中</c:if>
												<c:if test="${order.orderstate=='3'}">已发货</c:if>
												<c:if test="${order.orderstate=='4'}">已完成</c:if>
												<c:if test="${order.orderstate=='5'}">锁定</c:if>
												<c:if test="${order.orderstate=='6'}">已取消</c:if>
												</td>
										<td><a href="orderinfo.do?oid=${order.oid}"
											id="orderInfo${order.oid}">查看</a> <c:if test="${order.orderstate=='0'}">
												<a href="javascript:cancelOrder(${order.oid}, 0)"
													id="cancelOrderBut${order.oid}">取消订单</a>
											</c:if>
										</td>
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