<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="红狮水泥商城" />
<meta name="description" content="红狮水泥商城" />
<%@ include file="../../../../includes/homenew/header.jsp"%>
<script src="${ctx }/scripts/product.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="${ctx }/themes/grey/css/user.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/scripts/date/need/laydate.css" />
<script type="text/javascript" src="${ctx }/scripts/date/laydate.js"></script>

<title>在线交款方式选择-红狮水泥商城</title>
</head>
<body>

	<%@ include file="../../../../includes/homenew/headerBar.jsp"%>
	<%@ include file="../../../../includes/homenew/headerTop.jsp"%>

	<!--bof main-->
	<div class="user-main">
		<div class="wrap clearfix">
			<div class="crumbs">
				<strong>| <a href="seller_data.html">个人中心首页</a>
				</strong>&gt; <a href="###">经销商门户</a> &gt; <span>在线收款</span>
			</div>
			<!--bof menu-->
			<div class="user-menu left">
				<%@ include file="../centermenu.jsp"%>
			</div>
			<!--eof menu-->
			<!--bof content-->
			<div class="user-content right">
				<h1>选择支付方式</h1>
				<div class="online-pay">
					<form action="${ctx }/business/payOnline.do">
						<fieldset>
							<legend>支付方式：</legend>
							<input id="bandtype" type="radio" checked="checked" /> <img
								src="${ctx }/themes/grey/images/onlinepay_img01.jpg" width="160"
								height="39" alt="" />
								<input type="hidden" name="payorderid" value="${payorderid}"/>								
						</fieldset>
						<button type="submit" class="online-pay-btn">下一步</button>
					</form>
				</div>
				
			</div>
			<!--bof content-->
		</div>
	</div>

	<!--eof main-->






	<!--bof Service-->
	<c:import url="${ctx }/tool/newService.do"></c:import>
	<!--eof Service-->
	<%@ include file="../../../../includes/homenew/footer.jsp"%>

	<script type="text/javascript">
		//银联支付页面
		function gotoPayPage() {
			window.location.href = "${ctx }/business/payOnline.do";
		}
	</script>
</body>
</html>