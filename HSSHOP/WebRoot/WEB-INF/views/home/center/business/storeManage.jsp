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

<title>订单列表-红狮水泥商城</title>
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
				<%@ include file="../centermenu.jsp"%>
			</div>
			<!--eof menu-->
			<!--bof content-->
			<div class="user-content right">
				<form id="form" method="post"
					action="${ctx }/business/queryOrder.do">

					<input type="hidden" name="id" id="id" value="${store.id }" /> <input
						type="hidden" name="storeType" id="storeType"
						value="${storeType }" /> <input type="hidden" name="roleid"
						id="roleid" value="${roleid}" />


					<h1>订单列表</h1>
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
							<input name="ordernum" placeholder="请输入订单编号" value="${ordernum }" />
						</fieldset>
						<fieldset class="clearfix">
							<legend>商品名称：</legend>
							<input name="productname" placeholder="请输入商品名称" />
						</fieldset>
						<fieldset class="clearfix search-select">
							<legend>状态：</legend>
							<select name="orderstate">
								<option value="-1">请选择</option>
								<option value="0">待确认</option>
								<option value="4">待发货</option>
								<option value='5'>已发货</option>
								<option value="11">已失效</option>
							</select>


						</fieldset>
						<fieldset class="clearfix search-date">
							<legend>订单日期：</legend>
							<input type="text" name="begin"
								onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" /><label>到</label>
							<input type="text" name="end"
								onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})" />


						</fieldset>

						<fieldset class="clearfix btn">
							<input type="button" onclick="queryOrder()" value="查询"
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
													href="${ctx }/business/OrderStateQuery.do?ordernum=${item.osn }"><img
													src="${ctx }/upload/product/source/${orderProductList[status.index][0].showimg }"
													alt="" /></a>
											</div>

											<div class="intro">
												<a
													href="${ctx }/business/OrderStateQuery.do?ordernum=${item.osn }">${orderProductList[status.index][0].name }</a>
											</div></td>
										<td class="table-wd01">${item.productamount}</td>
										<td class="table-wd01">￥${item.orderamount}</td>
										<td class="table-wd01 order-status"><span class="green">${orderStateList[status.index]}</span></td>
										<td class="table-wd01 order-operate"><a target="_blank"
											href="${ctx }/business/OrderStateQuery.do?ordernum=${item.osn }">
												查看详情 </a><a
											href="${ctx }/business/OrderCancelPage.do?orderid=${item.oid}">取消订单
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
			<!--bof page-->
			<div class="page clearfix">
				<div class="page-info clearfix">
					<span>共20页 到第</span><input type="text" /> <span>页 <a
						href="javascript:void(0);" target="_blank">GO</a></span>
				</div>
				<div class="page-button clearfix">
					<a href="javascript:void(0);" class="curr">1</a><a
						href="javascript:void(0);">2</a><a href="javascript:void(0);">3</a><a
						href="javascript:void(0);">4</a><a href="javascript:void(0);">5</a><a
						href="javascript:void(0);">下一页</a><a href="javascript:void(0);">尾页</a>
				</div>
			</div>
			<!--eof page-->
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
		function queryOrder(state) {
			var $form = $("#form");
			var postdata = $form.serialize();
			//弹窗提示配置
			var hsArtDialog=dialog({
			  	title: '提示',
			  	id:"hs-dialog",
			  	fixed:true,
			  	width:300,
			  	height:50
			});
			$.ajax({
				url : "${ctx }/business/queryOrder.do",
				type : "post",
				async:false, //默认是true：异步，false：同步。
				data : postdata,
				async : false,
				success : function(data, staus) {
					//后台执行成功后的回调函数
					if (data.state = "success") {
						hsArtDialog.content("操作成功！").showModal();
						window.location.reload();
					} else {
						hsArtDialog.content(result.errmsg).showModal();
					}
				},
				error : function(xhr, errinfor, ex) {
					hsArtDialog.content(errinfor).showModal();
				}
			});

		}
	</script>

</body>
</html>
