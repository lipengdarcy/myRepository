<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />	
	<meta name="keywords" content="红狮水泥商城" />
	<meta name="description" content="红狮水泥商城" />
	<%@ include file="../../includes/homenew/header.jsp"%>
	<link rel="stylesheet" type="text/css" href="${ctx }/themes/grey/css/index.css"/>
	<title>经销商可购买水泥列表-红狮水泥商城</title>
</head>
<body>
	<%@ include file="../../includes/homenew/headerBar.jsp"%>
	<%@ include file="../../includes/homenew/headerTop.jsp"%>	

	<c:import url="${ctx }/tool/newTopMenu.do"></c:import>
	<!--bof banner-->
	<c:import url="${ctx }/tool/newBanner.do"></c:import>
	<!--eof banner--> 
	<!--bof Service-->
	<c:import url="${ctx }/tool/newService.do"></c:import>
	<!--eof Service--> 	
	<!-- main -->
	<div class="main">
  		<div class="wrap clearfix">
    		<div class="recommend left">
		      	<div class="tab">
		        	<h1>推荐商品</h1>
		        	<div class="arrow"><a class="prev left" href="javascript:void(0);"></a><a class="next right" href="javascript:void(0);"></a></div>
		      	</div>
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
			            <div class="price"><strong>￥
			            <fmt:formatNumber type="number" value="${p.ncprice}" pattern="0.00" maxFractionDigits="2"/> 
			            </strong></div>
			            <p><a href="${ctx}/dealproduct/detail.do?id=${p.pid}">${p.name} </a></p>
			          </div>
			        </li>
			       </c:forEach>
		      </ul>
    		</div>
    		<div class="hot-news right">
      			<div class="news-tab">
         			<h2>红狮快报</h2>
          			<a class="arrow" href="${ctx }/news/list.do?newstypeid=1">更多</a>
          		</div>
	     		<ul>
		     	   <c:forEach var="news" items="${newsList1 }">
						<li><a href="${ctx }/news/detail.do?id=${news.newsid }">${news.title }</a></li>
					 </c:forEach>
	      		</ul>
    		</div>
  		</div>
	</div>
	<!-- main -->
	<c:import url="${ctx }/tool/newPartner.do"></c:import>
	<%@ include file="../../includes/homenew/footer.jsp"%>
</body>
</html>