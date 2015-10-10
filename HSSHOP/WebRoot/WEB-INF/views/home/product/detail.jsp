<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${productDetail.entity.name }-${webinfor.shopName}</title>
<meta name="keywords"
	content="${productDetail.entity.name }-${webinfor.SEOKeyword}" />
<meta name="description"
	content="${productDetail.entity.name }-${webinfor.SEODescription}" />
<%@ include file="../../../includes/home/header.jsp"%>
<link href="${ctx }/themes/default/css/content.css" rel="stylesheet"
	type="text/css" />
<script src="${ctx }/scripts/product.js" type="text/javascript"></script>
<link href="${ctx }/scripts/magiczoom/magiczoom.css" rel="stylesheet"
	type="text/css" />
<script src="${ctx }/scripts/magiczoom/magiczoom.js"
	type="text/javascript"></script>

<script type="text/javascript"
	src="${ctx }/scripts/jquery.toastmessage.js"></script>
<link href="${ctx }/themes/default/css/jquery.toastmessage.css"
	rel="stylesheet" type="text/css" />

</head>

<body>

	<%@ include file="../../../includes/home/headerBar.jsp"%>

	<%@ include file="../../../includes/home/headerTop.jsp"%>

	<c:import url="${ctx }/tool/topMenu.do"></c:import>
	<%-- <%@ include file="../includes/topMenu.jsp"%> --%>

	<div class="breadcrumb box1210"></div>
	<div class="box1210" id="content">
		<div class="conL left">
			<div class="conLitme">
				<a
					href="${ctx}/upload/product/source/${productDetail.productPics[0].showimg}"
					onerror="nofind();" id="zoomer" class="MagicZoom"
					rel="selectors-effect:false;zoom-fade:true;background-opacity:70;zoom-width:350;zoom-height:350;caption-source:img:title;thumb-change:mouseover"
					title=""> <img
					src="${ctx}/upload/product/source/${productDetail.productPics[0].showimg}"
					onerror="nofind();" id="pShowImg"
					style="width: 450px; height: 340px;" />
				</a>
			</div>

			<div class="conLnav">
				<a href="javascript:void(0)" onclick="moveL()"
					class="leftBt leftBtl abtn aleft">&lt;</a>
				<div id="demo"
					style="margin-left: 19px; width: 310px; height: 58px; overflow: hidden; position: relative;">
					<div class="leftBtInner" id="demo1">
						<ul>
							<c:forEach items="${productDetail.productPics}" var="p">
								<li><a
									href="${ctx}/upload/product/source/${p.showimg}"
									rev="${ctx}/upload/product/source/${p.showimg}"
									rel="zoom-id:zoomer" title=""> <img
										src="${ctx}/upload/product/source/${p.showimg}"
										style="width: 54px; height: 54px;" onerror="nofind();"
										onmouseover="selectProductImg(this)" /></a></li>
							</c:forEach>

							<div class="clear"></div>
						</ul>
					</div>
				</div>
				<a href="javascript:void(0)" onclick="moveR()"
					class="leftBt leftBtR abtn aright">&gt;</a>
			</div>

		</div>
		<script type="text/javascript">
			var canRoll = false;
			var inBox = document.getElementById("demo1");
			var liList = inBox.getElementsByTagName("li");
			if (liList.length > 5) {
				canRoll = true;
			}
			var boxWidth = 62;
			var step = 0;
			var maxStep = liList.length - 5;
			function selectProductImg(obj) {
				for (var i = 0; i < liList.length; i++) {
					liList[i].className = ""
				}
				obj.parentNode.parentNode.className = "hot"
			}
			function moveL() {
				if (!canRoll) {
					return;
				}
				if (step < maxStep) {
					step = step + 1;
					inBox.style.marginLeft = (-1 * (step * boxWidth)) + "px"
				}
			}
			function moveR() {
				if (!canRoll) {
					return;
				}
				if (step > 0) {
					step = step - 1;
					inBox.style.marginLeft = (-1 * (step * boxWidth)) + "px"
				}
			}
		</script>
		<div class="conR right">
			<div id="name">
				<h1>${productDetail.entity.name }</h1>
				<strong></strong>
			</div>

			<div class="conLL">
				<div id="info">
					<dl>
						<dt id="productWeight" weight="${productDetail.entity.weight }">商品编号：</dt>
						<dd>${productDetail.entity.psn }</dd>
					</dl>
					<!-- 有区域价格则显示区域价格，没有则显示市场价 -->
					<c:if test="${not empty productDetail.productPrice }">
						<dl>
							<dt>价格：</dt>
							<c:forEach items="${productDetail.productPrice}" var="p"
								varStatus="status">
								<c:if test="${p.pricetype == 1 }">
									<dd id="price${status.index }" class="price"  oriprice="${p.price }"
										price="${p.price }">￥<span pricespan>${p.price }</span>
										元/${productDetail.QuantityUnit.unitname}</dd>
								</c:if>
							</c:forEach>

						</dl>
					</c:if>

					<c:if test="${empty productDetail.productPrice }">
						<dl>
							<dt>价格：</dt>
							<dd price="${productDetail.entity.marketprice }" oriprice="${productDetail.entity.marketprice }"
								id="priceDefault" class="price">￥
								<span pricespan>${productDetail.entity.marketprice }</span>
								元/${productDetail.QuantityUnit.unitname}</dd>
						</dl>
					</c:if>

					<c:forEach items="${productDetail.skuList}" var="p">
						<!-- 生产厂家 要根据选择的品牌来展示-->
						<c:if test="${p.attrid==52}">
							<!-- 品牌-->
							<dl class="choose ">
								<dt>${p.attrname}：</dt>
								<dd>
									<c:forEach items="${p.skuList}" var="ps">
										<a href="${ctx}/product/detail.do?id=${ps.pid}"
											class="itme <c:if test="${ps.pid==productId}">  hot</c:if> ">${ps.attrvalue}
										</a>
									</c:forEach>
								</dd>
								<div class="clear"></div>
							</dl>
							<!--生产厂家 -->
							<dl class="choose ">
								<dt>生产厂家：</dt>
								<dd>
									<c:forEach items="${p.brand_factory}" var="bf">
										<c:if test="${bf.key==brandId}">
											<c:forEach items="${bf.value}" var="ps">
												<a href="${ctx}/product/detail.do?id=${ps.pid}"
													class="itme <c:if test="${ps.pid==productId}">  hot</c:if> ">${ps.attrvalue}
												</a>
											</c:forEach>
										</c:if>
									</c:forEach>
								</dd>
								<div class="clear"></div>
							</dl>
						</c:if>

						<c:if test="${p.attrid!=51 && p.attrid!=52}">
							<dl class="choose ">
								<dt>${p.attrname}：</dt>
								<dd>
									<c:forEach items="${p.skuList}" var="ps">
										<a href="${ctx}/product/detail.do?id=${ps.pid}"
											class="itme <c:if test="${ps.pid==productId}">  hot</c:if> ">${ps.attrvalue}
										</a>
									</c:forEach>
								</dd>
								<div class="clear"></div>
							</dl>
						</c:if>
					</c:forEach>
					<div>
						<dl style="position: relative;">
							<dt style="float: none">配送方式：</dt>
							<div role="hs-pikc-goods" class="delivery">
								<div panel-id="2" class="pick-panel active clearfix">
									<span class="active" pick-way="2">工厂自提</span> <span class="" pick-way="1">门店自提</span> <span class="" pick-way="3">配送到家</span>
								</div>
							</div>
							<dl id="psdj-float-panel"
								class="area-float-panel float-panel hs-Ptable">
								<div class="panel-close-btn">
									<span class="glyphicon glyphicon-remove"></span>
								</div>
								<table cellpadding="0" cellspacing="1" width="100%" border="0"
									class="Ptable">
									<tbody>
										<tr>
											<td>运费</td>
											<!-- 与区域关联的运费 -->
											<c:forEach items="${productDetail.transPriceList}" var="p"
												varStatus="status">
												<td>${p.info }￥${p.price }&nbsp;元/吨</td>
											</c:forEach>
											<c:if test="${empty productDetail.transPriceList}">
												<td>该区域尚未设置运费</td>
											</c:if>

										</tr>
										<tr>
											<td>运费起步价</td>
											<td>${carry }&nbsp;元/吨</td>
										</tr>
										<tr>
											<td>装卸费</td>
											<c:forEach items="${productDetail.loadPriceList}" var="p">
												<td>${p.info }￥${p.price }&nbsp;元/吨</td>
											</c:forEach>
											<c:if test="${empty productDetail.loadPriceList}">
												<td>该区域尚未设置装卸费</td>
											</c:if>
										</tr>
										<tr>
											<td colspan="2">提示：在道路运输情况允许下可配送到家。</td>
										</tr>
									</tbody>
								</table>
							</dl>
						</dl>

					</div>

					<div id="delivery-way">
						<div class="show" pen="1"></div>
						<div pen="2"></div>
					</div>
					<div class="clear"></div>
				</div>

				<div id="buy">
					<dl class="buyNB">
						<dt>购买数量：</dt>
						<dd>
							<a href="javascript:void(0)" class="down"
								onclick="cutProductCount()">-</a> <input type="text"
								id="buyCount" name="buyCount" value="1" /> <a
								href="javascript:void(0)" class="up" onclick="addProuctCount()">+</a>
							&nbsp;&nbsp;包
						</dd>
						<div class="clear">
							<dt>总价：</dt>
							<dd>
								<dd id="totalprice" class="price">
									<span class='show-cal-gs'> </span>
								</dd>

							</dd>
						</div>
						<div class="clear">
							<dt>提示：</dt>
							<dd>
								<dd id="hint">
									<span class='show-cal-gs'> </span>
								</dd>
							</dd>
						</div>
					</dl>

					<div id="buyBt">
						<a href="javascript:void(0)"
							onclick="addProductToCart('${productId}', document.getElementById('buyCount').value)"></a>
						<b class="grayBT"
							onclick="addProductToBuy('${productId}',document.getElementById('buyCount').value)">立即购买</b>
						<b class="grayBT" onclick="addToFavorite('${productId}')">加收藏</b>
						<div class="clear"></div>
					</div>
				</div>
			</div>

		</div>
		<div class="clear"></div>
	</div>
	<br />
	<div style="display:none;">
		<c:forEach items="${extpriceList}" var="extitem" varStatus="status">
			<c:if test="${extitem.type==1 }">
				<div id="extp-type-1">${extitem.value}</div>
			</c:if>
			<c:if test="${extitem.type==2 }">
				<div id="extp-type-2">${extitem.value}</div>
			</c:if>
			<c:if test="${extitem.type==3 }">
				<div id="extp-type-3">${extitem.value}</div>
			</c:if>
		</c:forEach>
	</div>
	<div id="ConInfo" class="box1210">
		<div id="infoL">
			<c:import url="${ctx }/tool/leftMenu.do"></c:import>
			<%@ include file="../../../includes/home/recommended.jsp"%>
		</div>
		<div id="infoR">
			<div class="infoRBox" style="border-bottom: 0;">
				<h2 class="infoRT infoBT2">
					<ul>
						<li class="hot" onmouseover="selectProductInfo(1, this)">商品介绍</li>
						<li onmouseover="selectProductInfo(2, this)">规格参数</li>
						<li onmouseover="selectProductInfo(3, this)">售后保障</li>
						<li onmouseover="selectProductInfo(4, this)" id="hsshopInfo">门店详情</li>
						<li onmouseover="selectProductInfo(5, this)" id="hsfactoryInfo"
							style="display: none;">工厂详情</li>
						<div class="clear"></div>
					</ul>
				</h2>
				<div class="clear"></div>
			</div>
			<!-- 商品介绍 -->
			<div class="infoRCon" id="productInfo1">${productDetail.entity.description }</div>
			<!-- 规格参数 -->
			<div class="infoRCon hide" id="productInfo2">
				<table cellpadding="0" cellspacing="1" width="100%" border="0"
					class="Ptable">
					<tbody>
						<tr>
							<th colspan="2" class="tdTitle">主体属性</th>
						</tr>
						<tr></tr>
						<c:forEach items="${productDetail.attrs}" var="p">
							<tr>
								<td class="tdTitle">${p.attrname }</td>
								<td>${p.attrvalue }</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
			<!-- 售后保障 -->
			<div class="infoRCon hide" id="productInfo3">

				<table cellpadding="0" cellspacing="1" width="100%" border="0"
					class="Ptable">
					<tbody>
						<tr>
							<th class="tdTitle">服务咨询</th>
						</tr>
						<tr></tr>
						<tr>
							<td>${webinfor.afterSale}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- 门店详情 -->
			<div class="infoRCon hide" id="productInfo4">
				<div style="display: none" id="has-store"
					hasit="${productDetail.address_store.id }"></div>
				${productDetail.address_store.content}
				<table cellpadding="0" cellspacing="1" width="100%" border="0"
					class="Ptable">
					<tbody>
						<tr>
							<td class="tdTitle">门店名称：</td>
							<td>${productDetail.address_store.name}</td>
						</tr>
						<tr>
							<td class="tdTitle">门店地址：</td>
							<td>${productDetail.address_store.address}</td>
						</tr>
						<tr>
							<td class="tdTitle">联系电话：</td>
							<td>${productDetail.address_store.tel}</td>
						</tr>
						<tr>
							<td class="tdTitle">联系人：</td>
							<td>${productDetail.address_store.contacts}</td>
						</tr>
						<tr>
							<td class="tdTitle">营业时间：</td>
							<td>${productDetail.address_store.worktime}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- 工厂详情 -->
			<div class="infoRCon hide" id="productInfo5">
				<div style="display: none" id="has-factory"
					hasit="${productDetail.address_factory.id }"></div>
				${productDetail.address_factory.content}
				<table cellpadding="0" cellspacing="1" width="100%" border="0"
					class="Ptable">
					<tbody>
						<tr>
							<td class="tdTitle">工厂名称：</td>
							<td>${productDetail.address_factory.name}</td>
						</tr>
						<tr>
							<td class="tdTitle">工厂地址：</td>
							<td>${productDetail.address_factory.address}</td>
						</tr>
						<tr>
							<td class="tdTitle">联系电话：</td>
							<td>${productDetail.address_factory.tel}</td>
						</tr>
						<tr>
							<td class="tdTitle">联系人：</td>
							<td>${productDetail.address_factory.contacts}</td>
						</tr>
						<tr>
							<td class="tdTitle">营业时间：</td>
							<td>${productDetail.address_factory.worktime}</td>
						</tr>
					</tbody>
				</table>
			</div>

			<!-- 评价 -->
			<iframe width="778" align="center" height="240" id="win" name="win"
				onload="Javascript:SetWinHeight(this)" frameborder="0"
				scrolling="no"
				src="${ctx }/comment/productCommentDetail.do?id=${productId}"></iframe>
			<script type="text/javascript">
				function change(obj) {
					var win = obj;
					if (document.getElementById) {
						if (win && !window.opera) {
							if (win.contentDocument
									&& win.contentDocument.body.offsetHeight)
								win.height = win.contentDocument.body.offsetHeight + 30;
							else if (win.Document
									&& win.Document.body.scrollHeight)
								win.height = win.Document.body.scrollHeight - 30;
						}
					}
				}

				function SetWinHeight(obj) {
					$("#win").contents().delegate(".height li", "mouseover",
							function() {
								change(obj)
							});
					setTimeout(function() {
						change(obj)
					}, 150)
				}
			</script>
		</div>
		<div class="clear"></div>
		<script type="text/javascript">
			function selectProductInfo(id, obj) {
				var liList = obj.parentNode.getElementsByTagName("li");
				for (var i = 0; i < liList.length; i++) {
					liList[i].className = "";
				}
				obj.className = "hot";
				//$("#productInfo1").style.display = "none";
				document.getElementById("productInfo1").style.display = "none";
				document.getElementById("productInfo2").style.display = "none";
				document.getElementById("productInfo3").style.display = "none";
				document.getElementById("productInfo4").style.display = "none";
				document.getElementById("productInfo5").style.display = "none";
				document.getElementById("productInfo" + id).style.display = "block";
			}

			function selectCommentInfo(id, obj) {
				var liList = obj.parentNode.getElementsByTagName("li");
				for (var i = 0; i < liList.length; i++) {
					liList[i].className = "";
				}
				obj.className = "hot";
				//$("#productInfo1").style.display = "none";
				document.getElementById("commontInfo1").style.display = "none";
				document.getElementById("commontInfo2").style.display = "none";
				document.getElementById("commontInfo3").style.display = "none";
				document.getElementById("commontInfo4").style.display = "none";
				document.getElementById("commontInfo" + id).style.display = "block";
			}
			var oriprice=null;
			$(function() {
				var isPriceChanged = "${isPriceChanged}";

				//商品重量
				var weight = $("#productWeight").attr("weight");
				//商品计量单位
				var unitName = "${productDetail.unit.unitname}";
				unitName = unitName.replace(/(^\s*)|(\s*$)/g, "");
				var QuantityUnit = "${productDetail.QuantityUnit.unitname}";
				QuantityUnit = QuantityUnit.replace(/(^\s*)|(\s*$)/g, "");

				var count;
				var totalprice;

				var price = parseFloat($("#price0").attr("price"));

				//默认价格
				if (isNaN(price)) {
					price = parseFloat($("#priceDefault").attr("price"));
					count = 1;
					if (QuantityUnit == "包")
						count = 1;
					else
						count = parseFloat(weight);
					totalprice = price * count;
				}
				//区域价格，以吨计算
				else {
					//商品数量， 转化为吨					
					if (unitName == "千克")
						count = parseFloat(weight / 1000);
					else if (unitName == "克")
						count = parseFloat(weight / 1000000);
					else
						count = parseFloat(weight);
					totalprice = price * count;
				}
				$("#totalprice").html(
						"<span class='show-cal-gs'>" + count + QuantityUnit
								+ "X" + price + " 元/" + QuantityUnit + "=￥"
								+ totalprice.toFixed(2) + "元</span>");

				//提示信息
				var unitCount = 1 / weight;
				$("#hint").html(
						"<span class='show-cal-gs'> 1吨=" + unitCount.toFixed(2)
								+ "包，1包=" + weight + " 吨</span>");

				$("#buyCount").bind(
						"change",
						function(e) {
							var count = parseInt($(e.target).val(), 10);
							if (isNaN(count))
								return;
							var priceStr = null;

							price = parseFloat($("#price0").attr("price"));
							//默认价格，不以吨计算
							if (isNaN(price)) {
								price = parseFloat($("#priceDefault").attr(
										"price"));
								if (QuantityUnit == "包")
									count = count * 1;
								else
									count = count * parseFloat(weight);
								totalprice = price * count;
							}
							//区域价格
							else {
								//商品数量， 转化为吨
								if (unitName == "千克")
									count *= parseFloat(weight / 1000);
								else if (unitName == "克")
									count *= parseFloat(weight / 1000000);
								else
									count *= parseFloat(weight);
								priceStr = $("#price0").attr("price");
								price = parseFloat(priceStr, 10);
								totalprice = price * count;

							}
							$("#totalprice").html(
									"<span class='show-cal-gs'>"
											+ count.toFixed(2) + QuantityUnit
											+ " X" + price + " 元/"
											+ QuantityUnit + "=￥"
											+ totalprice.toFixed(2)
											+ "元</span>");

						});

				$("#buyCount").bind(
						"keyup",
						function(e) {
							var src = e.target;
							var countStr = $(src).val();
							var count = parseInt(countStr, 10);
							if (isNaN(count))
								return;

							var priceStr = null;
							var price = 0;

							price = parseFloat($("#price0").attr("price"));
							//默认价格，不以吨计算
							if (isNaN(price)) {
								price = parseFloat($("#priceDefault").attr(
										"price"));
								if (QuantityUnit == "包")
									count = count * 1;
								else
									count = count * parseFloat(weight);
								totalprice = price * count;
							}
							//区域价格，以吨计算
							else {
								//商品数量， 转化为吨
								unitName = "${productDetail.unit.unitname}";
								if (unitName == "千克")
									count *= parseFloat(weight / 1000);
								else if (unitName == "克")
									count *= parseFloat(weight / 1000000);
								else
									count *= parseFloat(weight);
								priceStr = $("#price0").attr("price");
								price = parseFloat(priceStr, 10);
								totalprice = price * count;

							}
							$("#totalprice").html(
									"<span class='show-cal-gs'>"
											+ count.toFixed(2) + QuantityUnit
											+ " X" + price + " 元/"
											+ QuantityUnit + "=￥"
											+ totalprice.toFixed(2)
											+ "元</span>");

						});
			});
			
			 $("[pick-way=\"1\"]").click(function(e){
			        $("#hsshopInfo").removeAttr("style")
			        $("#hsfactoryInfo").attr("style","display: none");
			        //onmouseover
			       // $("#hsshopInfo").trigger("mouseover");			        
			        var extp=$("#extp-type-1").html();
			        
			        if(extp){
			        	calByNewPrice(extp);
			        }
			        			        
			    });
			    $("[pick-way=\"2\"]").click(function(e){
			        $("#hsfactoryInfo").removeAttr("style")
			        $("#hsshopInfo").attr("style","display: none");
			       // $("#hsfactoryInfo").trigger("mouseover");
			        var extp=$("#extp-type-2").html();
			        if(extp){
			        	calByNewPrice(extp);
			        }
			        
			    });
			    $("[pick-way=\"3\"]").click(function(e){
			        $("#hsfactoryInfo").attr("style","display: none");
			        $("#hsshopInfo").attr("style","display: none");
			       // $(".infoBT2 ul li:first-child").trigger("mouseover");
			        var extp=$("#extp-type-3").html();
			        if(extp){
			        	calByNewPrice(extp);
			        }
			        
			    });
			    //默认显示工厂
			    $("[pick-way=\"2\"]").trigger("click");
			    
			    function calByNewPrice(extp){
			    	var extpnum=parseFloat(extp);
		        	if(!isNaN(extpnum)){
		        		var price = parseFloat($("#price0").attr("oriprice"));
		        		if(isNaN(price)){
		        			price = parseFloat($("#priceDefault").attr(
							"oriprice"));
		        			var newP=price+extpnum;
		        			$("#priceDefault").attr("price",newP);
		        			$("#priceDefault [pricespan]").html(newP);
		        		}else{
		        			var newP=price+extpnum;
		        			$("#price0").attr("price",newP);
		        			$("#price0 [pricespan]").html(newP);
		        		}
		        		$("#buyCount").trigger("change");
		        	}
			    }
		</script>
	</div>

	<%@ include file="../../../includes/home/footer.jsp"%>
</body>
</html>