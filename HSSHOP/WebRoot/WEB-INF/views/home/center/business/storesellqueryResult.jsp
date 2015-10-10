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

<title>经销商卖出订单列表-红狮水泥商城</title>
</head>
<body>

	<%@ include file="../../../../includes/homenew/headerBar.jsp"%>
	<%@ include file="../../../../includes/homenew/headerTop.jsp"%>
	<!--bof main-->
	<div class="user-main">
		<div class="wrap clearfix">
			<div class="crumbs">
				<strong>| <a href="seller_data.html">个人中心首页</a>
				</strong>&gt; <a href="${ctx }/ucenter/userinfo.do?&navid=10">个人中心</a> &gt; <span>订单列表</span>
			</div>
			<!--bof menu-->
			<div class="user-menu left">
				<%-- <c:import url="${ctx }/ucenter.do?userType=${userType}&navId=3" /> --%>
				<%@ include file="../centermenu.jsp"%>
			</div>
			<!--eof menu-->
			<!--bof content-->
			<div class="user-content right">
				<form id="form" method="post" action="">

					<input type="hidden" name="storeid" value="${param.storeid }" /> <input
						type="hidden" name="storeType" value="${param.storeType }" /> <input
						type="hidden" name="roleid" value="${param.roleid}" /> <input
						type="hidden" name="pageNum" value="${param.page.pageNum }" /> <input
						type="hidden" name="pageSize" value="${param.page.pageSize }" />

					<h1>订单列表 ${param.page }</h1>
					<ul class="user-content-tab clearfix">
						<li class="active"><a onclick="queryOrder(-1)"
							href="javascript:void(0);">所有订单</a></li>
						<li><a onclick="queryOrder(0)" href="javascript:void(0);">待确认</a></li>
						<li><a onclick="queryOrder(4)" href="javascript:void(0);">待发货</a></li>
						<li><a onclick="queryOrder(5)" href="javascript:void(0);">已发货</a></li>
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
							<!-- 经销商买入订单 -->
							<c:if test="${param.storeType=='1' &&  param.roleid == 1}">
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
										<c:if test="${param.orderstate=='4' }">selected="selected" </c:if>
										value="4">待发货</option>
									<option
										<c:if test="${param.orderstate=='5' }">selected="selected" </c:if>
										value='5'>已发货</option>
									<option
										<c:if test="${param.orderstate=='13' }">selected="selected" </c:if>
										value="13">已失效</option>
								</select>
							</c:if>

							<!-- 经销商卖出订单 -->
							<c:if test="${param.storeType=='1' &&  param.roleid == 2}">
								<select id="orderstate" name="orderstate">
									<option value="-1">请选择</option>
									<option
										<c:if test="${param.orderstate=='0' }">selected="selected" </c:if>
										value="0">待确认</option>
									<option
										<c:if test="${param.orderstate=='14' }">selected="selected" </c:if>
										value="14">已取消</option>
									<option
										<c:if test="${param.orderstate=='15' }">selected="selected" </c:if>
										value="15">待收款</option>
									<option
										<c:if test="${param.orderstate=='4' }">selected="selected" </c:if>
										value="4">待发货</option>
									<option
										<c:if test="${param.orderstate=='5' }">selected="selected" </c:if>
										value='5'>已发货</option>
									<option
										<c:if test="${param.orderstate=='9' }">selected="selected" </c:if>
										value='9'>已收货</option>
									<option
										<c:if test="${param.orderstate=='11' }">selected="selected" </c:if>
										value="11">已评价</option>
								</select>
							</c:if>

							<!-- 工厂B2C订单 -->
							<c:if test="${param.storeType=='2' &&  param.roleid == 1}">
								<select id="orderstate" name="orderstate">
									<option value="-1">请选择</option>
									<option
										<c:if test="${param.orderstate=='0' }">selected="selected" </c:if>
										value="0">待确认</option>
									<option
										<c:if test="${param.orderstate=='14' }">selected="selected" </c:if>
										value="14">已取消</option>
									<option
										<c:if test="${param.orderstate=='15' }">selected="selected" </c:if>
										value="15">待收款</option>
									<option
										<c:if test="${param.orderstate=='4' }">selected="selected" </c:if>
										value="4">待发货</option>
									<option
										<c:if test="${param.orderstate=='5' }">selected="selected" </c:if>
										value='5'>已发货</option>
									<option
										<c:if test="${param.orderstate=='9' }">selected="selected" </c:if>
										value='9'>已收货</option>
									<option
										<c:if test="${param.orderstate=='11' }">selected="selected" </c:if>
										value="11">已评价</option>
								</select>
							</c:if>

							<!-- 工厂B2B订单 -->
							<c:if test="${param.storeType=='2' &&  param.roleid == 2}">
								<select id="orderstate" name="orderstate">
									<option value="-1">请选择</option>
									<option
										<c:if test="${param.orderstate=='0' }">selected="selected" </c:if>
										value="0">待确认</option>
									<option
										<c:if test="${param.orderstate=='14' }">selected="selected" </c:if>
										value="14">已取消</option>
									<option
										<c:if test="${param.orderstate=='15' }">selected="selected" </c:if>
										value="15">待收款</option>
									<option
										<c:if test="${param.orderstate=='4' }">selected="selected" </c:if>
										value="4">待发货</option>
									<option
										<c:if test="${param.orderstate=='5' }">selected="selected" </c:if>
										value='5'>已发货</option>
								</select>
							</c:if>


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
					<div class="delete-all">
						<div class="check-icon">
							<input type="checkbox" /><label class="active"></label>
						</div>
						<strong>全选</strong><a>批量删除</a>
					</div>

					<c:forEach items="${orderList}" varStatus="status" var="item">

						<div class="user-table-li">
							<table>
								<tbody>
									<tr>
										<td colspan="6" class="product-num"><div
												class="check-icon">
												<input type="checkbox" /><label></label>
											</div>订单号：${item.osn } <span><fmt:formatDate
													pattern="yyyy-MM-dd HH:mm:ss" value="${item.addtime}" /></span><a
											class="delete"></a></td>
									</tr>
									<tr class="table-sline">
										<td class="product-info"><div class="imghloder">
												<a target="_blank"
													href="${ctx }/business/OrderStateQuery.do?storeType=${param.storeType}&roleid=${param.roleid}&ordernum=${item.osn }"><img
													src="${ctx }/upload/product/source/${orderProductList[status.index][0].showimg }"
													alt="" /></a>
											</div>

											<div class="intro">
												<a
													href="${ctx }/business/OrderStateQuery.do?storeType=${param.storeType}&roleid=${param.roleid}&ordernum=${item.osn }">${orderProductList[status.index][0].name }</a>
											</div></td>
										<td class="table-wd01">${item.productamount}</td>
										<td class="table-wd01">￥${item.orderamount}</td>
										<td class="table-wd01 order-status"><span class="green">${orderStateList[status.index]}</span></td>
										<td class="table-wd01 order-operate"><a target="_blank"
											href="${ctx }/business/OrderStateQuery.do?storeType=${param.storeType}&roleid=${param.roleid}&ordernum=${item.osn }">
												查看详情 </a><a
											href="${ctx }/business/OrderCancelPage.do?storeType=${param.storeType}&roleid=${param.roleid}&ordernum=${item.osn }">取消订单
										</a><a
											onclick="dealAddProductToBuy(${orderProductList[status.index][0].pid }, ${item.productamount })"
											class="goshop">再次购买 </a></td>
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
	</div>

	<!--eof main-->
	<!--bof Service-->
	<c:import url="${ctx }/tool/newService.do"></c:import>
	<!--eof Service-->
	<%@ include file="../../../../includes/homenew/footer.jsp"%>

	<script type="text/javascript">
		//查询订单
		function queryOrder2() {

			//var state="${param.orderstate}"
			//$('#form').find('#orderstate').val(state);
			var postdata = $("#form").serialize();
			window.location.href="${ctx }/business/queryOrder.do?" + postdata;
		}
		
		//查询订单 (根据订单状态)
		function queryOrder(state) {
			//alert($('#form').find('#orderstate').val());
			$('#form').find('#orderstate').val(state);
			var postdata = $('#form').serialize();	
			
			window.location.href="${ctx }/business/queryOrder.do?" + postdata;
		}
		
		
		
	</script>

</body>
</html>
