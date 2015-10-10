<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<link href="${ctx }/themes/default/css/content.css" rel="stylesheet"
	type="text/css" />

</head>
<body>

	<%@ include file="../../../includes/home/headerBar.jsp"%>

	<%@ include file="../../../includes/home/headerTop.jsp"%>

	<c:import url="${ctx }/tool/topMenu.do"></c:import>

	<div class="buyTip box">
		<div class="buyTipInner">
			<c:choose>
				<c:when test="${state == 'success'}">
					<img src="${ctx }/themes/default/images/ok.gif" width="43"
						height="48" /> 
					${msg }
				<a href="${ctx }/product/detail.do?id=${pid}" class="grayBT">继续购物</a>
					<a href="cart.do?pmId=83" class="redBT">去购物车结算&gt;</a>
				</c:when>
				<c:otherwise>
					${msg }
				<a href="${ctx }/product/detail.do?id=${pid}" class="grayBT">重新购买</a>
				</c:otherwise>
			</c:choose>


		</div>
	</div>

	<div id="footer" class="bigBox">
		<div class="bigBox footerB">
			<div id="footerB">
				<div class="links"></div>
				<div>
					浙ICP证000000号&nbsp;&nbsp;|&nbsp;&nbsp;<a href="#" target="_blank">互联网信息服务资格证编号(浙)-经营性-2015</a>&nbsp;&nbsp;<br />红狮水泥商城
					版权所有 @2015, runlion.com Inc.
				</div>
				<div>
					<a href="#"><img width="108" height="40"
						src="${ctx }/themes/default/images/power_jy.gif" /></a> <a href="#"><img
						width="108" height="40"
						src="${ctx }/themes/default/images/power_kx.gif" /></a> <a href="#"><img
						width="108" height="40"
						src="${ctx }/themes/default/images/power_wj.png" /></a> <a href="#"><img
						width="112" height="40"
						src="${ctx }/themes/default/images/power_cx.png" /></a>
				</div>
			</div>
		</div>
	</div>


</body>
</html>