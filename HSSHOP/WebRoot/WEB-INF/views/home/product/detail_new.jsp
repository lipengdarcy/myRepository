<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/includes/commons/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>红狮水泥商城</title>
<link rel="stylesheet" type="text/css"
	href="${ctx }/themes/grey/css/global.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/themes/grey/css/product.css" />
<link rel="shortcut icon" href="${ctx }/themes/grey/images/logo.ico" />
</head>
<body>
	<!--bof top-line-->
	<div class="top-line">
		<div class="wrap clearfix">
			<p class="left">
				您好，欢迎来到红狮水泥商城 ！ <a href="###" class="red">[ 请登录 ]</a> <a href="###">[
					免费注册 ]</a>
			</p>
			<ul class="right clearfix">
				<li><a href="###">我的订单 </a></li>
				<li><a href="seller_data.html">个人中心 </a></li>
				<li class="top-cart"><a href="###">购物车 </a><strong>0</strong></li>
				<li class="top-phone"><a href="###">手机端下载</a></li>
				<li><a href="###" class="red">[ 经销商门户 ]</a></li>
				<li><a href="###">帮助中心 </a></li>
			</ul>
		</div>
	</div>
	<!--eof top-line-->
	<!--bof header-->
	<div class="header">
		<div class="wrap clearfix">
			<div class="logo left" title="红狮水泥商城">
				<a href="${ctx }/index.do" title="红狮水泥商城">红狮水泥商城</a>
			</div>
			<div class="adress left">
				<span>配送地址</span>
				<p>浙江 - 金华 - 兰溪</p>
			</div>
			<div class="search right">
				<form>
					<fieldset class="clearfix">
						<input type="text" name="search" value='' placeholder="请输入您要查找的内容"
							class="s-input" /> <input type="button" name="search" value="搜索"
							class="s-button" />
					</fieldset>
				</form>
				<p>
					热门搜索： <a href="${ctx}/product/search.do?keyword=红狮">红狮</a> <a
						href="${ctx}/product/search.do?keyword=水泥">水泥</a>
				</p>
			</div>
		</div>
	</div>
	<!--eof header-->

	<!--顶部菜单-->
	<c:import url="${ctx }/tool/topMenu.do"></c:import>

	<div class="crumbs wrap">
		<strong>| <a href="seller_data.html">商城首页</a>
		</strong>&gt; <a href="###">红狮牌</a> &gt; <span>钱江牌 PC32.5 50KG 编织袋（白）
			兰溪红狮 通用水泥 </span>
	</div>
	<!--bof product-->
	<div class="product wrap clearfix">
		<!--bof productshow-->
		<div class="productshow clearfix">
			<div class="left productshow-img">
				<div class="img-big">
					<img src="themes/grey/images/products/pro.jpg" />
				</div>
				<ul class="img-ul clearfix">
					<li class="active"><a href="###"><img
							src="themes/grey/images/products/pro.jpg" /></a></li>
					<li><a href="###"><img
							src="themes/grey/images/products/pro.jpg" /></a></li>
					<li><a href="###"><img
							src="themes/grey/images/products/pro.jpg" /></a></li>
				</ul>
				<div class="img-btm">
					<a><i></i>加入收藏</a><span>商品编号：52019</span>
				</div>
			</div>
			<!--right-->
			<div class="left productshow-info">
				<h1>${productDetail.entity.name }</h1>
				<div class="price">
					<span>价格：</span>

					<!-- 有区域价格则显示区域价格，没有则显示市场价 -->
					<c:if test="${not empty productDetail.productPrice }">

						<c:forEach items="${productDetail.productPrice}" var="p"
							varStatus="status">
							<c:if test="${p.pricetype == 1 }">
								<span id="price${status.index }" price="${p.price }">￥${p.price }
									元/${productDetail.QuantityUnit.unitname}</span>
							</c:if>
						</c:forEach>
					</c:if>

					<c:if test="${empty productDetail.productPrice }">
						<span price="${productDetail.entity.marketprice }"
							id="priceDefault">￥ ${productDetail.entity.marketprice }
							元/${productDetail.QuantityUnit.unitname}
							</dd>
					</c:if>

				</div>
				
				<!--begin 产品sku-->
				<ul class="param">
					<c:forEach items="${productDetail.skuList}" var="p">
						<!-- 生产厂家 要根据选择的品牌来展示-->
						<c:if test="${p.attrid==52}">
							<!-- 品牌-->
							<li><strong>${p.attrname}：</strong>
								<ul>
									<c:forEach items="${p.skuList}" var="ps">
										<li <c:if test="${ps.pid==productId}">  class="active" </c:if>>
											<a href="${ctx}/product/detail.do?id=${ps.pid}">${ps.attrvalue}
										</a>
										</li>
									</c:forEach>
								</ul></li>
							<!--生产厂家 -->
							<li><strong>生产厂家：</strong>
								<ul>
									<c:forEach items="${p.brand_factory}" var="bf">
										<c:if test="${bf.key==brandId}">
											<c:forEach items="${bf.value}" var="ps">
												<li
													<c:if test="${ps.pid==productId}">  class="active" </c:if>>
													<a href="${ctx}/product/detail.do?id=${ps.pid}">${ps.attrvalue}
												</a>
												</li>
											</c:forEach>
										</c:if>
									</c:forEach>
								</ul></li>
						</c:if>

						<c:if test="${p.attrid!=51 && p.attrid!=52}">
							<li><strong>${p.attrname}：</strong>
								<ul>
									<c:forEach items="${p.skuList}" var="ps">
										<li <c:if test="${ps.pid==productId}">  class="active" </c:if>>
											<a href="${ctx}/product/detail.do?id=${ps.pid}">${ps.attrvalue}
										</a>
										</li>
									</c:forEach>
								</ul></li>
						</c:if>
					</c:forEach>
				</ul>
				<!--end 产品sku-->

				<div class="num">
					<strong>购买数量：</strong>
					<div class="num-put">
						<em class="del">-</em> <input type="text" value="1" /> <em
							class="add">+</em>
					</div>
					<span>吨</span>
				</div>
				<div class="total">
					<strong>总价：</strong><em>￥11.25元</em><span>= 0.05吨X225 元/吨</span>
				</div>
				<div class="btn">
					<!--<a class="in-cart">加入购物车</a>-->
					<a class="buy">立即购买</a>
				</div>
			</div>
		</div>
		<!--eof productshow-->
		<!--bof product-detial-->
		<div class="product-intro left">
			<ul class="detail-tab">
				<li class="active"><a href="javascript:void(0);">商品介绍</a></li>
				<li><a href="javascript:void(0);">质量指标</a></li>
				<li><a href="javascript:void(0);">适用范围</a></li>
				<li><a href="javascript:void(0);">售后保障</a></li>
			</ul>
			<div class="detial-info">我们的产品：
				公司目前的主导产品有：红狮牌P.O52.5（低碱）、P.O42.5（R）、P.O42.5（低碱）普通硅酸盐水泥、P.C32.5（R）复合硅酸盐水泥和商品硅酸盐水泥熟料，并可根据用户的不同需求生产其他品种水泥
				红狮水泥具有以下特点：色泽美观、安定性能优良、均匀性好、凝结硬化速度适中、早期强度高、后期强度增进率大、抗渗、耐磨等耐久性能突出、外加剂适应性强。公司产品可广泛适用于高层建筑、高速公路、桥梁隧道、水电工程等工业与民用建筑工程。
			</div>
		</div>
		<!--eof product-detial-->
		<!--bof product-related -->
		<div class="product-recommend">
			<h2>推荐商品</h2>
			<ul class="clearfix">
				<li>
					<div class="imghloder">
						<a href="product.html"><img
							src="themes/grey/images/products/product01.jpg" width="262"
							height="238" alt="" /></a>
					</div>
					<div class="info">
						<em>兰溪红狮</em>
						<div class="price">
							<del>￥360</del>
							<strong>￥340</strong>
						</div>
						<p>
							<a href="product.html">钱江牌 PC32.5 50KG 编织袋（白） 兰溪红狮 通用水泥 </a>
						</p>
					</div>
				</li>
				<li>
					<div class="imghloder">
						<a href="product.html"><img
							src="themes/grey/images/products/product01.jpg" width="262"
							height="238" alt="" /></a>
					</div>
					<div class="info">
						<em>兰溪红狮</em>
						<div class="price">
							<del>￥360</del>
							<strong>￥340</strong>
						</div>
						<p>
							<a href="product.html">钱江牌 PC32.5 50KG 编织袋（白） 兰溪红狮 通用水泥 </a>
						</p>
					</div>
				</li>
				<li>
					<div class="imghloder">
						<a href="product.html"><img
							src="themes/grey/images/products/product01.jpg" width="262"
							height="238" alt="" /></a>
					</div>
					<div class="info">
						<em>兰溪红狮</em>
						<div class="price">
							<del>￥360</del>
							<strong>￥340</strong>
						</div>
						<p>
							<a href="product.html">钱江牌 PC32.5 50KG 编织袋（白） 兰溪红狮 通用水泥 </a>
						</p>
					</div>
				</li>
			</ul>
		</div>
		<!--eof product-related -->
	</div>
	<!--eof product-->
	<!--bof service-->
	<div class="service">
		<div class="wrap">
			<ul class="clearfix">
				<li class="service-icon01">
					<div class="imghloder"></div>
					<dl>
						<dt>品质保障</dt>
						<dd>品质护航，购物无忧</dd>
					</dl>
				</li>
				<li class="service-icon02">
					<div class="imghloder"></div>
					<dl>
						<dt>急速物流</dt>
						<dd>急速物流，急速送达</dd>
					</dl>
				</li>
				<li class="service-icon03">
					<div class="imghloder"></div>
					<dl>
						<dt>送货到家</dt>
						<dd>自有物流，送货到家</dd>
					</dl>
				</li>
				<li class="service-icon04">
					<div class="imghloder"></div>
					<dl>
						<dt>无忧售后</dt>
						<dd>无忧售后，退订保障</dd>
					</dl>
				</li>
				<li class="service-icon05">
					<div class="imghloder"></div>
					<dl>
						<dt>会员特权</dt>
						<dd>会员升级，尊贵特权</dd>
					</dl>
				</li>
			</ul>
		</div>
	</div>
	<!--eof service-->

	<!--bof footer-->
	<div class="footer">
		<div class="wrap">
			<ul class="clearfix footer-menu">
				<li>
					<ul>
						<li><strong>| 关于我们</strong></li>
						<li><a href="###">商城介绍</a></li>
						<li><a href="###">联系我们</a></li>
						<li><a href="###">注册协议</a></li>
						<li><a href="###">短信模版</a></li>
						<li><a href="###">用户合同</a></li>
						<li><a href="###">公司招聘</a></li>
					</ul>
				</li>
				<li>
					<ul>
						<li><strong>| 用户注册</strong></li>
						<li><a href="###">手机注册</a></li>
						<li><a href="###">邮箱/手机验证</a></li>
						<li><a href="###">用户登录</a></li>
						<li><a href="###">忘记密码</a></li>
					</ul>
				</li>
				<li>
					<ul>
						<li><strong>| 支付方式</strong></li>
						<li><a href="###">在线支付</a></li>
						<li><a href="###">工厂支付</a></li>
						<li><a href="###">门店支付</a></li>
						<li><a href="###">经销商垫资</a></li>
						<li><a href="###">经销商结算规则</a></li>
						<li><a href="###">定价/调节规则</a></li>
					</ul>
				</li>
				<li>
					<ul>
						<li><strong>| 配送政策</strong></li>
						<li><a href="###">门店自提</a></li>
						<li><a href="###">工厂自提</a></li>
						<li><a href="###">门店配送</a></li>
						<li><a href="###">运费规则</a></li>
					</ul>
				</li>
				<li>
					<ul>
						<li><strong>| 服务咨询</strong></li>
						<li><a href="###">退货规则</a></li>
						<li><a href="###">售后服务</a></li>
						<li><a href="###">投诉与建议</a></li>
					</ul>
				</li>
				<li>
					<ul>
						<li><strong>| 帮助中心</strong></li>
						<li><a href="###">在线客服</a></li>
						<li><a href="###">法律声明</a></li>
					</ul>
				</li>
				<li class="f-er">
					<div class="imghloder">
						<img src="themes/grey/images/er.jpg" alt="" />
					</div> <em>扫描微信二维码下载APP</em>
				</li>
			</ul>
			<div class="certification">
				<a href="###"><img
					src="themes/grey/images/certification/certification01.jpg" alt="" /></a><a
					href="###"><img
					src="themes/grey/images/certification/certification02.jpg" alt="" /></a><a
					href="###"><img
					src="themes/grey/images/certification/certification03.jpg" alt="" /></a><a
					href="###"><img
					src="themes/grey/images/certification/certification04.jpg" alt="" /></a>
			</div>
		</div>
	</div>
	<!--eof footer-->
	<div class="copyright">浙ICP证000000号 | 互联网信息服务资格证编号(浙)-经营性-2015
		红狮水泥商城 版权所有 @2015, runlion.com Inc.</div>
	<!--bof fixed-->
	<div class="fixed-menu">
		<div class="fixed-user">
			<a href="###"><i></i>登录</a><a href="###">注册</a>
		</div>
		<div class="fixed-cart">
			<a href="###"><i></i>购物车</a><span>0</span>
		</div>
		<div class="fixed-service">
			<a href="###"><i></i>客服</a>
		</div>
		<div class="fixed-feedback">
			<a href="###"><i></i>反馈</a>
		</div>
		<div class="fixed-backtop">
			<a href="#"><i></i>TOP</a>
		</div>
		<div class="fixed-close">
			<a href="###"><i></i>close</a>
		</div>
	</div>
	<!--bof fixed-->
	<script type="text/javascript"
		src="${ctx }/scripts/grey/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx }/scripts/grey/main.js"></script>
</body>
</html>
