<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../includes/commons/taglibs.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>红狮水泥商城</title>
<meta name="keywords" content="红狮水泥商城" />
<meta name="description" content="红狮水泥商城" />
<%@ include file="../../includes/home/header.jsp"%>
<link href="${ctx }/themes/default/css/home.css" rel="stylesheet"
	type="text/css" />
<script src="${ctx }/scripts/MSClass.js" type="text/javascript"></script>
<script src="${ctx }/scripts/product.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function(e){
		if($.browser.msie&&($.browser.version == "7.0")){ 
			//alert("ie7");
			var Marquee1 = new Marquee("bannerBox");
			//Marquee1.MSClassID = "bannerBox";
			Marquee1.ContentID = "bannerContentID";
			Marquee1.TabID = "bannerNumID";
			Marquee1.Direction =2;
			Marquee1.Step = 0.5;
			Marquee1.Width = 700;
			Marquee1.Height = 300;
			Marquee1.Timer = 20;
			Marquee1.DelayTime = 3000;
			Marquee1.WaitTime = 0;
			Marquee1.ScrollStep = 700;
			Marquee1.Start();
			
		} else{
			var Marquee1=new Marquee({
				MSClassID : "bannerBox",
				ContentID : "bannerContentID",
				TabID : "bannerNumID",
				Direction : 2,
				Step : 0.5,
				Width : 700,
				Height : 300,
				Timer : 20,
				DelayTime : 3000,
				WaitTime : 0,
				ScrollStep : 700,
				SwitchType : 0,
				AutoStart : 1
			});
		}
		
	});
