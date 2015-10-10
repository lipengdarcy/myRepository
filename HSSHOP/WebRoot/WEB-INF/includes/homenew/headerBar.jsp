<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/includes/commons/taglibs.jsp"%>
<!--bof top-line-->
<div class="top-line">
	<div class="wrap clearfix">
		<p class="left">
			您好，欢迎来到红狮水泥商城 ！
			<c:if test="${empty account_session_id}">
				<a href="javascript:void(1);" onclick="toLogin();" class="red">[
					请登录 ]</a>
				<a href="${ctx }/account/register.do">[ 免费注册 ]</a>
			</c:if>
		</p>
		<ul class="right clearfix">
			<c:if test="${!empty account_session_id}">
				<li>${account_login_name }&nbsp;&nbsp;<a
					href="javascript:void(1);" onclick="logout();">[退出]</a>
				</li>
				<%-- <li><a href="${ctx }/ucenter/userinfo.do">个人中心 </a></li> --%>				
				<%-- <li><a href="${ctx }/ucenter/orderlist.do">我的订单 </a></li> --%>
				<c:if test="${account_session_isstoreuser==1}">
					<li><a href="${ctx }/ucenter/userinfo.do?&navid=10">用户中心 </a></li>
					<li><a href="${ctx }/dealproduct/salerIndexProList.do"
						class="red">[ 经销商门户 ]</a></li>
				</c:if>
			</c:if>
			<li class="top-cart"><a href="${ctx }/cart.do">购物车 </a> <strong class="cartnum-ele">
					<c:if test="${empty sessionScope.cartCount }">0</c:if> <c:if
						test="${not empty sessionScope.cartCount }">${sessionScope.cartCount }</c:if>
			</strong></li>
			<li class="top-phone"><a href="###">手机端下载</a></li>
			<li><a href="${ctx }/help/">帮助中心 </a></li>
		</ul>
	</div>
</div>
<!--eof top-line-->
<script type="text/javascript">
	var logout = function() {
		var $div = $("#headerLink");
		$div.empty();
		$.ajax({
			url : url + 'account.do?method=doLogout',
			data : {},
			type : 'post',
			dataType : 'json',
			success : function(data) {
				if (data.state == 'success') {
					var returnUrl = encodeURIComponent(location.href);
					location.href = url + "account/login.do?return_url="
							+ returnUrl;
				}
			},
			error : function() {
			}
		});

	}, toLogin = function() {
		var returnUrl = encodeURIComponent(location.href);
		location.href = url + "account/login.do?return_url=" + returnUrl;
	};
	$(document).ready(function(e){
		$.ajax({
			url : url + '/cart/countCartProQuantity.do',
			type : 'get',
			dataType : 'json',
			success : function(data) {
				if (data.state == 'success') {
					$(".cartnum-ele").html(data.quantity);
				}
			},
			error : function() {
			}
		});
	});
</script>
