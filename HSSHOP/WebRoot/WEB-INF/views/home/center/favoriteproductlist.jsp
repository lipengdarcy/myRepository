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


<title>评论列表-红狮水泥商城</title>
</head>
<body>

	<%@ include file="../../../includes/homenew/headerBar.jsp"%>
	<%@ include file="../../../includes/homenew/headerTop.jsp"%>


	<!--bof main-->
	<div class="user-main">
		<div class="wrap clearfix">
			<div class="crumbs">
				<strong>| <a href="seller_data.html">个人中心首页</a>
				</strong> &gt; <a href="${ctx }/ucenter/userinfo.do?&navid=10">个人中心</a>
			</div>
			<!--bof menu-->

			<div class="user-menu left">
				<%@ include file="centermenu.jsp"%>
			</div>
			<!--eof menu-->
			<!--bof content-->
			<div class="user-content right">
				<h1>商品收藏</h1>
				<!--bof product-favorites -->
				<div class="product-user favorites-order-list">
					<ul class="clearfix">
						

						<c:forEach items="${bflist}" var="bf" varStatus="vs">
							<li id="favoriteProduct${bf.pid}">
								<div class="imghloder">
									<a href="${ctx }/product/detail.do?id=${bf.pid}"><img
										src="${ctx}/upload/product/thumb/60_60/${bf.product.showimg}"
										alt="" /></a>
									<div class="cart-line">
									<a class="list-cart" href="javascript:void(0)"
									onclick="addProductToCart(${bf.pid}, 1)">加入购物车</a>
										<a class="list-collect active" onclick="cancelFavourite(${bf.recordid})">取消收藏</a>
										
									</div>
								</div>
								<div class="info">
									<em>兰溪红狮</em>
									<div class="price">
										<strong class="red">￥${bf.product.marketprice}</strong>
									</div>
									<p>
										<a href="${ctx }/product/detail.do?id=${bf.pid}">${bf.product.name}
										</a>
									</p>
								</div>
							</li>
						</c:forEach>
															
					</ul>
				</div>
				<!--eof product-favorites -->
				<!--bof page-->
				${pageDiv }				
				<!--eof page-->
			</div>
			<!--bof content-->
		</div>
	</div>
	<!--eof main-->

	<!--bof Service-->
	<c:import url="${ctx }/tool/newService.do"></c:import>
	<!--eof Service-->
	<%@ include file="../../../includes/homenew/footer.jsp"%>
	
	<script type="text/javascript">
		
		//取消收藏 
		function cancelFavourite(id) {

			//弹窗提示配置
			var hsArtDialog=dialog({
			  	title: '提示',
			  	id:"hs-dialog",
			  	fixed:true,
			  	width:300,
			  	height:50
			});
			$.ajax({
				url : '${ctx }/ucenter/cancelfavoriteproduct.do',
				data : {id:id},
				type : 'post',
				dataType : 'json',
				success : function(data) {					
						hsArtDialog.content(data.content).showModal();	
						//window.location.href = "${ctx }/ucenter/favoriteproductlist.do?&navid=14";
				},
				error : function() {
					hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
				}
			});
		}
	</script>
	
</body>
</html>
