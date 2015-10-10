<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />	
	<meta name="keywords" content="${productDetail.entity.name }-${webinfor.SEOKeyword}" />
	<meta name="description" content="${productDetail.entity.name }-${webinfor.SEODescription}" />
	<%@ include file="../../../includes/homenew/header.jsp"%>
	<link rel="stylesheet" type="text/css" href="${ctx }/themes/grey/css/product.css"/>	
	<script src="${ctx }/scripts/product.js" type="text/javascript"></script>
	<link href="${ctx }/scripts/magiczoom/magiczoom.css" rel="stylesheet" type="text/css" />
	<script src="${ctx }/scripts/magiczoom/magiczoom.js" type="text/javascript"></script>
	<script type="text/javascript" src="${ctx }/scripts/jquery.toastmessage.js"></script>
	<link href="${ctx }/themes/default/css/jquery.toastmessage.css" rel="stylesheet" type="text/css" />
	<title>${productDetail.entity.name }-${webinfor.shopName}</title>
</head>
<body>
	<%@ include file="../../../includes/homenew/headerBar.jsp"%>
	<%@ include file="../../../includes/homenew/headerTop.jsp"%>

	<c:import url="${ctx }/tool/newTopMenu.do"></c:import>
	<div class="crumbs wrap">
		<strong>| <a href="${ctx }/dealproduct/salerIndexProList.do">经销商首页</a> </strong>&gt; <span>${productDetail.entity.name }</span>
	</div>
	
	<div class="product wrap clearfix"> 
		<div class="productshow clearfix">
			 <div class="left productshow-img">
			      <div class="img-big"> 
			      		<a href="${ctx}/upload/product/source/${productDetail.productPics[0].showimg}" class="jqzoom" rel='gal1'  title="商品详细" > 
			      			<img src="${ctx}/upload/product/source/${productDetail.productPics[0].showimg}" /> 
			      		</a> 
			      </div>
			      <ul class="img-ul clearfix" id="thumblist">
			      	<c:forEach items="${productDetail.productPics}" var="p" varStatus="st">
			      		 <li >
			      		 	<a <c:if test="${st.index==0 }"> class="zoomThumbActive" </c:if>
			      		 	    rel="{gallery: 'gal1', smallimage: '${ctx}/upload/product/source/${p.showimg}',largeimage: '${ctx}/upload/product/show/source/${p.showimg}'}">
			      		 		<img src="${ctx}/upload/product/source/${p.showimg}" />
			      		 	</a>
			      		 </li>
			      	</c:forEach>			        
			      </ul>
			      <div class="img-btm"><span>商品编号：${productDetail.entity.ncpronum }</span></div>
			  </div>
		    <!--right-->
		    <div class="left productshow-info">
		      <h1>${productDetail.entity.name }</h1>
		      <div price="${productDetail.entity.marketprice }" id="priceDefault" class="price">
		      	<span>价格：</span>
		      	￥<fmt:formatNumber type="number" value="${productDetail.entity.marketprice }" pattern="0.00" maxFractionDigits="2"/> 
		      	元/吨 
		      </div>
		      <ul class="param">
		      <c:forEach items="${productDetail.skuList}" var="p">
					<!-- 生产厂家 要根据选择的品牌来展示-->
					<c:if test="${p.attrid==52}">
						<!-- 品牌-->
						<li>
							<strong>${p.attrname}：</strong>
							<ul>
								<c:forEach items="${p.skuList}" var="ps">
									<li class=" <c:if test="${ps.pid==productId}">active</c:if> ">
										${ps.attrvalue}
									</li>
								</c:forEach>
					         </ul>
						</li>
						<!--生产厂家 -->
						<li>
							<strong>生产厂家：</strong>
							<ul>
								<c:forEach items="${p.brand_factory}" var="bf">
									<c:if test="${bf.key==brandId}">
										<c:forEach items="${bf.value}" var="ps">
											<li class="<c:if test="${ps.pid==productId}">active</c:if> ">
												${ps.attrvalue}
											</li>
										</c:forEach>
									</c:if>
								</c:forEach>
							</ul>
						</li>
					</c:if>
	
					<c:if test="${p.attrid!=51 && p.attrid!=52}">
						<li class="choose ">
							<strong>${p.attrname}：</strong>
							<ul>
								<c:forEach items="${p.skuList}" var="ps">
									<li class="<c:if test="${ps.pid==productId}">active</c:if> ">
										${ps.attrvalue}
									</li>
								</c:forEach>
							</ul>
						</li>
					</c:if>
				</c:forEach>
			  </ul>
			  <div role="hs-pikc-goods" class="delivery" style="display:none;">
				  <div panel-id="1" class="pick-panel active clearfix">
					  <span class="" pick-way="2">工厂自提</span>
				  </div>
			  </div>
			  <div class="num" id="buy">
			  		<strong>购买数量：</strong>					
						<div class="num-put">
							<em class="del" onclick="cutProductCount()">-</em>
				          	<input type="text" type="text" id="buyCount" name="buyCount" value="1"/>
				          	<em class="add" onclick="addProuctCount()">+</em>
				          </div>
				        	<span>吨</span>
				</div>
		      <div id="buyBt" class="btn">
		      	<a class="buy" onclick="dealAddProductToBuy('${productId}','#buyCount')">立即下单</a>
		      </div>
		    </div>
		</div>
		<div class="product-intro left">
		    <ul class="detail-tab clearfix">
		      	<li class="active" onmouseover="selectProductInfo(1, this)"><a>商品介绍</a></li>
				<li onmouseover="selectProductInfo(2, this)"><a>规格参数</a></li>
				<li onmouseover="selectProductInfo(3, this)"><a>售后保障</a></li>
		    </ul>	    
	    	<div class="detial-info" id="productInfo1">${productDetail.entity.description }</div>
		    <div class="detial-info hide" id="productInfo2">
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
			<div class="detial-info hide" id="productInfo3">	
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
  		</div>
  		<div class="product-recommend">
		    <h2>推荐商品</h2>
		    <ul class="clearfix">
			    <c:forEach items="${productList}" var="p">
			        <li>
			          <div class="imghloder"> 
			          	 <a href="${ctx}/dealproduct/detail.do?id=${p.pid}">
			          		<img src="${ctx}/upload/product/source/${p.imgName}" onerror="nofind();" width="262" height="238" alt=""/>
			          	 </a>
			          	 <div onclick="dealAddProductToBuy('${p.pid}','1')" class="cart-line">
			          	 	<a class="list-cart list-cart-one">立即购买</a>
			          	 </div>				          	
					  </div>
			          <div class="info">
			          	<em>${p.placename}</em>
			            <div class="price"><strong>￥${p.ncprice}</strong></div>
			            <p><a href="${ctx}/dealproduct/detail.do?id=${p.pid}">${p.name} </a></p>
			          </div>
			        </li>
		       </c:forEach>		      
		    </ul>
		</div>
	</div>

	<script type="text/javascript">
		$(function() {
			$("[pick-way=\"2\"]").trigger("click");
		});
		
		function selectProductInfo(id, obj) {
			var liList = obj.parentNode.getElementsByTagName("li");
			for (var i = 0; i < liList.length; i++) {
				liList[i].className = "";
			}
			obj.className = "active";
			//$("#productInfo1").style.display = "none";
			document.getElementById("productInfo1").style.display = "none";
			document.getElementById("productInfo2").style.display = "none";
			document.getElementById("productInfo3").style.display = "none";
			document.getElementById("productInfo" + id).style.display = "block";
		}
	</script>

	<%@ include file="../../../includes/homenew/footer.jsp"%>
	<script type="text/javascript" src="${ctx }/scripts/grey/jquery.jqzoom-core.js"></script>
	<script>
/*图片放大插件配置参数*/	
$(function(){
	$('.jqzoom').jqzoom({
            zoomType: 'standard',
            lens:true,
            preloadImages: false,
            alwaysOn:false,
			 zoomWidth: 300,
            zoomHeight: 300
        });
	
	$(".product-packing>li").click(function(){
		$(this).addClass("active");
		$(this).siblings().removeClass("active");		
		})
	})	
</script>
</body>
</html>