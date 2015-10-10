<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../../includes/commons/taglibs.jsp"%>

<div class="user-menu-list">
	<div class="user-tab">个人中心</div>
	<ul id="memberNav">

		<li navid=10><a href="${ctx }/ucenter/userinfo.do">账户信息</a></li>
		<li><a href="${ctx }/ucenter/safeinfo.do">账户安全</a></li>
		<li><i></i><a href="${ctx }/ucenter/orderlist.do">我的订单</a>
			<ul>
				<li><a href="${ctx }/order/queryOrder.do">全部订单</a></li>
				<li><a href="${ctx }/order/queryOrder.do?orderstate=4">待付款</a></li>
				<li><a href="${ctx }/order/queryOrder.do?orderstate=8">待收货</a></li>
				<li><a href="${ctx }/order/queryOrder.do?orderstate=10">待评价</a></li>
			</ul></li>

		<li><a href="${ctx }/ucenter/shipaddresslist.do">收货地址</a></li>
		<li><a href="${ctx }/ucenter/favoriteproductlist.do">收藏商品</a>
		</li>
		<li><a href="${ctx }/ucenter/commentlist.do">商品评价</a>
		</li>

		<c:if test="${isFactoryUser!=1}">
			<li><a href="${ctx }/business/applyFactoryUserPage.do"><span
					style="color: red">申请为工厂用户</span></a></li>
		</c:if>

		<c:if test="${isStoreUser!=1}">
			<li><a href="${ctx }/business/applyStoreUserPage.do"><span
					style="color: red">申请为经销商用户</span></a></li>
		</c:if>

	</ul>
</div>

