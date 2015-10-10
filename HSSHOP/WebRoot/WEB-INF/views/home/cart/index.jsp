<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的购物车-红狮水泥商城</title>
<meta name="keywords" content="红狮水泥商城" />
<meta name="description" content="红狮水泥商城" />
<%@ include file="../../../includes/home/header.jsp"%>
<link href="${ctx }/themes/default/css/buy.css" rel="stylesheet"
	type="text/css" />
</head>

<body>

	<%@ include file="../../../includes/home/headerBar.jsp"%>

	<div id="buyTop" class="box">
		<a href="${ctx }/index.do"><img
			src="themes/default/images/logo.jpg" /></a>
		<div class="buyStep">
			<ul>
				<li class="hot"><s>1</s>1.我的购物车</li>
				<li><s>2</s>2.填写核对订单信息</li>
				<li><s>3</s>3.成功提交订单</li>
				<div class="clear"></div>
			</ul>
		</div>
	</div>

	<div class="box" id="buy">
		<script type="text/javascript">
			var tempValue = 0;
			function numberFocus(obj) {
				tempValue = obj.value;
				obj.value = "";
			}
			function numberBlur(obj, itemId, itemType) {
				//弹窗提示配置
				var hsArtDialog=dialog({
				  	title: '提示',
				  	id:"hs-dialog",
				  	fixed:true,
				  	width:300,
				  	height:50
				});
				var value = obj.value;
				if (value == "") {
					obj.value = tempValue;
				} else {
					if (!isInt(value)) {
						hsArtDialog.content("只能输入数字!").showModal();
						obj.value = tempValue;
					} else {
						if (itemType == 0) {
							changePruductCount(itemId, value);
						} else {
							changeSuitCount(itemId, value);
						}
					}

				}
			}
		</script>
		<h1 id="buyT">我的购物车</h1>
		<div id="cartBody">
			<!-- <ul id="noBuy">
	  <li>购物车内暂时没有商品，<a href="account/login?returnUrl=%2Fcart">登录</a>后，将显示您之前加入的商品</li>
	  <li><a href="index.do">去首页</a>挑选喜欢的商品</li>
	</ul>   -->

			${html }




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
						src="themes/default/images/power_jy.gif"></a> <a href="#"><img
						width="108" height="40" src="themes/default/images/power_kx.gif"></a>
					<a href="#"><img width="108" height="40"
						src="themes/default/images/power_wj.png"></a> <a href="#"><img
						width="112" height="40" src="themes/default/images/power_cx.png"></a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