</script>
</head>
<body>

	<%@ include file="../../includes/home/headerBar.jsp"%>

	<%@ include file="../../includes/home/headerTop.jsp"%>

	<%-- <c:import url="${ctx }/tool/topMenu.do"></c:import> --%>
	<%@ include file="includes/topMenu.jsp"%>

	<div id="index1" class="box clearfix">

		<div class="indexPic left">

			<div style="overflow: hidden; width: 700px; height: 300px; position: relative;">
				<div id="bannerBox">
					<ul id="bannerContentID">
						<li><a href="/"><img
								src="${ctx }/upload/banner/fr_1412061404419541666.jpg"
								width="700px" height="300px" /></a></li>
						<li><a href="/"><img
								src="${ctx }/upload/banner/fr_1412061404492825858.jpg"
								width="700px" height="300px" /></a></li>
						<li><a href="/"><img
								src="${ctx }/upload/banner/fr_1412061404554409380.jpg"
								width="700px" height="300px" /></a></li>
						<li><a href="/"><img
								src="${ctx }/upload/banner/fr_1412061405306289506.jpg"
								width="700px" height="300px" /></a></li>
					</ul>
				</div>
				<ul id="bannerNumID">
					<li class="">1</li>
					<li class="">2</li>
					<li class="">3</li>
					<li class="">4</li>
				</ul>
			</div>
			
		</div>
		<div class="gonggao right">
			<h2>
				最新动态<a href="${ctx }/news/list.do?newstypeid=1" class="more">最新动态></a>
			</h2>
			<ul class="textList">
				<c:forEach var="news" items="${newsList1 }">
					<li><a href="${ctx }/news/detail.do?id=${news.newsid }">${news.title }</a></li>
				</c:forEach>
			</ul>
		</div>

	</div>

	<!--bof index2-->
	<div id="index2" class="box clearfix">
		<!--bof category-->
		<div class="category left">
			<h2>
				热门分类<span>CATEGORY</span>
			</h2>
			<ul>
				<c:forEach items="${productCombo}" var="p">
					<!-- 包装 -->
					<c:if test="${p.attrid==47}">
						<li class="clearfix"><strong>${p.attrname}：</strong>
							<ul>
								<c:forEach items="${p.productlinklist}" var="pl">
									<c:if test="${pl.attrValue.attrvalueid !=null}">
										<li><a
											href="${ctx}/product/comboQuery.do?path=${pl.path}"><c:out
													value="${pl.attrValue.attrvalue}" /></a></li>
									</c:if>
								</c:forEach>
							</ul></li>
					</c:if>
					<!-- 品种 -->
					<c:if test="${p.attrid==48}">
						<li class="clearfix"><strong>${p.attrname}：</strong>
							<ul>
								<c:forEach items="${p.productlinklist}" var="pl">
									<c:if test="${pl.attrValue.attrvalueid !=null}">
										<li><a
											href="${ctx}/product/comboQuery.do?path=${pl.path}"><c:out
													value="${pl.attrValue.attrvalue}" /></a></li>
									</c:if>
								</c:forEach>
							</ul></li>
					</c:if>
					<!-- 强度 -->
					<c:if test="${p.attrid==49}">
						<li class="clearfix"><strong>${p.attrname}：</strong>
							<ul>
								<c:forEach items="${p.productlinklist}" var="pl">
									<c:if test="${pl.attrValue.attrvalueid !=null}">
										<li><a
											href="${ctx}/product/comboQuery.do?path=${pl.path}"><c:out
													value="${pl.attrValue.attrvalue}" /></a></li>
									</c:if>
								</c:forEach>
							</ul></li>
					</c:if>
					<!-- 品牌 -->
					<c:if test="${p.attrid==52}">
						<li class="clearfix"><strong>${p.attrname}：</strong>
							<ul>
								<c:forEach items="${p.productlinklist}" var="pl">
									<c:if test="${pl.attrValue.attrvalueid !=null}">
										<li><a
											href="${ctx}/product/comboQuery.do?path=${pl.path}"><c:out
													value="${pl.attrValue.attrvalue}" /></a></li>
									</c:if>
								</c:forEach>
							</ul></li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
		<!--eof category-->
		<!--bof shop-guide-->
		<div class="gonggao shop-guide right">
			<h2>
				促销活动<a href="${ctx }/news/list.do?newstypeid=2" class="more">促销活动></a>
			</h2>
			<ul class="textList">
				<c:forEach var="news" items="${newsList2 }">
					<li><a href="${ctx }/news/detail.do?id=${news.newsid }">${news.title }</a></li>
				</c:forEach>
			</ul>
		</div>
		<!--bof shop-guide-->
	</div>
	<!--eof index2-->
	<!--bof ad-->
	<div class="index-ad">
		<a href="###"><img src="${ctx}/upload/advert/ad01.jpg"
			width="1000px" height="126px" /></a>
	</div>
	<!--eof ad-->

	<!-- 1楼 -->
	<div class="floor box clearfix">
		<h2 class="floorT clearfix">热门商品</h2>

		<div class="floorBox box">
			<ul class="clearfix">

				<c:forEach items="${hotProducts}" var="p" varStatus="status">
					<c:if test="${status.first}">
						<li class="proBig"><div class="imghloder">
								<a href="${ctx}/product/detail.do?id=${p.product.pid}" title="${p.product.name}"><img
									src="${ctx}/upload/product/source/${p.picList[0].showimg}" onerror="nofind();"
									alt="${p.product.name}" height="330" /></a>
							</div></li>
					</c:if>

					<c:if test="${status.index >0}">
						<li>
							<div class="imghloder">
								<a href="${ctx}/product/detail.do?id=${p.product.pid}" title=""><img
									src="${ctx}/upload/product/source/${p.picList[0].showimg}" onerror="nofind();"
									alt="${p.product.name}" width="218" height="218" /></a>
							</div>
							<div class="product-info">
								<a href="javascript:void(0)"
									onclick="addProductToCart(${p.product.pid}, 1)">加入购物车</a>
								<div class="product-intro">
									<a href="${ctx}/product/detail.do?id=${p.product.pid}">${p.product.name}</a>
								</div>
							</div>
						</li>
					</c:if>

				</c:forEach>

			</ul>
		</div>
	</div>

	<!-- 2楼 -->
	<div class="floor box clearfix">
		<h2 class="floorT clearfix">最新商品</h2>
		<div class="floorBox box">
			<ul class="clearfix">


				<c:forEach items="${newestProducts}" var="p" varStatus="status">
					<c:if test="${status.first}">
						<li class="proBig"><div class="imghloder">
								<a href="${ctx}/product/detail.do?id=${p.product.pid}" title="${p.product.name}"><img
									src="${ctx}/upload/product/source/${p.picList[0].showimg}" onerror="nofind();"
									alt="${p.product.name}" height="330" /></a>
							</div></li>
					</c:if>

					<c:if test="${status.index >0}">
						<li>
							<div class="imghloder">
								<a href="${ctx}/product/detail.do?id=${p.product.pid}" title=""><img
									src="${ctx}/upload/product/source/${p.picList[0].showimg}" onerror="nofind();"
									alt="${p.product.name}" width="218" height="218" /></a>
							</div>
							<div class="product-info">
								<a href="javascript:void(0)"
									onclick="addProductToCart(${p.product.pid}, 1)">加入购物车</a>
								<div class="product-intro">
									<a href="${ctx}/product/detail.do?id=${p.product.pid}">${p.product.name}</a>
								</div>
							</div>
						</li>
					</c:if>

				</c:forEach>


			</ul>
		</div>
	</div>

	<%@ include file="../../includes/home/footer.jsp"%>

</body>
</html>
