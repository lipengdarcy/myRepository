<%@ include file="../../../includes/commons/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<script type="text/javascript">
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
	function apply(userType) {
		//弹窗提示配置
		var hsArtDialog=dialog({
		  	title: '提示',
		  	id:"hs-dialog",
		  	fixed:true,
		  	width:300,
		  	height:50
		});
		$.ajax({
			url : '${ctx }/business/applyStoreUser.do',
			data : {
				userType : userType
			},
			type : 'post',
			dataType : 'json',
			success : function(data) {	
				hsArtDialog.content(data.content).showModal();
			},
			error : function() {
				hsArtDialog.content("异常！请重新尝试或者联系管理员！").showModal();
			}
		});
	}
	
	
</script>
<!--01-->
<div class="user-menu-list">
	<div class="user-tab">个人中心</div>
	<ul id="memberNav">
		<li navid=19><i></i><a href="user_center.html?&navid=19">我的首页</a></li>
		<li navid=18><i></i><a href="my_cart.html?&navid=18">我的购物车</a></li>
		<li navid=12><i></i><a href="${ctx }/order/queryOrder.do?&navid=12">我的订单</a>
			<ul>
				<li navid=121><a href="${ctx }/order/queryOrder.do?&navid=121">全部订单</a></li>
				<li navid=122><a href="${ctx }/order/queryOrder.do?orderstate=4&navid=122">待付款</a></li>
				<li navid=123><a href="${ctx }/order/queryOrder.do?orderstate=8&navid=123">待收货</a></li>
				<li navid=124><a href="${ctx }/order/queryOrder.do?orderstate=10&navid=124">待评价</a></li>
			</ul>
		</li>
		<li navid=14><i></i><a href="${ctx }/ucenter/favoriteproductlist.do?&navid=14">商品收藏</a></li>
		<li navid=15><i></i><a href="${ctx }/ucenter/commentlist.do?&navid=15">商品评价</a></li>
		
        <li><i></i><a href="javascript:void(0);">个人设置</a>
          <ul>
            <li navid=10><a href="${ctx }/ucenter/userinfo.do?&navid=10">个人资料</a></li>
            <li navid=13><a href="${ctx }/ucenter/shipaddresslist.do?&navid=13">管理收货地址</a></li>
            <li navid=11><a href="${ctx }/ucenter/safeinfo.do?&navid=11">安全中心</a></li>
          </ul>
        </li>

		<c:if test="${account_session_isfacuser!=1}">
			<li>
				<a href="${ctx }/business/applyFactoryUserPage.do">
					<span style="color: red">申请为工厂用户</span>
				</a>
			</li>
		</c:if>

		<c:if test="${account_session_isstoreuser!=1}">
			<li>
				<a href="${ctx }/business/applyStoreUserPage.do">
					<span style="color: red">申请为经销商用户</span>
				</a>
			</li>
		</c:if>

	</ul>
</div>
<!--01-->
<!--02-->
<c:if test="${account_session_isstoreuser==1}">
	<div class="user-menu-list">
		<div class="user-tab">经销商门户</div>
		<ul>

			<li navid=20><i></i><a onclick="turnRed()" href="${ctx }/business/storeInfo.do?&navid=20">基础资料</a></li>
			<li navid=21><i></i><a
				href="${ctx }/dealproduct/salerProList.do?&navid=21">销售列表</a></li>
			<li navid=22><i></i><a href="${ctx }/salermny/toSalermnyList.do?&navid=22">资金管理</a>
				<ul>
					<li navid=23><a href="${ctx }/business/payOnlinePage.do?&navid=23">在线交款</a></li>
				</ul></li>
			<li navid=24><i></i><a
				href="${ctx }/business/queryOrder.do?storeType=1&roleid=2&navid=24">买入商品</a>
				<ul>
					<li navid=25><a
						href="${ctx }/business/queryOrder.do?storeType=1&roleid=2&navid=25">全部订单</a></li>
					<li navid=26><a
						href="${ctx }/business/queryOrder.do?storeType=1&roleid=2&orderstate=0&navid=26">待确认</a></li>
					<li navid=27><a
						href="${ctx }/business/queryOrder.do?storeType=1&roleid=2&orderstate=4&navid=27">待发货</a></li>
				</ul></li>
			<li navid=28><i></i><a
				href="${ctx }/business/queryOrder.do?storeType=1&roleid=1&navid=28">卖出商品</a>
				<ul>
					<li navid=29><a
						href="${ctx }/business/queryOrder.do?storeType=1&roleid=1&navid=29">全部订单</a></li>
					<li navid=291><a
						href="${ctx }/business/queryOrder.do?storeType=1&roleid=1&orderstate=1&navid=291">待收款</a></li>
					<li navid=292><a
						href="${ctx }/business/queryOrder.do?storeType=1&roleid=1&orderstate=4&navid=292">待发货</a></li>
				</ul></li>
		</ul>
	</div>
</c:if>
<!--02-->
<!--03-->
<c:if test="${account_session_isfacuser==1}">
	<div class="user-menu-list">
		<div class="user-tab">工厂门户</div>
		<ul>
			<li navid=30><i></i><a href="${ctx }/business/factoryInfo.do?&navid=30">基础资料</a></li>
			<li navid=31><i></i><a
				href="${ctx }/business/queryOrder.do?storeType=2&roleid=1&navid=31">我的订单</a>
				<ul>
					<li navid=32><a
						href="${ctx }/business/queryOrder.do?storeType=2&roleid=1&navid=32">个人订单</a></li>
					<li navid=33><a
						href="${ctx }/business/queryOrder.do?storeType=2&roleid=2&navid=33">经销商订单</a></li>
				</ul></li>
		</ul>
	</div>
</c:if>
<script type="text/javascript">
var navid = GetQueryString('navid');		
		//菜单变红效果 
		$("[navid='"+navid+"']").addClass("active");
		</script>
<!--03-->
