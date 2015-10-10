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
    <meta name="keywords" content="红狮水泥商城" />
    <meta name="description" content="红狮水泥商城" />
    <%@ include file="/WEB-INF/includes/homenew/header.jsp"%>
    <link rel="stylesheet" type="text/css" href="${ctx }/themes/grey/css/user.css"/>
    <title>成功添加到购物车-红狮水泥商城</title>
</head>
<body>
	<%@ include file="../../../includes/homenew/headerBar.jsp"%>
	<%@ include file="../../../includes/homenew/headerTop.jsp"%>

	<c:import url="${ctx }/tool/newTopMenu.do"></c:import>
	<!--eof nav-->
	<div class="crumbs wrap"><strong>| <a href="${ctx }/dealproduct/salerIndexProList.do">商城首页</a> </strong>&gt; <span>订单结算结果</span></div>
	<!--bof order_settlement-->
	<div class="order-settlement-succeed wrap">
	  <h1><i></i>您的订单提交成功！</h1>
	  <div class="succeed-next">
	  	  <input type="hidden" name="orderid" value="${orderid }" id="orderid">
		  <input type="hidden" name="ordernum" value="${ordernum }" id="ordernum">
		  <a href="${ctx }/dealproduct/salerIndexProList.do">继续购物>></a>
		  <a href="${ctx }/business/queryOrder.do?storeType=1&roleid=2">查看我的订单>></a>
	  </div>
	</div>
	<%@ include file="../../../includes/homenew/footer.jsp"%>
</body>
</html>