<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="红狮水泥商城" />
<meta name="description" content="红狮水泥商城" />
<%@ include file="../../../includes/homenew/header.jsp"%>
<script src="${ctx }/scripts/product.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="${ctx }/themes/grey/css/user.css" />

<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/date/need/laydate.css" />
<script type="text/javascript" src="${ctx }/scripts/date/laydate.js"></script>

<title>订单列表-红狮水泥商城</title>
</head>
<body>

	<%@ include file="../../../includes/homenew/headerBar.jsp"%>
	<%@ include file="../../../includes/homenew/headerTop.jsp"%>
	<!--bof main-->
	<div class="user-main">
		<div class="wrap clearfix">
			<div class="crumbs">
				<strong>| <a href="seller_data.html">个人中心首页</a>
				</strong>&gt; <a href="${ctx }/ucenter/userinfo.do?&navid=10">个人中心</a> &gt; <span>订单列表</span>
			</div>
			<!--bof menu-->
			<div class="user-menu left">
				<%-- <c:import url="${ctx }/ucenter.do?userType=${userType}&navId=${storeidNavid }" /> --%>
				<%@ include file="centermenu.jsp"%>
			</div>
			<!--eof menu-->
			<!--bof content-->
			<div class="user-content right">
				<form id="form" method="post" action="">
					<input type="hidden" name="pageNum" value="${param.page.pageNum }" />
					<input type="hidden" name="pageSize"
						value="${param.page.pageSize }" />
					<h1>订单列表</h1>
					<ul class="user-content-tab clearfix">
						<li <c:if test="${empty param.orderstate}"> class="active"</c:if>><a
							href="${ctx }/order/queryOrder.do">所有订单</a></li>
						<li <c:if test="${param.orderstate=='4'}"> class="active"</c:if>><a
							href="${ctx }/order/queryOrder.do?orderstate=4">待付款</a></li>
						<li <c:if test="${param.orderstate=='6'}"> class="active"</c:if>><a
							href="${ctx }/order/queryOrder.do?orderstate=6">待发货</a></li>
						<li <c:if test="${param.orderstate=='10'}"> class="active"</c:if>><a
							href="${ctx }/order/queryOrder.do?orderstate=10">待评价</a></li>
					</ul>
					<div class="user-search clearfix">
						<fieldset class="clearfix">
							<legend>订单编号：</legend>
							<input name="ordernum" value="${param.ordernum}"
								placeholder="请输入订单编号" />
						</fieldset>
						<fieldset class="clearfix">
							<legend>商品名称：</legend>
							<input name="productname" value="${param.productname}"
								placeholder="请输入商品名称" />
						</fieldset>
						<fieldset class="clearfix search-select">
							<legend>状态：</legend>

							<select id="orderstate" name="orderstate">
								<option value="-1">请选择</option>
								<option
									<c:if test="${param.orderstate=='0' }">selected="selected" </c:if>
									value="0">待确认</option>
								<option
									<c:if test="${param.orderstate=='14' }">selected="selected" </c:if>
									value="14">已取消</option>
								<option
									<c:if test="${param.orderstate=='16' }">selected="selected" </c:if>
									value="16">待付款</option>
								<option
									<c:if test="${param.orderstate=='6' }">selected="selected" </c:if>
									value="6">待发货</option>
								<option
									<c:if test="${param.orderstate=='7' }">selected="selected" </c:if>
									value='7'>已发货</option>
								<option
									<c:if test="${param.orderstate=='13' }">selected="selected" </c:if>
									value="13">已失效</option>
							</select>

						</fieldset>
						<fieldset class="clearfix search-date">
							<legend>订单日期：</legend>
							<input type="text" name="beginTime" value="${param.beginTime}"
								onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" /><label>到</label>
							<input type="text" name="endTime" value="${param.endTime}"
								onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" />
						</fieldset>

						<fieldset class="clearfix btn">
							<input type="button" onclick="queryOrder2()" value="查询"
								class="user-search-btn" />
						</fieldset>
				</form>
			</div>


			<!--bof product-list-->
			<div class="sales-list">
				<div class="user-table-head">
					<table>
						<tbody>
							<tr>
								<th scope="col" class="product-info">订单信息</th>
								<th scope="col" class="table-wd01">数量</th>
								<th scope="col" class="table-wd01">金额</th>
								<th scope="col" class="table-wd01">状态</th>
								<th scope="col" class="table-wd01">操作</th>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="user-table-ul">
					<c:forEach items="${orderList}" varStatus="status" var="item">
						<div class="user-table-li">
							<table>
								<tbody>
									<tr>
										<td colspan="6" class="product-num">订单号：<a
											href="${ctx }/business/OrderStateQuery.do?storeType=${param.storeType}&roleid=${param.roleid}&ordernum=${item.osn }">
												${item.osn }</a> <span><fmt:formatDate
													pattern="yyyy-MM-dd HH:mm:ss" value="${item.addtime}" /></span> <c:if
												test="${  param.roleid == 1}">
												<em> ${item.shipfriendname} </em>
											</c:if> <em> <c:if test="${item.paymode==1}">到店支付</c:if> <c:if
													test="${item.paymode==2}">到厂支付</c:if> <c:if
													test="${item.paymode==3}">货到付款</c:if> <c:if
													test="${item.paymode==4}">在线支付</c:if></em>
										</td>
									</tr>
									<tr class="table-sline">
										<td class="product-info"><c:forEach
												items="${item.boplist}" varStatus="status2" var="p">

												<!-- href="${ctx }/business/OrderStateQuery.do?storeType=${param.storeType}&roleid=${param.roleid}&ordernum=${item.osn }"> -->
												<div class="imghloder">
													<a target="_blank"
														href="${ctx }/dealproduct/detail.do?id=${item.oid }"><img
														src="${ctx }/upload/product/source/${p.showimg }" alt="" /></a>
												</div>

												<div class="intro">
													<a href="${ctx }/dealproduct/detail.do?id=${item.oid }">${p.name }</a>
												</div>

											</c:forEach></td>
										<td class="table-wd01">${item.boplist[0].buycount }</td>
										<td class="table-wd01">￥${item.orderamount}</td>
										<td class="table-wd01 order-status"><span class="green">${orderStateList[status.index]}</span></td>
										<td class="table-wd01 order-operate"><a target="_blank"
											href="${ctx }/business/OrderStateQuery.do?storeType=${param.storeType}&roleid=${param.roleid}&ordernum=${item.osn }">
												查看详情 </a> <c:if
												test="${item.orderstate==0 or item.orderstate==1 or item.orderstate==2 or item.orderstate==3 or item.orderstate==4 or item.orderstate==5 or item.orderstate==6}">
												<a
													href="${ctx }/business/OrderCancelPage.do?storeType=${param.storeType}&roleid=${param.roleid}&ordernum=${item.osn }">取消订单</a>
											</c:if> <c:if test="${item.orderstate==4}">
												<a href="${ctx }/business/payOrderPage.do?osn=${item.osn }">付款</a>
											</c:if> <c:if test="${item.orderstate==10}">
												<a
													href="${ctx }/business/OrderEvaluatePage.do?ordernum=${item.osn }">评价</a>
											</c:if></td>
									</tr>
								</tbody>
							</table>
						</div>

					</c:forEach>



				</div>
			</div>
			<!--eof product-list-->
			${pageDiv }

		</div>
		<!--bof content-->
	</div>


	<!--eof main-->
	<!--bof Service-->
	<c:import url="${ctx }/tool/newService.do"></c:import>
	<!--eof Service-->
	<%@ include file="../../../includes/homenew/footer.jsp"%>

	<script type="text/javascript">
		//查询订单
		function queryOrder2() {

			//var state="${param.orderstate}"
			//$('#form').find('#orderstate').val(state);
			var postdata = $("#form").serialize();
			window.location.href="${ctx }/order/queryOrder.do?" + postdata;
		}
		
		//查询订单 (根据订单状态)
		function queryOrder(state) {
			//alert($('#form').find('#orderstate').val());
			$('#form').find('#orderstate').val(state);
			var postdata = $('#form').serialize();	
			
			window.location.href="${ctx }/order/queryOrder.do?" + postdata;
		}
		
		
		
	</script>

</body>
</html>
