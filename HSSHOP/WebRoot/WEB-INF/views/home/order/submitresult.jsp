<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>成功添加到购物车-红狮水泥商城</title>
    <meta name="keywords" content="红狮水泥商城" />
    <meta name="description" content="红狮水泥商城" />
    <%@ include file="../../../includes/home/header.jsp"%>
    
    <link href="${ctx }/themes/default/css/content.css" rel="stylesheet" type="text/css" />
    <style>
    .buyTipInner {
	  width: 900px;
	}
	.buyTip {
	  font-size: 20px;
	}
    </style>
</head>
<body>

	<%@ include file="../../../includes/home/headerBar.jsp"%>

	<%@ include file="../../../includes/home/headerTop.jsp"%>

	<c:import url="${ctx }/tool/topMenu.do"></c:import>
	
	<div class="buyTip box">
		<div class="buyTipInner">
			<input type="hidden" name="orderid" value="${orderid }" id="orderid">
			<input type="hidden" name="ordernum" value="${ordernum }" id="ordernum">
			<!--  <img src="${ctx }/themes/default/images/ok.gif" width="43" height="48"> -->
  			 <div>您的订单提交成功，正在审核中，请记住您的订单号：${ordernum }</div> 
  			 <div>您选定的配送方式为：${pickName },你选定的支付方式为：
  			  <c:if test="${paymodel==1 }">
  			  	到店支付
  			  </c:if>
  			  <c:if test="${paymodel==2 }">
  			  	到厂支付
  			  </c:if>
  			  <c:if test="${paymodel==3 }">
  			  	货到付款
  			  </c:if>
  			  <c:if test="${paymodel==4 }">
  			 	在线支付
  			  </c:if>
  			  <c:if test="${paymodel==5 }">
  			  	垫资
  			  </c:if>，
  			  您的应付金额为:¥${surmny }
			</div> 
			<div>您可以</div>
			<div>
				<a href="${ctx }/index.do" >继续购物</a>
				<a href="${ctx }/ucenter/orderlist.do" >查看我的订单</a>
				<c:if test="${paymodel==4 }">  			  		
  			  		<a href="${ctx }/unionpay/submitOrder.do?orderId=${ordernum }" style="color:white;"
  			  		 class="btn btn-success go-pay-btn">继续支付</a>
  			    </c:if>
			</div>
  			  
		</div>
	</div>
	
	<div id="footer" class="bigBox">
	    <div class="bigBox footerB">
	        <div id="footerB">
	            <div class="links">
	            </div>
	            <div>浙ICP证000000号&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" target="_blank">互联网信息服务资格证编号(浙)-经营性-2015</a>&nbsp;&nbsp;<br />红狮水泥商城 版权所有 @2015, runlion.com Inc.</div>
	            <div><a href="#"><img width="108" height="40" src="${ctx }/themes/default/images/power_jy.gif"/></a> <a href="#"><img width="108" height="40" src="${ctx }/themes/default/images/power_kx.gif"/></a> <a href="#"><img width="108" height="40" src="${ctx }/themes/default/images/power_wj.png"/></a> <a href="#"><img width="112" height="40" src="${ctx }/themes/default/images/power_cx.png"/></a></div>
	        </div>
	    </div>
	</div>
	

</body>
</html>