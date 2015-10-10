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

<title>订单收款-红狮水泥商城</title>
</head>
<body>

	<%@ include file="../../../../includes/homenew/headerBar.jsp"%>
	<%@ include file="../../../../includes/homenew/headerTop.jsp"%>

	<!--bof main-->
	<div class="user-main">
		<div class="wrap clearfix">
			<div class="crumbs">
				<strong>| <a href="seller_data.html">个人中心首页</a>
				</strong>&gt; <a href="${ctx }/ucenter/userinfo.do?&navid=10">个人中心</a> &gt; <span>订单收款</span>
			</div>

			<!--bof menu-->
			<div class="user-menu left">
				<%-- <c:import url="${ctx }/ucenter.do?userType=${userType}&navId=${storeidNavid }" /> --%>
				<%@ include file="../centermenu.jsp"%>
			</div>
			<!--eof menu-->

			<!--bof content-->
			<div class="user-content right">
				<h1>订单收款</h1>
				<div class="user-order">
					<!--01-->
					<div class="order-list01">
						<ul>
							<li>
								<ul class="clearfix">
									<li>支付方式： <em class="blue"> 
									<!-- 
									<c:if
												test="${orderinfo.paymode=='0'}">门店支付</c:if> <c:if
												test="${orderinfo.paymode!='0'}">工厂支付</c:if>
												 -->
												
												<c:if test="${orderinfo.paymode==1}">到店支付</c:if>
													<c:if test="${orderinfo.paymode==2}">到厂支付</c:if>
													<c:if test="${orderinfo.paymode==3}">货到付款</c:if>
													<c:if test="${orderinfo.paymode==4}">在线支付</c:if>
									</em>
									</li>
									<li>合计金额： ￥ ${orderinfo.orderamount}</li>
									<li class="right">状态： <em class="green"><c:if
												test="${orderinfo.paystate!='0'}"> 已付款 </c:if> <c:if
												test="${orderinfo.paystate=='0'}"> 未付款 </c:if> </em></li>
								</ul>
							</li>
							<li>
								<ul class="clearfix">
									<li>订单号：${orderinfo.osn}</li>
									<li>下单时间：${formate.format(orderinfo.addtime)}</li>
									<li class="right">状态： <em class="green">${orderStateName}</em></li>
								</ul>
							</li>
						</ul>
					</div>
					<!--01-->
					<!--02-->
					<div class="order-list03">
						<div class="order-tab order-tab-grey">备注</div>
						<p>${orderinfo.remark}</p>
					</div>
					<!--02-->
					<!--03-->
					<div class="order-list04">
						<div class="order-tab order-tab-grey">商品清单</div>
						<div class="order-table">
							<div class="user-table-head">
								<table>
									<tbody>
										<tr>
											<th scope="col" class="product-info">商品信息</th>
											<th scope="col" class="table-wd04">价格</th>
											<th scope="col" class="table-wd04">数量</th>
											<th scope="col" class="table-wd04">合计</th>
											<th scope="col" class="table-wd04">运费</th>
											<th scope="col" class="table-wd04">装卸费</th>
											<th scope="col" class="table-wd04">总金额</th>
										</tr>
									</tbody>
								</table>
							</div>
							<!--01-->
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
												<td class="table-wd04">￥${bop.marketprice}</td>
												<td class="table-wd04">${bop.buycount}</td>
												<td class="table-wd04">￥${bop.marketprice.multiply(bop.buycount)}</td>
												<td class="table-wd04">￥ ${orderinfo.shipfee}</td>
												<td class="table-wd04">￥${orderinfo.handlingcost}</td>
												<td class="table-wd04">￥ ${orderinfo.orderamount}</td>
											</tr>
										</c:forEach>

									</tbody>
								</table>
							</div>
							<!--01-->
						</div>
					</div>
					<!--03-->
					<!--04-->

					<c:if test="${orderinfo.paystate=='0'}">
						<div class="order-list03">
							<div class="order-tab order-tab-grey">收款信息</div>
							<div class="order-cencel-box order-collection-box">
								<form id="form" action="" class="clearfix">
									<input type="hidden" name="oid" value="${payorder.oid }" /> <input
										type="hidden" name="osn" value="${payorder.osn }" />
									<fieldset class="clearfix">
										<legend>收款方式：</legend>
										<select id="paytype" name="paytype">
											<option value="-1">请选择</option>
											<option value="0">现金</option>
											<option value="1">承兑汇票</option>
										</select>
									</fieldset>
									<fieldset class="clearfix">
										<legend>收款金额(元)：</legend>
										<input type="text" name="payamount" value=""
											placeholder="请输入金额" />
									</fieldset>
									<fieldset class="clearfix collection-note">
										<legend>收款说明：</legend>
										<textarea name="description" rows="3" cols="20"></textarea>
									</fieldset>
									<fieldset class="clearfix cencel-btn clear">
										<input type="submit" class="submit-btn" value="提交" /> <input
											type="reset" class="reset-btn" value="重置" />
									</fieldset>
								</form>
							</div>
						</div>
					</c:if>
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
		$(function() {
			//检查后提交
			$("#form").validationEngine({
				onValidationComplete : function(form, status) {
					if (status) {
						gotoSelectPage();
					}
				}
			});
		});

		function gotoSelectPage() {
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
				url : 'payOrder.do',
				data : postdata,
				type : 'post',
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						hsArtDialog.content(data.errorMsg).showModal();
						window.location.href = "${ctx }/business/OrderStateQuery.do?storeType=${param.storeType }&roleid=${param.roleid }&ordernum=${payorder.osn }";
						//window.location.reload();
					} else {
						hsArtDialog.content(data.errorMsg).showModal();
					}
				},
				error : function() {
					hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
				}
			});
		}
	</script>

</body>
</html>
