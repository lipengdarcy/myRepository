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

<title>在线交款成功页面-红狮水泥商城</title>
</head>
<body>

	<%@ include file="../../../../includes/homenew/headerBar.jsp"%>
	<%@ include file="../../../../includes/homenew/headerTop.jsp"%>

	<!--bof main-->
	<div class="user-main">
		<div class="wrap clearfix">
			<div class="crumbs">
				<strong>| <a href="seller_data.html">个人中心首页</a>
				</strong>&gt; <a href="${ctx }/ucenter/userinfo.do?&navid=10">个人中心</a> &gt; <span>在线收款</span>
			</div>
			<!--bof menu-->
			<div class="user-menu left">
				<%@ include file="../centermenu.jsp"%>
			</div>
			<!--eof menu-->
			<!--bof order_settlement-->
			<div class="order-settlement-succeed wrap">
				<h1>
					<i></i>在线交款成功！
				</h1>
				<div class="succeed-next">
					<a onclick="gotoPayPage()" href="#">继续充值>></a><a href="###">返回>></a>
				</div>
			</div>
			<!--eof order_settlement-->
		</div>
	</div>

	<!--eof main-->






	<!--bof Service-->
	<c:import url="${ctx }/tool/newService.do"></c:import>
	<!--eof Service-->
	<%@ include file="../../../../includes/homenew/footer.jsp"%>

	<script type="text/javascript">
	function GetQueryString(name)
	{
	     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	     var r = window.location.search.substr(1).match(reg);
	     if(r!=null)return  unescape(r[2]); return null;
	}
	

		$(function() {
			//银联支付成功后，与nc交互 
			var osn = GetQueryString("osn");
			//弹窗提示配置
			var hsArtDialog=dialog({
			  	title: '提示',
			  	id:"hs-dialog",
			  	fixed:true,
			  	width:300,
			  	height:50
			});
			$.ajax({
				url : "${ctx }/business/payOnlineNC.do?osn=" + osn,
				type : "post",
				async : false,
				success : function(data, status) {
					//后台执行成功后的回调函数
					if (data.state == "success") {
						hsArtDialog.content(data.content).showModal();

					} else {
						hsArtDialog.content(data.content).showModal();
					}
				},
				error : function(xhr, errinfor, ex) {
					//后台发生异常后的回调函数
					hsArtDialog.content(errinfor).showModal();
				}
			});
		}); 
	</script>
</body>
</html>